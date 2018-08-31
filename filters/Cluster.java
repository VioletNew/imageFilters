package filters;

import java.util.ArrayList;

public class Cluster {
	private ArrayList<Position> data;
	private Position center;
	private Position previousCenter;
	public Cluster(ArrayList<Position> data) {
		this.data = data;
	}
	public void addPoint(int row, int col, short r, short b, short g){
		data.add(new Position(r, b, g));
		this.previousCenter = this.center;
		this.center = this.calculateCenter();
	}
	public ArrayList<Position> getData() {
		return data;
	}
	public void setData(ArrayList<Position> data) {
		this.data = data;
	}
	public Position getCenter() {
		return center;
	}
	public void setCenter(Position center) {
		this.center = center;
	}
	public Position getPreviousCenter() {
		return previousCenter;
	}
	public void setPreviousCenter(Position previousCenter) {
		this.previousCenter = previousCenter;
	}
	public Position calculateCenter(){
		short redSum = 0, blueSum = 0, greenSum = 0;
		Position position = new Position(redSum, blueSum, greenSum);
		for(int i = 0; i < data.size(); i++) {
			redSum += data.get(i).getR();
			blueSum += data.get(i).getB();
			greenSum += data.get(i).getG();
		}
		position.setR((short)(redSum/data.size()));
		position.setB((short)(blueSum/data.size()));
		position.setG((short)(greenSum/data.size()));
		return position;
	}
	
}
