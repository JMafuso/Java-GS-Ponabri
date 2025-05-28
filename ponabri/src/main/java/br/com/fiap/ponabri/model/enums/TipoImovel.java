package br.com.fiap.ponabri.model.enums;

public enum TipoImovel {
    CASA("Residencial unifamiliar"),
    APARTAMENTO("Unidade residencial vertical"),
    GALPAO("Depósito industrial/comercial"),
    COMERCIO("Estabelecimento comercial"),
    ESTACIONAMENTO("Área de estacionamento");

    private final String descricao;

    TipoImovel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}