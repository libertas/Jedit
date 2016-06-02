import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Jedit {
	private static JTextArea editor;
	private static Scanner s = null;;
	
	public static void main(String[] arg) {
		JFrame app = new JFrame("Editor");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 670);
		
		Container c = app.getContentPane();
		c.setLayout(new FlowLayout());
		
		editor = new JTextArea("", 35, 40);
		editor.setBorder(new LineBorder(Color.black));
		c.add(editor);
		
		app.setVisible(true);
	}
}
