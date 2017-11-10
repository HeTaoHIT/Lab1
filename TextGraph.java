/*
 * @(#)Lab6 --- TextGraph.java 
 */
package com.seeucode.lab6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * @(#)TextGraphProject --- TextProject.java 
 */

/**
 * @author 何涛
 * @version 1st on 2017年9月10日
 */
public class TextGraph {
	private Graph gph;

	public TextGraph() {
		gph = new Graph();
	}

	public TextGraph createDirectedGraph(String filename) {
		File src = new File(filename);
		BufferedReader fin = null;
		try {
			fin = new BufferedReader(new FileReader(src));
		} catch (FileNotFoundException e1) {
			System.out.println("打开文件失败");
			e1.printStackTrace();
		}

		String data = "";
		String line = null;
		try {
			while ((line = fin.readLine()) != null) {
				data = data + line + " ";
			}
		} catch (IOException e) {
			System.out.println("读文件失败");
			e.printStackTrace();
		}
		for (int i = 0; i < data.length(); i++) {
			boolean flag1 = data.charAt(i) >= 'a' && data.charAt(i) <= 'z';
			boolean flag2 = data.charAt(i) <= 'Z' && data.charAt(i) >= 'A';
			if (!(flag1 || flag2)) {
				data = data.substring(0, i) + " " + data.substring(i + 1);
			}
		}

		String[] curfile = data.split(" ");
		LinkedList<String> file = new LinkedList<>();
		for (int i = 0; i < curfile.length; i++) {
			if (!curfile[i].equals("")) {
				file.add(curfile[i].toLowerCase());
			}
		}

		try {
			if(fin != null){
				fin.close();
			}
		} catch (IOException e) {
			System.out.println("关闭流失败");
			e.printStackTrace();
		}

		for (int i = 0; i < file.size() - 1; i++) {
			addEdge(file.get(i), file.get(i + 1));
		}

		return this;
	}

	public void addEdge(String src, String des) {
		gph.addEdge(src, des);
	}

	public void addEdge(String src, String des, int weight) {
		gph.addEdge(src, des, weight);
	}

	public Graph getGph() {
		return gph;
	}

	public LinkedList<String> ShortestPath(String src) {
		LinkedList<String> llst = new LinkedList<>();
		if (!gph.getSet().contains(src)) {
			return null;
		}
		// by xutao
		String stri = "";
		LinkedList<String> lst = new LinkedList<String>();
		Map<String, Graph.vertex> strToVtx = gph.Dijkstra(src);
		for (String s : gph.getSet()) {
			if (s.equalsIgnoreCase(src))
				continue;
			stri = "";
			lst.clear();
			if (!s.equals(src) && strToVtx.get(s).prev != null) {
				Graph.vertex v = strToVtx.get(s);
				int count = v.dist;
				while (v.prev != null) {
					lst.addFirst(v.value);
					v = v.prev;
				}
				stri += src;
				for (String str : lst) {
					stri += ":" + str;
				}
				stri += "!" + count;
			} else if (strToVtx.get(s).prev == null) {
				stri += "?" + s;
			}
			llst.add(stri);
		}
		return llst;
	}

	public String ShortestPath(String src, String des) {
		if (!gph.getSet().contains(src)) {
			return new String("!No \"" + src + "\" existing in the graph!");
		}
		if (!gph.getSet().contains(des)) {
			return new String("!No \"" + des + "\" existing in the graph!");
		}

		Pair<LinkedList<LinkedList<String>>, Integer> lst = gph.getAllCloest(src, des);
		if(lst == null)	return "No path form \"" + src + "\" to " +  "\"" + des + "\"";
		int dist = lst.second;
		// return new Pair<LinkedList<String>,Integer>(lst,Dist);
		String answer = "";

		// by xutao
		String oneAnswer = "";
		//
		for (LinkedList<String> llst : lst.first) {
			// String oneAnswer=new String(src);
			oneAnswer = src;
			for (int i = 1; i < llst.size(); i++) {
				oneAnswer += ":" + llst.get(i);
			}
			answer += oneAnswer + "!";
		}
		answer += "|" + dist;
		return answer;
	}

	public ArrayList<String> FindBridge(String src, String des) {
		if (!gph.getSet().contains(src)) {
			return null;
		}
		if (!gph.getSet().contains(des)) {
			return null;
		}
		Set<String> desSet = new HashSet<String>();
		for (String s : gph.getMap().keySet()) {
			LinkedList<Graph.Node> lst = gph.getMap().get(s);
			for (Graph.Node nd : lst) {
				if (nd.value.equals(des)) {
					desSet.add(s);
					break;
				}
			}
		}
		Set<String> srcSet = new HashSet<>();
		for (Graph.Node nd : gph.getMap().get(src)) {
			srcSet.add(nd.value);
		}

		Set<String> birdge = new HashSet<>();
		birdge.addAll(srcSet);
		birdge.retainAll(desSet);
		if (birdge.isEmpty()) {
			return null;
		}
		ArrayList<String> lst = new ArrayList<>();
		for (String str : birdge) {
			lst.add(str);
		}

		return lst;
		/*
		 * if(lst.length==1)
		 * System.out.println("The bridge word from "+src+" to "+des+" is: "+lst
		 * [0]); else{
		 * System.out.print("The bridge words form "+src+" to "+des+" are: ");
		 * for(int i=0;i<lst.length-1;i++) System.out.print(lst[i]+",");
		 * System.out.println("and "+lst[lst.length-1]); }
		 */
	}

	public String OuputBridge(String src, String des) {
		if (src.equals("")) {
			// return new String("请输入first单词！");
			return "请输入first单词！";
		}
		if (des.equals("")) {
			// return new String("请输入second单词！");
			return "请输入second单词！";
		}
		if (!gph.getSet().contains(src)) {
			return new String("No " + src + " in the graph!");
		}
		if (!gph.getSet().contains(des)) {
			return new String("No " + des + " in the graph!");
		}
		ArrayList<String> lst = FindBridge(src, des);
		if (lst == null) {
			return new String("No bridge words from \"" + src + "\" to \"" + des + "\"!");
		}
		if (lst.size() == 1) {
			return new String("The bridge word from \"" + src + "\" to \"" + des + "\" is: \"" + lst.get(0) + "\"");
		}

		String answer = new String("The bridge words form \"" + src + "\" to \"" + des + "\" are: ");
		for (int i = 0; i < lst.size() - 1; i++) {
			answer += "\"" + lst.get(i) + "\",";
		}
		answer += "and \"" + lst.get(lst.size() - 1) + "\"";
		return answer;
	}

	public String generateNewText(String txt) {
		for (int i = 0; i < txt.length(); i++) {
			boolean flag1 = txt.charAt(i) >= 'a' && txt.charAt(i) <= 'z';
			boolean flag2 = txt.charAt(i) >= 'A' && txt.charAt(i) <= 'Z';
			if (!(flag1 || flag2)) {
				txt = txt.substring(0, i) + " " + txt.substring(i + 1);
			}
		}

		String[] data = txt.split(" ");
		LinkedList<String> file = new LinkedList<>();
		for (int i = 0; i < data.length; i++) {
			if (!data[i].equals("")) {
				file.add(data[i].toLowerCase());
			}
		}

		LinkedList<String> bridges = BuildNewTxt(file);
		String newTxt = "";
		for (String str : bridges) {
			newTxt += str + " ";
		}
		return newTxt;
	}

	private LinkedList<String> BuildNewTxt(LinkedList<String> txt) {
		ArrayList<String> strArr = new ArrayList<>();
		LinkedList<String> llst = new LinkedList<>();
		for (String str : txt) {
			llst.add(str);
		}

		// by xutao
		Random random = new Random();
		//
		for (int i = 0, times = 0; i < txt.size() - 1; i++) {
			strArr = FindBridge(txt.get(i), txt.get(i + 1));
			if (strArr == null) {
				continue;// return (String[])txt.toArray();
			}
			if (strArr.size() == 1) {
				llst.add(i + 1 + times++, strArr.get(0));
			} else {
				// Random random=new Random();
				int no = random.nextInt(strArr.size() - 1);
				llst.add(i + 1 + times++, strArr.get(no));
			}
		}
		return llst;
	}

	public ArrayList<String> randomWalk() {
		if (gph.size() == 0) {
			System.out.println("Empty Graph!");
			return null;
		}

		LinkedList<String> pointStr = new LinkedList<>();
		for (String str : gph.getSet()) {
			pointStr.add(str);
		}

		Random random = new Random();

		Set<Pair<String, String>> history = new HashSet<Pair<String, String>>();

		String start = pointStr.get(random.nextInt(pointStr.size()));
		ArrayList<String> alst = new ArrayList<>();
		alst.add(start);

		while (gph.getMap().containsKey(start)) {
			LinkedList<Graph.Node> lst = gph.getMap().get(start);
			String end = lst.get(random.nextInt(lst.size())).value;
			alst.add(end);
			if (isContains(history, start, end))
				break;
			history.add(new Pair<String, String>(start, end));
			start = end;
		}
		return alst;
	}

	private boolean isContains(Set<Pair<String, String>> set, String src, String des) {
		for (Pair<String, String> pi : set) {
			if (pi.first.equals(src) && pi.second.equals(des)) {
				return true;
			}
		}
		return false;
	}

}

