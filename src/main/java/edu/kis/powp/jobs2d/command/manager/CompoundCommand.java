package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.Iterator;
import java.util.List;

public class CompoundCommand implements ICompoundCommand {

    private List<DriverCommand> driverCommands;
    private String name;

    public CompoundCommand(List<DriverCommand> driverCommands, String name) {
        this.driverCommands = driverCommands;
        this.name = name;
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return driverCommands.iterator();
    }

    @Override
    public void execute(Job2dDriver driver) {
        this.iterator().forEachRemaining(e -> e.execute(driver));
    }

    @Override
    public String toString() {
        return name;
    }
}
