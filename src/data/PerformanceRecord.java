package data;

public class PerformanceRecord {

    private String performanceId;
    private Player player;
    private MatchRecord matchRecord;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int minutesPlayed;

    public PerformanceRecord() {
    }

    public PerformanceRecord(String performanceId, Player player,
            MatchRecord matchRecord, int goals, int assists,
            int yellowCards, int redCards, int minutesPlayed) {
        setPerformanceId(performanceId);
        setPlayer(player);
        setMatchRecord(matchRecord);
        setGoals(goals);
        setAssists(assists);
        setYellowCards(yellowCards);
        setRedCards(redCards);
        setMinutesPlayed(minutesPlayed);
    }

    public int calculatePerformancePoints() {
        int points = goals * 5 + assists * 3 - yellowCards - redCards * 3;
        if (points < 0) {
            return 0;
        }
        return points;
    }

    public void displayInfo() {
        System.out.printf("%-5s | %-20s | %5d | %7d | %6d | %4d | %7d | %6d%n",
                performanceId, player.getFullName(), goals, assists,
                yellowCards, redCards, minutesPlayed, calculatePerformancePoints());
    }

    public String getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(String performanceId) {
        if (performanceId == null || performanceId.trim().isEmpty()) {
            throw new RuntimeException("Performance ID must not be empty.");
        }
        this.performanceId = performanceId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (player == null) {
            throw new RuntimeException("Player must not be null.");
        }
        this.player = player;
    }

    public MatchRecord getMatchRecord() {
        return matchRecord;
    }

    public void setMatchRecord(MatchRecord matchRecord) {
        if (matchRecord == null) {
            throw new RuntimeException("Match record must not be null.");
        }
        this.matchRecord = matchRecord;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        if (goals >= 0) {
            this.goals = goals;
        } else {
            throw new RuntimeException("Goals must not be negative.");
        }
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        if (assists >= 0) {
            this.assists = assists;
        } else {
            throw new RuntimeException("Assists must not be negative.");
        }
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        if (yellowCards >= 0) {
            this.yellowCards = yellowCards;
        } else {
            throw new RuntimeException("Yellow cards must not be negative.");
        }
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        if (redCards >= 0) {
            this.redCards = redCards;
        } else {
            throw new RuntimeException("Red cards must not be negative.");
        }
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        if (minutesPlayed >= 0 && minutesPlayed <= 120) {
            this.minutesPlayed = minutesPlayed;
        } else {
            throw new RuntimeException("Minutes played must be between 0 and 120.");
        }
    }
}
