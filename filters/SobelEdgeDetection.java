package filters;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class SobelEdgeDetection implements ImageFilter {
	
	// Attribute to store the modified image
		ImgProvider filteredImage;
		short x, y;
		private static final int[][] SOBEL_Gx = 
			{{-1, 0, 1},
			{-2, 0, 2},
			{-1, 0, 1},
			};
		private static final int[][] SOBEL_Gy = 
			{{1, 2, 1},
			{0, 0, 0},
			{-1, -2, -1},
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
					x = multiply3by3(SOBEL_Gx, im, r, c);
					y = multiply3by3(SOBEL_Gy, im, r, c);
					newImage[r][c] = (short) Math.sqrt(x*x+y*y);
				}
			}
		

			// Create a new ImgProvider and set the filtered image to our new image
			filteredImage = new ImgProvider();
			filteredImage.setBWImage(newImage);

			// Show the new image in a new window with title "Flipped Horizontally"
			filteredImage.showPix("Sobel Edge!");
		}
		public short multiply3by3(int[][] filter, short[][] arr, int r, int c) {
			short sum = 0;
			short filterTotal = 0;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++){
					sum += (short)(filter[i][j])*arr[r-1+i][c+1-j];
					filterTotal += filter[i][j];
				}
			}
			return sum;
		}

		public ImgProvider getImgProvider() {
			return filteredImage;
		}

		// This is what users see in the Filter menu
		public String getMenuLabel() {
			return "Sobel Edge Detection..";
		}
}
