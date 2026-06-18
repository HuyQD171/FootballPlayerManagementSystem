package manager;

import data.MatchRecord;
import data.PerformanceRecord;
import data.Player;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.InputUtil;

public class MatchManager {

    private java.util.Scanner sc;
    private List<MatchRecord> matchList;

    public MatchManager() {
        sc = InputUtil.SCANNER;
        matchList = new ArrayList<>();
    }

    public void createMatchRecord() {
        String matchId = InputUtil.readRequiredString(sc, "Enter Match ID: ");

        if (findMatchById(matchId) != null) {
            System.out.println("Match ID already exists.");
            return;
        }

        LocalDate matchDate = InputUtil.readLocalDate(sc,
                "Enter Match Date (yyyy-MM-dd): ");

        String opponentTeam = InputUtil.readRequiredString(sc,
                "Enter Opponent Team: ");

        String matchType = InputUtil.readOption(sc,
                "Enter Match Type (Friendly/League/Cup): ",
                "Friendly", "League", "Cup");

        try {
            MatchRecord match = new MatchRecord(matchId, matchDate,
                    opponentTeam, matchType);
            matchList.add(match);
            System.out.println("Match record created successfully!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addPerformanceRecord(PlayerManager playerManager) {
        if (playerManager == null) {
            System.out.println("Player manager not found.");
            return;
        }

        String matchId = InputUtil.readRequiredString(sc, "Enter Match ID: ");
        MatchRecord match = findMatchById(matchId);

        if (match == null) {
            System.out.println("Match record not found.");
            return;
        }

        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID: ");
        Player player = playerManager.searchPlayerById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }
         if (player.getStatus() != null && "Injured".equalsIgnoreCase(player.getStatus().trim())) {
            System.out.println("Error: Player " + player.getFullName() + " is currently injured and cannot join the match!");
            return;
        }

        String performanceId = InputUtil.readRequiredString(sc,
                "Enter Performance ID: ");

        if (findPerformanceById(performanceId) != null) {
            System.out.println("Performance ID already exists.");
            return;
        }

        int goals = InputUtil.readIntMin(sc, "Enter Goals: ", 0);

        int assists = InputUtil.readIntMin(sc, "Enter Assists: ", 0);

        int yellowCards = InputUtil.readIntMin(sc, "Enter Yellow Cards: ", 0);

        int redCards = InputUtil.readIntMin(sc, "Enter Red Cards: ", 0);

        int minutesPlayed = InputUtil.readIntInRange(sc,
                "Enter Minutes Played: ", 0, 120);

        try {
            PerformanceRecord performance = new PerformanceRecord(
                    performanceId,
                    player,
                    match,
                    goals,
                    assists,
                    yellowCards,
                    redCards,
                    minutesPlayed);

            match.addPerformanceRecord(performance);
            player.getPerformanceRecords().add(performance);
            System.out.println("Performance record added successfully!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayMatchRecords() {

        System.out.println("\n===== MATCH RECORDS =====");

        if (matchList.isEmpty()) {
            System.out.println("No match records available.");
            return;
        }

        System.out.printf("%-5s | %-12s | %-20s | %-8s%n",
                "ID", "Date", "Opponent", "Type");

        for (MatchRecord match : matchList) {
            match.displayInfo();
        }
    }

    public void displayPerformanceRecords() {
        System.out.println("\n===== PERFORMANCE RECORDS =====");

        if (matchList.isEmpty()) {
            System.out.println("No match records available.");
            return;
        }

        boolean hasPerformance = false;
        System.out.printf("%-5s | %-20s | %5s | %7s | %6s | %4s | %7s | %6s%n",
                "ID", "Player", "Goals", "Assists", "Yellow", "Red", "Minutes", "Points");

        for (MatchRecord match : matchList) {
            for (PerformanceRecord performance : match.getPerformanceRecords()) {
                hasPerformance = true;
                performance.displayInfo();
            }
        }

        if (!hasPerformance) {
            System.out.println("No performance records available.");
        }
    }

    private MatchRecord findMatchById(String matchId) {
        if (InputUtil.isBlank(matchId)) {
            return null;
        }

        for (MatchRecord match : matchList) {
            if (matchId.equalsIgnoreCase(match.getMatchId())) {
                return match;
            }
        }
        return null;
    }

    private PerformanceRecord findPerformanceById(String performanceId) {
        if (InputUtil.isBlank(performanceId)) {
            return null;
        }

        for (MatchRecord match : matchList) {
            for (PerformanceRecord performance : match.getPerformanceRecords()) {
                if (performanceId.equalsIgnoreCase(performance.getPerformanceId())) {
                    return performance;
                }
            }
        }

        return null;
    }

}
