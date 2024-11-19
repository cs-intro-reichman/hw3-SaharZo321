// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
		// Tests some of the operations
		System.out.println(plus(-2, -3)); // 2 + 3
		System.out.println(minus(-7, -2)); // 7 - 2
		System.out.println(minus(2, -7)); // 2 - 7
		System.out.println(times(3, -4)); // 3 * 4
		System.out.println(plus(2, times(-4, 2))); // 2 + 4 * 2
		System.out.println(pow(-5, 3)); // 5^3
		System.out.println(pow(3, 5)); // 3^5
		System.out.println(div(-12, -3)); // 12 / 3
		System.out.println(div(5, 5)); // 5 / 5
		System.out.println(div(25, 7)); // 25 / 7
		System.out.println(mod(25, 7)); // 25 % 7
		System.out.println(mod(-121, -6)); // 120 % 6
		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
		System.out.println(sqrt(76123));
	}

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		// Replace the following statement with your code
		int count = 0;
		boolean isPlus = x2 > 0;
		while (count != x2) {
			if (isPlus) {
				count++;
				x1++;
			} else {
				count--;
				x1--;
			}
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		// Replace the following statement with your code
		int count = 0;
		boolean isPlus = x2 > 0;
		while (count != x2) {
			if (!isPlus) {
				count--;
				x1++;
			} else {
				count++;
				x1--;
			}
		}
		return x1;
	}

	public static boolean isSameSign(int x1, int x2) {
		return x1 > 0 && x2 > 0 || x1 < 0 && x2 < 0;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		// Replace the following statement with your code
		if (x1 == 0 || x2 == 0) {
			return 0;
		}
		int absX2 = x2 < 0 ? minus(0, x2) : x2;
		int absX1 = x1 < 0 ? minus(0, x1) : x1;
		int base = 0;
		boolean isSameSign = isSameSign(x1, x2);
		for (int i = 0; i < absX2; i++) {
			if (isSameSign) {
				base = plus(base, absX1);
			} else {
				base = minus(base, absX1);
			}
		}
		return base;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		// Replace the following statement with your code
		int base = x;
		for (int i = 1; i < n; i++) {
			x = times(x, base);
		}
		return x;
	}

	// Returns the integer part of x1 / x2
	public static int div(int x1, int x2) {
		// Replace the following statement with your code
		int counter = -1;
		int absX2 = x2 < 0 ? minus(0, x2) : x2;
		int absX1 = x1 < 0 ? minus(0, x1) : x1;
		boolean isSameSign = isSameSign(x1, x2);
		while (absX1 >= 0) {

			absX1 = minus(absX1, absX2);
			counter++;
		}
		return isSameSign ? counter : times(counter, -1);
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int absX2 = x2 < 0 ? minus(0, x2) : x2;
		if (x1 < 0) {
			while (plus(x1, absX2) < absX2) {	
				x1 = plus(x1, absX2);
			}
		} else {
			while (x1 >= absX2) {	
				x1 = minus(x1, absX2);
			}
		}
		return x1;
	}

	// Returns the integer part of sqrt(x)
	public static int sqrt(int x) {
		for (int i = 1; i < x; i++) {
			if (pow(i, 2) > x) {
				return i - 1;
			}
		}
		return 0;
	}
}