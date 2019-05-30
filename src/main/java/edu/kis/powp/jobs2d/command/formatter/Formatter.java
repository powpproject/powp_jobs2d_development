package edu.kis.powp.jobs2d.command.formatter;

import edu.kis.powp.jobs2d.command.manager.SingleCommand;

import java.util.List;

public interface Formatter {

    boolean validate(char firstChar);

    List<SingleCommand> createCommand(String inputTest);
}
