package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portaledu.model.UserModel;
import portaledu.utils.StatusEnum;

@Service(value = "UserDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
	
	@Override
	@Transactional
	public List<UserModel> getAll() {
		return (List<UserModel>) this.sessionFactory.getCurrentSession().createQuery("FROM UserModel").getResultList();
	}

	@Override
	@Transactional
	public List<UserModel> getActive() {
		return (List<UserModel>) this.sessionFactory.getCurrentSession().createQuery("FROM UserModel WHERE status = 'ACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public List<UserModel> getInactive() {
		return (List<UserModel>) this.sessionFactory.getCurrentSession().createQuery("FROM UserModel WHERE status = 'INACTIVE' ").getResultList();
	}
	
	@Override
	@Transactional
	public List<UserModel> getBlocked(){
		return (List<UserModel>) this.sessionFactory.getCurrentSession().createQuery("FROM UserModel WHERE status = 'BLOCKED' ").getResultList();
	}

	@Override
	@Transactional
	public UserModel getById(int id) {
		return (UserModel) this.sessionFactory.getCurrentSession().get(UserModel.class, new Integer(id));
	}

	@Override
	@Transactional
	public boolean insert(UserModel obj) {
		if (obj != null) {
			this.sessionFactory.getCurrentSession().save(obj);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(UserModel obj) {
		try {
			this.sessionFactory.getCurrentSession().update(obj);
			return true;			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean delete(UserModel obj) {
		try {
			this.sessionFactory.getCurrentSession().delete(obj);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return false;
	}

	@Override
	@Transactional
	public UserModel loginIsValid(String user, String pass) {
		if (user.isEmpty() || pass.isEmpty()) {
			addMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Usu�rio ou senha n�o informados.");
			return null;
		} else {
			try {
				Session session = sessionFactory.getCurrentSession();
				UserModel u = (UserModel) session.createQuery("FROM UserModel WHERE username = :user AND password = :pass")
						.setParameter("user", user)
						.setParameter("pass", pass)
						.uniqueResult();
				
				if (u != null && u.getStatus() == StatusEnum.ACTIVE) {
					return u;
				} else if (u.getStatus() == StatusEnum.BLOCKED) {
					addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usu�rio est� bloqueado. Por favor, entre em contato.");
				} else if (u.getStatus() == StatusEnum.INACTIVE) {
					addMessage(FacesMessage.SEVERITY_INFO, "Informa��o", "Usu�rio est� inativo. Por favor, entre em contato.");
				} else {
					addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usu�rio ou senha inv�lidos.");
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return null;
	}

	@Override
	@Transactional
	public boolean registerLogin(UserModel obj) {
		if (obj.getId() == null) {
			insert(obj);
			return true;
		} 			
		
		return false;
	}
		
}
