/**
 * 
 */
package lab1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author lenovo
 *
 */
public class TextgraphForm extends JPanel{
	private TextGraph tgh;
	private Interface  core;
	
	private ChooseFileForm filepanel;
	private Graphpanel digraphpanel;
	
	public TextgraphForm(ChooseFileForm filepanel,Graphpanel digraphpanel){
		this.filepanel=filepanel;
		this.digraphpanel=digraphpanel;
	}
	public void paintComponent(Graphics g) {
		JButton btnBuild = new JButton("build");//图像生成按钮
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				core=new Interface();
				tgh=core.createDirectedGraph(filepanel.getpath());
				try {
					core.showDirectedGraph(tgh);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("hahaha");
				}
				digraphpanel.setimgLabel();
				//digraphpanel.changeimage("D:/graphviz-2.38/workspace/5.jpg");
				//btnSave.setVisible(true);
			}
		});
		btnBuild.setBounds(0,0,120,30);
		this.add(btnBuild);
	}
	public TextGraph getTgh(){
		return tgh;
	}
}
