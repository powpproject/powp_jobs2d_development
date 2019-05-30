package edu.kis.powp.jobs2d.command.gui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.SingleCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommandManagerWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;

    private JTextArea currentCommandField;
    private JTextArea newCommand;

    private String observerListString;
    private JTextArea observerListField;
    DriverManager driverManager;

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

        this.commandManager = commandManager;

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
        List<String> commandList = new LinkedList<String>();
        commandList.add("1st command");
        commandList.add("2nd command");
        JComboBox commandComboBox = new JComboBox(commandList.toArray());
        commandComboBox.addActionListener((ActionEvent e) -> this.displayTextFieldWithSelectedValueFromComboBox(commandComboBox.getSelectedItem().toString()));
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
        commandManager.clearCurrentCommand();
        updateCurrentCommandField();
    }

    void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    private void deleteObservers() {
        commandManager.getChangePublisher().clearObservers();
        this.updateObserverListField();
    }
    
    void displayTextFieldWithSelectedValueFromComboBox(String selectedItem) {
    	this.newCommand.setText(null);
    	this.newCommand.replaceSelection(selectedItem);
    }

    private void updateObserverListField() {
        observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        observerListField.setText(observerListString);
    }

    private void addCommand() {
        String newCommandText = newCommand.getText();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<SingleCommand> singleCommands = objectMapper.readValue(newCommandText, new TypeReference<List<SingleCommand>>(){});
            List<DriverCommand> driverCommands = new ArrayList<>();
            singleCommands.forEach(e -> driverCommands.add(e.getCommand()));
            commandManager.setCurrentCommand(driverCommands, "TopSecretCommand");
        } catch (IOException e) {
            currentCommandField.setText("Wrong JSON format");
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
