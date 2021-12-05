package by.task.kukjan.parser;

import by.task.kukjan.entity.Ship;
import by.task.kukjan.exception.ThreadException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class ShipParser {
    private static final Logger logger = LogManager.getLogger();

    public List<Ship> parseShips(List<String> shipLines) throws ThreadException{
        List<Ship> shipList;
        try{
            shipList = shipLines.stream()
                .map(Ship.ShipTask::valueOf)
                .map(Ship::new)
                .collect(Collectors.toList());
        }catch (IllegalArgumentException e){
            logger.log(Level.ERROR, "Error while parsing ship lines");
            throw  new ThreadException("Error while parsing ship lines");
        }

        logger.log(Level.INFO, "Lines parsed successfully");
        return shipList;
    }
}
