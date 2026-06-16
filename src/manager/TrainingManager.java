package manager;

import data.AttendanceRecord;
import data.Player;
import data.TrainingSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.InputUtil;

public class TrainingManager {

    private static final java.util.Scanner SC = InputUtil.SCANNER;
    private static final List<TrainingSession> sessions = new ArrayList<>();
    private static final List<AttendanceRecord> attendanceList = new ArrayList<>();

    public static void createTrainingSession() {
        String sessionId = InputUtil.readRequiredString(SC,
                "Enter Session ID: ");

        if (searchTrainingById(sessionId) != null) {
            System.out.println("Session ID already exists.");
            return;
        }

        LocalDate trainingDate = InputUtil.readLocalDate(SC,
                "Enter Training Date (yyyy-MM-dd): ");

        String location = InputUtil.readRequiredString(SC,
                "Enter Location: ");

        String topic = InputUtil.readRequiredString(SC, "Enter Topic: ");

        TrainingSession session = new TrainingSession(
                sessionId,
                trainingDate,
                location,
                topic);

        sessions.add(session);

        System.out.println("Training Session added successfully!");
    }

    private static TrainingSession searchTrainingById(String id) {
        if (InputUtil.isBlank(id)) {
            return null;
        }

        for (TrainingSession session : sessions) {
            if (session.getSessionId().equalsIgnoreCase(id)) {
                return session;
            }
        }

        return null;
    }

    public static void searchTrainingById() {
        String sessionId = InputUtil.readRequiredString(SC,
                "Enter Session ID: ");
        TrainingSession session = searchTrainingById(sessionId);

        if (session == null) {
            System.out.println("Training session not found.");
            return;
        }

        System.out.printf("%-8s | %-12s | %-15s | %-20s%n",
                "ID", "Date", "Location", "Topic");
        session.displayInfo();
    }

    public static void recordAttendance(PlayerManager playerManager) {
        if (playerManager == null) {
            System.out.println("Player manager not found!");
            return;
        }

        String playerId = InputUtil.readRequiredString(SC,
                "Enter Player ID: ");
        Player player = playerManager.searchPlayerById(playerId);

        String sessionId = InputUtil.readRequiredString(SC,
                "Enter Session ID: ");
        TrainingSession session = searchTrainingById(sessionId);

        if (player == null) {
            System.out.println("Player not found!");
            return;
        }

        if (session == null) {
            System.out.println("Training session not found!");
            return;
        }

        String attendanceId = InputUtil.readRequiredString(SC,
                "Enter Attendance ID: ");

        if (searchAttendanceById(attendanceId) != null) {
            System.out.println("Attendance ID already exists.");
            return;
        }

        boolean present = InputUtil.readBoolean(SC, "Present (true/false): ");

        System.out.print("Note: ");
        String note = SC.nextLine();

        AttendanceRecord record = new AttendanceRecord(
                attendanceId,
                player,
                session,
                present,
                note);

        attendanceList.add(record);
        session.addAttendanceRecord(record);
        player.getAttendanceRecords().add(record);

        System.out.println("Attendance recorded successfully!");
    }

    public static void displayTrainingSessions() {
        if (sessions.isEmpty()) {
            System.out.println("No training sessions found!");
            return;
        }

        System.out.println("\n===== TRAINING SESSION LIST =====");

        System.out.printf("%-8s | %-12s | %-15s | %-20s%n",
                "ID", "Date", "Location", "Topic");

        for (TrainingSession session : sessions) {
            session.displayInfo();
        }
    }

    public static void displayAttendanceRecords() {
        if (attendanceList.isEmpty()) {
            System.out.println("No attendance records found!");
            return;
        }

        System.out.println("\n===== ATTENDANCE RECORDS =====");

        System.out.printf("%-8s | %-20s | %-8s | %-20s%n",
                "ID", "Player", "Present", "Note");

        for (AttendanceRecord record : attendanceList) {
            record.displayInfo();
        }
    }

    private static AttendanceRecord searchAttendanceById(String attendanceId) {
        if (InputUtil.isBlank(attendanceId)) {
            return null;
        }

        for (AttendanceRecord record : attendanceList) {
            if (record.getAttendanceId().equalsIgnoreCase(attendanceId)) {
                return record;
            }
        }

        return null;
    }
}
