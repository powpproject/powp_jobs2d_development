package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandInsertObserver implements Subscriber {

	@Override
	public void update() {
		CommandHistory.addCommandLog(CommandsFeature.getDriverCommandManager().getCommandList());
		System.out.print(CommandHistory.getAmount());
	}

}
