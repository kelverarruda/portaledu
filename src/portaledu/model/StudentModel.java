package portaledu.model;

import java.sql.Date;
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
@Table(name = "students")
public class StudentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pk_idstudents")
	private Long id;
	
	@Column(length = 80, nullable = false, unique = true)
	private String fullname;
	
	@Column(length = 11, nullable = false, unique = true)
	private String document;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;
	
	@Column(length = 80, nullable = true)
	private String fathername;
	
	@Column(length = 80, nullable = false)
	private String mothername;
	
	@Column(length = 80, nullable = true)
	private String address;
	
	@Column(length = 80, nullable = true)
	private String email;
	
	@Column(length = 11, nullable = true)
	private String phone;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
	private List<ExamModel> exams = new ArrayList<ExamModel>();
	
	@ManyToMany(mappedBy = "student", fetch = FetchType.EAGER)
	private List<ClassModel> classes = new ArrayList<ClassModel>();
	
	@Column(length = 10, nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
	@Enumerated(EnumType.STRING)
	private StatusesEnum status;

	public StudentModel() {
		
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

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
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

	public List<ExamModel> getExams() {
		return exams;
	}

	public void setExams(List<ExamModel> exams) {
		this.exams = exams;
	}

	public List<ClassModel> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
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
