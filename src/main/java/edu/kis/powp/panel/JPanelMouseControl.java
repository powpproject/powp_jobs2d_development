package edu.kis.powp.panel;


import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JPanelMouseControl {
    private Application application;
    private JPanel jPanel;
    private DriverManager driverManager;

    public JPanelMouseControl(Application application, DriverManager driverManager) {
        this.application = application;
        this.driverManager=driverManager;
    }

    public void setupPanel(){
        jPanel=application.getFreePanel();


        jPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getButton()==1){
                    driverManager.getCurrentDriver().setPosition((int) (e.getX()-(jPanel.getSize().getWidth()/2)),(int)(e.getY()-(jPanel.getSize().getHeight()/2)));

                }
                else if(e.getButton()==3){
                    driverManager.getCurrentDriver().operateTo((int)(e.getX()-(jPanel.getSize().getWidth()/2)),(int)(e.getY()-(jPanel.getSize().getHeight()/2)));
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
