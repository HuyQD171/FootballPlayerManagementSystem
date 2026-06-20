package service;

import model.Player;
import model.RegularPlayer;
import model.StarPlayer;
import java.util.List;
import storage.DataStore;
import util.InputUtil;

public class PlayerService {

    private final DataStore dataStore;

    public PlayerService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String addPlayer(String playerId, String playerType, String fullName,
            int age, String nationality, String position, int shirtNumber,
            double baseSalary) {

        if (findById(playerId) != null) {
            return "Player ID already exists.";
        }

        if (isShirtNumberExists(shirtNumber, null)) {
            return "Shirt number already exists.";
        }

        try {
            Player player;
            if ("Star".equalsIgnoreCase(playerType)) {
                player = new StarPlayer(playerId, "Star", fullName, age,
                        nationality, position, shirtNumber, baseSalary, "Active");
            } else {
                player = new RegularPlayer(playerId, "Regular", fullName, age,
                        nationality, position, shirtNumber, baseSalary, "Active");
            }

            dataStore.getPlayers().add(player);
            try {
                dataStore.savePlayers();
                return "Player added successfully!";
            } catch (RuntimeException e) {
                dataStore.getPlayers().remove(player);
                return e.getMessage();
            }
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public String updatePlayer(String playerId, String fullName, int age,
            String nationality, String position, int shirtNumber,
            double baseSalary, String status) {

        Player player = findById(playerId);
        if (player == null) {
            return "Player not found.";
        }

        if (isShirtNumberExists(shirtNumber, playerId)) {
            return "Shirt number already exists.";
        }

        String oldFullName = player.getFullName();
        int oldAge = player.getAge();
        String oldNationality = player.getNationality();
        String oldPosition = player.getPosition();
        int oldShirtNumber = player.getShirtNumber();
        double oldBaseSalary = player.getBaseSalary();
        String oldStatus = player.getStatus();

        try {
            player.setFullName(fullName);
            player.setAge(age);
            player.setNationality(nationality);
            player.setPosition(position);
            player.setShirtNumber(shirtNumber);
            player.setBaseSalary(baseSalary);
            player.setStatus(status);
            dataStore.savePlayers();
            return "Player updated successfully!";
        } catch (RuntimeException e) {
            player.setFullName(oldFullName);
            player.setAge(oldAge);
            player.setNationality(oldNationality);
            player.setPosition(oldPosition);
            player.setShirtNumber(oldShirtNumber);
            player.setBaseSalary(oldBaseSalary);
            player.setStatus(oldStatus);
            return e.getMessage();
        }
    }

    public String deactivatePlayer(String playerId) {
        Player player = findById(playerId);
        if (player == null) {
            return "Player not found.";
        }

        String oldStatus = player.getStatus();
        try {
            player.setStatus("Inactive");
            dataStore.savePlayers();
            return "Player deactivated successfully!";
        } catch (RuntimeException e) {
            player.setStatus(oldStatus);
            return e.getMessage();
        }
    }

    public Player findById(String playerId) {
        if (InputUtil.isBlank(playerId)) {
            return null;
        }

        for (Player player : dataStore.getPlayers()) {
            if (player.getPlayerId().equalsIgnoreCase(playerId)) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return dataStore.getPlayers();
    }

    public boolean isShirtNumberExists(int shirtNumber, String ignoredPlayerId) {
        for (Player player : dataStore.getPlayers()) {
            if (player.getShirtNumber() == shirtNumber
                    && (ignoredPlayerId == null
                    || !player.getPlayerId().equalsIgnoreCase(ignoredPlayerId))) {
                return true;
            }
        }
        return false;
    }
}
