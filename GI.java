Minimum Window String (Two Pointers, HashMap)
Use a hashMap to store the required characters, and its count, go through the original string to find the min length to meet the requirement.

public class Solution {
    public String minWindow(String s, String t) {
    if(s.equals(t)) return t;
	// The hashmap to store the char -> count pair of characters in t.
	HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
	int start = 0, end = 0, minLen = Integer.MAX_VALUE, count = t.length(), minStart = 0;

	char[] sArray = s.toCharArray();

	for(int i = 0; i < t.length(); i++){
		char c = t.charAt(i);
		if(hashMap.containsKey(c)){
			hashMap.put(c, hashMap.get(c) + 1);
		}
		else{
			hashMap.put(c, 1);
		}
	}

	while(start<sArray.length && end < sArray.length){
		
		char c = sArray[end];
		if(hashMap.containsKey(c)){
			if(hashMap.get(c) > 0){
				count--;
			}
			hashMap.put(c, hashMap.get(c) - 1);
		}
		
		while(count == 0 && start <= end){
	        if(end - start + 1 < minLen){
			    minLen = end - start +1;
			    minStart = start;
		    }
		    char startChar = sArray[start];
		    if(hashMap.containsKey(startChar)){
			    hashMap.put(startChar, hashMap.get(startChar) + 1);
			    if(hashMap.get(startChar) > 0){
				    count++;
			    }
		    }
		    start++;
	    }
	    end++;
	}
	return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart+minLen);
}
}

Parentness validation (String)



Maxmimum Reactangle (DP)
left, height, right
public int maxReact(char[][] board){
	if(board == null || board.length == 0) return 0;
	int row = board.length;
	int col = board[0].length;
	// DP value for left, right edge position, and height;
	int[] left = new int[col];
	int[] right = new int[col];
	int[] height = new int[col];
	Arrays.fill(right, col);

	int maxArea = 0;

	for(int i=0; i<row; i++){
		// Use curLeft to record the cur edge.
		int curLeft = 0, curRight = col;
		for(int j = 0; j < col; j++){
			if(board[i][j]=='1'){
				height[j]++;
				left[j] = Math.max(left[j], curLeft); 
			}
			else{
				height[j] = 0;
				left[j] = 0;
				curLeft = j+1;
			} 
		}
		for(int j = col-1; j>=0; j--){
			if(board[i][j]=='1'){
				right[j] = Math.min(right[j], curRight);
			}
			else{
				right[j] = col;
				curRight = j;
			}
		}

		for(int j = 0; j < col; j++){
			maxArea = Math.max(maxArea, (right[j] - left[j])*height[j]);
		}
	}
	return maxArea;
}



Maximum Square(DP)


Perfect Squares (DP)

Max Point

Add two linked List (1-2-3 + 1-7 = 1-4-0) O(n) time O(1) space

Plus 1

public class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
        	if(digits[i] != 9) {
        		digits[i]++;
        		return digits;
        	}
        	else{
        		digits[i] = 0;
        	}
        }

        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }
}



Maximum area in histogram(Stack)
public class Solution {
    public int largestRectangleArea(int[] height) {
        if(height == null || height.length==0) return 0;
        int max = 0;
        height = Arrays.copyOf(height, height.length+1);
        // A stack to store the index of a height;
        Stack<Integer> stack = new Stack<Integer>();

        for(int i = 0; i < height.length; i++){
        	while(!stack.isEmpty() && height[stack.peek()] > height[i]){
        		int topIndex = stack.pop();
        		// Get the width : from the current position(i) to the most left position with this height.
        		// Note : the left edge position of this height is the position of the peek element + 1!
        		int width = stack.isEmpty() ? i : i - (stack.peek() + 1);
        		// The maximal area will be the height at topIndex times width.
        		max = Math.max(max, width*height[topIndex]);
        	}
        	// push current index;
        	stack.push(i);
        }
        return max;
    }
}

HashTable:

Longest Consecutive Sequence
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

public class Solution {
    public int longestConsecutive(int[] nums) {
        // A map for a num and its maximum reach.
        HashMap<Integer, Integer> lengthMap = new HashMap<Integer, Integer>();
        int maxLen = 0;

        for(int num : nums){
        	if(lengthMap.containsKey(num)){
        		continue;
        	}

        	int left = lengthMap.containsKey(num-1) ? lengthMap.get(num-1) : 0;
        	int right = lengthMap.containsKey(num+1) ? lengthMap.get(num+1) : 0;
        	int newLen = right + left + 1;
        	lengthMap.put(num, newLen);

        	if(left != 0) lengthMap.put(num - left, newLen);
        	if(right != 0) lengthMap.put(num + right, newLen);
        	maxLen = maxLen < newLen ? newLen : maxLen;
        }
        return maxLen;
    }
}

Next Permutation


Binary Search:
Median of two sorted array.
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int len1 = num1.length, len2 = nums2.length;
    	if(len1 > len2) return findMedianSortedArrays(nums2, nums1);

    	int midLen = (len1+len2+1)/2;
    	int i = 0, j =0;
    	int num1 = 0, num2 = 0;
    	int left = 0, right = len1;
    	// Should be <= since we need to find the exact boundary.
    	while(left <= right){
    		i = (left+right)/2;
    		j = midLen - i;

    		if(i > 0 && j < len2 && nums2[j-1] > nums1[i]) left = i+1;
    		else if(j > 0 && i < len1 && num1[i-1] > nums2[j]) right = i-1;
    		else{
    			if(i==0) num1 = nums2[j-1];
    			else if(j==0) num1 = nums1[i-1];
    			else{
    				num1 = Math.max(nums1[i-1], nums2[j-1]);
    			}
    			break;
    		}
    	}
    	if((len1+len2)&1 == 1) return num1;

    	if(i==len1) num2 = nums2[j];
    	else if(j==len2) num2 = nums1[i];
    	else num2 = Math.min(nums1[i], nums2[j]);
    	return (num1+num2)/2.0;
    }
}

Find Kth largest number in array
public class Solution {
    public int findKthLargest(int[] nums, int k) {
    	return quickSelect(nums, k-1, 0, nums.length-1);
    }

    public int quickSelect(int[] nums, int k, int left, int right){
    	while(true){
    		int index = partition(nums, left, right);
    		if(index == k) return nums[k];
    		else if(index < k) left = index+1;
    		else right = index - 1;
    	}
    }

    public int partition(int[] nums, int left, int right){
    	int index = left;
    	for(int i = left; i < right; i++){
    		if(nums[i] > nums[right]){
    			swap(nums, i, index++);
    		}
    	}
    	swap(nums, index, right);
    	return index;
    }

    public void swap()
}


Search in rotated array. (Find Peek, binary search)




Merge K sorted list



String Numbers:
Integer to Roman 


Two pointers:
Trapping Rain water.


BackTracking/DFS:
Valid Sudoku (validate no duplicate in same row, column, square)



Find first missing positive integer:
Spot sort
[-1, 3, 5, 1, 4, 7] return 2 
public int firstMissingInteger(int[] nums){
	int len = nums.length;

	for(int i = 0; i < len; i++){
		// Swap if the value in position of (nums[i]-1) is not nums[i];
		while(nums[i] > 0 && nums[i] <= len && nums[nums[i]-1] != nums[i]){
			swap(nums, i, nums[i]-1);
		}
	}
	for(int i = 0; i < len; i++){
		if(nums[i] != i+1) return i+1;
	}
	return n+1;
}

public void swap(int[] nums, int i, int j){
	nums[i] = nums[i] + nums[j];
	nums[j] = nums[i] - nums[j];
	nums[i] -= nums[j];
}


Merge/Insert intervals:
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	ArrayList<Interval> result = new ArrayList();

    	for(Interval interval : intervals){
    		if(interval.start > newInterval.end){
    			result.add(newInterval);
    			newInterval = interval;
    		}
    		else if(interval.end < newInterval.start){
    			result.add(interval);
    		}
    		else{
    			newInterval.start = newInterval.start > interval.start ? interval.start : newInterval.start;
    			newInterval.end = newInterval.end < interval.end ? interval.end : newInterval.end;
    		}
    	}
    	result.add(newInterval);

    	return result;
    }
}

public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
    	if(intervals.size() < 2) return intervals;

    	Collections.sort(intervals, new Comparator<Interval>(){
    		@Override
    		public int compare(Interval i1, Interval i2){
    			return i1.start - i2.start;
    		}
    	});

    	ArrayList result = new ArrayList();

    	Interval pre = intervals.get(0);

    	for(int i = 1; i < intervals.size(); i++){
    		Interval cur = intervals.get(i);

    		if(pre.end < cur.start){
    			result.add(pre);
    			pre = cur;
    		}
    		else{
    			pre.end = pre.end < cur.end ? cur.end : pre.end;
    		}
    	}

    	result.add(pre);
    	return result;
    }
}



Wiggle Sort:

public int[] sort(int[] nums){
    int n = nums.length;
    if(n<2) return nums;

    int pre = nums[0];
    boolean inc = true;
    
    for(int i=1; i < n; i++){
    	if((nums[i] >= pre && inc) || (nums[i] <= pre && !inc){
    		nums[i-1] = pre;
    		pre = nums[i];
    	}
    	else{
    		nums[i-1] = nums[i];
    	}
    	inc = !inc;
    }
    nums[n-1] = pre;
    return nums;
}




Action Items:

1. Quick Sort, Heap Sort, Merge Sort, Select Sort, Bubble Sort, Insertion Sort.
public class QuickSort{
    public void quickSort(int[] nums){
        quickSort(nums, 0, nums.length-1);
    }

    public void quickSort(int[] nums, int left, int right){
        if(left <= right){
            int index = patition(nums, left, right);
            patition(nums, left, index -1);
            patition(nums, index+1, left);
        }
    }

    public int patition(int[] nums, int left, int right){
        int index = left;
        for(int i = left; i< right; i++){
            if(nums[i] < nums[right]) swap(nums, i, index++);
        }
        swap(nums, index, right);
        return index;
    }

    public void swap(int[] nums, int left, int right){
        nums[left] += nums[right];
        nums[right] = nums[left] - nums[right];
        nums[left] -= nums[right];
    }
}

public 




2. Power Of Two, Square Root.
http://blog.csdn.net/linhuanmars/article/details/20092829
http://blog.csdn.net/linhuanmars/article/details/20089131

3. Most Popular Number (State Maintain, Boyer-Mooer Method)