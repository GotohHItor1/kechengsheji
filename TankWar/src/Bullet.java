import java.awt.*;
import java.util.ArrayList;

public class Bullet extends GameObject{
    int width=10;
    int height=10;
    int speed=7;
    Direction direction;

    public Bullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel);
        this.direction = direction;
    }

    public void leftward(){
        x-=speed;
    }
    public void rightward(){
        x+=speed;
    }
    public void upward(){
        y-=speed;
    }
    public void downward(){
        y+=speed;
    }
    public void go(){
        switch (direction){
            case UP:
                upward();
                break;
            case LEFT:
                leftward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
        }
        this.hitWall();
        this.moveToBorder();
        this.hitBase();
    }


    public  void hitBot(){
        ArrayList<Bot> bots=this.gamePanel.botList;
        for (Bot bot:bots){
            if (this.getRec().intersects(bot.getRec())){
                this.gamePanel.botList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }

    }



    public  void hitBase(){
        ArrayList<Base> baseList=this.gamePanel.baseList;
        for (Base base:baseList){
            if (this.getRec().intersects(base.getRec())){
                this.gamePanel.baseList.remove(base);
                this.gamePanel.removeList.add(this);
                break;
            }
        }

    }


    public void hitWall(){
        ArrayList<Wall> walls=this.gamePanel.wallList;
        for (Wall wall:walls){
            if (this.getRec().intersects(wall.getRec())){
                this.gamePanel.wallList.remove(wall);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }
    public void moveToBorder(){
        if (x<0 || x+width>this.gamePanel.getWidth()){
            this.gamePanel.removeList.add(this);
        }
        if (y<0 || y+height>gamePanel.getHeight()){
            this.gamePanel.removeList.add(this);
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        this.go();
        this.hitBot();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
