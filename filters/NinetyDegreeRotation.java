package filters;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class NinetyDegreeRotation implements ImageFilter {
	private ImgProvider filteredImage;
	public static void main(String[] args) {
		
	}

	@Override
	public void filter(ImgProvider ip) {
		// get the pixel information
		short[][] red = ip.getRed();
		short[][] green = ip.getGreen();
		short[][] blue = ip.getBlue();
		short[][] transp = ip.getAlpha();
		// create a new array for new pixels
		int height = red.length;
		int width = red[0].length;
		
		short[][] newRed = new short[width][height];
		short[][] newGreen = new short[width][height];
		short[][] newBlue = new short[width][height];
		short[][] newTransp = new short[width][height];
		// copy pixel values into new array
		for(int row = 0; row < width; row++) {
			for(int col = 0; col < height; col++) {
				newRed[row][col] = red[height - col - 1][row];
				newGreen[row][col] = green[height - col - 1][row];
				newBlue[row][col] = blue[height - col - 1][row];
				newTransp[row][col] = transp[height - col - 1][row];
			}
		}


		filteredImage = new ImgProvider();
		filteredImage.setColors(newRed,newGreen,newBlue, newTransp);
		filteredImage.showPix("Rotated!");
		
	}

	@Override
	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	@Override
	public String getMenuLabel() {
		return "Rotate 90 degrees";
	}
}
