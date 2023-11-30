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
            if (!gameCore.getPlayer().isFired()) {
                System.out.println();
                System.out.println("𝟭 - 𝗡𝗘𝗫𝗧 𝗚𝗔𝗠𝗘");
                System.out.println("𝟮 - 𝗦𝗘𝗟𝗟 𝗣𝗟𝗔𝗬𝗘𝗥 \u001b[31m(" + gameCore.getPlayer().getClub().getSquad().size() + " players)\u001b[0m");
                System.out.println("𝟯 - 𝗕𝗨𝗬 𝗣𝗟𝗔𝗬𝗘𝗥 \u001b[31m(" + Market.getMarket().size() + " players)\u001b[0m");
                System.out.println("𝟰 - 𝗖𝗟𝗔𝗦𝗦𝗜𝗙𝗜𝗖𝗔𝗧𝗜𝗢𝗡 \u001b[31m(" + gameCore.getClassification() + "º)\u001b[0m");
                System.out.println("𝟱 - 𝗖𝗛𝗘𝗖𝗞 𝗣𝗟𝗔𝗬𝗘𝗥𝗦");
                System.out.print("Option: ");

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
                        gameCore.getPortugueseClubListTeams();
                        int choice = sc.nextInt();
                        gameCore.getMoreClubPlayers(choice);
                }
            } else {
                System.out.println("\n\nYou're fired because 3 consecutive loses. Please select other club.");
                System.out.println("1 - Select another club");
                System.out.println("2 - Retire from football");
                System.out.print("Option: ");

                switch (sc.next()) {
                    case "1":
                        gameCore.addPlayerToClub();
                        break;
                    case "2":
                        programIsRunning = false;
                        break;
                }
            }
        }
    }
}
