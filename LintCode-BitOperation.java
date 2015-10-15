/*
Easy Flip Bits

29% Accepted
Determine the number of bits required to flip if you want to convert integer n to integer m.

Example
Given n = 31 (11111), m = 14 (01110), return 2.

Note
Both n and m are 32-bit integers.
*/

class Solution {
    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int count = 0;
        for(int i = 0; i < 32; i++){
        	if(((a & 1) ^ (b & 1)) == 1) count++;
        	a >>= 1;
        	b >>= 1;
        }
        return count;
    }
}

/*
Easy O(1) Check Power of 2 Show result 

Using O(1) time to check whether an integer n is a power of 2.

Example
For n=4, return true;

For n=5, return false;

Challenge
O(1) time
*/

class Solution {
    /*
     * @param n: An integer
     * @return: True or false
     */
    public boolean checkPowerOf2(int n) {
        // write your code here
        if((n>>31)==1) return false;
        int count = 0;
        for(int i = 0; i < 31; i++){
            if((n&1)==1) count++;
            if(count > 1) return false;
            n>>=1;
        }
        return count==1;
    }
};


/*
Easy Unique Paths

35% Accepted
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
Note
m and n will be at most 100.
*/

public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here 
        int[][] paths = new int[m][n];
        for(int i = 0; i < m; i++){
        	for(int j = 0; j < n; j++){
        		if(i == 0 || j == 0) paths[i][j] = 1;
        		else paths[i][j] = paths[i-1][j] + paths[i][j-1];
        	}
        }
        return paths[m-1][n-1];
    }
}

/*
Easy Trailing Zeros

29% Accepted
Write an algorithm which computes the number of trailing zeros in n factorial.

Have you met this question in a real interview? Yes
Example
11! = 39916800, so the out should be 2

Challenge
O(log N) time
*/

class Solution {
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        long exp = (long) (Math.log(n)/Math.log(5));
        long div = 5;
        long count = 0;
        for(long i = 0; i < exp; i++){
        	count += n/div;
        	div *= 5;
        }
        return count;
    }
};

/*
Medium Update Bits

19% Accepted
Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)

Have you met this question in a real interview? Yes
Example
Given N=(10000000000)2, M=(10101)2, i=2, j=6

return N=(10001010100)2

Note
In the function, the numbers N and M will given in decimal, you should also return a decimal number.

Challenge
Minimum number of operations?

Clarification
You can assume that the bits j through i have enough space to fit all of M. 
That is, if M=10011， you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.
*/

class Solution {
    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        if(i==0 && j==31) return m;
        int rightValue = 0;
        int midValue = 0;
        int curValue = 1;
        for(int k = 0; k <= j; k++){
            if(k < i) rightValue += (n&1)*curValue;
            else {
                midValue += (m&1)*curValue;
                m >>= 1;
            }
            // This is important because the signal sign won't be moved.
            if(k==31 && n==-1) n=0;
            n >>= 1;
            curValue <<= 1;
        }
        n <<= (j+1);
        n += rightValue + midValue;
        return n;
    }
}


/*
Unique Binary Search Trees

32% Accepted
Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?

Have you met this question in a real interview? Yes
Example
Given n = 3, there are a total of 5 unique BST's.

1           3    3       2      1
 \         /    /       / \      \
  3      2     1       1   3      2
 /      /       \                  \
2     1          2                  3
*/

public class Solution {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        if(n <= 1) return 1;
        int[] trees = new int[n+1];
        trees[0] = 1;
        trees[1] = 1;
        for(int i = 2; i <= n; i++){
        	for(int j = 0; j < i; j++){
        		trees[i] += trees[j]*trees[i-j-1];
        	}
        }
        return trees[n];
    }
}

/*
Medium Fast Power

19% Accepted
Calculate the an % b where a, b and n are all 32bit integers.

Have you met this question in a real interview? Yes
Example
For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

Challenge
O(logn)
*/
class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        if(b == 1 || a == 0) return 0;
        if(n == 0 || a == 1) return 1;
        if(n == 1) return a%b;
        int half = fastPower(a, b, n>>1);
        // Note: Use long to make sure no overflow.
        long val = (long)half*(long)half;
        // Note: calculate this before times a, to avoid overflow.
        long res = val % b;
        if(n%2 != 0) res = (res*a) % b;
        return (int)res;
    }
};


/*
Hard Binary Representation

17% Accepted
Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string. 
If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

Example
For n = "3.72", return "ERROR".

For n = "3.5", return "11.1".
*/
// Note: This solution does not pass the OJ because the stupid JAVA double/floating precison issue.
public class Solution {
    /**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
        // write your code here
        int left = 0;
        double right = 0.0;
        double weight = 0.1;
        boolean beforeDot = true;
        for(int i = 0; i < n.length(); i++){
        	char cur = n.charAt(i);
        	if(cur == '.'){
        		beforeDot = false;
        		continue;
        	} 
        	if(cur < '0' || cur > '9') return "ERROR";
        	int num = cur - '0';
        	if(beforeDot) left = left*10 + num;
        	else{
        		if(i == n.length()-1 && num != 5 && num != 0) return "ERROR";
        		right += weight*num;
        		weight *= 0.1; 
        	} 
        }

        StringBuilder res = new StringBuilder();
        while(left != 0){
        	res.insert(0, left&1);
        	left >>= 1;
        }
        if(beforeDot) return res.toString();
        res.append('.');

        while(right != 1.0){
        	String strVal = String.valueOf(right);
        	if(strVal.charAt(strVal.length()-1)!='5') return "ERROR";
        	right *= 2;
        	if(right > 1){
        		res.append(1);
        		right -= 1;
        	}
        	else res.append(0);
        }
        return res.toString();
    }
}
