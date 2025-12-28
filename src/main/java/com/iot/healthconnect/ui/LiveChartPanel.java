package com.iot.healthconnect.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class LiveChartPanel extends JPanel {

    private TimeSeries series;

    public LiveChartPanel(String title, String yLabel) {

        setLayout(new BorderLayout());

        series = new TimeSeries("Valeur");

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                "Temps",
                yLabel,
                dataset,
                false,
                false,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    // Méthode appelée à chaque nouvelle valeur
    public void addValue(double value) {
        series.addOrUpdate(new Millisecond(), value);
    }
}
