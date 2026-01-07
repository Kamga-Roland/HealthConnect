package com.iot.healthconnect;

import com.formdev.flatlaf.FlatLightLaf;
import com.iot.healthconnect.repository.DeviceRepository;
import com.iot.healthconnect.service.SettingsService;
import com.iot.healthconnect.ui.MainWindow;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
public class HealthconnectApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(HealthconnectApplication.class, args);
    }

    @Bean
    public CommandLineRunner startUi(ApplicationContext ctx) {
        return args -> {

            FlatLightLaf.setup();

            SettingsService settingsService = ctx.getBean(SettingsService.class);
            DeviceRepository deviceRepository = ctx.getBean(DeviceRepository.class);

            SwingUtilities.invokeLater(() -> {
                MainWindow window = new MainWindow(settingsService, deviceRepository);
                window.setVisible(true);
            });
        };
    }
}
