import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class tic extends JFrame{  

    private OutputStream out;
    private String c;
    private int player;
    private table com;
    private label lab1;
    private label lab2;
    public XO xo;
    private Lock lock = new ReentrantLock();
    private Condition cond;
    public static int win =-1;
    private String message;


    public tic(int player,OutputStream out) {
        super("TIC TAC TO");
        this.player =  player;
        this.out = out;
        this.message = "WAIT FOR OTHER PLAYER";
        c = player==1?"X":"O"; 
        com = new table(300,150);
        lab1 = new label("PLAYER "+player,300,50,0);
        lab2 = new label(message,300,50,300);
        cond = lock.newCondition();
    }

    public void changeMessage(String message,boolean finish){
        this.message = message;
        lab2.setText(message);
        if(finish){
            clientManager.turn = 3;
        }
    }

    public void play1(){
      xo = new XO();  
      this.setSize(300,400);  
      this.add(lab1);
      this.add(lab2);
      this.add(com);
      this.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
         SwingUtilities.invokeLater(() -> {
          });
        if (player == clientManager.turn) { 
            int x = e.getX();
            int y = e.getY();
            try {
                win = xo.addXO(player,c, x, y, out); 
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            add(xo);
            clientManager.setTurn(3 - clientManager.turn);
            repaint();
            revalidate();
            if(win!=-1){
                if (win == 0) changeMessage("DRAW",true);
                else changeMessage("Player "+win+" WIN",true);
            }
        }
    }
});

    
    this.getContentPane().setBackground(Color.LIGHT_GRAY);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setVisible(true);  
    }  
}


