/*
Medium Combinations Show result 

32% Accepted
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example
For example,
If n = 4 and k = 2, a solution is:
[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
*/

public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
		// write your code here
		List<List<Integer>> res = new ArrayList();
		if(n == 0 || k == 0) return res;
		helper(res, new ArrayList(), n, 1, k);
		return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> path, int n, int startIndex, int k){
        if(path.size() == k){
            res.add(new ArrayList(path));
            return;
        }
        
        for(int i = startIndex; i <= n; i++){
            path.add(i);
            helper(res, path, n, i+1, k);
            path.remove(path.size()-1);
        }
    }
}

/*
Medium Combination Sum

27% Accepted
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

Note
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
*/

public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        if(candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper(res, new ArrayList(), candidates, target, 0, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int startIndex){
        if(sum == target){
            res.add(new ArrayList(path));
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            if(i > 0 && candidates[i] == candidates[i-1]) continue;
            if(sum+candidates[i] > target) continue;
            path.add(candidates[i]);
            helper(res, path, candidates, target, sum+candidates[i], i);
            path.remove(path.size()-1);
        }
    }
}

/*
Medium Subsets

22% Accepted
Given a set of distinct integers, return all possible subsets.

Example
If S = [1,2,3], a solution is:

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
Note
Elements in a subset must be in non-descending order.

The solution set must not contain duplicate subsets.
*/
class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        res.add(new ArrayList());
        helper(res, new ArrayList(), nums, 0);
        return res;
    }

    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, int[] nums, int startIndex){
        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            res.add(new ArrayList(path));
            helper(res, path, nums, i+1);
            path.remove(path.size()-1);
        }
    }
}

/*
Medium Subsets II

24% Accepted
Given a list of numbers that may has duplicate numbers, return all possible subsets

Have you met this question in a real interview? Yes
Example
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
Note
Each element in a subset must be in non-descending order.

The ordering between two subsets is free.

The solution set must not contain duplicate subsets.
*/
class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        Collections.sort(S);
        res.add(new ArrayList());
        helper(res, new ArrayList(), S, 0);
        return res;
    }

    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, ArrayList<Integer> nums, int startIndex){
        for(int i = startIndex; i < nums.size(); i++){
            if(i > startIndex && nums.get(i) == nums.get(i-1)) continue;
            path.add(nums.get(i));
            res.add(new ArrayList(path));
            helper(res, path, nums, i+1);
            path.remove(path.size()-1);
        }
    }
}


/*
Medium Permutations

24% Accepted
Given a list of numbers, return all possible permutations.

Have you met this question in a real interview? Yes
Example
For nums = [1,2,3], the permutations are:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
Challenge
Do it without recursion.
*/

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        Collections.sort(nums);
        helper(res, new ArrayList(), nums, new boolean[nums.size()]);
        return res;
    }

    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, ArrayList<Integer> nums, boolean[] visited){
        if(path.size() == nums.size()){
            res.add(new ArrayList(path));
            return;
        }
        for(int i = 0; i < nums.size(); i++){
            if(!visited[i]){
                path.add(nums.get(i));
                visited[i] = true;
                helper(res, path, nums, visited);
                visited[i] = false;
                path.remove(path.size()-1);
            } 
        }
    }
}

/*
Medium Permutations II

22% Accepted
Given a list of numbers with duplicate number in it. Find all unique permutations.

Have you met this question in a real interview? Yes
Example
For numbers [1,2,2] the unique permutations are:

[

    [1,2,2],

    [2,1,2],

    [2,2,1]

]

Challenge
Do it without recursion.
*/

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        if(nums == null) return res;
        Collections.sort(nums);
        helper(res, new ArrayList(), nums, new boolean[nums.size()]);
        return res;
    }

    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, ArrayList<Integer> nums, boolean[] visited){
        if(path.size() == nums.size()){
            res.add(new ArrayList(path));
            return;
        }
        for(int i = 0; i < nums.size(); i++){
            if(!visited[i]){
                if(i>0 && nums[i] == nums[i-1]) continue;
                path.add(nums.get(i));
                visited[i] = true;
                helper(res, path, nums, visited);
                visited[i] = false;
                path.remove(path.size()-1);
            } 
        }
    }
}

/*
Medium Topological Sorting Show result 

25% Accepted
Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

Example
For graph as follow:

picture

The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...
Note
You can assume that there is at least one topological order in the graph.

Challenge
Can you do it in both BFS and DFS?
*/
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    // DFS : keep a visited map. Traverse into the bottom.     
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashSet<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
        ArrayList<DirectedGraphNode> res = new ArrayList();
        for(DirectedGraphNode node : graph){
            if(visited.contains(node)) continue;
            dfs(node, res, visited, graph);
        }
        return res;
    }
    
    private void dfs(DirectedGraphNode node, ArrayList<DirectedGraphNode> res, HashSet<DirectedGraphNode> visited, ArrayList<DirectedGraphNode> graph){
        visited.add(node);
        for(DirectedGraphNode child : node.neighbors){
            if(!visited.contains(child)) dfs(child, res, visited, graph);
        }
        res.add(0, node);
    }

    // BFS : Add nodes without dependency first. Then traverse the map by removing visited dependency.
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> res = new ArrayList();
        HashMap<DirectedGraphNode, Integer> map = new HashMap<DirectedGraphNode, Integer>();
        Queue<DirectedGraphNode> queue = new LinkedList();

        for(DirectedGraphNode node : graph){
            for(DirectedGraphNode neighbor : node.neighbors){
                if(map.containsKey(neighbor)) map.put(neighbor, map.get(neighbor)+1);
                else map.put(neighbor, 1);
            }
        }

        for(DirectedGraphNode node : graph){
            if(!map.containsKey(node)){
                queue.offer(node);
                res.add(node);
            }
        }

        while(!queue.isEmpty()){
            DirectedGraphNode front = queue.poll();
            for(DirectedGraphNode neighbor : front.neighbors){
                if(map.containsKey(neighbor)){
                    int count = map.get(neighbor);
                    if(count == 1) {
                        map.remove(neighbor);
                        queue.offer(neighbor);
                        res.add(neighbor);
                    }
                    else{
                        map.put(neighbor, count-1);
                    }
                }
            }
        }
        return res;
    }
}

/*
Medium N-Queens

21% Accepted
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Have you met this question in a real interview? Yes
Example
There exist two distinct solutions to the 4-queens puzzle:

[

    [".Q..", // Solution 1

     "...Q",

     "Q...",

     "..Q."],

    ["..Q.", // Solution 2

     "Q...",

     "...Q",

     ".Q.."]

]

Challenge
Can you do it without recursion?
*/

class Solution {
    /**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
    ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> res = new ArrayList();
        char[][] board = new char[n][n];
        for(char[] level : board){
            Arrays.fill(level, '.');
        }
        solve(res, board, n, 0);
        return res;
    }

    private void solve(ArrayList<ArrayList<String>> res, char[][] board, int n, int row){
        if(row == n){
            res.add(boardToString(board));
            return;
        }
        for(int i = 0; i < n; i++){
            if(validPos(board, row, i)){
                board[row][i] = 'Q';
                solve(res, board, n, row+1);
                board[row][i] = '.';
            }
        }
    }

    private boolean validPos(char[][] board, int row, int col){
        for(int i = 0; i < row; i++){
            if(board[i][col] == 'Q') return false;
        }
        for(int i = row-1, j = col-1; i>= 0 && j >= 0; i--, j--){
            if(board[i][j] == 'Q') return false;
        }
        for(int i = row-1, j = col+1; i >= 0 && j < board.length; i--, j++){
            if(board[i][j] == 'Q') return false;
        }
        return true;
    }

    private ArrayList<String> boardToString(char[][] board){
        ArrayList<String> res = new ArrayList();
        for(int i = 0; i < board.length; i++){
            String level = "";
            for(int j = 0; j < board[0].length; j++){
                level += board[i][j];
            }
            res.add(level);
        }
        return res;
    }
};

/*
Medium Word Ladder

22% Accepted
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
Have you met this question in a real interview? Yes
Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class Solution {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return an integer
      */
    public int ladderLength(String start, String end, Set<String> dict) {
        Queue<String> queue = new LinkedList();
        queue.offer(start);
        dict.add(end);
        int step = 0;
        while(!queue.isEmpty()){
            LinkedList<String> level = new LinkedList();
            step++;
            while(!queue.isEmpty()){
                String front = queue.poll();
                if(front.equals(end)) return step;
                char[] arr = front.toCharArray();
                for(int i = 0; i < front.length(); i++){
                    char temp = arr[i];
                    for(char c = 'a'; c <= 'z'; c++){
                        arr[i] = c;
                        String newStr = String.valueOf(arr);
                        if(dict.contains(newStr)){
                            level.add(newStr);
                            dict.remove(newStr);
                        }
                    }
                    arr[i] = temp;
                }
            }
            queue = level;
        }
        return step;
    }
}


/*
Medium Print Numbers by Recursion Show result 

22% Accepted
Print numbers from 1 to the largest number with N digits by recursion.

Have you met this question in a real interview? Yes
Example
Given N = 1, return [1,2,3,4,5,6,7,8,9].

Given N = 2, return [1,2,3,4,5,6,7,8,9,10,11,12,...,99].

Note
It's pretty easy to do recursion like:

recursion(i) {
    if i > largest number:
        return
    results.add(i)
    recursion(i + 1)
}
however this cost a lot of recursion memory as the recursion depth maybe very large. Can you do it in another way to recursive with at most N depth?

Challenge
Do it in recursion, not for-loop.
*/
public class Solution {
    /**
     * @param n: An integer.
     * return : An array storing 1 to the largest number with n digits.
     */
    public List<Integer> numbersByRecursion(int n) {
        List<Integer> res = new ArrayList<Integer>();
        if(n == 0) return res;
        List<Integer> lastStep = numbersByRecursion(n-1);
        int base = (int)Math.pow(10, n-1);
        for(int i = 0; i <= 9; i++){
            if(i > 0) res.add(base*i);
            for(int num : lastStep){
                res.add(base*i+num);
            }
        }
        return res;
    }
}