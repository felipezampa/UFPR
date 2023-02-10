/**
 * @author Felipe Augusto Vieira Zampa
 * @author Bruna da Silva Lins
 * Lista 6 exercicio 10
 */
package T06_Ex10;

public abstract class C {
    //ATRIBUTOS
    private String atributo1;
    private float atributo2;
    
    //CONSTRUTORES
    public C() {
        atributo1 = "VAZIO";
        atributo2 = -999.99f;
    }
    
    public C(String p1, float p2) {
        atributo1 = p1;
        atributo2 = p2;
    }
    
    //METODOS
    public String getAtributo1() {
        return atributo1;
    }

    public void setAtributo1(String atributo1) {
        this.atributo1 = atributo1;
    }

    public float getAtributo2() {
        return atributo2;
    }

    public void setAtributo2(float atributo2) {
        this.atributo2 = atributo2;
    }
  
}
