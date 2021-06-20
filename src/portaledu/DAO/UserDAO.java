package portaledu.DAO;

import java.util.List;

import portaledu.model.UserModel;

public interface UserDAO {
	
	List<UserModel> getAll();
	List<UserModel> getActive();
	List<UserModel> getInactive();
	List<UserModel> getBlocked();
	UserModel getById(int id);
	UserModel loginIsValid(String user, String pass);
	boolean insert(UserModel obj);
	boolean update(UserModel obj);
	boolean delete(UserModel obj);
	boolean registerLogin(UserModel obj);
	
}
