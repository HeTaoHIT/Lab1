/**
 * 
 */
package lab1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * @author lenovo
 *
 */
public class ShortestpathForm extends JPanel{
	private JTextField firstword;
	private JTextField secondword;
	private JTextArea resultPanel;
	private TextgraphForm tghform;
	private Graphpanel digraphpanel;

	private Interface  core;
	   public ShortestpathForm( JTextField firstword, JTextField secondword,JTextArea resultPanel,TextgraphForm tghform,Graphpanel digraphpanel){
			this.firstword=firstword;
			this.secondword=secondword;
			this.resultPanel=resultPanel;
			this.tghform=tghform;
			this.digraphpanel=digraphpanel;
		}
	   
	   public void paintComponent(Graphics g) {
	       JComboBox comboBox = new JComboBox();
		   comboBox.addItemListener(new ItemListener(){
		     public void itemStateChanged(ItemEvent e){
		    	 if(e.getStateChange()==e.SELECTED){
				System.out.println((String)comboBox.getSelectedItem());
				String shp=core.calcShortestPath(tghform.getTgh(),firstword.getText(),(String)comboBox.getSelectedItem());
				resultPanel.setText(shp.replace(":","->").replace("|","  distance:"));
				try {
					showshortsestGraph(shp, tghform.getTgh());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		    	 digraphpanel.setimgLabel();
		     }
		});
		comboBox.setBounds(300, 200, 131, 29);
		comboBox.setVisible(false);
		this.add(comboBox);
		
	 JButton button = new JButton("\u6700\u77ED\u8DEF\u5F84");//查询最短路径的按钮
	 button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		   comboBox.setVisible(false);
			String shp=core.calcShortestPath(tghform.getTgh(),firstword.getText(), secondword.getText());
			
			if(shp==null){
				if(secondword.getText().equals("")){
					resultPanel.setText("请输入第二个单词");
				}else if(firstword.getText().equals("")){
					resultPanel.setText("请输入第一个单词");
				}else{
					resultPanel.setText("self-loop");
				}
			}
			else if(shp.substring(0,1).equals("!")){
				resultPanel.setText(shp.substring(1,shp.length()));
			}else{
				 if(secondword.getText().equals("")){
					// textArea_1.setText(shp.replace(":","->").replace("!","锟斤拷distance:").replace("|", "\r\n"));
					//锟斤拷锟斤拷选锟斤拷锟斤拷锟斤拷锟斤拷锟�
					 comboBox.removeAllItems();
					 
				   	 String[] ways=shp.split("\\|");
						for(String s:ways){
							System.out.println(s);
							String[] parts=s.split("!");
							String[] nodes=parts[0].split(":");
							comboBox.addItem(nodes[nodes.length-1]);
						}
						comboBox.setVisible(true);
				 }
				 else{
					 resultPanel.setText(shp.replace(":","->").replace("|","  distance:").replace("!","\r\n"));
				try {
					 String[] allpath=shp.split("!");
					 String[] dis=shp.split("|");
					showshortsestGraph(allpath[0]+"|"+dis[1], tghform.getTgh());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				digraphpanel.setimgLabel();
				 }
				
			}
		}
	});
	button.setForeground(Color.BLUE);
	button.setBackground(Color.LIGHT_GRAY);
	button.setBounds(0, 0, 130, 31);
	this.add(button);
 }
	   
	   public  void showshortsestGraph(String info,TextGraph G) throws IOException{//
			try {
				FileWriter fileWriter = new FileWriter(new File("GData.gv"));
				fileWriter .write("digraph g{\r\n");
				
				String[] parts=info.split("\\|");
			   String[] rednodes=parts[0].split(":");
			   
			   /*for(String s:rednodes ){
				   System.out.println(s);
			   }*/
			 
			    Set<String> nodes=G.getGph().getSet();
				for(String s:nodes){
					fileWriter .write(s+";\r\n");
				}
				AdjacencyList m_map=G.getGph().getMap();
			    Boolean flag=false;
			    for(String s:m_map.keySet()){
			    	for(Node nd:m_map.get(s)){
			    		//锟叫讹拷锟角凤拷锟斤拷锟斤拷锟铰凤拷锟斤拷械锟揭伙拷锟斤拷锟�
			    		flag=false;
			    		 for(int i=0;i<rednodes.length-1;i++){
			    			 if(rednodes[i].equals(s)&&rednodes[i+1].equals(nd.value)){
			    				fileWriter. write(s+"->"+nd.value+"[label="+nd.Count+",color=red];\r\n");
			    				flag=true;
			    			 }
			    		 }
			    		 if(flag==false){
			    	   			fileWriter .write(s+"->"+nd.value+"[label="+nd.Count+"];\r\n");
			    		 }
			    	}	
			    }
			    fileWriter .write("}");
			    fileWriter .close();
			} catch (FileNotFoundException e1) {
				System.out.println("");
			}
			    
			try {
				Process process = Runtime.getRuntime().exec("dot D:\\eclipse\\workspace\\lab1\\GData.gv -T jpg -o D:\\graphviz-2.38\\workspace\\5.jpg");
				try {
					process.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("");
			}
		}
}