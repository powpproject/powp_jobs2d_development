package edu.kis.powp.jobs2d.command.service;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

import java.util.List;

public class CommandService {

    public String updateObserver(DriverCommandManager commandManager){
        String observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
            System.out.println(observerListString);
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        return observerListString;
    }

}
