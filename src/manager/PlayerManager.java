package manager;

import data.Player;
import data.RegularPlayer;
import data.StarPlayer;
import java.util.ArrayList;
import java.util.List;
import util.InputUtil;

public class PlayerManager {

    private final java.util.Scanner sc = InputUtil.SCANNER;
    private final ArrayList<Player> players = new ArrayList<>();

    public void addPlayer() {
        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID: ");

        if (searchPlayerById(playerId) != null) {
            System.out.println("Player ID already exists.");
            return;
        }

        String playerType = InputUtil.readOption(sc,
                "Enter Player Type (Regular/Star): ", "Regular", "Star");

        String fullName = InputUtil.readRequiredString(sc, "Enter Full Name: ");

        int age = InputUtil.readIntMin(sc, "Enter Age: ", 1);

        String nationality = InputUtil.readRequiredString(sc, "Enter Nationality: ");
       

        String position = InputUtil.readPosition(sc, "Enter New Position (GK/DF/MF/ST/FW): ");

        int shirtNumber = InputUtil.readIntMin(sc, "Enter Shirt Number: ", 1);

        double baseSalary = InputUtil.readDoubleMin(sc, "Enter Base Salary: ", 0);

        Player player;
        if (playerType.equalsIgnoreCase("Star")) {
            player = new StarPlayer(playerId, "Star", fullName, age,
                    nationality, position, shirtNumber, baseSalary, "Active");
        } else {
            player = new RegularPlayer(playerId, "Regular", fullName, age,
                    nationality, position, shirtNumber, baseSalary, "Active");
        }

        players.add(player);
        System.out.println("Player added successfully!");
    }

    public void searchPlayerById() {
        String playerId = InputUtil.readRequiredString(sc, "Enter Player ID: ");
        Player player = searchPlayerById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        displayPlayerHeader();
        displayPlayerRow(player);
    }

    public Player searchPlayerById(String playerId) {
        if (InputUtil.isBlank(playerId)) {
            return null;
        }

        for (Player p : players) {
            if (p.getPlayerId().equalsIgnoreCase(playerId)) {
                return p;
            }
        }
        return null;
    }

    public void updatePlayer() {
        String playerId = InputUtil.readRequiredString(sc,
                "Enter Player ID to update: ");

        String fullName = InputUtil.readRequiredString(sc,
                "Enter New Full Name: ");

        int age = InputUtil.readIntMin(sc, "Enter New Age: ", 1);

        String nationality = InputUtil.readRequiredString(sc,
                "Enter New Nationality: ");

        String position = InputUtil.readRequiredString(sc,
                "Enter New Position: ");

        int shirtNumber = InputUtil.readIntMin(sc,
                "Enter New Shirt Number: ", 1);

        double baseSalary = InputUtil.readDoubleMin(sc,
                "Enter New Base Salary: ", 0);

        Player player = searchPlayerById(playerId);

        if (player != null) {
            player.setFullName(fullName);
            player.setAge(age);
            player.setNationality(nationality);
            player.setPosition(position);
            player.setShirtNumber(shirtNumber);
            player.setBaseSalary(baseSalary);
            System.out.println("Player updated successfully!");
            return;
        }

        System.out.println("Player not found.");
    }

    public void deactivatePlayer() {
        String playerId = InputUtil.readRequiredString(sc,
                "Enter Player ID to deactivate: ");

        Player player = searchPlayerById(playerId);

        if (player != null) {
            player.setStatus("Inactive");
            System.out.println("Player deactivated successfully!");
            return;
        }

        System.out.println("Player not found.");
    }

    public void displayPlayers() {

        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }

        System.out.println("\n===== PLAYER LIST =====");
        displayPlayerHeader();

        for (Player p : players) {
            displayPlayerRow(p);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void displayPlayerHeader() {
        System.out.printf("%-8s | %-20s | %-5s | %-15s | %-15s | %-9s | %-12s | %-10s%n",
                "ID", "Name", "Age", "Nationality", "Position", "Shirt No", "Salary", "Status");
    }

    private void displayPlayerRow(Player player) {
        System.out.printf("%-8s | %-20s | %-5d | %-15s | %-15s | %-9d | %-12.2f | %-10s%n",
                player.getPlayerId(),
                player.getFullName(),
                player.getAge(),
                player.getNationality(),
                player.getPosition(),
                player.getShirtNumber(),
                player.getBaseSalary(),
                player.getStatus());
    }

}
