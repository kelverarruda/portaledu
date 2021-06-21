package portaledu.DAO;

import java.util.List;

import portaledu.model.ProfessorModel;

public interface ProfessorDAO {
	
	List<ProfessorModel> getAll();
	List<ProfessorModel> getActive();
	List<ProfessorModel> getInactive();
	ProfessorModel getById(int id);
	boolean insert(ProfessorModel obj);
	boolean update(ProfessorModel obj);
	boolean delete(ProfessorModel obj);

}
