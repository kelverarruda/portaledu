package portaledu.DAO;

import java.util.List;

import portaledu.model.StudentModel;

public interface StudentDAO {
	
	List<StudentModel> getAll();
	List<StudentModel> getActive();
	List<StudentModel> getInactive();
	StudentModel getById(int id);
	boolean insert(StudentModel obj);
	boolean update(StudentModel obj);
	boolean delete(StudentModel obj);

}
