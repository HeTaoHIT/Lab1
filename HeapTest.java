package lab1;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import lab1.Graph.Node;

import java.util.Scanner;
import java.util.Set;
import java.util.*;

/*
 * @(#)TextGraphProject --- HeapTest.java 
 */

/**
 * @author 何涛
 * @version 1st   on 2017年9月9日
 */
public class HeapTest {//comments B2
	public static TextGraph createDirectedGraph(String filename) {
		TextGraph tgp=new TextGraph();
		return tgp.createDirectedGraph(filename);
	}
	
	public  void showDirectedGraph(TextGraph G) throws IOException{//TextGraph G
		try {
			FileWriter fileWriter = new FileWriter(new File("GData.gv"));
			fileWriter .write("digraph g{\r\n");
			Set<String> nodes=G.getGph().getSet();
			for(String s:nodes){
				fileWriter .write(s+";\r\n");
			}
		    Map<String, LinkedList<Node>> m_map=G.getGph().getMap();
		    for(String s:m_map.keySet()){
		    	for(Node nd:m_map.get(s)){
		    		fileWriter .write(s+"->"+nd.value+"[label="+nd.Count+"];\r\n");
		    	}	
		    }
		    fileWriter .write("}");
		    fileWriter .close();
		} catch (FileNotFoundException e1) {
			System.out.println("文件有错");
		}
		    
		try {
			  String name="D:\\eclipse\\workspace\\lab1\\GData.gv";
			Process process = Runtime.getRuntime().exec( "dot "+name+" -T jpg -o D:\\graphviz-2.38\\workspace\\5.jpg");
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		} catch (IOException e) {
			System.out.println("错了");
		}
	}
	
	public static String queryBridgeWords(TextGraph G, String word1, String word2){
		return G.OuputBridge(word1, word2);
	}

	public static String generateNewText(TextGraph G, String inputText){
		return G.generateNewText(inputText);
	}

	public static String calcShortestPath(TextGraph G, String word1, String word2){
		if(word1.equals(word2)||word1.equals(""))	return null;
        else if(word2.equals("")){
			String str=new String();
			LinkedList<String> llst=G.ShortestPath(word1);
			for(String s:llst){
				if(s.charAt(0)!='?')	
					str+=s+"|";
				else str+="no path to "+s.substring(1)+"|";
			}
			return str;
		}
		else
	           return G.ShortestPath(word1, word2);
	}

	public static String randomWalk(TextGraph G){
		ArrayList<String> llst=G.randomWalk();
		String answer="";
		for(String str:llst)
			answer+=str+" ";
		return answer;
	}
	
	/*public static void main(String[] args) throws IOException{
		String filename="D:/eclipse/workspace/lab1/src/lab1/GraphData.txt";
		TextGraph tgp=createDirectedGraph(filename);
		
		Set<Entry<String,LinkedList<Graph.Node>>> set=tgp.getGph().getMap().entrySet();
		for(Entry<String,LinkedList<Graph.Node>> ety:set){
			System.out.print(ety.getKey()+": ");
			for(Graph.Node nd:ety.getValue())
				System.out.print("("+nd.value+","+nd.Count+")");
			System.out.println();
		}
		
		Scanner in=new Scanner(System.in);
		String src=in.next();String des=in.next();
		System.out.println(queryBridgeWords(tgp,src,des));
		
		in.nextLine();
		
		String newTxt=in.nextLine();
		System.out.println(generateNewText(tgp,newTxt));
		
		src=in.next();des=in.next();
		System.out.println(calcShortestPath(tgp,src,des));
		
		System.out.println(randomWalk(tgp));
		
	}*/
}
