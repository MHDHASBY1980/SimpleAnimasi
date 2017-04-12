/**
 *
 * @author masQOD
 */
import javax.swing.*;
public class animasiAwan extends Thread implements Runnable{
    JPanel panel;
    int ordinat=800;

    public int getOrdinat(){
        return ordinat;
    }

    public final int mulai = 100;
    int kode;

    public animasiAwan(JPanel panel, int kode){
        this.panel = panel;
        this.kode = kode;
    }
    @Override
    public void run(){
        while(true){
            
               if(kode==mulai)
                    ordinat-=5;

                if(ordinat<=-500)
                    ordinat=kode;

            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
            panel.repaint();
        }
    }
}
