import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	private int width;
	private int height;
	private double[][] energy;
	private double top = 0.0;
	private double bottom = 0.0;
	public SeamCarver(Picture pic) {
		if (pic == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = pic;
		width = pic.width();
		height = pic.height();
		energy = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				energy[i][j] = energy(j, i);
			}
		}
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
		if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
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
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph((width * height) + 2);
		for (int j = 0; j < width; j++) {

			graph.addEdge(new DirectedEdge(graph.V() - 2, j, energy[0][j]));
		}
		for (int i = 0; i < height - 1; i++) {
			for (int j = 0; j < width; j++) {
				if(i == 0) {
					graph.addEdge(new DirectedEdge(i, (((i+1) * width) + j), energy[i +1][j]));
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + (j + 1)), energy[i + 1][j + 1]));
				} else if(i == width - 1) {
					graph.addEdge(new DirectedEdge(i, (((i +1) * width) + j), energy[i + 1][j]));
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + (j - 1)), energy[i + 1][j - 1]));
				} else {
					graph.addEdge(new DirectedEdge(i, (((i +1) * width) + j - 1), energy[i + 1][j - 1]));
					graph.addEdge(new DirectedEdge(i, (((i + 1) * width) + j), energy[i + 1][j]));
					graph.addEdge(new DirectedEdge(i, (((i +1) * width) + j + 1), energy[i + 1][j + 1]));
				}
			}
		}
		for (int j = 0; j < width; j++) {
			graph.addEdge(new DirectedEdge(((height - 1) * (width)) + j, graph.V()-1, energy[height - 1][j]));
		}
		AcyclicSP sp = new AcyclicSP(graph, graph.V()-2);
		Iterable<DirectedEdge> path = sp.pathTo(graph.V()-1);
		int[] sparray = new int[height];
		for(int i = 0; i < height; i++) {
			sparray[i] = path.iterator().next().from();
		}
		return sparray;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}