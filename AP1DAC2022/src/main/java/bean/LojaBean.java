package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.LojaDAO;
import entidade.Loja;

@ManagedBean
public class LojaBean {
	private Loja loja = new Loja();
	private List<Loja> lista;
	private Integer conta = LojaDAO.contar();

	
	public String salvar(){
		try {
			LojaDAO.salvar(loja);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Musica "+ loja.getNome() +" salva com sucesso "));
			loja = new Loja();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Erro ao salvar a musica "+ loja.getNome()));
		}
		
		return null;
	}
	
	public String editar(){
		try {
			LojaDAO.atualizar(loja);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Musica "+ loja.getNome() +" editada com sucesso "));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Erro ao editar a musica "+ loja.getNome()));
		}
		return null;
	}
	
	public String deletar(){
		try {
			LojaDAO.deletar(loja);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Musica "+ loja.getNome() +" deletada com sucesso "));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Erro ao apagar a musica "+ loja.getNome()+ e.getMessage()));
		}
		return "listagem.xhtml";
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Loja> getLista() {
		if (lista == null) {
			lista = LojaDAO.listar();
		}
		return lista;
	}

	public void setLista(List<Loja> lista) {
		this.lista = lista;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}
	
	
}
