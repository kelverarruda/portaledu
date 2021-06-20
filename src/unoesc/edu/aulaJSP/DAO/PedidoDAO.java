package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import unoesc.edu.aulaJSP.model.Pedido;

public interface PedidoDAO {
	
	List<Pedido> getAllPedidos(); //retorna todos os pedidos
	Pedido getPedidoById(int id); //retornar um pedido com base no id
	void insertPedido(Pedido cli); //insere pedido
	void updatePedido(Pedido cli); //atualiza
	void deletePedido(Pedido cli);
	void insertItensPedido(Pedido cli);
	

}
