package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//aa
public class MatchRecord {

    private String matchId;
    private LocalDate matchDate;
    private String opponentTeam;
    private String matchType;
    private List<PerformanceRecord> performanceRecords;

    public MatchRecord() {
        performanceRecords = new ArrayList<>();
    }

    public MatchRecord(String matchId, LocalDate matchDate,
            String opponentTeam, String matchType) {
        setMatchId(matchId);
        setMatchDate(matchDate);
        setOpponentTeam(opponentTeam);
        setMatchType(matchType);
        performanceRecords = new ArrayList<>();
    }

    public void addPerformanceRecord(PerformanceRecord record) {
        if (record == null) {
            throw new RuntimeException("Performance record must not be null.");
        }
        performanceRecords.add(record);
    }

    public void displayInfo() {
        System.out.printf("%-5s | %-12s | %-20s | %-8s%n",
                matchId, matchDate, opponentTeam, matchType);
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        if (matchId == null || matchId.trim().isEmpty()) {
            throw new RuntimeException("Match ID must not be empty.");
        }
        this.matchId = matchId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        if (matchDate == null) {
            throw new RuntimeException("Match date must not be null.");
        }
        this.matchDate = matchDate;
    }

    public String getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(String opponentTeam) {
        if (opponentTeam == null || opponentTeam.trim().isEmpty()) {
            throw new RuntimeException("Opponent team must not be empty.");
        }
        this.opponentTeam = opponentTeam;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        if (matchType != null
                && (matchType.equalsIgnoreCase("Friendly")
                || matchType.equalsIgnoreCase("League")
                || matchType.equalsIgnoreCase("Cup"))) {
            this.matchType = matchType.trim();
        } else {
            throw new RuntimeException("Match type must be Friendly, League, or Cup.");
        }
    }

    public List<PerformanceRecord> getPerformanceRecords() {
        return performanceRecords;
    }
}
