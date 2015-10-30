package google;

public class MatchArrangement {
	
	public static String arrange(int n) {
		if (n <= 0) {
			return "";
		}
		
		String[] team = new String[n];
		for (int i = 1; i <= n; i++) {
			team[i - 1] = String.valueOf(i);
		}
		
		while (n > 1) {
			for (int i = 0; i < n/2; i++) {
				team[i] = match(team[i], team[n - 1 - i]);
			}
			n /= 2;
		}
		
		return team[0];
	}
	
	private static String match(String s1, String s2) {
		return "(" + s1 + "," + s2 + ")";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(arrange(8));
	}

}
