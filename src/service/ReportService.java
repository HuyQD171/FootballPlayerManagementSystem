package service;

import model.PerformanceRecord;
import model.Player;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    public List<SalaryReportRow> generateSalaryReport(List<Player> players) {
        List<SalaryReportRow> rows = new ArrayList<>();

        if (players == null) {
            return rows;
        }

        for (Player player : players) {
            if (player == null) {
                continue;
            }

            int totalPoints = 0;
            if (player.getPerformanceRecords() != null) {
                for (PerformanceRecord record : player.getPerformanceRecords()) {
                    if (record != null) {
                        totalPoints += record.calculatePerformancePoints();
                    }
                }
            }

            double bonus = player.calculateBonus(totalPoints);
            double salary = player.calculateMonthlySalary(bonus);
            rows.add(new SalaryReportRow(player, totalPoints, bonus, salary));
        }

        return rows;
    }

    public TopScorerReport generateTopScorerReport(List<Player> players) {
        Player topPlayer = null;
        int maxGoals = -1;

        if (players != null) {
            for (Player player : players) {
                if (player == null) {
                    continue;
                }

                int goals = 0;
                if (player.getPerformanceRecords() != null) {
                    for (PerformanceRecord record : player.getPerformanceRecords()) {
                        if (record != null) {
                            goals += record.getGoals();
                        }
                    }
                }

                if (goals > maxGoals) {
                    maxGoals = goals;
                    topPlayer = player;
                }
            }
        }

        return new TopScorerReport(topPlayer, maxGoals < 0 ? 0 : maxGoals);
    }

    public static class SalaryReportRow {

        private final Player player;
        private final int totalPoints;
        private final double bonus;
        private final double monthlySalary;

        public SalaryReportRow(Player player, int totalPoints, double bonus,
                double monthlySalary) {
            this.player = player;
            this.totalPoints = totalPoints;
            this.bonus = bonus;
            this.monthlySalary = monthlySalary;
        }

        public Player getPlayer() {
            return player;
        }

        public int getTotalPoints() {
            return totalPoints;
        }

        public double getBonus() {
            return bonus;
        }

        public double getMonthlySalary() {
            return monthlySalary;
        }
    }

    public static class TopScorerReport {

        private final Player player;
        private final int goals;

        public TopScorerReport(Player player, int goals) {
            this.player = player;
            this.goals = goals;
        }

        public Player getPlayer() {
            return player;
        }

        public int getGoals() {
            return goals;
        }
    }
}
