package com.bilangieri.cursomc.domain;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	
	private int cod;
	private String descicao;
	
	
	private EstadoPagamento(int cod, String descicao) {
		this.cod = cod;
		this.descicao = descicao;
	}

	public int getCod() {
		return cod;
	}

		public String getDescicao() {
		return descicao;
	}

	public static EstadoPagamento toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for ( EstadoPagamento x : EstadoPagamento.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
	

}
