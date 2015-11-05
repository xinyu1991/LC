1. matrix class query / update.


2. Server A to Server B  backup huge file with small change. -> use Rsync.


3. Sparse Vector : hashTable to store the index -> value pair. Dotproduct will be O(n) by go through the keyset of one vector.

4. Linux diff, traverse the two input directory, find all the sub file, sub folder. Create relative path between input directory and files in it. Then, we can get two HashSets, Compare the items in both hashSet, 
Get group A : path/file in Set A, but not in Set B.
Group B: path/file in SetB, but not in Set A.
For files that have same relative path, compare the md5 hash of the file contents.

5. 有一排数量无限的object，每个object有两个状态，可以用true和false来表示，object的状态是可切换的，初始情况下所有object的状态都是false。
让你设计一个class，实现两个方法：isToggled(long idx), toggle(long start, long end)
Use an interval tree.

6. Spiral Matrix:
Keep track of RowBegin, RowEnd, ColBegin, ColEnd; Four forloop in a while loop (rowBegin <= rowEnd && ColBegin <= colEnd). until the matrix was all traversed.

7. Signature of number list (http://www.careercup.com/question?id=14912744):
Use a LinkedList, if is increasing just add the new Node to the back, if is the first decrese, just add it to the front of last node, if it is consecutive decrese, add it to the front of last decres node.
iidiiddii 
1->2->3 null,  1->2->4->3 4,    1->2->4->3->5->6  4, 1-2-4-3-5-7-6 7, 1-2-4-3-5-8-7-6 8, 1-2-4-3-5-8-7-6-9-10 iidiiddii.

This can also be solved by Topological sort, for iiddiid, it would be 1->2->3<-4<-5->6->7<-8, Topological sort with constrain that left(smaller) child goes first. 1-2-5-4-3-6-8-7 iiddiid

8. Give an array, find the count of Unique Binary Trees. DP.

9. ⼤大概就是，⼀一个数组, 1112223334445556677888...当中少2个数字，找到就⾏行了

10. ⼀一个数组
1,4,2,6.....
每次调⽤用⼀一个函数，按照数组⾥里⾯面的数字的⼤大⼩小，返回相应的 Index。
⽐比如， 上⾯面的例⼦子就是
1/13 的概率返回0,
4/13的概率返回1
先找cumulative probability［1, 5, 7, 13]， 然后弄个0〜～13
之间的random数字⽐比较过去找它的位置就好 (binary search)


11. 一队人打散后，每个人有一个高度，每个人记得原来前面有几个人比他高，没有重复⾼高度，求复原原始队列
Sort by Tvalue, then height, in increasing order. Then iterate from the beginning, insert value if Tvalue > 0

Example : Original 3, 5, 2, 4, 7 . Then, the Tvalue will be  0, 0, 2, 1, 0.
Sort the list by TValue, then height, Then the sorted list will be:

3 5 7 4 2
0 0 0 1 2

Then, insert people
3   3 5 7
0 , 0 0 0, 
When inserting (4,1), we need to watch the Tvalue, starting from the first element in the list, if the element is higher than 4, decrese TValue by 1, insert the node when Tvalue is 0.
3 5 4 7    3 5 2 4 7
0 0 1 0,   0 0 2 1 0

12. UTF Pattern validate. Bit operation  to match 10XXXXXX, use 10XXXXXX & 11000000 = 10000000

13. ⼀一个full binary tree的定义是每个node有0个或两个左右child，
给定树的⾼高度，求有多少种这样的binary tree

14. Leetcode 139,140: Word Break I, II

15. Roman -> Integer

16 backpack

17 skiing

18。 ⽤用RAND2()去实现RAND6() 

int sum = -1;
while (true) {
	sum = 4 * rand.nextInt(2) + 2 * rand.nextInt(2) + rand.nextInt(2);
	if (sum <= 5) {
		break;
	}
}
return sum;

19. Trapping Rain Water II  http://www.cnblogs.com/easonliu/p/4743644.html




20 报一个失败的面经：
GOOGLE SEATTLE 一共5轮

1.韩国人，很NICE，谈得挺开心，上来先BEHAVIORAL，然后问了一堆小问题，比如如何generate unique id,如何SCALE KEY-VALUE STORAGE.
Mac-Address with timestamp 
MessageDigest
2.好像是美国人，考了READ1024-CALL MULTIPLE TIMES ， 由于OJ上要收钱，没看，CODE写的一塌糊涂。

3.国人姐姐，很NICE，中间开始讲中文，考了CELEBRITY FINDING，CODE在引导之下写出WORST CASE O(N)，表现的也差强人意。 !!!

4.美国人带SHADOW，上来考了个CUP装水的问题，FOUNTAIN MACHINE有不同的optION对应不同出水量，出水量是个RANGE，CUP VOLUME也是RANGE，求可能的SEQUENCE，简单RECURSION搞定。
   http://www.careercup.com/question?id=15299689
   然后考了TWITTER FEED的设计，答得不好，后来网上找好像需要用到PUB/SUB的概念，但是还是没找到好的REFERENCE，希望有高人有LINK或者给我解释下。
https://www.quora.com/Software-Engineering-Best-Practices/What-are-the-best-practices-for-building-something-like-a-News-Feed
http://backchannel.org/blog/friendfeed-schemaless-mysql
5.美国人，考了POW和找H-INDEX，最后H-INDEX勉强写出O(N),发挥的还行吧。 !!!!


21. shuffle. 输入是[0,2,_,3] 输出是[0,_,2,3]. 就是一个乱序数组, 其中缺少了一个值, 然后输出, 每个数值都在自己对应的index上面.但是移动的时候, 只能把数字放在空缺的位置上, 要求移动的次数最少
Keep a HashMap of unmatched num and its index, track the index of  '_', If there is no unmatched num, return. Otherwise, if the '_' is in the position of missing num, then randomly select an unmatched num,
swap to the pos of '_'. If the '_' is not in the position of missing num, then just swap it with the number that need to be in the position. Do this until done.

22. Determine Binary Tree is complete tree.

23. random maze generator. 其实就是在一个二维空间画墙但不能允许有封闭空间

24. 题目大致是BACCBBAAA -> ABABACABC，就是输出相邻字母不能相同的string  
Greedy Algorithm, using max heap, deal with characters with most counts first

unordered_map<char, int> map;
string noSameNeighbor(string & s){
        string ans = "";
        for (char c : s)
                map[c]++;                
        
        auto comp = [](char char1, char char2){return map[char1] < map[char2]; };
        priority_queue <char, vector<char>, decltype(comp)> pq(comp);
        for (pair<char, int> p : map)
                pq.push(p.first);

        while (!pq.empty()){
                char c = pq.top();
                pq.pop();
                if (pq.empty()){
                        ans += c ;
                        break;
                }
                char d = pq.top();
                pq.pop();
                ans += c;
                ans += d;
                map[c]--;
                map[d]--;
                if (map[c] > 0) pq.push(c);
                if (map[d] > 0) pq.push(d);
        }
 
        return ans;
}


private static String helper(String s) {
                final Map<Character, Integer> map = new HashMap<Character, Integer>();
                char[] ss = s.toCharArray();
                for (int i = 0; i < ss.length; i++) {
                        if (map.containsKey(ss)) {
                                map.put(ss, map.get(ss) + 1);
                        } else {
                                map.put(ss, 1);
                        }
                }
                Queue<Character> queue = new PriorityQueue<Character>(1, new Comparator<Character>() {
                        @Override
                        public int compare(Character c1, Character c2) {
                                int num1 = map.get(c1);
                                int num2 = map.get(c2);
                                if (num1 > num2) {
                                        return -1;
                                } else if (num1 < num2) {
                                        return 1;
                                } else {
                                        return 0;
                                }
                        }
                });
                for (Map.Entry<Character, Integer> entry: map.entrySet()) {
                        queue.add(entry.getKey());
                }
                StringBuilder rs = new StringBuilder("");
                while (!queue.isEmpty()) {
                        Character c1 = queue.poll();
                        if (queue.isEmpty()) {
                                rs.append(c1);
                                break;
                        }
                        Character c2 = queue.poll();
                        rs.append(c1);
                        rs.append(c2);-google 1point3acres
                        if (map.get(c1) > 1) {
                                map.put(c1, map.get(c1) - 1);
                                queue.offer(c1);
                        }. 1point 3acres 璁哄潧
                        if (map.get(c2) > 1) {
                                map.put(c2, map.get(c2) - 1);
                                queue.offer(c2);
                                
                        }
                }
                return rs.toString();
            }

24. 欧拉回路？De Bruijn sequence


25. Two pointers
given sorted array of doubles, return the another sorted array of doubles where all elements are the squares from the input array， 然后optimize⼀一下到O(n)
Two pointers from both sides, moving towards middle

26. 输出任意permutation使得List中的相同element的间距要小于 minDistance。 (The same idea as No. 24, just change the distance from 1 to K)

27. 然后出了道题, 一个2d array of char, 'X'表示不能走, " "表示草地, 给一个初始x,y 然后把所有的草除掉并回到原点. 
要求的是,每走一步打印一个方向, 最后打印出所有的方向... 想到了是dfs, 
但是卡在往回走的过程了... 最后没写出全部的代码, 还被大叔嘲讽了.. 其实并不太难, dfs 可以解决. 主要是题目略长,然后时间比较不够了, 这种题套模板会比较快.
class Solution:
    def printPath(self, board, x, y):
        buf = [(x,y)]
        visited = set(buf)
        result = []
        dir = [(-1,0), (1,0), (0,-1), (0,1)]
        while buf:
            cur = buf[-1]
            flag = False
            for d in dir:
                n_x, n_y = cur[0]+d[0], cur[1]+d[1]
                if self.isValid(board, n_x, n_y) and not (n_x, n_y) in visited:
                    result.append(d)
                    buf.append((n_x, n_y))
                    visited.add((n_x, n_y))
                    flag = True
                    break
            if not flag:
                cur = buf.pop()
                if buf:
                    result.append((buf[-1][0]-cur[0], buf[-1][1]-cur[1]))

        return result

    def isValid(self, board, x, y):
        if x<0 or x>=len(board) or y<0 or y>=len(board[0]):
            return False
        if board[x][y]=='x':
            return False
        return True
sol = Solution()
result = sol.printPath(["x  x", "xx  ", "x   ", "    "], 0, 1)
print(result)




水校无实习背景，这波找工作还是很苦逼的，大部分公司连面试都不愿意给，能拿到google的机会还是挺高兴的。面试在youtube的一栋楼。
第一轮：
白人老太太，9年码工，题目是给一堆系统路径，类似“/abc/xyz/hij/f1”，找最长公共路径。
我说了longest common prefix的，她说怎么优化，于是说字典树，又问是不是要建完整的一颗树，于是说不用，发现要分叉了就标记一下跳出，
然后从根节点找到高度最低的分叉点就是公共路径，代码没写完，最后一段写了伪代码。
第二轮：
中国大哥，感觉非常亲切。题目是给一个function叫next()，返回的是多叉树的一条边，类似(m,n)，m是父节点n是子节点，让建立这棵树。
开始说先遍历一遍存成一个图，然后用拓扑排序的思路。然后在大量边的情况下优化，于是说用一个哈希表存节点指针，
对于一条边，先拿到父节点（不存在就建一个插进去），然后拿子节点（不存在就插进去），把这两个点连起来，并保存父节点关系。最后遍历哈希表找到根节点。代码写完有个小bug不过小哥说可以了。
午饭：
台湾小哥，各种聊
第三轮：
白人小哥，穿着紫色女巫的紧身镂空连衣裙带着法师帽就进来了。。。
题目是给一堆people，每人有skills，再给一堆tasks，每个有需要的skills，返回bool值是否所有任务都可完成。然后一通讨论corner case。follow up问people之间不能合作怎么做。这个代码没写完，中午有点懵，反应也慢了。时间一到被小哥打断拍照走人。
第四轮：
白人程序媛，leetcode原题，判断一个数旋转180度还是不是本身，数是string形式的。follow up就是给个N，然后返回所有长度小于等于N的满足旋转条件的字符串。写完代码后聊了两分钟结束。



 一个sorted array,找出现次数大于n/4的popular number，n是数组长度。写了O(n)之后要求优化时间复杂度，想优化成O(logn)不过要分成四份再找想的有点复杂
11 12 222 24 44 444 666 66
1 1 2 2
I would do it with similar approach as binary search.

At first I would find values of 8 numbers which would be on 0/8, 1/8, 2/8 ... 8/8 of array. 
The same numbers you find first when you binary search for something.

As the same number must be in n/4 of array size or higher, 
it must reach at least two of that boundaries in row. Like number at 2/8 and 3/8 is same.

Therefore this identification of number and partially position is done in constant time.

Then you just continue to find where it start and where it ends, which is typical binary search.

Complexity : O(log n)


Distributed Hash Table
Eventual Consistency vs Strong Consistency
Read Heavy vs Write Heavy
Consistent Hashing
Sticky Sessions
Structured Data(uses DynamoDB) vs Unstructured Data(uses S3)




public class CardGame{
    public static CardGame createCardGame(GameType type){
        if(type == GameType.Porker){
            return new PorkerGame();
        }
        if(type == GameType.BlackJack){
            return new BlackJack;
        }
        return nuklk
    }
}