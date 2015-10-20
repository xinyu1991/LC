/*
Easy Remove Nth Node From End of List

28% Accepted
Given a linked list, remove the nth node from the end of list and return its head.

Example
Given linked list: 1->2->3->4->5->null, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5->null.
Note
The minimum number of nodes in list is n.

Challenge
O(n) time
*/
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
    	if(head == null || n == 0) return head;
        ListNode faster = head;
        ListNode slower = head;
        while(n>0){
        	faster = faster.next;
        	n--;
        }
        // If faster is null, n is the length of the list, remove head.
        if(faster == null){
        	ListNode temp = head.next;
        	head.next = null;
        	return temp;
        }

        while(faster.next != null){
        	faster = faster.next;
        	slower = slower.next;
        }
        ListNode nex = slower.next;
        slower.next = nex.next;
        return head;
    }
}

/*
Easy Merge Two Sorted Lists Show result 

38% Accepted
Merge two sorted (ascending) linked lists and return it as a new sorted list. 
The new sorted list should be made by splicing together the nodes of the two lists and sorted in ascending order.

Example
Given 1->3->8->11->15->null, 2->null , return 1->2->3->8->11->15->null.
*/
public class Solution {
    /**
     * @param ListNode l1 is the head of the linked list
     * @param ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }
            else{
                cur.next = l2;
                l2= l2.next;
            }
            cur = cur.next;
        }
        if(l1!=null) cur.next = l1;
        if(l2!=null) cur.next = l2;
        return dummy.next;
    }
}

/*
Easy Remove Duplicates from Sorted List

38% Accepted
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

public class Solution {
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) { 
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy.next;

        while(head != null){
        	if(head.val != cur.val){
        		cur.next = head;
        		cur = cur.next;
        	}
        	head = head.next;
        }
        cur.next = null;
        return dummy.next;
    }  
}

/*
Easy Partition List

29% Accepted
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2->null and x = 3,
return 1->2->2->4->3->5->null.
*/

public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode 
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummyLeft = new ListNode(0);
        ListNode dummyRight = new ListNode(0);
        ListNode curLeft = dummyLeft;
        ListNode curRight = dummyRight;

        while(head != null){
        	if(head.val < x){
        		curLeft.next = head;
        		curLeft = curLeft.next;
        	}
        	else{
        		curRight.next = head;
        		curRight = curRight.next;
        	}
        	head = head.next;
        }
        curRight.next = null;
        curLeft.next = dummyRight.next;
        return dummyLeft.next;
    }
}

/*
Easy Reverse Linked List

41% Accepted
Reverse a linked list.

Example
For linked list 1->2->3, the reversed linked list is 3->2->1

Challenge
Reverse it in-place and in one-pass
*/
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode nex = dummy.next;
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
Medium Rotate List

26% Accepted
Given a list, rotate the list to the right by k places, where k is non-negative.

Example
Given 1->2->3->4->5 and k = 2, return 4->5->1->2->3.

1-2-3-4-5-null 3
2-3-4-5-1-null 2
3-4-5-1-2-null 1
4-5-1-2-3-null 0
*/
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || haed.next == null || k == 0) return head;
        ListNode tail = head;
        int length = 1;

        while(tail.next != null){
        	tail = tail.next;
        	length++;
        }
        // The step to go.
        int step = length - (k % length);
        
        while(step > 0){
        	tail.next = head;
        	head = head.next;
        	tail.next.next = null;
        	tail = tail.next;
        	step--;
        }
        return head;
    }
}

/* Medium 
Convert Sorted List to Binary Search Tree

27% Accepted
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Example
               2
1->2->3  =>   / \
             1   3
*/

public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {  
        // write your code here
        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.val);

        ListNode faster = head;
        ListNode slower = head;
        ListNode pre = null;
        while (faster != null && faster.next != null){
        	faster = faster.next.next;
        	pre = slower;
        	slower = slower.next;
        }
        TreeNode root = new TreeNode(slower.val);
        if(pre == null) root.left = null;
        else{
        	pre.next = null;
        	root.left = sortedListToBST(head);
        }
        root.right = sortedListToBST(slower.next);
        return root;
    }
}

/*
Copy List with Random Pointer

27% Accepted
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Example
Challenge
Could you solve it with O(1) space?
*/

public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head ==null) return null;
        HashMap<RandomListNode, RandomListNode> visitedNodes = new HashMap();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode copy = new RandomListNode(head.label);
        dummy.next = copy;
        visitedNodes.put(head, copy);
        while(head != null){
        	if(head.next == null){
        		copy.next = null;
        	}
        	else if(visitedNodes.containsKey(head.next)){
        		copy.next = visitedNodes.get(head.next);
        	}
        	else{
        		copy.next = new RandomListNode(head.next.label);
        		visitedNodes.put(head.next, copy.next);
        	}
        	if(head.random == null){
        		copy.random = null;
        	}
        	else if(visitedNodes.containsKey(head.random)){
        		copy.random = visitedNodes.get(head.random);
        	}
        	else{
        		copy.random = new RandomListNode(head.random.label);
        		visitedNodes.put(head.random, copy.random);
        	}
        	head = head.next;
        	copy = copy.next;
        }
        return dummy.next;
    }

    // O(1) space solution. Side by side operation.
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head ==null) return null;
        RandomListNode iter = head, next;
        
        // Add the new copy right after its original node.
        while(iter != null){
        	next = iter.next;
        	iter.next = new RandomListNode(iter.label);
        	iter.next.next = next;
        	iter = next;
        }
        iter = head;
        while(iter != null){
        	iter.next.random = iter.random == null ? null : iter.random.next;
        	iter = iter.next.next;
        }

        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        while(head != null){
        	cur.next = head.next;
        	cur = cur.next;
        	head = head.next.next;
        }
        return dummy.next;
    }
}

/*
Medium Linked List Cycle

47% Accepted
Given a linked list, determine if it has a cycle in it.
Example
Given -21->10->4->5, tail connects to node index 1, return true

Challenge
Follow up:
Can you solve it without using extra space?
*/

public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {  
        if(head == null || head.next == null) return false;
        ListNode faster = head;
        ListNode slower = head;

        while(faster != null && faster.next != null){
        	faster = faster.next.next;
        	slower = slower.next;
        	if(faster == slower) return true;
        }

        return false;
    }
}


/*
Medium Reorder List

22% Accepted
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

Example
For example,
Given 1->2->3->4->null, reorder it to 1->4->2->3->null.
*/

public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: void
     */
    public void reorderList(ListNode head) {  
        if(head == null || head.next == null || head.next.next == null) return;

        ListNode faster = head, slower = head, pre = null;
        // Find the middle point.
        while(faster != null && faster.next != null){
        	faster = faster.next.next;
        	pre = slower;
        	slower = slower.next;
        }
        // Swap the right part.
        ListNode nex = null;
        while(slower!=null){
        	nex = pre.next;
        	pre.next = slower;
        	slower = slower.next;
        	pre.next.next = nex;
        }

        // Merge.
        slower = pre.next;
        faster = head;
        while(faster != pre){
        	pre.next = slower.next;
        	slower.next = faster.next;
        	faster.next = slower;
        	faster = faster.next.next;
        	slower = pre.next;
        }
    }
}

/*
Medium Sort List

27% Accepted
Sort a linked list in O(n log n) time using constant space complexity.

Example
Given 1-3->2->null, sort it to 1->2->3->null.
*/

public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
     *               using constant space complexity.
     */
    public ListNode sortList(ListNode head) {  
        if(head == null || head.next == null) return head;

        ListNode faster = head, slower = head, pre = null;
        while(faster != null && faster.next != null){
        	faster = faster.next.next;
        	pre = slower;
        	slower = slower.next;
        }
        pre.next = null;
        return mergeTwoLists(sortList(head), sortList(slower));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }
            else{
                cur.next = l2;
                l2= l2.next;
            }
            cur = cur.next;
        }
        if(l1!=null) cur.next = l1;
        if(l2!=null) cur.next = l2;
        return dummy.next;
    }
}

/*
Medium Intersection of Two Linked Lists Show result 

42% Accepted
Write a program to find the node at which the intersection of two singly linked lists begins.

Have you met this question in a real interview? Yes
Example
The following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

Note
If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Challenge
Your code should preferably run in O(n) time and use only O(1) memory.
*/

public class Solution {
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode 
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode nodeA = headA, nodeB = headB;
        int lengthA = 0, lengthB = 0;
        while(nodeA != null){
            lengthA++;
            nodeA = nodeA.next;
        }
        while(nodeB != null){
            lengthB++;
            nodeB = nodeB.next;
        }
        nodeA = headA;
        nodeB = headB;
        if(lengthA > lengthB){
            for(int i = 0; i < lengthA - lengthB; i++){
                nodeA = nodeA.next;
            }
        }
        else if(lengthA < lengthB){
            for(int i = 0; i < lengthB - lengthA; i++){
                nodeB = nodeB.next;
            }
        }
        
        while(nodeA != null && nodeB != null){
            if(nodeA == nodeB) return nodeA;
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return null;
    }  
}