package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unoesc.edu.aulaJSP.model.Usuario;



@Service(value="UsuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Usuario getUsuarioById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Usuario p = (Usuario) session.get(Usuario.class, new Integer(id));
		
		//System.out.println("nome: " + p.getNome());
		
		return p;
	}

	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		
		return this.sessionFactory.getCurrentSession().createQuery("from Usuario").list();
	}

	@Override
	@Transactional
	public boolean deleteUsuario(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Usuario p = (Usuario) session.load(Usuario.class, new Integer(id));
		if (p!=null) {
			session.delete(p);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean insertUsuario(Usuario p) {
		
		this.sessionFactory.getCurrentSession().save(p);
		
		return false;
	}

	@Override
	@Transactional
	public boolean updateUsuario(Usuario p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		return true;
	}

	@Override
	@Transactional
	public Usuario validaLogin(String login, String senha) {
		Session session = this.sessionFactory.getCurrentSession();
		Usuario p = (Usuario) session.createQuery("from Usuario where login=:login and senha=:pwd")
								.setParameter("login", login)
								.setParameter("pwd", senha)
								.uniqueResult();
		return p;
	}

}
