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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import portaledu.utils.StatusEnum;

@Entity
@Table(name = "exams")
public class ExamModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "exam_id")}, 
	   inverseJoinColumns = { @JoinColumn(name = "professor_id")})
	private List<ProfessorModel> professor = new ArrayList<ProfessorModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private StudentModel student;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	@Column(length = 80, nullable = false)
	private String subject;
	
	@Column(scale = 3, precision = 2, nullable = false)
	private Double result;
	
	@Column(length = 10, nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	public ExamModel() {
		
	}
	
	
	public ExamModel(Long id, List<ProfessorModel> professor, StudentModel student, Date date, String subject,
			Double result, StatusEnum status) {
		this.id = id;
		this.professor = professor;
		this.student = student;
		this.date = date;
		this.subject = subject;
		this.result = result;
		this.status = status;
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<ProfessorModel> getProfessor() {
		return professor;
	}


	public void setProfessor(List<ProfessorModel> professor) {
		this.professor = professor;
	}


	public StudentModel getStudent() {
		return student;
	}


	public void setStudent(StudentModel student) {
		this.student = student;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public Double getResult() {
		return result;
	}


	public void setResult(Double result) {
		this.result = result;
	}


	public StatusEnum getStatus() {
		return status;
	}


	public void setStatus(StatusEnum status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamModel other = (ExamModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
