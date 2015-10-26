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