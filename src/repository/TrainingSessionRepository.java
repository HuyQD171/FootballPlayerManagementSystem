package repository;

import model.TrainingSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import storage.FileUtil;

public class TrainingSessionRepository {

    private static final String FILE_NAME = "training_sessions.csv";

    public List<TrainingSession> load() {
        List<TrainingSession> sessions = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_NAME)) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length < 4) {
                continue;
            }

            sessions.add(new TrainingSession(
                    parts[0],
                    LocalDate.parse(parts[1]),
                    parts[2],
                    parts[3]));
        }

        return sessions;
    }

    public void save(List<TrainingSession> sessions) {
        List<String> lines = new ArrayList<>();

        for (TrainingSession session : sessions) {
            lines.add(FileUtil.clean(session.getSessionId()) + "|"
                    + session.getTrainingDate() + "|"
                    + FileUtil.clean(session.getLocation()) + "|"
                    + FileUtil.clean(session.getTopic()));
        }

        FileUtil.writeLines(FILE_NAME, lines);
    }
}
