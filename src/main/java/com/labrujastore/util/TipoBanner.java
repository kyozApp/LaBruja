package com.labrujastore.util;

public enum TipoBanner {
    SLIDER("TipoSlider"),
    BANNERCOMUN("TipoBannerComun"),
    BANNERMEDIUM("TipoBannerMedium"),
    BANNERPREMIUN("TipoBannerPremiun"),
    CATEGORIA("TipoCategoria");

    private final String tipo;

    TipoBanner(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
