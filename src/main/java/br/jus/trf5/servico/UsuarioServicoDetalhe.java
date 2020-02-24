package br.jus.trf5.servico;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.jus.trf5.dominio.Usuario;
import br.jus.trf5.repositorio.RepositorioBase;
import br.jus.trf5.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicoDetalhe implements UserDetailsService {

	@Autowired
	RepositorioBase repositorio;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		String userlogin = login.substring(0, login.indexOf("_"));
		String userSecao = login.substring(login.indexOf("_") + 1, login.length());

		UsuarioRepositorio usuarioRepositorio;
		Usuario usuario = null;
		try {
			usuarioRepositorio = repositorio.usuarioStartBase(Integer.parseInt(userSecao));
			usuario = usuarioRepositorio.buscaPorLogin(userlogin, Integer.parseInt(userSecao));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (usuario == null) {
			throw new UsernameNotFoundException(login);
		}
		
		return new Usuario(usuario.getCodusu(), usuario.getCodsecao(), usuario.getLogin(), usuario.getSenha(),
				usuario.getUltimaAtualizacaoSenha(), usuario.getToken(), usuario.getUltimaAtualizacaoToken());

	}

}
