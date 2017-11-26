/**
 * 
 */
package lab1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * @author lenovo
 *
 */
public class NewTextForm extends JLabel{
	private JTextArea resultPanel;
	private TextgraphForm tghform;
	
	private Interface  core;
	public NewTextForm(JTextArea resultPanel,TextgraphForm tghform){
		this.resultPanel=resultPanel;
		this.tghform=tghform;
	}
   
   public void paintComponent(Graphics g) {
	JLabel label_1 = new JLabel("\u63D2\u5165\u6865\u63A5\u8BCD\uFF1A");
	label_1.setForeground(Color.BLUE);
	label_1.setFont(new Font("锟斤拷微锟斤拷锟斤拷暖色锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", Font.PLAIN, 25));
	label_1.setBounds(0, 0, 153, 43);
	this.add(label_1);
	
	JScrollPane scrollPane_2 = new JScrollPane();
	scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane_2.setBounds(159, 0, 268, 98);
	this.add(scrollPane_2);
	
	JTextArea textArea_2 = new JTextArea();
	textArea_2.setFont(new Font("Arial Black", Font.PLAIN, 20));
	textArea_2.setLineWrap(true);
	scrollPane_2.setViewportView(textArea_2);
	
	JButton btnInsert = new JButton("\u63D2\u5165");//插入桥接词
	btnInsert.setFont(new Font("锟斤拷锟斤拷", Font.PLAIN, 18));
	btnInsert.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			resultPanel.setText(core.generateNewText(tghform.getTgh(),textArea_2.getText()));
		}
	});
	btnInsert.setBounds(426, 0, 77, 97);
	this.add(btnInsert);
   }
}
