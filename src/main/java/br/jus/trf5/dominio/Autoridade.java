package br.jus.trf5.dominio;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Autoridade implements GrantedAuthority, Serializable{
	private static final long serialVersionUID = 1L;

	private String autoridade;
	
	public Autoridade(String autoridade) {
		this.autoridade = autoridade;		
	}
	
	@Override
	public String getAuthority() {
		return autoridade;
	}

}
