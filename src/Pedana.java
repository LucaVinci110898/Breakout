import java.awt.*;

public class Pedana {

    static final int WIDTH_PEDANA     = 100;
    static final int HEIGHT_PEDANA    = 8;
    static final int ARCWIDTH_PEDANA  = 5;
    static final int ARCHEIGHT_PEDANA = 5;

    private int pedanaPosX;
    private int pedanaPosY;

    public Pedana(int pedanaPosX, int pedanaPosY) {
        this.pedanaPosX = pedanaPosX;
        this.pedanaPosY = pedanaPosY;
    }

    public void drawPedana(Graphics2D g){
        g.setColor(Color.green);
        g.fillRoundRect(pedanaPosX, pedanaPosY, WIDTH_PEDANA, HEIGHT_PEDANA, ARCWIDTH_PEDANA, ARCHEIGHT_PEDANA);
    }

    public void moveRight(){
        setPedanaPosX(getPedanaPosX() + 20);
    }

    public void moveLeft(){
        setPedanaPosX(getPedanaPosX() - 20);
    }

    public int getPedanaPosX() {
        return pedanaPosX;
    }

    public void setPedanaPosX(int pedanaPosX) {
        this.pedanaPosX = pedanaPosX;
    }

    public int getPedanaPosY() {
        return pedanaPosY;
    }

    public void setPedanaPosY(int pedanaPosY) {
        this.pedanaPosY = pedanaPosY;
    }
}
