import java.util.Random;

public class Game {
    private int home;
    private int away;

    private Club first_club;
    private Club second_club;

    public Game(Club first_club, Club second_club) {
        this.first_club = first_club;
        this.second_club = second_club;
    }

    public void startMatch() {
        first_club.setMatches(first_club.getMatches() + 1);
        second_club.setMatches(second_club.getMatches() + 1);
        home = first_club.getAverage_strength() > second_club.getAverage_strength() ? (int) (Math.random() * (9 - 1)) + 1 : (int) (Math.random() * 4);
        away = second_club.getAverage_strength() > first_club.getAverage_strength() ? (int) (Math.random() * (9 - 1)) + 1 : (int) (Math.random() * 4);

        if (home > away) {
            first_club.setWins(first_club.getWins() + 1);
            second_club.setLosses(second_club.getLosses() + 1);
            first_club.setPoints(first_club.getPoints() + 3);
        } else if (home < away) {
            second_club.setWins(second_club.getWins() + 1);
            first_club.setLosses(first_club.getLosses() + 1);
            second_club.setPoints(second_club.getPoints() + 3);
            second_club.setGoalsConceded(second_club.getGoalsConceded() + home);
            second_club.setGoalsScored(second_club.getGoalsScored() + away);
        } else {
            first_club.setDraws(first_club.getDraws() + 1);
            second_club.setDraws(second_club.getDraws() + 1);
            first_club.setPoints(first_club.getPoints() + 1);
        }

        first_club.setGoalsConceded(first_club.getGoalsConceded() + away);
        first_club.setGoalsScored(first_club.getGoalsScored() + home);
        second_club.setGoalsConceded(second_club.getGoalsConceded() + home);
        second_club.setGoalsScored(second_club.getGoalsScored() + away);

        System.out.println(first_club.getName() + " " + home + " - " + away + " " + second_club.getName());

        int first = (int) (Math.random() * 100);
        int second = (int) (Math.random() * 100);

        if (first_club.getSquad().size() > 9) {
                if (first <= 10) first_club.sellComputer();
        }

        if (second_club.getSquad().size() > 9) {
                if (second <= 10) second_club.sellComputer();
        }
    }


    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }
}
