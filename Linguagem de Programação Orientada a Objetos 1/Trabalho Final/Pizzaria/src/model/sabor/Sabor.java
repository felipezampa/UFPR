package model.sabor;

public class Sabor {
    private String nome;
    private Tipo tipo;

    public Sabor(String nome, String tipo) {
        this.nome = nome;
        this.tipo = Tipo.valueOf(tipo);
    }

    public String getNome() {
        return nome;
    }
    
    
    public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
    	return tipo.toString();
    }

	public void setTipo(String tipo) {
		this.tipo = Tipo.valueOf(tipo);
	}
    
}
