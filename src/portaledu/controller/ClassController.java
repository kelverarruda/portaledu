package portaledu.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import portaledu.DAO.ClassDAO;
import portaledu.DAO.ProfessorDAO;
import portaledu.DAO.StudentDAO;
import portaledu.model.ClassModel;
import portaledu.model.ProfessorModel;
import portaledu.model.StudentModel;

@RequestScoped
@ManagedBean(name = "classBean")
public class ClassController {
	
	private ClassModel classe = new ClassModel();
	
	@ManagedProperty(value = "#{ClassDAO}")
	private ClassDAO classDao;
	
	@ManagedProperty(value = "#{ProfessorDAO}")
	private ProfessorDAO professorDao;
	
	@ManagedProperty(value = "#{StudentDAO}")
	private StudentDAO studentDao;
	
	private List<ClassModel> classes = null;
	private List<ProfessorModel> professors = null;
	private List<StudentModel> students = null;
	
	
	public ClassModel getClasse() {
		return classe;
	}
	
	public void setClasse(ClassModel classe) {
		this.classe = classe;
	}
	
	public ClassDAO getClassDao() {
		return classDao;
	}

	public void setClassDao(ClassDAO classDao) {
		this.classDao = classDao;
	}

	public ProfessorDAO getProfessorDao() {
		return professorDao;
	}
	
	public void setProfessorDao(ProfessorDAO professorDao) {
		this.professorDao = professorDao;
	}
	
	public StudentDAO getStudentDao() {
		return studentDao;
	}
	
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	public List<ClassModel> getClasses() {
		if (classes == null) {
			classes = classDao.getAll();
		}
		return classes;
	}

	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
	}

	public List<ProfessorModel> getProfessors() {
		if (professors == null) {
			professors = professorDao.getAll();
		}
		return professors;
	}
	
	public void setProfessors(List<ProfessorModel> professors) {
		this.professors = professors;
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
		classDao.insert(this.classe);
		FacesMessage msg = new FacesMessage("Inserido!", "O professor " + this.classe.getClassname() + " foi inserido." );
		FacesContext.getCurrentInstance().addMessage(null, msg);
		classe = new ClassModel();
	}
	
	public void onRowEdit(RowEditEvent<ClassModel> event) {
		classDao.update(event.getObject());
        FacesMessage msg = new FacesMessage("Atualizado!", "A turma " + event.getObject().getId() + " foi atualizado." );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ClassModel> event) {
        FacesMessage msg = new FacesMessage("Atualização cancelada!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
