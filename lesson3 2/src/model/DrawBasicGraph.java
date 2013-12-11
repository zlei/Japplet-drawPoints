package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class DrawBasicGraph {
	Model model;

	/** The instantiating applet's width. */
	public int width;

	/** The instantiating applet's height. */
	public int height;

	// set scale size
	private float scaleX;

	private float scaleY;
	/** x-axis tick marks. */
	public int tickX;

	/** y-axis tick marks. */
	public int tickY;

	/** Maximum x-axis value. */
	public int maxX;

	/** Maximum y-axis value. */
	public int maxY;

	/** Plot type */
	private int type = 0;

	private boolean setgrid = false;

	private boolean setaxis = false;

	private boolean setTrendline = false;

	private Graphics2D g2D;

	private CartesianGraph drawCartesian;

	private ColumnGraph drawColumn;

	private DrawTrendline drawTrendline;

	/*
	 * public DrawBasicGraph(boolean setgrid, boolean setaxis, boolean
	 * setTrendline, Graphics2D g2D) { this.setgrid = setgrid; this.setaxis =
	 * setaxis; this.setTrendline = setTrendline; this.g2D = g2D; }
	 */
	public DrawBasicGraph(Model model) {
		this.model = model;
	}

	public void paint(Graphics2D g2D) {
		width = 500;
		height = 500;
		// width = this.getSize().width;
		// height = this.getSize().height;

		// Hard coding the major subdivisions on each axis.
		// setRange();
		// Hard coding the major subdivisions on each axis.
		this.g2D = g2D;
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
		if (setTrendline) {
			drawTrendline(g2D);
		}
		setGraph(g2D);
	}

	public void drawTrendline(Graphics2D g2D) {

	}

	public void setGraph(Graphics2D g2D) {

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
	public void setScale(int divX, int divY) {
		tickX = width / divX;
		tickY = height / divY;
		maxX = width / 2;
		maxY = height / 2;
	}

	public void setRange() {
		model.getRange();
		scaleX = model.getRangeX() / width;
		scaleY = model.getRangeY() / height;
	}

	/**
	 * Helper method which draws the x and y axis..
	 * 
	 * @param g2D
	 *            the graphic context used.
	 * 
	 */
	public void setCoordSystem(Graphics2D g2D) {
		// default and cartisan system
		g2D.translate(maxX, maxY);

		g2D.drawString("x", maxX - 10, 10);
		g2D.drawString("y", -10, -maxY + 10);
		g2D.drawString("0", -15, 15);

		g2D.scale(1, -1);
		g2D.drawLine(-width / 2, 0, width / 2, 0);
		g2D.drawLine(0, -height / 2, 0, height / 2);
	}

	/**
	 * Helper method to set Background grid
	 * 
	 * @param g2D
	 */
	public void setBackGrid(Graphics2D g2D) {

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
	public void setAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);

		// Draw Cartesian for default or cartesian coordinate
		// mark the x-axis
		float count = -maxX;
		String value;
		while (count < maxX + 1) {
			if (count != 0) {
				value = Integer.toString((int) count);
				g2D.drawString(value, (int) count - 5, 15);
				g2D.drawLine((int) (-count), -5, (int) (-count), 5);
			}
			count = count + tickX;
		}

		// mark the y-axis
		count = -maxY;
		while (count < maxY + 1) {
			if (count != 0) {
				value = Integer.toString((int) (-count));
				g2D.drawString(value, -40, (int) count + 5);
				g2D.drawLine(-5, (int) (-count), 5, (int) (-count));
			}
			count = count + tickY;
		}
	}
}
