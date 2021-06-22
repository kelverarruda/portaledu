package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portaledu.model.ProfessorModel;

@Service(value = "ProfessorDAO")
public class ProfessorDAOImpl implements ProfessorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

	@Override
	@Transactional
	public List<ProfessorModel> getAll() {
		return (List<ProfessorModel>) this.sessionFactory.getCurrentSession().createQuery("FROM ProfessorModel").getResultList();
	}

	@Override
	@Transactional
	public List<ProfessorModel> getActive() {
		return (List<ProfessorModel>) this.sessionFactory.getCurrentSession().createQuery("FROM ProfessorModel WHERE status = 'ACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public List<ProfessorModel> getInactive() {
		return (List<ProfessorModel>) this.sessionFactory.getCurrentSession().createQuery("FROM ProfessorModel WHERE status = 'INACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public ProfessorModel getById(int id) {
		return (ProfessorModel) this.sessionFactory.getCurrentSession().get(ProfessorModel.class, new Integer(id));
	}

	@Override
	@Transactional
	public boolean insert(ProfessorModel obj) {
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
	public boolean update(ProfessorModel obj) {
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
	public boolean delete(ProfessorModel obj) {
		try {
			this.sessionFactory.getCurrentSession().delete(obj);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return false;
	}

}
