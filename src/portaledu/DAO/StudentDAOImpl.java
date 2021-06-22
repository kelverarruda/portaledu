package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portaledu.model.ProfessorModel;
import portaledu.model.StudentModel;

@Service(value = "StudentDAO")
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

	@Override
	@Transactional
	public List<StudentModel> getAll() {
		return (List<StudentModel>) this.sessionFactory.getCurrentSession().createQuery("FROM StudentModel").getResultList();
	}

	@Override
	@Transactional
	public List<StudentModel> getActive() {
		return (List<StudentModel>) this.sessionFactory.getCurrentSession().createQuery("FROM StudentModel WHERE status = 'ACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public List<StudentModel> getInactive() {
		return (List<StudentModel>) this.sessionFactory.getCurrentSession().createQuery("FROM StudentModel WHERE status = 'INACTIVE' ").getResultList();
	}

	@Override
	@Transactional
	public StudentModel getById(int id) {
		return (StudentModel) this.sessionFactory.getCurrentSession().get(StudentModel.class, new Integer(id));
	}

	@Override
	@Transactional
	public boolean insert(StudentModel obj) {
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
	public boolean update(StudentModel obj) {
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
	public boolean delete(StudentModel obj) {
		try {
			this.sessionFactory.getCurrentSession().delete(obj);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return false;
	}

	
}
