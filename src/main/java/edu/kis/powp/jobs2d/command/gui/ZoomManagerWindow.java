package edu.kis.powp.jobs2d.command.gui;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ZoomManagerWindow extends JFrame implements WindowComponent {
    //private DriverCommandManager commandManager;
    private int scale=1;
    private static final long serialVersionUID = 9204679248304669948L;

    public ZoomManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("Zoom Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();


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
        DrawerFeature.getDrawerController().clearPanel();
        scale*=2;
        System.out.println(DriverFeature.getDriverManager().getCurrentDriver().toString());
        Job2dDriver driver;
        DrawPanelController drawerController = DrawerFeature.getDrawerController();
        if(DriverFeature.getDriverManager().getCurrentDriver().toString().equals("2d device simulator - basic")){
            driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic",scale);
        }else{
            driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special",scale);
        }
        DriverFeature.getDriverManager().setCurrentDriver(driver);
        //FiguresJoe.figureScript1(DriverFeature.getDriverManager().getCurrentDriver());

    }

    private void zoomIn() {
        DrawerFeature.getDrawerController().clearPanel();
        scale/=2;
        System.out.println(DriverFeature.getDriverManager().getCurrentDriver().toString());
        Job2dDriver driver;
        DrawPanelController drawerController = DrawerFeature.getDrawerController();
        if(DriverFeature.getDriverManager().getCurrentDriver().toString().equals("2d device simulator - basic")){
            driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic",scale);
        }else{
            driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special",scale);
        }
        DriverFeature.getDriverManager().setCurrentDriver(driver);
        //FiguresJoe.figureScript1(DriverFeature.getDriverManager().getCurrentDriver());
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


