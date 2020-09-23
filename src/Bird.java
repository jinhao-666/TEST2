import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
    int x,y;//为小鸟图片的旋转坐标
    BufferedImage bird;
    BufferedImage [] birds;
    int index=0;
    int g;//重力加速度
    double t;//间隔时间秒
    double v0;//初始速度 像素/秒
    double vt;//当期时刻速度
    double s;//运动的距离
    double angle;//飞行角度
    double size=26;
    public Bird(int x,int y,int l) throws IOException {
        this.x=x;
        this.y=y;
        bird=ImageIO.read(getClass().getResource("images/player"+l+"_0.png"));
        birds=new BufferedImage[8];
        for(int i=0;i<8;i++) {
            String s="images/player"+l+"_"+i+".png";
            birds[i]=ImageIO.read(getClass().getResource(s));
        }
        g=4;
        t=0.25;
        v0=20;

    }
    public Bird() {
        // TODO 自动生成的构造函数存根
    }
    public void paint(Graphics g)
    {
        //g.drawImage(bird, x, y, null);
        Graphics2D g2d=(Graphics2D)g;
        g2d.rotate(angle,x,y);
        int x1=x-bird.getWidth()/2;//绘制图片
        int y1=y-bird.getHeight()/2;
        g.drawImage(bird, x1, y1, null);
        g2d.rotate(-angle, x, y);
    }
    public void step() {
        double vt1=vt;//当前速度
        double v=vt1-g*t;//计算垂直上抛运动，经过时间t以后的速度
        vt=v;//作为下一次计算位移时的初速度
        s=vt1*t-0.5*g*t*t;//运行距离
        y=y-(int)s;
        angle=-Math.atan(s/15);
        index++;
        bird=birds[index/8%8];//放慢8倍index=0,1,2,3,4,5,6，7,8,而index/8%6=00000000111111
    }
    public void flappy() {
        vt=v0;
        new AudioPlayWave("src/images/飞.wav").start();
    }
    public boolean hit(Column col1,Column col2,Ground gro) {
        if(y-size/2<=0) {
            new AudioPlayWave("src/images/命中.wav").start();
            return true;
        }
        if(y+size/2>=gro.y) {
            new AudioPlayWave("src/images/命中.wav").start();
            return true;
        }
        if(hitColumn(col1)||hitColumn(col2)) {
            new AudioPlayWave("src/images/命中.wav").start();
            return true;
        }
        return false;
    }
    public boolean hitColumn(Column col) {
        if(x>col.x-size/2&&x<col.x+col.width+size/2) {
            if(y<col.yup+col.heightgap-size/2&&y>col.yup+420+size/2) {
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean pass(Column col1,Column col2) {
        return col1.x==x||col2.x==x;//积分专用
    }
    public boolean hitCoin(GoldCoin coin) {
        if(x+size/2>coin.x&&x<coin.x+coin.size&&y>coin.y-coin.size&&y-size/2<coin.y) {
            new AudioPlayWave("src/images/金币.wav").start();
            return true;
        }
        return false;
    }
}


