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
        first_club.setMatches(first_club.getMatches()+1);
        second_club.setMatches(second_club.getMatches()+1);
        double firstClubStrength = first_club.getAverage_strength();
        double secondClubStrength = second_club.getAverage_strength();

        int numPlayersFirstClub = first_club.getSquad().size();
        int numPlayersSecondClub = second_club.getSquad().size();

        double penaltyFactor = 1.0;
        if (numPlayersFirstClub < 11) {
            penaltyFactor *= 0.1;
            if (numPlayersFirstClub <= 7) {
                home = 0;
                away = 3;
            }
        }
        if (numPlayersSecondClub < 11) {
            penaltyFactor *= 0.1;
            if (numPlayersSecondClub <= 7) {
                home = 3;
                away = 0;
            }
        }

        double strengthDifference = Math.abs(firstClubStrength - secondClubStrength);

        int maxGoalsPerTeam = 5;

        double scoringFactor = 0.4 / (1 + Math.exp(-strengthDifference));

        double fixedWinProbability = 0.05;

        scoringFactor = scoringFactor * (1 - fixedWinProbability) + fixedWinProbability;

        scoringFactor *= penaltyFactor;

        if (home == 0 && away == 0) {
            home = (firstClubStrength > secondClubStrength) ? generateRandomGoals(strengthDifference, maxGoalsPerTeam, scoringFactor) : generateRandomGoals(0, maxGoalsPerTeam, scoringFactor);
            away = (secondClubStrength > firstClubStrength) ? generateRandomGoals(strengthDifference, maxGoalsPerTeam, scoringFactor) : generateRandomGoals(0, maxGoalsPerTeam, scoringFactor);
        }


            if (home > away) {
            first_club.setWins(first_club.getWins() + 1);
            second_club.setLosses(second_club.getLosses() + 1);
            second_club.setLoseStreak(second_club.getLoseStreak()+1);
            first_club.setLoseStreak(0);
            first_club.setPoints(first_club.getPoints() + 3);
        } else if (home < away) {
            second_club.setWins(second_club.getWins() + 1);
            first_club.setLosses(first_club.getLosses() + 1);
            first_club.setLoseStreak(first_club.getLoseStreak()+1);
            second_club.setLoseStreak(0);
            second_club.setPoints(second_club.getPoints() + 3);
            second_club.setGoalsConceded(second_club.getGoalsConceded() + home);
            second_club.setGoalsScored(second_club.getGoalsScored() + away);
        } else {
            first_club.setDraws(first_club.getDraws() + 1);
            first_club.setLoseStreak(0);
            second_club.setLoseStreak(0);
            second_club.setDraws(second_club.getDraws() + 1);
            first_club.setPoints(first_club.getPoints() + 1);
        }

        first_club.setGoalsConceded(first_club.getGoalsConceded() + away);
        first_club.setGoalsScored(first_club.getGoalsScored() + home);
        second_club.setGoalsConceded(second_club.getGoalsConceded() + home);
        second_club.setGoalsScored(second_club.getGoalsScored() + away);

        for (int i = 0; i < home; i++) {
            int random = randomOne();
            first_club.getSquad().get(random).setGoals(first_club.getSquad().get(random).getGoals() + 1);

            int random2 = randomOne();
            first_club.getSquad().get(random2).setAssists(first_club.getSquad().get(random2).getAssists() + 1);
        }

        for (int i = 0; i < away; i++) {
            int random = randomTwo();
            second_club.getSquad().get(random).setGoals(second_club.getSquad().get(random).getGoals() + 1);

            int random2 = randomTwo();
            second_club.getSquad().get(random2).setAssists(second_club.getSquad().get(random2).getAssists() + 1);

        }

        System.out.println("\u001b[37m" + first_club.getName() + " \u001b[0m" + home + " \u001b[37m-\u001b[0m " + away + " " + "\u001b[37m" + second_club.getName() + "\u001b[0m");
    }


    public int getHome() {
        return home;
    }

    public int randomOne() {
        return (int) (Math.random() * (first_club.getSquad().size() - 1) + 1);
    }

    public int randomTwo() {
        return (int) (Math.random() * (second_club.getSquad().size() - 1) + 1);
    }


    public void loading() {
        System.out.print("Loading...");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.flush();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\r");
        System.out.print("                ");
        System.out.print("\r");
    }

    int generateRandomGoals(double strengthDifference, int maxGoalsPerTeam, double scoringFactor) {
        int maxGoals = (int) (maxGoalsPerTeam + strengthDifference * scoringFactor);
        return (int) (Math.random() * (maxGoals - 1)) + 1;
    }

    public int getAway() {
        return away;
    }
}
