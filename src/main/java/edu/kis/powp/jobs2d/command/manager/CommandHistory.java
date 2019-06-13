package edu.kis.powp.jobs2d.command.manager;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.DriverCommand;

public class CommandHistory {
	static List<List<DriverCommand>> commandsHistory=new ArrayList<List<DriverCommand>>();
	
	static public void addCommandLog(List<DriverCommand> command){
		commandsHistory.add(command);
	}
	
	static public int getAmount() {return commandsHistory.size();}

}
