import java.awt.*;
import java.util.Random;

public class Bot extends Tank{

    public Bot(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel, upImg, leftImg, rightImg, downImg);
    }



    int movetime=0;
    public void go(){
        attack();
        if (movetime>=100){
            direction=getRandomDirection();
            movetime=0;
        }
        else{
            movetime++;
        }
        switch (direction){
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;

        }
    }

    @Override
    public void attack() {
        Point p=getHeadPoint();
        Random random=new Random();
        int rnum= random.nextInt(100);
        if (rnum<4){
            this.gamePanel.bulletList.add(new EnemyBullet("images/2.jpg",p.x,p.y,this.gamePanel,direction));
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
    public Direction getRandomDirection(){
        Random random=new Random();
        int rnum=random.nextInt(4);
        switch (rnum){
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return null;
        }


    }
}

