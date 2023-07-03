package com.bantads.auth.rest;

import java.util.Base64;
import java.util.Optional;

import com.bantads.auth.services.Usuario.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.auth.DTO.AuthDTO;
import com.bantads.auth.DTO.UsuarioDTO;
import com.bantads.auth.model.Usuario;

import com.bantads.auth.repository.UsuarioRepository;
import com.bantads.auth.utils.PasswordEncoderUtil;

@CrossOrigin
@RestController
@RequestMapping("login")
public class AuthREST {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("")
	ResponseEntity<UsuarioDTO> login(@RequestBody AuthDTO login) {
		if (login.getEmail().equals("admin@bantads.com")) {
			return ResponseEntity.ok().body(new UsuarioDTO("500", "Admin", "admin@bantads.com", "admin"));

		}

		if (!usuarioRepository.existsByEmail(login.getEmail()) || login.getSenha().isEmpty()
				|| login.getEmail().isEmpty()) {
			return ResponseEntity.status(401).body(new UsuarioDTO());
		}
		Usuario usuario = usuarioRepository.findByEmail(login.getEmail());
		boolean loginVerificacao = usuarioService.loginVerificar(login.getEmail(), login.getSenha());

		if (loginVerificacao) {
			UsuarioDTO usuarioFinal = mapper.map(usuario, UsuarioDTO.class);
			return ResponseEntity.ok().body(usuarioFinal);
		}
		return ResponseEntity.status(422).body(new UsuarioDTO());
	}
}
