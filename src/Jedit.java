import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Jedit {
	private static ArrayList<Data> dataList = new ArrayList<Data>();
	
	public static void main(String[] arg) {
		JFrame app = new JFrame("JEdit");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 670);
		
		Container c = app.getContentPane();
		c.setLayout(new BoxLayout(c, 1));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		JMenuItem newItem = new JMenuItem("New");
		fileMenu.add(newItem);
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileCreator fc = new FileCreator();
				
				File f = new File(fc.filename);
				if(f.isDirectory()) {
					return;
				}
				try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
					return;
				}
				
				Object[] obj = MakeTextTab(); 
				tabbedPane.add((Component) obj[0], fc.filename);
				Data data = new Data(fc.filename, (JTextArea) obj[1]);
				dataList.add(data);
				data.open();
			}
		});
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser fc = new FileChooser();
				
				File f = new File(fc.filename);
				if(f.isDirectory()) {
					return;
				}
				
				Data data;
				
				if(fc.filename.endsWith(".png")) {
					ImageIcon icon = new ImageIcon(fc.filename);
					tabbedPane.add(MakeImgTab(icon));
					data = new Data(fc.filename);
				} else {
					Object[] obj = MakeTextTab(); 
					tabbedPane.add((Component) obj[0], fc.filename);
					data = new Data(fc.filename, (JTextArea) obj[1]);
					
				}
				dataList.add(data);
				data.open();
			}
				
		});
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataList.get(tabbedPane.getSelectedIndex()).save();
			}
		});
		
		JMenuItem saveAllItem = new JMenuItem("Save All");
		fileMenu.add(saveAllItem);
		saveAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < dataList.size(); i++){
					dataList.get(i).save();
				}
			}
				
		});
		
		JMenuItem closeItem = new JMenuItem("Close");
		fileMenu.add(closeItem);
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataList.remove(tabbedPane.getSelectedIndex());
				tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
				if(tabbedPane.getSelectedIndex() == -1) {
					System.exit(0);
				}
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenu helpMenu = new JMenu("Help");
		menubar.add(helpMenu);
		
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Welcome to Jedit",
						"ABOUT", JOptionPane.PLAIN_MESSAGE);
			}
				
		});
		
		c.add(menubar);
		c.add(tabbedPane);
		
		app.setVisible(true);
	}
	
	protected static Object[] MakeTextTab() {
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, 1));

		JTextArea editor = new JTextArea("", 35, 40);
		editor.setBorder(new LineBorder(Color.black));
		JScrollPane editSP = new JScrollPane(editor);
		jp.add(editSP);
		
		Object[] obj = new Object[2];
		obj[0] = jp;
		obj[1] = editor;
		
		return obj;
	}
	
	protected static JPanel MakeImgTab(ImageIcon icon) {
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, 1));

		JLabel jlpic = new JLabel();
		jp.add(jlpic);
		
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		jlpic.setBounds(0, 0, 500, 670);
		jlpic.setHorizontalAlignment(0);
		jlpic.setIcon(icon);
		
		return jp;
	}
}
