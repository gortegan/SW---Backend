package services;

import java.util.Hashtable;

import org.springframework.stereotype.Service;
import model.Usuario;;

@Service
public class UsuarioService {
	Hashtable <String, Usuario> usuarios = new Hashtable<String, Usuario>(); 
	
	public UsuarioService(){
		Usuario u = new Usuario();
		u.setNombre("Gerard");
		u.setApellido("Ortega");
		u.setEdad(24);
		u.setId("12345");
		usuarios.put("1", u);
	}
	
	public Usuario obtenerUsuario(String id){
		if(usuarios.containsKey(id))
			return usuarios.get(id);
		else
			return null;
	}
	
	public Hashtable<String, Usuario> obtenerTodos(){
		return usuarios;
	}
}
