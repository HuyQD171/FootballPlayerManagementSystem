package data;

public class StarPlayer extends Player {

    public StarPlayer() {
        super();
    }

    public StarPlayer(String playerId, String playerType,
            String fullName, int age,
            String nationality, String position,
            int shirtNumber, double baseSalary,
            String status) {
        super(playerId, playerType, fullName, age, nationality, position, shirtNumber, baseSalary, status);
    }

    @Override
    public double calculateBonus(int points) {
        return points * 500000;
    }
}
