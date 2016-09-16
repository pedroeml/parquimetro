package ui;


import java.awt.Container;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class BarChart {
	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	
	public BarChart(String title, String y_axis_label, String x_axis_label) {
		dataset = new DefaultCategoryDataset();
		chart = ChartFactory.createBarChart(title, x_axis_label, y_axis_label, dataset, PlotOrientation.VERTICAL, false, true, false);
	}

	public void addData(double value, String rowKey, String columnKey) {
		dataset.setValue(value, rowKey, columnKey);
	}
	
	private ChartPanel getChartPanel(int width, int height) {
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(width, height));
		return chartPanel;
	}
	
	private ApplicationFrame getApplicationFrame(int width, int height) {
		ApplicationFrame appFrame = new ApplicationFrame(chart.getTitle().getText());
		appFrame.setContentPane(this.getChartPanel(width, height));
		return appFrame;
	}
	
	public Container getPane(int x, int y, int width, int height) {
		Container pane = this.getApplicationFrame(width, height).getContentPane();
		pane.setBounds(x, y, width, height);
		return pane;
	}
}