import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class label extends JLabel {

    private String message;
    private int width;
    private int height;
    private int start_y;
    
    public label(String message,int width,int height,int start_y) {
        this.message = message;
        this.width = width;
        this.height = height;
        this.start_y = start_y;
        createMessage();
    }

    public void createMessage(){
        this.setText(message);
        this.setBounds(0, start_y, width, height);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBackground(Color.WHITE);
        this.setOpaque(true); 
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(border);
    }
}