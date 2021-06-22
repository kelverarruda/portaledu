package portaledu.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.usertype.UserType;
import org.primefaces.PrimeFaces;
import org.springframework.dao.support.DaoSupport;

import portaledu.DAO.UserDAO;
import portaledu.DAO.UserDAOImpl;
import portaledu.model.UserModel;
import portaledu.service.UserService;
import portaledu.utils.SessionContext;
import portaledu.utils.StatusesEnum;
import portaledu.utils.UserTypeEnum;

@RequestScoped
@ManagedBean(name = "loginBean")
public class LoginController {
	
	private UserModel user = new UserModel();
	
	@ManagedProperty(value="#{UserDAO}")
	private UserDAO userDao;
	
	private boolean isLogged = false;
	private boolean isRoot = false;
	
	public LoginController() throws IOException {
		UserModel user = (UserModel) SessionContext.getInstance().getAttribute("userLogged");
			if (user != null) {
				isLogged = true;
				
				if (user.getUsertype() == UserTypeEnum.ADMIN) {
					isRoot = true;
				}
				else {
					isRoot = false;
				}
			}
			
		}
	
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
	
	public void doLogin() throws IOException {
		
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Usuário ou senha não informados."));
			return;
		} else {
			user = userDao.loginIsValid(user.getUsername(), hashPass(user.getPassword()));
			if (user == null) {
				isLogged = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usuário ou senha inválidos."));
				return;
			} else if (user != null && user.getStatus() == StatusesEnum.ACTIVE) {
				isLogged = true;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login OK!", "Bem vindo, " + this.user.getFullname()));
				SessionContext.getInstance().setAttribute("userLogged", user);
			} else if (user.getStatus() == StatusesEnum.BLOCKED) {
				isLogged = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usuário está bloqueado. Por favor, entre em contato."));
				return;
			} else if (user.getStatus() == StatusesEnum.INACTIVE) {
				isLogged = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Usuário está inativo. Por favor, entre em contato."));
				return;
			} else {
				isLogged = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Desconhecido!", "Entre em contato com o administrador."));
				return;
			}
		}
		
		// PrimeFaces.current().ajax().addCallbackParam("loggedIn", isLogged);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		
		ec.redirect("index.xhtml");
		
	}
	
	public void doLogout() throws IOException {
		
		FacesMessage message = null;
		
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout!","");
		SessionContext.getInstance().setAttribute("userLogged", null);
		isLogged = false;
		isRoot = false;
		user = new UserModel();
		
		FacesContext.getCurrentInstance().addMessage(null, message);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect("login.xhtml");
		
	    // ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		
	}

	public void doRegister() {
		
		if (this.user.getFullname().isEmpty() || this.user.getEmail().isEmpty() ||
			this.user.getUsername().isEmpty() || this.user.getPassword().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Todos os campos devem ser preenchidos!"));
			return;
		} else {
			this.user.setPassword(hashPass(this.getUser().getPassword()));
			this.user.setStatus(StatusesEnum.INACTIVE);
			
			if (user.getId() == null) {
				this.userDao.insert(user);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Seu usuário será liberado por um administrador do sistema. Obrigado!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro cadastrar usuário."));
				return;
			}
		}
		
		user = new UserModel();
		
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