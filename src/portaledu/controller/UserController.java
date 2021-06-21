package portaledu.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import portaledu.DAO.UserDAOImpl;
import portaledu.model.UserModel;

@RequestScoped
@ManagedBean(name = "userBean")
public class UserController {
		
	private UserDAOImpl DAO = new UserDAOImpl();
	private UserModel selectedUser;
	private List<UserModel> list;
	private List<UserModel> selectedList;
	
	public UserModel getSelectedUser() {
		return selectedUser;
	}
	
    public void setSelectedUser(UserModel user) {
        this.selectedUser = user;
    }
    
	public List<UserModel> getList() {
		list = DAO.getAll();
		return list;
	}
	
    public void setList(List<UserModel> list) {
		this.list = list;
	}

	public List<UserModel> getSelectedUsers() {
        return selectedList;
    }

    public void setSelectedUsers(List<UserModel> selUsers) {
        this.selectedList = selUsers;
    }

    public void openNew() {
        this.selectedUser = new UserModel();
    }

    public void saveUser() {
    	if (this.selectedUser.getId() == null) {
    		DAO.insert(this.selectedUser);
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado!"));
    	} else if (this.selectedUser.getId() != null){
    		DAO.update(this.selectedUser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário atualizado!"));
        }
    	
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public void deleteUser() {
    	DAO.delete(this.selectedUser);
        this.selectedUser = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário excluído!"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedUsers()) {
            int size = this.selectedList.size();
            return size > 1 ? size + " Usuários selecionados" : "1 Usuário selecionado";
        }

        return "Excluir";
    }

    public boolean hasSelectedUsers() {
        return this.selectedList != null && !this.selectedList.isEmpty();
    }

    public void deleteSelectedUsers() {
        this.list.removeAll(this.selectedList);
        this.selectedList = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuários excluídos!"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
        PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");
    }



}
