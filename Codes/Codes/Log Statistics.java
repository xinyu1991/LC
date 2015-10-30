class LogEntry {
	public String candidate;
	public int time;

}

class ResultNode implements Comparable {
	public String candidate;
	public int stat;

	public ResultNode(String candidate, int stat) {
		this.candidate = candidate;
		this.stat = stat;
	}

	public int compareTo(Object obj) {
		ResultNode other = (ResultNode) obj;
		return this.stat - other.stat;
	}
}

// time: O(n)
// space: O(n)
public String findWinner(int time, ArrayList<LogEntry> logs) {
	if (logs == null) {
		return null;
	}

	Map<String, List<Integer>> map = buildMap(logs);	

	int maxTickets = 0;
	String winner = null;
	Set<String> set = map.keySet();
	for (String name : set) {
		List<Integer> stat = map.get(name);
		int num = 0;
		for (int n : stat) {
			if (n <= time) {
				num++;
			}
		}
		if (num > maxTickets) {
			maxTickets = num;
			winner = name;
		}
	}

	return winner;
}

// follow up
public ArrayList<String> findFirstKWinner(int time, int k, ArrayList<LogEntry> logs) {
	List<String> result = new ArrayList<String>();
	if (logs == null) {
		return result;
	}

	Map<String, List<Integer>> map = buildMap(logs);

	if (k >= map.size()) {
		result.addAll(map.keySet());
		return result;
	}

	// get an array of all candidates with tickets number
	ResultNode[] temp = new ResultNode[map.size()];
	Set<String> candidates = map.keySet();
	int i = 0;
	for (String name : candidates) {
		List<Integer> stat = map.get(name);
		int num = 0;
		for (int n : stat) {
			if (n <= time) {
				num++;
			}
		}
		temp[i++] = new ResultNode(name, num);
	}

	// using quick select to get the candidate with kth largest tickets
	quickSelect(temp, k);
	for (int j = 0; j < k; j++) {
		result.add(temp[j].candidate);
	}

	return result;

}

// helper function: establish map
private Map<String, List<Integer>> buildMap<ArrayList<LogEntry> logs) {
	Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
	for (LogEntry entry : logs) {
		List<Integer> stat = map.get(entry.candidate);
		if (stat == null) {
			stat = new ArrayList<Integer>();
		}
		stat.add(entry.time);
		map.put(entry.candidate, stat);
	}

	return map;
}

private ResultNode quickSelect(ResultNode[] a, int k) {
	int left = 0;
	int right = a.length - 1;
	Random rand = new Random();
	while (left <= right) {
		int pivot = partition(a, left, right, rand.nextInt(right - left + 1) + left);
		if (pivot == k) {
			return a[pivot];
		} else if (pivot > k) {
			right = pivot - 1;
		} else {
			left = pivot + 1;
		}
	}
	return null;
}

private int partition(ResultNode a, int left, int right, int pivot) {
	ResultNode pivotValue = a[pivot];
	swap(a, pivot, right);
	int storeIndex = left;
	for (int i = left; i < right; i++) {
		if (a[i] > pivotValue) {
			swap(a, i, storeIndex++);
		}
	}
	swap(a, right, storeIndex);
	return storeIndex;
}

private void swap(ResultNode[] a, int x, int y) {
	ResultNode temp = a[x];
	a[x] = a[y]
	a[y] = temp;
}