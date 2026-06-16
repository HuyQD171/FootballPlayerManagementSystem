package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingSession {

    private String sessionId;
    private LocalDate trainingDate;
    private String location;
    private String topic;
    private List<AttendanceRecord> attendanceRecords;

    public TrainingSession() {
        attendanceRecords = new ArrayList<>();
    }

    public TrainingSession(String sessionId, LocalDate trainingDate,
            String location, String topic) {
        this.sessionId = sessionId;
        this.trainingDate = trainingDate;
        this.location = location;
        this.topic = topic;
        attendanceRecords = new ArrayList<>();
    }

    public void addAttendanceRecord(AttendanceRecord record) {
        if (record != null) {
            attendanceRecords.add(record);
        }
    }

    public void displayInfo() {
        System.out.printf("%-8s | %-12s | %-15s | %-20s%n",
                sessionId, trainingDate, location, topic);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }
}
