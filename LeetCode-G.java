/*
Flip Game 
You are playing the following Flip Game with your friend: Given a string 
that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
*/
public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList();
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++){
        	if(arr[i] == '-') continue;
        	if(i < arr.length-1 && arr[i+1] == '+'){
        		arr[i] = arr[i+1] = '-';
        		res.add(String.valueOf(arr));
        		arr[i] = arr[i+1] = '+';
        	}
        }
        return res;
    }
}

/*
You are playing the following Flip Game with your friend: 
Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
---+++++++--+++--++++--
+ lose
++ win
+++ win
++++ win
+++++ lose
++++++ win
+++++ ++ win
++++++++ win
+++++++++ lose
++++++++++ win

+++++++++
"++++++"
Follow up:
Derive your algorithm's runtime complexity.
*/

public class Solution {
    public boolean canWin(String s) {
    	if(s==null || s.length() < 2) return false; 
        boolean[] canWin = new boolean[s.length()];
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == '-'){
        		canWin[i] = i == 0 ? false : canWin[i-1];
        		continue;
        	} 
        	start = i;
        	while(i < s.length() && s.charAt(i) == '+'){
        		end = i;
        		if(i==0) canWin[i] = false;
        		else if(end == start) canWin[i] = canWin[i-1];
        		else{
        			int dis = end-start;
        			canWin[i] = (dis%5) == 4 ? canWin[start] : !canWin[start];
        		}
        		i++;
        	}
        }
        return canWin[s.length()-1];
    }
}