import java.util.ArrayList;

public class Market {

    private static ArrayList<FootballPlayer> market;

    public Market() {
        market = new ArrayList<>();
    }

    static public void seePlayersOnMarket() {
        int count = 1;
        for (FootballPlayer footballPlayers : market) {
            System.out.println(count++ +". " + footballPlayers.getClub().getName() + " - " +  footballPlayers.getName() + " | " + footballPlayers.getCost()+"€" + " | Strength: " + footballPlayers.getStrength());
        }
    }

    static public void addPlayerOnMarket(FootballPlayer footballPlayer) {
        market.add(footballPlayer);
    }

    public static ArrayList<FootballPlayer> getMarket() {
        return market;
    }
}
