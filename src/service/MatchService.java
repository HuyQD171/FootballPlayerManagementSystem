package service;

import model.MatchRecord;
import model.PerformanceRecord;
import model.Player;
import java.time.LocalDate;
import java.util.List;
import storage.DataStore;
import util.InputUtil;

public class MatchService {

    private final DataStore dataStore;
    private final PlayerService playerService;

    public MatchService(DataStore dataStore, PlayerService playerService) {
        this.dataStore = dataStore;
        this.playerService = playerService;
    }

    public String createMatchRecord(String matchId, LocalDate matchDate,
            String opponentTeam, String matchType) {
        if (findById(matchId) != null) {
            return "Match ID already exists.";
        }

        try {
            MatchRecord match = new MatchRecord(matchId, matchDate,
                    opponentTeam, matchType);
            dataStore.getMatches().add(match);
            dataStore.saveMatches();
            return "Match record created successfully!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public String addPerformanceRecord(String performanceId, String playerId,
            String matchId, int goals, int assists, int yellowCards,
            int redCards, int minutesPlayed) {
        if (findPerformanceById(performanceId) != null) {
            return "Performance ID already exists.";
        }

        MatchRecord match = findById(matchId);
        if (match == null) {
            return "Match record not found.";
        }

        Player player = playerService.findById(playerId);
        if (player == null) {
            return "Player not found.";
        }

        if (player.getStatus() != null
                && "Injured".equalsIgnoreCase(player.getStatus().trim())) {
            return "Error: Player " + player.getFullName()
                    + " is currently injured and cannot join the match!";
        }

        try {
            PerformanceRecord performance = new PerformanceRecord(
                    performanceId, player, match, goals, assists,
                    yellowCards, redCards, minutesPlayed);
            dataStore.getPerformanceRecords().add(performance);
            match.addPerformanceRecord(performance);
            player.getPerformanceRecords().add(performance);
            dataStore.savePerformanceRecords();
            return "Performance record added successfully!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public MatchRecord findById(String matchId) {
        if (InputUtil.isBlank(matchId)) {
            return null;
        }

        for (MatchRecord match : dataStore.getMatches()) {
            if (match.getMatchId().equalsIgnoreCase(matchId)) {
                return match;
            }
        }
        return null;
    }

    public PerformanceRecord findPerformanceById(String performanceId) {
        if (InputUtil.isBlank(performanceId)) {
            return null;
        }

        for (PerformanceRecord record : dataStore.getPerformanceRecords()) {
            if (record.getPerformanceId().equalsIgnoreCase(performanceId)) {
                return record;
            }
        }
        return null;
    }

    public List<MatchRecord> getMatches() {
        return dataStore.getMatches();
    }

    public List<PerformanceRecord> getPerformanceRecords() {
        return dataStore.getPerformanceRecords();
    }
}
