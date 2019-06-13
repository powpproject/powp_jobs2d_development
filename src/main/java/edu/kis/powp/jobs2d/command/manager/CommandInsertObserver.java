package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandInsertObserver implements Subscriber {

	@Override
	public void update() {
		DriverCommand c = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		//CommandManagerWindow.commandList.add(e)
		CommandHistory.addCommandLog(c);
		CommandManagerWindow.commandList.add(CommandHistory.getAmount()-1+"-"+c.toString());
		System.out.print(CommandManagerWindow.commandList.get(CommandHistory.getAmount()-1));
	}

}
