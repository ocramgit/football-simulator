import java.util.ArrayList;
import java.util.InputMismatchException;
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
        if (loseStreak == 3) {
            player.setFired(true);
            player = null;
            name = name.replaceAll("\u001B\\[[;\\d]*m", "");
        }
    }

    @Override
    public void sell() {
        int count = 1;
        for (FootballPlayer footballPlayer : squad) {
            System.out.println("\u001b[31;1m" + count++ + "\u001b[0m > " + footballPlayer.getName() + " | " + footballPlayer.getCost() + "€" + " | Strength: " + footballPlayer.getStrength());
        }
        System.out.println(0 + " > " + "\uD835\uDDE1\uD835\uDDFC\uD835\uDDEF\uD835\uDDFC\uD835\uDDF1\uD835\uDE06.");

        try {
            int choice;
            while (true) {
                try {
                    System.out.print("\nSelect a player to sell: ");
                    choice = sc.nextInt() - 1;
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("\uD835\uDE44\uD835\uDE63\uD835\uDE6B\uD835\uDE56\uD835\uDE61\uD835\uDE5E\uD835\uDE59 \uD835\uDE63\uD835\uDE6A\uD835\uDE62\uD835\uDE57\uD835\uDE5A\uD835\uDE67.");
                    sc.next();
                }
            }
            if (choice == -1) {
                return;
            } else {
                FootballPlayer player = squad.get(choice);
                for (FootballPlayer footballPlayer : Market.getMarket()) {
                    if (squad.get(choice).equals(footballPlayer))
                        throw new AlreadyOnMarketException("Already on market.");
                }
                Market.addPlayerOnMarket(player);
                System.out.println();
                System.out.println(player.getName() + " \uD835\uDE56\uD835\uDE59\uD835\uDE59\uD835\uDE5A\uD835\uDE59 \uD835\uDE64\uD835\uDE63 \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE48\uD835\uDE56\uD835\uDE67\uD835\uDE60\uD835\uDE5A\uD835\uDE69.");
            }
        } catch (AlreadyOnMarketException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sellComputer() {
        try {
            int getPlayerToSell = (int) (Math.random() * squad.size());
            for (FootballPlayer footballPlayer : Market.getMarket()) {
                if (squad.get(getPlayerToSell).equals(footballPlayer))
                    throw new AlreadyOnMarketException("Already on market.");
            }
            Market.addPlayerOnMarket(squad.get(getPlayerToSell));
            System.out.println(squad.get(getPlayerToSell).getName() + " \uD835\uDC86\uD835\uDC8F\uD835\uDC95\uD835\uDC86\uD835\uDC93\uD835\uDC86\uD835\uDC85 \uD835\uDC8A\uD835\uDC8F \uD835\uDC95\uD835\uDC89\uD835\uDC86 \uD835\uDC8E\uD835\uDC82\uD835\uDC93\uD835\uDC8C\uD835\uDC86\uD835\uDC95 \uD835\uDC83\uD835\uDC9A " + name);
        } catch (AlreadyOnMarketException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void buy() {
        if (Market.getMarket().isEmpty()) return;
        Market.seePlayersOnMarket();
        System.out.println(0 + " > " + "\uD835\uDDE1\uD835\uDDFC\uD835\uDDEF\uD835\uDDFC\uD835\uDDF1\uD835\uDE06.");
        int choice;
        while (true) {
            try {
                System.out.print("\nSelect a player to buy: ");
                choice = sc.nextInt() - 1;
                break;
            } catch (InputMismatchException e) {
                System.out.println("\uD835\uDE44\uD835\uDE63\uD835\uDE6B\uD835\uDE56\uD835\uDE61\uD835\uDE5E\uD835\uDE59 \uD835\uDE63\uD835\uDE6A\uD835\uDE62\uD835\uDE57\uD835\uDE5A\uD835\uDE67.");
                sc.next();
            }
        }
        if(choice == -1) {
            return;
        }
        for (FootballPlayer footballPlayer : squad) {
            if (Market.getMarket().get(choice).getName().equals(footballPlayer.getName())) {
                System.out.println("\uD835\uDDEC\uD835\uDDFC\uD835\uDE02 \uD835\uDDF0\uD835\uDDEE\uD835\uDDFB'\uD835\uDE01 \uD835\uDDEF\uD835\uDE02\uD835\uDE06 \uD835\uDE06\uD835\uDDFC\uD835\uDE02\uD835\uDDFF \uD835\uDDFD\uD835\uDDF9\uD835\uDDEE\uD835\uDE06\uD835\uDDF2\uD835\uDDFF.");
                return;
            }
        }
        if (club_balance < Market.getMarket().get(choice).getCost()) return;
        squad.add(Market.getMarket().get(choice));
        Club club = Market.getMarket().get(choice).getClub();
        club.getSquad().remove(choice);
        System.out.println();
        System.out.println(Market.getMarket().get(choice).getName() + " \uD835\uDE6C\uD835\uDE56\uD835\uDE68 \uD835\uDE57\uD835\uDE64\uD835\uDE6A\uD835\uDE5C\uD835\uDE5D\uD835\uDE69 \uD835\uDE57\uD835\uDE6E " + name);
        club_balance -= Market.getMarket().get(choice).getCost();
        Market.getMarket().remove(choice);
    }

    public void buyComputer() {
        if (Market.getMarket().isEmpty()) return;
        int random = (int) (Math.random() * Market.getMarket().size());
        for (FootballPlayer footballPlayer : squad) {
            if (Market.getMarket().get(random).getName().equals(footballPlayer.getName())) {
                return;
            }
        }
        if (club_balance < Market.getMarket().get(random).getCost()) return;
        squad.add(Market.getMarket().get(random));
        Club club = Market.getMarket().get(random).getClub();
        FootballPlayer boughtPlayer = Market.getMarket().get(random);
        club.getSquad().remove(boughtPlayer);
        System.out.println(Market.getMarket().get(random).getName() + " \uD835\uDE6C\uD835\uDE56\uD835\uDE68 \uD835\uDE57\uD835\uDE64\uD835\uDE6A\uD835\uDE5C\uD835\uDE5D\uD835\uDE69 \uD835\uDE57\uD835\uDE6E " + name);
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