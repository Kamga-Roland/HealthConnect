package com.iot.healthconnect.ui;

import com.iot.healthconnect.entity.DeviceEntity;
import com.iot.healthconnect.entity.DeviceSettings;
import com.iot.healthconnect.model.*;
import com.iot.healthconnect.repository.DeviceRepository;
import com.iot.healthconnect.service.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame {

    private DefaultListModel<IoTDevice> deviceModel;
    private final SettingsService settingsService;
    private final DeviceRepository deviceRepository;

    private JList<IoTDevice> deviceList;
    private JPanel contentPanel;

    private DevicePanel currentPanel;

    private final Map<IoTDevice, DataSimulator> simulators = new HashMap<>();

    public MainWindow(SettingsService settingsService, DeviceRepository deviceRepository) {
        this.settingsService = settingsService;
        this.deviceRepository = deviceRepository;

        setTitle("HealthConnect – IoT Dashboard");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
        initAutoRefresh();
    }

    private void initUI() {

        deviceModel = new DefaultListModel<>();
        deviceList = new JList<>(deviceModel);

        deviceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        deviceList.setFixedCellHeight(40);
        deviceList.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JScrollPane leftPane = new JScrollPane(deviceList);
        leftPane.setPreferredSize(new Dimension(250, 0));

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("Sélectionnez un IoT dans la liste"), BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, contentPanel);
        split.setDividerLocation(250);
        split.setContinuousLayout(true);

        add(split, BorderLayout.CENTER);

        JButton addDeviceBtn = new JButton("Ajouter un appareil");
        addDeviceBtn.addActionListener(e -> openAddDeviceDialog());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(addDeviceBtn);

        add(topPanel, BorderLayout.NORTH);

        deviceList.addListSelectionListener(e -> {
            IoTDevice selected = deviceList.getSelectedValue();
            if (selected != null) {
                contentPanel.removeAll();
                currentPanel = new DevicePanel(selected, settingsService);
                currentPanel.setSimulator(simulators.get(selected));
                contentPanel.add(currentPanel, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });
    }

    private void openAddDeviceDialog() {

        String[] types = {"Tensiomètre", "Oxymètre", "Glucomètre"};

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Choisissez un type d'appareil :",
                "Nouvel appareil",
                JOptionPane.QUESTION_MESSAGE,
                null,
                types,
                types[0]
        );

        if (choice == null) return;

        askThresholdsForNewDevice(choice);
    }

    private void askThresholdsForNewDevice(String type) {

        double suggestedMin;
        double suggestedMax;

        switch (type) {
            case "Tensiomètre" -> {
                suggestedMin = 110;
                suggestedMax = 130;
            }
            case "Oxymètre" -> {
                suggestedMin = 92;
                suggestedMax = 98;
            }
            case "Glucomètre" -> {
                suggestedMin = 70;
                suggestedMax = 140;
            }
            default -> {
                suggestedMin = 0;
                suggestedMax = 100;
            }
        }

        JTextField minField = new JTextField(String.valueOf(suggestedMin));
        JTextField maxField = new JTextField(String.valueOf(suggestedMax));

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Seuil minimum :"));
        panel.add(minField);
        panel.add(new JLabel("Seuil maximum :"));
        panel.add(maxField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Définir les seuils pour " + type, JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) return;

        double min = Double.parseDouble(minField.getText());
        double max = Double.parseDouble(maxField.getText());

        createDevice(type, min, max);
    }

    private void createDevice(String type, double min, double max) {

        long count = deviceRepository.count() + 1;
        String baseName = switch (type) {
            case "Tensiomètre" -> "Tensiomètre #";
            case "Oxymètre" -> "Oxymètre #";
            case "Glucomètre" -> "Glucomètre #";
            default -> "Device #";
        };

        String name = baseName + count;

        IoTDevice device = switch (type) {
            case "Tensiomètre" -> new Tensiometre(name, true);
            case "Oxymètre" -> new Oxymetre(name, true);
            case "Glucometre", "Glucomètre" -> new Glucometre(name, true);
            default -> null;
        };

        if (device == null) return;

        DeviceEntity entity = new DeviceEntity();
        entity.setName(device.getName());
        entity.setType(device.getType());
        deviceRepository.save(entity);

        DeviceSettings settings = settingsService.createSettingsWithThresholds(device, min, max);

        addDevice(device);
        startSimulator(device, settings);
    }

    private void startSimulator(IoTDevice device, DeviceSettings settings) {

        DataSimulator sim = null;

        if (device instanceof Tensiometre t) {
            sim = new TensiometreSimulator(t, settings);
        }
        if (device instanceof Oxymetre o) {
            sim = new OxymetreSimulator(o, settings);
        }
        if (device instanceof Glucometre g) {
            sim = new GlucometreSimulator(g, settings);
        }

        if (sim != null) {
            simulators.put(device, sim);
            sim.start();
        }
    }

    private void initAutoRefresh() {
        Timer timer = new Timer(500, e -> {
            IoTDevice selected = deviceList.getSelectedValue();
            if (selected != null && currentPanel != null) {
                currentPanel.updateDevice(selected);
            }
        });
        timer.start();
    }

    public void addDevice(IoTDevice device) {
        deviceModel.addElement(device);
    }
}
