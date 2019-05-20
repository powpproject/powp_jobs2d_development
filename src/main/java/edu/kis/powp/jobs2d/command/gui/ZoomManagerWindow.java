package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ZoomManagerWindow extends JFrame implements WindowComponent {
    //private DriverCommandManager commandManager;



    private static final long serialVersionUID = 9204679248304669948L;

    public ZoomManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("Zoom Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;

        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener((ActionEvent e) -> this.zoomIn());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(zoomInButton, c);

        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener((ActionEvent e) -> this.zoomOut());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(zoomOutButton, c);
    }

    private void zoomOut() {
        System.out.println("OUT");
    }

    private void zoomIn() {
        System.out.println("IN");
    }


    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }
}


