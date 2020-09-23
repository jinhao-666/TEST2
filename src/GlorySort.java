import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GlorySort extends JFrame {
    private JPanel panel = new JPanel();

    public GlorySort()
    {
        Font font = new Font("宋体", Font.BOLD, 25);
        JLabel label = new JLabel()
        {
            protected void paintComponent(Graphics g)
            {
                ImageIcon icon = new ImageIcon("src/images/sort.png");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), icon.getImageObserver());
            }
        };
        //设置label的大小
        //数据库读取前三名开始
        Jdbcs d = new Jdbcs();
        ResultSet sort= d.sort();
        int a = 0;
        // 处理结果集
        String[] gloryName = new String[3];
        String[] gloryCards = new String[3];

        try
        {
            while (sort.next()&&a < 3)
            {
                gloryName[a] = sort.getString("username");
                gloryCards[a] = sort.getString("cards");
                a++;
            }
        }
        catch (SQLException e)
        {

            e.printStackTrace();
        }
        //排行榜名字位置
        JLabel label1 = new JLabel(gloryName[0]);//number1 name
        JLabel label2 = new JLabel(gloryCards[0]);//number1 cards
        JLabel label3 = new JLabel(gloryName[1]);//number2 name
        JLabel label4 = new JLabel(gloryCards[1]);//number2 cards
        JLabel label5 = new JLabel(gloryName[2]);//number3 name
        JLabel label6 = new JLabel(gloryCards[2]);//number3 cards

        label1.setBounds(105, 300, 80, 80);
        label2.setBounds(120, 510, 80, 80);
        label3.setBounds(470, 300,80, 80);
        label4.setBounds(490, 510, 80, 80);
        label5.setBounds(840, 320, 80, 80);
        label6.setBounds(850, 510, 80, 80);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
//        label1.setForeground(Color.red);
        label2.setForeground(Color.yellow);
//        label3.setForeground(Color.red);
        label4.setForeground(Color.yellow);
//        label5.setForeground(Color.red);
        label6.setForeground(Color.yellow);
        this.setTitle("荣耀殿堂");
        panel.setLayout(null);
        this.setBounds(0, 0, 1000, 620);
        label.setBounds(0, 0, getWidth(), getHeight());
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label);
        this.add(panel);
        this.setLocationRelativeTo(null);
        setResizable(false);
    }
}

