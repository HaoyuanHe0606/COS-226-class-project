/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Partner Name:    Yutong Shen
 *  Partner NetID:   yutongs
 *  Partner Precept: P03A
 *
 *  Description:  A data type that resizes a W-by-H image using
 * the seam-carving technique.
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class SeamCarver {
    // constant for color rgb
    private static final int ENCODE = 0xFF;
    // positive infinity
    private static final double MAX = Double.POSITIVE_INFINITY;
    // current picture
    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException();
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        // corner case
        if (x < 0 || x >= width()) throw new IllegalArgumentException();
        if (y < 0 || y >= height()) throw new IllegalArgumentException();
        // int color of 4 directions
        int left;
        int right;
        int up;
        int down;
        if (y == 0) up = picture.getRGB(x, height() - 1);
        else up = picture.getRGB(x, y - 1);
        if (y == height() - 1) down = picture.getRGB(x, 0);
        else down = picture.getRGB(x, y + 1);
        if (x == 0) left = picture.getRGB(width() - 1, y);
        else left = picture.getRGB(x - 1, y);
        if (x == width() - 1) right = this.picture.getRGB(0, y);
        else right = this.picture.getRGB(x + 1, y);

        // get the squared energe of dx and dy
        double dx = gradient(right, left);
        double dy = gradient(down, up);
        return Math.sqrt(dx + dy);
    }

    // helper method to calculate dx and dy
    private double gradient(int a, int b) {
        int dR = getR(a) - getR(b);
        int dB = getB(a) - getB(b);
        int dG = getG(a) - getG(b);
        return dR * dR + dB * dB + dG * dG;
    }

    // helper method to get red from input int
    private static int getR(int color) {
        return (color >> 16) & ENCODE;
    }

    // helper method to get green from input int
    private static int getG(int color) {
        return (color >> 8) & ENCODE;
    }

    // helper method to get blue from input int
    private static int getB(int color) {
        return color & ENCODE;
    }

    // change the 2d index to 1d array
    private int tooned(int x, int y) {
        return x + y * width();
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        picture = transpose();
        int[] shortest = findVerticalSeam();
        picture = transpose();
        return shortest;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int width = width();
        int height = height();
        int n = width * height;
        int[] edge = new int[n];
        double[] dist = new double[n];
        double[] energy = new double[n];

        // initialize the distance , edge, and energy array for every pixel
        // before searching the shortest path
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int current = tooned(x, y);
                dist[current] = MAX;
                edge[current] = -1;
                energy[current] = energy(x, y);
            }
        }
        // pixel at top row
        for (int x = 0; x < width; x++) dist[tooned(x, 0)] = energy(x, 0);
        // search through every pixel to find the shortest path
        for (int y = 1; y < height; y++)
            for (int x = 0; x < width; x++) {
                int toPix = tooned(x, y);
                int leftPix = tooned(x - 1, y - 1);
                int midPix = tooned(x, y - 1);
                int rightPix = tooned(x + 1, y - 1);

                // check left
                if (x - 1 >= 0) {
                    double leftEne = dist[leftPix] + energy[toPix];
                    if (dist[toPix] > leftEne) {
                        dist[toPix] = leftEne;
                        edge[toPix] = leftPix;
                    }
                }

                // check mid
                double midEne = dist[midPix] + energy[toPix];
                if (dist[toPix] > midEne) {
                    dist[toPix] = midEne;
                    edge[toPix] = midPix;
                }

                // check right
                if (x + 1 <= width) {
                    double rightEne = dist[rightPix] + energy[toPix];
                    if (dist[toPix] > rightEne) {
                        dist[toPix] = rightEne;
                        edge[toPix] = rightPix;
                    }
                }
            }

        // update dist array and the shortest path
        int shortest = 0;
        for (int i = 0; i < width; i++)
            if (dist[tooned(i, height - 1)] <
                    dist[tooned(shortest, height - 1)])
                shortest = i;

        // construct the shortest path
        int[] seam = new int[height];
        int current = tooned(shortest, height - 1);

        while (current > -1) {
            seam[current / width] = current % width;
            current = edge[current];
        }
        return seam;
    }

    // transpose the picture to help us find the shortest horizontal seam
    private Picture transpose() {
        int w = width();
        int h = height();
        Picture transposedPic = new Picture(h, w);
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                transposedPic.setRGB(y, x, picture.getRGB(x, y));
        return transposedPic;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        // corner case
        if (seam == null)
            throw new IllegalArgumentException();
        if (seam.length != width() || height() <= 1)
            throw new IllegalArgumentException();
        for (int i = 0; i < seam.length; i++) {
            if (i > 0) {
                if (Math.abs(seam[i - 1] - seam[i]) > 1)
                    throw new IllegalArgumentException();
            }
            if (seam[i] < 0 || seam[i] > height() - 1)
                throw new IllegalArgumentException();
        }

        // create a copy of the picture then update the new color pixel
        // using set rgb and get rgb
        int width = width();
        int height = height();
        Picture result = new Picture(width, height - 1);
        for (int x = 0; x < width; x++) {
            int mid = seam[x];
            for (int y = 0; y < height - 1; y++) {
                if (y < mid) {
                    int rgb = picture.getRGB(x, y);
                    result.setRGB(x, y, rgb);
                }
                else {
                    int rgb = picture.getRGB(x, y + 1);
                    result.setRGB(x, y, rgb);
                }
            }
        }
        picture = result;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        // corner case
        if (seam == null)
            throw new IllegalArgumentException();
        if (seam.length != height() || width() <= 1)
            throw new IllegalArgumentException();
        for (int i = 0; i < seam.length; i++) {
            if (i > 0) {
                if (Math.abs(seam[i - 1] - seam[i]) > 1)
                    throw new IllegalArgumentException();
            }
            if (seam[i] < 0 || seam[i] > width() - 1)
                throw new IllegalArgumentException();
        }

        // create a copy of the picture then update the new color pixel
        // using set rgb and get rgb
        int width = width();
        int height = height();
        Picture result = new Picture((width - 1), height);
        for (int y = 0; y < height; y++) {
            int mid = seam[y];
            for (int x = 0; x < width - 1; x++) {
                if (x < mid) {
                    int rgb = picture.getRGB(x, y);
                    result.setRGB(x, y, rgb);
                }
                else {
                    int rgb = picture.getRGB(x + 1, y);
                    result.setRGB(x, y, rgb);
                }
            }
        }
        picture = result;
    }

    //  unit testing (required)
    public static void main(String[] args) {
        Picture test;
        SeamCarver seamCarver;
        test = SCUtility.randomPicture(128000, 1000);
        seamCarver = new SeamCarver(test);
        StdOut.println(seamCarver.energy(0, 0));
        StdOut.println(seamCarver.width());
        StdOut.println(seamCarver.height());
        Stopwatch timer = new Stopwatch();
        seamCarver.removeHorizontalSeam(seamCarver.findHorizontalSeam());
        seamCarver.removeVerticalSeam(seamCarver.findVerticalSeam());
        System.out.println(timer.elapsedTime());
    }
}
