package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.manager.CommandVisitor;

public interface CommandAcceptor {

    void accept(CommandVisitor commandVisitor);
}
