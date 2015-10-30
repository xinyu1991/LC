package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizeHashTable {
	
	private Map<Integer, Integer> map1; // key + value
	private Map<Integer, Integer> map2; // key + index
	private List<Integer> list; // key
	
	public RandomizeHashTable() {
		map1 = new HashMap<Integer, Integer>();
		map2 = new HashMap<Integer, Integer>();
		list = new ArrayList<Integer>();
	}
	
	public Integer get(int key) {
		return map1.get(key);
	}
	
	public void set(int key, int value) {
		if (map1.containsKey(key)) {
			map1.put(key, value);
		} else {
			map1.put(key, value);
			list.add(key);
			map2.put(key, list.size() - 1);
		}
	}
	
	public void delete(int key) {
		map1.remove(key);
		swap(list, map2.get(key), list.size() - 1);
		list.remove(list.size() - 1);
		map2.remove(key);
	}
	
	public Integer getRandom() {
		Random rand = new Random();
		int index = rand.nextInt(list.size());
		return map1.get(list.get(index));
	}
	
	private void swap(List<Integer> list, int x, int y) {
		int temp = list.get(x);
		list.set(x,  list.get(y));
		list.set(y, temp);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
