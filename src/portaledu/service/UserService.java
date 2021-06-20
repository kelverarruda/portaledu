package portaledu.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.faces.bean.ApplicationScoped;

import portaledu.DAO.UserDAOImpl;
import portaledu.model.UserModel;
import portaledu.utils.StatusEnum;
import portaledu.utils.UserTypeEnum;

@ApplicationScoped
public class UserService {
	
	private UserModel user = new UserModel();
	private UserDAOImpl dao = new UserDAOImpl();
	
	public String hashPass(String pass) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
			byte[] passCrypt = mDigest.digest(pass.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder(2 * passCrypt.length);
			for (int i = 0; i < passCrypt.length; i++) {
				String hex = Integer.toHexString(0xff & passCrypt[i]);
				if(hex.length() == 1) {
					sb.append("0");
				}
				sb.append(hex);
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public UserService() throws Exception {
		user.setFullname("Kelver Arruda");
		user.setEmail("arrudakelver@gmail.com");
		user.setUsername("admin");
		user.setPassword(hashPass("admin"));
		user.setUsertype(UserTypeEnum.ADMIN);
		user.setStatus(StatusEnum.ACTIVE);
		dao.insert(user);
	}


	
	

}
