/*
Two Sum My Submissions Question Solution 
Total Accepted: 148668 Total Submissions: 792827 Difficulty: Medium
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        if(nums == null || nums.length < 2) return res;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
        	int left = target - nums[i];
        	if(map.containsKey(left)){
        		res[0] = map.get(left) + 1;
        		res[1] = i+1;
        		return res;
        	}
        	map.put(nums[i], i);
        }
        return res;
    }
}


/*
Two Sum II - Input array is sorted 

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if(numbers == null || numbers.length < 2) return res;
        int left = 0, right = numbers.length-1;
        
        while(left < right){
        	int sum = numbers[right] + numbers[left];
        	if(sum == target){
        		res[0] = left+1;
        		res[1] = right+1;
        		return res;
        	}
        	if(sum < target) left++;
        	else right--;
        }
        return res;
    }
}

/*
Reverse Words in a String II 

Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
*/

public class Solution {
    public void reverseWords(char[] s) {
        if(s == null || s.length < 2) return;
        reverse(s, 0, s.length-1);
        int start = 0, end = 0;
        for(int i = 0; i < s.length; i++){
        	if(i != s.length - 1 && s[i] != ' ') continue;
        	if(i == s.length-1) end = i;
        	else if(s[i] == ' ') end = i-1;
        	reverse(s, start, end);
        	start = i+1;
        }
    }

    public void reverse(char[] s, int start, int end){
    	while(start < end){
    		char temp = s[start];
    		s[start++] = s[end];
    		s[end--] = temp;
    	}
    }
}

/*
Validate Binary Search Tree 

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/

public class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        addLeftChild(root, stack);
        TreeNode pre = null;
        while(!stack.isEmpty()){
        	TreeNode top = stack.pop();
        	if(pre != null && pre.val >= top.val) return false;
        	pre = top;
        	if(top.right != null) addLeftChild(top.right, stack);
        }
        return true;
    }

    public void addLeftChild(TreeNode node, Stack<TreeNode> stack){
    	while(node != null){
    		stack.push(node);
    		node = node.left;
    	}
    }
}

/*
Valid Parentheses My Submissions Question Solution 
Total Accepted: 75040 Total Submissions: 276542 Difficulty: Easy
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

public class Solution {
    public boolean isValid(String s) {
        if(s == null) return false;
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
        	char cur = s.charAt(i);
        	if(isLeftPart(cur)) stack.push(cur);
        	else if(stack.isEmpty() || !isMatch(stack.peek(), cur)) return false;
        	else stack.pop();
        }
        return stack.isEmpty();
    }

    public boolean isLeftPart(char c){
    	return c == '(' || c == '{' || c == '[';
    }

    public boolean isMatch(char l, char r){
    	switch(l){
    		case '(':
    			return r == ')';
            case '{':
                return r == '}';
            case '[':
                return r == ']';
            default:
                return false;    
    	}
    }
}

/* Tag: BackTracking.
Generate Parentheses 
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        if(n <= 0) return res;
        helper(res, "", 0, 0, n);
        return res;
    }
    
    public void helper(List<String> res, String path, int open, int close, int max){
        if(path.length() == max*2){
            res.add(path);
            return;
        }
        if(open < max){
        	// Add the "(" to max first.
            helper(res, path+"(", open+1, close, max);
        }
        if(close < open){
            helper(res, path+")", open, close+1, max);
        }
    }
}

/*
Longest Valid Parentheses My Submissions Question Solution 
Total Accepted: 48292 Total Submissions: 225934 Difficulty: Hard
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

public class Solution {
    public int longestValidParentheses(String s) {
        
    }
}


/*
Read N Characters Given Read4 

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
*/

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        boolean eof = false;
        int length = 0;

        while(!eof && length < n){
        	int r = read4(buffer);
        	if(r < 4){
        		eof = true;
        	}
        	int cur = Math.min(r, n-length);
        	for(int i = 0; i < cur; i++){
        		buf[length+i] = buffer[i];
        	}
        	length += cur;
        }
        return length;
    }
}

// Read N Characters Given Read4 II - Call Multiple Times
public class Solution extends Reader4 {
    Queue<Character> lastCallBuffer = new LinkedList();

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
    	int length = 0;
    	char[] buffer = new char[4];
    	boolean eof = false;
    	while(length < n && !lastCallBuffer.isEmpty()){
    		buf[length++] = lastCallBuffer.poll();
    	}
    	while(!eof && length < n){
    		int cur = read4(buffer);
    		if(cur < 4) eof = true;

    		int toAdd = Math.min(cur, n-length);
    		for(int i = 0; i < toAdd; i++){
    			buf[length+i] = buffer[i];
    		}

    		if(n-length < cur){
    			for(int i = n-length; i < cur; i++){
    				lastCallBuffer.offer(buffer[i]);
    			}
    		}
    		length+=toAdd;
    	}
    	return length;        
    }
}

/*
Valid Anagram My Submissions Question Solution 
Total Accepted: 32892 Total Submissions: 87975 Difficulty: Easy
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        int[] charCounts = new int[26];
        for(char c : s.toCharArray()){
            charCounts[c-'a']++;
        }
        for(char c: t.toCharArray()){
            if(--charCounts[c-'a'] < 0) return false;
        }
        for(int count : charCounts){
            if(count != 0) return false;
        }
        return true;
    }
}

/*
Group Anagrams My Submissions Question Solution 
Total Accepted: 52924 Total Submissions: 213038 Difficulty: Medium
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
*/
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList();
        if(strs == null || strs.length == 0) return res;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        Arrays.sort(strs);
        for(String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            List<String> list = map.get(key);
            if(list == null) list = new ArrayList();
            list.add(str);
            map.put(key, list);
        }
        for(List<String> list : map.values()){
            res.add(list);
        }
        return res;
    }
}

/*
Merge Two Sorted Lists 

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
*/
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }
            else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        
        return dummy.next;
    }
}

/*
Reverse Linked List
*/

public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode nex = null;
        while(head != null){
        	dummy.next = head;
        	head = head.next;
        	dummy.next.next = nex;
        	nex = dummy.next;
        }
        return dummy.next;
    }
}

/*
ntersection of Two Linked Lists My Submissions Question Solution 
Total Accepted: 48603 Total Submissions: 164821 Difficulty: Easy
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lengthA = 0, lengthB = 0;
        ListNode nodeA = headA, nodeB = headB;
        while(nodeA != null){
        	nodeA = nodeA.next;
        	lengthA++;
        }
        while(nodeB != null){
        	nodeB = nodeB.next;
        	lengthB++;
        }
        nodeA = headA;
        nodeB = headB;
        
        while(lengthA > lengthB){
        	nodeA = nodeA.next;
        	lengthA--;
        }
        while(lengthB > lengthA){
        	nodeB = nodeB.next;
        	lengthB--;
        }
        while(nodeA != null && nodeA != nodeB){
        	nodeA = nodeA.next;
        	nodeB = nodeB.next;
        }
        return nodeA;
    }
}

/*
Subsets 
Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> path = new ArrayList();
        Arrays.sort(nums);
        res.add(path);
        helper(res, path, 0, nums);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> path, int startIndex, int[] nums){
    	for(int i = startIndex; i < nums.length; i++){
    		path.add(nums[i]);
    		res.add(new ArrayList(path));
    		helper(res, path, i+1, nums);
    		path.remove(path.size()-1);
    	}
    }
}


/*
Lowest Common Ancestor of a Binary Search Tree My Submissions Question Solution 
Total Accepted: 33197 Total Submissions: 87686 Difficulty: Easy
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
*/
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        if(root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        return root;
    }
}

/*
Level Order Traversal
*/

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList();
            for(int i = 0; i < size; i++){
                TreeNode front = queue.poll();
                if(front.left != null) queue.offer(front.left);
                if(front.right != null) queue.offer(front.right);
                level.add(front.val);
            }
            res.add(level);
        }
        return res;
    }
}

/*
Binary Tree Right Side View
*/

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode front = queue.poll();
                if(front.left != null) queue.offer(front.left);
                if(front.right != null) queue.offer(front.right);
                if(i==size-1) res.add(front.val);
            }
        }
        return res;
    }
}

/*
Rotate Image My Submissions Question Solution 
Total Accepted: 50254 Total Submissions: 153852 Difficulty: Medium
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

public class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length <= 1) return;
        reverseLevel(matrix);
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = i+1; j < matrix.length; j++){
                swap(matrix, i, j);
            }
        }
    }
    
    public void reverseLevel(int[][] matrix){
        int top = 0, bottom = matrix.length-1;
        while(top < bottom){
            int[] temp = matrix[top];
            matrix[top++] = matrix[bottom];
            matrix[bottom--] = temp;
        }
    }
    
    public void swap(int[][] matrix, int i, int j){
        matrix[i][j] += matrix[j][i];
        matrix[j][i] = matrix[i][j] - matrix[j][i];
        matrix[i][j] -= matrix[j][i];
    }
}