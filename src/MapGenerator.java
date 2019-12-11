import java.awt.*;

public class MapGenerator {

    private int map[][];
    private int row;
    private int col;
    private int brickWidth;
    private int brickHeight;
    private int nBrick;

    public MapGenerator(int row, int col){
        map = new int[row][col];
        this.row = row;
        this.col = col;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                map[r][c] = 1;
            }
        }
        brickWidth  = 540/col;
        brickHeight = 250/row;
        nBrick      = row * col;
    }

    public void drawMap(Graphics2D g){
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(map[r][c] > 0){
                    g.setColor(Color.white);
                    g.fillRect(c * brickWidth + 20, r * brickHeight + 30, brickWidth, brickHeight);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(5));
                    g.drawRect(c * brickWidth + 20, r * brickHeight + 30, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }

    public int getnBrick(){
        return nBrick;
    }

    public void reduceBrick(){
        nBrick--;
    }

    public int getBrickValue(int row, int col){
        return map[row][col];
    }

    public int getBrickWidth(){
        return brickWidth;
    }

    public int getBrickHeight(){
        return brickHeight;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

}
