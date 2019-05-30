package edu.kis.powp.jobs2d.command.service;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.formatter.Formatter;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.SingleCommand;
import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandService implements ICommandService{

    private DriverCommandManager commandManager;
    private List<SingleCommand> singleCommands = new ArrayList<>();
    private List<Formatter> formatters;

    public CommandService(DriverCommandManager commandManager, List<Formatter> formatters) {
        this.commandManager = commandManager;
        this.formatters = formatters;
    }

    public String updateObserver() {
        String observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        return observerListString;
    }

    public void setCurrentCommand(String newCommandText) throws IOException {
        this.commandManager.setCurrentCommand(manageLoadedCommands(newCommandText), "TopSecretCommand");
    }

    public List<DriverCommand> manageLoadedCommands(String readCommand) throws IOException {
        char charDefiningFormat = readCommand.trim().charAt(0);

        formatters.forEach(formatter -> {
            if (formatter.validate(charDefiningFormat)) {
                singleCommands = formatter.createCommand(readCommand);
            }
        });

        if (singleCommands == null) {
            throw new IOException();
        }

        List<DriverCommand> driverCommands = new ArrayList<>();
        singleCommands.forEach(e -> driverCommands.add(e.getCommand()));

        return driverCommands;
    }

    public void clearObservers(){
        commandManager.getChangePublisher().clearObservers();
    }

    public String getCurrentCommandString(){
        return commandManager.getCurrentCommandString();
    }

    public void clearCurrentCommand(){
        commandManager.clearCurrentCommand();
    }
}
