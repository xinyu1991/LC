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
