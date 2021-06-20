package unoesc.edu.aulaJSP.model;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator ="seq_pk_clientes")
	private int id;
	
	@Column
	private String nome;
	
	@Column(name="sobre_nome")
	private String sobrenome;
	
	@Column(name="data_nasc")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNasc;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "servicos_clientes", 
		joinColumns = { @JoinColumn(name = "id_cliente") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_servico") })
	private Set<Servico> servicos = new HashSet<Servico>();

	
	
	public Cliente() {
		this.id = 0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Cliente) obj).getId() == this.id;
				
	}

	public Set<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}

	
	
	
}
