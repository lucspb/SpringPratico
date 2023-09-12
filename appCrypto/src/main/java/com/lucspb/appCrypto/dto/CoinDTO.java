package com.lucspb.appCrypto.dto;

import java.math.BigDecimal;

public class CoinDTO {

    private String name;
    private BigDecimal qtd;

    public CoinDTO(String name, BigDecimal qtd){
        this.name = name;
        this.qtd = qtd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQtd() {
        return qtd;
    }

    public void setQtd(BigDecimal qtd) {
        this.qtd = qtd;
    }
}
