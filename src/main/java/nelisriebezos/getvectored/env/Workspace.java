package nelisriebezos.getvectored.env;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Workspace {
    private static final Logger logger = Logger.getLogger(Workspace.class.getName());
    private final String rootFolder = System.getProperty("user.dir");

    public List<String> listDirectories(String pathString) {
        List<String> directoryNames = new ArrayList<>();
        Path path = Paths.get(createFullPath(pathString));

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    directoryNames.add(entry.getFileName().toString());
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to list directories: " + e.getMessage());
        }

        return directoryNames;
    }

    public void createDirectory(String directoryName) {
        Path path = Paths.get(createFullPath(directoryName));
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to create directory: " + e.getMessage());
        }
    }

    public void createDirectoriesForFile(String fileName) {
        File file = new File(fileName);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
    }

    private String createFullPath(String fileName) {
        if (fileName.startsWith(File.separator))
            fileName = fileName.substring(1);
        Path fullPath = Path.of(rootFolder).resolve(Paths.get(fileName));
        return fullPath.toString();
    }

    public boolean fileExists(String fileName) {
        return Files.exists(Paths.get(createFullPath(fileName)));
    }

    public List<String> listFiles(String folder) {
        List<String> fileNames = new ArrayList<>();
        File directory = new File(createFullPath(folder));
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        return fileNames;
    }

    public String readFile(String sourceFile) throws IOException {
        return Files.readString(Paths.get(createFullPath(sourceFile)), UTF_8);
    }

    public void writeFile(String fileName, String contents) throws IOException {
        String fullPath = createFullPath(fileName);
        createDirectoriesForFile(fullPath);
        Path filePath = Paths.get(fullPath);
        try (FileWriter fw = new FileWriter(filePath.toFile())) {
            fw.write(contents);
        }
    }

    public boolean deleteFile(String fileName) throws IOException {
        Path path = Paths.get(createFullPath(fileName));
        if (Files.exists(path)) {
            try {
                Files.delete(path);
                return true;
            } catch (NoSuchFileException e) {
                logger.log(Level.WARNING, "File not found: " + fileName);
                throw new IOException("File not found: " + fileName, e);
            }
        } else {
            logger.log(Level.WARNING, "File not found: " + fileName);
            throw new IOException("File not found: " + fileName);
        }
    }
}