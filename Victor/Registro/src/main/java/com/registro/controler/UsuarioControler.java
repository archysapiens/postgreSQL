package com.registro.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.model.Usuario;
import com.registro.service.UsuarioService;

@CrossOrigin(origins =  "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioControler {
	
	@Autowired
	UsuarioService service;
	
	@GetMapping("/listarId/{id}")
	public List<Usuario> buscarId(@PathVariable int id) {
		return service.listarId(id);
	}
	
	@GetMapping("/listarCorreo/{correo}")
	public List<Usuario> buscarId(@PathVariable String correo) {
		return service.listarCorreo(correo);
	}
		
	@PostMapping("/nuevo")
	public Usuario nuevo(@RequestBody Usuario u) throws Exception {
		String tempemail = u.getEmail();
		if(tempemail != null) {
			List<Usuario> userobj = service.listarCorreo(tempemail);
			if(!userobj.isEmpty()) {
				throw new Exception("El usuario "+tempemail+" ya existe");
			}
		}
		Usuario user = null;
		user = service.add(u);
		return user;
	}
	
	@PutMapping("/actualizar")
	public Usuario actualizar(@RequestBody Usuario u) {
		return service.update(u);
	}
	
	@DeleteMapping("/borrar")
	public Usuario borrar(@RequestBody int id) {
		return service.deleteId(id);
	}

}
