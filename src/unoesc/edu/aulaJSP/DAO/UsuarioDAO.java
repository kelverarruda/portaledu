package unoesc.edu.aulaJSP.DAO;

import java.util.List;

import unoesc.edu.aulaJSP.model.Usuario;




public interface UsuarioDAO {

	Usuario getUsuarioById (int id);
	List<Usuario> getUsuarios();
	boolean deleteUsuario(int id);
	boolean insertUsuario(Usuario p);
	boolean updateUsuario(Usuario p);
	Usuario validaLogin(String login, String senha);
	
}
