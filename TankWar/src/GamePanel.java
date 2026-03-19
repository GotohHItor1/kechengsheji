import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JFrame {
    Image offScreamImage=null;
    int width=600;
    int height=610;

    //添加图片
    Image select=Toolkit.getDefaultToolkit().getImage("images/6.jpg");
    int y=150;
    //添加游戏模式 0游戏未开始 1单人 2双人 4游戏失败 5游戏胜利
    int state=0;
    int a=1;
    int count=0;
    int enemyCount=0;

    //游戏元素列表
    ArrayList<Bullet> bulletList=new ArrayList<Bullet>();
    ArrayList<Bot> botList=new ArrayList<Bot>();
    ArrayList<Bullet> removeList=new ArrayList<Bullet>();
    ArrayList<Tank> playerList=new ArrayList<Tank>();
    ArrayList<Wall> wallList=new ArrayList<Wall>();
    ArrayList<Base>baseList=new ArrayList<Base>();




    //定义玩家1
    PlayerOne playerOne=new PlayerOne("images/3.jpg",125,510,this,"images/3.jpg",
            "images/5.jpg","images/6.jpg","images/4.jpg");

    //定义玩家2
    PlayerTwo playerTwo=new PlayerTwo("images/7.jpg",525,510,this,"images/7.jpg",
            "images/9.jpg","images/10.jpg","images/8.jpg");



    Bot bot=new Bot("images/81.jpg",500,110,this,"images/81.JPG","images/83.JPG",
            "images/84.JPG","images/82.JPG");
    Base base=new Base("images/52.jpg",375,570,this);




    public void launch(){
        setTitle("坦克大战");
        setSize(width,height);
        setLocationRelativeTo(null);//屏幕居中
        setDefaultCloseOperation(3);
        setResizable(false);
        setVisible(true);
        this.addKeyListener(new GamePanel.KeyMonitor());


        //添加围墙
        for (int i=0;i<14;i++){
            wallList.add(new Wall("images/54.jpg",50+i*50,170,this));
        }
        wallList.add(new Wall("images/54.jpg",305,560,this));
        wallList.add(new Wall("images/54.jpg",305,500,this));
        wallList.add(new Wall("images/54.jpg",365,500,this));
        wallList.add(new Wall("images/54.jpg",425,500,this));
        wallList.add(new Wall("images/54.jpg",425,560,this));
        baseList.add(base);






        //添加电脑坦克
        while (true){
            //游戏胜利判定
            if (botList.size()==0 && enemyCount==10){
                state=5;
            }
            if (playerList.size()==0 && (state==1 || state==2)||baseList.size()==0){
                state=4;
            }
            if (count%100==1 && enemyCount<10 && (state==1 || state==2))
            {
                Random random=new Random();
                int rnum=random.nextInt(800);
                botList.add(new Bot("images/81.jpg",rnum,110,this,
                        "images/81.jpg","images/83.jpg",
                        "images/84.jpg","images/82.jpg"));
                enemyCount++;
            }
            repaint();

            try{
                Thread.sleep(25);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //paint()方法
    @Override
    public void paint(Graphics g){
        //创建容器一样大小的Image图片
        if (offScreamImage==null){
            offScreamImage=this.createImage(width,height);
        }
        //获取图片画笔
        Graphics gImage=offScreamImage.getGraphics();
        gImage.setColor(Color.GRAY);
        gImage.fillRect(0,0,width,height);
        gImage.setColor(Color.blue);
        gImage.setFont(new Font("仿宋",Font.BOLD,50));
        if (state==0){
            gImage.drawString("选择游戏模式",220,100);
            gImage.drawString("单人模式",220,200);
            gImage.drawString("双人模式",220,300);
            gImage.drawString("按1，2选择模式",100,400);
            gImage .drawString("按回车开始游戏",100,500);
            gImage.drawImage(select,160,y,null);
        }
        else if (state==1 || state==2){
            gImage.setFont(new Font("仿宋",Font.BOLD,30));
            gImage.setColor(Color.RED);
            gImage.drawString("剩余敌人"+botList.size(),0,50);
            //绘制玩家
            for (Tank player:playerList){
                player.paintSelf(gImage);
            }
            //发射子弹
            for (Bullet bullet:bulletList){
                bullet.paintSelf(gImage);
            }
            bulletList.removeAll(removeList);
            for(Bot bot:botList){
                bot.paintSelf(gImage);
            }
            for (Wall wall:wallList){
                wall.paintSelf(gImage);
            }
            for (Base base:baseList){
                base.paintSelf(gImage);
            }
            count++;
        }
        else if (state==3) {
            gImage.drawString("游戏暂停",220,200);
        }
        else if (state==5) {
            gImage.drawString("游戏胜利",220,200);
        }
        else if (state==4) {
            gImage.drawString("游戏失败",220,200);
        }
        g.drawImage(offScreamImage,0,0,null);


    }
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            switch(key){
                case KeyEvent.VK_1:
                    a=1;
                    y=150;
                    break;
                case KeyEvent.VK_2:
                    a=2;
                    y=250;
                    break;
                case KeyEvent.VK_ENTER:
                    state=a;
                    playerList.add(playerOne);
                    //playerTwo
                    if (state==2){
                        playerList.add(playerTwo);
                        playerTwo.alive=false;
                    }
                    playerOne.alive=true;


                    break;
                case KeyEvent.VK_P:
                    if (state!=3){
                        a=state;
                        state=3;
                    }
                    else {
                        state=a;
                        if (a==0){
                            a=1;
                        }
                    }
                default:
                    playerOne.keyPressed(e);
                    playerTwo.keyPressed(e);
            }
        }
        //松开键盘
        @Override
        public void keyReleased(KeyEvent e){
            playerOne.keyReleased(e);
            playerTwo.keyReleased(e);
        }
    }








    //main函数
    public static void main(String args[]){
        GamePanel gp=new GamePanel();
        gp.launch();
    }



}
