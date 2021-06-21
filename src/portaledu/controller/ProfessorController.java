package portaledu.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import portaledu.DAO.ProfessorDAO;
import portaledu.model.ProfessorModel;

@RequestScoped
@ManagedBean(name = "professorBean")
public class ProfessorController {
	
	@ManagedProperty(value = "#{ProfessorDAO}")
	private ProfessorDAO professorDao;
	private List<ProfessorModel> professors = null;
	
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
	
	
	
}
