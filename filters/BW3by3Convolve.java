package filters;
import imagelab.*;

public class BW3by3Convolve implements ImageFilter {

	// Attribute to store the modified image
	ImgProvider filteredImage;
	int sum;
	
	private static final int[][] GAUSSIAN_BLUR = 
		{{1, 2, 1},
		{2, 4, 2},
		{1, 2, 1},
		};
	private static final int[][] VANLLA_AVERAGE = 
		{{1, 1, 1},
		{1, 1, 1},
		{1, 1, 1},
		};
	public void filter (ImgProvider ip) {

		// Grab the pixel information and put it into a 2D array
		short[][] im = ip.getBWImage();

		// Make variables for image height and width
		int height = im.length;
		int width  = im[0].length;

		// Create a new array to store the modified image
		short[][] newImage = new short[height][width];
		
		//loop over all (row, col) in im[][]
			//loop over a 3x3 "block", get out the pixel value, get out weight from mask, multiply and add to average
		for(int r = 1; r< im.length-1; r++) {
			for(int c = 1; c < im[0].length-1; c++){
				sum = im[r-1][c-1]+2*im[r-1][c]+im[r][c+1]+2*im[r][c-1]+4*im[r][c]+2*im[r][c+1]+im[r+1][c-1]+2*im[r+1][c]+im[r+1][c+1];
				newImage[r][c] = (short)(sum/16);
			}
		}
	

		// Create a new ImgProvider and set the filtered image to our new image
		filteredImage = new ImgProvider();
		filteredImage.setBWImage(newImage);

		// Show the new image in a new window with title "Flipped Horizontally"
		filteredImage.showPix("Convulation!");
	}

	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	// This is what users see in the Filter menu
	public String getMenuLabel() {
		return "Convolution (BW)";
	}

}