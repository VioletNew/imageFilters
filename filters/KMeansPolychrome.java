package filters;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class KMeansPolychrome implements ImageFilter {
	private ImgProvider filteredImage;
	private double shortest = Double.MAX_VALUE;
	private int group = 0;
	private double redSum = 0;
	private double blueSum = 0;
	private double greenSum = 0;
	private short redAverage = 0;
	private short blueAverage = 0;
	private short greenAverage = 0;
	private short totalChange = 0;
	public static void main(String[] args) {
		
	}

	public double dist(short r1, short g1, short b1, short r2, short g2, short b2) {
		double a = (double) r1 - (double) r2;
		double b = (double) g1 - (double) g2;
		double c = (double) b1 - (double) b2;
		return Math.sqrt(a*a+b*b+c*c);
	}
	@Override
	public void filter(ImgProvider ip) {

		short[][] r = ip.getRed();
		short[][] b = ip.getBlue();
		short[][] g = ip.getGreen();
		short[][] transp = ip.getAlpha();
		int height = r.length;
		int width = r[0].length;
		short[][] newRed = new short[height][width];
		short[][] newGreen = new short[height][width];
		short[][] newBlue = new short[height][width];
		
		String response = JOptionPane.showInputDialog("How many clusters do you want?");
		int clusters = Integer.parseInt(response);
		
		short[][] meanValues = new short[clusters][3];
		for (int i = 0; i < meanValues.length; i++) {
			for(int j = 0; j < meanValues[0].length; j++) {
				meanValues[i][j] = (short)(Math.random()*255);
			}
		}
		ArrayList<Cluster>[] pointClusters = new ArrayList[clusters];
		for(int i=0; i<clusters; i++)
		{
			pointClusters[i] = new ArrayList<Cluster>();
		}
		do{
		for(int i=0; i<r.length; i++){
			for(int j=0; j<r[0].length; j++){
				for(int k = 0; k < meanValues.length; k++) {
					if(dist(r[i][j], g[i][j], b[i][j], meanValues[k][0], meanValues[k][1], meanValues[k][2]) < shortest){
						shortest = dist(r[i][j], g[i][j], b[i][j], meanValues[k][0], meanValues[k][1], meanValues[k][2]);
						group = k;
					}
				}
				pointClusters[group].addPoint(r[i][j], b[i][j], g[i][j]);
				shortest = Double.MAX_VALUE;
			}
		}
		for(int i = 0; i < clusters; i++) {
			for(int j = 0; j < pointClusters[clusters].size(); j++) {
				redAverage = pointClusters[clusters].get(j).getCenter().getR();
				blueAverage = pointClusters[clusters].get(j).getCenter().getB();
				greenAverage = pointClusters[clusters].get(j).getCenter().getG();
				
				//set RGB values
			}
			totalChange += (Math.abs(redAverage - meanValues[clusters][0])+Math.abs(blueAverage-meanValues[clusters][1])+Math.abs(greenAverage-meanValues[clusters][2]));
		} 
		}while(totalChange < 5 && totalChange!=0);
	
		filteredImage = new ImgProvider();
		filteredImage.setColors(newRed, newGreen, newBlue, transp);
		filteredImage.showPix("Polychrome!!");
		
	}

	@Override
	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	@Override
	public String getMenuLabel() {
		return "KMeansPolychrome";
	}
}

