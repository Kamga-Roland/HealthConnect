package com.iot.healthconnect.ui;

import com.iot.healthconnect.model.Glucometre;
import com.iot.healthconnect.model.IoTDevice;
import com.iot.healthconnect.model.Oxymetre;
import com.iot.healthconnect.model.Tensiometre;

import javax.swing.*;
import java.awt.*;

public class DevicePanel extends JPanel {

    private final JLabel statusLabel;
    private final AlarmLabel alarmLabel;
    private final LiveChartPanel chartPanel;

    public DevicePanel(IoTDevice device) {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel(device.getName());
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JPanel header = new JPanel(new BorderLayout());
        header.add(title, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        statusLabel = new JLabel("Statut : " + device.getStatus());
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        alarmLabel = new AlarmLabel();

        infoPanel.add(statusLabel);
        infoPanel.add(alarmLabel);

        add(infoPanel, BorderLayout.WEST);

        // Graphique dynamique
        chartPanel = new LiveChartPanel("Évolution", "Valeur");
        add(chartPanel, BorderLayout.CENTER);
    }

    // Mise à jour automatique
    public void updateDevice(IoTDevice device) {

        statusLabel.setText("Statut : " + device.getStatus());

        if (device instanceof Tensiometre t) {
            chartPanel.addValue(t.getSystolic());

            if (t.getSystolic() > 150 || t.getDiastolic() > 95) {
                alarmLabel.setAlarm(true, "Alarme : Tension élevée !");
            } else {
                alarmLabel.setAlarm(false, "");
            }
        }
        if (device instanceof Oxymetre o) {
            chartPanel.addValue(o.getSpo2());

            if (o.getSpo2() < 92) {
                alarmLabel.setAlarm(true, "Alarme : hypoxie !");
            } else  {
                alarmLabel.setAlarm(false, "");
            }
        }
        if (device instanceof Glucometre g) {
            chartPanel.addValue(g.getGlucose());

            if (g.getGlucose() > 180) {
                alarmLabel.setAlarm(true, "Alarme : Hyperglycémie !");
            } else if (g.getGlucose() < 60) {
                alarmLabel.setAlarm(true, "Alarme : Hypoglycémie !");
            } else {
                alarmLabel.setAlarm(false, "");
            }
        }
    }
}
