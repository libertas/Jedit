import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FileCreator {
	JButton open = null;
	public String filename = "";
    public FileCreator(){
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "Create");
        File file=jfc.getSelectedFile();
        filename = file.getAbsolutePath();
    }
}
