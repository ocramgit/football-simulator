import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortugueseLeague extends League {

    public PortugueseLeague(List<Club> league) {
        super(league);
    }

    @Override
    public void simulate() {
        if(clubs.get(0).getMatches() != 31) {
            List<Club> remainingTeams = new ArrayList<>(getLeague());
            Collections.shuffle(remainingTeams);

            int numTeams = remainingTeams.size();
            int numMatchesPerRound = Math.min(18, numTeams / 2 * 2);

            for (int i = 0; i < numMatchesPerRound; i += 2) {
                Club team1 = remainingTeams.get(i);
                Club team2 = remainingTeams.get(i + 1);

                Game game = new Game(team1, team2);

                game.startMatch();
            }
            System.out.println();
        } else {
            System.out.println("The season already ended. Check classifications.");
        }
    }
}
