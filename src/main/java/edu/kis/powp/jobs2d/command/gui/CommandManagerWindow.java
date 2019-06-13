package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandHistory;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.SingleCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;
import edu.kis.powp.jobs2d.command.service.ICommandService;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	  private DriverCommandManager commandManager;
    public static List<String> commandList = new ArrayList<String>();

    private JTextArea currentCommandField;
    private JTextArea newCommand;

    private JTextArea observerListField;
    private JComboBox commandComboBox;
    DriverManager driverManager;

    private ICommandService commandService;

    /**
     *
     */
    private static final long serialVersionUID = 9204679248304669948L;


    public CommandManagerWindow(DriverCommandManager commandManager, DriverManager driverManager) {
        this.driverManager=driverManager;
        this.setTitle("Command Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        this.commandService = commandService;

        GridBagConstraints c = new GridBagConstraints();

        observerListField = new JTextArea("");
        observerListField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(observerListField, c);
        updateObserverListField();

        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);
        updateCurrentCommandField();

        newCommand = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(newCommand);
        newCommand.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(scrollPane, c);
        
        //TO-DO
        /*List<String> commandList = new LinkedList<String>();
        for(int i=0;i<CommandHistory.getAmount()-1;i++)
        	commandList.add(i+" command");*/
        //DefaultComboBoxModel model = new DefaultComboBoxModel( commandList.toArray() );
        commandComboBox = new JComboBox(commandList.toArray());
        //commandComboBox.setModel( model );
        commandComboBox.addActionListener((ActionEvent e) -> commandManager.setCurrentCommand(CommandHistory.getCommand(commandComboBox.getSelectedIndex())));
        content.add(commandComboBox, c);

        JButton btnAddCommand = new JButton("Add command");
        btnAddCommand.addActionListener((ActionEvent e) -> this.addCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnAddCommand, c);
      
          JButton btnRunCommand = new JButton("Run command");
        btnRunCommand.addActionListener((ActionEvent e) -> this.runCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnRunCommand, c);

        JButton btnClearCommand = new JButton("Clear command");
        btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);

        JButton btnClearObservers = new JButton("Delete observers");
        btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearObservers, c);
    }
  
    private void runCommand() {
      commandManager.runCurrentCommand().execute(driverManager.getCurrentDriver());;
    }

    private void clearCommand() {
        commandService.clearCurrentCommand();
        updateCurrentCommandField();
    }

    void updateCurrentCommandField() {
        currentCommandField.setText(commandService.getCurrentCommandString());
    }

    private void deleteObservers() {
        commandService.clearObservers();
        this.updateObserverListField();
    }
    
    void fillTextArea(String selectedItem) {
    	this.newCommand.setText(null);
    	this.newCommand.replaceSelection(selectedItem);
    }

    private void updateObserverListField() {
        observerListField.setText(commandService.updateObserver());
    }

    private void addCommand() {
        try {
            List<SingleCommand> singleCommands = objectMapper.readValue(newCommandText, new TypeReference<List<SingleCommand>>(){});
            List<DriverCommand> driverCommands = new ArrayList<>();
            singleCommands.forEach(e -> driverCommands.add(e.getCommand()));
            commandManager.setCurrentCommand(driverCommands, "AddedCommand");
            commandComboBox.removeAllItems();
            for(int i=0;i<commandList.size()-1;i++)
            	commandComboBox.addItem(commandList.get(i));
            commandService.setCurrentCommand(newCommand.getText());
        } catch (IOException e) {
            currentCommandField.setText("Wrong command format");
        }
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        updateObserverListField();
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

}
