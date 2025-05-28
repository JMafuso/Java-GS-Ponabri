package br.com.fiap.ponabri.model.enums;

public enum TipoRisco {
    DESLIZAMENTO("Deslizamento de terra"),
    ERUPCAO_VULCANICA("Erupção vulcânica"),
    FURACOES("Furacões"),
    INUNDACAO("Inundação"),
    TEMPESTADE("Tempestade"),
    TERREMOTO("Terremoto");

    private final String descricao;

    TipoRisco(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}


