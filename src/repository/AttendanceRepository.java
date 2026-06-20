package repository;

import model.AttendanceRecord;
import model.Player;
import model.TrainingSession;
import java.util.ArrayList;
import java.util.List;
import storage.FileUtil;

public class AttendanceRepository {

    private static final String FILE_NAME = "attendance.csv";

    public List<AttendanceRecord> load(List<Player> players, List<TrainingSession> sessions) {
        List<AttendanceRecord> records = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_NAME)) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length < 5) {
                continue;
            }

            Player player = findPlayerById(players, parts[1]);
            TrainingSession session = findSessionById(sessions, parts[2]);
            if (player == null || session == null) {
                continue;
            }

            AttendanceRecord record = new AttendanceRecord(
                    parts[0],
                    player,
                    session,
                    Boolean.parseBoolean(parts[3]),
                    parts[4]);

            records.add(record);
            session.addAttendanceRecord(record);
            player.getAttendanceRecords().add(record);
        }

        return records;
    }

    public void save(List<AttendanceRecord> records) {
        List<String> lines = new ArrayList<>();

        for (AttendanceRecord record : records) {
            lines.add(FileUtil.clean(record.getAttendanceId()) + "|"
                    + FileUtil.clean(record.getPlayer().getPlayerId()) + "|"
                    + FileUtil.clean(record.getTrainingSession().getSessionId()) + "|"
                    + record.isPresent() + "|"
                    + FileUtil.clean(record.getNote()));
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

    private TrainingSession findSessionById(List<TrainingSession> sessions, String sessionId) {
        for (TrainingSession session : sessions) {
            if (session.getSessionId().equalsIgnoreCase(sessionId)) {
                return session;
            }
        }
        return null;
    }
}
