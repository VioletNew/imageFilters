package filters;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class colorNoise implements ImageFilter {
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
		
		String response = JOptionPane.showInputDialog("How much noise do you want?");
		int noisePercent = Integer.parseInt(response);
		
		int numPixels = (int)((noisePercent/100.0)*(width*height));
		for(int i = 0; i < numPixels; i++) {
			int row = (int)(Math.random()*height);
			int col = (int)(Math.random()*width);
			
			newRed[row][col] = (short)(Math.random()*256);
			newGreen[row][col] = (short)(Math.random()*256);
			newBlue[row][col] = (short)(Math.random()*256);
		}

		filteredImage = new ImgProvider();
		filteredImage.setColors(newRed,newGreen,newBlue, newTransp);
		filteredImage.showPix("Noisy!");
		
	}

	@Override
	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	@Override
	public String getMenuLabel() {
		return "Color Noise Filter";
	}
}
