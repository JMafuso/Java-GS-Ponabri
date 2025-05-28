package br.com.fiap.ponabri.model.enums;

public enum Status {
    PENDPENDENTE("Pedido pendente de análise"),
    ACEITO("Pedido aceito"),
    NEGADO("Pedido negado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
