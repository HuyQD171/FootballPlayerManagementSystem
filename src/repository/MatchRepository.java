package repository;

import model.MatchRecord;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import storage.FileUtil;

public class MatchRepository {

    private static final String FILE_NAME = "matches.csv";

    public List<MatchRecord> load() {
        List<MatchRecord> matches = new ArrayList<>();

        for (String line : FileUtil.readLines(FILE_NAME)) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length < 4) {
                continue;
            }

            matches.add(new MatchRecord(
                    parts[0],
                    LocalDate.parse(parts[1]),
                    parts[2],
                    parts[3]));
        }

        return matches;
    }

    public void save(List<MatchRecord> matches) {
        List<String> lines = new ArrayList<>();

        for (MatchRecord match : matches) {
            lines.add(FileUtil.clean(match.getMatchId()) + "|"
                    + match.getMatchDate() + "|"
                    + FileUtil.clean(match.getOpponentTeam()) + "|"
                    + FileUtil.clean(match.getMatchType()));
        }

        FileUtil.writeLines(FILE_NAME, lines);
    }
}
