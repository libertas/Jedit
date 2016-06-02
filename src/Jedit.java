import java.awt.Color;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
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
		
		editor = new JTextArea("", 35, 40);
		editor.setBorder(new LineBorder(Color.black));
		JScrollPane editSP = new JScrollPane(editor);
		c.add(editSP);
		
		app.setVisible(true);
	}
}
