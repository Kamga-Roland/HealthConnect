package com.iot.healthconnect.ui;

import com.iot.healthconnect.model.IoTDevice;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private DefaultListModel<IoTDevice> deviceModel;
    private JList<IoTDevice> deviceList;
    private JPanel contentPanel;

    private DevicePanel currentPanel; // panneau affiché actuellement

    public MainWindow() {
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

        // Listener
        deviceList.addListSelectionListener(e -> {
            IoTDevice selected = deviceList.getSelectedValue();
            if (selected != null) {
                contentPanel.removeAll();
                currentPanel = new DevicePanel(selected);
                contentPanel.add(currentPanel, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });
    }

    // Timer Swing pour mise à jour automatique
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
