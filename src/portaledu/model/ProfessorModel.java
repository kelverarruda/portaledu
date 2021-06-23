package portaledu.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import portaledu.utils.StatusesEnum;

@Entity
@Table(name = "professors")
public class ProfessorModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pk_idprofessors")
	private Long id;
	
	@Column(length = 80, nullable = false, unique = true)
	private String fullname;

	@Column(length = 11, nullable = false, unique = true)
	private String document;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;
	
	@Column(length = 80, nullable = false)
	private String address;
	
	@Column(length = 80, nullable = true)
	private String email;
	
	@Column(length = 11, nullable = false)
	private String phone;
	
	@ManyToMany(mappedBy = "professor", fetch = FetchType.EAGER)
	private List<ExamModel> exam = new ArrayList<ExamModel>();
	
	@OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
	private List<ClassModel> classe = new ArrayList<ClassModel>();
	
	@Column(length = 10, nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
	@Enumerated(EnumType.STRING)
	private StatusesEnum status;

	
	public ProfessorModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<ExamModel> getExam() {
		return exam;
	}

	public void setExam(List<ExamModel> exam) {
		this.exam = exam;
	}

	public List<ClassModel> getClasse() {
		return classe;
	}

	public void setClasse(List<ClassModel> classe) {
		this.classe = classe;
	}

	public StatusesEnum getStatus() {
		return status;
	}

	public void setStatus(StatusesEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
}
