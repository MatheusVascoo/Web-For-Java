package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import entidade.Usuario;

@ManagedBean
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private List<Usuario> lista;
	
	public String login() {
		try {
			usuario = UsuarioDAO.getUsuario(usuario.getUsuario(), usuario.getSenha());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Erro ao efetuar login "+ usuario.getNomeUsuario()));
		}
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Usuario não encontrado, ou com a senha incorreta"));
			return null;
		}else {
			return "opcoes.xhtml";
		}
	}
	
	public String salvar() {
			UsuarioDAO.salvar(usuario);
			usuario = new Usuario();
		return null;
	}
	
	public String editar() {
		try {
			UsuarioDAO.editar(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Usuario "+ usuario.getNomeUsuario() +" editado com sucesso "));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Erro ao editar o usuario "+ usuario.getNomeUsuario()));
		}
		return null;
	}
	
	public String deletar() {
		try {
			UsuarioDAO.deletar(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Usuario "+ usuario.getNomeUsuario() +" deletado com sucesso "));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Erro ao deletar o usuario "+ usuario.getNomeUsuario()));
		}
		return "listagem_usuario.xhtml";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getLista() {
		if (lista == null) {
			lista = UsuarioDAO.listar();
		}
		return lista;
	}
	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
}
