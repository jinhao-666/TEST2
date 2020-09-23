import client.ClientMain;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class MyWindow extends JFrame
{
    //创建一个容器
    Container cont;
    JPanel panel=new JPanel();
    //创建背景面板。
    JLabel bgi;
    public MyWindow()
    {
//        cont = this.getContentPane();
        this.setLocationRelativeTo(null);
        this.setTitle("Happy Flying Birds");
        panel.setLayout(null);
        JButton button1=new JButton("账号登录");
        JButton button2=new JButton("游客模式");
        JButton button3=new JButton("游戏介绍");
        JButton button4=new JButton("荣耀殿堂");
        JButton button5=new JButton("游戏退出");
        Font f=new Font("黑体",Font.BOLD,500);
//        button1.setForeground(Color.yellow);
//        button2.setForeground(Color.yellow);
//        button3.setForeground(Color.yellow);
//        button4.setForeground(Color.yellow);
//        button5.setForeground(Color.yellow);

        button1.setFont(f);
        button1.setBounds(190,50,100,40);
        button2.setBounds(190,130,100,40);
        button3.setBounds(190,210,100,40);
        button4.setBounds(190,290,100,40);
        button5.setBounds(190,370,100,40);
//        button1.setContentAreaFilled(false);
        button1.setBorder(null);
        button1.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
//        button2.setContentAreaFilled(false);
        button2.setBorder(null);
        button2.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
//        button3.setContentAreaFilled(false);
        button3.setBorder(null);
        button3.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
//        button4.setContentAreaFilled(false);
        button4.setBorder(null);
        button4.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
//        button5.setContentAreaFilled(false);
        button5.setBorder(null);
        button5.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.setOpaque(false);
        //Image im=new Image(icon);
        //将图片放入label中
        JLabel label = new JLabel()
        {
            protected void paintComponent(Graphics g)
            {
                ImageIcon icon=new ImageIcon("src/images/background1.jpeg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(), icon.getImageObserver());
            }
        };

        this.setBounds(300,200,500,500);
        label.setBounds(0,0,getWidth(),getHeight());
        panel.add(label);
        this.add(panel);
        setLocationRelativeTo(null);
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LoginWidget loginwidget=new LoginWidget();
                loginwidget.setVisible(true);
                setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                TravelWidget travel = new TravelWidget();
                travel.setVisible(true);
                setVisible(false);
            }
        });
        button3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GameIntroduce introduce = new GameIntroduce();
                introduce.setVisible(true);
//                ClientMain cmain = new ClientMain();
//                cmain.room();
            }
        });
        button4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GlorySort gloryWidget = new GlorySort();
                gloryWidget.setVisible(true);
            }
        });
        button5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
//        this.add(cont);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }
    public static void main(String[] args)
    {
        MyWindow frame=new MyWindow();
//        getBackgroundPicture(bglabel,panel);
        frame.setVisible(true);
    }

}

