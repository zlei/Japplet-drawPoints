package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class DrawTrendline extends DrawBasicGraph {

	public DrawTrendline(Model model) {
		super(model);
		this.model = model;
	}

	Model model;

	Graphics2D g2D;

	public void drawTrendline(Graphics2D g2D) {
		this.g2D = g2D;
		g2D.setColor(Color.BLACK);
		if (model.linearRegression()) {
			g2D.scale(1, -1);
			g2D.drawString(model.getLRFormula(), -220, -220);
			g2D.scale(1, -1);

			Model.Point p1 = model.getStartPoint();
			Model.Point p2 = model.getEndPoint();
			g2D.drawLine((int) p1.xValue, (int) p1.yValue, (int) p2.xValue,
					(int) p2.yValue);
		}
	}
}
