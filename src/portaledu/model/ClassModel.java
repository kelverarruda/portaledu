package portaledu.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "classes")
public class ClassModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pk_idclass")
	private Long id;
	
	@Column(length = 80, nullable = false, unique = true)
	private String classname;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ProfessorModel professor;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "class_id")}, 
	   		   inverseJoinColumns = { @JoinColumn(name = "student_id")})
	private List<StudentModel> student = new ArrayList<StudentModel>();
	
	public ClassModel() {
		
	}

	public ClassModel(Long id, String classname, Date year) {
		this.id = id;
		this.classname = classname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public ProfessorModel getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorModel professor) {
		this.professor = professor;
	}

	public List<StudentModel> getStudent() {
		return student;
	}

	public void setStudent(List<StudentModel> student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
}
