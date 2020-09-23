import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TravelWidget extends JFrame
{


    private JPanel panel = new JPanel();

    public TravelWidget()
    {
        JLabel label = new JLabel()
        {
            protected void paintComponent(Graphics g)
            {
                ImageIcon icon = new ImageIcon("src/images/select.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        //设置按钮，包含单人游戏，双人游戏，社区模式
        JButton button1 = new JButton("单人游戏");
//        button1.setContentAreaFilled(false);
        JButton button2 = new JButton("双人游戏");
        button1.setBounds(100,220,100,40);
        button2.setBounds(300,220,100,40);
        this.setTitle("游客模式");
        panel.setLayout(null);
        this.setBounds(0, 0, 500, 500);
        label.setBounds(0, 0, getWidth(), getHeight());

        //按钮点击触发事件
        button1.addActionListener(new ActionListener()//单人游戏
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    setVisible(false);
                    World wd=new World(1,null,null);
                    wd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                } catch (IOException | InterruptedException d) {
                    // TODO 自动生成的 catch 块
                    d.printStackTrace();
                }
            }
        });
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    setVisible(false);
                    World wd=new World(2,null,null);
                    wd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                } catch (IOException | InterruptedException d) {
                    // TODO 自动生成的 catch 块
                    d.printStackTrace();
                }
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(label);
        setResizable(false);
        this.add(panel);
        this.setLocationRelativeTo(null);
        setResizable(false);
    }

}
