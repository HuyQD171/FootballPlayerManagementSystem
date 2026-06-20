package model;

public class AttendanceRecord {

    private String attendanceId;
    private Player player;
    private TrainingSession trainingSession;
    private boolean present;
    private String note;

    public AttendanceRecord() {
    }

    public AttendanceRecord(String attendanceId, Player player,
            TrainingSession trainingSession, boolean present, String note) {
        setAttendanceId(attendanceId);
        setPlayer(player);
        setTrainingSession(trainingSession);
        setPresent(present);
        setNote(note);
    }

    public void displayInfo() {
        System.out.printf("%-8s | %-20s | %-8s | %-20s%n",
                attendanceId, player.getFullName(), present, note);
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        if (attendanceId == null || attendanceId.trim().isEmpty()) {
            throw new RuntimeException("Attendance ID must not be empty.");
        }
        this.attendanceId = attendanceId;
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

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        if (trainingSession == null) {
            throw new RuntimeException("Training session must not be null.");
        }
        this.trainingSession = trainingSession;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? "" : note;
    }
}
