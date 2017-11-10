package lab1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import lab1.Graph.Node;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class Myframe {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private HeapTest core;
	private TextGraph tgh;
	private JLabel imgabel;
	private String[] walkway;
	private int walkStep;
	private JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {// 直接在框架类里写主函数，而框架就可以写成私有的，只在类里使用就好

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Myframe window = new Myframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Myframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		JPanel panel0 = new JPanel();
		panel0.setBounds(0, 0, 1026, 595);
		frame.getContentPane().add(panel0);
		panel0.setLayout(null);

		JPanel digraphpanel = new JPanel();
		digraphpanel.setBounds(507, 0, 519, 597);
		panel0.add(digraphpanel);
		digraphpanel.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 519, 597);
		digraphpanel.add(scrollPane_3);

		imgabel = new JLabel();
		scrollPane_3.setViewportView(imgabel);

		JButton btnRepaint = new JButton("save");
		btnRepaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setSelectedFile(new File("newImage.jpg"));
				int result = chooser.showSaveDialog(panel0);
				String filePath = chooser.getSelectedFile().getPath();
				Image img = Toolkit.getDefaultToolkit().getImage("D:/graphviz-2.38/workspace/5.jpg");
				BufferedImage bi_scale = toBufferedImage(img);
				String[] strings = filePath.split("\\\\");// 噗
				String newstring = "";
				for (String s : strings) {
					newstring = newstring + s + "/";
				}
				newstring = newstring.substring(0, newstring.length() - 1);

				try {
					ImageIO.write(bi_scale, "jpg", new File(newstring));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("图片保存错误");
				}

			}
		});
		scrollPane_3.setColumnHeaderView(btnRepaint);

		JPanel inputpanel = new JPanel();
		inputpanel.setBounds(0, 0, 509, 597);
		panel0.add(inputpanel);
		inputpanel.setLayout(null);

		JPanel textipanel = new JPanel();
		textipanel.setBounds(0, 0, 508, 381);
		inputpanel.add(textipanel);
		textipanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("name\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 25));
		lblNewLabel.setBounds(41, 47, 82, 31);
		textipanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Location:");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 25));
		lblNewLabel_1.setBounds(0, 86, 136, 21);
		textipanel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.BOLD, 18));
		textField.setBounds(125, 51, 247, 27);
		textipanel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setSelectedFile(new File("MyText.txt"));
				int result = chooser.showOpenDialog(panel0);
				String filePath = chooser.getSelectedFile().getPath();
				String filename = chooser.getSelectedFile().getName();
				/*
				 * String[] strings=filePath.split("\\\\");//噗 String
				 * newstring=""; for(String s:strings){
				 * newstring=newstring+s+"/"; }
				 * newstring=newstring.substring(0,newstring.length()-1);
				 */

				textField_1.setText(filePath);
				// textField_1 .setText(newstring);
				textField.setText(filename);
			}
		});
		btnNewButton.setBounds(380, 49, 123, 30);
		textipanel.add(btnNewButton);

		JPanel resultpanel = new JPanel();
		resultpanel.setBounds(0, 383, 508, 212);
		inputpanel.add(resultpanel);
		resultpanel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 508, 212);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultpanel.add(scrollPane_1);

		JLabel label = new JLabel("\u67E5\u8BE2\u6865\u63A5\u8BCD\u53CA\u6C42\u6700\u77ED\u8DEF\u5F84\uFF1A");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("【微博：暖色君】萌妹体", Font.PLAIN, 25));
		label.setBounds(15, 110, 327, 43);
		textipanel.add(label);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setBounds(96, 149, 123, 31);
		textipanel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(357, 149, 123, 31);
		textipanel.add(textField_3);

		JLabel lblFirst = new JLabel("first word");
		lblFirst.setFont(new Font("Gabriola", Font.BOLD, 25));
		lblFirst.setBounds(10, 156, 91, 21);
		textipanel.add(lblFirst);

		JLabel lblSecondWord = new JLabel("second word");
		lblSecondWord.setFont(new Font("Gabriola", Font.BOLD, 25));
		lblSecondWord.setBounds(230, 156, 122, 21);
		textipanel.add(lblSecondWord);

		JButton btnSearch = new JButton("\u6865\u63A5\u8BCD");
		btnSearch.setForeground(Color.RED);
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(core.queryBridgeWords(tgh, textField_2.getText(), textField_3.getText()));
			}
		});

		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.BOLD, 25));
		textArea_1.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setBounds(0, 0, 200, 200);
		textArea_1.setRows(1);
		textArea_1.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.BOLD, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(125, 85, 247, 27);
		textipanel.add(textField_1);
		btnSearch.setBounds(54, 195, 136, 31);
		textipanel.add(btnSearch);

		JLabel label_1 = new JLabel("\u63D2\u5165\u6865\u63A5\u8BCD\uFF1A");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("【微博：暖色君】萌妹体", Font.PLAIN, 25));
		label_1.setBounds(15, 241, 153, 43);
		textipanel.add(label_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(159, 241, 268, 98);
		textipanel.add(scrollPane_2);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		textArea_2.setLineWrap(true);
		scrollPane_2.setViewportView(textArea_2);

		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == e.SELECTED) {
					System.out.println((String) comboBox.getSelectedItem());
					String shp = core.calcShortestPath(tgh, textField_2.getText(), (String) comboBox.getSelectedItem());
					textArea_1.setText(shp.replace(":", "->").replace("|", "；distance:"));
					try {
						showshortsestGraph(shp, tgh);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				setimgLabel();
			}
		});
		comboBox.setBounds(349, 197, 131, 29);
		comboBox.setVisible(false);
		textipanel.add(comboBox);

		JButton button = new JButton("\u6700\u77ED\u8DEF\u5F84");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setVisible(false);
				String shp = core.calcShortestPath(tgh, textField_2.getText(), textField_3.getText());

				if (shp == null) {
					if (textField_3.getText().equals("")) {
						textArea_1.setText("没有输入任何单词");
					} else if (textField_2.getText().equals("")) {
						textArea_1.setText("请输入first单词");
					} else {
						textArea_1.setText("self-loop");
					}
				} else if (shp.substring(0, 1).equals("!")) {
					textArea_1.setText(shp.substring(1, shp.length()));

				} else {
					if (textField_3.getText().equals("")) {
						// textArea_1.setText(shp.replace(":","->").replace("!","；distance:").replace("|",
						// "\r\n"));
						// 给复选框添加内容
						comboBox.removeAllItems();

						String[] ways = shp.split("\\|");
						for (String s : ways) {
							System.out.println(s);
							String[] parts = s.split("!");
							String[] nodes = parts[0].split(":");
							comboBox.addItem(nodes[nodes.length - 1]);
						}
						comboBox.setVisible(true);
					} else {
						textArea_1.setText(shp.replace(":", "->").replace("|", "；distance:").replace("!", "\r\n"));
						try {
							String[] allpath = shp.split("!");
							String[] dis = shp.split("|");
							showshortsestGraph(allpath[0] + "|" + dis[1], tgh);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setimgLabel();
					}

				}
			}
		});
		button.setForeground(Color.BLUE);
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(205, 195, 136, 31);
		textipanel.add(button);

		JButton btnInsert = new JButton("\u63D2\u5165");
		btnInsert.setFont(new Font("宋体", Font.PLAIN, 18));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(core.generateNewText(tgh, textArea_2.getText()));
			}
		});
		btnInsert.setBounds(426, 241, 77, 97);
		textipanel.add(btnInsert);

		JButton btnBuild = new JButton("build");
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				core = new HeapTest();
				tgh = core.createDirectedGraph(textField_1.getText());
				try {
					core.showDirectedGraph(tgh);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("hahaha");
				}
				setimgLabel();
				// digraphpanel.changeimage("D:/graphviz-2.38/workspace/5.jpg");
				// btnSave.setVisible(true);
			}
		});
		btnBuild.setBounds(380, 83, 123, 30);
		textipanel.add(btnBuild);

		JLabel label_2 = new JLabel("\u8F93\u5165\u6587\u4EF6\u540D\u53CA\u76EE\u5F55\uFF1A");
		label_2.setBounds(15, 0, 327, 43);
		textipanel.add(label_2);
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("【微博：暖色君】萌妹体", Font.PLAIN, 25));

		JButton btnRandomWalk = new JButton("random walk");
		WalkListener walklistener = new WalkListener();
		btnRandomWalk.addActionListener(walklistener);
		btnRandomWalk.setBounds(0, 352, 254, 29);
		textipanel.add(btnRandomWalk);

		JButton btnContinue = new JButton("continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				walkStep++;
				if (walkStep < walkway.length) {
					String s = "";
					for (int i = 0; i < walkStep; i++) {
						s += walkway[i] + ":";
					}
					s += walkway[walkStep];
					textArea_1.setText(s);
					try {
						showshortsestGraph(s, tgh);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setimgLabel();
				} else {
					textArea_1.setText("已走完");
				}
			}
		});
		btnContinue.setBounds(254, 352, 254, 29);
		textipanel.add(btnContinue);

		frame.setFont(new Font("Arial", Font.PLAIN, 12));
		frame.setTitle("\u6587\u672C\u6709\u5411\u56FE");
		frame.setBounds(100, 100, 1039, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setUndecorated(true);
	}

	public void setimgLabel() {
		// Timer timer=new Timer(100);
		Icon icon;
		try {
			icon = new ImageIcon(ImageIO.read(new File("D:\\graphviz-2.38\\workspace\\5.jpg")));
			imgabel.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	class WalkListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = core.randomWalk(tgh);
			walkway = s.split(" ");
			walkStep = 0;
			// ActionListener listener=new TimeListener();
		}
	}

	// class TimeListener implements ActionListener()
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		// boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			/*
			 * if (hasAlpha) { transparency = Transparency.BITMASK; }
			 */

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			// int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
			/*
			 * if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }
			 */
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	public void showshortsestGraph(String info, TextGraph G) throws IOException {//
		try {
			FileWriter fileWriter = new FileWriter(new File("GData.gv"));
			fileWriter.write("digraph g{\r\n");

			String[] parts = info.split("\\|");
			String[] rednodes = parts[0].split(":");

			/*
			 * for(String s:rednodes ){ System.out.println(s); }
			 */

			Set<String> nodes = G.getGph().getSet();
			for (String s : nodes) {
				fileWriter.write(s + ";\r\n");
			}
			Map<String, LinkedList<Node>> m_map = G.getGph().getMap();
			Boolean flag = false;
			for (String s : m_map.keySet()) {
				for (Node nd : m_map.get(s)) {
					// 判断是否是最短路径中的一条边
					flag = false;
					for (int i = 0; i < rednodes.length - 1; i++) {
						if (rednodes[i].equals(s) && rednodes[i + 1].equals(nd.value)) {
							fileWriter.write(s + "->" + nd.value + "[label=" + nd.Count + ",color=red];\r\n");
							flag = true;
						}
					}
					if (flag == false) {
						fileWriter.write(s + "->" + nd.value + "[label=" + nd.Count + "];\r\n");
					}
				}
			}
			fileWriter.write("}");
			fileWriter.close();
		} catch (FileNotFoundException e1) {
			System.out.println("文件有错");
		}

		try {
			// Process process = Runtime.getRuntime().exec("dot
			// D:\\eclipse\\workspace\\lab1\\GData.gv -T jpg -o
			// D:\\graphviz-2.38\\workspace\\5.jpg");
			Process process = Runtime.getRuntime().exec(
					dot D:\\eclipse\\workspace\\lab1\\GData.gv -T jpg -o D:\\graphviz-2.38\\workspace\\5.jpg);
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("错了");
		}
	}
}
