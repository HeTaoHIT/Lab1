/*
 * @(#)Lab6 --- AdjacencyList.java 
 */
package lab1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 * @author 何涛
 * @version 1st   on 2017年11月25日
 */
public class AdjacencyList {
	private Map<String, LinkedList<Node>> m_map;
	
	public AdjacencyList(){
		m_map = new HashMap<String, LinkedList<Node>>();
	}
	
	public void put(String src, LinkedList<Node> lst){
		m_map.put(src, lst);
	}
	
	public LinkedList<Node> get(String src){
		return m_map.get(src);
	}
	
	public void remove(String str){
		m_map.remove(str);
	}
	
	public Set<String> keySet(){
		return m_map.keySet();
	}
	
	public boolean containsKey(String key){
		return m_map.containsKey(key);
	}
}
