package portaledu.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import portaledu.DAO.ExamDAOImpl;
import portaledu.DAO.ProfessorDAOImpl;
import portaledu.model.ProfessorModel;

@ViewScoped
@ManagedBean(name = "professorBean")
public class ProfessorController {
	
	private ProfessorDAOImpl DAO = new ProfessorDAOImpl();
	private ExamDAOImpl exam;
	private ProfessorModel prof;
	
	
}
