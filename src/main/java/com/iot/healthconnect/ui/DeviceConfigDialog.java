package com.iot.healthconnect.ui;

import javax.swing.*;
import java.awt.*;

import com.iot.healthconnect.entity.DeviceSettings;
import com.iot.healthconnect.model.*;
import com.iot.healthconnect.service.SettingsService;

public class DeviceConfigDialog extends JDialog {

    private JTextField nameField;
    private JCheckBox subscribedBox;

    private JTextField minField;
    private JTextField maxField;

    private JTextField intervalField;

    private JComboBox<String> themeBox;
    private JComboBox<String> iconBox;

    private final SettingsService settingsService;
    private final DeviceSettings settings;

    public DeviceConfigDialog(IoTDevice device, SettingsService settingsService) {

        this.settingsService = settingsService;
        this.settings = settingsService.loadSettings(device);

        setTitle("Configuration – " + device.getName());
        setSize(450, 450);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        // COMMON SETTINGS
        form.add(new JLabel("Nom du device :"));
        nameField = new JTextField(device.getName());
        form.add(nameField);

        form.add(new JLabel("Abonnement actif :"));
        subscribedBox = new JCheckBox("", device.isSubscribed());
        form.add(subscribedBox);

        // ALARM THRESHOLDS
        form.add(new JLabel("Seuil min :"));
        minField = new JTextField(settings.getMinValue() != null ? settings.getMinValue().toString() : "");
        form.add(minField);

        form.add(new JLabel("Seuil max :"));
        maxField = new JTextField(settings.getMaxValue() != null ? settings.getMaxValue().toString() : "");
        form.add(maxField);

        // SIMULATION SPEED
        form.add(new JLabel("Intervalle simulation (ms) :"));
        intervalField = new JTextField(settings.getSimulationInterval().toString());
        form.add(intervalField);

        // COLOR THEME
        form.add(new JLabel("Thème couleur :"));
        themeBox = new JComboBox<>(new String[]{"light", "dark", "blue", "green"});
        themeBox.setSelectedItem(settings.getColorTheme());
        form.add(themeBox);

        // ICON SELECTION
        form.add(new JLabel("Icône :"));
        iconBox = new JComboBox<>(new String[]{"default", "heart", "drop", "scale", "glucose"});
        iconBox.setSelectedItem(settings.getIconName());
        form.add(iconBox);

        add(form, BorderLayout.CENTER);

        // BUTTONS
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");

        buttons.add(saveBtn);
        buttons.add(cancelBtn);
        add(buttons, BorderLayout.SOUTH);

        // ACTIONS
        saveBtn.addActionListener(e -> {

            device.setName(nameField.getText());
            device.setSubscribed(subscribedBox.isSelected());

            settings.setMinValue(parseDouble(minField.getText()));
            settings.setMaxValue(parseDouble(maxField.getText()));
            settings.setSimulationInterval(Integer.parseInt(intervalField.getText()));
            settings.setColorTheme((String) themeBox.getSelectedItem());
            settings.setIconName((String) iconBox.getSelectedItem());

            settingsService.saveSettings(settings);

            dispose();
        });

        cancelBtn.addActionListener(e -> dispose());
    }

    private Double parseDouble(String s) {
        try { return Double.parseDouble(s); }
        catch (Exception e) { return null; }
    }
}
