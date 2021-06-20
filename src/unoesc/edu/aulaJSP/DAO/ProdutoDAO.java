package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import unoesc.edu.aulaJSP.model.Produto;

public interface ProdutoDAO {
	
	List<Produto> getAllProdutos(); //retorna todos os produtos
	Produto getProdutoById(int id); //retornar um produto com base no id
	void insertProduto(Produto cli); //insere produto
	void updateProduto(Produto cli); //atualiza
	void deleteProduto(Produto cli);
	

}
