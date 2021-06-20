package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unoesc.edu.aulaJSP.model.ItemPedido;
import unoesc.edu.aulaJSP.model.Pedido;

@Service(value="PedidoDAO")
public class PedidoDAOImpl implements PedidoDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Pedido> getAllPedidos() {
		Session session = sessionFactory.getCurrentSession();
		List<Pedido> pedidos = (List) session.createQuery("FROM Pedido").list();
		return pedidos;
	}

	@Override
	@Transactional
	public Pedido getPedidoById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Pedido c = session.get(Pedido.class, id);
		return c;
	}

	@Override
	@Transactional
	public void insertPedido(Pedido cli) {
		Session session = sessionFactory.getCurrentSession();
		session.save(cli);		
	}

	@Override
	@Transactional
	public void updatePedido(Pedido cli) {
		Session session = sessionFactory.getCurrentSession();
		session.update(cli);
		
	}

	@Override
	@Transactional
	public void deletePedido(Pedido cli) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cli);
		
	}

	@Override
	@Transactional
	public void insertItensPedido(Pedido cli) {
		Session session = sessionFactory.getCurrentSession();
		for (ItemPedido item: cli.getItens())
			session.save(item);
		
	}

}
