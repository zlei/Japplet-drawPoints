package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

public class CartesianGraph extends DrawBasicGraph implements IGraph{

	Model model;

	Graphics2D g2D;

	public CartesianGraph(Model model) {
		super(model);
		this.model = model;
	}

	/**
	 * To draw Cartesian plot
	 * 
	 * @param g2D
	 */
	public void setGraph(Graphics2D g2D) {
		this.g2D = g2D;
		g2D.setColor(Color.RED);
		model.loadPoints();
		for (Model.Point point : model.loadPointList()) {
			point.xValue = point.xValue;
			point.yValue = point.yValue;
			g2D.fillRect((int) point.xValue, (int) point.yValue, 4, 4);
		}
	}

	public void drawTrendline(Graphics2D g2D) {
		DrawTrendline drawTrend = new DrawTrendline(model);
		drawTrend.drawTrendline(g2D);
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
