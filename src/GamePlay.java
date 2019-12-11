import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.VetoableChangeListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    static final int MARGIN_SX  = 0;
    static final int MARGIN_TOP = 0;
    static final int MARGIN_DX  = 560;
    static final int POINTS     = 5;
    static final int VITE       = 3;
    static final int MAP_ROW    = 5;
    static final int MAP_COL    = 10;

    static private final int PEDANAPOSX = 45;
    static private final int PEDANAPOSY = 540;
    static private final int BALLPOSX   = 90;
    static private final int BALLPOSY   = 510;
    static private final int DELAY      = 7;

    private Timer timer;
    private Ball ball;
    private Pedana pedana;
    private MapGenerator map;

    private int score;
    private int vite;
    private boolean play = false;

    public GamePlay(){

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        score  = 0;
        vite   = VITE;
        ball   = new Ball(BALLPOSX,BALLPOSY);
        pedana = new Pedana(PEDANAPOSX, PEDANAPOSY);
        map    = new MapGenerator(MAP_ROW, MAP_COL);
        timer  = new Timer(DELAY, this);

        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

        //Sfondo
        g2d.setColor(Color.black);
        g2d.fillRect(1,1,582,559);

        //Bordi
        g2d.setColor(Color.green);
        g2d.fillRect(0, 0,5, 560);
        g2d.fillRect(579,0, 5,560);
        g2d.fillRect(0,0, 582,5);

        //Score
        g2d.setColor(Color.white);
        g2d.setFont(new Font("serif", Font.BOLD, 20));
        g2d.drawString("Score: "+score, 490, 22);
        g2d.drawString("Vite: "+vite, 10, 22);
        g2d.drawString("Brick: "+map.getnBrick(), 250, 22);

        map.drawMap(g2d);
        pedana.drawPedana(g2d);
        ball.drawBall(g2d);

        if(vite == 0){
            g2d.setColor(Color.red);
            g2d.setFont(new Font("serif", Font.BOLD, 40));
            g2d.drawString("Hai Perso", 210, 300);
            g2d.setFont(new Font("serif", Font.BOLD, 20));
            g2d.drawString("Premi Invio per ricominciare", 160, 320);
        }
        if(map.getnBrick() == 0){
            g2d.setColor(Color.green);
            g2d.setFont(new Font("serif", Font.BOLD, 40));
            g2d.drawString("Hai Vinto", 210, 300);
            g2d.setFont(new Font("serif", Font.BOLD, 20));
            g2d.drawString("Premi Invio per ricominciare", 160, 320);
        }

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            ball.moveBall();
            ball.rilevaCollisioniMargini();
            ball.rilevaCollisioniPedana(pedana);
            ball.rilevaCollissioniBrick(map, this);

            if(vite == 0)
                play = false;

            if(map.getnBrick() == 0)
                play = false;

            if(ball.isOut()){
                play = false;
                ball.setBallPosX(BALLPOSX);
                ball.setBallPosY(BALLPOSY);
                ball.setBallDirX(1);
                ball.setBallDirY(-2);
                pedana.setPedanaPosX(PEDANAPOSX);
                pedana.setPedanaPosY(PEDANAPOSY);
                vite--;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(vite == 0 || map.getnBrick() == 0){
                play = false;
                ball.setBallPosX(BALLPOSX);
                ball.setBallPosY(BALLPOSY);
                ball.setBallDirX(1);
                ball.setBallDirY(-2);
                pedana.setPedanaPosX(PEDANAPOSX);
                pedana.setPedanaPosY(PEDANAPOSY);
                map = new MapGenerator(MAP_ROW, MAP_COL);
                score = 0;
                vite = VITE;
            }
            else
                play = true;
        }
        if(play) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (pedana.getPedanaPosX() >= 480)
                    pedana.setPedanaPosX(480);
                else
                    pedana.moveRight();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (pedana.getPedanaPosX() < 5)
                    pedana.setPedanaPosX(0);
                else
                    pedana.moveLeft();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void addScore(){
        score += POINTS;
    }

}
