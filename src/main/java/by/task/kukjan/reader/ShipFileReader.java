package by.task.kukjan.reader;

import by.task.kukjan.exception.ThreadException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShipFileReader {

    private static final Logger logger = LogManager.getLogger();

    public List<String> readFile(String filename) throws ThreadException {
        if (getClass().getClassLoader().getResource(filename) == null) {
            logger.error("File \"{}\" doesn't exist in specified directory.", filename);
            throw new ThreadException("File \"" + filename + "\" doesn't exist in specified directory.");
        }
        List<String> shipsData;
        Stream<String> lines = Stream.<String>builder().build();
        try {
            Path pathToFile = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
            lines = Files.lines(pathToFile);
            shipsData = lines.collect(Collectors.toList());
        } catch (IOException | URISyntaxException exception) {
            logger.error("Error was found while extracting buses' data from the file  \"{}\"", filename);
            throw new ThreadException("Error was found while extracting buses' data from the file  \"" + filename + "\"", exception);
        } finally {
            lines.close();
        }
        return shipsData;
    }
}
