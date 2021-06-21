package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portaledu.model.ExamModel;

@Service(value = "ExamDAO")
public class ExamDAOImpl implements ExamDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

	@Override
	public List<ExamModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamModel> getActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamModel> getInactive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(ExamModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ExamModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ExamModel obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
