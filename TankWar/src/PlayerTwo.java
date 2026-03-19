import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import java.beans.Transient;

public class PlayerTwo extends Tank {
    public PlayerTwo(String img, int x, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg, String downImg) {
        super(img, x, y, gamePanel, upImg, leftImg, rightImg, downImg);

    }
    boolean left;
    boolean down;
    boolean right;
    boolean up;
    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                left=true;
                break;
            case KeyEvent.VK_DOWN:
                down=true;
                break;
            case KeyEvent.VK_RIGHT:
                right=true;
                break;
            case KeyEvent.VK_UP:
                up=true;
                break;
            case KeyEvent.VK_K:
                attack();
            default:
                break;
        }
    }


    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
            case KeyEvent.VK_UP:
                up=false;
                break;
            default:
                break;
        }
    }
    public void move(){
        if (left){
            leftward();
        }
        else if  (right){
            rightward();
        }
        else if (up){
            upward();
        }
        else if (down){
            downward();
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        move();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}


