package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Loja;
import util.JPAUtil;

public class LojaDAO {
	public static void salvar(Loja loja) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();//begin transaction
		em.persist(loja);//insert
		em.getTransaction().commit();//commit
		em.close();
	}
	
	public static void atualizar(Loja loja) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();//begin transaction
		em.merge(loja);//insert
		em.getTransaction().commit();//commit
		em.close();
	}
	
	public static void deletar(Loja loja) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();//begin transaction
		em.remove(em.contains(loja) ? loja : em.merge(loja));
		em.getTransaction().commit();//commit
		em.close();
	}
	
	public static List<Loja> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select l from Loja l order by id");
		List<Loja> lista = q.getResultList();
		em.close();
		return lista;
		
	}
	
	public static Loja acharPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		
		Loja loja = em.find(Loja.class, id);
		em.close();
		return loja;
	}
	
	public static Integer contar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Integer count =  (Integer) em.createQuery("select l from Loja l").getResultList().size();
		em.close();
		return count;
		
	}
}
