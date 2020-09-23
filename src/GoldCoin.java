import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class GoldCoin {
    BufferedImage goldcoin;
    Random random;
    int width,height;
    int x,y;
    int size=10;
    boolean getcoin;
    public GoldCoin() throws IOException {
        // TODO 自动生成的构造函数存根
        goldcoin=ImageIO.read(getClass().getResource("images/金牌.png"));
        width=goldcoin.getWidth();
        height=goldcoin.getHeight();
        x=999;
        y=999;

    }
    void TrackChange() {
        x=999;
        y=999;
    }
    void paint(Graphics g) {
        g.drawImage(goldcoin,x,y,null);
    }
    public void step(Column col) {
        x=col.x+col.width/2;
        y=col.yup+col.heightgap/2+col.heightup/2;
        if(x<25) {//重置
            x=999;
            y=999;
            getcoin=false;
        }
    }

}
