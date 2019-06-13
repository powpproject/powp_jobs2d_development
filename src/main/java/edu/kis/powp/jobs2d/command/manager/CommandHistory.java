package edu.kis.powp.jobs2d.command.manager;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.DriverCommand;

public class CommandHistory {
	static List<DriverCommand> commandsHistory=new ArrayList<DriverCommand>();
	
	static public void addCommandLog(DriverCommand command){
		commandsHistory.add(command);
	}
	
	static public int getAmount() {return commandsHistory.size();}
	
	static public DriverCommand getCommand(int index){
		return commandsHistory.get(index);
	}

}
