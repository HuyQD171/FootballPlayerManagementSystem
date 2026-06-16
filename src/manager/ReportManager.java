package manager;
import java.util.List;

import data.PerformanceRecord;
import data.Player;

public class ReportManager {

    public void generateSalaryReport(List<Player> players, int month, int year) {

        System.out.println("\n=++= PLAYER SALARY REPORT =++=");

        if (players == null || players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-25s | %-10s | %-18s | %-12s | %-15s%n",
                "Player ID", "Name", "Type", "Performance Points", "Bonus", "Monthly Salary");
        System.out.println("-----------------------------------------------------------------------------------------------");

        boolean hasValidPlayer = false;

        for (Player p : players) {
            if (p != null) {
                hasValidPlayer = true;
                int totalPoints = 0;

                if (p.getPerformanceRecords() != null) {
                    for (PerformanceRecord pr : p.getPerformanceRecords()) {
                        if (pr != null) {
                            if(pr.getMonth()==month && pr.getYear()==year) totalPoints += pr.calculatePerformancePoints();
                        }
                    }
                }

                double bonus = p.calculateBonus(totalPoints);
                double salary = p.calculateMonthlySalary(bonus);

                System.out.printf("%-10s | %-25s | %-10s | %-18d | %-12.2f | %-15.2f%n",
                        p.getPlayerId(), p.getFullName(), p.getPlayerType(),
                        totalPoints, bonus, salary);
            }
        }

        if (!hasValidPlayer) {
            System.out.println("No valid player records.");
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    public void generateTopScorerReport(List<Player> players) {

        System.out.println("\n=++= TOP SCORER REPORT =++=");

        if (players == null || players.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        Player topPlayer = null;
        int maxGoals = -1;

        for (Player p : players) {
            if (p != null) {
                int goals = 0;

                if (p.getPerformanceRecords() != null) {
                    for (PerformanceRecord pr : p.getPerformanceRecords()) {
                        if (pr != null) {
                            goals += pr.getGoals();
                        }
                    }
                }

                if (goals > maxGoals) {
                    maxGoals = goals;
                    topPlayer = p;
                }
            }
        }

        if (topPlayer != null) {
            System.out.println("Player: " + topPlayer.getFullName());
            System.out.println("Goals : " + maxGoals);
        } else {
            System.out.println("No valid player records.");
        }
    }
}
