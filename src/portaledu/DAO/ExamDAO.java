package portaledu.DAO;

import java.util.List;

import portaledu.model.ExamModel;

public interface ExamDAO {
	
	List<ExamModel> getAll();
	List<ExamModel> getActive();
	List<ExamModel> getInactive();
	ExamModel getById(int id);
	boolean insert(ExamModel obj);
	boolean update(ExamModel obj);
	boolean delete(ExamModel obj);

}
