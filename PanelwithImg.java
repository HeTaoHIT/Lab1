package lab1;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelwithImg extends JPanel {
	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Image image;
	//private String  pos;
	private JLabel label;
	public PanelwithImg(){
		super();
		label=new JLabel("sdsadasd");
		this.add(label);
	}
	public PanelwithImg(String s){
		super();
		label=new JLabel();
		this.add(label);
		changeimage(s);
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		//Graphics2D g2 = (Graphics2D) g;
		//JLabel label=new JLabel();
		//label.setIcon(new ImageIcon(pos));
		//add(label);
		//label.setBounds(0,0,getSize().width,getSize().height);
		//g2.clearRect(0, 0,getSize().width,getSize().height);
		//g2.drawImage(image,0,0,getSize().width,getSize().height,null);
		}

	public void changeimage(String s){ 
		    Icon icon;
			try {
		      icon=new ImageIcon(ImageIO.read(new File(s)));
		      label.setIcon(icon);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(s);
		   //pos=s;
		  // removeAll();
			repaint();
	}
}
