/* 1
struct LogEntry{
string candidate; 投票姓名
int time; 投票时间
};
string findWinner(int time, vector<LogEntry> &logs); 让找出在
这个时间时候的winner. c1(1), c2(2), c1(2), c2(3),c2(4) 括号里是投票时间。 所以
findWinner(2, logs) = c1;
findWinner(4, logs) = c2;
用的hash表。找出最多的那个（投票在此时间后的不算）
*/


/* 1
第⼆二题（follow up）
给一个时间，找出前k个winner。
我的做法是⽤用hash表 先统计每个candidate的票数， 形成⼀一个
array，然后就是找前k大个数。 ⽤用的quick select。刚刚写完，
partition的时候估计有bug。
*/

/* 4
设计合并若干个字符串到一个字符串的encoding算法与对应的decoding算法
string encode(vector<string>& list);
vector<string> decode(string s);
*/


/* 4
给⼀一个字符串数组，找出这样的字符串对（str1,str2），使得
1，两个字符串不包含⼀一样的字符，2. ⻓长度乘积最⼤大
*/

/* 3
返回一个整数的平方和表示，使得个数最小
*/


/*
3Sum Smaller 
Total Accepted: 2435 Total Submissions: 7056 Difficulty: Medium
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?

*/

public int threeSumSmaller(int[] nums, int target){
	if(nums == null || nums.length < 3) return 0;
	Arrays.sort(nums);
	int count = 0;
	for(int i = 0; i < nums.length - 2; i++){
		int left = i+1, right = nums.length - 1;
		while(left < right){
			int sum = nums[i] + nums[left] + nums[right];
			if(sum < target){
				// Note: when you move left, the right index could be in any position between left and right.
				// Example: -2, 0, 1, 3  i =0, left = 1, right = 3, you should add 2 here because when right = 2, it also works.
				// count++; Wrong
				count += right - left;
				left++;
			}
			else{
				right--;
			}
		}
	}
	return count;
}

/*
Alien Dictionary 
Total Accepted: 1919 Total Submissions: 10633 Difficulty: Hard
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

public class Solution {
   	public class charNode{
		public Character val;
		public List<charNode> neighbors;
		public int dependencyCount;
		public charNode(Character c){
			dependencyCount = 0;
			val = c;
			neighbors = new ArrayList();
		}

		public void addNeighbor(charNode neighbor){
			this.neighbors.add(neighbor);
			neighbor.dependencyCount += 1;
		}
	}

	public String alienOrder(String[] words) {
		StringBuilder res = new StringBuilder();
		if(words == null || words.length == 0) return res.toString();
		if(words.length == 1) return words[0];
    	HashMap<Character, charNode> nodeMap = new HashMap();

	    for(int i = 0; i < words.length-1; i++){
    		char[] cur = words[i].toCharArray();
    		char[] nex = words[i+1].toCharArray();
    		boolean orderChecked = false;
	    	int j = 0;
    		for(;j < cur.length && j < nex.length; j++){
    			if(cur[j] == nex[j]){
	    			if(!nodeMap.containsKey(cur[j])) nodeMap.put(cur[j], new charNode(cur[j]));
    				continue;
    			}
    			if(orderChecked){
    				if(!nodeMap.containsKey(cur[j])) nodeMap.put(cur[j], new charNode(cur[j]));
    				if(!nodeMap.containsKey(nex[j])) nodeMap.put(nex[j], new charNode(nex[j]));
    				continue;
    			}
	    		charNode curNode = nodeMap.get(cur[j]);
    			charNode nexNode = nodeMap.get(nex[j]);
	    		if(curNode == null) curNode = new charNode(cur[j]);
    			if(nexNode == null) nexNode = new charNode(nex[j]);
    			curNode.addNeighbor(nexNode);
    			nodeMap.put(cur[j], curNode);
	    		nodeMap.put(nex[j], nexNode);
    			orderChecked = true;
    		}
	    	while(j < cur.length){
	    		if(!nodeMap.containsKey(cur[j])) nodeMap.put(cur[j], new charNode(cur[j]));
    			j++;
    		}
    		while(j < nex.length){
	    		if(!nodeMap.containsKey(nex[j])) nodeMap.put(nex[j], new charNode(nex[j]));
    			j++;
    		}
    	}

    	Queue<charNode> queue = new LinkedList();
	    for(charNode c : nodeMap.values()){
	    	if(c.dependencyCount == 0){
    			queue.offer(c);
    			res.append(c.val);
    		} 
    	}
    	while(!queue.isEmpty()){
    		charNode front = queue.poll();
	    	for(charNode neighbor : front.neighbors){
    			if(neighbor.dependencyCount == 0) continue;
	    			neighbor.dependencyCount--;
    			if(neighbor.dependencyCount == 0){
	    			queue.offer(neighbor);
    				res.append(neighbor.val);
    			}
    		}
    	}
    	for(charNode c: nodeMap.values()){
	        if(c.dependencyCount != 0) return "";
	    }
	    return res.toString();
	}
}

/*
Paint Fence 
Total Accepted: 2214 Total Submissions: 8479 Difficulty: Easy
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

public class Solution {
    public int numWays(int n, int k) {
        if(n <= 1) return k*n;
        int diff = k, same = 0;
        for(int i = 2; i <= n; i++){
        	int tempSame = same;
        	same = diff;
        	diff = (diff+tempSame)*(k-1);
        }
        return same+diff;
    }
}

/*
Palindrome Permutation My Submissions Question Solution 
Total Accepted: 2905 Total Submissions: 6230 Difficulty: Easy
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.
*/

public class Solution {
    public boolean canPermutePalindrome(String s) {
    	if(s==null) return false;
    	if(s.length() <= 1) return true;
        HashMap<Character, Integer> charMap = new HashMap();

        for(int i = 0; i < s.length(); i++){
        	char c = s.charAt(i);
        	if(charMap.containsKey(c)){
        		charMap.put(c, charMap.get(c)+1);
        	}
        	else charMap.put(c, 1);
        }
        int oddCount = 0;
        for(int count : charMap.values()){
        	if(count%2 == 1){
        		if(oddCount > 0) return false;
        		oddCount++;
        	} 
        }
        return true;
    }
}

/*
Palindrome Permutation II My Submissions Question Solution 
Total Accepted: 1559 Total Submissions: 6681 Difficulty: Medium
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].
*/
public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList();
        if(s == null || s.length() == 0) return res;
        HashMap<Character, Integer> charMap = new HashMap();
        for(char c : s.toCharArray()){
        	if(charMap.containsKey(c)) charMap.put(c, charMap.get(c)+1);
        	else charMap.put(c, 1);
        }
        String halfString = "";
        String center = "";

        for(char c : charMap.keySet()){
        	int charCount = charMap.get(c);
        	if(charCount % 2 == 1){
        		if(!center.equals("")) return res;
        		center += c;
        		charCount--;
        	}
        	for(int i = 0; i < charCount/2; i++) halfString += c;
        }
        helper(res, new StringBuilder(), halfString, center, new boolean[halfString.length()]);
        return res;
    }

    public void helper(List<String> res, StringBuilder path, String input, String center, boolean[] visited){
    	if(path.length() == input.length()){
    	    res.add(path.toString()+center+path.reverse().toString());
    	    // Note: if you use StringBuilder.reverse() and want to keep its orginal order, you need to use it again.
    	    path.reverse();
    	    return;
    	} 
    	for(int i = 0; i < input.length(); i++){
    		if(!visited[i]){
    			char c = input.charAt(i);
    			path.append(c);
    			visited[i] = true;
    			helper(res, path, input, center, visited);
    			visited[i] = false;
    			path.deleteCharAt(path.length()-1);
    			while(i+1 < input.length() && input.charAt(i+1) == c) i++;
    		}
    	}
    }
}

/*
Meeting Rooms 
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.

*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length < 2) return true;
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });

        Interval pre = intervals[0];
        for(int i = 1; i < intervals.length; i++){
            if(pre.end > intervals[i].start) return false;
            pre = intervals[i];
        }
        return true;
    }
}


/* Meeting Rooms II 
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/

public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        if(intervals.length == 1) return 1;
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        // A priority queue of the end time of meetings.
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(Interval interval : intervals){
            if(!queue.isEmpty() && queue.peek() <= interval.start) queue.poll();
            queue.offer(interval.end);
        }

        return queue.size();
    }
}

/*
Missing Ranges 
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res  = new ArrayList();
        if(nums == null || nums.length == 0) res.add(createRangeString(lower, upper));
        for(int i = 0; i < nums.length; i++){
            if(i == 0 && nums[i] != lower) res.add(createRangeString(lower, nums[i]-1));
            if(i != 0 && nums[i] != nums[i-1] + 1) res.add(createRangeString(nums[i-1]+1, nums[i]-1));
            if(i == nums.length - 1 && nums[i] != upper) res.add(createRangeString(nums[i]+1, upper));
        }

        return res;
    }

    public String createRangeString(int start, int end){
        if(start == end) return String.valueOf(start);
        return start + "->" + end;
    }
}

/*
Encode and Decode Strings 
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/

public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encode = new StringBuilder();
        if(strs == null || strs.size() == 0) return encode.toString();
        encode.append(strs.size() + "_");
        for(String str : strs) encode.append(str.length() + "_");
        for(String str : strs) encode.append(str);
        return encode.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList();
        if(s == null || s.length() == 0) return res;
        String[] lens = s.split("_");
        int size = Integer.valueOf(lens[0]);
        int[] stringLens = new int[size];
        int startIndex = 0;

        for(int i = 0; i < size; i++) stringLens[i] = Integer.valueOf(lens[i+1]);
        for(int i = 0; i <= size; i++) startIndex += lens[i].length()+1;

        for(int i = 0; i < size; i++){
            if(stringLens[i] == 0) res.add("");
            else res.add(s.substring(startIndex, startIndex+stringLens[i]));
            startIndex += stringLens[i];
        }
        return res;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));



/*
Flatten 2D Vector My Submissions Question Solution 
Total Accepted: 2529 Total Submissions: 8783 Difficulty: Medium
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/
public class Vector2D {
    int listIndex;
    int elementIndex;
    List<List<Integer>> list;
    public Vector2D(List<List<Integer>> vec2d) {
        list = vec2d;
    }

    public int next() {
        while(list.get(listIndex).isEmpty()) listIndex++;
        int val = list.get(listIndex).get(elementIndex);
        if(elementIndex < list.get(listIndex).size() - 1) elementIndex++;
        else{
            listIndex++;
            elementIndex = 0;
        }
        return val;
    }

    public boolean hasNext() {
        if(listIndex < list.size() && elementIndex < list.get(listIndex).size()) return true;
        for(int i = listIndex+1; i < list.size(); i++){
            if(list.get(i).size() > 0) return true;
        }
        return false;
    }
}

// Using Iterator.
public class Vector2D {
    Iterator<List<Integer>> listIter;
    Iterator<Integer> intIter;
    public Vector2D(List<List<Integer>> vec2d) {
        listIter = vec2d.iterator();
    }

    public int next() {
        return intIter.next();
    }

    public boolean hasNext() {
        while((intIter == null || !intIter.hasNext()) && listIter.hasNext()) intIter = listIter.next().iterator();
        return intIter != null && intIter.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */


/*
LRU Cache My Submissions Question Solution 
Total Accepted: 52484 Total Submissions: 340216 Difficulty: Hard
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/

// Using LinkedHashMap
public class LRUCache {
    Map<Integer, Integer> map;
    public LRUCache(int capacity) {
        // 16 is the initial capacity.
        // 0.75 is the load factor, indicates when to double the size.
        // true : order by access. false: order by insertion.
        map = new LinkedHashMap(16, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry eldest){
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        return map.get(key);
    }
    
    public void set(int key, int value) {
        map.put(key, value);
    }
}

// Using HashMap and DoubleLinkedList
public class LRUCache {
    int capacity, count;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    // head is the placeholder node in the front of the list.
    // tail is the placeholder node in the end of the list.
    Node head, tail;

    public LRUCache(int cacheCapacity) {
        count = 0;
        capacity = cacheCapacity;
        
        head = new Node();
        head.pre = null;
        
        tail = new Node();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }
        
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            moveToHead(node);
        }
        else {
            Node node = new Node(key, value);
            map.put(key, node);
            addNode(node);
            count++;
            if(count > capacity){
                Node tail = removeTail();
                map.remove(tail.key);
                count--;
            }
        }
    }
    
    private Node removeTail(){
        Node res = tail.pre;
        removeNode(res);
        return res;
    }

    private void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }

    private void addNode(Node node){
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(Node node){
        Node preNode = node.pre;
        Node nextNode = node.next;

        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    private class Node{
        int key;
        int val;
        Node pre;
        Node next;

        public Node(){

        }

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}


/*
Closest Binary Search Tree Value 

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

public class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        double dist = Math.abs(root.val - target);
        TreeNode node = root;
        while(node != null){
            if((double)node.val == target) return node.val;
            double curDis = Math.abs(node.val - target);
            if(curDis <= dist){
                dist = curDis;
                res = node.val;
            }
            if((double)node.val < target){
                node = node.right;
            }
            else node = node.left;
        }
        return res;
    }
}

/*
Closest Binary Search Tree Value II 

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
*/

public class Solution {
    // Inorder Traversal O(n).
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> stack = new Stack();
        Queue<Integer> queue = new LinkedList();
        addLeftChild(stack, root);

        while(!stack.isEmpty()){
            TreeNode top = stack.pop();
            if(queue.size() < k) queue.offer(top.val);
            else{
                if(Math.abs(queue.peek()-target) <= Math.abs(top.val - target)) return new ArrayList(queue);
                queue.poll();
                queue.offer(top.val);
            }
            if(top.right != null) addLeftChild(stack, top.right);
        }
        return new ArrayList(queue);
    }

    private void addLeftChild(Stack<TreeNode> stack, TreeNode node){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }
}

/*
Strobogrammatic Number My Submissions Question Solution 
Total Accepted: 2736 Total Submissions: 8502 Difficulty: Easy
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

public class Solution {
    private static HashMap<Character, Character> map = new HashMap();
    private static HashSet<Character> set = new HashSet();
    private static void buildMap(){
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        set.add('0');
        set.add('1');
        set.add('8');
    }
    
    public boolean isStrobogrammatic(String num) {
        if(num == null) return false;
        buildMap();
        int left = 0, right = num.length()-1;
        while(left < right){
            char leftChar = num.charAt(left);
            char rightChar = num.charAt(right);
            if(!map.containsKey(leftChar) || map.get(leftChar) != rightChar) return false;
            left++;
            right--;
        }
        
        return left == right ? set.contains(num.charAt(left)) : true;
    }
}

/*
Strobogrammatic Number II My Submissions Question Solution 
Total Accepted: 2332 Total Submissions: 8506 Difficulty: Medium
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/
public class Solution {
    public List<String> findStrobogrammatic(int n){
        List<String> res = new ArrayList();
        findStrobogrammatic(res, "", "", n, n);
        return res;
    }

    public void findStrobogrammatic(List<String> res, String left, String right, int k, int n) {
        if(k == 0) {
            res.add(left+right);
            return;
        }
        if(k == 1) {
            res.add(left + 0 + right);
            res.add(left + 1 + right);
            res.add(left + 8 + right);
            return;
        }

        
        if (k != n) findStrobogrammatic(res, left+0, 0+right, k-2, n);
        
        findStrobogrammatic(res, left+1, 1+right, k-2, n);
        findStrobogrammatic(res, left+8, 8+right, k-2, n);
        findStrobogrammatic(res, left+6, 9+right, k-2, n);
        findStrobogrammatic(res, left+9, 6+right, k-2, n);
    }
}

/*
Binary Tree Longest Consecutive Sequence My Submissions Question Solution 
Total Accepted: 8 Total Submissions: 26 Difficulty: Medium
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
*/

public class Solution {
    int max = 1;
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        longestRec(root);
        return max;
    }
    
    public int longestRec(TreeNode node){
        if(node == null) return 0;
        if(node.left == null && node.right == null) return 1;
        int leftLength = longestRec(node.left);
        int rightLength = longestRec(node.right);
        
        leftLength = node.left == null || node.left.val != node.val + 1 ? 1 : leftLength+1;
        rightLength = node.right == null || node.right.val != node.val + 1 ? 1 : rightLength+1;
        int cur = Math.max(leftLength, rightLength);
        max = Math.max(max, cur);
        return cur;
    }
}

/*
Graph Valid Tree My Submissions Question
Total Accepted: 3312 Total Submissions: 12436 Difficulty: Medium
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(n != edges.length+1) return false;
        if(edges.length == 0) return true;
        HashMap<Integer, List<Integer>> adjList = new HashMap();
        HashSet<Integer> visited = new HashSet();

        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int v1 = edge[0], v2 = edge[1];
            List<Integer> adjs1 = adjList.get(v1);
            List<Integer> adjs2 = adjList.get(v2);
            if(adjs1 == null) adjs1 = new ArrayList();
            if(adjs2 == null) adjs2 = new ArrayList();
            adjs1.add(v2);
            adjs2.add(v1);
            adjList.put(v1, adjs1);
            adjList.put(v2, adjs2);
        }

        int root = edges[0][0];
        if (dfs(root, root, adjList, visited) == false) return false;
        return visited.size() == n;

    }

    public boolean dfs(int cur, int parent, HashMap<Integer, List<Integer>> map, HashSet<Integer> visited){
        visited.add(cur);
        for(int neighbor : map.get(cur)){
            if(neighbor != parent){
                if(visited.contains(neighbor)) return false;
                visited.add(neighbor);
                if(!dfs(neighbor, cur, map, visited)) return false;
            }
        }
        return true;
    }
}

/*
Longest Substring with At Most Two Distinct Characters My Submissions Question
Total Accepted: 5631 Total Submissions: 18131 Difficulty: Hard
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null) return 0;
        if(s.length() <= 2) return s.length();
        Queue<Character> queue = new LinkedList();
        HashMap<Character, Integer> charIndex = new HashMap();
        int max = 0;
        int startIndex = 0;
        for(int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if(i == s.length()-1 && (cur == queue.peek() || cur == s.charAt(i-1))) {
                max = Math.max(i-startIndex+1, max);
                break;
            }
            if(i != 0 && cur == s.charAt(i-1)) continue;
            charIndex.put(cur, i);
            queue.offer(cur);
            if(queue.size() > 2){
                char front = queue.poll();
                if(front != cur){
                    max = Math.max(i-startIndex, max);
                    startIndex = map.get(queue.peek());
                }
            }
        }
        return max;
    }
}

/*
Find Median from Data Stream My Submissions Question
Total Accepted: 3269 Total Submissions: 18250 Difficulty: Hard
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
*/

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
class MedianFinder {
    // A minQueue to store the bigger half.
    PriorityQueue<Integer> minQueue = new PriorityQueue();
    // A maxQueue to store the smaller half.
    PriorityQueue<Integer> maxQueue = new PriorityQueue(11, new Comparator<Integer>(){
           public int compare(Integer num1, Integer num2){
               return num2-num1;
           }
        });
    // Adds a number into the data structure.
    public void addNum(int num) {
        if(maxQueue.size()>minQueue.size()){
            maxQueue.offer(num);
            minQueue.offer(maxQueue.poll());
        }
        else if(!minQueue.isEmpty() && num > minQueue.peek()){
            minQueue.offer(num);
            maxQueue.offer(minQueue.poll());
        }
        else maxQueue.offer(num);
    }

    // Returns the median of current data stream
    public double findMedian() {
        return maxQueue.size() > minQueue.size()
           ? maxQueue.peek()
           : (maxQueue.peek()+minQueue.peek())/2.0;
    }
};

/*
Flip Game II My Submissions Question
Total Accepted: 1579 Total Submissions: 4267 Difficulty: Medium
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/
public class Solution {
    public boolean canWin(String s) {
        
    }
}

/*
Unique Word Abbreviation My Submissions Question
Total Accepted: 1690 Total Submissions: 10417 Difficulty: Easy
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/

// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
public class ValidWordAbbr {
    HashMap<String, HashSet<String>> map = new HashMap();
    public ValidWordAbbr(String[] dictionary) {
        for(String str : dictionary){
            String key = getKey(str);
            HashSet<String> set = map.get(key);
            if(set == null) set = new HashSet();
            set.add(str);
            map.put(key, set);
        }
    }

    public boolean isUnique(String word) {
        String key = getKey(word);
        return !map.containsKey(key) || map.get(key).contains(word) && map.get(key).size()==1;
    }
    
    public String getKey(String word){
        int num = word.length() - 2;
        return num > 0 ? word.charAt(0) + "" + num + "" + word.charAt(num+1) : word;
    }
}

/*
Zigzag Iterator My Submissions Question
Total Accepted: 2249 Total Submissions: 6333 Difficulty: Medium
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
 public class ZigzagIterator {

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        
    }

    public int next() {
        
    }

    public boolean hasNext() {
        
    }
}