package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portaledu.model.ClassModel;
import portaledu.model.ProfessorModel;

@Service(value = "ClassDAO")
public class ClassDAOImpl implements ClassDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

	@Override
	@Transactional
	public List<ClassModel> getAll() {
		return (List<ClassModel>) this.sessionFactory.getCurrentSession().createQuery("FROM ClassModel").getResultList();
	}

	@Override
	@Transactional
	public ClassModel getById(int id) {
		return (ClassModel) this.sessionFactory.getCurrentSession().get(ClassModel.class, new Integer(id));
	}

	@Override
	@Transactional
	public boolean insert(ClassModel obj) {
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
	public boolean update(ClassModel obj) {
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
	public boolean delete(ClassModel obj) {
		if (obj != null) {
			this.sessionFactory.getCurrentSession().delete(obj);
			return true;
		}
		else {
			return false;
		}
	}

	
}
