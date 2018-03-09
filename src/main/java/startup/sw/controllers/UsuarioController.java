package startup.sw.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import startup.sw.entities.Usuario;
import startup.sw.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("")
@RestController
public class UsuarioController implements ErrorController{

     
	
	@Autowired
	private static final String PATH = "/error";
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@RequestMapping(method = RequestMethod.POST, value="/users")
	  public @ResponseBody String getUsers() {
	    return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
	           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	  }
	@RequestMapping(method = RequestMethod.POST, value="/login")
	  public Principal user(Principal user) {
	    return user;
	  }
	
	@RequestMapping (method = RequestMethod.POST, value="/registrar")
	public @ResponseBody Usuario registrarUsuario(@RequestBody Usuario usuario){
		usuario.setActive(1);
		usuarioService.registrarUsuario(usuario);
		return usuario;
	}
	
	@Override
    public String getErrorPath() {
        return PATH;
    }
}
