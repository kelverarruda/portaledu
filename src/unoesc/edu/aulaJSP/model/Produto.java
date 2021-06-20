package unoesc.edu.aulaJSP.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator ="seq_pk_produto")
	private int id;
	
	@Column(name = "nome")
	private String name;
	@Column(name = "detalhes")
	private String detail;
	@Column(name = "marca")
	private String brand;
	@Column(name = "valor")
	private float valor;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "produtos_pedidos", 
//  joinColumns = { @JoinColumn(name = "id_pedido") }, 
//  inverseJoinColumns = { @JoinColumn(name = "id_produto") })
//	private List<Pedido> pedidos = new HashSet<Pedido>();

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}
