import java.util.List;

public abstract class League {

    static List<Club> clubs;

    public League(List<Club> league) {
        clubs = league;
    }


    public abstract void simulate();

    public List<Club> getLeague() {
        return clubs;
    }
}