package controllers;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Usuario;
import services.UsuarioService;

@RestController
@RequestMapping("/test")
public class UsuarioController implements ErrorController{

	@Autowired
	UsuarioService us;
	private static final String PATH = "/error";
	
	@RequestMapping(value = "/all", method=RequestMethod.GET)
	public Hashtable<String, Usuario> obtenerTodos(){
		return us.obtenerTodos();
	}
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Usuario obtenerUsuario(@PathVariable("id") String id){
		return us.obtenerUsuario(id);
	}
	
	@Override
    public String getErrorPath() {
        return PATH;
    }
}
