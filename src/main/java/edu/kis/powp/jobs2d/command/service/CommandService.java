package edu.kis.powp.jobs2d.command.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.SingleCommand;
import edu.kis.powp.jobs2d.command.manager.SingleCommandList;
import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandService {

    public String updateObserver(DriverCommandManager commandManager) {
        String observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        return observerListString;
    }

    public List<DriverCommand> manageLoadedCommands(String readCommand) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<SingleCommand> singleCommands;

        char charDefiningFormat = readCommand.trim().charAt(0);

        switch (charDefiningFormat) {
            case '<': {
                XmlMapper xmlMapper = new XmlMapper();
                SingleCommandList singleCommandList = xmlMapper.readValue(readCommand, SingleCommandList.class);
                singleCommands = objectMapper.convertValue(singleCommandList.getSingleCommand(), new TypeReference<List<SingleCommand>>() {

                });
                break;
            }
            case '{':
            case '[': {
                singleCommands = objectMapper.readValue(readCommand, new TypeReference<List<SingleCommand>>() {

                });
                break;
            }
            default: {
                throw new IOException();
            }
        }
        List<DriverCommand> driverCommands = new ArrayList<>();
        singleCommands.forEach(e -> driverCommands.add(e.getCommand()));

        return driverCommands;
    }
}
