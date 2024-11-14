import java.awt.Graphics;
import java.io.OutputStream;
import java.util.Arrays;

import javax.swing.JComponent;  


public class XO extends JComponent{
    private String[] xo = new String[9];
    private Integer[][] locations = table.getLocations();
    private String[] xArray = {"X","X","X"};
    private String[] oArray = {"O","O","O"};
    public void paint(Graphics g){
        for(int i=0;i<9;i++){
            if (xo[i]!=null){
                g.drawString(xo[i], locations[i][0], locations[i][1]);
            }
        }
    }

    public int addXO(int player,String c,int x,int y,OutputStream out) throws Exception{
        int w = table.getW();
        int s = table.getStart();
        if (x<w && y<(w+25) && x>s && y>(s+25)){
            int dx = (w-s)/3;
            int posX = (x-s)/dx;
            int posY = ((y-s-25)/dx)*3;
            int index = posX+posY;
            if (xo[index]==null){
                xo[index]=c;
                out.write(index);
                repaint();
                return check(out);
            }           
        }
        return -1;
    }

    public int addXO1(String c,int i,OutputStream out) throws Exception{
        xo[i]=c;
        repaint(); 
        revalidate();
        return check(out); 
    }

    public String[] getXo() {
        return xo;
    }
    public void setXo(String[] xo) {
        this.xo = xo;
    }

    public int check(OutputStream out){
        int[][] win = {
            {0, 1, 2},
            {3, 4, 5}, 
            {6, 7, 8}, 
            {0, 3, 6},
            {1, 4, 7}, 
            {2, 5, 8}, 
            {0, 4, 8}, 
            {2, 4, 6} 
        };
        
        for (int[] condition : win) {
            String[] arr = {xo[condition[0]], xo[condition[1]], xo[condition[2]]};
            int res = checkLine(arr);
            if (res != -1) {
                return res;
            }
        }
        if (!Arrays.asList(xo).contains(null)) {
            return 0;
        }        
        return -1;
    }

    private int checkLine(String[] line) {
        if (Arrays.equals(line, xArray) || Arrays.equals(line, oArray)) {
            return line[0].equals("X") ? 1 : 2;
        }
        return -1;
    }

}
