package portaledu.DAO;

import java.util.List;

import portaledu.model.ClassModel;

public interface ClassDAO {
	
	List<ClassModel> getAll();
	List<ClassModel> getActive();
	List<ClassModel> getInactive();
	ClassModel getById(int id);
	boolean insert(ClassModel obj);
	boolean update(ClassModel obj);
	boolean delete(ClassModel obj);

}
