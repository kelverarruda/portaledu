package unoesc.edu.aulaJSP.DAO;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unoesc.edu.aulaJSP.model.Servico;

@Service(value="ServicoDAO")
public class ServicoDAOImpl implements ServicoDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public Set<Servico> getAllServicos() {
		Session session = sessionFactory.getCurrentSession();
		Set<Servico> servicos = (Set) session.createQuery("FROM Servico").list();
		return servicos;
	}

	@Override
	@Transactional
	public Servico getServicoById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Servico c = session.get(Servico.class, id);
		return c;
	}

	@Override
	public void insertServico(Servico cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateServico(Servico cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteServico(Servico cli) {
		// TODO Auto-generated method stub
		
	}

}
