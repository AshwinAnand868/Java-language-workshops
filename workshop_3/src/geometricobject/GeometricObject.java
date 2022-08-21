/**********************************************
Workshop 3
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 15 June 2022
**********************************************/
package geometricobject;

public abstract class GeometricObject {
	String color;
	boolean filled;
	
	GeometricObject(){
		color = "white";
		filled = true;
	}
	
	protected GeometricObject(String color, boolean filled) {
		this.color = color;
		this.filled = filled;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public boolean isFilled() {
		return filled;
	}
	
	public void setFill(boolean filled) {
		this.filled = filled;
	}
	
	protected abstract double getArea();
	protected abstract double getPerimeter();
}


