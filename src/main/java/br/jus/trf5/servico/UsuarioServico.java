package br.jus.trf5.servico;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.jus.trf5.dominio.LoginDTO;
import br.jus.trf5.dominio.Usuario;
import br.jus.trf5.interfaces.IServiceSecurityToken;
import br.jus.trf5.repositorio.RepositorioBase;
import br.jus.trf5.repositorio.UsuarioRepositorio;
import br.jus.trf5.seguranca.JwtUtil;
import br.jus.trf5.servico.excecao.ExcecaoAutorizacao;

@Service
@Configurable
public class UsuarioServico implements IServiceSecurityToken<Usuario> {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	RepositorioBase repositorio;

	@Autowired
	LoginDTO dto;

	@Bean
	public BCryptPasswordEncoder codificadorDeSenha() {
		return new BCryptPasswordEncoder();
	}

	public Boolean estaLogado(String token) throws NumberFormatException, SQLException, UnknownHostException {
		String login = jwtUtil.retLoginFromToken(token);
		String userlogin = login.substring(0, login.indexOf("_"));
		String userSecao = login.substring(login.indexOf("_") + 1, login.length());
		UsuarioRepositorio usuarioRepositorio = repositorio.usuarioStartBase(Integer.parseInt(userSecao));
		String tokenBase = usuarioRepositorio.obterToken(userlogin, Integer.parseInt(userSecao));
		return tokenBase.equals(token);
	}

	@Override
	public List<Usuario> Consultar(String token) throws ExcecaoAutorizacao, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscaPorToken(String token) throws ExcecaoAutorizacao, SQLException {
		UsuarioRepositorio usuarioRepositorio;
		try {
			usuarioRepositorio = repositorio.usuarioLogin(85);
			return usuarioRepositorio.buscaPorLogin(jwtUtil.getLogin(token), jwtUtil.getSecaoToken(token));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	public LoginDTO loginComTokenPorDTO(LoginDTO loginDto) throws SQLException, UnknownHostException {
		int codsecao = loginDto.getSecao();
		UsuarioRepositorio usuarioRepositorio = repositorio.usuarioLogin(codsecao);
		Usuario usuario = usuarioRepositorio.buscaPorLogin(loginDto.getLogin(), codsecao);
		Boolean valido = codificadorDeSenha().matches(loginDto.getSenha(), usuario.getPassword());
		if (valido) {			
			String token = jwtUtil.gerarToken(usuario.getLogin() + "_" + usuario.getCodsecao());
			usuario.setToken(token);
			usuarioRepositorio.salvarOrAtualizar(usuario);//atualizarToken(usuario.getLogin(), token);
			dto.setSecao(usuario.getCodsecao());
			dto.setLogin(usuario.getLogin());
			dto.setSenha(usuario.getSenha());
			dto.setToken(token);
			return dto;
		}
		return null;
	}

}
