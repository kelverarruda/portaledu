package unoesc.edu.aulaJSP.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import unoesc.edu.aulaJSP.DAO.ClienteDAO;
import unoesc.edu.aulaJSP.DAO.PedidoDAO;
import unoesc.edu.aulaJSP.DAO.ProdutoDAO;
import unoesc.edu.aulaJSP.model.Cliente;
import unoesc.edu.aulaJSP.model.ItemPedido;
import unoesc.edu.aulaJSP.model.Pedido;

@ManagedBean(name="pedidoMB")
@RequestScoped
public class PedidosController {
	
	private List<Pedido> pedidos;
	private List<Cliente> clientes;
	private Pedido pedido = new Pedido();;
	
	@ManagedProperty(value="#{PedidoDAO}")
	private PedidoDAO pedidoDao;
	
	@ManagedProperty(value="#{ProdutoDAO}")
	private ProdutoDAO produtoDao;
	
	@ManagedProperty(value="#{ClienteDAO}")
	private ClienteDAO clienteDao;
	

	
	public void save() {
		

		if (pedido.getId() == 0) {
			this.pedidoDao.insertPedido(pedido);
		} else {
			this.pedidoDao.updatePedido(pedido);
		}
		
		this.pedido = new Pedido();

	}
	
	
	



	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}

	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	public List<Pedido> getPedidos() {
		return this.pedidoDao.getAllPedidos();
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public ProdutoDAO getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDAO produtoDao) {
		this.produtoDao = produtoDao;
	}

	public ClienteDAO getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Cliente> getClientes() {
		return this.clienteDao.getAllClientes();
	}

	public void setClientes(List<Cliente> cliente) {
		this.clientes = cliente;
	}
	
}
