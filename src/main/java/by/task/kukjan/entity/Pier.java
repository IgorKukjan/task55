package by.task.kukjan.entity;

import by.task.kukjan.util.PierIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Pier {

    public static final Logger logger = LogManager.getLogger();
    private final int pierId;


    public Pier() {
        pierId = PierIdGenerator.generateId();
    }

    public void processShip(Ship ship){
        logger.log(Level.INFO, "Pier {} processes ship {}:Started", pierId, ship.getShipId());
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while processing ship : {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        Port port = Port.getInstance();
        switch (ship.getTask()){
            case LOAD -> port.loadContainer();
            case UNLOAD -> port.unloadContainer();
        }
        logger.log(Level.INFO, "Pier {} processed ship {} : Complete", pierId, ship.getShipId());
    }

    public long getPierId(){
        return pierId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pier{");
        sb.append("pierId=").append(pierId);
        sb.append('}');
        return sb.toString();
    }
}
