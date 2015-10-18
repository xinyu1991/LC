/* Easy
Maximum Depth of Binary Tree

55% Accepted
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example
Given a binary tree as follow:

  1
 / \ 
2   3
   / \
  4   5
The maximum depth is 3.
*/

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

/*
Easy Insert Node in a Binary Search Tree

40% Accepted
Given a binary search tree and a new tree node, insert the node into the tree. 
You should keep the tree still be a valid binary search tree.

Example
Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6
Challenge
Can you do it without recursion?
*/
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if(root == null) return node;
        if(node == null) return root;
        TreeNode cur = root;
        while(cur != null){
        	if(cur.val == node.val) return root;
        	if(cur.val < node.val){
        		if(cur.right == null) {
        			cur.right = node;
        			break;
        		}
        		cur = cur.right;
        	}
        	else {
        		if(cur.left == null) {
        			cur.left = node;
        			break;
        		}
        		cur = cur.left;
        	}
        }
        return root;
    }
}

/*
Easy Binary Tree Preorder Traversal

39% Accepted
Given a binary tree, return the preorder traversal of its nodes' values.
Example
Given binary tree {1,#,2,3}:

1
 \
  2
 /
3
return [1,2,3].

Challenge
Can you do it without recursion?
*/

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack();
        addLeftChild(root, stack, res);
        while(!stack.isEmpty()){
        	TreeNode top = stack.pop();
        	if(top.right != null){
        		addLeftChild(top.right, stack, res);
        	}
        }
        return res;
    }
    public void addLeftChild(TreeNode root, Stack<TreeNode> stack, ArrayList<Integer> res){
    	while(root != null){
        	stack.push(root);
        	res.add(root.val);
        	root = root.left;
        }
    }

    public void preorderTraversalRec(TreeNode root, ArrayList<Integer> res){
    	if(root == null) return;
    	res.add(root.val);
    	preorderTraversalRec(root.left, res);
    	preorderTraversalRec(root.right, res);
    }
}

/*
Medium Validate Binary Search Tree

22% Accepted
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example
An example:

  2
 / \
1   3
   /
  4
   \
    5
The above binary tree is serialized as {2,1,3,#,#,4,#,#,5} (in level order).
*/
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return true;
        Stack<TreeNode> stack = new Stack();
        addLeftChild(root, stack);
        TreeNode pre = null, cur = null;
        while(!stack.isEmpty()){
        	cur = stack.pop();
        	if(pre != null && pre.val >= cur.val) return false;
        	if(cur.right != null) addLeftChild(cur.right, stack);
        	pre = cur;
        }
        return true;
    }

    private void addLeftChild(TreeNode root, Stack<TreeNode> stack){
    	while(root != null){
        	stack.push(root);
        	root = root.left;
        }
    }
}

/*
Medium Balanced Binary Tree

40% Accepted
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

Example
Given binary tree A={3,9,20,#,#,15,7}, B={3,#,20,15,7}

A)  3            B)    3 
   / \                  \
  9  20                 20
    /  \                / \
   15   7              15  7
The binary tree A is a height-balanced binary tree, but B is not.
 1
  \
   2
  /
 3 
*/

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return isBalanced(root.left) && isBalanced(root.right) 
               && Math.abs(height(root.left)-height(root.right)) < 2;
    }

    public int height(TreeNode root){
    	if(root == null) return 0;
    	return 1 + Math.max(height(root.left), height(root.right));
    }
}

/*
Medium 
Construct Binary Tree from Preorder and Inorder Traversal

27% Accepted
Given preorder and inorder traversal of a tree, construct the binary tree.

Have you met this question in a real interview? Yes
Example
Given in-order [1,2,3] and pre-order [2,1,3], return a tree:

  2
 / \
1   3
Note
You may assume that duplicates do not exist in the tree.
*/
public class Solution {
    /**
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) return null;
        return buildTree(preorder, inorder, 0, 0, inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd){
        if(preStart >= preorder.length) return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int inRoot = -1;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == rootVal) {
                inRoot = i; 
                break;
            }
        }
        root.left = inRoot == inStart ? null : buildTree(preorder, inorder, preStart+1, inStart, inRoot-1);
        root.right = inRoot == inEnd ? null : buildTree(preorder, inorder, preStart+inRoot-inStart+1, inRoot+1, inEnd);
        return root;
    }
}

/*
Medium Binary Tree Level Order Traversal

32% Accepted
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

Have you met this question in a real interview? Yes
Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
Challenge
Challenge 1: Using only 1 queue to implement it.

Challenge 2: Use DFS algorithm to do it.
*/
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> curLevel = new ArrayList();
            // Poll out all TreeNode in cur level.
            for(int i = 0; i < size; i++){
                TreeNode front = queue.poll();
                curLevel.add(front.val);
                if(front.left != null) queue.offer(front.left);
                if(front.right != null) queue.offer(front.right);
            }
            res.add(curLevel);
        }
        return res;
    }
}

/*
Medium Search Range in Binary Search Tree

36% Accepted
Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. Find all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST. Return all the keys in ascending order.

Have you met this question in a real interview? Yes
Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].

    20
   /  \
  8   22
 / \
4   12
*/

public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        ArrayList<Integer> res = new ArrayList();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack();
        addLeftChild(root, stack);
        while(!stack.isEmpty()){
            TreeNode front = stack.pop();
            if(front.val >= k1 && front.val <= k2) res.add(front.val);
            if(front.right != null) addLeftChild(front.right, stack);
        }
        return res;
    }

    private void addLeftChild(TreeNode root, Stack<TreeNode> stack){
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}

/*
Medium Binary Tree Serialization

16% Accepted
Design an algorithm and write code to serialize and deserialize a binary tree. 
Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

There is no limit of how you deserialize or serialize a binary tree, you only need to make sure you can serialize a binary tree to a string and deserialize this string to the original structure.

Example
An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:

  3
 / \
9  20
  /  \
 15   7
Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.

You can use other method to do serializaiton and deserialization.
*/

class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        StringBuilder res = new StringBuilder();
        if(root == null) return res.toString();
        int height = getHeight(root);
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        res.append(root.val+",");
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode front = queue.poll();
                if(front.left != null){
                    queue.offer(front.left);
                    res.append(front.left.val+",");
                } 
                else if(level != height) res.append("#,");
                if(front.right != null) {
                    queue.offer(front.right);
                    res.append(front.right.val+",");
                }
                else if(level != height) res.append("#,");
            }
            level++;
        }

        return res.toString();
    }

    public int getHeight(TreeNode root){
        if(root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int nodeIndex = 0;
        while(!queue.isEmpty() && (nodeIndex*2+1 < nodes.length)){
            TreeNode front = queue.poll();
            String leftVal = nodes[nodeIndex*2+1], rightVal = nodes[nodeIndex*2+2];
            front.left = leftVal.equals("#") ? null : new TreeNode(Integer.valueOf(leftVal));
            front.right = rightVal.equals("#") ? null : new TreeNode(Integer.valueOf(rightVal));            
            if(front.left != null) queue.offer(front.left);
            if(front.right != null) queue.offer(front.right);
            nodeIndex++;
        }
        return root;
    }
}

/*
Hard 
Remove Node in Binary Search Tree

25% Accepted
Given a root of Binary Search Tree with unique value for each node.  Remove the node with given value. 
If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.

Have you met this question in a real interview? Yes
Example
Given binary search tree:

          5

       /    \

    3          6

 /    \
2       4
Remove 3, you can either return:

          5

       /    \

    2          6

      \

         4

or :

          5

       /    \

    4          6

 /   

2
*/

public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        if(root == null) return root;
        if(root.val == value){
            if (root.left == null && root.right == null) return null;
            if(root.right == null) return root.left;
            root.val = removeSmallestRightChild(root, root.right);
            return root;
        } 
        TreeNode parent = root;
        TreeNode node = root.val > value ? root.left : root.right;
        while(node!=null){
            if(node.val < value){
                parent = node;
                node = node.right;
            } 
            else if(node.val > value){
                parent = node;
                node = node.left;
            } 
            else{
                if(node.right == null && node.left == null) node = null;
                else if(node.right == null){
                    if(parent.left == node) parent.left = node.left;
                    else parent.right = node.left;
                    break;
                } 
                else {
                    node.val = removeSmallestRightChild(node, node.right);
                    break;
                }
            }
        }
        return root;
    }

    private int removeSmallestRightChild(TreeNode parent, TreeNode child){
        if(child.left == null){
            parent.right = child.right;
            return child.val; 
        } 
        while(child.left != null){
            parent = child;
            child = child.left;
        }
        parent.left = child.right;
        return child.val;
    }
}
