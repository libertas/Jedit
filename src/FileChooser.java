
import java.io.File;  
  
import javax.swing.JButton;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
  

public class FileChooser extends JFrame{
	JButton open = null;
	public String filename = "";
    public FileChooser(){
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "Choose");
        File file=jfc.getSelectedFile();
        filename = file.getAbsolutePath();
    }
  
}
