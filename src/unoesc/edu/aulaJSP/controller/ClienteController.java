package unoesc.edu.aulaJSP.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
import unoesc.edu.aulaJSP.model.Cliente;

@ManagedBean(name="clienteMB")
@RequestScoped
public class ClienteController {
	
	private List<Cliente> listClientes = null;
	private Cliente cli = new Cliente();
	
	@ManagedProperty(value="#{ClienteDAO}")
	private ClienteDAO clienteDao;
	


	public void save() {
		if (cli.getId() == 0) {
			this.clienteDao.insertCliente(cli);
		} else {
			this.clienteDao.updateCliente(cli);
		}
		
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente " + cli.getNome()+ " cadastrado!"));
		
		this.cli = new Cliente();
		
	}

	public void edit(int id) {
		//List<Cliente> clientes = this.clienteDao.getAllClientes();

		this.cli = this.clienteDao.getClienteById(id);

	}
	

	public List<Cliente> getListClientes() {
		if (this.listClientes == null)
			this.listClientes = this.clienteDao.getAllClientes();
		return this.listClientes;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public ClienteDAO getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}

}
