public class ClubFactory {
    public static Club create(ClubType clubType) {
        return switch (clubType) {
            case SPORTING_CP -> new Club(clubType.getName(), 1000000000);
            case SL_BENFICA -> new Club(clubType.getName(), 2000000000);
            case FC_PORTO -> new Club(clubType.getName(), 1500000000);
            case ESTRELA_AMADORA -> new Club(clubType.getName(), 540000000);
            case SC_BRAGA -> new Club(clubType.getName(), 900000000);
            case MOREIRENSE -> new Club(clubType.getName(), 2000050000);
            case VITORIA_SC -> new Club(clubType.getName(), 400000000);
            case FC_FAMALICAO ->  new Club(clubType.getName(), 600000000);
            case SC_FARENSE -> new Club(clubType.getName(), 300000000);
            case BOAVISTA_FC -> new Club(clubType.getName(), 500000000);
            case PORTIMONENSE -> new Club(clubType.getName(), 250000000);
            case GIL_VICENTE_FC -> new Club(clubType.getName(), 460000000);
            case ESTORIL_PRAIA -> new Club(clubType.getName(), 1600000000);
            case FC_VIZELA -> new Club(clubType.getName(), 380000000);
            case CASA_PIA_AC -> new Club(clubType.getName(), 1700000000);
            case RIO_AVE_FC -> new Club(clubType.getName(), 510000000);
            case GD_CHAVES -> new Club(clubType.getName(), 310000000);
            case FC_AROUCA -> new Club(clubType.getName(), 1000040000);
        };
    }
}
