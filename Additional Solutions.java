Additional Solutions

public class Solution {
    // Sliding Window    360ms
    // ask interviewer if words is empty, should I return empty list
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new LinkedList<>();
        if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
        int N = S.length(), M = L.length, K = L[0].length();
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        for (String s : L) {
            if (map.containsKey(s))   map.put(s, map.get(s) + 1);
            else                      map.put(s, 1);
        }
        String str = null, tmp = null;
        for (int i = 0; i < K; i++) {
            int count = 0;  // remark: reset count 
            for (int l = i, r = i; r + K <= N; r += K) {
                str = S.substring(r, r + K);
                if (map.containsKey(str)) {
                    if (curMap.containsKey(str))   curMap.put(str, curMap.get(str) + 1);
                    else                           curMap.put(str, 1);

                    if (curMap.get(str) <= map.get(str))    count++;
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        if (curMap.get(tmp) < map.get(tmp)) count--;
                    }
                    if (count == M) {
                        res.add(l);
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }
                }else {
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
            }
            curMap.clear();
        }
        return res;
    }
}



public class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;
        int max = 0, open = 0;
        int[] longest = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') open++;
            else if(open > 0){
                longest[i] = longest[i-1] +2;
                if(i-2 >= longest[i-1]) longest[i] += longest[i-2-longest[i-1]];
                max = Math.max(longest[i], max);
                open--;
            }
        }
        return max;
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

public class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
    int len2 = num2.length();
    int[] product = new int[len1 + len2];
    for (int i = len1 - 1; i >= 0; i--) {
        for (int j = len2 - 1; j >= 0; j--) {
            int index = len1 + len2 - i - j - 2;
            product[index] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            product[index + 1] += product[index] / 10;
            product[index] %= 10;
        }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = product.length - 1; i > 0; i--) {
        if (stringBuilder.length() == 0 && product[i] == 0)
            continue;
        stringBuilder.append(product[i]);
    }
    stringBuilder.append(product[0]);
    return stringBuilder.toString();
    }
}
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList();
        helper(candidates, result, new ArrayList<Integer>(), 0, 0, target);
        return result;
    }
    
    public void helper(int[] nums, List<List<Integer>> result, List<Integer> path, int start, int sum, int target){
        for(int i = start; i < nums.length; i++){
            path.add(nums[i]);
            sum += nums[i];
            if(sum == target) result.add(new ArrayList(path));
            if(sum < target) helper(nums, result, path, i+1, sum, target);
            sum -= nums[i];
            path.remove(path.size() - 1);
        }
    }
}

public class Solution {
    public string GetPermutation(int n, int k) {
        int[] fracArray = new int[] {1, 1, 2, 6, 24, 120, 720, 5040, 40320};
        
        string result = string.Empty;
        
        List<int> indexes = new List<int>();
        
        for(int i = 1; i <= n; i ++){
            indexes.Add(i);
        }
        
        k--;
        
        for(int i = n; i > 0; i--){
            int index = k / fracArray[i-1];
            
            k = k % fracArray[i-1];
            
            result += indexes[index].ToString();
            
            indexes.RemoveAt(index);
        }
        
        return result;
    }
}

public class Solution {
    public string IntToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };  
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        string result = string.Empty;
        int i = 0;
        while(num > 0){
            while(num >= values[i]){
                result += numerals[i];
                num = num - values[i];
            }
            
            i++;
        }
        
        return result;
    }
}

public class Solution {
    public int romanToInt(String s) {
        //：Ⅰ（1）Ⅴ（5）Ⅹ（10）L（50）C（100）D（500）M（1000） 
        // rules:位于大数的后面时就作为加数；位于大数的前面就作为减数
        //eg：Ⅲ=3,Ⅳ=4,Ⅵ=6,ⅩⅨ=19,ⅩⅩ=20,ⅩLⅤ=45,MCMⅩⅩC=1980
        //"DCXXI"

        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result = map.get(s.charAt(len -1));
        int pivot = result;
        for(int i = len -2; i>= 0;i--){
            int curr = map.get(s.charAt(i));
            if(curr >=  pivot){
                result += curr;
            }else{
                result -= curr;
            }
            pivot = curr;
        }
        return result;
    }
}

public static String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

public class Solution {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
    String[] big= {"", "Thousand", "Million", "Billion"};
    String[] small = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    StringBuilder res = new StringBuilder();
    int count = 0;
    while (num != 0) {
        int cur = num % 1000;
        int o = cur % 10, t = (cur / 10) % 10, h = cur / 100;
        StringBuilder tmp = new StringBuilder();
        if (h != 0) tmp.append(ones[h] + " Hundred ");
        if (t == 1) tmp.append(small[o] + " ");
        else {
            if (t > 1) tmp.append(tens[t-2] + " ");
            if (o > 0) tmp.append(ones[o] + " ");
        }
        if(tmp.length() != 0) tmp.append(big[count] + " ");
        res.insert(0, tmp);
        num /= 1000;
        count++;
    }
    return res.toString().trim();
    }
}

public class Solution {
    public boolean isNumber(String s) {
    s = s.trim();

    boolean pointSeen = false;
    boolean eSeen = false;
    boolean numberSeen = false;
    boolean numberAfterE = true;
    for(int i=0; i<s.length(); i++) {
        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
            numberSeen = true;
            numberAfterE = true;
        } else if(s.charAt(i) == '.') {
            if(eSeen || pointSeen) {
                return false;
            }
            pointSeen = true;
        } else if(s.charAt(i) == 'e') {
            if(eSeen || !numberSeen) {
                return false;
            }
            numberAfterE = false;
            eSeen = true;
        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
            if(i != 0 && s.charAt(i-1) != 'e') {
                return false;
            }
        } else {
            return false;
        }
    }

    return numberSeen && numberAfterE;
}
}

public class Solution {
    public void sortColors(int[] nums) {
        int n0 = 0, n1 = 0, n2 = nums.length;
        
        while(n1 < n2){
            switch(nums[n1]){
                case 0:
                    if(n0 != n1){
                        swap(nums, n0, n1);
                    }
                    n0++; n1++;
                    break;
                case 1:
                    n1++;
                    break;
                case 2: 
                    n2--;
                    swap(nums, n1, n2);
                    break;
            }
        }
    }
    
    public void swap(int[] nums, int n, int m){
        int temp = nums[n];
        nums[n] = nums[m];
        nums[m] = temp;
    }
}

public class Solution {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<String>();
        for(String p : paths){
            if(p.equals(".") || p.equals("/") || p.equals("") || (p.equals("..") && stack.isEmpty())) continue;
            if(p.equals("..")) stack.pop();
            else stack.push(p);
        }
        if(stack.isEmpty()) return "/";
        
        String res = "";
        while(!stack.isEmpty()){
            res = "/" + stack.pop() + res;
        }
        return res;
    }
}

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList();
        List<String> line = new ArrayList();
        int len = 0;
        StringBuilder str = new StringBuilder();
        for(int k = 0; k < words.length; k++){
            String word = words[k];
            int curLen = word.length();
            if(len + curLen + line.size() <= maxWidth){
                line.add(word);
                len += curLen;
            }
            else if(line.isEmpty()){
                res.add(word);
                len = 0;
                continue;
            }
            else{
                int spaceAmount = (maxWidth - len);
                int spaceCount = line.size() == 1 ? spaceAmount : spaceAmount/(line.size()-1);
                int spaceLeft = line.size() == 1 ? 0 : spaceAmount%(line.size()-1);
                
                for(int i = 0; i < line.size(); i++){
                    String cur = line.get(i);
                    str.append(cur);
                    int spaces = spaceLeft-- > 0 ? spaceCount+1 : spaceCount;
                    if(i==line.size()-1 && i!=0) spaces = 0;
                    for(int j = 0; j < spaces; j++){
                        str.append(" ");
                    }
                }
                res.add(str.toString());
                str.setLength(0);
                line.clear();
                line.add(word);
                len = curLen;
            }
            
            if(k == words.length-1){
                int spaceAmount = (maxWidth - len);
                for(int i = 0; i < line.size(); i++){
                    String cur = line.get(i);
                    str.append(cur);
                    int spaces = i==line.size()-1 ? spaceAmount - i : 1;
                    for(int j = 0; j < spaces; j++){
                        str.append(" ");
                    }
                }
                res.add(str.toString());
            }
        }
        return res;
    }
}

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

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
        cur.next = null;
        return dummy.next;
    }
}

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

public class Solution {
    public int numDecodings(String s) {
        if(s.length() == 0 || s.charAt(0) == '0') return 0;
        // r1 is the value at i-2, r2 is the value at i-1.
        int r1 = 1, r2 = 1;
        for(int i = 1; i < s.length(); i++){
        	if(s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6')){
        		r2 = s.charAt(i)=='0' ? r1 : r1+r2;
        		r1 = r2 - r1;
        	}
        	else if(s.charAt(i)=='0'){
        	    return 0;
        	}
        	else{
        		 r1 = r2;
        	}
        }
        return r2;
    }
}

public class Solution {
    public int numDistinct(String s, String t) {
    	int slen = s.length();
    	int tlen = t.length();
        int[][] dis = new int[tlen+1][slen+1];
        dis[0][0] = 1;
        for(int i = 0; i < slen+1; i++){
        	dis[0][i] = 1;
        }
        for(int i = 1; i < tlen+1; i++){
        	dis[i][0] = 0;
        }

        for(int i = 0; i < tlen; i++){
            for(int j = 0; j < slen; j++){
            	if(s.charAt(j) == t.charAt(i)){
            		dis[i+1][j+1] = dis[i+1][j] + dis[i][j];
            	}
            	else{
            		dis[i+1][j+1] = dis[i+1][j];
            	}
            }
        }
        return dis[tlen][slen];
    }
}

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList();
        HashMap<String, List<List<String>>> map = new HashMap();
        wordList.add(endWord);
        helper(map, new ArrayList(), wordList, beginWord, endWord);
        if(map.containsKey(endWord)){
            res.addAll(map.get(endWord));
        }
        return res;
    }
    
    public void helper(HashMap<String, List<List<String>>> map, List<String> path, Set<String> wordList, String beginWord, String endWord){
        Queue<String> queue = new LinkedList();
        queue.offer(beginWord);
        List<List<String>> init = new ArrayList();
        List<String> beg = new ArrayList();
        beg.add(beginWord);
        init.add(beg);
        map.put(beginWord, init);
        boolean findTarget = false;
        while(!queue.isEmpty() && !findTarget){
            int size = queue.size();
            for(int k = 0; k < size; k++){
                String front = queue.poll();
                path.add(front);
                if(wordList.contains(front)) wordList.remove(front);
                
                for(int i = 0; i < front.length(); i++){
                	char[] arr = front.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++){
                        arr[i] = c;
                        String temp = String.valueOf(arr);
                        if(wordList.contains(temp)){
                            List<List<String>> cur = map.get(temp);
                            if(cur == null){
                                cur = new ArrayList();
                                queue.add(temp);
                            }
                            for(List<String> pre : map.get(front)){
                                List<String> tem = new ArrayList(pre);
                                tem.add(temp);
                                cur.add(tem);
                            }
                            map.put(temp, cur);
                        }
                        if(temp.equals(endWord)) findTarget = true;
                    }
                }
            }
            for(String w : queue){
                if(wordList.contains(w)) wordList.remove(w);
            }
        }
    }
}

public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;
        int len = s1.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < s1.length(); i++){
            char c = s1.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c)+1 : 1);
        }
        
        for(int i = 0; i < s2.length(); i++){
            char c = s2.charAt(i);
            if(!map.containsKey(c)) return false;
            if(map.get(c) == 1) map.remove(c);
            else map.put(c, map.get(c)-1);
        }
        
        if(!map.isEmpty()) return false;
        
        for(int i = 0; i < len-1; i++){
            if((isScramble(s1.substring(0, i+1), s2.substring(0, i+1)) &&
                isScramble(s1.substring(i+1), s2.substring(i+1))) ||
               (isScramble(s1.substring(0, i+1), s2.substring(len-i-1)) &&
                isScramble(s1.substring(i+1), s2.substring(0, len-i-1)))){
                    return true;
                }
        }
        return false;
    }
}

public class Solution {
    public int candy(int[] ratings) {
        if(ratings.length == 0) return 0;
        if(ratings.length == 1) return 1;
        int startIndex = 0;
        int res = 0;
        int[] counts = new int[ratings.length];
        int candy = 1;
        for(int i = 1; i < ratings.length; i++){
        	if(ratings[i] < ratings[i-1]) continue;
        	for(int j = i-1; j >= startIndex; j--){
        		counts[j] = (j == startIndex && j > 0 && ratings[j] > ratings[j-1]) ? Math.max(counts[j-1]+1, candy++) : candy++;
        	}
        	candy = 1;
        	startIndex = i;
        }

        for(int j = ratings.length-1; j >= startIndex; j--){
        	counts[j] = (j == startIndex && j > 0 && ratings[j] > ratings[j-1]) ? Math.max(counts[j-1]+1, candy++) : candy++;
        }

        for(int count : counts){
        	res += count;
        }

        return res;
    }
}

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Stack<UndirectedGraphNode> stack = new Stack<UndirectedGraphNode>();
        stack.push(node);
        map.put(node, new UndirectedGraphNode(node.label));
        while(!stack.isEmpty()){
            UndirectedGraphNode top = stack.pop();
            
            for(UndirectedGraphNode neighbor : top.neighbors){
                if(!map.containsKey(neighbor)){
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    stack.push(neighbor);
                }
                map.get(top).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
}

public class Solution {
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        
        for(int num : nums){
            max = max < num ? num : max;
            min = min > num ? num : min;
        }
        if(max == min) return 0;
        
        int count = nums.length + 1;
        int bucketGap = (max - min)/count;
        bucketGap = bucketGap == 0 ? 1 : bucketGap;
        
        int[] bucketMins = new int[count];
        int[] bucketMaxs = new int[count];
        
        Arrays.fill(bucketMins, Integer.MAX_VALUE);
        Arrays.fill(bucketMaxs, Integer.MIN_VALUE);
        
        for(int num : nums){
            int index = (num-min)/bucketGap;
            index = index >= count ? count-1 : index;
            bucketMins[index] = bucketMins[index] > num ? num : bucketMins[index];
            bucketMaxs[index] = bucketMaxs[index] < num ? num : bucketMaxs[index];
        }
        
        int maxGap = 0, pre = bucketMaxs[0];
        for(int i = 1; i < count; i++){
            if(bucketMins[i] == Integer.MAX_VALUE && bucketMaxs[i] == Integer.MIN_VALUE) continue;
            maxGap = Math.max(maxGap, bucketMins[i] - pre);
            pre = bucketMaxs[i];
        }
        return maxGap;
    }
}

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

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k <= 0) return false;
        TreeSet<Integer> tree = new TreeSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            Integer floor = tree.floor(nums[i]);
            Integer ceiling = tree.ceiling(nums[i]);
            if((floor != null && floor >= nums[i]-t) ||
               (ceiling != null && ceiling <= nums[i]+t)){
                   return true;
               }
            tree.add(nums[i]);
            if(i >= k) tree.remove(nums[i-k]);
        }
        return false;
    }
}


class Solution {
    /*
     * param k : As description.
     * param n : As description.
     * return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        int count = 0;
        int base = 1;
        while (n / base >= 1) {
            int curBit = n % (base*10) / base;
            int higher = n / (base*10);
            int lower = n % base;
            if (curBit < k) {
                count += higher * base;
            }
            else if (curBit == k) {
                count += higher * base + lower + 1;
            }
            else {
                if(k != 0 || n < 10*base) count += (higher + 1) * base;
            }
            base *= 10;
        }
        return count;
    }
};

public class Solution {
    public int[] singleNumber(int[] nums) {
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }
}

public class Solution {
    public int nthUglyNumber(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        int i2 = 0, i3 =0, i5 =0;
        
        while(list.size() < n){
            int m = Math.min(list.get(i2)*2, Math.min(list.get(i3)*3, list.get(i5)*5));
            list.add(m);
            i2 = list.get(i2)*2 == m ? i2+1 : i2;
            i3 = list.get(i3)*3 == m ? i3+1 : i3;
            i5 = list.get(i5)*5 == m ? i5+1 : i5;
        }
        return list.get(n-1);
    }
}

public class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1, mid = 0;
        
        return findMin(nums, l, r);
    }
    
    public int findMin(int[] nums, int l, int r){
        if(nums[l] < nums[r] || l==r) return nums[l]; 
        int mid = (l+r)/2;
        
        if(nums[l] == nums[mid] && nums[mid] == nums[r]) return Math.min(findMin(nums, l, mid), findMin(nums, mid+1, r));
        if(nums[mid] >= nums[l]) return findMin(nums, mid+1, r);
        return findMin(nums, l, mid);
    }
}

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, postorder.length-1, 0, inorder.length-1);
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder, int postStart, int inStart, int inEnd){
        if(postStart < 0 || inStart>inEnd) return null;
        int rootVal = postorder[postStart];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == rootVal) {
                inIndex = i;
                break;
            }
        }
        
        root.left = buildTree(inorder, postorder, postStart-(inEnd-inIndex+1), inStart, inIndex-1);
        root.right = buildTree(inorder, postorder, postStart-1, inIndex+1, inEnd);
        return root;
        
    }
}

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
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        postOrder(root, result);
        return result;
    }
    
    public void postOrder(TreeNode root, List<Integer> result){
        if(root == null) return;
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode lastVisited = null;
        TreeNode node = root;
        while(!stack.isEmpty() || node != null){
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        if(stack.peek().right != null && stack.peek().right != lastVisited){
            node = stack.peek().right;
        }
        else{
            TreeNode top = stack.pop();
            result.add(top.val);
            lastVisited = top;
        }
        }
        return result;
    }
}