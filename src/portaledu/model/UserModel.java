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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 80, nullable = false)
	private String fullname;
	
	@Column(length = 80, nullable = false, unique = true)
	private String email;
	
	@Column(length = 30, nullable = false, unique = true)
	private String username;

	@Column(length = 255, nullable = false)
	private String password;
	
	@Column(length = 10, nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'UNKNOWN'")
	@Enumerated(EnumType.STRING)
	private UserTypeEnum usertype;
	
	@Column(length = 10, nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'INACTIVE'")
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	

	public UserModel() {
		
	}

	public UserModel(Long id, String fullname, String email, String username, String password, UserTypeEnum usertype,
			StatusEnum status) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.status = status;
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
		UserModel other = (UserModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
