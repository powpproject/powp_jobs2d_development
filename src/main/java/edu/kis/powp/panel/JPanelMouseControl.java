package edu.kis.powp.panel;


import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JPanelMouseControl {
    private JPanel jPanel = null;
    private DriverManager driverManager = null;
    static private JPanelMouseControl instance = null;
    private int LEFT_MOUSE_BUTTON = 1;
    private int RIGHT_MOUSE_BUTTON = 3;

    private JPanelMouseControl() {
        super();
    }

    public static JPanelMouseControl getInstance() {
        if (instance == null)
            instance = new JPanelMouseControl();
        return instance;
    }

    public JPanelMouseControl setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
        return instance;
    }

    public JPanelMouseControl setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
        return instance;
    }

    public void startListener() {
        jPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == LEFT_MOUSE_BUTTON) {
                    driverManager.getCurrentDriver().setPosition(e.getX() - jPanel.getBounds().width / 2, e.getY() - jPanel.getBounds().height / 2);
                } else if (e.getButton() == RIGHT_MOUSE_BUTTON) {
                    driverManager.getCurrentDriver().operateTo(e.getX() - jPanel.getBounds().width / 2, e.getY() - jPanel.getBounds().height / 2);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

    }
}
