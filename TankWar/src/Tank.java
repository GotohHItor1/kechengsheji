import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject{
    public int width=40;
    public int height=50;
    private int speed=3;
    public Direction direction=Direction.UP;
    public boolean alive=false;
    //定义坦克四个方向的图片
    private String upImg;
    private String leftImg;
    private String rightImg;
    private String downImg;



    //攻击冷却状态
    private boolean attackCoolDown=true;
    //攻击冷却时间：1000ms
    private int attackCoolDownTime=1000;




    public Tank(String img,int x,int y,GamePanel gamePanel,String upImg,
                String leftImg,String rightImg,String downImg){
        super(img,x,y,gamePanel);
        this.upImg=upImg;
        this.leftImg=leftImg;
        this.rightImg=rightImg;
        this.downImg=downImg;
    }
    public void leftward(){
        setImg(leftImg);
        direction=Direction.UP;
        if (!hitWall(x-speed,y) && !moveToBorder(x-speed,y)){
            this.x-=speed;
        }
    }
    public void upward(){
        setImg(upImg);
        direction=Direction.LEFT;
        if (!hitWall(x,y-speed) && !moveToBorder(x,y-speed)){
            this.y-=speed;
        }
    }
    public void rightward(){
        setImg(rightImg);
        direction=Direction.RIGHT;
        if (!hitWall(x+speed,y) && !moveToBorder(x+speed,y)){
            this.x+=speed;
        }
    }
    public void downward(){
        setImg(downImg);
        direction=Direction.DOWN;
        if (!hitWall(x,y+speed) && !moveToBorder(x,y+speed)){
            this.y+=speed;
        }
    }
    public void setImg(String img){
        this.img=Toolkit.getDefaultToolkit().getImage(img);
    }


    public void attack(){
        if (attackCoolDown && alive){
            Point p=this.getHeadPoint();
            Bullet bullet=new Bullet("images/1.jpg",p.x,p.y,this.gamePanel,direction);
            this.gamePanel.bulletList.add(bullet);
        }
        new AttackCD().start();

    }

    //新线程控制坦克攻击冷却
    class AttackCD extends Thread{
        public void run(){
           attackCoolDown=false;
           try{
               Thread.sleep(attackCoolDownTime);
           }catch (Exception e){
               e.printStackTrace();
           }
           attackCoolDown=true;
           this.stop();
        }
    }


    public  Point getHeadPoint(){
        switch (direction){
            case LEFT:
                return new Point(x,y+height/2);
            case RIGHT:
                return new Point(x+width,y+height/2);
            case UP:
                return new Point(x+width/2,y);
            case DOWN:
                return new Point(x+width/2,y+height);
            default:
                return null;
        }
    }




    public boolean hitWall(int x,int y){
        ArrayList<Wall> walls=this.gamePanel.wallList;
        Rectangle next=new Rectangle(x,y,width,height);
        for (Wall wall:walls){
            if (next.intersects(wall.getRec())){
                return true;
            }
        }
        return false;
    }


    public boolean moveToBorder(int x,int y){
        if (x<0){
            return true;
        }
        else if (x+width>this.gamePanel.getWidth()){
            return true;
        }
        else if (y<0){
            return true;
        }
        else if (y+height>this.gamePanel.getHeight()){
            return true;

        }
        return false;
    }

    @Override
    public abstract void paintSelf(Graphics g);





    @Override
    public abstract Rectangle getRec();
}
