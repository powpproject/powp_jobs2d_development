package edu.kis.powp.panel;


import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JPanelMouseControl {
    private static JPanel jPanel = null;
    private static DriverManager driverManager = null;
    private static int LEFT_MOUSE_BUTTON = 1;
    private static int RIGHT_MOUSE_BUTTON = 3;

    public static void engage(JPanel jPanel, DriverManager driverManager)
    {
        setjPanel(jPanel);
        setDriverManager(driverManager);
        startListener();
    }

    private static void setjPanel(JPanel currentJPanel) {
        jPanel = currentJPanel;
    }

    private static void setDriverManager(DriverManager currentDriverManager) {
        driverManager = currentDriverManager;
    }

    private static void startListener() {
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
