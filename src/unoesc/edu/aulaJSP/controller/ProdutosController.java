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

import unoesc.edu.aulaJSP.DAO.ProdutoDAO;
import unoesc.edu.aulaJSP.model.Produto;

@ManagedBean(name="produtoMB")
@RequestScoped
public class ProdutosController {
	
	private List<Produto> produtos;
	private Produto produto = new Produto();
	
	@ManagedProperty(value="#{ProdutoDAO}")
	private ProdutoDAO produtoDao;
	

	public void save() {
		System.out.println("Chamou aqui");
		if (produto.getId() == 0) {
			this.produtoDao.insertProduto(produto);
		} else {
			System.out.println("CHegou");
			this.produtoDao.updateProduto(produto);
		}
		this.produto = new Produto();
	}

	public void load(int id) {
		
		this.produto = this.produtoDao.getProdutoById(id);
		System.out.println(this.produto.getId());

	}
	
	public void delete() {
		
		this.produtoDao.deleteProduto(produto);

	}

	
	/* GETTERS E SETTERS*/
	public List<Produto> getProdutos() {
		return this.produtoDao.getAllProdutos();
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDAO getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDAO produtoDao) {
		this.produtoDao = produtoDao;
	}

}
