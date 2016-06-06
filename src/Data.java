import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextArea;

public class Data {
	String dataType;
	String filename;
	JTextArea editor;
	
	public Data(String dataType, String filename, JTextArea editor) {
		this.dataType = dataType;
		this.filename = filename;
		this.editor = editor;
	}
	
	public void open() {
		File f = new File(filename);
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
        
        editor.setText(sb.toString());
	}
	
	public void save(){
		File f = new File(filename);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fos.write(editor.getText().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
