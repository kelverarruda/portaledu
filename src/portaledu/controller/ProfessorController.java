package portaledu.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import portaledu.DAO.ProfessorDAO;
import portaledu.model.ProfessorModel;
import portaledu.utils.StatusesEnum;

@RequestScoped
@ManagedBean(name = "professorBean")
public class ProfessorController {
	
	private ProfessorModel professor = new ProfessorModel();
	
	@ManagedProperty(value = "#{ProfessorDAO}")
	private ProfessorDAO professorDao;
	
	private List<ProfessorModel> professors = null;
	
		
	public ProfessorModel getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorModel professor) {
		this.professor = professor;
	}

	public ProfessorDAO getProfessorDao() {
		return professorDao;
	}
	
	public void setProfessorDao(ProfessorDAO professorDao) {
		this.professorDao = professorDao;
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
	
	public StatusesEnum[] getStatusE() {
		return StatusesEnum.values();
	}
	
	public void save() {
		professorDao.insert(this.professor);
		FacesMessage msg = new FacesMessage("Inserido!", "O professor " + this.professor.getFullname() + " foi inserido." );
		FacesContext.getCurrentInstance().addMessage(null, msg);
		professor = new ProfessorModel();
	}
	
	public void onRowEdit(RowEditEvent<ProfessorModel> event) {
		professorDao.update(event.getObject());
        FacesMessage msg = new FacesMessage("Atualizado!", "O professor " + event.getObject().getFullname() + " foi atualizado." );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ProfessorModel> event) {
    	
        FacesMessage msg = new FacesMessage("Atualização cancelada!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	
}
