package com.bilangieri.cursomc.domain;

public enum TipoCliente {

	
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	
	private int cod;
	private String descicao;
	
	
	private TipoCliente(int cod, String descicao) {
		this.cod = cod;
		this.descicao = descicao;
	}

	public int getCod() {
		return cod;
	}

		public String getDescicao() {
		return descicao;
	}

	public static TipoCliente toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for ( TipoCliente x : TipoCliente.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
	
	
}
