package br.jus.trf5.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	private int codusu;
	private int codsecao;
	private String login;
	private String senha;
	private Date ultima_atualizacao_senha;
	private String token;
	private Date ultima_atualizacao_token;
	private String nomesobrenome;
	
	public Usuario() {

	}

	public Usuario(int codusu, int codsecao, String login, String senha, Date ultima_atualizacao_senha,
			String token, Date ultima_atualizacao_token) {
		super();
		this.codusu = codusu;
		this.codsecao = codsecao;
		this.login = login;
		this.senha = senha;
		this.ultima_atualizacao_senha = ultima_atualizacao_senha;
		this.token = token;
		this.ultima_atualizacao_token = ultima_atualizacao_token;
	}

	public Integer getCodsecao() {
		return codsecao;
	}

	public void setCodsecao(Integer codsecao) {
		this.codsecao = codsecao;
	}

	public String getNomesobrenome() {
		return nomesobrenome;
	}

	public void setNomesobrenome(String nomesobrenome) {
		this.nomesobrenome = nomesobrenome;
	}

	public Integer getCodusu() {
		return codusu;
	}

	public void settCodusu(int id) {
		this.codusu = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLogin() {
		return login;
	}

	public Date getUltimaAtualizacaoSenha() {
		return ultima_atualizacao_senha;
	}

	public void setUltimaAtualizacaoSenha(Date ultimaAtualizacaoSenha) {
		this.ultima_atualizacao_senha = ultimaAtualizacaoSenha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getUltimaAtualizacaoToken() {
		return ultima_atualizacao_token;
	}

	public void setUltimaAtualizacaoToken(Date ultimaAtualizacaoToken) {
		this.ultima_atualizacao_token = ultimaAtualizacaoToken;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean temPerfil(br.jus.trf5.dominio.enums.Perfil perfil) {
		return getAuthorities().contains(perfil.getDescricao());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
