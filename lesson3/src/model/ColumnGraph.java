package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

public class ColumnGraph extends DrawBasicGraph implements IGraph{

	Model model;

	Graphics2D g2D;

	public ColumnGraph(Model model) {
		super(model);
		this.model = model;
	}

	public void setCoordSystem(Graphics2D g2D) {
		g2D.translate(maxX, maxY);
		g2D.drawString("x", maxX - 10, 10);
		g2D.drawString("y", -maxX + 10, -maxY + 10);
		g2D.drawString("0", -maxX + 10, 10);

		g2D.scale(1, -1);
		g2D.drawLine(-width / 2, 0, width / 2, 0);
		g2D.drawLine(-width / 2, -height / 2, -width / 2, height / 2);
	}

	public void setAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		// Draw column axis
		int count = 1;
		String value;
		while (count < model.getDataset().size() + 1) {
			value = Integer.toString(count);
			g2D.drawString(value, -maxX + count * tickX, 15);
			count++;
		}

		// mark the y-axis
		count = -maxY;
		while (count < maxY + 1) {
			if (count != 0) {
				value = Integer.toString(-count);
				g2D.drawString(value, -maxX + 5, count);
				g2D.drawLine(-maxX - 5, -count, -maxX + 5, -count);
			}
			count = count + tickY;
		}
	}

	public void setGraph(Graphics2D g2D) {
		model.loadPoints();
		int xCount = -maxX + 3 * tickX / 4;
		int yCount = -maxX + tickX;

		// load all points in dataset
		for (Model.Point point : model.loadPointList()) {
			// draw X values
			g2D.setColor(Color.RED);
			if (point.xValue > 0)
				g2D.fillRect(xCount, 0, tickX / 4, (int) point.xValue);
			else
				g2D.fillRect(xCount, (int) point.xValue, tickX / 4,
						-(int) point.xValue);
			xCount += tickX;
			// draw Y values
			g2D.setColor(Color.BLUE);
			if (point.yValue > 0)
				g2D.fillRect(yCount, 0, tickX / 4, (int) point.yValue);
			if (point.yValue < 0)
				g2D.fillRect(yCount, (int) point.yValue, tickX / 4,
						-(int) point.yValue);
			yCount += tickX;
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
