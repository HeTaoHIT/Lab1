/**
 * 
 */
package lab1;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author lenovo
 *
 */
public class Graphpanel extends JPanel{
	private JLabel imgabel;
	public Graphpanel(){
		
	}
	public void paintComponent(Graphics g) {
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 519, 597);
		this.add(scrollPane_3);
		
		 imgabel = new JLabel();
		scrollPane_3.setViewportView(imgabel);
        
          JButton btnRepaint = new JButton("save");
          btnRepaint.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		 JFileChooser chooser =new JFileChooser();
     			    chooser.setCurrentDirectory(new File("."));
     			    chooser.setSelectedFile(new File("newImage.jpg"));
     			   //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);只选锟斤拷
     			    //file.exists() && dir.isDirectory()锟叫讹拷锟侥硷拷锟斤拷目录锟角凤拷锟斤拷锟�
     			     int result =chooser.showSaveDialog(null);
     			     String filePath=chooser.getSelectedFile().getPath();
     			    Image img = Toolkit.getDefaultToolkit().getImage("D:/graphviz-2.38/workspace/5.jpg");
     			    BufferedImage bi_scale = toBufferedImage(img);
     			   String[] strings=filePath.split("\\\\");//锟斤拷
			    	 String newstring="";
			    	for(String s:strings){
			    		   newstring=newstring+s+"/";
			    	}
			    	 newstring=newstring.substring(0,newstring.length()-1);
			    
			     try {
					ImageIO.write(bi_scale, "jpg",new File(newstring));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("图片不存在");
				}
                   
            	}
            });
            scrollPane_3.setColumnHeaderView(btnRepaint);
	}
	public static BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage)image;
	     }
	 
	    // This code ensures that all the pixels in the image are loaded
	     image = new ImageIcon(image).getImage();
	 
	    // Determine if the image has transparent pixels; for this method's
	    // implementation, see e661 Determining If an Image Has Transparent Pixels
	    //boolean hasAlpha = hasAlpha(image);
	 
	    // Create a buffered image with a format that's compatible with the screen
	     BufferedImage bimage = null;
	     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    try {
	        // Determine the type of transparency of the new buffered image
	        int transparency = Transparency.OPAQUE;
	       /* if (hasAlpha) {
	         transparency = Transparency.BITMASK;
	         }*/
	 
	        // Create the buffered image
	         GraphicsDevice gs = ge.getDefaultScreenDevice();
	         GraphicsConfiguration gc = gs.getDefaultConfiguration();
	         bimage = gc.createCompatibleImage(
	         image.getWidth(null), image.getHeight(null), transparency);
	     } catch (HeadlessException e) {
	        // The system does not have a screen
	     }
	 
	    if (bimage == null) {
	        // Create a buffered image using the default color model
	        int type = BufferedImage.TYPE_INT_RGB;
	        //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
	        /*if (hasAlpha) {
	         type = BufferedImage.TYPE_INT_ARGB;
	         }*/
	         bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	     }
	 
	    // Copy image to buffered image
	     Graphics g = bimage.createGraphics();
	 
	    // Paint the image onto the buffered image
	     g.drawImage(image, 0, 0, null);
	    g.dispose();
	 
	    return bimage;
	}
	
	public void setimgLabel(){
		//Timer timer=new Timer(100);
		Icon icon;
		try {
			icon = new ImageIcon(ImageIO.read(new File("D:\\graphviz-2.38\\workspace\\5.jpg"))); 
			imgabel.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
           
	}
}
