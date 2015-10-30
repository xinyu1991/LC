package google;

public class MirrorBinary {
	
	public static int getMirrorBinary(int a) {
		int b = 0;
		for (int i = 0; i < 32; i++) {
			int bit = (a >> i) & 1;
			b |= (bit << (31 - i));
		}
		return b;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 574909044;
		System.out.println(getMirrorBinary(a));
	}

}
