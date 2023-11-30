public enum ClubType {
    SPORTING_CP("Sporting CP"),
    SL_BENFICA("SL Benfica"),
    FC_PORTO("FC Porto"),
    ESTRELA_AMADORA("Estrela Amadora"),
    SC_BRAGA("SC Braga"),
    MOREIRENSE("Moreirense"),
    VITORIA_SC("Vitória SC"),
    FC_FAMALICAO("FC Famalicão"),
    SC_FARENSE("SC Farense"),
    BOAVISTA_FC("Boavista FC"),
    PORTIMONENSE("Portimonense"),
    GIL_VICENTE_FC("Gil Vicente FC"),
    ESTORIL_PRAIA("Estoril Praia"),
    FC_VIZELA("FC Vizela"),
    CASA_PIA_AC("Casa Pia AC"),
    RIO_AVE_FC("Rio Ave FC"),
    GD_CHAVES("GD Chaves"),
    FC_AROUCA("FC Arouca");

    private String name;

    ClubType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
