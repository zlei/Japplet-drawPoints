package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

import view.MyBasicPanel;
import dataset.IDataSet;
import dataset.IGraph;

public class CartesianGraph extends DrawBasicGraph implements IGraph {

	Model model;

	Graphics2D g2D;

	public CartesianGraph() {
	}

	public void drawTrendline(Graphics2D g2D) {
		this.g2D = g2D;
		g2D.setColor(Color.BLACK);
		if (model.getDataSet().linearRegression()) {
			g2D.scale(1, -1);
			g2D.drawString(model.getDataSet().getLRFormula(), -220, -220);
			g2D.scale(1, -1);

			MyPoint p1 = model.getDataSet().getStartPoint();
			MyPoint p2 = model.getDataSet().getEndPoint();
			g2D.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
		}
	}

	@Override
	public void setDataSet(IDataSet ds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g, JPanel panel) {
		// TODO Auto-generated method stub
		MyBasicPanel myPanel = (MyBasicPanel) panel;
		model = myPanel.getModel();
		super.draw(g, panel);
		this.g2D = (Graphics2D) g;
		if (model.getDataSet().size() != 0)
			for (MyPoint point : model.getDataSet().loadPointList()) {
				point.x = point.x;
				point.y = point.y;
				g2D.fillRect((int) point.x, (int) point.y, 4, 4);
			}
	}

	@Override
	public void setProperties(Properties p) {
		// TODO Auto-generated method stub

	}
}
