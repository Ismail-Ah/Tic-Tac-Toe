import java.awt.Color;  
import java.awt.Graphics;  
import javax.swing.JComponent;  

public class table extends JComponent{

    private int width;
    private int start;
    private static int w;
    private static int s;

    public table(int width,int w){
        this.width = w;
        this.w = w;
        this.start = (width-w)/2;
        this.s = start;
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);  
        g.drawRect(start, start, width, width);
        paintLines(g,start+width/3);
        paintColumns(g,start+width/3);
    }

    
   

    private void paintLines(Graphics g,int x){
        int dx = width/3;
        g.drawLine(x, start, x, width+start);
        g.drawLine(x+dx, start, x+dx, width+start);
    }

    private void paintColumns(Graphics g,int y){
        int dy = width/3;
        g.drawLine(start, y, width+start, y);
        g.drawLine(start, y+dy, width+start, y+dy);
    }

    public static int getW(){
        return table.w+s;
    } 

    public static int getStart(){
        return table.s;
    }

    public static Integer[][] getLocations(){
        Integer[][] locations = new Integer[9][2];
        int dx = w/3;
        int x = s+(dx/2);
        int y = s+(dx/2);
        for (int i = 0;i<9;i++){
            locations[i][0] = x-2;
            locations[i][1] = y+5;
            x+=dx;
            if (i==2 || i==5){
                y+=dx;
                x = s+(dx/2);
            }
        }
        return locations;
    }


}
