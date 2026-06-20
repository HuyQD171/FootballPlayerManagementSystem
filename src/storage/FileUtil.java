package storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final Path DATA_DIR = Paths.get("data");

    public static Path dataFile(String fileName) {
        ensureDataDirectory();
        return DATA_DIR.resolve(fileName);
    }

    public static void ensureDataDirectory() {
        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot create data directory.", e);
        }
    }

    public static List<String> readLines(String fileName) {
        try {
            Path path = dataFile(fileName);
            if (!Files.exists(path)) {
                return new ArrayList<>();
            }
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + fileName, e);
        }
    }

    public static void writeLines(String fileName, List<String> lines) {
        try {
            Path path = dataFile(fileName);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file: " + dataFile(fileName).toAbsolutePath()
                    + ". Please close Excel/WPS if this file is open.", e);
        }
    }

    public static boolean isMissingOrEmpty(String fileName) {
        try {
            Path path = dataFile(fileName);
            return !Files.exists(path) || Files.size(path) == 0;
        } catch (IOException e) {
            throw new RuntimeException("Cannot check file: " + fileName, e);
        }
    }

    public static String clean(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("|", "/").replace("\r", " ").replace("\n", " ").trim();
    }
}
