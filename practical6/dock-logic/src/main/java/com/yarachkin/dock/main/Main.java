package com.yarachkin.dock.main;

import com.yarachkin.dock.dock.Dock;
import com.yarachkin.dock.entity.Ship;
import com.yarachkin.dock.exception.IoDockException;
import com.yarachkin.dock.reader.DockFileReader;
import com.yarachkin.dock.util.DockParser;

import java.util.List;

public class Main {
    public static void main(String[] args) throws IoDockException {
        Dock.getInstance().initializeCapacityAndDockContainersCounts(30, 20, 3);
        List<Ship> ships = DockParser.parse(DockFileReader.getInstance().readFromFile());
        ships.forEach(Ship::start);
    }
}
