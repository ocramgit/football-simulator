import java.util.InputMismatchException;
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
                        System.out.println("\n\uD835\uDE4B\uD835\uDE61\uD835\uDE5A\uD835\uDE56\uD835\uDE68\uD835\uDE5A \uD835\uDE68\uD835\uDE5A\uD835\uDE61\uD835\uDE5A\uD835\uDE58\uD835\uDE69 \uD835\uDE56 \uD835\uDE58\uD835\uDE61\uD835\uDE6A\uD835\uDE57 \uD835\uDE69\uD835\uDE64 \uD835\uDE68\uD835\uDE5A\uD835\uDE5A \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE65\uD835\uDE61\uD835\uDE56\uD835\uDE6E\uD835\uDE5A\uD835\uDE67\uD835\uDE68:");
                        int choice;
                        while (true) {
                            try {
                                System.out.print("\nSelect a club: ");
                                choice = sc.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("\uD835\uDE44\uD835\uDE63\uD835\uDE6B\uD835\uDE56\uD835\uDE61\uD835\uDE5E\uD835\uDE59 \uD835\uDE63\uD835\uDE6A\uD835\uDE62\uD835\uDE57\uD835\uDE5A\uD835\uDE67.");
                                sc.next();
                            }
                        }
                        gameCore.getMoreClubPlayers(choice);
                }
            } else {
                System.out.println("\n\n\uD835\uDE54\uD835\uDE64\uD835\uDE6A'\uD835\uDE67\uD835\uDE5A \uD835\uDE5B\uD835\uDE5E\uD835\uDE67\uD835\uDE5A\uD835\uDE59 \uD835\uDE57\uD835\uDE5A\uD835\uDE58\uD835\uDE56\uD835\uDE6A\uD835\uDE68\uD835\uDE5A 3 \uD835\uDE58\uD835\uDE64\uD835\uDE63\uD835\uDE68\uD835\uDE5A\uD835\uDE58\uD835\uDE6A\uD835\uDE69\uD835\uDE5E\uD835\uDE6B\uD835\uDE5A \uD835\uDE61\uD835\uDE64\uD835\uDE68\uD835\uDE5A\uD835\uDE68. \uD835\uDE4B\uD835\uDE61\uD835\uDE5A\uD835\uDE56\uD835\uDE68\uD835\uDE5A \uD835\uDE68\uD835\uDE5A\uD835\uDE61\uD835\uDE5A\uD835\uDE58\uD835\uDE69 \uD835\uDE64\uD835\uDE69\uD835\uDE5D\uD835\uDE5A\uD835\uDE67 \uD835\uDE58\uD835\uDE61\uD835\uDE6A\uD835\uDE57.");
                System.out.println("\uD835\uDFED - \uD835\uDDE6\uD835\uDDF2\uD835\uDDF9\uD835\uDDF2\uD835\uDDF0\uD835\uDE01 \uD835\uDDEE\uD835\uDDFB\uD835\uDDFC\uD835\uDE01\uD835\uDDF5\uD835\uDDF2\uD835\uDDFF \uD835\uDDF0\uD835\uDDF9\uD835\uDE02\uD835\uDDEF");
                System.out.println("\uD835\uDE4D\uD835\uDE5A\uD835\uDE69\uD835\uDE5E\uD835\uDE67\uD835\uDE5A \uD835\uDE5B\uD835\uDE67\uD835\uDE64\uD835\uDE62 \uD835\uDE5B\uD835\uDE64\uD835\uDE64\uD835\uDE69\uD835\uDE57\uD835\uDE56\uD835\uDE61\uD835\uDE61");
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
