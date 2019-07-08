package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.transformators.ScalerCommandTransformator;
import edu.kis.powp.jobs2d.command.manager.CommandVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectScaleOptionListener implements ActionListener {

    private double factor;

    public SelectScaleOptionListener(double factor)
    {
        this.factor = factor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandVisitor v = new ScalerCommandTransformator(factor);
        CommandsFeature.getDriverCommandManager().getCurrentCommand().accept(v);
        CommandsFeature.getDriverCommandManager().setCurrentCommand(((ScalerCommandTransformator) v).getCommands(),"ScaledCommand");

    }
}
