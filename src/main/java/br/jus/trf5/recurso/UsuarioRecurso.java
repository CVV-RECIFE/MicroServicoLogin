package br.jus.trf5.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trf5.dominio.LoginDTO;
import br.jus.trf5.dominio.Usuario;
import br.jus.trf5.servico.UsuarioServico;

@RestController
@ResponseBody
@RequestMapping(value = "/usuarios")
public class UsuarioRecurso {

	@Autowired
	UsuarioServico servico;
	
	@PostMapping(value = "/login") //Devolve um token
	public LoginDTO login(@RequestBody LoginDTO loginDto) throws Exception {
		LoginDTO dto = servico.loginComTokenPorDTO(loginDto);
		return dto;
	}

	@GetMapping(value = "/usuarioPorToken")
	public Usuario buscaPorToken(@RequestHeader("Authorization") String token) throws Exception {
		return servico.buscaPorToken(token);
	}
	
}
