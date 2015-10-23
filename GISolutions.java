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