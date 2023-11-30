import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameCore {
    private ArrayList<Club> portugueseClubList;
    private League portugueseLeague;
    private Player player;
    private Scanner sc;
    private int classification = 0;

    public GameCore() {
        rules();
        portugueseClubList = new ArrayList<>();
        addClubsToThePortugueseClubList();
        player = new Player();
        setPlayerClub();
        sc = new Scanner(System.in);
        setAverageClub();
        addPlayerToClub();
        portugueseLeague = new PortugueseLeague(portugueseClubList);
        new Market();
        checkClassification();
    }

    private boolean isValid = false;

    public void addPlayerToClub() {
        getPortugueseClubListTeams();

        int clubIndex;
        while (true) {
            try {
                System.out.print("\uD835\uDC7A\uD835\uDC86\uD835\uDC8D\uD835\uDC86\uD835\uDC84\uD835\uDC95 \uD835\uDC95\uD835\uDC89\uD835\uDC86 \uD835\uDC84\uD835\uDC8D\uD835\uDC96\uD835\uDC83: ");
                clubIndex = sc.nextInt() - 1;

                if (clubIndex >= 0 && clubIndex < portugueseClubList.size()) {
                    break;
                } else {
                    System.out.println("\uD835\uDE44\uD835\uDE63\uD835\uDE6B\uD835\uDE56\uD835\uDE61\uD835\uDE5E\uD835\uDE59 \uD835\uDE58\uD835\uDE61\uD835\uDE6A\uD835\uDE57. \uD835\uDE4E\uD835\uDE5A\uD835\uDE61\uD835\uDE5A\uD835\uDE58\uD835\uDE69 \uD835\uDE56\uD835\uDE63\uD835\uDE64\uD835\uDE69\uD835\uDE5D\uD835\uDE5A\uD835\uDE67.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\uD835\uDE44\uD835\uDE63\uD835\uDE6B\uD835\uDE56\uD835\uDE61\uD835\uDE5E\uD835\uDE59 \uD835\uDE63\uD835\uDE6A\uD835\uDE62\uD835\uDE57\uD835\uDE5A\uD835\uDE67.");
                sc.next();
            }
        }

        if (player.getClub() != null && player.getClub().equals(portugueseClubList.get(clubIndex))) {
            System.out.println("\n\uD835\uDE54\uD835\uDE64\uD835\uDE6A \uD835\uDE58\uD835\uDE56\uD835\uDE63'\uD835\uDE69 \uD835\uDE68\uD835\uDE5A\uD835\uDE61\uD835\uDE5A\uD835\uDE58\uD835\uDE69 \uD835\uDE69\uD835\uDE5D\uD835\uDE5A \uD835\uDE68\uD835\uDE56\uD835\uDE62\uD835\uDE5A \uD835\uDE58\uD835\uDE61\uD835\uDE6A\uD835\uDE57. \uD835\uDE54\uD835\uDE64\uD835\uDE6A \uD835\uDE59\uD835\uDE64\uD835\uDE63'\uD835\uDE69 \uD835\uDE5D\uD835\uDE56\uD835\uDE6B\uD835\uDE5A \uD835\uDE56 \uD835\uDE5C\uD835\uDE64\uD835\uDE64\uD835\uDE59 \uD835\uDE67\uD835\uDE5A\uD835\uDE61\uD835\uDE56\uD835\uDE69\uD835\uDE5E\uD835\uDE64\uD835\uDE63\uD835\uDE68\uD835\uDE5D\uD835\uDE5E\uD835\uDE65.\n");
            addPlayerToClub();
        } else {
            player.setClub(portugueseClubList.get(clubIndex));
            portugueseClubList.get(clubIndex).setPlayer(player);
            player.setFired(false);
            portugueseClubList.get(clubIndex).setName("\u001b[0m\u001b[4m" + portugueseClubList.get(clubIndex).getName() + "\u001B[0m");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }



    public void rules() {
        System.out.println("\n\u001b[37mThis is a fantasy game inspired by the system of a football match season.\nChoose a team, simulate each round, buy or sell players, see the standings, \nclimb levels, see the players in each team and most importantly: have fun.\u001b[0m\n");
    }

    public void simulate() {
        player.getClub().setName("\u001b[47;1m" + player.getClub().getName() + "\u001b[0m");
        player.getClub().play();
        setAverageClub();
        checkClassification();
    }

    public void setAverageClub() {
        for (Club club : portugueseClubList) {
            int total = 0;
            for (FootballPlayer footballPlayer : club.getSquad()) {
                total += footballPlayer.getStrength();
            }

            int squadSize = club.getSquad().size();
            if (squadSize > 0) {
                club.setAverage_strength(total / squadSize);
            } else {
                club.setAverage_strength(0);
            }
        }
    }


    public void seeClassification() {
        portugueseClubList.sort(Comparator.comparingInt(Club::getPoints)
                .thenComparingInt(Club::getWins)
                .reversed()
                .thenComparingInt(Club::getLosses)
                .thenComparing(Club::getDraws));

        System.out.println("Classification: ");
        int position = 1;

        for (Club club : portugueseClubList) {
            int goalDifference = club.getGoalsScored() - club.getGoalsConceded();
            String output = " | " + club.getName() +
                    " - Points: " + club.getPoints() +
                    ", Wins: " + club.getWins() +
                    ", Losses: " + club.getLosses() +
                    ", Draws: " + club.getDraws() +
                    ", Goal Difference: " + goalDifference +
                    " | Matches: " + player.getClub().getMatches();

            String colorCode = "";
            if (position == 1 || position == 2) {
                colorCode = "\u001B[44;1m";
            } else if (position == 3) {
                colorCode = "\u001B[42;1m";
            } else if (position == 17 || position == 18) {
                colorCode = "\u001B[41;1m";
            }

            System.out.println(colorCode + position + "º\u001B[0m" + output);

            position++;
        }
    }

    private void checkClassification() {
        portugueseClubList.sort(Comparator.comparingInt(Club::getPoints)
                .thenComparingInt(Club::getWins)
                .reversed()
                .thenComparingInt(Club::getLosses)
                .thenComparing(Club::getDraws));

        int position = 1;

        for (Club club : portugueseClubList) {
            if (player.getClub().equals(club)) {
                classification = position;
            }
            position++;
        }
    }

    public void setPlayerClub() {
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Adán", 1000000, 86, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Gonçalo Inácio", 300000000, 87, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Ousmane Diomande", 250000000, 87, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Sebastián Coates", 5000000, 85, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Nuno Santos", 14000000, 88, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Matheus Reis", 8000000, 88, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Iván Fresneda", 15000000, 89, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Morten Hjulmand", 24000000, 92, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Hidemasa Morita", 12000000, 85, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Pedro Gonçalves", 32000000, 88, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Viktor Gyökeres", 32000000, 92, portugueseClubList.get(0)));

        portugueseClubList.get(1).getSquad().add(new FootballPlayer("Anatoliy Trubin", 22000000, 91, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("António Silva", 45000000, 89, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("Nicolás Otamendi", 13000000, 92, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("Morato", 13000000, 88, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("David Jurásek", 8000000, 86, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("Florentino", 20000000, 88, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("João Neves", 20000000, 88, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("João Mário", 14000000, 87, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("Di Maria", 8000000, 91, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("Casper Tengstedt", 6000000, 88, portugueseClubList.get(1)));
        portugueseClubList.get(1).getSquad().add(new FootballPlayer("David Neres", 25000000, 94, portugueseClubList.get(1)));

        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Diogo Costa", 45000000, 93, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("João Mário", 16000000, 87, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Pepe", 1000000, 90, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("David Carmo", 14000000, 88, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("André Franco", 4000000, 85, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Eustáquio", 12000000, 87, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Pepê", 25000000, 86, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Varela", 9000000, 83, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Taremi", 18000000, 93, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Evanilson", 22000000, 94, portugueseClubList.get(2)));
        portugueseClubList.get(2).getSquad().add(new FootballPlayer("Sanusi", 25000000, 86, portugueseClubList.get(2)));

        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Ruben Ribeiro", 3000000, 77, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("João Afonso", 2000000, 76, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Lucas Mineiro", 2500000, 75, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Ygor Nogueira", 1800000, 74, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Kanya Fujimoto", 3500000, 78, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("João Pedro", 1600000, 73, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Lourency", 2800000, 76, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Vitor Carvalho", 2000000, 75, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Pedro Marques", 2300000, 77, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Fábio Martins", 4500000, 79, portugueseClubList.get(11)));
        portugueseClubList.get(11).getSquad().add(new FootballPlayer("Renan Oliveira", 1500000, 72, portugueseClubList.get(11)));

        portugueseClubList.get(12).getSquad().add(new FootballPlayer("André Vidigal", 1200000, 72, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Joãozinho", 800000, 73, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Hugo Gomes", 1000000, 70, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Luís Gustavo", 1500000, 75, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("André Franco", 900000, 71, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Bruno Lourenço", 1300000, 74, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("André Claro", 2000000, 76, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Dadashov", 3000000, 78, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Lucas Fernandes", 1800000, 77, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Hugo Basto", 2200000, 75, portugueseClubList.get(12)));
        portugueseClubList.get(12).getSquad().add(new FootballPlayer("Gonçalo Santos", 1000000, 70, portugueseClubList.get(12)));

        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Hugo Basto", 1500000, 74, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Moussa Bana", 2000000, 75, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Edu Machado", 1800000, 76, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("André Soares", 1000000, 72, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Matheus Costa", 1200000, 73, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Fábio Fortes", 1300000, 71, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Samuel Lino", 1600000, 78, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Kiki Kouyaté", 2200000, 77, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Marcelo Lopes", 1900000, 75, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Diogo Ribeiro", 1200000, 70, portugueseClubList.get(13)));
        portugueseClubList.get(13).getSquad().add(new FootballPlayer("Koffi Kouao", 2500000, 79, portugueseClubList.get(13)));

        portugueseClubList.get(14).getSquad().add(new FootballPlayer("David Rosa", 1000000, 70, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Vasco Rocha", 1200000, 71, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Pedro Machado", 1800000, 75, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Rafael Vieira", 900000, 73, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Tomás Inácio", 1500000, 72, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Wilson Kenidy", 2000000, 74, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Diogo Rosado", 1300000, 76, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Leandro Silva", 1600000, 78, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("João Vieira", 1400000, 77, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Tomás Silva", 1100000, 79, portugueseClubList.get(14)));
        portugueseClubList.get(14).getSquad().add(new FootballPlayer("Tiago Morgado", 1700000, 76, portugueseClubList.get(14)));

        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Fábio Coentrão", 5000000, 82, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Gelson Dala", 3000000, 80, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Ivo Pinto", 2200000, 78, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Filipe Augusto", 2000000, 76, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Píris", 1200000, 74, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Carlos Mané", 1800000, 72, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Léo Vieira", 1500000, 71, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Zé Pedro", 1600000, 73, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Anderson Luís", 1400000, 75, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Juninho", 1300000, 79, portugueseClubList.get(15)));
        portugueseClubList.get(15).getSquad().add(new FootballPlayer("Wesley", 1100000, 77, portugueseClubList.get(15)));

        portugueseClubList.get(16).getSquad().add(new FootballPlayer("Tiago Pereira", 1000000, 72, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("Erick Moreno", 1500000, 73, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("André Gomes", 1200000, 74, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("Wellington Carvalho", 800000, 75, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("Abdoulaye Diallo", 1800000, 76, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("João Correia", 1300000, 77, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("Hugo Basto", 2000000, 78, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("Nuno Rodrigues", 1000000, 79, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("Hélder Guedes", 1100000, 80, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("João Teixeira", 1400000, 81, portugueseClubList.get(16)));
        portugueseClubList.get(16).getSquad().add(new FootballPlayer("João Lopes", 1600000, 82, portugueseClubList.get(16)));

        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Tiago Silva", 1000000, 76, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Paulo Fonseca", 1500000, 78, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Marco Silva", 1200000, 80, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Ricardo Silva", 800000, 81, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Martim Borges", 1800000, 80, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Alexandre Henriques", 1300000, 71, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("José Luís", 2000000, 73, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Nuno Rodrigues", 1000000, 71, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("Hélder Guedes", 1100000, 80, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("João Teixeira", 1400000, 81, portugueseClubList.get(17)));
        portugueseClubList.get(17).getSquad().add(new FootballPlayer("João Lopes", 1600000, 82, portugueseClubList.get(17)));

        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Wellington Silva", 2000000, 78, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Galeno", 2500000, 80, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Ricardo Mangas", 1800000, 76, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Bruno Moreira", 3200000, 79, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("César Peixoto", 1500000, 75, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("João Mendes", 900000, 74, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("André Geraldes", 1100000, 77, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Cristiano", 1000000, 73, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Gégé", 1300000, 72, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Sérgio Oliveira", 3000000, 81, portugueseClubList.get(17)));
        portugueseClubList.get(3).getSquad().add(new FootballPlayer("Alex Soares", 1700000, 79, portugueseClubList.get(17)));

        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Rodrigo Pinho", 4000000, 88, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Jean Irmer", 2800000, 83, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("André Carvalhas", 1500000, 81, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Lucas Áfrico", 2000000, 85, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Diego Moreno", 1800000, 90, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Fábio China", 1600000, 82, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Éber Bessa", 2200000, 82, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Zainadine Júnior", 2500000, 80, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Amir Abedzadeh", 3000000, 84, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("François-Xavier Fumu Tamuzo", 1700000, 78, portugueseClubList.get(4)));
        portugueseClubList.get(4).getSquad().add(new FootballPlayer("Júlio César", 1200000, 86, portugueseClubList.get(4)));

        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Rashid", 2000000, 79, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Zaidu Sanusi", 2200000, 80, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Pablo Teixeira", 1800000, 78, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Lincoln", 2500000, 82, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Lucas Marques", 1500000, 76, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Crysan", 2700000, 81, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Hidemasa Morita", 2000000, 77, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Carlos Júnior", 3000000, 82, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Mansur", 1800000, 75, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("Vladimir Bajić", 2200000, 80, portugueseClubList.get(5)));
        portugueseClubList.get(5).getSquad().add(new FootballPlayer("João Afonso", 1700000, 77, portugueseClubList.get(5)));

        portugueseClubList.get(6).getSquad().add(new FootballPlayer("António Xavier", 1500000, 76, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("João Mendes", 1200000, 74, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Beunardeau", 1800000, 75, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Yohan Tavares", 1600000, 73, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Pedro Augusto", 2000000, 78, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Murilo", 2200000, 80, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Tomislav Štrkalj", 1400000, 72, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Pedro Augusto", 2500000, 79, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Jaquité", 2000000, 77, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Jhon Murillo", 3000000, 82, portugueseClubList.get(6)));
        portugueseClubList.get(6).getSquad().add(new FootballPlayer("Tiago Almeida", 1300000, 71, portugueseClubList.get(6)));

        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Luiz Carlos", 1800000, 75, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Stephen Eustáquio", 2200000, 77, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Jorge Silva", 1500000, 76, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Yan Dhanda", 2500000, 80, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Maracás", 2000000, 78, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("João Pedro", 1600000, 74, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Hélder Ferreira", 1300000, 73, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Jorge Silva", 1700000, 76, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Fábio Cardoso", 1900000, 79, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Walterson", 1400000, 72, portugueseClubList.get(7)));
        portugueseClubList.get(7).getSquad().add(new FootballPlayer("Luiz Phellype", 2800000, 81, portugueseClubList.get(7)));

        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Lucas Lima", 2500000, 76, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Vinícius Pereira", 2800000, 78, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Ricardo Quaresma", 4000000, 80, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Felipe Pires", 3200000, 77, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Rúben Vinagre", 3500000, 79, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Gustavo Assunção", 4500000, 82, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Carlos Miguel", 1800000, 75, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Jhonata Robert", 3000000, 78, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Anderson", 2200000, 76, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Ruben Vinagre", 4000000, 81, portugueseClubList.get(8)));
        portugueseClubList.get(8).getSquad().add(new FootballPlayer("Mateus", 1600000, 74, portugueseClubList.get(8)));

        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Beto", 2000000, 77, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("André Pinto", 1800000, 76, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Jonas Mendes", 1500000, 75, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Eduardo Furtado", 2200000, 78, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Hugo Seco", 2000000, 79, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Filipe Melo", 1600000, 74, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Amarildo", 2500000, 77, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Cláudio Falcão", 1800000, 76, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Brian Mansilla", 2000000, 78, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Licá", 2200000, 79, portugueseClubList.get(9)));
        portugueseClubList.get(9).getSquad().add(new FootballPlayer("Fabrício Isidoro", 1600000, 74, portugueseClubList.get(9)));

        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Léo Jardim", 3000000, 80, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Paulinho", 2800000, 78, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Nathan", 2200000, 76, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Javi García", 4000000, 82, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Yanis Hamache", 3500000, 81, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Gustavo Sauer", 2200000, 76, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Alberth Elis", 2500000, 77, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Germán Conti", 1800000, 75, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Reisinho", 2000000, 78, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Jesper Daland", 1600000, 74, portugueseClubList.get(10)));
        portugueseClubList.get(10).getSquad().add(new FootballPlayer("Mateus Pereira", 2000000, 76, portugueseClubList.get(10)));
    }

    public int getRandomCost() {
        return (int) (Math.random() * 1000000);
    }

    public int getRandomStr() {
        return (int) (Math.random() * (85 - 50) + 50);
    }

    public void getClubPlayers(Club club) {
        for (FootballPlayer footballPlayer : club.getSquad()) {
            System.out.println("Name: " + footballPlayer.getName() + " | Cost: " + footballPlayer.getCost() + "€ | Strength: " + footballPlayer.getStrength() + " | Goals: " + footballPlayer.getGoals() + " | Assists: " + footballPlayer.getAssists());
        }
    }

    public void getPortugueseClubListTeams() {
        int count = 1;

        for (Club club : portugueseClubList) {
            if (player != null && player.getClub() != null && player.getClub().equals(club)) {
                System.out.println("\u001b[1m\u001b[31m" + count++ + " | " + club.getName() + " | \uD835\uDE4E\uD835\uDE69\uD835\uDE67\uD835\uDE5A\uD835\uDE63\uD835\uDE5C\uD835\uDE69\uD835\uDE5D: " + club.getAverage_strength() + "\u001b[0m");
            } else {
                System.out.println(count++ + " | \u001b[33m" + club.getName() + "\u001b[0m | \uD835\uDE4E\uD835\uDE69\uD835\uDE67\uD835\uDE5A\uD835\uDE63\uD835\uDE5C\uD835\uDE69\uD835\uDE5D: " + club.getAverage_strength());
            }
        }
    }


    public Player getPlayer() {
        return player;
    }

    private void addClubsToThePortugueseClubList() {
        portugueseClubList.add(ClubFactory.create(ClubType.SPORTING_CP));
        portugueseClubList.add(ClubFactory.create(ClubType.SL_BENFICA));
        portugueseClubList.add(ClubFactory.create(ClubType.FC_PORTO));
        portugueseClubList.add(ClubFactory.create(ClubType.ESTRELA_AMADORA));
        portugueseClubList.add(ClubFactory.create(ClubType.SC_BRAGA));
        portugueseClubList.add(ClubFactory.create(ClubType.MOREIRENSE));
        portugueseClubList.add(ClubFactory.create(ClubType.VITORIA_SC));
        portugueseClubList.add(ClubFactory.create(ClubType.FC_FAMALICAO));
        portugueseClubList.add(ClubFactory.create(ClubType.SC_FARENSE));
        portugueseClubList.add(ClubFactory.create(ClubType.BOAVISTA_FC));
        portugueseClubList.add(ClubFactory.create(ClubType.PORTIMONENSE));
        portugueseClubList.add(ClubFactory.create(ClubType.GIL_VICENTE_FC));
        portugueseClubList.add(ClubFactory.create(ClubType.ESTORIL_PRAIA));
        portugueseClubList.add(ClubFactory.create(ClubType.FC_VIZELA));
        portugueseClubList.add(ClubFactory.create(ClubType.CASA_PIA_AC));
        portugueseClubList.add(ClubFactory.create(ClubType.RIO_AVE_FC));
        portugueseClubList.add(ClubFactory.create(ClubType.GD_CHAVES));
        portugueseClubList.add(ClubFactory.create(ClubType.FC_AROUCA));
    }

    public void getMoreClubPlayers(int choice) {
        if (choice < portugueseClubList.size()) {
            for (FootballPlayer footballPlayer : portugueseClubList.get(choice - 1).getSquad()) {
                System.out.println("Name: \u001b[33m" + footballPlayer.getName() + "\u001b[0m | \u001b[31m\uD835\uDE3E\uD835\uDE64\uD835\uDE68\uD835\uDE69:\u001b[0m " + footballPlayer.getCost() + "€ | \u001b[31m\uD835\uDE4E\uD835\uDE69\uD835\uDE67\uD835\uDE5A\uD835\uDE63\uD835\uDE5C\uD835\uDE69\uD835\uDE5D:\u001b[0m " + footballPlayer.getStrength() + " | \u001b[33m\uD835\uDE42\uD835\uDE64\uD835\uDE56\uD835\uDE61\uD835\uDE68:\u001b[0m " + footballPlayer.getGoals() + " | \u001b[33m\uD835\uDE3C\uD835\uDE68\uD835\uDE68\uD835\uDE5E\uD835\uDE68\uD835\uDE69\uD835\uDE68:\u001b[0m " + footballPlayer.getAssists());
            }
        } else {
            System.out.println("\uD835\uDC70\uD835\uDC8F\uD835\uDC97\uD835\uDC82\uD835\uDC8D\uD835\uDC8A\uD835\uDC85 \uD835\uDC84\uD835\uDC8D\uD835\uDC96\uD835\uDC83.");
        }
    }

    public int getClassification() {
        return classification;
    }
}