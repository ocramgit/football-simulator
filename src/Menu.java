import java.util.Scanner;

public class Menu {
    GameCore gameCore;
    Scanner sc = new Scanner(System.in);
    Club club;
    boolean programIsRunning = true;

    public Menu() {
        gameCore = new GameCore();
    }

    public void start() {
        while (programIsRunning) {
            System.out.println("1 - NEXT GAME");
            System.out.println("2 - SELL PLAYER");
            System.out.println("3 - BUY PLAYER");
            System.out.println("4 - CLASSIFICATION");
            System.out.println("5 - CHECK PLAYERS");

            switch (sc.next()) {
                case "1":
                    gameCore.simulate();
                    break;
                case "2":
                    gameCore.getPlayer().getClub().sell();
                    break;
                case "4":
                    gameCore.seeClassification();
                    break;
                case "3":
                    gameCore.getPlayer().getClub().buy();
                    break;
                case "5":
                    gameCore.getportugueseClubListTeams();
                    int choice = sc.nextInt();
                    gameCore.getMoreClubPlayers(choice);
            }
        }
    }
}
