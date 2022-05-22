package dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Usuario;
import util.JPAUtil;

public class UsuarioDAO {
	public static void salvar(Usuario usuario) {
			EntityManager em = JPAUtil.criarEntityManager();
			Query q = em.createQuery("SELECT u from Usuario u where u.usuario = :name").setParameter("name", usuario.getUsuario());
			if(q.getResultList().isEmpty()) {
				em.getTransaction().begin();
				em.persist(usuario);
				em.getTransaction().commit();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Usuario "+ usuario.getNomeUsuario() +" salva com sucesso "));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Usuario já existe"));
			}
			em.close();
			
	}
	
	public static void editar(Usuario usuario) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void deletar(Usuario usuario) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Usuario> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select u from Usuario u order by id");
		List<Usuario> lista = q.getResultList();
		em.close();
		return lista;
	}
	
	public static Usuario acharPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		
		Usuario usuario = em.find(Usuario.class, id);
		em.close();
		return usuario;
	}
	public static Usuario getUsuario(String usuario, String senha) {
		try {
			EntityManager em = JPAUtil.criarEntityManager();
			Usuario entUsuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.usuario = :name and u.senha = :senha")
					.setParameter("name", usuario)
					.setParameter("senha", senha)
					.getSingleResult();
			em.close();
			return entUsuario;
		}catch (Exception e){
			return null;
		}
		
	}
}
