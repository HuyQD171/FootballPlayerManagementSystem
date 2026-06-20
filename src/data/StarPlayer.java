package data;

public class StarPlayer extends Player {
    private static double bonusMultiplier = 500000;

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
    public static double getBonusMultiplier() {
        return bonusMultiplier;
    }

    public static void setBonusMultiplier(double multiplier) {
        if (multiplier > 0) {
            bonusMultiplier = multiplier;
            System.out.println("Updated bonus multiplier to: " + multiplier);
        } else {
            System.out.println("Bonus multiplier must be greater than 0!");
        }
    }

    @Override
    public double calculateBonus(int points) {
        return points * bonusMultiplier;
    }
}
