import java.awt.Color;
import java.util.ArrayList;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	private double[][] energy;
	public SeamCarver(Picture pic) {
		if (pic == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = pic;
		energy = new double[pic.width()][pic.height()];
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				energy[col][row] = energy(col, row);
			}
		}
	}
	// current picture
	public Picture picture() {
		return this.picture;
	}
	// width of current picture
	public int width() {
		return energy.length;
	}

	// height of current picture
	public int height() {
		return energy[0].length;
	}
	public void relaxEdge(int fromPixel, int toPixel, double[] distTo, int[] edgeTo) {

		if (distTo[fromPixel] + energy[toPixel % width()][toPixel / width()] <
		        distTo[toPixel]) {
			distTo[toPixel] = distTo[fromPixel] +
			                  energy[toPixel % width()][toPixel / width()];
			edgeTo[toPixel] = fromPixel;
		}
	}
	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		double dx = 0.0;
		double dy = 0.0;
		Color front;
		Color back;
		if (x < 0 || x >= width() || y < 0 || y >= height()) {
			throw new IllegalArgumentException("picture is null");
		}
		if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
			return 1000.0;
		} else {
			front = picture.get(x - 1, y);
			back = picture.get(x + 1, y);
			dx = Math.pow((front.getRed() - back.getRed()), 2) + Math.pow((front.getGreen() - back.getGreen()), 2) + Math.pow((front.getBlue() - back.getBlue()), 2);
			front = picture.get(x, y - 1);
			back = picture.get(x, y + 1);
			dy = Math.pow((front.getRed() - back.getRed()), 2) + Math.pow((front.getGreen() - back.getGreen()), 2) + Math.pow((front.getBlue() - back.getBlue()), 2);
			return  Math.sqrt((dx + dy));
		}
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		double[] distTo = new double[width() * height()];
		int[] edgeTo = new int[width() * height()];
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				if (col == 0)
					distTo[width() * row + col] = 1000.0;
				else
					distTo[width() * row + col] = Double.POSITIVE_INFINITY;
				edgeTo[width() * row + col] = -1;
			}
		}
		for (int col = 0; col < width() - 1; col++) {
			for (int row = 0; row < height(); row++) {

				// relax right-upper edge if it exists
				// relax right-middle edge
				relaxEdge(width() * row + col, width() * row + (col + 1), distTo, edgeTo);
				// relax right-bottom edge if it exists
				if (row + 1 <= height() - 1)
					relaxEdge(width() * row + col, width() * (row + 1) + (col + 1), distTo, edgeTo);
				if (row - 1 >= 0)
					relaxEdge(width() * row + col, width() * (row - 1) + (col + 1), distTo, edgeTo);
			}
		}
		double curMinDist = Double.POSITIVE_INFINITY;
		int lastSeamPixel = 0;
		for (int row = 0; row < height(); row++) {
			if (distTo[(width() * row) + (width() - 1)] < curMinDist) {
				curMinDist = distTo[(width() * row) + (width() - 1)];
				lastSeamPixel = (width() * row) + (width() - 1);
			}
		}
		int[] horSeam = new int[width()];
		int curPixel = lastSeamPixel;
		int i = horSeam.length - 1;
		// while not beginning of the seam. LeftCol = -1
		while (curPixel != -1) {
			horSeam[i] = curPixel / width();
			curPixel = edgeTo[curPixel];
			i--;
		}
		return horSeam;
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		// transpose image
		double[][] energycopy = energy;
		transposeEnergy();
		// find horizontal seam
		int[] verSeam  = findHorizontalSeam();
		// transpose back
		energy = energycopy;
		return verSeam;

	}
	public void transposeEnergy() {
		double[][] transposedEnergy = new double[height()][width()];
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				transposedEnergy[row][col] = energy[col][row];
			}
		}
		energy = transposedEnergy;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		Picture updatedPicture = new Picture(width(), height() - 1);
		double[][] updatedPicEnergy = new double[width()][height() - 1];
		for (int col = 0; col < width(); col++) {
			// copy the upper part of the picture and picEnergy
			for (int row = 0; row < seam[col]; row++) {
				updatedPicture.set(col, row, picture.get(col, row));
				updatedPicEnergy[col][row] = energy[col][row];
			}
			// copy the bottom part of the picEnergy
			for (int row = seam[col] + 1; row < height(); row++) {
				updatedPicture.set(col, row - 1, picture.get(col, row));
				updatedPicEnergy[col][row - 1] = energy[col][row];
			}
		}
		picture = updatedPicture;
		energy = updatedPicEnergy;

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		Picture updatedPicture = new Picture(width() - 1, height());
		double[][] updatedPicEnergy = new double[width() - 1][height()];
		for (int row = 0; row < height(); row++) {
			// copy the upper part of the picture and picEnergy
			for (int col = 0; col < seam[row]; col++) {
				updatedPicture.set(col, row, picture.get(col, row));
				updatedPicEnergy[col][row] = energy[col][row];
			}
			// copy the bottom part of the picEnergy
			for (int col = seam[row] + 1; col < width(); col++) {
				updatedPicture.set(col - 1, row, picture.get(col, row));
				updatedPicEnergy[col - 1][row] = energy[col][row];
			}
		}

		picture = updatedPicture;
		energy = updatedPicEnergy;
	}
}