package br.jus.trf5.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.jus.trf5.servico.excecao.ExcecaoAutorizacao;

public interface IServiceSecurityToken<T> {
	
	public List<T> Consultar(String token) throws ExcecaoAutorizacao, SQLException;

	public T buscaPorToken(String token) throws ExcecaoAutorizacao, SQLException;	
	
}
