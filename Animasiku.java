import javax.swing.JApplet;
/**
 *
 * @author masQOD
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.applet.*;
import java.awt.font.*;

public class Animasiku extends JApplet {
    public static void main(String[] args) {
       JFrame frame = new JFrame();
        frame.setTitle("Qodir Masruri | 201481045 | Tugas Online 1 : Java Aplet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JApplet applet = new Animasiku();
        applet.init();
        frame.getContentPane().add(applet);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    @Override
    public void init(){
        JPanel panel = new PanelMobil();
        getContentPane().add(panel);
    }
}

class PanelMobil extends JPanel implements Runnable{

    //inisialisasi koordinat orang
    int xMobil = -500, yMobil = 100, vMobil = 1;
    
    //inisialisasi koordinat awan
    int mulai = 100;

    //memanggil kelas thread
    animasiMobil mobil = new animasiMobil(this, xMobil, yMobil, vMobil);
    animasiAwan awan = new animasiAwan(this, mulai);

    PanelMobil(){
        setPreferredSize(new Dimension(1000, 600));
        setBackground(Color.white);
        //membuat thread
        Thread thread = new Thread(this);
        //memulai thread
        thread.start();
        mobil.start();
        awan.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawLangit(g2, 0, 0);
        drawRumput(g2, 0, 300);
        drawJalan(g2, 0, 350);
        drawSemak(g2, 0, 300);
        drawSemak(g2, 300, 300);
        drawSemak(g2, 650, 300);
        drawBendera(g2, 0, 400);
        drawNama(g2, 700, 400);
        
        //gettter koordinat mobil
        xMobil = mobil.getX();
        yMobil = mobil.getY();
        vMobil = mobil.getV();
        drawMobil(g2, xMobil, yMobil, vMobil);
        
        //getter koordinat awan
        mulai = awan.getOrdinat();
        drawMatahari(g2, 900, 20);
        drawAwan(g2, 880+mulai, 100);
        drawAwan(g2, 800+mulai, 50);
        drawAwan(g2, 500+mulai, 50);
        drawAwan(g2, 380+mulai, 70);
        drawTulisan(g2, 50, 570);
    }

    //method menggambar matahari
    public void drawMatahari(Graphics2D g2, int x, int y){
        Ellipse2D.Double matahari = new Ellipse2D.Double(x, y, 80, 80);
        GradientPaint warnaMatahari = new GradientPaint(870, 20, Color.white, 950, 0, Color.yellow);
        g2.setPaint(warnaMatahari);
        g2.fill(matahari);
    }
    //method menggambar awan
    public void drawAwan(Graphics2D g2, int x, int y){
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2.0f));
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x, y);
        gp.curveTo(x, y, x+20, y-30, x+40, y);
        gp.curveTo(x+40, y, x+60, y-30, x+80, y);
        gp.curveTo(x+80, y, x+130, y+20, x+80, y+40);
        gp.curveTo(x+80, y+40, x+60, y+60, x+40, y+40);
        gp.curveTo(x+40, y+40, x+20, y+60, x, y+40);
        gp.curveTo(x, y+40, x-50, y+20, x, y);
        g2.setColor(Color.white);
        g2.draw(gp);
        g2.fill(gp);
    }
    //method menggambar rumput
    public void drawRumput(Graphics2D g2, int x, int y){
        Rectangle2D.Double rumput = new Rectangle2D.Double(x, y, 1000, 300);
        g2.setColor(Color.green);
        g2.fill(rumput);
    }
    //method menggambar jalan
    public void drawJalan(Graphics2D g2, int x, int y){
        Rectangle2D.Double jalan = new Rectangle2D.Double(x, y, 1000, 150);
        g2.setColor(Color.black);
        g2.fill(jalan);
    //Membuat garis jalan
        Graphics2D g2d = (Graphics2D) g2;
        g2d.setColor(Color.white);
        float[] dash = {30.0f};
        BasicStroke strok = new BasicStroke(5.0f, BasicStroke.CAP_SQUARE,
        BasicStroke.JOIN_MITER, 1.0f, dash, 0.0f);
        g2d.setStroke(strok);
        g2d.draw(new Line2D.Double(10, 420, 1000, 420));
    }
    //method menggambar langit
    public void drawLangit(Graphics2D g2, int x, int y){
        Rectangle2D.Double langit = new Rectangle2D.Double(x, y, 1000, 300);
        GradientPaint warna = new GradientPaint(0, 0, Color.cyan, 0, 250, Color.white);
        g2.setPaint(warna);
        g2.fill(langit);
    }
    //method menggambar orang
    public void drawMobil(Graphics2D g2, int x, int y, int v){
        g2.setColor(Color.red);
        g2.fillRect(105,355,100,30 ); //badan bawah
        g2.fillOval(107,328,104,45); //badan atas 
        g2.setColor(Color.blue);
        g2.fillRect(110,355,100,30 ); //badan bawah
        g2.fillOval(110,330,100,45); //badan atas
        g2.fillArc (80,348,60,70,0,180);// atas ban
        g2.fillArc (185,348,60,70,0,180);// atas ban
        g2.setColor(Color.yellow);
        g2.fillArc (218,351,23,23,0,80);// atas ban 
        g2.setColor(Color.black); //ban
        g2.fillOval(98, 363, 32,32); //ban
        g2.fillOval(198, 363, 32,32); //ban
        g2.setColor(Color.white); //ban
        g2.fillOval(104, 369, 20,20); //ban
        g2.fillOval(204, 369, 20,20); //ban 
        g2.setColor(Color.red);
        g2.fillArc (130,335,70,40,0,90);// atas ban
        g2.setColor(Color.black);
        g2.fillArc (134,336,64,36,0,90);// atas ban 
        g2.setColor(Color.red);
        g2.fillArc (120,335,70,40,90,90);// atas ban
        g2.setColor(Color.black);
        g2.fillArc (123,336,68,36,90,90);// atas ban
        g2.setColor(Color.red);
        g2.fillRect(158,336,10,18 );
    }
    //method menggambar semak
    public void drawSemak(Graphics2D g2, int x, int y){
        g2.setColor(new Color(44, 133, 55));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2.0f));
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x, y);
        gp.quadTo(x+30, y-80, x+60, y-50);
        gp.quadTo(x+80, y-90, x+100, y-50);
        gp.quadTo(x+150, y-100, x+180, y-50);
        gp.quadTo(x+210, y-90, x+230, y-50);
        gp.quadTo(x+250, y-80, x+260, y-50);
        gp.quadTo(x+280, y-70, x+300, y-30);
        gp.quadTo(x+310, y-50, x+320, y);
        gp.lineTo(x, y);
        g2.draw(gp);
        g2.fill(gp);
    }
    //bendera
    public void drawBendera(Graphics2D g2, int x, int y){
        g2.setColor(Color.red);
        g2.fillRect(100,120,100 ,35 );
        g2.setColor(Color.darkGray);
        g2.fillRect(100,120,5 ,120 );
        g2.setColor(Color.white);
        g2.fillRect(100,150,100 ,35 );
        g2.setColor(Color.darkGray);
        g2.fillRect(100,150,5 ,150 ); 
    }
    
    public void drawNama(Graphics2D g2, int x, int y){
        //nama background
        g2.setColor(Color.white);
        g2.fillRect(750,498,170 ,40 );
        //Q
        g2.setColor(Color.black);
        g2.fillRect(770,510,3,15 );
        g2.fillRect(770,510,10,3 ); //atas
        g2.fillRect(770,522,10,3 ); //atas
        g2.draw(new Line2D.Double(772, 512, 780, 526));
        g2.fillRect(780,510,3,15 ); 
        //O
        g2.fillRect(790,510,3,15 ); // berdiri
        g2.fillRect(800,510,3,15 ); //berdiri
        g2.fillRect(790,510,10,3 ); //atas
        g2.fillRect(790,522,10,3 );//tengah 
        //D
        g2.fillRect(810,510,3,15 ); //berdiri 
        g2.fillRect(820,512,3,11 ); //berdiri
        g2.fillRect(810,510,10,3  ); //atas
        g2.fillRect(810,522,10,3 ); //bawah
        //I
        g2.fillRect(830,510,3,15 ); // berdiri            
        //R
        g2.fillRect(840,510,3,15 ); // berdiri
        g2.fillRect(850,510,3,10 ); //berdiri
        g2.draw(new Line2D.Double(849, 520, 860, 525));
        g2.fillRect(840,510,12,3 ); //atas
        g2.fillRect(840,517,12,3 );//tengah          
        //M
        g2.fillRect(880,510,3,15 ); //berdiri
        g2.fillRect(888,510,3,15 ); //berdiri
        g2.fillRect(896,510,3,15 ); //berdiri
        g2.fillRect(880,510,16,3 ); //atas 
    }
    
    //method menggambar tulisan
    public void drawTulisan(Graphics2D g2, int x, int y){
        Font font = new Font("Serif", Font.BOLD, 75);
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(frc, "Parkir Mobil");
        Shape glyph = gv.getOutline(x,y);
        g2.setStroke(new BasicStroke(3.0f));
        g2.draw(glyph);
        g2.setColor(Color.ORANGE);
        g2.fill(glyph);
    }

    public void run() {
        while(true){
            try{
                Thread.sleep(20);
            }catch(Exception e){}
        }
    }
}
