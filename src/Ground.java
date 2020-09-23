import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Ground {
    BufferedImage ground;
    int x,y;
    public Ground() throws IOException {
        // TODO 自动生成的构造函数存根
        ground=ImageIO.read(getClass().getResource("images/ground.png"));
        y=448-60;

    }
    public void step() {
        x--;
        if(x<-100) {//重置地面
            x=0;
        }
    }
    public void paint(Graphics g) {
        //绘制图片
        g.drawImage(ground, x, y,null);
    }


}
