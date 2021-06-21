package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portaledu.model.ClassModel;

@Service(value = "ClassDAO")
public class ClassDAOImpl implements ClassDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

	@Override
	public List<ClassModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassModel> getActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassModel> getInactive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(ClassModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ClassModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ClassModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
