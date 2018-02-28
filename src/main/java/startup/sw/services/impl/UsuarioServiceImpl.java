package startup.sw.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import startup.sw.entities.Usuario;
import startup.sw.repositories.UsuarioRepository;
import startup.sw.services.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRep;
	
	public UsuarioServiceImpl(){	
	}
	
	@Override
	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRep.save(usuario);
	}
}
