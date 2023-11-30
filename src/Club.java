import java.util.ArrayList;
import java.util.Scanner;

public class Club implements Playable {
    private ArrayList<FootballPlayer> squad;
    private String name;
    private int club_balance, average_strength, matches, wins, losses, draws, points, goalsScored, goalsConceded, loseStreak;
    private Scanner sc;
    private PortugueseLeague portugueseLeague;
    private Player player;
    private int classification = 0;

    public Club(String name, int clubBalance) {
        squad = new ArrayList<>();
        this.name = name;
        sc = new Scanner(System.in);
        portugueseLeague = new PortugueseLeague(League.clubs);
        this.club_balance = clubBalance;
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
        portugueseLeague.simulate();
        if(loseStreak == 3) {
            player.setFired(true);
            player = null;
            name = name.replaceAll("\u001B\\[[;\\d]*m", "");
        }
    }

    @Override
    public void sell() {
        int count = 1;
        for (FootballPlayer footballPlayer : squad) {
            System.out.println(count++ + " > " + footballPlayer.getName() + " | " + footballPlayer.getCost() + "€");
        }
        System.out.println(0 + " > " + "Nobody.");

        System.out.print("Player to sell: ");
        int choice = sc.nextInt() - 1;
        if (choice == -1) {
        } else {
            FootballPlayer player = squad.get(choice);
            Market.addPlayerOnMarket(player);
            System.out.println();
            System.out.println(player.getName() + " added on the Market.");
        }
    }

    public void sellComputer() {
        int getPlayerToSell = (int) (Math.random() * squad.size());
        Market.addPlayerOnMarket(squad.get(getPlayerToSell));
        System.out.println(squad.get(getPlayerToSell).getName() + " entered in the market by " + name);
    }

    @Override
    public void buy() {
        if (Market.getMarket().isEmpty()) return;
        Market.seePlayersOnMarket();
        System.out.println();
        int choice = sc.nextInt() - 1;
        for (FootballPlayer footballPlayer : squad) {
            if(Market.getMarket().get(choice).getName().equals(footballPlayer.getName())) {
                System.out.println("You can't buy your player.");
                return;
            }
        }
        if (club_balance < Market.getMarket().get(choice).getCost()) return;
        squad.add(Market.getMarket().get(choice));
        Club club = Market.getMarket().get(choice).getClub();
        club.getSquad().remove(choice);
        System.out.println();
        System.out.println(Market.getMarket().get(choice).getName() + " was bought by " + name);
        club_balance -= Market.getMarket().get(choice).getCost();
        Market.getMarket().remove(choice);
    }

    public void buyComputer() {
        if (Market.getMarket().isEmpty()) return;
        int random = (int) (Math.random() * Market.getMarket().size());
        for (FootballPlayer footballPlayer : squad) {
            if(Market.getMarket().get(random).getName().equals(footballPlayer.getName())) {
                return;
            }
        }
        if (club_balance < Market.getMarket().get(random).getCost()) return;
        squad.add(Market.getMarket().get(random));
        Club club = Market.getMarket().get(random).getClub();
        FootballPlayer boughtPlayer = Market.getMarket().get(random);
        club.getSquad().remove(boughtPlayer);
        System.out.println(Market.getMarket().get(random).getName() + " was bought by " + name);
        club_balance -= Market.getMarket().get(random).getCost();
        Market.getMarket().remove(random);
    }

    public int getLoseStreak() {
        return loseStreak;
    }

    public void setLoseStreak(int loseStreak) {
        this.loseStreak = loseStreak;
    }
}