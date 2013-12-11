package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import dataset.IDataSet;

public class DataSet implements IDataSet {

	ArrayList<String> dataset = new ArrayList<String>();
	ArrayList<MyPoint> pointList = new ArrayList<MyPoint>();
	// linear regression formula
	String formula;
	// two points to draw linear regression graph
	MyPoint startPoint = new MyPoint();
	MyPoint endPoint = new MyPoint();
	final int maxNum = 2048;

	public void addPoint(String data) {
		dataset.add(data);
		MyPoint point = createPoint(data);
		pointList.add(point);
	}

	public void removePoint(int index) {
		dataset.remove(index);
		pointList.remove(index);
	}

	public void editPoint(int index, String data) {
		dataset.remove(data);
		dataset.add(index, data);

		pointList.remove(index);
		MyPoint point = createPoint(data);
		pointList.add(index, point);
	}

	public int size() {
		return dataset.size();
	}

	public double getMinX() {
		double minX = pointList.get(0).x;
		for (MyPoint point : pointList)
			if (point.x < minX)
				minX = point.x;
		return minX;
	}

	public double getMaxX() {
		double maxX = pointList.get(0).x;
		for (MyPoint point : pointList)
			if (point.x > maxX)
				maxX = point.x;
		return maxX;
	}

	public double getMinY() {
		double minY = pointList.get(0).y;
		for (MyPoint point : pointList)
			if (point.y < minY)
				minY = point.y;
		return minY;
	}

	public double getMaxY() {
		double maxY = pointList.get(0).y;
		for (MyPoint point : pointList)
			if (point.y > maxY)
				maxY = point.y;
		return maxY;
	}

	/**
	 * return pointList
	 * 
	 * @return
	 */
	public ArrayList<MyPoint> loadPointList() {
		return pointList;
	}

	/**
	 * Retrieves the desired coordinate for the value of the data at the given
	 * index location.
	 * 
	 * index must be between 0 and size() dimension must be either 0 (for X) or
	 * 1 (for Y)
	 * 
	 * If index is invalid, throws ArrayIndexOutOfBoundsException If dimension
	 * is invalid (0 or 1 only) throws IllegalArgumentException
	 */
	public double getCoordinate(int index, int dimension)
			throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		if (index < 0 || index > size() - 1)
			throw new ArrayIndexOutOfBoundsException();
		if (dimension == 0)
			return pointList.get(index).x;
		else if (dimension == 1)
			return pointList.get(index).y;
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Read file from local file
	 * 
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> loadDataset(String filepath)
			throws FileNotFoundException {

		Scanner s = new Scanner(new File(filepath));

		int index = 0;
		dataset.clear();
		pointList.clear();
		String data;

		// make sure no more than 2048
		while (s.hasNext() && index < maxNum) {
			data = s.nextLine();
			addPoint(data);
			index++;
		}
		s.close();
		return dataset;
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

	/**
	 * Split point from file, or data in the JList
	 * 
	 * @param line
	 * @return
	 */
	public MyPoint createPoint(String line) {
		MyPoint point = new MyPoint();
		String[] parts = line.split(",");
		String x = parts[0];
		String y = parts[1];

		point.x = Double.parseDouble(x);
		point.y = Double.parseDouble(y);

		return point;
	}

	/**
	 * linear regression function to estimate trend lin
	 */
	public boolean linearRegression() {
		int MAXN = 1000;
		int n = 0;
		double[] x = new double[MAXN];
		double[] y = new double[MAXN];
		loadPointList();
		if (!pointList.isEmpty()) {
			// first pass: read in data, compute xbar and ybar
			float sumx = 0, sumy = 0, sumx2 = 0;
			for (n = 0; n < pointList.size(); n++) {
				x[n] = pointList.get(n).x;
				y[n] = pointList.get(n).y;
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

			startPoint.x = -200;
			startPoint.y = beta1 * (-200) + beta0;
			endPoint.x = 200;
			endPoint.y = beta1 * 200 + beta0;
			return true;
		}
		return false;
	}

	public MyPoint getStartPoint() {
		return startPoint;
	}

	public MyPoint getEndPoint() {
		return endPoint;
	}

	public String getLRFormula() {
		return formula;
	}

	public ArrayList<String> getDataset() {
		return dataset;
	}

}
