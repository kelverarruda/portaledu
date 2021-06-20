package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import unoesc.edu.aulaJSP.model.Cliente;

public interface ClienteDAO {
	
	List<Cliente> getAllClientes(); //retorna todos os clientes
	Cliente getClienteById(int id); //retornar um cliente com base no id
	void insertCliente(Cliente cli); //insere cliente
	void updateCliente(Cliente cli); //atualiza
	void deleteCliente(Cliente cli);
	

}
