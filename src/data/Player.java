package data;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String playerId;
    private String playerType;
    private String fullName;
    private int age;
    private String nationality;
    private String position;
    private int shirtNumber;
    private double baseSalary;
    private String status;
    private String number;
    //23232

    private List<AttendanceRecord> attendanceRecords;
    private List<PerformanceRecord> performanceRecords;

    public Player() {
        attendanceRecords = new ArrayList<>();
        performanceRecords = new ArrayList<>();
    }

    public Player(String playerId, String playerType,
            String fullName, int age,
            String nationality, String position,
            int shirtNumber, double baseSalary,
            String status) {

        this.playerId = playerId;
        this.playerType = playerType;
        this.fullName = fullName;

        setAge(age);

        this.nationality = nationality;

        setPosition(position);

        setShirtNumber(shirtNumber);

        setBaseSalary(baseSalary);

        setStatus(status);

        attendanceRecords = new ArrayList<>();
        performanceRecords = new ArrayList<>();
    }

    public abstract double calculateBonus(int points);

    public double calculateMonthlySalary(double bonus) {
        return baseSalary + bonus;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position != null && !position.isEmpty()) {
            this.position = position;
        }
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        if (shirtNumber > 0) {
            this.shirtNumber = shirtNumber;
        }
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.status = status;
        }
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void setAttendanceRecords(List<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    public List<PerformanceRecord> getPerformanceRecords() {
        return performanceRecords;
    }

    public void setPerformanceRecords(List<PerformanceRecord> performanceRecords) {
        this.performanceRecords = performanceRecords;
    }
}
