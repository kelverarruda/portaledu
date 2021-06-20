package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import portaledu.model.UserModel;
import portaledu.utils.StatusEnum;


public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
	
	@Override
	@Transactional
	public List<UserModel> getAll() {
		return (List<UserModel>) sessionFactory.getCurrentSession().createQuery("FROM UserModel").getResultList();
	}

	@Override
	@Transactional
	public List<UserModel> getActive() {
		return (List<UserModel>) sessionFactory.getCurrentSession().createQuery("FROM UserModel WHERE status = 'ACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public List<UserModel> getInactive() {
		return (List<UserModel>) sessionFactory.getCurrentSession().createQuery("FROM UserModel WHERE status = 'INACTIVE' ").getResultList();
	}
	
	@Override
	@Transactional
	public List<UserModel> getBlocked(){
		return (List<UserModel>) sessionFactory.getCurrentSession().createQuery("FROM UserModel WHERE status = 'BLOCKED' ").getResultList();
	}

	@Override
	@Transactional
	public UserModel getById(int id) {
		return (UserModel) sessionFactory.getCurrentSession().get(UserModel.class, new Integer(id));
	}

	@Override
	@Transactional
	public boolean insert(UserModel obj) {
		sessionFactory.getCurrentSession().save(obj);
		return true;
	}

	@Override
	@Transactional
	public boolean update(UserModel obj) {
		try {
			sessionFactory.getCurrentSession().update(obj);
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
			sessionFactory.getCurrentSession().delete(obj);
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
			addMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Usuário ou senha não informados.");
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
					addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usuário está bloqueado. Por favor, entre em contato.");
				} else if (u.getStatus() == StatusEnum.INACTIVE) {
					addMessage(FacesMessage.SEVERITY_INFO, "Informação", "Usuário está inativo. Por favor, entre em contato.");
				} else {
					addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usuário ou senha inválidos.");
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return null;
	}

	@Override
	public boolean registerLogin(UserModel obj) {
		try {
			insert(obj);
			addMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Seu usuário será liberado por um administrador do sistema. Obrigado!");
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		addMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro cadastrar usuário.");
		return false;
	}
		
}
