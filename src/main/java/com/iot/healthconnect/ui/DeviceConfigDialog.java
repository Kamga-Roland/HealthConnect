package com.iot.healthconnect.ui;

import javax.swing.*;
import java.awt.*;

import com.iot.healthconnect.entity.DeviceSettings;
import com.iot.healthconnect.model.IoTDevice;
import com.iot.healthconnect.service.SettingsService;

public class DeviceConfigDialog extends JDialog {

    private JTextField minField;
    private JTextField maxField;
    private JTextField intervalField;

    private final SettingsService settingsService;
    private final DeviceSettings settings;

    public DeviceConfigDialog(IoTDevice device, SettingsService settingsService) {

        this.settingsService = settingsService;
        this.settings = settingsService.loadSettings(device);

        setTitle("Configuration – " + device.getName());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        form.add(new JLabel("Seuil min :"));
        minField = new JTextField(settings.getMinValue() != null ? settings.getMinValue().toString() : "");
        form.add(minField);

        form.add(new JLabel("Seuil max :"));
        maxField = new JTextField(settings.getMaxValue() != null ? settings.getMaxValue().toString() : "");
        form.add(maxField);

        form.add(new JLabel("Intervalle simulation (ms) :"));
        intervalField = new JTextField(settings.getSimulationInterval() != null ? settings.getSimulationInterval().toString() : "3000");
        form.add(intervalField);

        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");

        buttons.add(saveBtn);
        buttons.add(cancelBtn);
        add(buttons, BorderLayout.SOUTH);

        saveBtn.addActionListener(e -> {
            settings.setMinValue(parseDouble(minField.getText()));
            settings.setMaxValue(parseDouble(maxField.getText()));
            settings.setSimulationInterval(parseInt(intervalField.getText(), 3000));
            settingsService.saveSettings(settings);
            dispose();
        });

        cancelBtn.addActionListener(e -> dispose());
    }

    private Double parseDouble(String s) {
        try { return Double.parseDouble(s); }
        catch (Exception e) { return null; }
    }

    private Integer parseInt(String s, int defaultValue) {
        try { return Integer.parseInt(s); }
        catch (Exception e) { return defaultValue; }
    }
}
