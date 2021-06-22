package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portaledu.model.ClassModel;
import portaledu.model.ExamModel;
import portaledu.model.ProfessorModel;

@Service(value = "ExamDAO")
public class ExamDAOImpl implements ExamDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

	@Override
	@Transactional
	public List<ExamModel> getAll() {
		return (List<ExamModel>) this.sessionFactory.getCurrentSession().createQuery("FROM ExamModel").getResultList();
	}

	@Override
	@Transactional
	public List<ExamModel> getActive() {
		return (List<ExamModel>) this.sessionFactory.getCurrentSession().createQuery("FROM ExamModel WHERE status = 'ACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public List<ExamModel> getInactive() {
		return (List<ExamModel>) this.sessionFactory.getCurrentSession().createQuery("FROM ExamModel WHERE status = 'INACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public ExamModel getById(int id) {
		return (ExamModel) this.sessionFactory.getCurrentSession().get(ExamModel.class, new Integer(id));
	}

	@Override
	@Transactional
	public boolean insert(ExamModel obj) {
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
	public boolean update(ExamModel obj) {
		if (obj != null) {
			this.sessionFactory.getCurrentSession().update(obj);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(ExamModel obj) {
		if (obj != null) {
			this.sessionFactory.getCurrentSession().delete(obj);
			return true;
		}
		else {
			return false;
		}
	}

}
