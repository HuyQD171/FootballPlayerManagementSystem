package repository;

import model.MatchRecord;
import model.PerformanceRecord;
import model.Player;
import java.util.ArrayList;
import java.util.List;
import storage.FileUtil;

public class PerformanceRepository {

    private static final String FILE_NAME = "performances.csv";

    public List<PerformanceRecord> load(List<Player> players, List<MatchRecord> matches) {
        List<PerformanceRecord> records = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_NAME)) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length < 8) {
                continue;
            }

            Player player = findPlayerById(players, parts[1]);
            MatchRecord match = findMatchById(matches, parts[2]);
            if (player == null || match == null) {
                continue;
            }

            PerformanceRecord record = new PerformanceRecord(
                    parts[0],
                    player,
                    match,
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4]),
                    Integer.parseInt(parts[5]),
                    Integer.parseInt(parts[6]),
                    Integer.parseInt(parts[7]));

            records.add(record);
            match.addPerformanceRecord(record);
            player.getPerformanceRecords().add(record);
        }

        return records;
    }

    public void save(List<PerformanceRecord> records) {
        List<String> lines = new ArrayList<>();

        for (PerformanceRecord record : records) {
            lines.add(FileUtil.clean(record.getPerformanceId()) + "|"
                    + FileUtil.clean(record.getPlayer().getPlayerId()) + "|"
                    + FileUtil.clean(record.getMatchRecord().getMatchId()) + "|"
                    + record.getGoals() + "|"
                    + record.getAssists() + "|"
                    + record.getYellowCards() + "|"
                    + record.getRedCards() + "|"
                    + record.getMinutesPlayed());
        }

        FileUtil.writeLines(FILE_NAME, lines);
    }

    private Player findPlayerById(List<Player> players, String playerId) {
        for (Player player : players) {
            if (player.getPlayerId().equalsIgnoreCase(playerId)) {
                return player;
            }
        }
        return null;
    }

    private MatchRecord findMatchById(List<MatchRecord> matches, String matchId) {
        for (MatchRecord match : matches) {
            if (match.getMatchId().equalsIgnoreCase(matchId)) {
                return match;
            }
        }
        return null;
    }
}
