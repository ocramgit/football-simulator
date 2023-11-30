import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortugueseLeague extends League {

    public PortugueseLeague(List<Club> league) {
        super(league);
    }

    @Override
    public void simulate() {
        System.out.println();
        if (clubs.get(0).getMatches() != 31) {
            List<Club> remainingTeams = new ArrayList<>(getLeague());
            Collections.shuffle(remainingTeams);

            int numTeams = remainingTeams.size();
            int numMatchesPerRound = Math.min(18, numTeams / 2 * 2);

            Club team1 = null;
            Club team2 = null;

            for (int i = 0; i < numMatchesPerRound; i += 2) {
                team1 = remainingTeams.get(i);
                team2 = remainingTeams.get(i + 1);

                Game game = new Game(team1, team2);

                game.startMatch();
            }

            System.out.println();
            if (getNumber() < 50) team1.sellComputer();
            if (getNumber() < 50) team2.sellComputer();
            if (getNumber() > 75) team1.buyComputer();
            if (getNumber() > 75) team2.buyComputer();

            if (team1.getSquad().size() > 11) team1.sellComputer();
            if (team2.getSquad().size() > 11) team2.sellComputer();
            if (team1.getSquad().size() < 11) team1.buyComputer();
            if (team2.getSquad().size() < 11) team2.buyComputer();
        } else {
            System.out.println("\n\uD835\uDE4F\uD835\uDE5D\uD835\uDE5A \uD835\uDE68\uD835\uDE5A\uD835\uDE56\uD835\uDE68\uD835\uDE64\uD835\uDE63 \uD835\uDE56\uD835\uDE61\uD835\uDE67\uD835\uDE5A\uD835\uDE56\uD835\uDE59\uD835\uDE6E \uD835\uDE5A\uD835\uDE63\uD835\uDE59\uD835\uDE5A\uD835\uDE59. \uD835\uDE3E\uD835\uDE5D\uD835\uDE5A\uD835\uDE58\uD835\uDE60 \uD835\uDE58\uD835\uDE61\uD835\uDE56\uD835\uDE68\uD835\uDE68\uD835\uDE5E\uD835\uDE5B\uD835\uDE5E\uD835\uDE58\uD835\uDE56\uD835\uDE69\uD835\uDE5E\uD835\uDE64\uD835\uDE63\uD835\uDE68.\n");
        }
    }

    public int getNumber() {
        return (int) (Math.random() * 100);
    }
}
