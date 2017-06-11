package org.npj.campanha.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Campanha {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long idTimeDoCoracao;
	private Date dataInicial;
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
