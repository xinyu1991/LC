package google;

import java.util.HashSet;
import java.util.Set;

public class GenerateCode {
	
	public static String generateCode() {
		StringBuilder result = new StringBuilder();
		Set<Integer> visited = new HashSet<Integer>();
		int num = 9999;
		result.append("999");
		
		while (visited.size() < 10000) {
			result.append(num % 10);
			visited.add(num);
			num = num % 1000 * 10;
			
			int count = 0;
			while (count < 9) {
				if (!visited.contains(num + count)) {
					break;
				}
				count++;
			}
			
			num += count;
		}
		
		return result.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = generateCode();
		System.out.println(s);
		for (int i = 0; i <= 9999; i++) {
			if (!s.contains(String.valueOf(i))) {
				System.out.println("Oops!");
			}
		}
	}

}
