package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unoesc.edu.aulaJSP.model.Produto;

@Service(value="ProdutoDAO")
public class ProdutoDAOImpl implements ProdutoDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Produto> getAllProdutos() {
		Session session = sessionFactory.getCurrentSession();
		List<Produto> produtos = (List) session.createQuery("FROM Produto").list();
		return produtos;
	}

	@Override
	@Transactional
	public Produto getProdutoById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Produto c = session.get(Produto.class, id);
		return c;
	}

	@Override
	@Transactional
	public void insertProduto(Produto cli) {
		Session session = sessionFactory.getCurrentSession();
		session.save(cli);		
	}

	@Override
	@Transactional
	public void updateProduto(Produto cli) {
		Session session = sessionFactory.getCurrentSession();
		session.update(cli);
		
	}

	@Override
	@Transactional
	public void deleteProduto(Produto cli) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cli);
		
	}

}
