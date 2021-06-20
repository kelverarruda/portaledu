package portaledu.DAO;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "#{GenericDAO}")
public class GenericDAOImpl<T> implements GenericDAO<T>{
	
	@Autowired
	SessionFactory sessionFactory;
	
	protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
	
	@Override
	@Transactional
	public List<T> getAll(Class<T> cla) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		List<T> list = (List<T>) session.createQuery("FROM " + cla.getName()).getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<T> getActive(Class<T> cla) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		List<T> list = (List<T>) session.createQuery("FROM " + cla.getName() + " WHERE status = 'ACTIVE' ").getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<T> getInactive(Class<T> cla) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		List<T> list = (List<T>) session.createQuery("FROM " + cla.getName() + " WHERE status = 'INACTIVE' ").getResultList();
		return list;
	}

	@Override
	@Transactional
	public T getById(Class<T> cla, int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		T t = (T) session.get(cla.getName(), id);
		return t;
	}

	@Override
	@Transactional
	public void insert(T obj) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(obj);	
	}

	@Override
	@Transactional
	public void update(T obj) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(obj);
	}

	@Override
	@Transactional
	public void delete(T obj) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(obj);		
	}
	
}
