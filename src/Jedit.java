import java.awt.Color;
import java.awt.Container;
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
	
	public static void main(String[] arg) {
		JFrame app = new JFrame("JEdit");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 670);
		
		Container c = app.getContentPane();
		c.setLayout(new BoxLayout(c, 1));
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		JMenuItem newItem = new JMenuItem("New");
		fileMenu.add(newItem);
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		JMenu helpMenu = new JMenu("Help");
		menubar.add(helpMenu);
		
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
		
		c.add(menubar);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		c.add(tabbedPane);
		
		tabbedPane.add(MakeTab(), "1");
		tabbedPane.add(MakeTab(), "2");
		tabbedPane.add(MakeTab(), "3");
		
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
