package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Model {

	// to store data string
	ArrayList<String> dataset = new ArrayList<String>();
	ArrayList<Point> pointList = new ArrayList<Point>();
	Point point = new Point();
	// to manupilate data
	HashMap<Integer, Point> points = new HashMap<Integer, Point>();
	// linear regression formula
	String formula;
	// two points to draw linear regression graph
	Point startPoint = new Point();
	Point endPoint = new Point();
	// two points with largest absolute x, y values to scale coordinate system
	Point rangePoint = new Point();
	float rangeX = 0;
	float rangeY = 0;

	public Model() {
		/*
		 * dataset.add("12.3 , 2.3"); dataset.add("22.5 ,-23.3");
		 * dataset.add("32.3 , 98.2"); dataset.add("-43.3 , -5.5");
		 * dataset.add("-23.2 , 21.3"); dataset.add("15.2 , 24");
		 * dataset.add("-32 , -89.3");
		 */}

	// holding point
	public class Point {
		public float xValue;
		public float yValue;
	}

	public void addPoint(String point) {
		dataset.add(point);
	}

	public void removePoint(int index) {
		dataset.remove(index);
		points.remove(index);
	}

	public void updatePoint(int index, String point) {
		dataset.remove(index);
		dataset.add(index, point);
	}

	/**
	 * Read file from local file
	 * 
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 */
	public HashMap<Integer, Point> loadDataset(String filepath)
			throws FileNotFoundException {
		int index = 0;
		points.clear();
		dataset.clear();
		pointList.clear();
		Scanner s = new Scanner(new File(filepath));
		//dataset should be smaller than 2048
		while (s.hasNext() && index < 2048) {
			String data = s.nextLine();
			dataset.add(data);
			points.put(index, createPoint(data));
			index++;
		}
		s.close();
		return points;
	}

	/**
	 * get points hashmap from JList
	 * 
	 * @return
	 */
	public HashMap<Integer, Point> loadPoints() {
		int index = 0;
		for (String testdata : getDataset()) {
			points.put(index, createPoint(testdata));
		}
		return points;
	}

	public ArrayList<Point> loadPointList() {
		pointList = new ArrayList<Point>();
		for (String testdata : getDataset()) {
			Point point = new Point();
			point = createPoint(testdata);
			pointList.add(point);
		}
		return pointList;
	}

	/**
	 * Split point from file, or data in the JList
	 * 
	 * @param line
	 * @return
	 */
	public Point createPoint(String line) {
		Point point = new Point();
		String[] parts = line.split(",");
		String x = parts[0];
		String y = parts[1];
		// Point curPoint = new Point();
		// curPoint.xValue = Float.parseFloat(x);
		// curPoint.yValue = Float.parseFloat(y);
		point.xValue = Float.parseFloat(x);
		point.yValue = Float.parseFloat(y);
		this.point = point;
		return point;
	}

	/**
	 * Get data from JEditText
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Point getPointFromInput(String x, String y) {
		point.xValue = Float.parseFloat(x);
		point.yValue = Float.parseFloat(y);
		return point;
	}

	/**
	 * translate points into string
	 * 
	 * @return
	 */
	public String printPoint() {
		String pointForPrint;
		pointForPrint = (Float.toString(point.xValue) + " , " + Float
				.toString(point.yValue));
		return pointForPrint;
	}

	public void saveDataset(String filepath) throws IOException {
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();

		FileWriter fstream = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter writer = new BufferedWriter(fstream);
		for (String data : dataset) {
			writer.write(data);
			writer.newLine();
		}
		writer.close();
	}

	public void setDataset() {

	}

	/**
	 * linear regression function to estimate trend lin
	 */
	public boolean linearRegression() {
		int MAXN = 1000;
		int n = 0;
		float[] x = new float[MAXN];
		float[] y = new float[MAXN];
		loadPointList();
		if (!pointList.isEmpty()) {
			// first pass: read in data, compute xbar and ybar
			float sumx = 0, sumy = 0, sumx2 = 0;
			for (n = 0; n < pointList.size(); n++) {
				x[n] = pointList.get(n).xValue;
				y[n] = pointList.get(n).yValue;
				sumx += x[n];
				sumx2 += x[n] * x[n];
				sumy += y[n];
			}
			float xbar = sumx / n;
			float ybar = sumy / n;

			// second pass: compute summary statistics
			float xxbar = 0, yybar = 0, xybar = 0;
			for (int i = 0; i < n; i++) {
				xxbar += (x[i] - xbar) * (x[i] - xbar);
				yybar += (y[i] - ybar) * (y[i] - ybar);
				xybar += (x[i] - xbar) * (y[i] - ybar);
			}
			float beta1 = xybar / xxbar;
			float beta0 = ybar - beta1 * xbar;

			beta1 = Float.parseFloat(new DecimalFormat("##.##").format(beta1));
			beta0 = Float.parseFloat(new DecimalFormat("##.##").format(beta0));

			formula = "y = " + beta1 + " * x + " + beta0;

			startPoint.xValue = -200;
			startPoint.yValue = beta1 * (-200) + beta0;
			endPoint.xValue = 200;
			endPoint.yValue = beta1 * 200 + beta0;
			return true;
		}
		return false;
		/*
		 * // analyze results int df = n - 2; float rss = 0.0; // residual sum
		 * of squares float ssr = 0.0; // regression sum of squares for (int i =
		 * 0; i < n; i++) { float fit = beta1 * x[i] + beta0; rss += (fit -
		 * y[i]) * (fit - y[i]); ssr += (fit - ybar) * (fit - ybar); } float R2
		 * = ssr / yybar; float svar = rss / df; float svar1 = svar / xxbar;
		 * float svar0 = svar / n + xbar * xbar * svar1;
		 * System.out.println("R^2                 = " + R2);
		 * System.out.println("std error of beta_1 = " + Math.sqrt(svar1));
		 * System.out.println("std error of beta_0 = " + Math.sqrt(svar0));
		 * svar0 = svar * sumx2 / (n * xxbar);
		 * System.out.println("std error of beta_0 = " + Math.sqrt(svar0));
		 * 
		 * System.out.println("SSTO = " + yybar); System.out.println("SSE  = " +
		 * rss); System.out.println("SSR  = " + ssr);
		 */
	}

	public ArrayList<String> getDataset() {
		return dataset;
	}

	public HashMap<Integer, Point> getPoints() {
		return points;
	}

	public float getCurrentPointX() {
		return point.xValue;
	}

	public float getCurrentPointY() {
		return point.yValue;
	}

	public void getRange() {
		// default range
		rangePoint.xValue = 0;
		rangePoint.yValue = 0;
		loadPointList();
		if (pointList != null) {
			for (Point point : pointList) {
				if (Math.abs(point.xValue) > Math.abs(rangePoint.xValue))
					rangePoint.xValue = Math.abs(point.xValue);
			}
			rangeX = rangePoint.xValue;
			for (Point point : pointList) {
				if (Math.abs(point.yValue) > Math.abs(rangePoint.yValue))
					rangePoint.yValue = Math.abs(point.yValue);
			}
			rangeY = rangePoint.yValue;
		}
	}

	public float getRangeX() {
		return rangeX;
	}

	public float getRangeY() {
		return rangeY;
	}

	public String getLRFormula() {
		return formula;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

}
