package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.CartesianGraph;
import model.ColumnGraph;
import model.DrawBasicGraph;
import model.HorizontalBarGraph;
import model.Model;

public class MyBasicPanel extends JPanel {

	Model model;

	int type;

	/*
	 * public MyBasicPanel(Model model, int type) { this.model = model;
	 * this.type = type; }
	 */
	public MyBasicPanel(Model model) {
		this.model = model;
	}

	DrawBasicGraph drawGraph = new DrawBasicGraph(model);

	public void setType(int type) {
		switch (type) {
		case 0:
			drawGraph = new DrawBasicGraph(model);
			break;
		case 1:
			drawGraph = new CartesianGraph(model);
			break;
		case 2:
			drawGraph = new ColumnGraph(model);
			break;
		case 3:
			drawGraph = new HorizontalBarGraph(model);
			break;
		}
		this.type = type;
	}

	/**
	 * Draws the defined function.
	 * 
	 * @param g
	 *            the components graphics context.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// setType(type);
		Graphics2D g2D = (Graphics2D) g;
		drawGraph.paint(g2D);
	}

	public DrawBasicGraph getBasicPanel() {
		return drawGraph;
	}
	
	public int getCurrentType(){
		return type;
	}
}
