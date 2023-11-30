import java.util.Scanner;

public class Player {
    private Club club;
    private boolean isFired = false;
    private Scanner sc = new Scanner(System.in);

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    public boolean isFired() {
        return isFired;
    }
}
