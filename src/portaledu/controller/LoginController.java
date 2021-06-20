package portaledu.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;

import portaledu.DAO.UserDAOImpl;
import portaledu.model.UserModel;
// import portaledu.utils.SessionContext;
import portaledu.utils.StatusEnum;


@RequestScoped
@ManagedBean(name = "loginBean")
public class LoginController {
	
	private UserModel user = new UserModel();
	@ManagedProperty(value="#{GenericDAO}")
	private UserDAOImpl userDao;
	private boolean isLogged = false;
	private boolean isRoot = false;
	
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public UserDAOImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAOImpl userDao) {
		this.userDao = userDao;
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
	
	public String doLogin() {
		if (userDao.loginIsValid(user.getUsername(), hashPass(user.getPassword()))) {
			try {
				// session.setUserLogged(user);
				return "home?faces-redirect=true";
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}		
		return "";	
	}
	
	public String doLogout() {
		return "index?faces-redirect=true";
	}
	
	public String doRegister() {
		user.setPassword(hashPass(user.getPassword()));
		
		user.setStatus(StatusEnum.INACTIVE);
		
		if (userDao.registerForm(user)) {
			user = null;
		}
		return "";
	}
	
	
}