package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

public class HorizontalBarGraph extends DrawBasicGraph implements IGraph {

	Model model;
	Graphics2D g2D;

	public HorizontalBarGraph(Model model) {
		super(model);
		this.model = model;

	}

	public void setCoordSystem(Graphics2D g2D) {
		g2D.translate(maxX, maxY);
		g2D.drawString("x", maxX - 10, maxY + 10);
		g2D.drawString("y", 0, -maxY + 10);
		// g2D.drawString("0", -maxX + 10, 10);

		g2D.scale(1, -1);
		g2D.drawLine(-width / 2, -height / 2, width / 2, -height / 2);
		g2D.drawLine(0, -height / 2, 0, height / 2);
	}

	public void setAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		// Draw column axis
		int count = 1;
		String value;
		while (count < model.getDataset().size() + 1) {
			value = Integer.toString(count);
			g2D.drawString(value, -15, maxY + tickY / 4 - count * tickY / 2);
			count++;
		}

		// mark the y-axis
		/*
		 * count = -maxY; while (count < maxY + 1) { if (count != 0) { value =
		 * Integer.toString(-count); g2D.drawString(value, -maxX + 5, count);
		 * g2D.drawLine(-maxX - 5, -count, -maxX + 5, -count); } count = count +
		 * tickY; }
		 */
	}

	public void setGraph(Graphics2D g2D) {
		model.loadPoints();
		int count = -maxY + tickY / 4;

		// load all points in dataset
		for (Model.Point point : model.loadPointList()) {
			// draw X values
			if (point.xValue > 0 && point.yValue > 0) {
				g2D.setColor(Color.RED);
				g2D.fillRect(0, count, (int) point.xValue, tickY / 4);
				g2D.setColor(Color.BLUE);
				g2D.fillRect((int) point.xValue, count, (int) point.yValue,
						tickY / 4);
			} else if (point.xValue > 0 && point.yValue < 0) {
				g2D.setColor(Color.RED);
				g2D.fillRect(0, count, (int) point.xValue, tickY / 4);
				g2D.setColor(Color.BLUE);
				g2D.fillRect((int) point.yValue, count, -(int) point.yValue,
						tickY / 4);
			} else if (point.xValue < 0 && point.yValue > 0) {
				g2D.setColor(Color.RED);
				g2D.fillRect((int) point.xValue, count, -(int) point.xValue,
						tickY / 4);
				g2D.setColor(Color.BLUE);
				g2D.fillRect(0, count, (int) point.yValue, tickY / 4);
			} else {
				g2D.setColor(Color.RED);
				g2D.fillRect((int) point.xValue, count, -(int) point.xValue,
						tickY / 4);
				g2D.setColor(Color.BLUE);
				g2D.fillRect((int) (point.xValue + point.yValue), count,
						-(int) point.yValue, tickY / 4);
			}
			count += 2 * tickY / 4;
		}
	}

	@Override
	public void setDataSet(IDataSet ds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g, JPanel panel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProperties(Properties p) {
		// TODO Auto-generated method stub

	}
}
