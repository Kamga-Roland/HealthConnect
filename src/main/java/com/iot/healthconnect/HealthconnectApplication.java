package com.iot.healthconnect;

import com.formdev.flatlaf.FlatLightLaf;
import com.iot.healthconnect.model.Glucometre;
import com.iot.healthconnect.model.Oxymetre;
import com.iot.healthconnect.model.Tensiometre;
import com.iot.healthconnect.service.GlucometreSimulator;
import com.iot.healthconnect.service.OxymetreSimulator;
import com.iot.healthconnect.service.TensiometreSimulator;
import com.iot.healthconnect.ui.MainWindow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class HealthconnectApplication {

    public static void main(String[] args) {

        // Empêche les erreurs Headless
        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(HealthconnectApplication.class, args);

        // UI moderne
        FlatLightLaf.setup();

        // Lance l’interface Swing
        SwingUtilities.invokeLater(() -> {

            MainWindow window = new MainWindow();

            // Création des appareilles
            Tensiometre t1 = new Tensiometre("Tensiomètre #1", true);
            Glucometre g1 = new Glucometre("Glucomètre #1", true);
            Oxymetre o1 = new Oxymetre("Oxymetre #1", true);
            window.addDevice(t1);
            window.addDevice(o1);
            window.addDevice(g1);


            // Simulation des données
            TensiometreSimulator simT = new TensiometreSimulator(t1);
            GlucometreSimulator simG = new GlucometreSimulator(g1);
            OxymetreSimulator simO = new OxymetreSimulator(o1);
            simT.start();
            simO.start();
            simG.start();

            // Affiche la fenêtre
            window.setVisible(true);
        });
    }
}
