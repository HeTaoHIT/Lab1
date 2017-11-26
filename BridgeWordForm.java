/**
 * 
 */
package lab1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author lenovo
 *
 */
public class BridgeWordForm extends JPanel{
	private JTextField firstword;
	private JTextField secondword;
	private JTextArea resultPanel;
	private TextgraphForm tghform;
	
	private Interface  core;
   public BridgeWordForm( JTextField firstword, JTextField secondword,JTextArea resultPanel,TextgraphForm tghform){
		this.firstword=firstword;
		this.secondword=secondword;
		this.resultPanel=resultPanel;
		this.tghform=tghform;
	}
   
   public void paintComponent(Graphics g) {
	   JButton btnSearch = new JButton("\u6865\u63A5\u8BCD");//查询桥接词按钮
		btnSearch.setForeground(Color.RED);
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				core=new Interface();
				resultPanel.setText(core.queryBridgeWords(tghform.getTgh(),firstword.getText(), secondword.getText()));
			}
		});
		 btnSearch.setBounds(0, 0, 136, 31);
		 this.add(btnSearch);
   }
}
