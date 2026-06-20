package service;

import model.AttendanceRecord;
import model.Player;
import model.TrainingSession;
import java.time.LocalDate;
import java.util.List;
import storage.DataStore;
import util.InputUtil;

public class TrainingService {

    private final DataStore dataStore;
    private final PlayerService playerService;

    public TrainingService(DataStore dataStore, PlayerService playerService) {
        this.dataStore = dataStore;
        this.playerService = playerService;
    }

    public String createTrainingSession(String sessionId, LocalDate trainingDate,
            String location, String topic) {
        if (findById(sessionId) != null) {
            return "Session ID already exists.";
        }

        TrainingSession session = new TrainingSession(sessionId, trainingDate,
                location, topic);
        dataStore.getTrainingSessions().add(session);
        dataStore.saveTrainingSessions();
        return "Training Session added successfully!";
    }

    public String recordAttendance(String attendanceId, String playerId,
            String sessionId, boolean present, String note) {
        if (findAttendanceById(attendanceId) != null) {
            return "Attendance ID already exists.";
        }

        Player player = playerService.findById(playerId);
        if (player == null) {
            return "Player not found!";
        }

        TrainingSession session = findById(sessionId);
        if (session == null) {
            return "Training session not found!";
        }

        try {
            AttendanceRecord record = new AttendanceRecord(attendanceId, player,
                    session, present, note);
            dataStore.getAttendanceRecords().add(record);
            session.addAttendanceRecord(record);
            player.getAttendanceRecords().add(record);
            dataStore.saveAttendanceRecords();
            return "Attendance recorded successfully!";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public TrainingSession findById(String sessionId) {
        if (InputUtil.isBlank(sessionId)) {
            return null;
        }

        for (TrainingSession session : dataStore.getTrainingSessions()) {
            if (session.getSessionId().equalsIgnoreCase(sessionId)) {
                return session;
            }
        }
        return null;
    }

    public AttendanceRecord findAttendanceById(String attendanceId) {
        if (InputUtil.isBlank(attendanceId)) {
            return null;
        }

        for (AttendanceRecord record : dataStore.getAttendanceRecords()) {
            if (record.getAttendanceId().equalsIgnoreCase(attendanceId)) {
                return record;
            }
        }
        return null;
    }

    public List<TrainingSession> getTrainingSessions() {
        return dataStore.getTrainingSessions();
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return dataStore.getAttendanceRecords();
    }
}
