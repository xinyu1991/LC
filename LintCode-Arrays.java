/*
Given a sorted array, remove the duplicates in place 
such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.
Example
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
*/

public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if(nums.length < 2) return nums.length;
        int index = 0;
        for(int i = 1; i < nums.length; i++){
        	if(nums[i] == nums[index]) continue;
        	nums[++index] = nums[i];
        }
        return index+1;
    }
}

/*
Easy Merge Sorted Array

Given two sorted integer arrays A and B, merge B into A as one sorted array.

Example
A = [1, 2, 3, empty, empty], B = [4, 5]

After merge, A will be filled as [1, 2, 3, 4, 5]

[4,5,e,e] [1, 6]

Note
You may assume that A has enough space (size that is greater or equal to m + n) 
to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
*/

class Solution {
    /**
     * @param A: sorted integer array A which has m elements, 
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int indexA = m-1, indexB = n-1;
        for(int i = m+n-1; i>=0 && indexB >= 0; i--){
        	if(indexA < 0 || A[indexA] < B[indexB]) A[i] = B[indexB--];
        	else A[i] = A[indexA--];
        }
    }
}

/*
Easy Product of Array Exclude Itself

25% Accepted
Given an integers array A.

Define B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1], calculate B WITHOUT divide operation.

For A = [1, 2, 3], return [6, 3, 2].
*/

public class Solution {
    /**
     * @param A: Given an integers array A
     * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        ArrayList<Long> res = new ArrayList();
        Long pre = (long)1;
        res.add(pre);
        if(A == null || A.size() <=1 ) return res;
        
        for(int i = 0; i < A.size()-1; i++){
        	pre *= A.get(i);
        	res.add(pre);
        }
        pre = (long)1;
        for(int i = A.size()-1; i >= 0; i--){
        	res.set(i, res.get(i)*pre);
        	pre *= A.get(i);
        }
        return res;
    }
}


/*
First Missing Positive

22% Accepted
Given an unsorted integer array, find the first missing positive integer.

Have you met this question in a real interview? Yes
Example
Given [1,2,0] return 3, and [3,4,-1,1] return 2.

Challenge
Your algorithm should run in O(n) time and uses constant space.
*/
public class Solution {
    /**    
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
        int len = A.length;
        for(int i = 0; i < len; i++){
        	while(A[i] > 0 && A[i] < len && (A[A[i]-1] != A[i])){
        		swap(A, i, A[i]-1);
        	}
        }
        int i = 0;
        for(; i < len; i++){
        	if(A[i] != i+1) break;
        }
        return i+1;
    }
    private void swap(int[] A, int i, int j){
    	A[i] += A[j];
    	A[j] = A[i] - A[j];
    	A[i] -= A[j];
    }
}


/*
2 Sum

27% Accepted
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are NOT zero-based.

Example
numbers=[2, 7, 11, 15], target=9

return [1, 2]

Note
You may assume that each input would have exactly one solution

Challenge
Either of the following solutions are acceptable:

O(n) Space, O(nlogn) Time
O(n) Space, O(n) Time
*/

public class Solution {
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap();
        int[] res = new int[2];
        for(int i = 0; i < numbers.length; i++){
        	int num = numbers[i];
        	if(map.containsKey(target-num)){
        		res[0] = map.get(target-num)+1;
        		res[1] = i+1;
        		break;
        	}
        	if(!map.containsKey(num)){
        		map.put(num, i);
        	}
        }
        return res;
    }
}


/*
Partition Array

25% Accepted
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") 
such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

Have you met this question in a real interview? Yes
Example
If nums = [3,2,2,1] and k=2, a valid answer is 1.

Note
You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length

Challenge
Can you partition the array in-place and in O(n)?
*/
public class Solution {
	/** 
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
	    int index = 0;
	    for(int i = 0; i < nums.length; i++){
	    	if(nums[i] < k) swap(nums, i, index++);
	    }
	    return index;
    }
    private void swap(int[] A, int i, int j){
    	A[i] += A[j];
    	A[j] = A[i] - A[j];
    	A[i] -= A[j];
    }
}


/*
3 Sum

19% Accepted
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:
-4, -1, -1, 0, 1, 2, 2, 2

(-1, 0, 1)
(-1, -1, 2)

[1,0,-1,-1,-1,-1,0,1,1,1]
[-1,-1,-1,-1,0,0,1,1,1,1]

Note
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

The solution set must not contain duplicate triplets.
*/


public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        Arrays.sort(numbers);

        for(int i = 0; i < numbers.length-2; i++){
        	if(i > 0 && numbers[i]==numbers[i-1]) continue;
        	int left = i+1, right = numbers.length-1;
        	while(left < right){
        		int sum = numbers[right] + numbers[left] + numbers[i];
        		if(sum == 0){
        			ArrayList<Integer> sub = new ArrayList();
        			sub.add(numbers[i]);
        			sub.add(numbers[left++]);
        			sub.add(numbers[right--]);
        			res.add(sub);
        			while(left < right && numbers[left] == numbers[left-1]) left++;
        			while(left < right && numbers[right] == numbers[right+1]) right--;
        		}
        		else if(sum > 0){
        			right--;
        		}
        		else{
        			left++;
        		}
            }
        }
        return res;
    }
}

/*
3 Sum Closest

29% Accepted
Given an array S of n integers, find three integers in S 
such that the sum is closest to a given number, target. Return the sum of the three integers.

Example
For example, given array S = {-1 2 1 -4}, and target = 1. 
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Note
You may assume that each input would have exactly one solution.

Challenge
O(n^2) time, O(1) extra space
*/

public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers ,int target) {
        Arrays.sort(numbers);
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < numbers.length-2; i++){
        	if(i > 0 && numbers[i] == numbers[i-1]) continue;
        	int left = i+1, right = numbers.length-1;
        	while(left < right){
        		int sum = numbers[i] + numbers[left] + numbers[right];
        		if(sum == target){
        			return sum;
        		}
        		if(Math.abs(sum-target) < Math.abs(res-target)) res = sum;
        		if(sum < target) left++;
        		else right--;
        	}
        }
        return res;
    }
}