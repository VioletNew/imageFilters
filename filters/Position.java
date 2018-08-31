package filters;

public class Position {
	private int row, column;
	private short red, green, blue;

	public Position(short red, short blue, short green) {
		super();
		this.red = red;
		this.blue = blue;
		this.green= green;
	}
	public short getR(){
		return red;
	}
	public short getB(){
		return blue;
	}
	public short getG() {
		return green;
	}
	public void setR(short r){
		this.red = r;
	}
	public void setG(short g) {
		this.green = g;
	}
	public void setB(short b) {
		this.blue = b;
	}

	
}
