package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Model;

public class MyBasicPanel extends JPanel {

	Model model;

	/** The instantiating applet's width. */
	private int width;

	/** The instantiating applet's height. */
	private int height;

	/** x-axis tick marks. */
	private int tickX;

	/** y-axis tick marks. */
	private int tickY;

	/** Maximum x-axis value. */
	private int maxX;

	/** Maximum y-axis value. */
	private int maxY;

	/** Plot type */
	private int type;

	private boolean setgrid = false;

	private boolean setaxis = false;

	private boolean setTrendline = false;

	public MyBasicPanel(Model model, int type) {
		this.model = model;
		this.type = type;
	}

	/**
	 * Draws the defined function.
	 * 
	 * @param g
	 *            the components graphics context.
	 * @return void (We don't normally indicate "voids"!)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		// width = this.getSize().width;
		// height = this.getSize().height;

		width = 500;
		height = 500;
		// Hard coding the major subdivisions on each axis.
		setScale(10, 10);

		setCoordSystem(g2D);
		if (setgrid) {
			setBackGrid(g2D);
		}
		if (setaxis) {
			g2D.scale(1, -1);
			setAxis(g2D);
			g2D.scale(1, -1);
		}
		if (setTrendline && type == 1) {
			drawTrendLine(g2D);
		}

		switch (type) {
		case 0:
			break;
		case 1:
			graphCartesian(g2D);
			break;
		case 2:
			graphColumn(g2D);
			break;
		}
	}

	/**
	 * To determine which type is chosen
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Show background grid or not
	 */
	public void changeGrid() {
		setgrid = !setgrid;
	}

	/**
	 * Show axis or not
	 */
	public void changeAxis() {
		setaxis = !setaxis;
	}

	public void changeTrendline() {
		setTrendline = !setTrendline;
	}

	/**
	 * Helper method which sets the scale on each axis..
	 * 
	 * @param divX
	 *            x-axis scaling parameter.
	 * @param divY
	 *            y-axis scaling parameter.
	 */
	private void setScale(int divX, int divY) {
		tickX = width / divX;
		tickY = height / divY;
		maxX = width / 2;
		maxY = height / 2;
	}

	/**
	 * Helper method which draws the x and y axis..
	 * 
	 * @param g2D
	 *            the graphic context used.
	 * 
	 */
	private void setCoordSystem(Graphics2D g2D) {
		// draw coordsystem for column
		if (this.type == 2) {
			g2D.translate(maxX, maxY);
			g2D.drawString("x", maxX - 10, 10);
			g2D.drawString("y", -maxX + 10, -maxY + 10);
			g2D.drawString("0", -maxX + 10, 10);

			g2D.scale(1, -1);
			g2D.drawLine(-width / 2, 0, width / 2, 0);
			g2D.drawLine(-width / 2, -height / 2, -width / 2, height / 2);

		}

		// default and cartisan system
		else {
			g2D.translate(maxX, maxY);

			g2D.drawString("x", maxX - 10, 10);
			g2D.drawString("y", -10, -maxY + 10);
			g2D.drawString("0", -10, 10);

			g2D.scale(1.0, -1.0);
			g2D.drawLine(-width / 2, 0, width / 2, 0);
			g2D.drawLine(0, -height / 2, 0, height / 2);

		}
	}

	/**
	 * Helper method to set Background grid
	 * 
	 * @param g2D
	 */
	private void setBackGrid(Graphics2D g2D) {

		g2D.setColor(Color.GRAY);
		int count = -maxX;
		/*
		 * // mark the x-axis // to run one more time on max while (count < maxX
		 * + 1) { g2D.drawLine(count, -maxY, count, maxY); count = count +
		 * tickX; }
		 */
		// mark the y-axis, only show horizontal lines
		count = -maxY;
		while (count < maxY + 1) {
			g2D.drawLine(-maxX, count, maxX, count);
			// g2D.drawLine(-maxX, -count, maxX, -count);
			count = count + tickY;
		}
	}

	/**
	 * Helper method to set Axis
	 * 
	 * @param g2D
	 */
	private void setAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);

		// Draw column axis
		if (this.type == 2) {
			int count = 1;
			String value;
			while (count < model.getDataset().size() + 1) {
				value = Integer.toString(count);
				g2D.drawString(value, -maxX + count * tickX, 5);
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
		// Draw Cartesian for default or cartesian coordinate
		// mark the x-axis
		else {
			int count = -maxX;
			String value;
			while (count < maxX + 1) {
				if (count != 0) {
					value = Integer.toString(count);
					g2D.drawString(value, count - 5, 15);
					g2D.drawLine(-count, -5, -count, 5);
				}
				count = count + tickX;
			}

			// mark the y-axis
			count = -maxY;
			while (count < maxY + 1) {
				if (count != 0) {
					value = Integer.toString(count);
					g2D.drawString(value, -40, count + 5);
					g2D.scale(1, -1);
					g2D.drawLine(-5, count, 5, count);
					g2D.scale(1, -1);
				}
				count = count + tickY;
			}
		}
	}

	/**
	 * To draw Cartesian plot
	 * 
	 * @param g2D
	 */
	private void graphCartesian(Graphics2D g2D) {
		g2D.setColor(Color.RED);
		model.loadPoints();
		for (Model.Point point : model.loadPointList()) {
			g2D.fillRect((int) point.xValue, (int) point.yValue, 4, 4);
		}
	}

	/**
	 * To draw column plot
	 * 
	 * @param g2D
	 */
	private void graphColumn(Graphics2D g2D) {
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

	private void drawTrendLine(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		model.linearRegression();
		g2D.scale(1, -1);
		g2D.drawString(model.getLRFormula(), -maxX + 30, -maxY + 30);
		g2D.scale(1, -1);
		Model.Point p1 = model.getStartPoint();
		Model.Point p2 = model.getEndPoint();
		g2D.drawLine((int) p1.xValue, (int) p1.yValue, (int) p2.xValue,
				(int) p2.yValue);
	}
}
