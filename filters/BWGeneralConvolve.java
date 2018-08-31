package filters;
import imagelab.*;

public class BWGeneralConvolve implements ImageFilter {

	// Attribute to store the modified image
	ImgProvider filteredImage;
	int totalSum = 0;

	public void filter (ImgProvider ip, int[][] matrix) {

		// Grab the pixel information and put it into a 2D array
		short[][] im = ip.getBWImage();

		// Make variables for image height and width
		int height = im.length;
		int width  = im[0].length;
		int matrixSum = findSum(matrix);

		// Create a new array to store the modified image
		short[][] newImage = new short[height][width];
		
		//loop over all (row, col) in im[][]
			//loop over a nxm "block", get out the pixel value, get out weight from mask, multiply and add to average
		for(int r = matrix.length/2 + 1 ; r < im.length-matrix.length; r++) {
			for(int c = matrix[0].length/2 + 1; c < im[0].length-matrix[0].length; c++){
				newImage[r][c] = (short)(multiplicationResult(im, matrix, r, c)/matrixSum);
			}
		}
	

		// Create a new ImgProvider and set the filtered image to our new image
		filteredImage = new ImgProvider();
		filteredImage.setBWImage(newImage);

		// Show the new image in a new window with title "Flipped Horizontally"
		filteredImage.showPix("Convulation!");
	}

	private int multiplicationResult(short[][] im, int[][] matrix, int centerR, int centerC) {
		int sum = 0;
		for(int r = 0; r < matrix.length; r++){
			for(int c = 0; c < matrix[0].length; c++) {
				sum += im[centerR - matrix.length/2 + r][centerC - matrix[0].length/2 + c]*matrix[r][c];
			}
		}
		return sum;
	}

	private int findSum(int[][] matrix) {
		int sum = 0;
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[0].length; c++) {
				sum+=matrix[r][c];
			}
		}
		return sum;
	}

	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	// This is what users see in the Filter menu
	public String getMenuLabel() {
		return "Convolution (BW)";
	}

	@Override
	public void filter(ImgProvider ip) {
		// TODO Auto-generated method stub
		
	}

}