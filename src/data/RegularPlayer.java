package data;

public class RegularPlayer extends Player {

    public RegularPlayer() {
        super();
    }

    public RegularPlayer(String playerId, String playerType,
            String fullName, int age,
            String nationality, String position,
            int shirtNumber, double baseSalary,
            String status) {
        super(playerId, playerType, fullName, age, nationality, position, shirtNumber, baseSalary, status);
    }

    @Override
    public double calculateBonus(int points) {
        return 0;
    }
}
