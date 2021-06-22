package portaledu.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import portaledu.DAO.UserDAO;
import portaledu.model.UserModel;
import portaledu.utils.StatusesEnum;
import portaledu.utils.UserTypeEnum;

@RequestScoped
@ManagedBean(name = "userBean")
public class UserController {
	
	private UserModel user = new UserModel();
	
	@ManagedProperty(value="#{UserDAO}")
	private UserDAO userDao;
	private List<UserModel> users;
	
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	public List<UserModel> getUsers() {
		users = userDao.getAll();
		return users;
	}
	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
	
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public UserTypeEnum[] getUserTypes() {
		return UserTypeEnum.values();
	}
	
	public StatusesEnum[] getStatusE() {
		return StatusesEnum.values();
	}
	
	public void onRowEdit(RowEditEvent<UserModel> event) {
		userDao.update(event.getObject());
        FacesMessage msg = new FacesMessage("Atualizado!", "O usuário " + event.getObject().getUsername() + " foi atualizado." );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<UserModel> event) {
    	
        FacesMessage msg = new FacesMessage("Atualização cancelada!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}
