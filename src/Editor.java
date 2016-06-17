import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

public class Editor {
	private static ArrayList<Data> dataList = new ArrayList<Data>();
	public Editor() {
		JFrame app = new JFrame("JEdit");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ĭ�ϵĹرղ���
		app.setSize(500, 370);
		
		Container c = app.getContentPane(); // ȡ���������
		c.setLayout(new BoxLayout(c, 1));//���񲼾�  һ���һ��??????
		//c.setBackground(Color.gray);
		
		JTabbedPane tabbedPane = new JTabbedPane();  //ת���˵�
		
		JMenuBar menubar = new JMenuBar();   //�����˵���
		
		JMenu fileMenu = new JMenu("File");  //�ļ���ť
		menubar.add(fileMenu);   
		
		JMenuItem newItem = new JMenuItem("New");  //newѡ��
		fileMenu.add(newItem);
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //�½�����������     e�Ǹ�ʲô�õ�
				FileCreator fc = new FileCreator();   //�������ļ�
				
				File f = new File(fc.filename);
				if(f.isDirectory()) {   //������ļ�Ŀ¼������
					return;
				}
				try {
					f.createNewFile();
				} catch (IOException e1) {   //����IO���쳣
					e1.printStackTrace();   //�������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
					return;
				}
				
				Object[] obj = MakeTextTab(); //�����ı��ĵ��ı�ǩҳ
				tabbedPane.add((Component) obj[0], fc.filename);
				Data data = new Data(fc.filename, (JTextArea) obj[1]);
				dataList.add(data);
				data.open();
			}
		});
		
		JMenuItem openItem = new JMenuItem("Open");   //Openѡ��
		fileMenu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser fc = new FileChooser();   //ѡ���ļ�
				
				File f = new File(fc.filename);
				if(f.isDirectory()) {
					return;
				}
				
				Data data;
				
				if(fc.filename.endsWith(".jpg")
						|| fc.filename.endsWith(".gif") 
						|| fc.filename.endsWith(".png")) {  //��.jpg��.gif��.png���͵�ͼƬ
					ImageIcon icon = new ImageIcon(fc.filename);   //����ͼƬ
					tabbedPane.add(MakeImgTab(icon), fc.filename);
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
		
		JMenuItem saveItem = new JMenuItem("Save");  //Saveѡ��
		fileMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataList.get(tabbedPane.getSelectedIndex()).save(); //�õ�dataList�е�����
			}
		});
		
		JMenuItem saveAllItem = new JMenuItem("Save All");   //Save allѡ��
		fileMenu.add(saveAllItem);
		saveAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < dataList.size(); i++){
					dataList.get(i).save();
				}
			}
				
		});
		
		JMenuItem closeItem = new JMenuItem("Close");  //Closeѡ��
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
		
		JMenuItem exitItem = new JMenuItem("Exit");   //Exitѡ��
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);    //�˳�
			}
		});
		
		JMenu helpMenu = new JMenu("Help");   //Help��ť
		menubar.add(helpMenu);
		
		JMenuItem aboutItem = new JMenuItem("About");  //Aboutѡ��
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"��ӭʹ�ñ��༭��\n"
						+ "������������\n"
						+ "ѧ�ţ�2014211067\n"
						+ "ѧԺ�����ӹ���ѧԺ\n",
						"ABOUT", JOptionPane.PLAIN_MESSAGE);  //����Ϣ
				//JOptionPane.showMessageDialog(null, "������ʾ"); //��ʾ��Ϣ
				/*JOptionPane.showMessageDialog(aboutItem, 
						"����", "ABOUT",JOptionPane.WARNING_MESSAGE);  //������Ϣ*/
				/*JOptionPane.showMessageDialog(null,
						"����.", "����",JOptionPane.ERROR_MESSAGE);  //������Ϣ*/
			}
		});
		
		c.add(menubar); //�Ѳ˵�����ӵ�����c��
		c.add(tabbedPane);
		
		app.setVisible(true);//�ɼ�
	}
	
	protected static Object[] MakeTextTab() {      //�����ı��ļ�
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, 1));

		JTextArea editor = new JTextArea("", 35, 40);
		editor.setBorder(new LineBorder(Color.green));
		JScrollPane editSP = new JScrollPane(editor); //�������
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
		JScrollPane imgSP = new JScrollPane(jlpic);
		jp.add(imgSP);
		
		jlpic.setIcon(icon);
		
		return jp;
	}
}
