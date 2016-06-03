import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Jedit {
	private static JTextArea editor;
	private static long tab_count = 1;
	
	public static void main(String[] arg) {
		JFrame app = new JFrame("JEdit");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 670);
		
		Container c = app.getContentPane();
		c.setLayout(new BoxLayout(c, 1));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.add(MakeTab(), "1");
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		JMenuItem newItem = new JMenuItem("New");
		fileMenu.add(newItem);
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tab_count++;
				tabbedPane.add(MakeTab(), String.valueOf(tab_count));
			}
		});
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser fc = new FileChooser();
				File f = new File(fc.filename);
				 FileInputStream fis = null;
				try {
					fis = new FileInputStream(f);
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
				 
				 byte[] buf = new byte[1024];
				 StringBuffer sb = new StringBuffer();
		         try {
					while((fis.read(buf)) != -1) {
					     sb.append(new String(buf));    
					     buf=new byte[1024];
					 }
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        
		         System.out.println(tabbedPane.getSelectedComponent().
		        		 getComponentAt(0, 0));
			}
		});
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		
		JMenuItem closeItem = new JMenuItem("Close");
		fileMenu.add(closeItem);
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		c.add(menubar);
		c.add(tabbedPane);
		
		app.setVisible(true);
	}
	
	protected static JPanel MakeTab() {
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, 1));
		
		editor = new JTextArea("", 35, 40);
		editor.setBorder(new LineBorder(Color.black));
		JScrollPane editSP = new JScrollPane(editor);
		jp.add(editSP);
		
		return jp;
	}
}
