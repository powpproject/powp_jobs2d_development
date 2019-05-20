package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class SingleCommand {

    private String commandName;
    private int x;
    private int y;

    public SingleCommand() {

    }

    public SingleCommand(String commandName, int x, int y) {
        this.commandName = commandName;
        this.x = x;
        this.y = y;
    }

    public DriverCommand getCommand() {
        if (commandName.equalsIgnoreCase("set"))
            return new SetPositionCommand(x, y);
        return new OperateToCommand(x, y);
    }

    @Override
    public String toString() {
        return commandName + " (" + x + ", " + y + ")";
    }
}

