package portaledu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import portaledu.utils.StatusEnum;
import portaledu.utils.UserTypeEnum;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pk_idusers")
	private Integer id;
	
	@Column(length = 80, nullable = false)
	private String fullname;
	
	@Column(length = 80, nullable = false, unique = true)
	private String email;
	
	@Column(length = 30, nullable = false, unique = true)
	private String username;

	@Column(length = 255, nullable = false)
	private String password;

	@Column(length = 10, nullable = true, columnDefinition = "VARCHAR(10) DEFAULT 'STUDENT'")
	@Enumerated(EnumType.STRING)
	private UserTypeEnum usertype;
	
	@Column(length = 10, nullable = true)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	

	public UserModel() {
		
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserTypeEnum getUsertype() {
		return usertype;
	}

	public void setUsertype(UserTypeEnum usertype) {
		this.usertype = usertype;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return this.id + this.username.length() + this.email.length();
	}

	@Override
	public String toString() {
		return this.username;
	}	
	
}
