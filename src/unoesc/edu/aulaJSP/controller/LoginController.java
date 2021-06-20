package unoesc.edu.aulaJSP.controller;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CaptureEvent;

import com.sun.istack.NotNull;

import unoesc.edu.aulaJSP.DAO.UsuarioDAO;
import unoesc.edu.aulaJSP.model.Usuario;
import unoesc.edu.aulaJSP.util.SessionContext;


@ManagedBean(name="usuarioMB")
@RequestScoped
public class LoginController {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> listUsuario;
	private static String photoFile;
	
	private boolean logado = false;
	
	private boolean isRoot = false;
	
	@ManagedProperty(value="#{UsuarioDAO}")
	private UsuarioDAO usuarioDao;
	
	public LoginController() {
	 Usuario user =	(Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		if (user!=null) {
			logado = true;
			isRoot = user.getRoot();
		}
	}

    private String getRandomImageName() {
		int i = (int) (Math.random() * 10000000);

		return String.valueOf(i);
	}


    public void oncapture(CaptureEvent captureEvent) {
        this.photoFile = getRandomImageName();
        byte[] data = captureEvent.getData();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = "C:\\Temp\\"+ this.photoFile + ".jpeg";
        //String newFileName = externalContext.getRealPath("WEB-INF")+ File.separator + "resources" + File.separator + "img" +
         //                           File.separator + "users"  + filename + ".jpeg";
        

		FileImageOutputStream imageOutput;
		try {
			System.out.println("Criou?: "+ newFileName);
			File file = new File(newFileName);
			file.createNewFile();
			imageOutput = new FileImageOutputStream(file);
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			this.usuario.setImagem(this.photoFile);
			System.out.println("aqui: " + this.photoFile);
		}
        catch(IOException e) {
        	System.err.print(e);
			throw new FacesException("Error in writing captured image.", e);
		}
    }
	
	
	

	public void save() {
		this.usuario.setSenha(this.encryptSenha(this.getUsuario().getSenha()));
		this.usuario.setImagem(this.photoFile);
		System.out.println("teste: "+ this.photoFile);
		
		if (usuario.getId() == 0) {
			this.usuarioDao.insertUsuario(usuario);
		} else {
			this.usuarioDao.updateUsuario(usuario);
		}
		
		usuario = new Usuario();
	}
	
	
	public void usuarioEdit() {

		this.usuarioDao.updateUsuario(usuario);
	}
	
	
	public void usuarioDelete(int id) {
		this.usuarioDao.deleteUsuario(id);
	}
	
	public void load (int id) {
		this.usuario = usuarioDao.getUsuarioById(id);
		this.photoFile = this.usuario.getImagem();
	}

	public void fazLogin() throws IOException {
		FacesMessage message = null;
        
		usuario = usuarioDao.validaLogin(this.getUsuario().getLogin(), this.encryptSenha(this.getUsuario().getSenha()));
		
		if (usuario	!=	null) {
			logado = true;
			SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login realizado com sucesso!", this.getUsuario().getLogin());
		}else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro no Login!", "Usuário ou senha inválido");
			SessionContext.getInstance().setAttribute("usuarioLogado", null);
			logado = false;
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", logado);
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect("index.xhtml");
       // return "redirect:views/Index.xhtml";
	}
	
	
	public void fazLogout() throws IOException {
		FacesMessage message = null;
        
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout!","Até mais.");
			SessionContext.getInstance().setAttribute("usuarioLogado", null);
			logado = false;
			isRoot = false;
			usuario = new Usuario();
         
        FacesContext.getCurrentInstance().addMessage(null, message);
       
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}
	
	
	private String encryptSenha (String senha) {
		MessageDigest mDigest;
		  try { 
		      //Instanciamos o nosso HASH MD5, poderíamos usar outro como
		      //SHA, por exemplo, mas optamos por MD5.
		      mDigest = MessageDigest.getInstance("MD5");
		       
		      //Convert a String valor para um array de bytes em MD5
		      byte[] valorMD5 = mDigest.digest(senha.getBytes("UTF-8"));
		       
		      //Convertemos os bytes para hexadecimal, assim podemos salvar
		      //no banco para posterior comparação se senhas
		      StringBuffer sb = new StringBuffer();
		      for (byte b : valorMD5){
		             sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
		      }
		 
		      return sb.toString();
		       
		  } catch (NoSuchAlgorithmException e) {
		      e.printStackTrace();
		      return null;
		  } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		      return null;
		  }
	}
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListUsuario() {
		listUsuario = usuarioDao.getUsuarios();
		return listUsuario;
	}


	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}


	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}


	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}


	public boolean isLogado() {
		return logado;
	}


	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	
	public boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}
	
}