package com.rodrigofujioka.dev.web.service.dto;

public class DisciplinaBuscaAnoDTO {

	private int anoInicial;
	private int anoFinal;

	public DisciplinaBuscaAnoDTO(int anoInicial, int anoFinal) {
		this.anoInicial = anoInicial;
		this.anoFinal = anoFinal;
	}

	public final int getAnoInicial() { return anoInicial; }
	public final void setAnoInicial(int anoInicial) { this.anoInicial = anoInicial; }
	public final int getAnoFinal() { return anoFinal; }
	public final void setAnoFinal(int anoFinal) { this.anoFinal = anoFinal; }

}
