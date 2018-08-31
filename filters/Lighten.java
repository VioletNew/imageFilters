package filters;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class Lighten implements ImageFilter {
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
		
		short[][] newRed = new short[height][width];
		short[][] newGreen = new short[height][width];
		short[][] newBlue = new short[height][width];
		short[][] newTransp = new short[height][width];
		
		// copy pixel values into new array
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				newRed[row][col] = red[row][col];
				newGreen[row][col] = green[row][col];
				newBlue[row][col] = blue[row][col];
				newTransp[row][col] = transp[row][col];
			}
		}
		
		String response = JOptionPane.showInputDialog("How much do you want to lighten the image?");
		int lightPercent = Integer.parseInt(response);
		
		int percentage = (int)((lightPercent/100.0)*255);
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				newRed[row][col] = (short) (red[row][col]+percentage);
				newGreen[row][col] = (short)(green[row][col] + percentage);
				newBlue[row][col] = (short)(blue[row][col] + percentage);
			}
		}

		filteredImage = new ImgProvider();
		filteredImage.setColors(newRed, newGreen, newBlue, newTransp);
		filteredImage.showPix("Ligthen!");
		
	}

	@Override
	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	@Override
	public String getMenuLabel() {
		return "Lighten";
	}
}
