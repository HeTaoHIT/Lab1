package lab1;

/*
 * @(#)TextGraphProject --- Pair.java 
 */

/**
 * @author 何涛
 * @version 1st   on 2017年9月10日
 */
public class Pair<T1,T2> {
	T1 first;
	T2 second;
	
	public Pair(){
		first=null;second=null;
	}
	
	public Pair(T1 f,T2 s){
		first=f;second=s;
	}
	
	public boolean equals(Pair<T1,T2> other){
		if(first==other.first&&second==other.second)	return true;
		return false;
	}
	
	public T1 getFirst(){
		return first;
	}
	
	public T2 getSecond(){
		return second;
	}
}//comments B1
