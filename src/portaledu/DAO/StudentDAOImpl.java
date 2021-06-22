package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<StudentModel> getActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<StudentModel> getInactive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public StudentModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean insert(StudentModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean update(StudentModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(StudentModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
