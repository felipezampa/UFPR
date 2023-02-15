package com.ufpr.tads.web2.enums;

public enum SituacaoPedido {
    AGUARDANDO_ORCAMENTO("Aguardando orçamento", "table-warning"),
    AGUARDANDO_APROVACAO("Aguardando aprovação", "table-warning"),
    EM_ABERTO("Em aberto", "table-warning"),
    CANCELADO("Cancelado", "table-danger"),
    REJEITADO("Rejeitado", "table-danger"),
    RECOLHIDO("Recolhido", "table-active"),
    AGUARDANDO_PAGAMENTO("Aguardando pagamento", "table-primary"),
    PAGO("Pago", "table-orange"),
    FINALIZADO("Finalizado", "table-success");

    private String descricao;
    private String classe;

    private SituacaoPedido(String descricao, String classe) {
        this.descricao = descricao;
        this.classe = classe;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getClasse() {
        return classe;
    }
}