/*
Easy Unique Paths II

27% Accepted
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Have you met this question in a real interview? Yes
Example
For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note
m and n will be at most 100.
*/

public class Solution {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int[][] paths = new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int i = 0; i < obstacleGrid.length; i++){
        	for(int j = 0; j < obstacleGrid[0].length; j++){
        		if(i == 0 && j == 0) paths[i][j] = obstacleGrid[i][j] == 1 ? 0 : 1;
        		else if(i == 0) paths[i][j] = obstacleGrid[i][j] == 1 ? 0 :paths[i][j-1];
        		else if(j == 0) paths[i][j] = obstacleGrid[i][j] == 1 ? 0 :paths[i-1][j];
        		else paths[i][j] = obstacleGrid[i][j] == 1 ? 0 : paths[i-1][j] + paths[i][j-1];
        	}
        }
        return paths[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}

/*
Easy Climbing Stairs

39% Accepted
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Have you met this question in a real interview? Yes
Example
Given an example n=3 , 1+1+1=2+1=1+2=3

return 3
*/
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if(n < 0) return 0;
        int[] paths = new int[n+1];
        paths[0] = 1;
        paths[1] = 1;
        for(int i = 2; i <= n; i++){
        	paths[i] = paths[i-1] + paths[i-2];
        }
        return paths[n];
    }
}

/*
Easy Triangle

25% Accepted
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Have you met this question in a real interview? Yes
Example
For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(int[][] triangle) {
        if(triangle == null || triangle.length == 0) return 0;
        int[] min = new int[triangle.length];
        min[0] = triangle[0][0];
        for(int i = 1; i < triangle.length; i++){
        	for(int j = triangle[i].length-1; j >= 0; j--){
        		if(j == triangle[i].length-1) min[j] = min[j-1] + triangle[i][j];
        		else if( j == 0) min[j] = min[j] + triangle[i][j];
        		else min[j] = triangle[i][j] + Math.min(min[j], min[j-1]);
        	}
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < min.length; i++){
        	res = min[i] < res ? min[i] : res;
        }
        return res;
    }
}

/*
Easy Maximum Subarray

37% Accepted
Given an array of integers, find a contiguous subarray which has the largest sum.

Have you met this question in a real interview? Yes
Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Note
The subarray should contain at least one number.

Challenge
Can you do it in time complexity O(n)?
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(ArrayList<Integer> nums) {
        if(nums == null || nums.size()==0) return 0;
        int pre = nums.get(0);
        int max = pre;
        for(int i = 1; i < nums.size(); i++){
        	int num = nums.get(i);
        	pre = Math.max(nums.get(i), pre+nums.get(i));
        	max = Math.max(max, pre);
        }
        return max;
    }
}

/*
Medium Maximum Product Subarray

28% Accepted
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Have you met this question in a real interview? Yes
Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        int curMin = nums[0], curMax = nums[0], max = nums[0];
        int tempMin = curMin;
        for(int i = 1; i < nums.length; i++){
        	curMin = Math.min(nums[i], Math.min(nums[i]*curMin, nums[i]*curMax));
        	curMax = Math.max(nums[i], Math.max(nums[i]*tempMin, nums[i]*curMax));
        	max = Math.max(max, curMax);
        	tempMin = curMin;
        }
        return max;
    }
}

/*
Medium Edit Distance

29% Accepted
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Have you met this question in a real interview? Yes
Example
Given word1 = "mart" and word2 = "karma", return 3.
*/

public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        int[][] minChanges = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++) minChanges[i][0] = i;
        for(int i = 0; i <= word2.length(); i++) minChanges[0][i] = i;
        for(int i = 0; i < word1.length(); i++){
        	for(int j = 0; j < word2.length(); j++){
        		if(word1.charAt(i) == word2.charAt(j)) minChanges[i+1][j+1] = minChanges[i][j];
        		else minChanges[i+1][j+1] = 1 + Math.min(minChanges[i][j], Math.min(minChanges[i][j+1], minChanges[i+1][j]));
        	}
        }
        return minChanges[word1.length()][word2.length()];
    }
}

/*
Medium Distinct Subsequences

30% Accepted
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Have you met this question in a real interview? Yes
Example
Given S = "rabbbit", T = "rabbit", return 3.

Challenge
Do it in O(n2) time and O(n) memory.

O(n2) memory is also acceptable if you do not know how to optimize memory.
*/

public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        int[][] counts = new int[S.length()+1][T.length()+1];
        for(int i = 1; i <= S.length(); i++) counts[i][0] = 1;
        for(int i = 0; i <= T.length(); i++) counts[0][i] = 0;

        for(int i = 0; i < S.length(); i++){
        	for(int j = 0; j < T.length(); j++){
        		if(S.charAt(i) != T.charAt(j)) counts[i][j] = counts[i-1][j];
        		else counts[i][j] = counts[i-1][j-1] + counts[i-1][j];
        	}
        }
        return counts[S.length()][T.length()];
    }

    public int numDistinct(String S, String T){
    	int[] count = new int[T.length()+1];
    	count[0] = 1;
    	for(int i = 0; i < S.length(); i++){
    		// Note: We need to start from the end so that we can use the orignal values from last step.
    		for(int j = T.length()-1; j >= 0 ; j--){
    			if(S.charAt(i) == T.charAt(j)) count[j+1] = count[j] + count[j+1]; 
    		}
    	}
    	return count[T.length()];
    }
}


/*
Medium Word Break Show result 

12% Accepted
Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.

Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".
*/

public class Solution {
    /**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
    // DP solution, traversing every word in dict.
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++){
            for(String str: dict){
                if(str.length() <= i){
                    if(dp[i - str.length()]){
                        if(s.substring(i-str.length(), i).equals(str)){
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[s.length()];
    }

    // DP solution by spliting subString.
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++){
            for(int j = i-1; j >= 0; j--){
                if(dp[j] && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

/*
Medium Backpack

19% Accepted
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

Have you met this question in a real interview? Yes
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Note
You can not divide any item into small pieces.

Challenge
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.
*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        boolean[] canSum = new boolean[m+1];
        canSum[0] = true;
        int max = 0;
        for(int num : A){
        	for(int i = m; i > 0; i--){
        		canSum[i] = canSum[i] || (num <= i && canSum[i-num]);
        		if(canSum[i]) max = Math.max(max, i);
        	}
        }
        return max;
    }

    public int backPack(int m, int[] A){
    	boolean[][] canSum = new boolean[m+1][A.length+1];
    	Arrays.fill(canSum[0], true);
    	int max = 0;
    	for(int i = 1; i <= A.length; i++){
    		for(int j = 1; j <= m; j++){
    			canSum[j][i] = canSum[j][i-1] || (A[i-1] <= j && canSum[j-A[i-1]][i-1]);
    			if(canSum[j][i]) max = Math.max(max, j);
    		}
    	}
    	return max;
    }
}

/*
Medium Interleaving String

26% Accepted
Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.

Have you met this question in a real interview? Yes
Example
For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
Challenge
O(n2) time or better
*/

public class Solution {
    /**
     * Determine whether s3 is formed by interleaving of s1 and s2.
     * @param s1, s2, s3: As description.
     * @return: true or false.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length() != s3.length()) return false;
        boolean[][] canReach = new boolean[s1.length()+1][s2.length()+1];
        canReach[0][0] = true;
        for(int i = 1; i <= s1.length(); i++) canReach[i][0] = canReach[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        for(int i = 1; i <= s2.length(); i++) canReach[0][i] = canReach[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);
        
        for(int i = 0; i < s1.length(); i++){
        	for(int j = 0; j < s2.length(); j++){
        		canReach[i+1][j+1] = (canReach[i][j+1] && (s1.charAt(i) == s3.charAt(i+j+1))) ||
        		               (canReach[i+1][j] && (s2.charAt(j) == s3.charAt(i+j+1)));

        	}
        }
        return canReach[s1.length()][s2.length()];
    }
}