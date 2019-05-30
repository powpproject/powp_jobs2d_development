package edu.kis.powp.jobs2d.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapterUseControl;

public class DataFile {
	LineDriverAdapterUseControl driver;
	private double currentLevel;

	public DataFile() throws FileNotFoundException {
		getDataFromFile();
	}
	
	public DataFile(LineDriverAdapterUseControl driver) {
		this.driver = driver;
	}

	@SuppressWarnings("resource")
	private void getDataFromFile() throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner in = new Scanner(file);

        String dataString = in.nextLine();
            
        setCurrentLevel(Double.parseDouble((dataString)));
	}
	
	public void saveData() {
		File fnew=new File("data.txt");
		FileWriter f2;

		try 
		{
		    f2 = new FileWriter(fnew,false);
		    f2.write(Double.toString(driver.getDistance())); 
	
		    f2.close();
		} catch (IOException e) {
		        e.printStackTrace();
		} 
	}
	
	public double getCurrentLevel() throws FileNotFoundException {
		getDataFromFile();
		return currentLevel;
	}

	public void setCurrentLevel(double d) {
		this.currentLevel = d;
	}
}
