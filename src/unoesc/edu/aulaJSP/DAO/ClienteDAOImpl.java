package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unoesc.edu.aulaJSP.model.Cliente;

@Service(value="ClienteDAO")
public class ClienteDAOImpl implements ClienteDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Cliente> getAllClientes() {
		Session session = sessionFactory.getCurrentSession();
		List<Cliente> clientes = (List) session.createQuery("FROM Cliente").list();
		return clientes;
	}

	@Override
	@Transactional
	public Cliente getClienteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Cliente c = session.get(Cliente.class, id);
		return c;
	}

	@Override
	@Transactional
	public void insertCliente(Cliente cli) {
		Session session = sessionFactory.getCurrentSession();
		session.save(cli);		
	}

	@Override
	@Transactional
	public void updateCliente(Cliente cli) {
		Session session = sessionFactory.getCurrentSession();
		session.update(cli);
		
	}

	@Override
	@Transactional
	public void deleteCliente(Cliente cli) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cli);
		
	}

}
