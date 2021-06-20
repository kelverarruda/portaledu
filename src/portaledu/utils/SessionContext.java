package portaledu.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import portaledu.model.UserModel;

public class SessionContext {
	
	private static SessionContext instance;
	
	static {
		try {
			if (instance == null) {
				instance = new SessionContext();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
	}
	
	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		}
		else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}
	
	public SessionContext getInstance() {
		return instance;
	}
	
	public UserModel getUserLogged() {
		return (UserModel) getAttribute("userLogged");
	}
	
	public void setUserLogged(UserModel user) {
		setAttribute("userLogged", user);
	}
	
	public void closeSession( ) {
		currentExternalContext().invalidateSession();
	}
	
	public Object getAttribute(String name) {
		return currentExternalContext().getSessionMap().get(name);
	}
	
	public void setAttribute(String name, Object value){
		currentExternalContext().getSessionMap().put(name, value);
    }
	
}