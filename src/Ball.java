import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class Ball {

    private int ballPosX;
    private int ballPosY;
    private int ballDirX;
    private int ballDirY;

    public Ball(int ballPosX, int ballPosY) {
        this.ballPosX = ballPosX;
        this.ballPosY = ballPosY;
        this.ballDirX = 1;
        this.ballDirY = -2;
    }

    public void drawBall(Graphics2D g){
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(hints);
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20);
    }

    public void rilevaCollisioniMargini(){
        if(getBallPosX() < GamePlay.MARGIN_SX) {
            setBallDirX(-1 * getBallDirX());
        }
        if(getBallPosY() < GamePlay.MARGIN_TOP) {
            setBallDirY(-1 * getBallDirY());
        }
        if(getBallPosX() > GamePlay.MARGIN_DX) {
            setBallDirX(-1 * getBallDirX());
        }
    }

    public void rilevaCollisioniPedana(Pedana p){
        if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects
                (new Rectangle(p.getPedanaPosX(), p.getPedanaPosY(), Pedana.WIDTH_PEDANA, Pedana.HEIGHT_PEDANA))){
            setBallDirY(-1 * getBallDirY());
        }
    }

    public void rilevaCollissioniBrick(MapGenerator map, GamePlay gp){
        for(int r = 0; r < map.getRow(); r++){
            for(int c = 0; c < map.getCol(); c++){
                if(map.getBrickValue(r, c) > 0){
                    int brickX = c * map.getBrickWidth() + 20;
                    int brickY = r * map.getBrickHeight() + 30;
                    if(new Rectangle(brickX, brickY, map.getBrickWidth(), map.getBrickHeight()).intersects
                            (new Rectangle(ballPosX, ballPosY, 20, 20))){
                        map.setBrickValue(0, r, c);

                        if(ballPosX + 19 < brickX || ballPosX + 1 >= brickX + map.getBrickWidth()){
                            setBallDirX(-1 * getBallDirX());
                        }
                        else {
                            setBallDirY(-1 * getBallDirY());
                        }
                        map.reduceBrick();
                        gp.addScore();
                    }
                }
            }
        }
    }

    public boolean isOut(){
        if(ballPosY > 540)
            return true;
        else
            return false;
    }

    public void moveBall(){
        setBallPosX(getBallPosX()+getBallDirX());
        setBallPosY(getBallPosY()+getBallDirY());
    }

    public int getBallPosX() {
        return ballPosX;
    }

    synchronized public void setBallPosX(int ballPosX) {
        this.ballPosX = ballPosX;
    }

    public int getBallPosY() {
        return ballPosY;
    }

    synchronized public void setBallPosY(int ballPosY) {
        this.ballPosY = ballPosY;
    }

    public int getBallDirX() {
        return ballDirX;
    }

    synchronized public void setBallDirX(int ballDirX) {
        this.ballDirX = ballDirX;
    }

    public int getBallDirY() {
        return ballDirY;
    }

    synchronized public void setBallDirY(int ballDirY) {
        this.ballDirY = ballDirY;
    }
}
