package lab1;

import java.util.*;


public class Graph {
	private Map<String, LinkedList<Node>> m_map;
	private Set<String> m_set;
	
	public Graph(){
		m_map=new HashMap<String,LinkedList<Node>>();
		m_set=new HashSet<String>();
	}
	
	public Map<String,LinkedList<Node>> getMap(){
		return m_map;
	}
	
	public Set<String> getSet(){
		return m_set;
	}
	
	public boolean isEmpty(){
		return m_set.size()==0;
	}
	
	public int size(){
		return m_set.size();
	}
	
	public void addEdge(String lhs,String rhs,int aCount){
		if(m_map.get(lhs)==null){
			LinkedList<Node> lst=new LinkedList<>();
			lst.addFirst(new Node(rhs,aCount));
			m_map.put(lhs, lst);m_set.add(lhs);m_set.add(rhs);
			return;
		}
		for(Node nd:m_map.get(lhs))
			if(nd.value.equals(rhs)){
				System.out.print("ThE edge "+lhs+" to "+rhs+" has occured, and the weight of this edge is "
			+nd.Count+",Do you want to cover it? Y/N:");
				Scanner in=new Scanner(System.in);
				if(in.next()=="Y")
					nd.Count=aCount;
				in.close();return;
			}
		m_map.get(lhs).add(new Node(rhs,aCount));m_set.add(rhs);
	}
	
	public void addEdge(String lhs,String rhs){
		if(m_map.get(lhs)==null){
			LinkedList<Node> lst=new LinkedList<>();
			lst.addFirst(new Node(rhs,1));
			m_map.put(lhs, lst);
			m_set.add(lhs);m_set.add(rhs);
			return;
		}
		LinkedList<Node> lst=m_map.get(lhs);
		for(Node nd:lst)
			if(nd.value.equals(rhs)){
				nd.Count++;return;
			}
		m_map.get(lhs).add(new Node(rhs,1));m_set.add(rhs);
	}
	
	public void remove(String str){
		Set<String> KeySet=m_map.keySet();
		for(String key:KeySet){
			if(key.equals(str))	{
				m_map.remove(key);continue;
			}
			for(Node nd:m_map.get(key))
				if(nd.value.equals(str)){
					m_map.get(key).remove(nd);continue;
				}
		}
	}
	
	public void remove(String lhs,String rhs){
		for(Node nd:m_map.get(lhs))
			if(nd.value.equals(rhs)){
				m_map.get(rhs).remove(nd);return;
			}
	}
	
	public LinkedList<String> dfs(){
		if(isEmpty())	{
			System.out.println("ø’Õº£°");return null;
		}
		Map<String,Boolean> visited=new HashMap<String,Boolean>();
		LinkedList<String> dfsList=new LinkedList<String>();
		for(String str:m_set)
			visited.put(str, false);
		for(String str:m_set)
			if(!visited.get(str)){
				LinkedList<String> llt=dfs(str,visited);
				dfsList.addAll(llt);
				for(String s:llt)
					visited.put(s, true);
			}
		return dfsList;
	}
	
	private LinkedList<String> dfs(String str,
			Map<String,Boolean>visited){
		LinkedList<String> bfsList=new LinkedList<String>();
		Stack<String> stk=new Stack<>();stk.push(str);
		String tempStr;
		while(!stk.empty()){
			tempStr=stk.peek();stk.pop();
			if(!visited.get(tempStr))	bfsList.add(tempStr);
			visited.put(tempStr, true);
			if(!m_map.containsKey(tempStr))	continue;
			for(Node nd:m_map.get(tempStr))
				if(!visited.get(nd.value))
					stk.push(nd.value);
		}
		return bfsList;
	}
	
	public LinkedList<String> bfs(){
		if(isEmpty())	{
			System.out.println("ø’Õº£°");return null;
		}
		Map<String,Boolean> visited=new HashMap<String,Boolean>();
		LinkedList<String> dfsList=new LinkedList<String>();
		for(String str:m_set)
			visited.put(str, false);
		for(String str:m_set)
			if(!visited.get(str)){
				LinkedList<String> llt=bfs(str,visited);
				dfsList.addAll(llt);
				for(String s:llt)
					visited.put(s, true);
			}
		return dfsList;
	}
	
	private LinkedList<String> bfs(String str,
			Map<String,Boolean> visited){
		Queue<String> que=new LinkedList<>();
		LinkedList<String> bfsList=new LinkedList<>();
		String tempStr;
		while(!que.isEmpty()){
			tempStr=que.poll();
			if(!visited.get(tempStr))	bfsList.add(tempStr);
			visited.put(tempStr, true);
			if(m_map.containsKey(tempStr))	continue;
			for(Node nd:m_map.get(tempStr))
				if(!visited.get(nd.value))
					que.add(nd.value);
		}
		return bfsList;
	}
	
	public Pair<LinkedList<String>,Integer> ShortestPath(String src,String des){
		if(!m_set.contains(src)){
			System.out.println("No \""+src+"\" exciting in the graph!");return null;
		}
		if(!m_set.contains(des)){
			System.out.println("No \""+des+"\" exciting in the graph!");return null;
		}
		LinkedList<String> lst=new LinkedList<>();
		Map<String,vertex> strToVtx=Dijkstra(src);
		vertex cur=strToVtx.get(des);int Dist=cur.dist;
		if(cur.prev==null){
			System.out.println("No path form \""+src+"\" to "+des+"\"");
			return null;
		}
		while(cur.prev!=null){
			lst.addFirst(cur.value);
			cur=cur.prev;
		}
		return new Pair<LinkedList<String>,Integer>(lst,Dist);
	}
	
	
	public Map<String,vertex> Dijkstra(String str){
		final int INFINITY=1000000;
		Map<String,vertex> strToVtx=new HashMap<String,vertex>();
		for(String s:m_set){
			vertex vtx=new vertex(s,false,INFINITY,null);
			strToVtx.put(s, vtx);
		}
		BinaryHeap<vertex> bhp=new BinaryHeap<vertex>();
		strToVtx.get(str).dist=0;bhp.insert(strToVtx.get(str));
		
		while(!bhp.isEmpty()){
			vertex v=bhp.deleteMin();
			if(v.known)	continue;
			v.known=true;
			if(m_map.containsKey(v.value))
				for(Node nd:m_map.get(v.value)){
					vertex tmp=strToVtx.get(nd.value);
					if(!tmp.known){
						int cost=nd.Count;
						if(v.dist+cost<tmp.dist){
							tmp.dist=v.dist+cost;tmp.prev=v;
							bhp.insert(tmp);
						}
					}
				}	
		}
		
		return strToVtx;
	}
	
	public Map<String,LinkedList<String>> getSubNode(Map<String,vertex> map){
		Map<String,LinkedList<String>> TreeNode=new HashMap<String,LinkedList<String>>();
		for(String str:m_map.keySet()){
			LinkedList<String> childs=new LinkedList<>();
			for(Node nd:m_map.get(str))
				if(map.get(str).dist+nd.Count==map.get(nd.value).dist)
					childs.add(nd.value);
			TreeNode.put(str, childs);
		}
		return TreeNode;
	}
	
	public Pair<LinkedList<LinkedList<String>>,Integer> getAllCloest(String src,String des){
		Map<String,vertex> map=Dijkstra(src);
		int dist=map.get(des).dist;
		LinkedList<LinkedList<String>> allPath=new LinkedList<LinkedList<String>>();
		Map<String,LinkedList<String>> TreeNode=getSubNode(map);
		Stack<LinkedList<String>> stk=new Stack<>();LinkedList<String> path=new LinkedList<>();
		LinkedList<String> temp=new LinkedList<>();temp.add(src);stk.add(temp);
		while(!stk.isEmpty()){
		LinkedList<String> fathers=stk.pop();
		if(fathers.size()!=0){
		String father=fathers.getFirst();fathers.removeFirst();stk.add(fathers);
		path.add(father);
		if(father.equals(des)){
		allPath.add((LinkedList<String>) path.clone());path.removeLast();
		}
		//else if(m_map.keySet().contains(father)){
		else if(TreeNode.keySet().contains(father)&&TreeNode.get(father).size()!=0){
		stk.add( (LinkedList<String>) (TreeNode.get(father)).clone());
		}
		else{
		path.removeLast();
		if(stk.peek().size()==0){
		stk.pop();path.removeLast();
		}
		}
		}
		else if(path.size()!=0)
		path.removeLast();
		}

		return new Pair(allPath,dist);

		}

	
	class vertex implements Comparable<vertex>{
		String value;
		boolean known;
		int dist;
		vertex prev;
		
		vertex(){
			value=null;dist=1000000;
			known=false;prev=null;
		}
		
		vertex(String avalue,boolean aknown,int adist,vertex aprev){
			value=avalue;known=aknown;dist=adist;prev=aprev;
		}
		
		@Override
		public int compareTo(vertex vtx) {
			if(dist<vtx.dist)	return -1;
			else if(dist>vtx.dist) return 1;
			return 0;
		}
	}
	
	class Node implements Comparable<Node>{
		String value;
		int Count;
		
		Node(String aValue,int aCount){
			value=aValue;Count=aCount;
		}

		public int compareTo(Node other) {
			if(Count>other.Count)	return 1;
			else if(Count<other.Count)	return -1;
			return 0;
		}
	}
	
}//comments
