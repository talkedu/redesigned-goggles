package org.npj.sociotorcedor.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SocioTorcedor {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String nomeCompleto;
	private Date dataNascimento;
	private Long IdTimeDoCoracao;
	
	@JsonIgnore
	@ElementCollection(fetch=FetchType.EAGER)
	private List<Long> idsCampanhasAssociadas;
	
	@Transient
	private List<Campanha> campanhasAssociadas = new ArrayList<>();
	
	public SocioTorcedor() {
	
	}
	
	public SocioTorcedor(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getIdTimeDoCoracao() {
		return IdTimeDoCoracao;
	}

	public void setIdTimeDoCoracao(Long idTimeDoCoracao) {
		IdTimeDoCoracao = idTimeDoCoracao;
	}
	
	public List<Long> getIdsCampanhasAssociadas() {
		return idsCampanhasAssociadas;
	}
	
	public void setIdsCampanhasAssociadas(List<Long> idsCampanhasAssociadas) {
		this.idsCampanhasAssociadas = idsCampanhasAssociadas;
	}

	public List<Campanha> getCampanhasAssociadas() {
		return campanhasAssociadas;
	}

	public void setCampanhasAssociadas(List<Campanha> campanhasAssociadas) {
		this.campanhasAssociadas = campanhasAssociadas;
	}
	
	public void addIdCampanhaAssociada(Long id) {
		this.idsCampanhasAssociadas.add(id);
	}

}
