import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	private int width;
	private int height;
	public SeamCarver(Picture pic) throws IllegalArgumentException {
		if (pic == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = pic;
		width = pic.width();
		height = pic.height();
	}
	// current picture
	public Picture picture() {
		return this.picture;
	}
	// width of current picture
	public int width() {
		return this.width;
	}

	// height of current picture
	public int height() {
		return this.height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		double dx = 0.0;
		double dy = 0.0;
		Color front;
		Color back;
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new IllegalArgumentException("picture is null");
		}
		if(x == 0 || y == 0 || x == width -1 || y == height -1) {
			return 1000.0;
		} else {
			front = picture.get(x-1, y);
			back = picture.get(x+1, y);
			dx = Math.pow((front.getRed() - back.getRed()), 2) + Math.pow((front.getGreen() - back.getGreen()), 2) + Math.pow((front.getBlue() - back.getBlue()), 2);
			front = picture.get(x, y -1);
			back = picture.get(x, y + 1);
			dy = Math.pow((front.getRed() - back.getRed()), 2) + Math.pow((front.getGreen() - back.getGreen()), 2) + Math.pow((front.getBlue() - back.getBlue()), 2);
			return  Math.sqrt((dx + dy));
		}
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}