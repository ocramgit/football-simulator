import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GameCore {
    private ArrayList<Club> portugueseClubList;
    private League portugueseLeague;
    private Player player;
    private Scanner sc;

    public GameCore() {
        portugueseClubList = new ArrayList<>();
        addClubsToTheportugueseClubList();
        player = new Player();
        setPlayerClub();
        sc = new Scanner(System.in);
        setAverageClub();
        addPlayerToClub();
        portugueseLeague = new PortugueseLeague(portugueseClubList);
        getClubPlayers(player.getClub());
        new Market();
    }

    private void addPlayerToClub() {
        getportugueseClubListTeams();
        System.out.print("Select the club: ");
        int clubIndex = sc.nextInt();
        player.setClub(portugueseClubList.get(clubIndex - 1));
        portugueseClubList.get(clubIndex - 1).setPlayer(player);
        portugueseClubList.get(clubIndex-1).setName("\u001b[47;1m"+portugueseClubList.get(clubIndex-1).getName()+"\u001B[0m");
        System.out.println("Welcome! Let's start!");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void simulate() {
        player.getClub().setName("\u001b[47;1m"+player.getClub().getName()+"\u001b[0m");
        player.getClub().play();
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


    public void setPlayerClub() {
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Adán", 1000000, 86, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Gonçalo Inácio", 30000000, 87, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Ousmane Diomande", 25000000, 870, portugueseClubList.get(0)));
        portugueseClubList.get(0).getSquad().add(new FootballPlayer("Sebastián Coates", 5000000, 850, portugueseClubList.get(0)));
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

        int count = 0;

        for (int i = 3; i <= 17; i++) {
            for (int j = 0; j <= 10; j++) {
                portugueseClubList.get(i).getSquad().add(new FootballPlayer("PlayerPortuguese" + count++, getRandomCost(), getRandomStr(), portugueseClubList.get(i)));
            }
        }
    }

    public int getRandomCost() {
        return (int) (Math.random() * 1000000);
    }

    public int getRandomStr() {
        return (int) (Math.random() * (85 - 50) + 50);
    }

    public void getClubPlayers(Club club) {
        for (FootballPlayer footballPlayer : club.getSquad()) {
            System.out.println("Name: " + footballPlayer.getName() + " | Cost: " + footballPlayer.getCost() + "€ | Strength: " + footballPlayer.getStrength());
        }
    }

    public void getportugueseClubListTeams() {

        int count = 1;

        for (Club club : portugueseClubList) {
            System.out.println(count++ + " | " + club.getName() + " | Strength: " + club.getAverage_strength());
        }
    }

    public Player getPlayer() {
        return player;
    }

    private void addClubsToTheportugueseClubList() {
        portugueseClubList.add(new Club("Sporting CP", 1000000));
        portugueseClubList.add(new Club("SL Benfica", 2000000));
        portugueseClubList.add(new Club("FC Porto", 1500000));
        portugueseClubList.add(new Club("Estrela Amadora", 540000));
        portugueseClubList.add(new Club("SC Braga", 900000));
        portugueseClubList.add(new Club("Moreirense", 200000));
        portugueseClubList.add(new Club("Vitória SC", 400000));
        portugueseClubList.add(new Club("FC Famalicão", 600000));
        portugueseClubList.add(new Club("SC Farense", 300000));
        portugueseClubList.add(new Club("Boavista FC", 500000));
        portugueseClubList.add(new Club("Portimonense", 250000));
        portugueseClubList.add(new Club("Gil Vicente FC", 460000));
        portugueseClubList.add(new Club("Estoril Praia", 160000));
        portugueseClubList.add(new Club("FC Vizela", 380000));
        portugueseClubList.add(new Club("Casa Pia AC", 170000));
        portugueseClubList.add(new Club("Rio Ave FC", 500000));
        portugueseClubList.add(new Club("GD Chaves", 310000));
        portugueseClubList.add(new Club("FC Arouca", 100000));
    }

    public void getMoreClubPlayers(int choice) {
        for (FootballPlayer footballPlayer : portugueseClubList.get(choice-1).getSquad()) {
            System.out.println("Name: " + footballPlayer.getName() + " | Cost: " + footballPlayer.getCost() + "€ | Strength: " + footballPlayer.getStrength());
        }
    }
}