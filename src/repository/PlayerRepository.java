package repository;

import model.Player;
import model.RegularPlayer;
import model.StarPlayer;
import java.util.ArrayList;
import java.util.List;
import storage.FileUtil;

public class PlayerRepository {

    private static final String FILE_NAME = "players.csv";

    public List<Player> load() {
        List<Player> players = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_NAME)) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length < 9) {
                continue;
            }

            String playerId = parts[0];
            String playerType = parts[1];
            String fullName = parts[2];
            int age = Integer.parseInt(parts[3]);
            String nationality = parts[4];
            String position = parts[5];
            int shirtNumber = Integer.parseInt(parts[6]);
            double baseSalary = Double.parseDouble(parts[7]);
            String status = parts[8];

            Player player;
            if ("Star".equalsIgnoreCase(playerType)) {
                player = new StarPlayer(playerId, "Star", fullName, age,
                        nationality, position, shirtNumber, baseSalary, status);
            } else {
                player = new RegularPlayer(playerId, "Regular", fullName, age,
                        nationality, position, shirtNumber, baseSalary, status);
            }

            players.add(player);
        }

        return players;
    }

    public void save(List<Player> players) {
        List<String> lines = new ArrayList<>();

        for (Player player : players) {
            lines.add(FileUtil.clean(player.getPlayerId()) + "|"
                    + FileUtil.clean(player.getPlayerType()) + "|"
                    + FileUtil.clean(player.getFullName()) + "|"
                    + player.getAge() + "|"
                    + FileUtil.clean(player.getNationality()) + "|"
                    + FileUtil.clean(player.getPosition()) + "|"
                    + player.getShirtNumber() + "|"
                    + player.getBaseSalary() + "|"
                    + FileUtil.clean(player.getStatus()));
        }

        FileUtil.writeLines(FILE_NAME, lines);
    }
}
