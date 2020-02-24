package br.jus.trf5.dominio;

public class ClassConnection {
	
	public String user;
	public String pass;
	public String urlConnection;
	//private	BCryptPasswordEncoder bCryptEDCoder;
		
	public ClassConnection() {
		super();
	}

	public ClassConnection(String user, String pass, String connection) {
		super();
		this.user = user;
		this.pass = pass;
		this.urlConnection = connection;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUrlConnection() {
		return urlConnection;
	}

	public void setUrlConnection(String connection) {
		this.urlConnection = connection;
	}
	
	
		

}
