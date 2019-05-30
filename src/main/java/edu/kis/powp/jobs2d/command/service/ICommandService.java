package edu.kis.powp.jobs2d.command.service;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.io.IOException;
import java.util.List;

public interface ICommandService {

    void clearCurrentCommand();

    String getCurrentCommandString();

    void clearObservers();

    String updateObserver();

    List<DriverCommand> manageLoadedCommands(String readCommand) throws IOException;

    void setCurrentCommand(String newCommandText) throws IOException;
}
