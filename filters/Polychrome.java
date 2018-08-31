package filters;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class Polychrome implements ImageFilter {
	private ImgProvider filteredImage;
	public static void main(String[] args) {
		
	}

	@Override
	public void filter(ImgProvider ip) {
		// get the pixel information
		short[][] pic = ip.getBWImage();
		// create a new array for new pixels
		int height = pic.length;
		int width = pic[0].length;
		
		short[][] newPic = new short[height][width];

		// copy pixel values into new array
		String response = JOptionPane.showInputDialog("How many groups do you want?");
		int groups = Integer.parseInt(response);
		short[][] groupMeans = new short[groups][3];
		for(int r = 0; r < groups; r++) {
			for(int c = 0; c < 3; c++) {
				groupMeans[r][c] = (short) (100 + r + c);
			}
		}
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(pic[row][col] >= splitPoint) {
					newPic[row][col] = 255;
				} else {
					newPic[row][col] = 0;
				}
			}
		}
		

		filteredImage = new ImgProvider();
		filteredImage.setBWImage(newPic);
		filteredImage.showPix("Polychrome!");
		
	}

	@Override
	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	@Override
	public String getMenuLabel() {
		return "PolyKrome";
	}
}
