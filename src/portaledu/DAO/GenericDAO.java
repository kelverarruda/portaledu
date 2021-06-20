package portaledu.DAO;

import java.util.List;



public interface GenericDAO<T> {
	
	List<T> getAll(Class<T> cla) throws Exception;
	List<T> getActive(Class<T> cla) throws Exception;
	List<T> getInactive(Class<T> cla) throws Exception;
	T getById(Class<T> cla, int id) throws Exception;
	void insert(T obj) throws Exception;
	void update(T obj) throws Exception;
	void delete(T obj) throws Exception;
	
}
