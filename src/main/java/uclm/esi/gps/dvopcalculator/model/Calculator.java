package uclm.esi.gps.dvopcalculator.model;

public class Calculator {
	
	private double n1;
	private double n2;
	
	public Calculator(double n1, double n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public double addTwoNumbers() {
		return (n1 + n2);
	}
	
	public double substractTwoNumbers() {
		return (Math.round((n1 - n2)*100.0)/100.0);
	}
	
	public double multiplyTwoNumbers() {
		return n1 * n2;
	}
}
