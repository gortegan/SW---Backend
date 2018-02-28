package startup.sw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import startup.sw.entities.Usuario;
import startup.sw.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/app")
@RestController
public class UsuarioController implements ErrorController{

	
	@Autowired
	private static final String PATH = "/error";
	
	@Autowired
	private UsuarioService usuarioService; 
	
	
	@RequestMapping (method = RequestMethod.GET, value="/app")
	public @ResponseBody String obtenerTodos(){
		JSONObject json = new JSONObject();
	    String message = "Success";
	    json.put("message", message);
	    return json.toString();
	}
	
	@RequestMapping (method = RequestMethod.GET, value="/testGet")
	public String prueba(){
		return "Grande!";
	}
	
	@RequestMapping (method = RequestMethod.POST, value="/registrar")
	public @ResponseBody String registrarUsuario(@RequestBody Usuario usuario){
		usuarioService.registrarUsuario(usuario);
		JSONObject json = new JSONObject();
	    String message = "Usuario registrado";
	    json.put("message", message);
	    return json.toString();
	}
	
	@Override
    public String getErrorPath() {
        return PATH;
    }
}
