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

public class Triangle extends GeometricObject{
	private double side1, side2, side3;
	
	Triangle(){
		side1 = side2 = side3 = 1.0;
	}
	
	Triangle(double side1, double side2, double side3) throws TriangleException{
		double sumS1S2 = side1 + side2;
		double sumS2S3 = side2 + side3;
		double sumS3S1 = side3 + side1;
		if(sumS1S2 < side3 || sumS2S3 < side1 || sumS3S1 < side2)
			throw new TriangleException("Error: Invalid Side(s); Sum of any two sides of triangle should be greator than third side");
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	public double getSide1() {
		return side1;
	}

	public void setSide1(double side1) {
		this.side1 = side1;
	}

	public double getSide2() {
		return side2;
	}

	public void setSide2(double side2) {
		this.side2 = side2;
	}
	
	public double getSide3() {
		return side3;
	}
	
	public void setSide3(double side3) {
		this.side3 = side3;
	}
	
	public double getArea() {
		
		double semiPerm = getPerimeter()/2;
		double areaSquared = semiPerm * (semiPerm - side1) * (semiPerm - side2) * (semiPerm - side3);
		double area = Math.sqrt(areaSquared);
		return area;		
	}
	
	public double getPerimeter() {
		return side1 + side2 + side3;
	}
	
	public String toString() {
		
		return "Side1: " + getSide1() + " units"
						+ "\nSide2: " + getSide2() + " units"
						+ "\nSide3: " + getSide3() + " units\nArea: " + String.format("%.3f", getArea()) +" units"
						+ "\nPerimeter: " + String.format("%.3f", getPerimeter()) + " units"
						+ "\nColor: " + getColor()
						+ "\nFilled: " + isFilled();
	}
	
}

