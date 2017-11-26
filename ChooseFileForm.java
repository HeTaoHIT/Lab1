/**
 * 
 */
package lab1;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author lenovo
 *
 */
public class ChooseFileForm extends JPanel{
	
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	
	public ChooseFileForm(){
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		textField = new JTextField();//文件名文本框
		textField.setFont(new Font("Arial", Font.BOLD, 18));
		textField.setBounds(0, 0, 247, 27);
		this.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();//文件路径文本框
	    textField_1.setFont(new Font("Arial", Font.BOLD, 18));
	    textField_1.setColumns(10);
	    textField_1.setBounds(0, 35, 247, 27);
		this.add(textField_1);
		
		 btnNewButton = new JButton("browse");//本地文件浏览按钮
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser =new JFileChooser();
			    chooser.setCurrentDirectory(new File("."));
			    chooser.setSelectedFile(new File("MyText.txt"));
			     int result =chooser.showOpenDialog(null);
			     String filePath=chooser.getSelectedFile().getPath();
			     String filename=chooser.getSelectedFile().getName();
			     String[] strings=filePath.split("\\\\");//锟斤拷
			    	 String newstring="";
			    	for(String s:strings){
			    		   newstring=newstring+s+"/";
			    	}
			    	 newstring=newstring.substring(0,newstring.length()-1);
			     
			   textField_1 .setText(filePath);
			    // textField_1 .setText(newstring);
			     textField.setText(filename);
			}
		});
		btnNewButton.setBounds(250, 0, 120, 30);
		this.add(btnNewButton);
	}
	public String getpath(){
		return  textField_1.getText();
	}
}
