package portaledu.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import portaledu.DAO.StudentDAO;
import portaledu.model.ProfessorModel;
import portaledu.model.StudentModel;
import portaledu.utils.StatusesEnum;

@RequestScoped
@ManagedBean(name = "studentBean")
public class StudentController {
	
	private StudentModel student = new StudentModel();
	@ManagedProperty(value = "#{StudentDAO}")
	private StudentDAO studentDao;
	private List<StudentModel> students = null;
	
	public StatusesEnum[] getStatusE() {
		return StatusesEnum.values();
	}
	
	public StudentModel getStudent() {
		return student;
	}
	
	public void setStudent(StudentModel student) {
		this.student = student;
	}
	
	public StudentDAO getStudentDao() {
		return studentDao;
	}
	
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	public List<StudentModel> getStudents() {
		if (students == null) {
			students = studentDao.getAll();
		}
		return students;
	}
	
	public void setStudents(List<StudentModel> students) {
		this.students = students;
	}
	
	public void save() {
		studentDao.insert(this.student);
		FacesMessage msg = new FacesMessage("Inserido!", "O professor " + this.student.getFullname() + " foi inserido." );
		FacesContext.getCurrentInstance().addMessage(null, msg);
		student = new StudentModel();
	}
	
	public void onRowEdit(RowEditEvent<StudentModel> event) {
		studentDao.update(event.getObject());
        FacesMessage msg = new FacesMessage("Atualizado!", "O aluno " + event.getObject().getFullname() + " foi atualizado." );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<StudentModel> event) {
        FacesMessage msg = new FacesMessage("Atualização cancelada!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	

}
