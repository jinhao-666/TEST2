import javax.swing.*;
import java.awt.*;

public class GameIntroduce extends JFrame
{


    private JPanel panel = new JPanel();

    public GameIntroduce()
    {
        JLabel label = new JLabel()
        {
            protected void paintComponent(Graphics g)
            {
                ImageIcon icon = new ImageIcon("src/images/introduce.png");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        //设置label的大小
        this.setTitle("游戏介绍");
        panel.setLayout(null);
        this.setBounds(0, 0, 1000, 600);
        label.setBounds(0, 0, getWidth(), getHeight());
        panel.add(label);
        this.add(panel);
        this.setLocationRelativeTo(null);
        setResizable(false);
    }
}

