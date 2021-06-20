package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import portaledu.model.UserModel;
import portaledu.utils.StatusEnum;

public class UserDAOImpl extends GenericDAOImpl<UserModel, Long> {
	
	@Transactional
	public Boolean loginIsValid(String user, String pass) {
		if (user.isEmpty() || pass.isEmpty()) {
			addMessage(FacesMessage.SEVERITY_WARN,"Aviso!", "Usu�rio ou senha n�o informados.");
			return false;
		} else {
			try {			
				UserModel u = (UserModel) em.createQuery("SELECT u FROM UserModel u WHERE username = :user AND password = :pass", UserModel.class)
						.setParameter("user", user)
						.setParameter("pass", pass)
						.getSingleResult();
				
				if (u != null && u.getStatus() == StatusEnum.ACTIVE) {
					return true;
				} else if (u.getStatus() == StatusEnum.BLOCKED) {
					addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usu�rio est� bloqueado. Por favor, entre em contato.");
				} else if (u.getStatus() == StatusEnum.INACTIVE) {
					addMessage(FacesMessage.SEVERITY_INFO, "Informa��o", "Usu�rio est� inativo. Por favor, entre em contato.");
				} else {
					addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usu�rio ou senha inv�lidos.");
				}
				return false;
				
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
		}
	}
	
	public Boolean registerForm(UserModel user) {
		if(user.getFullname().isEmpty() || user.getEmail().isEmpty() || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			addMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Todos os campos devem ser preenchidos!");
			return false;
		} else {
			try {
				insert(user);
				addMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Seu usu�rio ser� liberado por um administrador do sistema. Obrigado!");
				return true;
			} catch (Exception e) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro cadastrar usu�rio.");
				return false;
			}
		}
	}
	
	public List<UserModel> getBlocked(Class<UserModel> cla){
		List<UserModel> list = (List<UserModel>) em.createQuery("FROM " + cla.getName() + " WHERE status = 'BLOCKED' ").getResultList();
		return list;
	}
		
}
