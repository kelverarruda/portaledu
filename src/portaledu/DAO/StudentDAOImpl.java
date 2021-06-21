package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portaledu.model.StudentModel;

@Service(value = "StudentDAO")
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

	@Override
	public List<StudentModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentModel> getActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentModel> getInactive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(StudentModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(StudentModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(StudentModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
