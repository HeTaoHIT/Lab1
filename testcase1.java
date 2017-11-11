/**
 * 
 */
package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author lenovo
 *
 */
public class testcase1 {

	/**
	 * Test method for {@link lab1.HeapTest#queryBridgeWords(lab1.TextGraph, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testQueryBridgeWords1() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No bridge words from \"explore\" to \"life\"!", HeapTest.queryBridgeWords(tgh, "explore", "life"));
	}
@Test
	public void testQueryBridgeWords2() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("«Î ‰»Îsecondµ•¥ £°", HeapTest.queryBridgeWords(tgh, "new", ""));
	}
@Test
	public void testQueryBridgeWords3() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No hello in the graph!", HeapTest.queryBridgeWords(tgh, "hello", "new"));
	}
public void testQueryBridgeWords4() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No hello in the graph!", HeapTest.queryBridgeWords(tgh, "new", "hello"));
	}
@Test
	public void testQueryBridgeWords5() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No bridge words from \"civil\" to \"home\"!", HeapTest.queryBridgeWords(tgh, "civil", "home"));
	}
@Test
	public void testQueryBridgeWords6() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("The bridge word from \"civil\" to \"my\" is: \"izations\"", HeapTest.queryBridgeWords(tgh, "civil", "my"));
}
@Test
	public void testQueryBridgeWords7() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("The bridge word from \"my\" to \"horse\" is: \"home\"", HeapTest.queryBridgeWords(tgh, "my", "horse"));
	}
@Test
	public void testQueryBridgeWords8() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No bridge words from \"horse\" to \"civilization\"!", HeapTest.queryBridgeWords(tgh, "horse", "civilization"));
	}

@Test
	public void testQueryBridgeWords9() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No bridge words from \"new\" to \"my\"!", HeapTest.queryBridgeWords(tgh, "new", "my"));
	}

@Test
	public void testQueryBridgeWords10() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No bridge words from \"worlds\" to \"new\"!", HeapTest.queryBridgeWords(tgh, "worlds", "new"));
	}
@Test
	public void testQueryBridgeWords11() {
		TextGraph tgh=HeapTest.createDirectedGraph("D:/eclipse/workspace/lab1/src/lab1/GraphData.txt");
		 assertEquals("No bridge words from \"explore\" to \"life\"!", HeapTest.queryBridgeWords(tgh, "explore", "life"));
	}

}
