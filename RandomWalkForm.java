/**
 * 
 */
package lab1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author lenovo
 *
 */
public class RandomWalkForm extends JLabel{
	private String[] walkway;
	private int walkStep;
	
	private JTextArea resultPanel;
	private TextgraphForm tghform;
	private Graphpanel digraphpanel;

	private Interface  core;
	public RandomWalkForm(JTextArea resultPanel,TextgraphForm tghform,Graphpanel digraphpanel){
		this.resultPanel=resultPanel;
		this.tghform=tghform;
		this.digraphpanel=digraphpanel;
	}
	public void paintComponent(Graphics g) {
		JButton btnRandomWalk = new JButton("random walk");//随机游走
		 WalkListener walklistener=  new WalkListener() ;
		btnRandomWalk.addActionListener(walklistener);
		btnRandomWalk.setBounds(0, 0, 254, 29);
		this.add(btnRandomWalk);
		
		JButton btnContinue = new JButton("continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				walkStep++;
				if(walkStep<walkway.length){
					String s="";
					for(int i=0;i<walkStep;i++){
						s+=walkway[i]+":";
					}
					s+=walkway[walkStep];
					resultPanel.setText(s);
					try {
						showshortsestGraph(s, tghform.getTgh());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					digraphpanel.setimgLabel();
				}else{
					resultPanel.setText("随机游走结束");
				}
			}
		});
		btnContinue.setBounds(254,0,254, 29);
		this.add(btnContinue);
	}
	class WalkListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String s=core.randomWalk(tghform.getTgh());
			 walkway=s.split(" ");
			   walkStep=0;
			   //ActionListener listener=new TimeListener();
			}
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
			System.out.println("文件不存在");
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
			System.out.println("graviz使用失败");
		}
	}
}
