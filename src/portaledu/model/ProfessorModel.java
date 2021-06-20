package portaledu.model;

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

import portaledu.utils.StatusEnum;

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
	private StatusEnum status;

	
	public ProfessorModel() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
}
