import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class clientManager extends Thread{
    private int player = -1;
    private int choix = -1;
    private Socket socket;
    private OutputStream out;
    private InputStream inp;
    private boolean addNew = false;
    private String message1 = "Your Tourn";
    public static int turn = 1;
    private StringBuilder Message = new StringBuilder();
    public tic f;


    public clientManager(Socket socket) throws Exception{
        this.player = player;
        this.socket = socket;
        this.out = socket.getOutputStream();
        this.inp = socket.getInputStream();
    }


    public void setChoix(int choix){
        this.choix = choix;
        this.addNew = true;
        turn = 3-turn;
    }

    private void thread1(){
        while(true){
            int c;
            try {
                while((c=inp.read())!=-1){
                    if(player==-1){
                        player=c;
                    }
                    else if(turn!=player){
                        setChoix(c);
                    }
                    else{
                        Message.append(Integer.toString(c));
                    } 
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void thread2(){
        f = new tic(player, out);
        f.play1();
        while(true){
            if (choix!=-1 && addNew){
                try {
                    tic.win = f.xo.addXO1(player==1?"O":"X",choix,out);
                    if(tic.win!=-1){
                        if (tic.win == 0) f.changeMessage("DRAW",true);
                        else f.changeMessage("Player "+tic.win+" WIN",true);
                    }
                    f.add(f.xo);
                    f.repaint();
                    f.revalidate();
                    if(turn!=3){
                        f.changeMessage("Your Turn",false);
                    }
                    
                    addNew = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            if(turn!=player && player!=-1 && turn!=3){
                f.changeMessage("Wait until Other Player Play",false);
            }
            if (turn ==3){
                if (tic.win == 0) f.changeMessage("DRAW",true);
                else f.changeMessage("Player "+tic.win+" WIN",true);
            }
            Thread.yield();

        }
    }

    public synchronized static int getTurn(){
        return turn;
    }

    public static void setTurn(int t){
        turn=t;
    }

    public void run(){
        new Thread(()->{
            thread1();
        }).start();
        new Thread(()->{
            thread2();
        }).start();
    }

}
