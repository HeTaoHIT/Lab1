/*
 * @(#)Lab6 --- Interfacetest.java 
 */
package com.seeucode.lab6;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author 何涛
 * @version 1st   on 2017年11月10日
 */
public class Interfacetest {
	final static String filename = "F:/java/workspace/Lab6/src/GraphData.txt";
	@Test
	public void test1() {
		TextGraph tgh = Interface.createDirectedGraph(filename);
		
		String paths = "to:explore:new!2|to:explore:new:worlds!3"
				+ "|to:explore:new:key:seek:out!6"
				+ "|to:explore:new:key!4|to:life!1"
				+ "|to:explore!1|to:explore:new:key:seek:out:and!7"
				+ "|to:explore:new:civil!3|to:explore:new:key:seek!5|";
		String[] path1 = paths.split("\\|");
		Set set1 = new HashSet();
		for(String node: path1)
			set1.add(node);
		
		String answer = Interface.calcShortestPath(tgh, "to", null);
		System.out.println(answer);
		String[] answers = answer.split("\\|");
		Set set2 = new HashSet();
		for(String path: answers)
			set2.add(path);
		
		assertEquals(set1,set2);
	}
	
	@Test
	public void test2(){
		TextGraph tgh = Interface.createDirectedGraph(filename);
		
		String word1 = "seek"; String word2 = "to";
		String path = Interface.calcShortestPath(tgh, word1, word2);
		assertEquals(path,"seek:out:and:new:key:to!|6");
	}
	
	@Test
	public void test3(){
		TextGraph tgh = Interface.createDirectedGraph(filename);
		
		String word1 = null; String word2 = "to";
		String path = Interface.calcShortestPath(tgh, word1, word2);
		assertEquals(path,null);
	}
	
	@Test
	public void test4(){
		TextGraph tgh = Interface.createDirectedGraph(filename);
		String word1 = "adu";String word2 = "ein";
		String path = Interface.calcShortestPath(tgh, word1, word2);
		assertEquals(path,"!No \"adu\" existing in the graph!");
	}
	
	@Test
	public void test5(){
		TextGraph tgh = Interface.createDirectedGraph(filename);
		String word1 = "seek"; String word2 = "adoi";
		String path = Interface.calcShortestPath(tgh, word1, word2);
		assertEquals(path,"!No \"adoi\" existing in the graph!");
	}
	
	@Test
	public void test6(){
		TextGraph tgh = Interface.createDirectedGraph(filename);
		String word1 = "civil"; String word2 = "to";
		String path = Interface.calcShortestPath(tgh, word1, word2);
		assertEquals(path,"No path form \"civil\" to \"to\"");
	}
	
	@Test
	public void test7(){
		TextGraph tgh = Interface.createDirectedGraph(filename);
		String word1 = "to"; String word2 = "new";
		String path = Interface.calcShortestPath(tgh, word1, word2);
		System.out.println(path);
		assertEquals(path,"to:explore:new!to:life:new!|2");
	}
}








