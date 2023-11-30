public class FootballPlayer {
    private String name;
    private Club club;
    private int cost;
    private int strength;
    private int goals;
    private int assists;

    public FootballPlayer(String name, int cost, int strength, Club club) {
        this.name = name;
        this.cost = cost;
        this.strength = strength;
        this.club = club;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Club getClub() {
        return club;
    }

    public int getStrength() {
        return strength;
    }

    public int getCost() {
        return cost;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }
}