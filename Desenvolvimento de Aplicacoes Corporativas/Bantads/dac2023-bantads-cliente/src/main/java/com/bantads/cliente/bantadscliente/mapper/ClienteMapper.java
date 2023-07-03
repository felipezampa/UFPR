package com.bantads.cliente.bantadscliente.mapper;

import org.modelmapper.ModelMapper;

import com.bantads.cliente.bantadscliente.DTOs.PostClienteDTO;
import com.bantads.cliente.bantadscliente.DTOs.PutClienteDTO;
import com.bantads.cliente.bantadscliente.model.Cliente;
import com.bantads.cliente.bantadscliente.model.Endereco;

public final class ClienteMapper {
    public static void map(Cliente cliente, PutClienteDTO putClienteDTO, ModelMapper mapper) {
        cliente.setNome(putClienteDTO.getNome());
        cliente.setSalario(putClienteDTO.getSalario());
        cliente.setCpf(putClienteDTO.getCpf());
        cliente.setSenha(putClienteDTO.getSenha());
        cliente.setEmail(putClienteDTO.getEmail());
        cliente.getEndereco().setCep(putClienteDTO.getEndereco().getCep());
        cliente.getEndereco().setLogradouro(putClienteDTO.getEndereco().getLogradouro());
        cliente.getEndereco().setNumero(putClienteDTO.getEndereco().getNumero());
        cliente.getEndereco().setComplemento(putClienteDTO.getEndereco().getComplemento());
        cliente.getEndereco().setBairro(putClienteDTO.getEndereco().getBairro());
        cliente.getEndereco().setCidade(putClienteDTO.getEndereco().getCidade());
        cliente.getEndereco().setEstado(putClienteDTO.getEndereco().getEstado());
    }
    
    public static void map(Cliente cliente, Cliente putClienteDTO, ModelMapper mapper) {
        cliente.setNome(putClienteDTO.getNome());
        cliente.setSalario(putClienteDTO.getSalario());
        cliente.setCpf(putClienteDTO.getCpf());
        cliente.setSenha(putClienteDTO.getSenha());
        cliente.setEmail(putClienteDTO.getEmail());
        cliente.getEndereco().setCep(putClienteDTO.getEndereco().getCep());
        cliente.getEndereco().setLogradouro(putClienteDTO.getEndereco().getLogradouro());
        cliente.getEndereco().setNumero(putClienteDTO.getEndereco().getNumero());
        cliente.getEndereco().setComplemento(putClienteDTO.getEndereco().getComplemento());
        cliente.getEndereco().setBairro(putClienteDTO.getEndereco().getBairro());
        cliente.getEndereco().setCidade(putClienteDTO.getEndereco().getCidade());
        cliente.getEndereco().setEstado(putClienteDTO.getEndereco().getEstado());
    }

    public static void map(Cliente cliente, PostClienteDTO postClienteDTO, ModelMapper mapper) {
        cliente.setNome(postClienteDTO.getNome());
        cliente.setSalario(postClienteDTO.getSalario());
        cliente.setCpf(postClienteDTO.getCpf());
        cliente.setSenha(postClienteDTO.getSenha());
        cliente.setEmail(postClienteDTO.getEmail());

        Endereco endereco = new Endereco();
        endereco.setCep(postClienteDTO.getEndereco().getCep());
        endereco.setLogradouro(postClienteDTO.getEndereco().getLogradouro());
        endereco.setNumero(postClienteDTO.getEndereco().getNumero());
        endereco.setComplemento(postClienteDTO.getEndereco().getComplemento());
        endereco.setBairro(postClienteDTO.getEndereco().getBairro());
        endereco.setCidade(postClienteDTO.getEndereco().getCidade());
        endereco.setEstado(postClienteDTO.getEndereco().getEstado());
        cliente.setEndereco(endereco);
    }
}
