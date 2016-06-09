import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Data {
	String dataType;
	String filename;
	JTextArea editor;
	
	public Data(String filename, JTextArea editor) {
		this.filename = filename;
		this.editor = editor;
		
		if(filename.endsWith(".txt")) {
			this.dataType = "text";
		} else if(filename.endsWith(".png")) {
			this.dataType = "image";
		} else {
			this.dataType = "bin";
		}
	}
	
	public Data(String filename) {
		this.filename = filename;
		this.editor = null;
		
		this.dataType = "image";
	}
	
	public void open() {
		File f = new File(filename);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		if(dataType == "text" || dataType == "bin") {
			editor.setText("");
			
			if(dataType == "text") {
				byte[] buf = new byte[1024];
				
		        try {
		        	while((fis.read(buf)) != -1) {
		        		String str = new String(buf);
		        		str = str.trim();
	
		        		this.editor.append(str);
		        		
		        		buf=new byte[1024];
		        	}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	        
	        if(dataType == "bin") {
	        	try {
	        		byte[] b = new byte[(int) f.length()];
	        		fis.read(b);
	        		
	        		String tmp;
		        	for(int i = 0; i < b.length; i++) {
		        		tmp = Integer.toHexString(b[i] & 0xff);
			        	if((i + 1) % 5 == 0) {
			        		editor.append(tmp + "\n");
			        	} else {
			        		editor.append(tmp + "\t");
			        	}
		        	}
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}
    		}
		}
        
        
	}
	
	public void save(){
		File f = new File(filename);
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(dataType == "text") {
			try {
				fos.write(editor.getText().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(dataType == "bin") {
			String text = this.editor.getText();
			String[] tmp_list = text.split("\n");
			String[][] tlist = new String[tmp_list.length][];
			int length = 0;
			for(int i = 0; i < tmp_list.length; i++) {
				tlist[i] = tmp_list[i].split("\t");
				length += tlist[i].length;
			}
			
			byte[] binary = new byte[length];
			int count = 0;
			
			for(int i = 0; i < tlist.length; i++) {
				for(int j = 0; j < tlist[i].length; j++) {
					binary[count] = (byte)(int)Integer.valueOf(tlist[i][j], 16);
					count++;
				}
			}

			try {
				fos.write(binary);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(dataType == "image") {
		}
	}
}
