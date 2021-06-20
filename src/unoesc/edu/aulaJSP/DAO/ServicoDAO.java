package unoesc.edu.aulaJSP.DAO;

import java.util.List;
import java.util.Set;

import unoesc.edu.aulaJSP.model.Servico;

public interface ServicoDAO {
	
	Set<Servico> getAllServicos(); //retorna todos os produtos
	Servico getServicoById(int id); //retornar um produto com base no id
	void insertServico(Servico cli); //insere produto
	void updateServico(Servico cli); //atualiza
	void deleteServico(Servico cli);
	

}
