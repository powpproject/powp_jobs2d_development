package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * DriverCommand interface.
 */
public interface DriverCommand extends CommandAcceptor {

	/**
	 * Execute command on driver.
	 * 
	 * @param driver driver.
	 */
	public void execute(Job2dDriver driver);

}
