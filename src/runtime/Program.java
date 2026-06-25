package runtime;

import model.AttendanceRecord;
import model.MatchRecord;
import model.PerformanceRecord;
import model.Player;
import model.StarPlayer;
import model.TrainingSession;
import java.util.List;
import java.util.Scanner;
import service.MatchService;
import service.PlayerService;
import service.ReportService;
import service.TrainingService;
import storage.DataStore;
import util.InputUtil;

public class Program {

    private static final Scanner sc = InputUtil.SCANNER;
    private static final DataStore dataStore = new DataStore();
    private static final PlayerService playerService = new PlayerService(dataStore);
    private static final TrainingService trainingService = new TrainingService(dataStore, playerService);
    private static final MatchService matchService = new MatchService(dataStore, playerService);
    private static final ReportService reportService = new ReportService();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== FOOTBALL PLAYER MANAGEMENT SYSTEM =====");
            System.out.println("1. Player Management");
            System.out.println("2. Training Management");
            System.out.println("3. Match Management");
            System.out.println("4. Reports");
            System.out.println("0. Exit");

            choice = InputUtil.readIntInRange(sc, "Choose: ", 0, 4);

            switch (choice) {
                case 1:
                    playerMenu();
                    break;
                case 2:
                    trainingMenu();
                    break;
                case 3:
                    matchMenu();
                    break;
                case 4:
                    reportMenu();
                    break;
                case 0:
                    System.out.println("Program closed.");
                    break;
            }
        } while (choice != 0);
    }

    public static void playerMenu() {
        int choice;

        do {
            System.out.println("\n===== PLAYER MANAGEMENT =====");
            System.out.println("1. Add Player");
            System.out.println("2. Update Player");
            System.out.println("3. Search Player By ID");
            System.out.println("4. Deactivate Player");
            System.out.println("5. Display Players");
            System.out.println("0. Back");

            choice = InputUtil.readIntInRange(sc, "Choose: ", 0, 5);

            switch (choice) {
                case 1:
                    addPlayer();
                    break;
                case 2:
                    updatePlayer();
                    break;
                case 3:
                    searchPlayerById();
                    break;
                case 4:
                    deactivatePlayer();
                    break;
                case 5:
                    displayPlayers();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    public static void trainingMenu() {
        int choice;

        do {
            System.out.println("\n===== TRAINING MANAGEMENT =====");
            System.out.println("1. Create Training Session");
            System.out.println("2. Search Training By ID");
            System.out.println("3. Record Attendance");
            System.out.println("4. Display Training Sessions");
            System.out.println("5. Display Attendance Records");
            System.out.println("0. Back");

            choice = InputUtil.readIntInRange(sc, "Choose: ", 0, 5);

            switch (choice) {
                case 1:
                    createTrainingSession();
                    break;
                case 2:
                    searchTrainingById();
                    break;
                case 3:
                    recordAttendance();
                    break;
                case 4:
                    displayTrainingSessions();
                    break;
                case 5:
                    displayAttendanceRecords();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    public static void matchMenu() {
        int choice;

        do {
            System.out.println("\n===== MATCH MANAGEMENT =====");
            System.out.println("1. Create Match Record");
            System.out.println("2. Add Performance Record");
            System.out.println("3. Display Match Records");
            System.out.println("4. Display Performance Records");
            System.out.println("0. Back");

            choice = InputUtil.readIntInRange(sc, "Choose: ", 0, 4);

            switch (choice) {
                case 1:
                    createMatchRecord();
                    break;
                case 2:
                    addPerformanceRecord();
                    break;
                case 3:
                    displayMatchRecords();
                    break;
                case 4:
                    displayPerformanceRecords();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    public static void reportMenu() {
        int choice;

        do {
            System.out.println("\n===== REPORT MANAGEMENT =====");
            System.out.println("1. Generate Salary Report");
            System.out.println("2. Generate Top Scorer Report");
            System.out.println("3. Manage Bonus Multiplier (Star Player)");
           
            System.out.println("0. Back");

            choice = InputUtil.readIntInRange(sc, "Choose: ", 0, 3);

            switch (choice) {
                case 1:
                    generateSalaryReport();
                    break;
                case 2:
                    generateTopScorerReport();
                    break;
                case 3:
                    manageBonusMultiplier();
                    break;
                     
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    private static void addPlayer() {
        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID: ");
        String playerType = InputUtil.readOption(sc,
                "Enter Player Type (Regular/Star): ", "Regular", "Star");
        String fullName = InputUtil.readRequiredString(sc, "Enter Full Name: ");
        int age = InputUtil.readIntMin(sc, "Enter Age: ", 1);
        String nationality = InputUtil.readRequiredString(sc, "Enter Nationality: ");
        String position = InputUtil.readPosition(sc, "Enter Position (GK/DF/MF/ST/FW): ");
        int shirtNumber = readAvailableShirtNumber(null);
        double baseSalary = InputUtil.readDoubleMin(sc, "Enter Base Salary: ", 0);

        System.out.println(playerService.addPlayer(playerId, playerType, fullName,
                age, nationality, position, shirtNumber, baseSalary));
    }

    private static void updatePlayer() {
        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID to update: ");
        Player player = playerService.findById(playerId);
        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        String fullName = InputUtil.readRequiredString(sc, "Enter New Full Name: ");
        int age = InputUtil.readIntMin(sc, "Enter New Age: ", 1);
        String nationality = InputUtil.readRequiredString(sc, "Enter New Nationality: ");
        String position = InputUtil.readPosition(sc, "Enter New Position (GK/DF/MF/ST/FW): ");
        int shirtNumber = readAvailableShirtNumber(playerId);
        double baseSalary = InputUtil.readDoubleMin(sc, "Enter New Base Salary: ", 0);
        String status = InputUtil.readOption(sc,
                "Enter New Status (Active/Inactive/Injured): ",
                "Active", "Inactive", "Injured");

        System.out.println(playerService.updatePlayer(playerId, fullName, age,
                nationality, position, shirtNumber, baseSalary, status));
    }

    private static void searchPlayerById() {
        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID: ");
        Player player = playerService.findById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        displayPlayerHeader();
        displayPlayerRow(player);
    }

    private static void deactivatePlayer() {
        String playerId = InputUtil.readRequiredString(sc,
                "Enter Player ID to deactivate: ");
        System.out.println(playerService.deactivatePlayer(playerId));
    }

    private static void displayPlayers() {
        List<Player> players = playerService.getPlayers();
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }

        System.out.println("\n===== PLAYER LIST =====");
        displayPlayerHeader();
        for (Player player : players) {
            displayPlayerRow(player);
        }
    }

    private static int readAvailableShirtNumber(String ignoredPlayerId) {
        while (true) {
            int shirtNumber = InputUtil.readIntInRange(sc,
                    "Enter Shirt Number (1-99): ", 1, 99);
            if (!playerService.isShirtNumberExists(shirtNumber, ignoredPlayerId)) {
                return shirtNumber;
            }
            System.out.println("Shirt number already exists.");
        }
    }

    private static void displayPlayerHeader() {
        System.out.printf("%-8s | %-20s | %-5s | %-15s | %-8s | %-9s | %-12s | %-10s | %-7s%n",
                "ID", "Name", "Age", "Nationality", "Position", "Shirt No", "Salary", "Status", "Type");
    }

    private static void displayPlayerRow(Player player) {
        System.out.printf("%-8s | %-20s | %-5d | %-15s | %-8s | %-9d | %-12.2f | %-10s | %-7s%n",
                player.getPlayerId(),
                player.getFullName(),
                player.getAge(),
                player.getNationality(),
                player.getPosition(),
                player.getShirtNumber(),
                player.getBaseSalary(),
                player.getStatus(),
                player.getPlayerType());
    }

    private static void createTrainingSession() {
        String sessionId = InputUtil.readRequiredString(sc, "Enter Session ID: ");
        java.time.LocalDate trainingDate = InputUtil.readLocalDate(sc,
                "Enter Training Date (yyyy-MM-dd): ");
        String location = InputUtil.readRequiredString(sc, "Enter Location: ");
        String topic = InputUtil.readRequiredString(sc, "Enter Topic: ");

        System.out.println(trainingService.createTrainingSession(sessionId,
                trainingDate, location, topic));
    }

    private static void searchTrainingById() {
        String sessionId = InputUtil.readRequiredString(sc, "Enter Session ID: ");
        TrainingSession session = trainingService.findById(sessionId);

        if (session == null) {
            System.out.println("Training session not found.");
            return;
        }

        displayTrainingHeader();
        displayTrainingRow(session);
    }

    private static void recordAttendance() {
        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID: ");
        String sessionId = InputUtil.readRequiredString(sc, "Enter Session ID: ");
        String attendanceId = InputUtil.readRequiredString(sc,
                "Enter Attendance ID: ");
        boolean present = InputUtil.readBoolean(sc, "Present (true/false): ");
        System.out.print("Note: ");
        String note = sc.nextLine();

        System.out.println(trainingService.recordAttendance(attendanceId,
                playerId, sessionId, present, note));
    }

    private static void displayTrainingSessions() {
        List<TrainingSession> sessions = trainingService.getTrainingSessions();
        if (sessions.isEmpty()) {
            System.out.println("No training sessions found!");
            return;
        }

        System.out.println("\n===== TRAINING SESSION LIST =====");
        displayTrainingHeader();
        for (TrainingSession session : sessions) {
            displayTrainingRow(session);
        }
    }

    private static void displayAttendanceRecords() {
        List<AttendanceRecord> records = trainingService.getAttendanceRecords();
        if (records.isEmpty()) {
            System.out.println("No attendance records found!");
            return;
        }

        System.out.println("\n===== ATTENDANCE RECORDS =====");
        System.out.printf("%-8s | %-20s | %-8s | %-20s | %-20s%n",
                "ID", "Player", "Present", "Session", "Note");
        for (AttendanceRecord record : records) {
            System.out.printf("%-8s | %-20s | %-8s | %-20s | %-20s%n",
                    record.getAttendanceId(),
                    record.getPlayer().getFullName(),
                    record.isPresent(),
                    record.getTrainingSession().getSessionId(),
                    record.getNote());
        }
    }

    private static void displayTrainingHeader() {
        System.out.printf("%-8s | %-12s | %-15s | %-20s%n",
                "ID", "Date", "Location", "Topic");
    }

    private static void displayTrainingRow(TrainingSession session) {
        System.out.printf("%-8s | %-12s | %-15s | %-20s%n",
                session.getSessionId(),
                session.getTrainingDate(),
                session.getLocation(),
                session.getTopic());
    }

    private static void createMatchRecord() {
        String matchId = InputUtil.readRequiredString(sc, "Enter Match ID: ");
        java.time.LocalDate matchDate = InputUtil.readLocalDate(sc,
                "Enter Match Date (yyyy-MM-dd): ");
        String opponentTeam = InputUtil.readRequiredString(sc,
                "Enter Opponent Team: ");
        String matchType = InputUtil.readOption(sc,
                "Enter Match Type (Friendly/League/Cup): ",
                "Friendly", "League", "Cup");

        System.out.println(matchService.createMatchRecord(matchId, matchDate,
                opponentTeam, matchType));
    }

    private static void addPerformanceRecord() {
        String matchId = InputUtil.readRequiredString(sc, "Enter Match ID: ");
        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID: ");
        String performanceId = InputUtil.readRequiredString(sc,
                "Enter Performance ID: ");
        int goals = InputUtil.readIntMin(sc, "Enter Goals: ", 0);
        int assists = InputUtil.readIntMin(sc, "Enter Assists: ", 0);
        int yellowCards = InputUtil.readIntMin(sc, "Enter Yellow Cards: ", 0);
        int redCards = InputUtil.readIntMin(sc, "Enter Red Cards: ", 0);
        int minutesPlayed = InputUtil.readIntInRange(sc,
                "Enter Minutes Played: ", 0, 120);

        System.out.println(matchService.addPerformanceRecord(performanceId,
                playerId, matchId, goals, assists, yellowCards,
                redCards, minutesPlayed));
    }

    private static void displayMatchRecords() {
        List<MatchRecord> matches = matchService.getMatches();
        if (matches.isEmpty()) {
            System.out.println("No match records available.");
            return;
        }

        System.out.println("\n===== MATCH RECORDS =====");
        displayMatchHeader();
        for (MatchRecord match : matches) {
            displayMatchRow(match);
        }
    }

    private static void displayPerformanceRecords() {
        List<PerformanceRecord> records = matchService.getPerformanceRecords();
        if (records.isEmpty()) {
            System.out.println("No performance records available.");
            return;
        }

        System.out.println("\n===== PERFORMANCE RECORDS =====");
        System.out.printf("%-8s | %-20s | %-8s | %5s | %7s | %6s | %4s | %7s | %6s%n",
                "ID", "Player", "Match", "Goals", "Assists", "Yellow", "Red", "Minutes", "Points");
        for (PerformanceRecord record : records) {
            System.out.printf("%-8s | %-20s | %-8s | %5d | %7d | %6d | %4d | %7d | %6d%n",
                    record.getPerformanceId(),
                    record.getPlayer().getFullName(),
                    record.getMatchRecord().getMatchId(),
                    record.getGoals(),
                    record.getAssists(),
                    record.getYellowCards(),
                    record.getRedCards(),
                    record.getMinutesPlayed(),
                    record.calculatePerformancePoints());
        }
    }

    private static void displayMatchHeader() {
        System.out.printf("%-8s | %-12s | %-20s | %-8s%n",
                "ID", "Date", "Opponent", "Type");
    }

    private static void displayMatchRow(MatchRecord match) {
        System.out.printf("%-8s | %-12s | %-20s | %-8s%n",
                match.getMatchId(),
                match.getMatchDate(),
                match.getOpponentTeam(),
                match.getMatchType());
    }

    private static void generateSalaryReport() {
        List<ReportService.SalaryReportRow> rows = reportService.generateSalaryReport(playerService.getPlayers());

        System.out.println("\n=++= PLAYER SALARY REPORT =++=");
        if (rows.isEmpty()) {
            System.out.println("No players available.");
            return;
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-25s | %-10s | %-18s | %-12s | %-15s%n",
                "Player ID", "Name", "Type", "Performance Points", "Bonus", "Monthly Salary");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (ReportService.SalaryReportRow row : rows) {
            Player player = row.getPlayer();
            System.out.printf("%-10s | %-25s | %-10s | %-18d | %-12.2f | %-15.2f%n",
                    player.getPlayerId(),
                    player.getFullName(),
                    player.getPlayerType(),
                    row.getTotalPoints(),
                    row.getBonus(),
                    row.getMonthlySalary());
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    private static void generateTopScorerReport() {
        ReportService.TopScorerReport report = reportService.generateTopScorerReport(playerService.getPlayers());

        System.out.println("\n=++= TOP SCORER REPORT =++=");
        if (report.getPlayer() == null) {
            System.out.println("No valid player records.");
            return;
        }

        System.out.println("Player: " + report.getPlayer().getFullName());
        System.out.println("Goals : " + report.getGoals());
        System.out.println("\n===== PLAYERS SORTED BY PERFORMANCE POINTS =====");

    List<Player> sortedPlayers =
            reportService.sortPlayersByPerformanceDesc(playerService.getPlayers());

    int rank = 1;

    for (Player player : sortedPlayers) {

        int totalPoints =
                reportService.calculateTotalPerformancePoints(player);

        System.out.printf("%-3d %-20s %-10d\n",
                rank++,
                player.getFullName(),
                totalPoints);
    }

    

    private static void manageBonusMultiplier() {
        System.out.println("\n===== BONUS MULTIPLIER MANAGEMENT =====");
        System.out.println("Current Bonus Multiplier for Star Player: "
                + StarPlayer.getBonusMultiplier() + " VND/point");

        double newMultiplier = InputUtil.readDoubleMin(sc,
                "Enter new multiplier (or 0 to keep current): ", 0);
        if (newMultiplier > 0) {
            StarPlayer.setBonusMultiplier(newMultiplier);
        } else {
            System.out.println("No change was made.");
        }
    }
    }
