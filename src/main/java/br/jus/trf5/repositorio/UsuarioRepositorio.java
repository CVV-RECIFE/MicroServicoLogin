package br.jus.trf5.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import br.jus.trf5.dominio.ClassConnection;
import br.jus.trf5.dominio.Usuario;
import br.jus.trf5.interfaces.IRepositorioDAO;

@Repository
public class UsuarioRepositorio implements IRepositorioDAO<Usuario> {

	public Sql2o connection;
	public ClassConnection cconnection;

	public UsuarioRepositorio() {
	}

	public UsuarioRepositorio(Sql2o connection) {
		this.connection = connection;
	}

	public UsuarioRepositorio(ClassConnection cconnection) {
		this.cconnection = cconnection;
	}

	public Sql2o getConnection() {
		return connection;
	}

	public void setConnection(Sql2o connection) {
		this.connection = connection;
	}

	public ClassConnection getCconnection() {
		return cconnection;
	}

	public void setCconnection(ClassConnection cconnection) {
		this.cconnection = cconnection;
	}

	public String obterToken(String login, Integer codsecao) {
		String sqlUsuario = " SELECT u.token FROM Usuario u WHERE u.login = :login and u.codsecao = :codsecao ";

		connection = getConnection(cconnection);
		try (Connection con = connection.open()) {
			String retorno = con.createQuery(sqlUsuario).addParameter("codsecao", codsecao).addParameter("login", login)
					.executeAndFetchFirst(String.class);
			con.close();
			return retorno;
		}
	}

	public Usuario buscaPorLogin(String login, Integer codsecao) {
		String sqlUsuario = " SELECT u.codusu, u.login, u.codsecao, u.ultima_atualizacao_senha, u.senha,"
				+ " u.ultima_atualizacao_token, u.token  FROM Usuario u WHERE u.login = :login and u.codsecao = :codsecao ";

		connection = getConnection(cconnection);
		try (Connection con = connection.open()) {
			Usuario retorno = con.createQuery(sqlUsuario).addParameter("codsecao", codsecao)
					.addParameter("login", login).executeAndFetchFirst(Usuario.class);
			return retorno;
		}
	}

	public void atualizarToken(String login, int codsecao, String token) {
		String sqlupdateToken = "UPDATE Usuario\n" + "   SET Token = :token, Ultima_Atualizacao_Token = SYSDATE\n"
				+ " WHERE Login = :login\n" + "   AND Codsecao = :codsecao";
		try (Connection con = connection.beginTransaction()) {
			con.createQuery(sqlupdateToken).addParameter("token", token).addParameter("login", login)
					.addParameter("codsecao", codsecao).executeUpdate();
			con.commit();
		}
	}

	private Sql2o getConnection(ClassConnection cconnection2) {
		String url = cconnection2.getUrlConnection();
		String user = cconnection2.getUser();
		String pass = cconnection2.getPass();
		return new Sql2o(url, user, pass);
	}

	@Override
	public List<Usuario> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> buscaPorNome(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvarOrAtualizar(Usuario bean) {
		String sqlupdateToken = "UPDATE Usuario\n" + "   SET Token = :token, Ultima_Atualizacao_Token = SYSDATE\n"
				+ " WHERE Login = :login\n" + "   AND Codsecao = :codsecao";
		try (Connection con = connection.beginTransaction()) {
			con.createQuery(sqlupdateToken).addParameter("token", bean.getToken())
					.addParameter("login", bean.getLogin()).addParameter("codsecao", bean.getCodsecao())
					.executeUpdate();
			con.commit();
		}

	}

	@Override
	public void deletar(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Usuario> consulta(Usuario bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscaPorChave(int value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscaPorChave(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
