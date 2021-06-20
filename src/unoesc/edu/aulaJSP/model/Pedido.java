package unoesc.edu.aulaJSP.model;

import java.util.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator ="seq_pk_pedidos")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")//
	private Cliente cliente;
	
	@Column(name = "data")
	private Date data;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean faturado;
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean entregue;
	
	//@ManyToMany(mappedBy = "produtos")
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<ItemPedido> itens;
	
	public Pedido() {
		this.itens = new LinkedList<ItemPedido>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date dataHora) {
		this.data = dataHora;
	}
	public Boolean getFaturado() {
		return faturado;
	}
	public void setFaturado(Boolean faturado) {
		this.faturado = faturado;
	}
	public Boolean getEntregue() {
		return entregue;
	}
	public void setEntregue(Boolean entregue) {
		this.entregue = entregue;
	}
	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> items) {
		this.itens = items;
	}
	
	
	
}
