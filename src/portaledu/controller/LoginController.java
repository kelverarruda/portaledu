package portaledu.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import portaledu.DAO.UserDAO;
import portaledu.model.UserModel;
import portaledu.utils.SessionContext;
import portaledu.utils.UserTypeEnum;

@RequestScoped
@ManagedBean(name = "loginBean")
public class LoginController {
	
	private UserModel user = new UserModel();
	
	@ManagedProperty(value="#{GenericDAO}")
	private UserDAO userDao;
	
	private boolean isLogged = false;
	private boolean isRoot = false;
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
		
	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public UserTypeEnum[] getUserTypes() {
		return UserTypeEnum.values();
	}
	
	public String doLogin() throws Exception {
		user = userDao.loginIsValid(user.getUsername(), hashPass(user.getPassword()));
		if (user != null) {
			isLogged = true;
			SessionContext.getInstance().setAttribute("userLogged", user);
		} else {
			isLogged = false;
			SessionContext.getInstance().setAttribute("userLogged", null);
			user = new UserModel();
		}
		
		return "";	
	}
	
	public String doLogout() {
		return "index?faces-redirect=true";
	}
	
	public String doRegister() {
		if (user.getFullname().isEmpty() || user.getEmail().isEmpty() ||
			user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Todos os campos devem ser preenchidos!"));
		} else {
			user.setPassword(hashPass(user.getPassword()));
			// user.setStatus(StatusEnum.INACTIVE);
			if (user.getId() == null) {
				userDao.insert(user);
			} else {
				userDao.update(user);
			}
			
		}
		
		return "";
	}
	
	public String hashPass(String pass) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
			byte[] passCrypt = mDigest.digest(pass.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder(2 * passCrypt.length);
			for (int i = 0; i < passCrypt.length; i++) {
				String hex = Integer.toHexString(0xff & passCrypt[i]);
				if(hex.length() == 1) {
					sb.append("0");
				}
				sb.append(hex);
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	
}