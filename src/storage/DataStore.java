package storage;

import model.AttendanceRecord;
import model.MatchRecord;
import model.PerformanceRecord;
import model.Player;
import model.TrainingSession;
import java.util.List;
import repository.AttendanceRepository;
import repository.MatchRepository;
import repository.PerformanceRepository;
import repository.PlayerRepository;
import repository.TrainingSessionRepository;

public class DataStore {

    private final PlayerRepository playerRepository = new PlayerRepository();
    private final TrainingSessionRepository trainingSessionRepository = new TrainingSessionRepository();
    private final MatchRepository matchRepository = new MatchRepository();
    private final AttendanceRepository attendanceRepository = new AttendanceRepository();
    private final PerformanceRepository performanceRepository = new PerformanceRepository();

    private final List<Player> players;
    private final List<TrainingSession> trainingSessions;
    private final List<MatchRecord> matches;
    private final List<AttendanceRecord> attendanceRecords;
    private final List<PerformanceRecord> performanceRecords;

    public DataStore() {
        DataSeeder.seedIfNeeded();
        players = playerRepository.load();
        trainingSessions = trainingSessionRepository.load();
        matches = matchRepository.load();
        attendanceRecords = attendanceRepository.load(players, trainingSessions);
        performanceRecords = performanceRepository.load(players, matches);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public List<MatchRecord> getMatches() {
        return matches;
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    public List<PerformanceRecord> getPerformanceRecords() {
        return performanceRecords;
    }

    public void savePlayers() {
        playerRepository.save(players);
    }

    public void saveTrainingSessions() {
        trainingSessionRepository.save(trainingSessions);
    }

    public void saveMatches() {
        matchRepository.save(matches);
    }

    public void saveAttendanceRecords() {
        attendanceRepository.save(attendanceRecords);
    }

    public void savePerformanceRecords() {
        performanceRepository.save(performanceRecords);
    }
}
