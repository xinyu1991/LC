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
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

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

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

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
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.”
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
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/
public class Solution {
    public boolean canWin(String s) {
        Map<String, Boolean> winMap = new HashMap<String, Boolean>();
        return canWin(s.toCharArray(), winMap);
    }

    public boolean canWin(char[] s, Map<String, Boolean> winMap){
        String str = String.valueOf(s);
        if(winMap.containsKey(str)) return winMap.get(str);
        for(int i = 0; i < s.length-1; i++){
            if(s[i] == '+' && s[i+1] == '+'){
                s[i] = '-'; s[i+1] = '-';
                boolean win = !canWin(s, winMap);
                s[i] = '+'; s[i+1] = '+';
                if(win){
                    winMap.put(str, true);
                    return true;
                } 
            }
        }
        winMap.put(str, false);
        return false;
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
    Iterator iterator1, iterator2, curIterator;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iterator1 = v1.iterator();
        iterator2 = v2.iterator();
        curIterator = iterator1;
    }

    public int next() {
        int val = curIterator.next();
        if(curIterator == iterator1 && iterator2.hasNext()) curIterator = iterator2;
        else if(curIterator == iterator2 && iterator1.hasNext()) curIterator = iterator1;
        return val;
    }

    public boolean hasNext() {
        return iterator1.hasNext() || iterator2.hasNext;
    }
}

// SkyLine problem

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if(buildings == null || buildings.length == 0) return new ArrayList();
        return getSkyline(buildings, 0, buildings.length-1);
    }
    
    public LinkedList<int[]> getSkyline(int[][] buildings, int left, int right){
        if(left == right) return getKeyPoint(buildings[left]);
        int mid = left+right >> 1;
        return mergeKeyPoints(getSkyline(buildings, left, mid), getSkyline(buildings, mid+1, right));
    }
    
    public LinkedList<int[]> mergeKeyPoints(LinkedList<int[]> keys1, LinkedList<int[]> keys2){
        LinkedList<int[]> res = new LinkedList();
        int h1 = 0, h2 = 0;
        while(keys1.size() > 0 && keys2.size() > 0){
            int x1 = keys1.getFirst()[0], x2 = keys2.getFirst()[0];
            int x = x1, h = 0;
            if(x1 < x2){
                h1 = keys1.getFirst()[1];
                keys1.removeFirst();
            }
            else if(x1 > x2){
                h2 = keys2.getFirst()[1];
                x = x2;
                keys2.removeFirst();
            }
            else{
                h1 = keys1.getFirst()[1];
                h2 = keys2.getFirst()[1];
                keys1.removeFirst();
                keys2.removeFirst();
            }
            h = Math.max(h1, h2);
            if(res.size() == 0 || res.getLast()[1] != h){
                res.add(new int[] {x, h});
            }
        }
        res.addAll(keys1);
        res.addAll(keys2);
        return res;
    }
    
    public LinkedList<int[]> getKeyPoint(int[] building){
        LinkedList<int[]> keyPoints = new LinkedList();
        int[] top = {building[0], building[2]};
        int[] bottom = {building[1], 0};
        keyPoints.add(top);
        keyPoints.add(bottom);
        return keyPoints;
    }
}

/* 1
Wildcard Matching My Submissions Question
Total Accepted: 42287 Total Submissions: 265326 Difficulty: Hard
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

public class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        boolean[] matched = new boolean[s.length()+1];
        Arrays.fill(matched, false);
        matched[0] = true;

        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '*'){
                for(int j = 0; j < s.length(); j++){
                    matched[j+1] = matched[j] || matched[j+1];
                }
            }
            else{
                for(int j = s.length()-1; j >= 0; j--){
                    matched[j+1] = matched[j] && (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j));
                }
                matched[0] = false;
            }
        }
        return matched[s.length()];
    }

    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        if(p.length() == 0) return false;
        boolean[][] match = new boolean[p.length()+1][s.length()+1];
        match[0][0] = true;
        // The p[0..i] matches empty string if current char is '*' and p[0..i-1] matches empty string.
        for(int i = 0; i < p.length(); i++){
            match[i+1][0] = match[i][0] && p.charAt(i) == '*';
        }

        for(int i = 0; i < p.length(); i++){
            for(int j = 0; j < s.length(); j++){
                // If p[i] == '*', the p[0..i] matches s[0..j] if:
                // 1. p[0..i] matches s[0..j-1]. because * matches sequence.
                // 2. p[0..i-1] matches s[0..j]. where p[i] matches empty here.
                if(p.charAt(i) == '*'){
                    match[i+1][j+1] = match[i][j+1] || match[i+1][j];
                }
                else{
                    match[i+1][j+1] = match[i][j] && (p.charAt(i)=='?' || p.charAt(i) == s.charAt(j));
                }
            }
        }
        return match[p.length()][s.length()];
    }
}

/* 2
Regular Expression Matching My Submissions Question
Total Accepted: 60134 Total Submissions: 287965 Difficulty: Hard
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        if(p.length() == 0 || p.charAt(0) == '*') return false;
        // macth[i][j] means p[0..i-1] matches s[0..j-1].
        boolean[][] match = new boolean[p.length()+1][s.length()+1];
        // Set the empty to empty matched.
        match[0][0] = true;
        // p[0..i] matches empty string if p[i] is '*' and p[0..i-2] matches empty string.
        for(int i = 1; i < p.length(); i++){
            match[i+1][0] = p.charAt(i) == '*' && match[i-1][0];
        }

        for(int i = 0; i < p.length(); i++){
            char pChar = p.charAt(i);
            for(int j = 0; j < s.length(); j++){
                // If the current char is '*', p[0..i] matches with s[0..j] if:
                // 1. p[0..i-2] matches s[0..j] (* reprensents 0 char);
                // 2. p[0..i] matches s[0..j-1] and p[i-1] == s[j-1];  
                if(pChar == '*') 
                    match[i+1][j+1] = match[i-1][j+1] || ((p.charAt(i-1) == '.' || p.charAt(i-1) == s.charAt(j)) && match[i+1][j]);
                // If the current char is not '*', p[0..i] matches with s[0..j] if:
                // p[0..i-1] matches s[0..j-1] and p[i-1] == s[j-1];
                else match[i+1][j+1] = match[i][j] && (pChar == '.' || pChar == s.charAt(j));
            }
        }
        return match[p.length()][s.length()];
    }

    public boolean isMatch(String s, String p){
        if(p.length() == 0) return s.length() == 0;
        // If the second char is '*', try to see if s matches p[2..], otherwise if the first char matches, try to see if s[1..] matches p;
        if(p.length() > 1 && p.charAt(1) == '*'){
            return isMatch(s, p.substring(2)) || (s.length()!=0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) && isMatch(s.substring(1), p));
        }
        else{
            return s.length() != 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) && isMatch(s.substring(1), p.substring(1));
        }
    }
}

/* 3
Shortest Palindrome My Submissions Question
Total Accepted: 11642 Total Submissions: 66797 Difficulty: Hard
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
For example:


Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*/
public class Solution {
    public String shortestPalindrome(String s) {
        int left = 0, right = s.length-1, end = s.length-1;
        while(left < right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            }
            else{
                right = end--;
                left = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(end)).reverse();
        return sb.append(s).toString();
    }
}

// Using KMP.
public class Solution {
    public String shortestPalindrome(String s) {
        if(s == null || s.length() < 2) return s;
        String temp = s+"#"+ new StringBuilder(s).reverse().toString();
        int length = buildTable(temp);
        StringBuilder sb = new StringBuilder();
        return sb.append(s.substring(length)).reverse().toString() + s;
    }

    public int buildTable(String s){
        int[] table = new int[s.length()];
        table[0] = 0;
        for(int i = 0, j = 1; j < s.length(); j++){
            if(s.charAt(i) == s.charAt(j)){
                table[j] = table[j-1] + 1;
                i++;
            }
            else{
                i = table[j-1];
                while(i > 0 && s.charAt(i) != s.charAt(j)){
                    i = table[i-1];
                }
                
                table[j] = s.charAt(i) == s.charAt(j) ? ++i : 0;
            }
        }
        return table[s.length() - 1];
    }
}

/*
4
Peeking Iterator My Submissions Question
Total Accepted: 7457 Total Submissions: 24013 Difficulty: Medium
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation 
-- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:

Think of "looking ahead". You want to cache the next element.
Is one variable sufficient? Why or why not?
Test your design with call order of peek() before next() vs next() before peek().
For a clean implementation, check out Google's guava library source code.
Follow up: How would you extend your design to be generic and work with all types, not just integer?
*/

class PeekingIterator implements Iterator<Integer> {
    Queue<Integer> queue = new LinkedList();
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        while(iterator.hasNext()){
            queue.offer(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return queue.peek();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return queue.isEmpty();
    }
}

class PeekingIterator implements Iterator<Integer> {
    Integer peek = null;
    Iterator<Integer> iter;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if(iter.hasNext()){
            peek = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return peek;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer val = peek;
        peek = iter.hasNext() ? iter.next() : null;
        return val;
    }

    @Override
    public boolean hasNext() {
        return peek != null || iter.hasNext();
    }
}

/* 5
Median of Two Sorted Arrays My Submissions Question
Total Accepted: 71218 Total Submissions: 409032 Difficulty: Hard
There are two sorted arrays nums1 and nums2 of size m and n respectively. 
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
        {
            int len1 = nums1.length, len2 = nums2.length;
            if (len1 > len2) return findMedianSortedArrays(nums2, nums1);
            int halfLen = len1 + len2 + 1 >> 1;
            int mid1 = -1, mid2 = -1;
            int left = 0, right = len1;
            int m1 = 0, m2 = 0;
            while (left <= right)
            {
                m1 = left + right >> 1;
                m2 = halfLen - m1;
                if (m1 > 0 && m2 < len2 && nums1[m1 - 1] > nums2[m2]) right = m1;
                else if (m2 > 0 && m1 < len1 && nums2[m2 - 1] > nums1[m1]) left = m1 + 1;
                else
                {
                    if (m1 == 0)
                    {
                        mid1 = nums2[m2-1];
                    }
                    else if (m2 == 0)
                    {
                        mid1 = nums1[m1-1];
                    }
                    else
                    {
                        mid1 = Math.max(nums1[m1-1], nums2[m2-1]);
                    }
                    break;
                }                
            }
            if ((len1 + len2) % 2 == 1) return mid1;
            if (m1 == len1) mid2 = nums2[m2];
            else if (m2 == len2) mid2 = nums1[m1];
            else mid2 = Math.min(nums1[m1], nums2[m2]);
            return (mid1 + mid2) / 2.0;
        }
}


/* 6
Meeting Rooms II My Submissions Question
Total Accepted: 2860 Total Submissions: 9708 Difficulty: Medium
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/

public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for(int i = 0; i < intervals.length; i++){
            Interval cur = intervals[i];
            if(!queue.isEmpty() && queue.peek().end <= cur.start) queue.poll();
            queue.offer(cur.end);
        }
        return queue.size();
    }
}

/* 7
Implement Trie (Prefix Tree) My Submissions Question
Total Accepted: 19839 Total Submissions: 80042 Difficulty: Medium
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/
class TrieNode {
    // Initialize your data structure here.
    TrieNode[] children;
    boolean isWord;
    public TrieNode() {
        children = new TrieNode[26];
        isWord = false;
    }
}
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for(char c : word){
            int index = c-'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for(char c : word){
            int index = c - 'a';
            if(node.children[index] == null) return false;
            node = node.children[index];
        }
        return node.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix){
            int index = c-'a';
            if(node.children[index] == null) return false;
            node = node.children[index];
        }
        return true;
    }
}



/* 9
Generate  Parentheses My Submissions Question
Total Accepted: 64720 Total Submissions: 191622 Difficulty: Medium
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        helper(res, "", 0, 0, 3);
        return res;
    }

    public void helper(List<String> res, String s, int open, int close, int n){
        if(close == n){
            res.add(s);
            return;
        }
        if(open < n) helper(res, s+'(', open+1, close, n);
        if(close < open) helper(res, s+')', open, close+1, n);
    }
}

/* 10
Game of Life
*/
public class Solution {
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0) return;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                int liveNeighbor = countLiveNeighbors(board, i, j);
                if(board[i][j] == 1 && (liveNeighbor < 2 || liveNeighbor > 3)) board[i][j] = 2;
                if(board[i][j] == 0 && liveNeighbor == 3) board[i][j] = 3;
            }
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = board[i][j] % 2;
            }
        }
    }

    public int countLiveNeighbors(int[][] board, int m, int n){
        int count = 0;
        for(int i = m-1; i <= m+1; i++){
            if (i < 0 || i >= board.length) continue;
            for(int j = n-1; j <= n+1; j++){
                if (j < 0 || j >= board[0].length || (i==m && j ==n)) continue;
                if (board[i][j] == 1 || board[i][j] == 2) count++;
            }
        }
        return count;
    }
}

/* 11
Expression Add Operators My Submissions Question
Total Accepted: 3948 Total Submissions: 19894 Difficulty: Hard
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/
public class Solution
        {
            public List<String> addOperators(String num, int target)
            {
                List<String> res = new ArrayList();
                helper(res, num, "", 0, target, 0, 0);
                return res;
            }

            private void helper(List<String> res, String num, String path, int startIndex, int target, long sum, long eval)
            {
                if (startIndex == num.length() && sum == target)
                {
                    res.add(path);
                }
                for (int i = startIndex; i < num.length(); i++)
                {
                    if (i != startIndex && num.charAt(startIndex) == '0') break;
                    long cur = Long.parseLong(num.substring(startIndex, i + 1));

                    if (startIndex == 0)
                    {
                        helper(res, num, path + cur, i + 1, target, cur, cur);
                    }
                    else
                    {
                        helper(res, num, path + "+" + cur, i + 1, target, sum + cur, cur);
                        helper(res, num, path + '-' + cur, i + 1, target, sum - cur, -cur);
                        helper(res, num, path + '*' + cur, i + 1, target, sum - eval + eval * cur, eval * cur);
                    }
                }
            }
        }

/* 12
Basic Calculator My Submissions Question
Total Accepted: 15862 Total Submissions: 85286 Difficulty: Medium
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
*/
public class Solution {
    public int calculate(String s)
        {
            int res = 0, num = 0, sign = 1;
            Stack<Integer> stack = new Stack<Integer>();
            for (int i = 0; i < s.length(); i++)
            {
                char cur = s.charAt(i);
                if (cur >= '0' && cur <= '9')
                {
                    num = num * 10 + (cur - '0');
                }
                else if (cur == '+' || cur == '-')
                {
                    res += sign * num;
                    sign = cur == '-' ? -1 : 1;
                    num = 0;
                }
                else if (cur == '(')
                {
                    stack.push(res);
                    stack.push(sign);
                    res = 0;
                    sign = 1;
                    num = 0;
                }
                else if (cur == ')')
                {
                    res += num * sign;
                    res *= stack.pop();
                    res += stack.pop();
                    num = 0;
                }
            }
            if(num != 0) res+=num*sign;
            return res;
        }
}

/*
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
*/
public class Solution {
    public int calculate(String s)
        {
            Stack<Integer> stack = new Stack<Integer>();
            int num = 0;
            char sign = '+';
            for(int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if (c == ' ' && i != s.length()-1) continue;
                if (Character.isDigit(c))
                {
                    num = 10 * num + c - '0';
                }

                if(!Character.isDigit(c) || i == s.length()-1)
                {
                    switch (sign)
                    {
                        case '+':
                            stack.push(num);
                            break;
                        case '-':
                            stack.push(0 - num);
                            break;
                        case '*':
                            stack.push(stack.pop() * num);
                            break;
                        case '*':
                            stack.push(stack.pop() / num);
                            break;
                    }
                    sign = c;
                    if(i != s.length()-1) num = 0;
                }
            }
            int res = 0;
            if(stack.isEmpty()) res+=num; 
            while (!stack.isEmpty())
            {
                res += stack.pop();
            }
            return res;
        }
}

/* 13
Binary Tree Longest Consecutive Sequence My Submissions Question
Total Accepted: 709 Total Submissions: 2216 Difficulty: Medium
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

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
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }
    public int helper(TreeNode root) {
        if(root == null) return 0;
        int leftLength = helper(root.left);
        int rightLength = helper(root.right);
        leftLength = root.left != null && root.val + 1 == root.left.val ? leftLength+1 : 1;
        rightLength = root.right != null && root.val + 1 == root.right.val ? rightLength+1 : 1;
        int curLength = Math.max(leftLength, rightLength);
        max = Math.max(max, curLength);
        return curLength;
    }
}

/* 14
Binary Tree Paths My Submissions Question
Total Accepted: 20033 Total Submissions: 86148 Difficulty: Easy
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
*/
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList();
        helper(res, root, new ArrayList());
        return res;
    }

    private void helper(List<String> res, TreeNode node, List<String> path){
        path.add(node);
        if(node.left == null && node.right == null){
            res.add(toPathString(path));
        }
        if(node.left != null) helper(res, node.left, path);
        if(node.right != null) helper(res, node.right, path);
        path.remove(path.size()-1);
    }

    private String toPathString(List<String> path){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < path.size(); i++){
            sb.append(path.get(i));
            if(i!=path.size()-1) sb.append("->");
        }
        return sb.toString();
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList();
        helper(res, root, "");
        return res;
    }

    private void dfs(List<String> res, TreeNode node, String path){
        if(node == null) return;
        if(node.left == null && node.right == null) res.add(path+node.val);
        dfs(res, node.left, path+node.val+"->");
        dfs(res, node.right, path+node.val+"->");
    }
}

/* 15
Alien Dictionary My Submissions Question
Total Accepted: 2136 Total Submissions: 11646 Difficulty: Hard
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

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

["wrt","wrf","er","ett","rftt"]
"twfre"

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.*/
public class Solution
{
    private class CharNode
    {
        public char val;
        public HashMap<Character, CharNode> neighbors;
        public int dependencyCount;
        public CharNode(char v)
        {
            val = v;
            dependencyCount = 0;
            neighbors = new HashMap();
        }

        public void addNeighbor(CharNode nextNode)
        {
            if (!neighbors.containsKey(nextNode.val))
            {
                nextNode.dependencyCount++;
                neighbors.put(nextNode.val, nextNode);
            }
        }
    }

    public String alienOrder(String[] words)
    {
        if(words == null || words.length == 0) return "";
        if(words.length == 1) return words[0];
        HashMap<Character, CharNode> map = new HashMap<Character,CharNode>();
        String pre = words[0];
        for (int i = 1; i < words.length; i++)
        {
            String cur = words[i];
            boolean orderChecked = false;
            for (int j = 0; j < pre.length() || j < cur.length() ; j++)
            {
                if (j >= pre.length() || j >= cur.length())
                {
                    char c = j >= pre.length() ? cur.charAt(j) : pre.charAt(j);
                    if (!map.containsKey(c))
                    {
                        map.put(c, new CharNode(c));
                    }
                    continue;
                }
                char preChar = pre.charAt(j);
                char curChar = cur.charAt(j);
                CharNode preNode = map.containsKey(preChar) ? map.get(preChar) : new CharNode(preChar);
                if (preChar != curChar)
                {
                    CharNode curNode = map.containsKey(curChar) ? map.get(curChar) : new CharNode(curChar);
                    if(!orderChecked){
                        preNode.addNeighbor(curNode);
                        orderChecked = true;
                    }
                    map.put(curChar, curNode); 
                }
                map.put(preChar, preNode);
            }
            pre = cur;
        }
        StringBuilder sb = new StringBuilder();
        Queue<CharNode> queue = new LinkedList<CharNode>();

        for(char c : map.keySet())
        {
            CharNode node = map.get(c);
            if (node.dependencyCount == 0)
            {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty())
        {
            CharNode front = queue.poll();
            sb.append(front.val);
            for(CharNode node : front.neighbors.values())
            {
                node.dependencyCount--;
                if (node.dependencyCount == 0)
                {
                    queue.offer(node);
                }
            }
        }
        for(CharNode node : map.values()){
            if(node.dependencyCount != 0) return "";
        }
        return sb.toString();
    }
}

/* 16
Recover Binary Search Tree My Submissions Question
Total Accepted: 42605 Total Submissions: 170468 Difficulty: Hard
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/
public class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode node1 = null, node2 = null;

        Stack<TreeNode> stack = new Stack();
        addLeftChild(stack, root);
        TreeNode pre = null;
        while(!stack.isEmpty()){
            TreeNode top = stack.pop();
            if(pre != null && pre.val > top.val){
                if(node1 == null) node1 = pre;
                node2 = top;
            }
            
            if(top.right != null){
                addLeftChild(stack, top.right);
            }
            pre = top;
        }
        
        if(node1 != null && node2 != null){
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
    }

    private void addLeftChild(Stack<TreeNode> stack, TreeNode node){
        while(node!=null){
            stack.push(node);
            node = node.left;
        }
    }
}

/* 17
Populating Next Right Pointers in Each Node II My Submissions Question
Total Accepted: 48222 Total Submissions: 149755 Difficulty: Hard
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/
// O(n), O(1);
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = null, pre = null, cur = root;
        while(cur != null){
            while(cur != null){
                if(cur.left != null){
                    if(pre == null){
                        head = cur.left;
                        pre = cur.left;
                    }
                    else{
                        pre.next = cur.left;
                        pre = pre.next;
                    }
                }
                if(cur.right != null){
                    if(pre == null){
                        head = cur.right;
                        pre = cur.right;
                    }
                    else {
                        pre.next = cur.right;
                        pre = pre.next;
                    }
                }
                cur = cur.next;
            }
            cur = head;
            head = null;
            pre = null;
        }
    }
}

/* 18
Fraction to Recurring Decimal My Submissions Question
Total Accepted: 21231 Total Submissions: 155226 Difficulty: Medium
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/
public class Solution {
    public String fractionToDecimal(int numerator, int denominator)
            {
                StringBuilder res = new StringBuilder();
                if ((numerator < 0) ^ (denominator < 0) && numerator!=0) res.append('-');
                long num = Math.abs((long)numerator);
                long den = Math.abs((long)denominator);
                res.append(num / den);
                num = num % den;
                if (num == 0)
                {
                    return res.toString();
                }
                res.append('.');
                HashMap<Long, Integer> set = new HashMap<Long, Integer>();
                while (num != 0)
                {
                    num = num * 10;
                    if (set.containsKey(num))
                    {
                        int index = set.get(num);
                        res.insert(index, '(');
                        res.append(')');
                        break;
                    }
                    set.put(num, res.length());
                    res.append(num / den);
                    num = num % den;
                }
                return res.toString();
            }
}

// 19 Best Time to Buy and Sell Stock IV
// 4
// [1,2,4,2,5,7,2,4,9,0]
public class Solution {
    public int maxProfit(int k, int[] prices) {
        if(k >= prices.length/2) return quickSolve(prices);
        int[][] profit = new int[k+1][prices.length()];        
        profit[0][0] = 0;
        for(int i = 1; i < k+1; i++){
            int maxBuy = 0-prices[0];
            for(int j = 1; j < prices.length; j++){
                profit[i][j] = Math.max(profit[i][j-1], maxBuy + prices[j]);
                maxBuy = Math.max(maxBuy, profit[i-1][j-1] - prices[j]);                    
            }
        }
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}

/* 20
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k);
    }

    public int quickSelect(int[] nums, int k){
        int left = 0, right = nums.length-1;
        while(left < right){
            int index = partition(nums, left, right);
            if(k-1 == index) return nums[index];
            else if(k-1 < index) right = index-1;
            else left = index+1;
        }
        return nums[left];
    }

    public int partition(int[] nums, int left, int right){
        int index = left;
        for(int i = left; i < right; i++){
            if(nums[i] > nums[right]){
                swap(nums, index++, i);
            }
        }
        swap(nums, index, right);
        return index;
    }

    private void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}

// tree serializer.
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "#,";
        String res = root.val + ",";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("") || data.equals("#")) return null;
        Queue<String> nodes = new LinkedList();
        nodes.addAll(Arrays.asList(data.split(",")));
        return helper(nodes);
    }
    
    public TreeNode helper(Queue<String> nodes){
        if(nodes.isEmpty()) return null;
        String str = nodes.poll();
        if(str.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(str));
        node.left = helper(nodes);
        node.right = helper(nodes);
        return node;
    }
}

/*
Longest Increasing Subsequence My Submissions Question
Total Accepted: 2570 Total Submissions: 8088 Difficulty: Medium
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        // keep a list of the smallest num at its position.
        ArrayList<Integer> res = new ArrayList();
        for(int num : nums){
            if(res.isEmpty() || res.get(res.size()-1) < num) res.add(num);
            else{
                int insertPoint = Collections.binarySearch(res, num);
                if(insertPoint < 0) insertPoint = -insertPoint -1;
                res.set(insertPoint, num);
            }
        }
        return res.size();
    }
}

/*
3. given a probability = [.5 .1 .2 .2], label = [A B C D], write a data structure that generates the label based on the prob. 我说先找cumulative probability［.5, .6, .8 1]， 
然后弄个0～1之间的random数字比较过去找它的位置就好
*/


// Leetcode 79, 212
public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        HashSet<String> res = new HashSet();
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j =0; j < n; j++){
                search(res, trie, board, visited, i, j, "");
            }
        }
        return res;
    }

    public void search(HashSet<String> res, Trie trie, char[][] board, boolean[][] visited, int i, int j, String str){
        if(i < 0 || j < 0 || i >= visited.length || j >= visited[0].length) return;
        if(visited[i][j]) return;
        str += board[i][j];
        if(!trie.startsWith(str)) return;
        if(trie.search(str)) res.add(str);
        visited[i][j] = true;
        search(res, trie, board, visited, i-1, j, str);
        search(res, trie, board, visited, i+1, j, str);
        search(res, trie, board, visited, i, j-1, str);
        search(res, trie, board, visited, i, j+1, str);
        visited[i][j] = false;
    }
}

public class Solution {
    Set<String> res = new HashSet<String>();
    
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }
        
        return new ArrayList<String>(res);
    }
    
    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;
        
        str += board[x][y];
        if (!trie.startsWith(str)) return;
        
        if (trie.search(str)) {
            res.add(str);
        }
        
        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        visited[x][y] = false;
    }
}

class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public String item = "";
    
    // Initialize your data structure here.
    public TrieNode() {
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.item = word;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return node.item.equals(word);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return true;
    }
}

class Solution {
public:
    struct TrieNode{

        bool is_end;
        bool starts_with;
        struct TrieNode* children[26];
    };

    struct TrieNode* createNode(){
        struct TrieNode* n = new TrieNode;
        n->is_end = false;
        n->starts_with = false;

        for(int i = 0 ; i < 26; i++){
            n->children[i] = NULL;
        }
        return n;
    }

    void insert(TrieNode* root, string s){
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(root->children[s[i] - 'a'] == NULL){
                root->children[s[i] - 'a'] = createNode();
                root->children[s[i] - 'a']->starts_with = true;
            }
            root = root->children[s[i] - 'a'];
        }
        root->is_end = true;
    }

    bool search(TrieNode* root, string s){
        int len = s.length();

        for(int i = 0 ; i < len; i++){
            if(root->children[s[i] - 'a'] == NULL){
                return false;
            }
            root = root->children[s[i] - 'a'];
        }

        return root->is_end;
    }

    bool startsWith(TrieNode* root, string s){
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(root->children[s[i] - 'a']  == NULL){
                return false;
            }

            root = root->children[s[i] - 'a'];
        }
        return root->starts_with;
    }

    void helper(vector<vector<char> >& board, string s, int x, int y, vector<string>& res, bool** track, TrieNode* root){
        if(x < 0 || x >= board.size() || y < 0 || y >= board[0].size() || track[x][y] == true){
            return;
        }

        s += board[x][y];

        if(!startsWith(root,s)){
            return;
        }

        if(search(root,s)){
            res.push_back(s);
        }

        track[x][y] = true;

        helper(board, s,x-1,y,res,track,root);
        helper(board, s,x+1,y,res,track,root);
        helper(board, s,x,y-1,res,track,root);
        helper(board, s,x,y+1,res,track,root);

        track[x][y] = false;
    }

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        int n = words.size();
        vector<string> res;
        bool** track = new bool*[board.size()];

        for(int i = 0 ; i < board.size(); i++){

            track[i] = new bool[board[0].size()];
        }
        for(int i = 0;  i <  board.size(); i++){
           for(int j = 0 ; j < board[0].size(); j++){
               track[i][j] = false;
           }
        }
        if(n == 0){

            return res;
        }

        TrieNode* root = createNode();
        for(int i = 0; i < n; i++){

            insert(root,words[i]);

        }

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board[0].size(); j++) {
               helper(board,"",i,j,res,track,root);
            }
        }

        sort(res.begin(),res.end());
        res.erase( unique( res.begin(), res.end() ), res.end() );
        return res;
    }
};


// H-Index
/*
For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
*/
public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null) return 0;
        int len = citations.length;
        int[] counts = new int[len+1];
        for(int c : citations){
            if(c >= len) counts[len]++;
            else counts[c]++;
        }
        int sum = 0;
        for(int i = len; i >= 0; --i){
            sum += counts[i];
            if(sum >= i) return i;
        }
        return 0;
    }
}

// Sorted
public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int left = 0, right = citations.length-1;
        while(left < right){
            int mid = left+right >> 1;
            int cite = citations[mid];
            int higherCount = citations.length-mid;
            if(cite == higherCount) return cite;
            if(cite > higherCount){
                right = mid;
            }
            else left = mid+1;
        }
        return Math.min(citations[left], citations.length-left);
    }
}


// 12 Trapping Rain Water

public class Solution {
    public int trap(int[] height) {
        
    }
}

/* 8
Graph Valid Tree My Submissions Question
Total Accepted: 3450 Total Submissions: 12959 Difficulty: Medium
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Show Hint 
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/
public class Solution {
    public boolean validTree(int n, int[][] edges)
        {
            // Use an adjacent list to store the graph
            List<Set<Integer>> graph = new ArrayList();
            for (int i = 0; i < n; i++)
            {
                graph.add(new HashSet());
            }

            for(int[] edge : edges)
            {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }

            boolean[] visited = new boolean[n];

            Stack<Integer> stack = new Stack<Integer>();
            stack.push(0);

            while (!stack.isEmpty())
            {
                int node = stack.pop();
                if (visited[node]) return false;
                visited[node] = true;
                for(int neighbor : graph.get(node))
                {
                    stack.push(neighbor);
                    graph.get(neighbor).remove(node);
                }
            }

            for(boolean visit : visited)
            {
                if (!visit) return false;
            }
            return true;
        }
    public boolean validTreeBFS(int n, int[][] edges)
        {
            // Use an adjacent list to store the graph
            List<Set<Integer>> graph = new ArrayList();
            for (int i = 0; i < n; i++)
            {
                graph.add(new HashSet());
            }

            for(int[] edge : edges)
            {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }

            boolean[] visited = new boolean[n];

            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(0);

            while (!queue.isEmpty())
            {
                int node = queue.poll();
                if (visited[node]) return false;
                visited[node] = true;
                for(int neighbor : graph.get(node))
                {
                    queue.offer(neighbor);
                    graph.get(neighbor).remove(node);
                }
            }

            for(boolean visit : visited)
            {
                if (!visit) return false;
            }
            return true;
        }
}

class Node{
    int val;
    Node parent;
    public Node(int val)
    {
        this.val = val;
    }
}

public class Solution {
    Map<Integer, Node> forest;

    public boolean validTree(int n, int[][] edges) {
        return unionFind(n, edges);
    }

    private boolean unionFind(int n, int[][] edges)
    {
        // make set for each node
        forest = new HashMap<Integer, Node>();
        for(int i = 0; i < n; i++)
            forest.put(i, makeSet(i));

        // for the two vertice of each edge, find if they are in the same set,
        // If so, there is a cycle, return false.
        for(int[] edge : edges)
        {
            if(find(edge[0]) == find(edge[1]))
                return false;

            union(edge[0], edge[1]);
        }

        return edges.length == n - 1;
    }

    private Node makeSet(int val)
    {
        Node node = new Node(val);
        node.parent = node;
        return node;
    }

    private Node find(int node)
    {
        if(forest.get(node).parent.val == node)
            return forest.get(node);

        return find(forest.get(node).parent.val);
    }

    private void union(int node1, int node2)
    {
        Node set1 = find(node1);
        Node set2 = find(node2);
        set1.parent = set2;
    }
}

/* 20 
Sqrt(x) My Submissions Question
Total Accepted: 71240 Total Submissions: 298949 Difficulty: Medium
Implement int sqrt(int x).

Compute and return the square root of x.
*/

/* 20 
Sqrt(x) My Submissions Question
Total Accepted: 71240 Total Submissions: 298949 Difficulty: Medium
Implement int sqrt(int x).
        f(x) = x^2-n; f'(x) = 2x; 
       tagent line  g(x) = g(x0) + f'(x)*(x-x0) = f(x0) +f'(x0)*(x-x0); 
         * Next point x1 - x0= (0-f(x0))/f'(x0);
         * x1 = x0-f(x0)/f'(x0) = x0-(x0^2-n)/(2x0) = (x0/2 + n/x0);
Compute and return the square root of x.
*/
        public int mySqrt(int x)
        {
            if (x <= 0) return 0;
            double cur = x/2;
            double pre = 0;
            while (pre != cur)
            {
                pre = cur;
                cur = (cur + x/cur)/2;
            }
            return cur;
        }

public class Solution {
    public int mySqrt(int x){
        if(x<= 1) return x;
        double y = 1;
        double pre = 0;
        
        while(y != pre){
            pre = y;
            y = (y + x/y)/2;
        }
        return (int)pre;
    }
    
    public int mySqrt1(int x) {
        if(x <= 0) return 0;
        if(x == 1) return 1;
        long l= 0, r = x; 
        
        while(l < r){
            long mid = l + (r-l)/2;
            if(mid > x/mid) r = mid;
            else l = mid+1;
        }
        
        return r > x/r ? (int)r-1 : (int)r;
    }
    
    public int mySqrt(int num, int left, int right){
        int mid = left + (right - left)/2;
        if(mid == num/mid) return mid;
        if(mid < num/mid){
            return (mid+1) > num/(mid+1) ? mid : mySqrt(num, mid, right);
        }
        return mySqrt(num, left, mid-1); 
    }
}

/* 41
Word Break II My Submissions Question
Total Accepted: 43549 Total Submissions: 240709 Difficulty: Hard
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> dp[] = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<String>();
        for(int i = 0; i < s.length(); i++){
            if(dp[i] == null) continue;
            for(String word : wordDict){
                int len = word.length();
                int end = i+len;
                if(end > s.length()) continue;
                if(s.substring(i, end).equals(word)){
                    if(dp[end] == null) dp[end] = new ArrayList();
                    dp[end].add(word);
                }
            }
        }
        List<String> res = new ArrayList();
        if(dp[s.length()] == null) return res;
        helper(res, new ArrayList(), dp, s.length());
        return res;
    }

    public void helper(List<String> res, List<String> path, List<String>[] dp, int end){
        if(end <= 0){
            String ans = path.get(path.size()-1);
            for(int i = path.size()-2; i >= 0; --i){
                ans += " " + path.get(i);
            }
            res.add(ans);
            return;
        }
        
        for(String str : dp[i]){
            path.add(str);
            helper(res, path, dp, end-str.length());
            path.remove(path.size()-1);
        }
    }
}

public class Solution {
    public static List<String> wordBreak(String s, Set<String> dict) {
        List<String> dp[] = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<String>();
        for(int i=0; i<s.length(); i++){
            //i是开始位置
            if( dp[i] == null ) continue; //前面的部分必须是可以匹配的
            for(String word:dict){
                int len = word.length();
                int end = i+len;
                if(end > s.length()) continue;
                if(s.substring(i,end).equals(word)){
                    if(dp[end] == null){
                        dp[end] = new ArrayList<String>();
                    }
                    dp[end].add(word);//记录上一个位置
                }
            }
        }
 
        List<String> ans = new LinkedList<String>();
        if(dp[s.length()] == null) return ans;
        ArrayList<String> tmp = new ArrayList<String>();
        dfsStringList(dp,s.length(),ans, tmp);
        return ans;
    }
 
    public static void dfsStringList(List<String> dp[],int end,List<String> res, ArrayList<String> tmp){
        if(end <= 0){
            String ans = tmp.get(tmp.size()-1);
            for(int i=tmp.size()-2; i>=0; i--)
                ans += (" " + tmp.get(i) );
            res.add(ans);
            return;
        }
        for(String str:dp[end]){
            tmp.add(str);
            dfsStringList(dp,end-str.length(), res, tmp);
            tmp.remove(tmp.size()-1);
        }
    }
}


/*
Unique Binary Search Trees II My Submissions Question
Total Accepted: 42996 Total Submissions: 149106 Difficulty: Medium
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n)
        {
            List<TreeNode> dp[] = new ArrayList[n+1];
            dp[0] = new ArrayList();
            dp[0].add(null);
            if(n==0) return dp[0];
            dp[1] = new ArrayList();
            dp[1].add(new TreeNode(1));
            for (int i = 2; i <= n; i++)
            {
                dp[i] = new ArrayList();
                for (int j = 0; j < i; j++)
                {
                    for (TreeNode left : dp[j])
                    {
                        for(TreeNode right : dp[i-j-1])
                        {
                            TreeNode root = new TreeNode(j+1);
                            root.left = left;
                            root.right = buildTree(right, j+1);
                            dp[i].add(root);
                        }
                    }
                }
            }
            return dp[n];
        }

        public TreeNode buildTree(TreeNode n, int offset)
        {
            if (n == null) return null;
            TreeNode node = new TreeNode(n.val + offset);
            node.left = buildTree(n.left, offset);
            node.right = buildTree(n.right, offset);
            return node;
        }
}