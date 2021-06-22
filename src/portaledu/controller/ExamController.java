package portaledu.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import portaledu.DAO.ExamDAO;
import portaledu.DAO.StudentDAO;
import portaledu.model.ExamModel;
import portaledu.model.ProfessorModel;
import portaledu.model.StudentModel;
import portaledu.utils.StatusesEnum;

/**
 * @author kelver.arruda
 *
 */
@RequestScoped
@ManagedBean(name = "examBean")
public class ExamController {
	
	private ExamModel exam = new ExamModel();
	
	@ManagedProperty(value = "#{ExamDAO}")
	private ExamDAO examDao;
	
	@ManagedProperty(value = "#{StudentDAO}")
	private StudentDAO studentDao;
	
	private List<ExamModel> exams;
	private List<StudentModel> students;
	
	
	public StatusesEnum[] getStatusE() {
		return StatusesEnum.values();
	}

	public ExamModel getExam() {
		return exam;
	}

	public void setExam(ExamModel exam) {
		this.exam = exam;
	}

	public ExamDAO getExamDao() {
		return examDao;
	}

	public void setExamDao(ExamDAO examDao) {
		this.examDao = examDao;
	}

	public StudentDAO getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	public List<ExamModel> getExams() {
		exams = examDao.getAll();
		return exams;
	}

	public void setExams(List<ExamModel> exams) {
		this.exams = exams;
	}

	public List<StudentModel> getStudents() {
		students = studentDao.getActive();
		return students;
	}

	public void setStudents(List<StudentModel> students) {
		this.students = students;
	}
	
	
	public void save() {
		examDao.insert(this.exam);
		FacesMessage msg = new FacesMessage("Inserido!", "O professor " + this.exam.getId() + " foi inserido." );
		FacesContext.getCurrentInstance().addMessage(null, msg);
		exam = new ExamModel();
	}
	

	public void onRowEdit(RowEditEvent<ExamModel> event) {
		examDao.update(event.getObject());
        FacesMessage msg = new FacesMessage("Atualizado!", "A prova " + event.getObject().getId() + " foi atualizado." );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ExamModel> event) {
        FacesMessage msg = new FacesMessage("Atualização cancelada!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	

}
