package google;

public class NewtonMethod {
	
	public static double sqrt(double x, double epsilon) {
		double t = x;
		while (t > (x + epsilon) / t) {
			t = 0.5 * (t + x / t);
		}
		
		return t;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sqrt(2, 0.00000000001));
	}

}
