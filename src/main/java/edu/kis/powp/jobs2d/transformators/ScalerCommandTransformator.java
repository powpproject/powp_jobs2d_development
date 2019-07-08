package edu.kis.powp.jobs2d.transformators;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.CommandVisitor;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScalerCommandTransformator implements CommandVisitor{

    private double factor;

    public List<DriverCommand> getCommands() {
        return commands;
    }

    private List<DriverCommand> commands = new ArrayList<>();

    public ScalerCommandTransformator(double factor)
    {
        this.factor = factor;
    }

    @Override
    public void visit(SetPositionCommand command) {
        commands.add(new SetPositionCommand((int)(command.getPosX()*factor),(int)(command.getPosY()*factor)));
    }

    @Override
    public void visit(OperateToCommand command) {
        commands.add(new OperateToCommand((int)(command.getPosX()*factor),(int)(command.getPosY()*factor)));
    }

    @Override
    public void visit(ICompoundCommand command) {
        command.iterator().forEachRemaining(s -> s.accept(this));
    }


}
