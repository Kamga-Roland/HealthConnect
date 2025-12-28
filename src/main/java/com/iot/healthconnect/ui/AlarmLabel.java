package com.iot.healthconnect.ui;

import javax.swing.*;
import java.awt.*;

public class AlarmLabel extends JLabel {

    private boolean alarm = false;
    private boolean visibleState = true;

    public AlarmLabel() {
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setForeground(Color.GREEN);
        setText("Aucune alarme");
        initBlinkTimer();
    }

    private void initBlinkTimer() {
        Timer timer = new Timer(500, e -> {
            if (alarm) {
                visibleState = !visibleState;
                setForeground(visibleState ? Color.RED : Color.DARK_GRAY);
            }
        });
        timer.start();
    }

    public void setAlarm(boolean state, String message) {
        this.alarm = state;
        if (!state) {
            setForeground(Color.GREEN);
            setText("Aucune alarme");
        } else {
            setText(message);
        }
    }
}
