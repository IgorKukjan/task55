package by.task.kukjan._main;

import by.task.kukjan.entity.Ship;
import by.task.kukjan.exception.ThreadException;
import by.task.kukjan.parser.ShipParser;
import by.task.kukjan.reader.ShipFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args)  throws ThreadException {
        ShipFileReader shipReader = new ShipFileReader();
        ShipParser shipParser = new ShipParser();
        List<String> readShipData = shipReader.readFile("file/data.txt");
        List<Ship>  ships = shipParser.parseShips(readShipData);

        ExecutorService executorService = Executors.newFixedThreadPool(readShipData.size());
        ships.forEach(executorService::execute);
        executorService.shutdown();
    }
}
