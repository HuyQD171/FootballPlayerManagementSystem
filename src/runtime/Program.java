package runtime;

import manager.PlayerManager;
import manager.ReportManager;
import manager.TrainingManager;
import manager.MatchManager;
import util.InputUtil;
import data.StarPlayer;

public class Program {

    static java.util.Scanner sc = InputUtil.SCANNER;
    static PlayerManager playerManager = new PlayerManager();
    static MatchManager matchManager = new MatchManager();
    static ReportManager reportManager = new ReportManager();

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
                    playerManager.addPlayer();
                    break;

                case 2:
                    playerManager.updatePlayer();
                    break;

                case 3:
                    playerManager.searchPlayerById();
                    break;

                case 4:
                    playerManager.deactivatePlayer();
                    break;

                case 5:
                    playerManager.displayPlayers();
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
                    TrainingManager.createTrainingSession();
                    break;

                case 2:
                    TrainingManager.searchTrainingById();
                    break;

                case 3:
                    TrainingManager.recordAttendance(playerManager);
                    break;

                case 4:
                    TrainingManager.displayTrainingSessions();
                    break;

                case 5:
                    TrainingManager.displayAttendanceRecords();
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
                    matchManager.createMatchRecord();
                    break;

                case 2:
                    matchManager.addPerformanceRecord(playerManager);
                    break;

                case 3:
                    matchManager.displayMatchRecords();
                    break;

                case 4:
                    matchManager.displayPerformanceRecords();
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
            System.out.println("0. Back");

            choice = InputUtil.readIntInRange(sc, "Choose: ", 0, 2);

            switch (choice) {

                case 1:
                    reportManager.generateSalaryReport(playerManager.getPlayers());
                    break;

                case 2:
                    reportManager.generateTopScorerReport(playerManager.getPlayers());
                    break;

                case 0:
                    break;
            }

        } while (choice != 0);
    }
    private static void manageBonusMultiplier() {
        System.out.println("\n===== BONUS MULTIPLIER MANAGEMENT =====");
        System.out.println("Current Bonus Multiplier for Star Player: " 
                + StarPlayer.getBonusMultiplier() + " VND/point");

        System.out.print("Enter new multiplier (or 0 to keep current): ");
        double newMultiplier = sc.nextDouble();
        sc.nextLine(); 

}
