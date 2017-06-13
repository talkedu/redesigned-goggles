package org.npj.campanha.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Campanha implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Campanha() {
		
	}
	
	public Campanha(Long id) {
		this.id = id; 
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long idTimeDoCoracao;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT-3")
	private Date dataInicial;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT-3")
	private Date dataFinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTimeDoCoracao() {
		return idTimeDoCoracao;
	}

	public void setIdTimeDoCoracao(Long idTimeDoCoracao) {
		this.idTimeDoCoracao = idTimeDoCoracao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}