import java.util.ArrayList;
import java.util.Scanner;

public class Club implements Playable {
    private ArrayList<FootballPlayer> squad;
    private String name;
    private int club_balance, average_strength, matches, wins, losses, draws, points, goalsScored, goalsConceded;
    private Scanner sc;
    private PortugueseLeague portugueseLeague;
    private Player player;

    public Club(String name, int clubBalance) {
        squad = new ArrayList<>();
        this.name = name;
        sc = new Scanner(System.in);
        portugueseLeague = new PortugueseLeague(League.clubs);
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getName() {
        return name;
    }

    public int getDraws() {
        return draws;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public void setAverage_strength(int average_strength) {
        this.average_strength = average_strength;
    }

    public int getAverage_strength() {
        return average_strength;
    }

    public int getClub_balance() {
        return club_balance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getMatches() {
        return matches;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public ArrayList<FootballPlayer> getSquad() {
        return squad;
    }

    @Override
    public void play() {
        if(squad.size() > 10) {
            portugueseLeague.simulate();
        } else {
            System.out.println("Your team don't have 11 players. Go to the market.");
        }
    }

    @Override
    public void sell() {
        int count = 1;
        for (FootballPlayer footballPlayer : squad) {
            System.out.println(count++ + " > " + footballPlayer.getName() + " | " + footballPlayer.getCost()+"€");
        }

        System.out.print("Player to sell: ");
        FootballPlayer player = squad.get(sc.nextInt()-1);

        Market.addPlayerOnMarket(player);
    }

    public void sellComputer() {
        int getPlayerToSell = (int) (Math.random() * 11);
        Market.addPlayerOnMarket(squad.get(getPlayerToSell));
    }

    @Override
    public void buy() {
        Market.seePlayersOnMarket();
        System.out.println("What player you want to buy?");
    }
}