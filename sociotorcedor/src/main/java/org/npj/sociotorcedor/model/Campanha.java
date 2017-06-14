package org.npj.sociotorcedor.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Campanha {
	public Campanha() {

	}

	public Campanha(Long id) {
		this.id = id;
	}

	private Long id;
	private Long idTimeDoCoracao;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
	private Date dataInicial;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-3")
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