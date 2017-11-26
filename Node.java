/*
 * @(#)Lab6 --- Node.java 
 */
package lab1;

/**
 * @author 何涛
 * @version 1st   on 2017年11月25日
 */
class Node implements Comparable<Node> {
	String value;
	int Count;

	Node(String aValue, int aCount) {
		value = aValue;
		Count = aCount;
	}

	public int compareTo(Node other) {
		if (Count > other.Count)
			return 1;
		else if (Count < other.Count)
			return -1;
		return 0;
	}
}
