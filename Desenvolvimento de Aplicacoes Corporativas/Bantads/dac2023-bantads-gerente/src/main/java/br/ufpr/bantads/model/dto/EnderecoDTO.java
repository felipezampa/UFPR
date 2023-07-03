package br.ufpr.bantads.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO{
    private Long id;
    private String cep;
    private String bairro;
    private int numero;
    private String logadouro;
    private String complemento;
    private String estado;
    private String cidade;
}
