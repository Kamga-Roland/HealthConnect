package com.iot.healthconnect.ui;

import com.iot.healthconnect.entity.DeviceSettings;
import com.iot.healthconnect.model.Glucometre;
import com.iot.healthconnect.model.IoTDevice;
import com.iot.healthconnect.model.Oxymetre;
import com.iot.healthconnect.model.Tensiometre;
import com.iot.healthconnect.service.DataSimulator;
import com.iot.healthconnect.service.SettingsService;

import javax.swing.*;
import java.awt.*;

public class DevicePanel extends JPanel {

    private final JLabel statusLabel;
    private final AlarmLabel alarmLabel;
    private final LiveChartPanel chartPanel;

    private final IoTDevice device;
    private final SettingsService settingsService;
    private final DeviceSettings settings;

    private DataSimulator simulator;

    public DevicePanel(IoTDevice device, SettingsService settingsService) {

        this.device = device;
        this.settingsService = settingsService;
        this.settings = settingsService.loadSettings(device);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel(device.getName());
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JPanel header = new JPanel(new BorderLayout());
        header.add(title, BorderLayout.WEST);

        JButton configBtn = new JButton("Configurer");
        configBtn.addActionListener(e -> new DeviceConfigDialog(this.device, this.settingsService).setVisible(true));
        header.add(configBtn, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        JPanel infoPanel = new    JPanel(new GridLayout(0, 1, 5, 5));

        statusLabel = new JLabel("Statut : " + device.getStatus());
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        alarmLabel = new AlarmLabel();

        infoPanel.add(statusLabel);
        infoPanel.add(alarmLabel);

        add(infoPanel, BorderLayout.WEST);

        chartPanel = new LiveChartPanel("Évolution", "Valeur");
        add(chartPanel, BorderLayout.CENTER);
    }

    public void setSimulator(DataSimulator simulator) {
        this.simulator = simulator;
    }

    public void stopSimulation() {
        if (simulator != null) {
            simulator.stop();
            simulator = null;
        }
    }

    public void updateDevice(IoTDevice device) {

        // Recharger les settings depuis la BD
        DeviceSettings fresh = settingsService.loadSettings(device);

        double min = fresh.getMinValue() != null ? fresh.getMinValue() : Double.MIN_VALUE;
        double max = fresh.getMaxValue() != null ? fresh.getMaxValue() : Double.MAX_VALUE;

        statusLabel.setText("Statut : " + device.getStatus());

        if (device instanceof Tensiometre t) {
            chartPanel.addValue(t.getSystolic());
            boolean alarm = t.getSystolic() < min || t.getSystolic() > max;
            alarmLabel.setAlarm(alarm, alarm ? "Alarme : tension hors seuil !" : "");
        }

        if (device instanceof Oxymetre o) {
            chartPanel.addValue(o.getSpo2());
            boolean alarm = o.getSpo2() < min || o.getSpo2() > max;
            alarmLabel.setAlarm(alarm, alarm ? "Alarme : SpO₂ hors seuil !" : "");
        }

        if (device instanceof Glucometre g) {
            chartPanel.addValue(g.getGlucose());
            boolean alarm = g.getGlucose() < min || g.getGlucose() > max;
            alarmLabel.setAlarm(alarm, alarm ? "Alarme : glycémie hors seuil !" : "");
        }
    }

}
