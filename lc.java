public findKthElement(int[] nums, int k){
    return findKthSmallest(nums, 0, nums.length -1, nums.length-k+1);
}

public findKthSmallest(int[] nums, int left, int right, int k){
    int pos = partition(nums, left, right);
    if(pos == k) return nums[pos];
    if(pos > k) return findKthSmallest(nums, pos + 1, right, k);
    else return findKthSmallest(nums, 0, pos, k);
}

public int partition(int[] nums, int left, int right){
    int low = left;
    while(left < right){
    	if(nums[left] < nums[right] && low != left){
            swap(nums, left, low);
            low++;
    	}
    	left++;
    }
    swap(nums, right, low);
    return low;
}

public void swap(int[] nums, int l1, int l2){
	int temp = nums[l1];
	nums[l1] = nums[l2];
	nums[l2] = temp;
}

def findKthLargest(self, nums, k):
    # convert the kth largest to smallest
    return self.findKthSmallest(nums, len(nums)+1-k)

def findKthSmallest(self, nums, k):
    if nums:
        pos = self.partition(nums, 0, len(nums)-1)
        if k > pos+1:
            return self.findKthSmallest(nums[pos+1:], k-pos-1)
        elif k < pos+1:
            return self.findKthSmallest(nums[:pos], k)
        else:
            return nums[pos]

# choose the right-most element as pivot   
def partition(self, nums, l, r):
    low = l
    while l < r:
        if nums[l] < nums[r]:
            nums[l], nums[low] = nums[low], nums[l]
            low += 1
        l += 1
    nums[low], nums[r] = nums[r], nums[low]
    return low

5 7 7 8 8 10, 8

2 2, 1

public int[] searchRange(int[] nums, int target){
    int[] result = new int[2];
    int left = 0, right = nums.length - 1;
    result[0] = searchLeftEdge(nums, left, right, target);
    result[1] = searchRightEdge(nums, left, right, target);
    return result;
}

public int searchLeftEdge(int[] nums, int left, int right, int target){
	while(left < right){
		int mid = left + (right-left)/2;
		if(nums[mid] > target) right = mid;
		else if(nums[mid] < target) left = mid+1;
		else{
			if(mid==left || nums[mid-1] != target) return mid;
			right = mid-1;
		}
	}
	return nums[left]==target ? left : -1;
}

public int searchRightEdge(int[] nums, int left, int right, int target){
	while(left < right){
		int mid = left + (right-left)/2;
		if(nums[mid] > target) right = mid;
		else if(nums[mid] < target) left = mid+1;
		else{
			if(mid==right || nums[mid+1] != target) return mid;
			left = mid+1;
		}
	}
	return nums[right]==target ? right : -1;
}


[1,2,3,4,5]
[3,4,5,1,2]

public int gasStation(int[] gas, int[] cost){
	int startIndex = 0;
	int sum = 0;
	int gasLeft = 0;
	for(int i = 0; i < gas.length; i++){
		if(gasLeft < 0){
			startIndex = i;
			gasLeft = 0;
		} 
		gasLeft += gas[i] - cost[i];
		sum += gas[i] - cost[i];
 	}

	return sum >= 0 ? startIndex : -1;
}

[100, 4, 200, 1, 3, 2] 4
public class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> lengthMap = new HashMap<Integer, Integer>();
        int maxlength = 0;
        
        for(int num : nums){
            if(lengthMap.containsKey(num)) continue;
            int left = lengthMap.containsKey(num-1) ? lengthMap.get(num-1) : 0;
            int right = lengthMap.containsKey(num+1) ? lengthMap.get(num+1) : 0;
            int sum = left+right+1;
            lengthMap.put(num, sum);
            maxlength = Math.max(maxlength, sum);

            if(left != 0) lengthMap.put(num-left, sum);
            if(right != 0) lengthMap.put(num+right, sum);
        }
        return maxlength;
    }
}

1 2 3   7 4 1
4 5 6   8 5 2
7 8 9   9 6 3

i,j -> j, n-1-i
public class Solution {
    public void rotate(int[][] matrix) {
    	if(matrix==null || matrix.length == 0) return;
        for(int i = 0; i < matrix.length; i++){
        	for(int j = i; j < matrix.length-i - 1; j++){
        		int start = matrix[i][j];
        		matrix[i][j] = matrix[n-1-j][i];
        		matrix[n-1-j][i] = matrix[n-1-i, n-1-j];
        		matrix[n-1-i, n-1-j] = matrix[j, n-1-i];
        		matrix[j, n-1-i] = start;
        	}
        }
    }

1 2 3   7 8 9   7 4 1
4 5 6   4 5 6   8 5 2
7 8 9   1 2 3   9 6 3
    public void rotate(int[][] matrix) {
    	if(matrix==null || matrix.length == 0) return;
        matrix.reverse();
        for(int i = 0; i < matrix.length / 2; i++){
        	for(int j = 0; j < matrix.length/2; j++){
        		swap(matrix, i, j);
        	}
        }
    }
}

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++){
        	if(!isValidSudokuPartially(board, i, 0, i, 8) ||
        	   !isValidSudokuPartially(board, 0, i, 8, i)){
        		return false;
        	}
        }
       	for(int i = 0; i < 3; i++){
       		for(int j = 0; j < 3; j++){
       			if(!isValidSudokuPartially(board, i*3, j*3, i*3+2, j*3+2))
       				return false;
       		}
     	}
        return true;
    }

    public boolean isValidSudokuPartially(char[][] board, int x1, int y1, int x2, int y2){
    	HashSet<Character> set = new HashSet<Character>();

    	for(int i = x1; i <= x2; i++){
    		for(int j = y1; j <= y2; j++){
    			if(board[i][j]!='.'){
    				if(set.contains(board[i][j])) return false;
    			    set.add(board[i][j]);
    			}
    		}
    	}
    	return true;
    }
}

[2,3,1,2,4,3] and s = 7, [4, 3] return 2;

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0, end = 0, int sum = 0;
        int minLen = Integer.MAX_VALUE;

        while(end < nums.length){
        	while(end < nums.length && sum < s){
        		sum += nums[end++];
        	}
        	if(sum < s) return break;
        	while(sum >= s) {
        		sum -= nums[start++];
        	}
        	minLen = Math.min(minLen, end-start+1);
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

// Two Points, one at end, one at start.
    public int minSubArrayLen(int s, int[] nums){
    	int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
    	while(end < nums.length){
    		sum += nums[end++];
    		while(sum >= s){
    			// End is one index out of the valid subarray.
    			minLen = Math.min(minLen, end-1-start+1);
    			sum -= nums[start++];
    		}
    	}
    	return minLen;
    }
}

15555 23

public class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor == 1) return dividend;

        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;

        long res = 0;
        long d1 = Math.abs(dividend);
        long d2 = Math.abs(divisor);

        while(d1 >= d2){
        	long temp = d2;
        	long power = 1;

        	while((temp << 1) <= d1){
        		temp <<= 1;
        		power <<= 1;
        	}
        	res += power;
        	d1 -= temp;
        }

        return res*sign;
    }
}

Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
[1,1] 2
[1, 2, 3] 3
[1,2,0,3,1,2,1,0,2,2,2,1,0,2] 2
public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        ListNode dummyPoint = dummy;
        ListNode node = head;
        while(node != null){
        	if(node.val < x){
        		ListNode temp = dummyPoint.next;
        		if(temp != null && temp.val == node.val){
        			dummyPoint = temp;
        			node = node.next;
        			continue;
        		}
        		ListNode next = node.next;
        		node.next = temp == null || next == null ? next : temp;
        		dummyPoint.next = node;
        		dummyPoint = dummyPoint.next;
        		node = next;
        	}
        	else{
        		node = node.next;
        	}
        }
        return dummy.next;
    }
}
[2, 1] 2
public class Solution {
    public ListNode partition(ListNode head, int x) {
    	ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
    	ListNode curr1 = dummy1, curr2 = dummy2;
    	while(head != null){
    		if(head.val < x){
    			curr1.next = head;
    			curr1 = head;
    		}
    		else{
    			curr2.next = head;
    			curr2 = head;
    		}
    		head = head.next;
    	}
    	curr2.next = null;
    	curr1.next = dummy2.next;
    	return dummy1.next;
    }
}

public class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        
        boolean firstRow = false, firstColumn = false;
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    if(i == 0) firstRow = true;
                    if(j == 0) firstColumn = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        
        if(firstRow){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[0][j] = 0;
            }
        }
        
        if(firstColumn){
            for(int i = 1; i < matrix.length; i++){
                matrix[i][0] = 0;
            }
        }
    }
}

Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
1 1
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
    	if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(head != null){
        	if(head.next != null && head.val == head.next.val){
        		while(head.next != null && head.val == head.next.val){
        		    head = head.next;
        	    }
        	}
        	else{
        		cur.next = head;
        		cur = head;
        	}
        	head = head.next;
        }
        return dummy.next;
    }
}
10
public class Solution {
    public int numDecodings(String s) {
        if(s.length() == 0 || s.charAt(0) == '0') return 0;

        int[] dp = new int[s.length()];
        dp[0] = 1;

        for(int i = 1; i < s.length(); i++){
        	if(s.charAt(i)=='0') dp[i-1] = 0;

        	if(s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6')){
        		dp[i] = dp[i-1] + 1;
        	}
        	else{
        		dp[i] = dp[i-1];
        	}
        }
        return dp[s.length()-1];
    }
}

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        int length = beginWord.length();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordList.add(endWord);
        int step = 0;

        while(!queue.isEmpty()){
            step++;
            LinkedList<String> level = new LinkedList<String>();
            while(!queue.isEmpty()){
                String current = queue.poll();
                if(current == endWord){
                    return step;
                }
                char[] table = current.toCharArray();
            
                // Add the neighbors to the level list.
                for(int i = 0; i < length; i++){
                    for(char c = 'a'; c <= 'z'; c++){
                        char tempC = table[i];
                        table[i] = c;
                        String temp = String.valueOf(table);
                        table[i] = tempC;
                        if(wordList.contains(temp)){
                            level.add(temp);
                            wordList.remove(temp);
                        }
                    }
                }
            }
            queue = level;
        }
        return 0;
    }
}

public class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0) return;
        solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j<9; j++){
                if(board[i][j] == '.'){
                    for(char k = '1'; k <= '9'; k++){
                        if(isValidSudoku(board, i, j, k)){
                            board[i][j] = k;
                            if(solve(board)){
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board, int row, int column, char c){
        for(int i = 0; i < 9; i++){
            if(board[row][i] == c || board[i][column] == c) return false;
        }

        for (int i = (row/3)*3; i <= (row/3)*3+2; i++) {
            for(int j = (column/3)*3; j <= (column/3)*3+2; j++){
                if(board[i][j] == c) return false;
            }
        }
        return true;
    }
}


Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '+' ||
               input.charAt(i) == '-' ||
               input.charAt(i) == '*'){
                List<Integer> leftPart = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightPart = diffWaysToCompute(input.substring(i+1));
                for(Integer left : leftPart){
                    for(Integer right : rightPart){
                        int value = 0;
                        switch(input.charAt(i)){
                            case '+':
                                value = left + right;
                                break;
                            case '-':
                                value = left - right;
                                break;
                            case '*':
                                value = left * right;
                                break;
                        }
                        res.add(value);
                    }
                }
            }
        }

        // Do not contain any operator, just return the value.
        if(res.isEmpty()) res.add(Integer.valueOf(input));
        return res;
    }
}

")()())" return 4
public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Character> stack = new Stack<Character>();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c==')' && stack.isEmpty()){
                continue;
            }
            if(c==')' && stack.peek() == '('){
                stack.pop();
                count += 2;
            }
            else{
                stack.push(c);
            }
        }
        return count;
    }
}

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If the root is null, return.
        // If the root is p or q, find a possible result;
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both sides are not null, then the root will be the result.
        if(left != null && right != null) return root;
        if(left != null) return left;
        if(right != null) return right;
        return null;

        return left == null ? right : right == null ? left : root;
    }
}


For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

public class Solution {
    public int sumNumbers(TreeNode root) {
        List<List<TreeNode>> result = traverse(root);
        int sum = 0;
        for (List<TreeNode> path : result){
            int pathSum = 0;
            for(int i = 0 ; i < path.size(); i++){
                pathSum = 10*pathSum + path.get(i).val;
            }
            sum += pathSum;
        }
        return sum;
    }
    
    public List<List<TreeNode>> traverse(TreeNode root){
        List<List<TreeNode>> result = new ArrayList();
        if(root == null) return result;
        if(root.left == null && root.right == null) {
            ArrayList<TreeNode> list = new ArrayList();
            list.add(root);
            result.add(list);
            return result;
        }
        
        for(List<TreeNode> nodelist : traverse(root.left)){
            nodelist.add(0, root);
            result.add(nodelist);
        }
        for(List<TreeNode> nodelist : traverse(root.right)){
            nodelist.add(0, root);
            result.add(nodelist);
        }
        return result;
    }

    public int sumNumbers(TreeNode root){
        return sumNumbers(root, 0);
    }

    public int sumNumbers(TreeNode root, int sum){
        if(root == null) return 0;
        sum = sum*10 + root.val;
        if(root.left == null && root.right == null) return sum;
        return sumNumbers(root.left, sum) + sumNumbers(root.right, sum);
    }
}

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> result = new ArrayList();
        helper(result, board, n, 0);
        return result;
    }
    
    public void helper(List<List<String>> res, char[][] board, int n, int rowIndex){
        if(rowIndex == n){
            res.add(construct(board));
            return;
        }
        
        for(int col = 0; col < n; col++){
            if(isValid(board, rowIndex, col, n)){
                board[rowIndex][col] = 'Q';
                helper(res, board, n, rowIndex+1);
                board[rowIndex][col] = '.';
            }
        }
    }
    
    public boolean isValid(char[][] board, int row, int col, int n){
        for(int i = 0; i < n; i++){
            if(board[i][col] == 'Q') return false; 
        }
        
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--){
            if(board[i][j] == 'Q') return false;
        }
        
        for(int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++){
            if(board[i][j] == 'Q') return false;
        }
        return true;
    }
    
    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}

