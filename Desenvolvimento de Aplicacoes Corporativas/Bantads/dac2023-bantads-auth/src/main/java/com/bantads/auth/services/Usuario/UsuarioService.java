package com.bantads.auth.services.Usuario;

import com.bantads.auth.model.Usuario;
import com.bantads.auth.repository.UsuarioRepository;
import com.bantads.auth.services.rabbitMQ.RabbitMqController;
import com.bantads.auth.utils.PasswordEncoderUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RabbitMqController rabbitMqController;
	@Autowired
	private ObjectMapper objectMapper;

	public void RegistroUsuario(JsonNode node) throws JsonProcessingException {
		JsonNode managerNode = node.get("manager");
		String nome = managerNode.get("nome").asText();
		String email = managerNode.get("email").asText();
		String id = managerNode.get("id").asText();
		System.out.println(node);

		if (usuarioRepository.existsByEmail(email)) {
			rabbitMqController.enviarMensagem(node, "auth-duplicated", "DUPLICADO");
			return;
		}

		byte[] salt = PasswordEncoderUtil.saltGenerator();
		String passwordGenerated = PasswordEncoderUtil.generatePassword();
		String senhaCripto = criptoSenha(passwordGenerated, salt);

		Usuario usuario = new Usuario(id, nome, email, senhaCripto, "user",
				new String(Base64.getEncoder().encode(salt)));
		usuarioRepository.save(usuario);
//        usuario = objectMapper.readValue(node.toString(), Usuario.class);
//        usuarioRepository.save(usuario);
		rabbitMqController.enviarMensagem(node, "auth-ok", "OK");
	}

	public void UpdateUsuario(JsonNode node) {

		JsonNode managerNode = node.get("manager");
		System.out.println(node.toString());

		String email = managerNode.get("email").asText();
		String nome = managerNode.get("nome").asText();
		String id = managerNode.get("id").asText();

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isEmpty()) {
			rabbitMqController.enviarMensagem(node, "auth-update-error", "NÃO HÁ CLIENTE COM ESSE ID");

		}
		usuario.get().setEmail(email);
		usuario.get().setNome(nome);
		usuarioRepository.save(usuario.get());
		rabbitMqController.enviarMensagem(node, "auth-updated", "ATUALIZADO");
	}

	public void DeleteUsuario(JsonNode node) {
		JsonNode managerNode = node.get("manager");
		String id = managerNode.get("id").asText();
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isEmpty()) {
			rabbitMqController.enviarMensagem(node, "auth-delete-error", "FALHA AO DELETAR");
		}
		usuarioRepository.deleteById(usuario.get().getId());
		rabbitMqController.enviarMensagem(node, "auth-deleted", "DELETADO");
	}

	public String criptoSenha(String password, byte[] salt) {
		return PasswordEncoderUtil.encodePassword(password, salt);
	}
	public void RegistroGerente(JsonNode node) throws JsonProcessingException {
		JsonNode managerNode = node.get("manager");
		String nome = managerNode.get("nome").asText();
		String email = managerNode.get("email").asText();
		String id = managerNode.get("id").asText();
		String senha = managerNode.get("password").asText();

		System.out.println(node);

		if (usuarioRepository.existsByEmail(email)) {
			rabbitMqController.enviarMensagem(node, "auth-duplicated", "DUPLICADO");
			return;
		}

		byte[] salt = PasswordEncoderUtil.saltGenerator();
		String senhaCripto = criptoSenha(senha, salt);

		Usuario usuario = new Usuario(id, nome, email, senhaCripto, "gerente",
				new String(Base64.getEncoder().encode(salt)));
		usuarioRepository.save(usuario);
//        usuario = objectMapper.readValue(node.toString(), Usuario.class);
//        usuarioRepository.save(usuario);
		rabbitMqController.enviarMensagem(node, "auth-ok", "OK");
	}
	
	public void UpdateGerente(JsonNode node) {

		JsonNode managerNode = node.get("manager");
		System.out.println(node.toString());

		String email = managerNode.get("email").asText();
		String nome = managerNode.get("nome").asText();
		String id = managerNode.get("id").asText();

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isEmpty()) {
			rabbitMqController.enviarMensagem(node, "auth-update-error", "NÃO HÁ CLIENTE COM ESSE ID");

		}
		usuario.get().setEmail(email);
		usuario.get().setNome(nome);
		usuarioRepository.save(usuario.get());
		rabbitMqController.enviarMensagem(node, "auth-updated", "ATUALIZADO");
	}

	public void DeleteGerente(JsonNode node) {
		JsonNode managerNode = node.get("manager");
		String id = managerNode.get("id").asText();
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isEmpty()) {
			rabbitMqController.enviarMensagem(node, "auth-delete-error", "FALHA AO DELETAR");
		}
		usuarioRepository.deleteById(usuario.get().getId());
		rabbitMqController.enviarMensagem(node, "auth-deleted", "DELETADO");
	}
	
	public boolean loginVerificar(String email, String password) {
	
		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario != null) {
			byte[] salt = Base64.getDecoder().decode(usuario.getSalt());
			String passwordCripto = criptoSenha(password, salt) + usuario.getSalt();
			String senhaSalt = usuario.getSenha() + usuario.getSalt();
			if (passwordCripto.equals(senhaSalt)) {
				return true; // Senha correta
			}
		}
		return false; // Usuário não existe ou senha incorreta
	}

	public void aprovarLogin(JsonNode node) {
		JsonNode managerNode = node.get("customer");
		String id = managerNode.get("id").asText();
		String senha = managerNode.get("password").asText();
		byte[] salt = PasswordEncoderUtil.saltGenerator();
		String senhaCripto = criptoSenha(senha, salt);
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		Usuario usuario = usuarioOptional.get();
		usuario.setSenha(senhaCripto);
		usuario.setSalt(new String(Base64.getEncoder().encode(salt)));
		usuarioRepository.save(usuario);
	}

}
