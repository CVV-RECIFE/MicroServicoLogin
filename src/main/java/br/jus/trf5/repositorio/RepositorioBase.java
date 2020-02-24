package br.jus.trf5.repositorio;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import br.jus.trf5.dominio.ClassConnection;

@Repository
public class RepositorioBase {

	// ----------------------------------------------------------------------------------------------------------------
	// CADA NOVO REPOSITÓRIO DEVERÁ SER INCLUIDO
	// AQUI PARA SER INSTANCIADO NA BASE CORRETA.
	private Sql2o connection;
	private ClassConnection cconnection = new ClassConnection();

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	// ----------------------------------------------------------------------------------------------------------------

	public static final RepositorioBase INSTANCE = new RepositorioBase();

	public RepositorioBase() {
	}

	// #MÉTODOS DE ACESSO A BANCO RESOLVIDO AQUI //
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

	public UsuarioRepositorio getUsuarioRepositorio() {
		return usuarioRepositorio;
	}

	public void setUsuarioRepositorio(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	private ClassConnection getConnection2(int value) throws SQLException, UnknownHostException {

		// SE NÃO FOR IP DE PRODUÇÃO, USAR RECURSOS DE DESENVOLVIMENTO/HOMOLOGAÇÃO
		String ip = null;
		ip = InetAddress.getLocalHost().getHostAddress();
		if (!ip.equals("192.168.2.200")) {
			if (value == 2) {//Desenvolvimento
				cconnection.setUser("id12684648_secretariacvv");
				cconnection.setPass("mariabibi2610");
				cconnection.setUrlConnection("jdbc:mariadb:thin:@localhost:1521:TRF5SUP");

			} else {//Producao
				cconnection.setUser("id12684648_secretariacvv");
				cconnection.setPass("mariabibi2610");
				cconnection.setUrlConnection("jdbc:mariadb:thin:@id12684648_sadcvv:1521:SJSE");
			}

			return cconnection;
		}

//		switch (value) {
//		case 0: // BASE TRF5PRD DO ESPARTA (TROCAR QUANDO FOR PARA PRODUÇÃO)
//			cconnection.setUser("esparta2");
//			cconnection.setPass("esparta2");
//			cconnection.setUrlConnection("jdbc:oracle:thin:@TAMANDARES:1521:TRF5SUP");
//			break;
//
//		case 80: // BASE TEBAS.SJAL
//			cconnection.setUser("tebas");
//			cconnection.setPass("tbjfal");
//			cconnection.setUrlConnection("jdbc:oracle:thin:@10.103.0.66:1521:SJAL");
//			break;
//
//		case 81: // BASE TEBAS.SJCE
//			cconnection.setUser("tebas");
//			cconnection.setPass("tbjfce");
//			cconnection.setUrlConnection("jdbc:oracle:thin:@10.2.106.5:1521:SJCE");
//			break;
//
//		case 82: // BASE TEBAS.SJPB
//			cconnection.setUser("tebas");
//			cconnection.setPass("tbjfpb");
//			cconnection.setUrlConnection("jdbc:oracle:thin:@10.2.102.3:1521:SJPB");
//			break;
//
//		case 83: // base TEBAS.SJPE
//			cconnection.setUser("tebas");
//			cconnection.setPass("tbjfpe");
//			cconnection.setUrlConnection("jdbc:oracle:thin:@10.2.105.17:1521:SJPE");
//			break;
//
//		case 84: // base TEBAS.SJRN
//			cconnection.setUser("tebas");
//			cconnection.setPass("tbjfrn");
//			cconnection.setUrlConnection("jdbc:oracle:thin:@10.101.0.26:1521:SJRN");
//			break;
//
//		case 85: // base TEBAS.SJSE
//			cconnection.setUser("tebas");
//			cconnection.setPass("tbjfse");
//			cconnection.setUrlConnection("jdbc:oracle:thin:@10.104.0.211:1521:SJSE");
//			break;
//		}

		if (connection == null)
			throw new SQLException("Connection Invalid Return (Verifique o acesso ao SGBD)");

		return cconnection;
	}
	// ----------------------------------------------------------------------------------------------------------------------------

	// # ÁREA DE DECLARAÇÃO DOS MÉTODOS DE INSTANCIAÇÃO DAS CLASSES DE REPOSITORIO
	// ----------------------------------------------------
	public UsuarioRepositorio usuarioStartBase(int value) throws SQLException, UnknownHostException {
		usuarioRepositorio.setCconnection(getConnection2(value));
		return usuarioRepositorio;
	}

	public UsuarioRepositorio usuarioLogin(int value) throws SQLException, UnknownHostException {
		usuarioRepositorio.setCconnection(getConnection2(value));
		return usuarioRepositorio;
	}
	// #FIM
	// ----------------------------------------------------------------------------------------------------------------------------
}
