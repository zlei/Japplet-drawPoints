package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;

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

	public MyBasicPanel(int type) {
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

		width = this.getSize().width;
		height = this.getSize().height;

		// Hard coding the major subdivisions on each axis.
		setScale(10, 10);

		setCoordSystem(g2D);
		if (setgrid) {
			setBackGrid(g2D);
		}
		if (setaxis) {
			setAxis(g2D);
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

	public void setType(int type) {
		this.type = type;
	}

	public void changeGrid() {
		setgrid = !setgrid;
	}

	public void changeAxis() {
		setaxis = !setaxis;
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

		g2D.drawString("x", 2 * maxX - 10, maxY - 5);
		g2D.drawString("y", maxX + 5, +10);

		g2D.translate(maxX, maxY);
		g2D.scale(1.0, -1.0);
		g2D.drawLine(-maxX, 0, maxX, 0);
		g2D.drawLine(0, maxY, 0, -maxY);

		// mark the x-axis
		int count = -maxX;
		while (count < maxX) {
			g2D.drawLine(count, -5, count, 5);
			// g2D.drawLine(-count, -5, -count, 5);
			count = count + tickX;
		}

		// mark the y-axis
		count = -maxY;
		while (count < maxY) {
			g2D.drawLine(-5, count, 5, count);
			// g2D.drawLine(-5, -count, 5, -count);
			count = count + tickY;
		}
	}

	private void setBackGrid(Graphics2D g2D) {

		g2D.setColor(Color.WHITE);
		g2D.translate(maxX, maxY);
		g2D.scale(1.0, -1.0);

		// mark the x-axis
		int count = -2 * maxX;
		while (count < 2 * maxX) {
			g2D.drawLine(count, -2 * maxY, count, 2 * maxY);
			// g2D.drawLine(-count, -maxY, -count, maxY);
			count = count + tickX;
		}

		// mark the y-axis
		count = -2 * maxY;
		while (count < 2 * maxY) {
			g2D.drawLine(-2 * maxX, count, 2 * maxX, count);
			// g2D.drawLine(-maxX, -count, maxX, -count);
			count = count + tickY;
		}
	}

	private void setAxis(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		g2D.translate(maxX, maxY);
		g2D.scale(1.0, -1.0);
		g2D.drawString("x", 2 * maxX - 10, maxY - 5);
		g2D.drawString("y", maxX + 5, +10);

		// mark the x-axis
		int count = -2 * maxX;
		String value = Integer.toString(count);
		while (count < 2 * maxX) {
			g2D.drawString(value, count, maxY - 5);
			// g2D.drawLine(-count, -5, -count, 5);
			count = count + tickX;
		}

		// mark the y-axis
		count = -2 * maxY;
		while (count < 2 * maxY) {
			g2D.drawString(value, maxX - 5, count);
			// g2D.drawLine(-5, -count, 5, -count);
			count = count + tickY;
		}
	}

	/**
	 * Helper method which specifies the function to be drawn. The function is
	 * "hardcoded" to be the cosine funtion.
	 * 
	 * @param x
	 *            the unadjusted value passed to the cosine function.
	 * 
	 */
	private double f(double x) {
		x = x / tickX;
		return Math.cos(x);
	}

	/**
	 * Helper method which graphs the function.
	 * 
	 * @param g2D
	 *            the graphics context used.
	 * 
	 */

	private void graphCartesian(Graphics2D g2D) {
		g2D.translate(maxX, maxY);
		g2D.setColor(Color.RED);
		g2D.drawLine(-maxX, 5, maxX, 5);
		// g2D.fillRect((int) model.getCurrentPointX(),
		// (int) model.getCurrentPointY(), 2, 2);

	}

	private void graphColumn(Graphics2D g2D) {
		int y2, x2;
		int pixel = -maxX;
		double x = (double) pixel;
		double y = f(x);
		int y1 = (int) (y * tickY);
		int x1 = pixel;
		for (pixel = -maxX; pixel < maxX; pixel++) {
			x = (double) pixel;
			y = f(x);
			y2 = (int) (y * tickY);
			x2 = pixel;
			g2D.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
	}
}
