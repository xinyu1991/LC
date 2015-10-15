/*
Single Number  Easy

58% Accepted
Given 2*n + 1 numbers, every numbers occurs twice except one, find it.

Have you met this question in a real interview? Yes
Example
Given [1,2,2,1,3,4,3], return 4

Challenge
One-pass, constant extra space.
*/

public class Solution {
	/**
	 *@param A : an integer array
	 *return : a integer 
	 */
	public int singleNumber(int[] A) {
		if (A.length == 0) {
			return 0;
		}

		int n = A[0];
		for(int i = 1; i < A.length; i++) {
			n = n ^ A[i];
		}

		return n;
	}
}

/*
Easy Majority Number Show result 

40% Accepted
Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

Example
Given [1, 1, 1, 1, 2, 2, 2], return 1

Challenge
O(n) time and O(1) extra space
*/

public class Solution {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int res = nums.get(0);
        int count = 0;
        for(int i = 0; i < nums.size(); i++){
            if(res == nums.get(i)) count++;
            else count--;
            if(count == 0){
                res = nums.get(i);
                count = 1;
            } 
        }
        return res;
    }
}

/* Medium 

Gas Station

29% Accepted
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Have you met this question in a real interview? Yes
Example
Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. The starting gas station's index is 2.

Note
The solution is guaranteed to be unique.

Challenge
O(n) time and O(1) extra space

/*

public class Solution {
    /**
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // write your code here
        int sum = 0;
        int gasLeft = 0;
        int startIndex = 0;
        
        for(int i = 0; i < gas.length; i++){
            if(gasLeft < 0){
                gasLeft = 0;
                startIndex = i;
            }
            sum += gas[i] - cost[i];
            gasLeft += gas[i] - cost[i];
        }
        return sum >= 0 ? startIndex : -1;
    }
}

/* Medium

Largest Number

17% Accepted
Given a list of non negative integers, arrange them such that they form the largest number.

Example
Given [1, 20, 23, 4, 8], the largest formed number is 8423201.

Note
The result may be very large, so you need to return a string instead of an integer.
*/

public class Solution {
    /**
     *@param num: A list of non negative integers
     *@return: A string
     */
    public String largestNumber(int[] num) {
        String[] snum = new String[num.length];
        for(int i = 0; i < num.length; i++){
        	snum[i] = String.valueOf(num[i]);
        }
        Arrays.sort(snum, new Comparator<String>(){
        	@Override
        	public int compare(String s1, String s2){ 
        		String res1 = s1+s2;
        		String res2 = s2+s1;
        		return res2.compareTo(res1); 
        	}
        });
        String res = "";
        for(String s : snum){
        	res += s;
        	if(s.equals("0")) break;
        }
        return res;
    }
}

/*
Delete Digits

16% Accepted
Given string A representative a positive integer which has N digits, 
remove any k digits of the number, 
the remaining digits are arranged according to the original order to become a new positive integer.

Find the smallest integer after remove k digits.

N <= 240 and k <= N,

Example
Given an integer A = "178542", k = 4  return a string "12"

721061835790467 8 -> 0130467

1785 42, 4 492
*/

public class Solution {
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
    	StringBuilder res = new StringBuilder();
    	if(A == null || A.length() == 0 || A.length() == k) return res.toString();
    	char[] arr = A.toCharArray();
    	int selStart = 0, selEnd = k-1;
    	boolean stopSwitch = false;
    	for(int i = k; i < arr.length; i++){
    		int min = Integer.MAX_VALUE;
    		int minIndex = -1;
    		if(!stopSwitch){
    			for(int j = selStart; j <= selEnd; j++){
    				if(min > arr[j] - '0'){
    					min = arr[j] - '0';
    					minIndex = j;
    				}
    			}
    		}
    		
    		if(!stopSwitch && arr[i] - '0' >= min){
    			res.append(min);
    			selStart = minIndex+1;
    			selEnd++;
    		}
    		else{
    			res.append(arr[i]);
    			stopSwitch = true;
    		}
    	}
        return res.toString();
    }
}

/* Medium 

Next Permutation

23% Accepted
Given a list of integers, which denote a permutation.

Find the next permutation in ascending order.
Example
For [1,3,2,3], the next permutation is [1,3,3,2]

For [4,3,2,1], the next permutation is [1,2,3,4]

2 5 4 3 1  ->  3 1 2 5 4
1 3 3 -> 3 1 3

Note
The list may contains duplicate integers.
*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    public int[] nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) return nums;
        int index = nums.length-1;
        int pos = -1;
        while(index > 0){
        	if(nums[index] > nums[index-1]) {
        		pos = index-1;
        		break;
        	}
        	index--;
        }
        if(pos >= 0){
        	for(int i = nums.length-1; i > pos; i--){
        		if(nums[i] <= nums[pos]) continue;
        		swap(nums, i, pos);
        		break;
        	}
        }
        
        int left = pos+1, right = nums.length-1;
        while(left < right){
        	swap(nums, left++, right--);
        }
    }
    private void swap(int[] nums, int i, int j){
    	nums[i] += nums[j];
    	nums[j] = nums[i] - nums[j];
    	nums[i] -= nums[j];
    }
}
