import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginWidget extends JFrame implements ActionListener {
    private JPanel panel = new JPanel();
    private JLabel username = new JLabel("用户名");
    private JLabel password = new JLabel("密    码");
    private JTextField nametext = new JTextField();
    private JPasswordField passtext = new JPasswordField();


    public JButton login = new JButton("登录");
    public JButton register = new JButton("注册");
    public JButton updatepassword = new JButton("修改密码");
    public JButton deleteuser = new JButton("删除用户");


    public LoginWidget() {
        Font font = new Font("宋体", Font.BOLD, 12);
        this.setTitle("欢迎登录Happy Flying Birds");
        panel.setLayout(null);
        username.setBounds(20, 20, 60, 30);
        nametext.setBounds(90, 20, 140, 30);
        password.setBounds(20, 60, 60, 30);
        passtext.setBounds(90, 60, 140, 30);
        login.setBounds(30, 120, 90, 20);
        register.setBounds(140, 120, 90, 20);
        updatepassword.setBounds(30, 150, 90, 20);
        deleteuser.setBounds(140, 150, 90, 20);
        panel.add(username);
        panel.add(nametext);
        panel.add(password);
        panel.add(passtext);
        panel.add(login);
        panel.add(register);
        panel.add(updatepassword);
        panel.add(deleteuser);

        passtext.setFont(font);
        register.setFont(font);
        updatepassword.setFont(font);
        deleteuser.setFont(font);

        login.addActionListener(this);
        register.addActionListener(this);
        updatepassword.addActionListener(this);
        deleteuser.addActionListener(this);


        this.setSize(300, 250);
        JLabel label = new JLabel()
        {
            protected void paintComponent(Graphics g)
            {
                ImageIcon icon=new ImageIcon("src/images/login.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(), icon.getImageObserver());
            }
        };
        //设置label的大小
        label.setBounds(0,0,getWidth(),getHeight());
        panel.add(label);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {

        new LoginWidget();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == login)
        {
            denglu();
        } else if (arg0.getSource() == register)
        {
            zhuce();
        } else if (arg0.getSource() == updatepassword)
        {
            updatepass();
        } else if (arg0.getSource() == deleteuser)
        {
            deleteuser();
        }

    }

    //登录按钮的事件处理函数
    public void denglu()
    {
        Jdbcs d = new Jdbcs();
        String username = nametext.getText();
        String password = passtext.getText();
        if (d.compare(username, password))
        {
            JOptionPane.showMessageDialog(null, "登录成功！");
            super.setVisible(false);
            GameSelection selection = new GameSelection(username,password);
            selection.setVisible(true);
        }
    }

    //注册按钮触发后的事件处理函数
    public void zhuce()
    {
        Jdbcs d = new Jdbcs();
        String username = nametext.getText();
        String password = passtext.getText();
        d.insert(username, password);
    }

    //修改密码按钮触发后的事件处理函数
    public void updatepass()
    {
        panel.setEnabled(false);
        JFrame frame1 = new JFrame("密码修改");
        frame1.setSize(250, 200);
        JPanel updatepass = new JPanel();
        JLabel namelab1 = new JLabel("用户名");
        JLabel passlab1 = new JLabel("旧密码");
        JLabel newpasslab = new JLabel("新密码");
        JTextField nametext1 = new JTextField("" + nametext.getText());
        JPasswordField passtext1 = new JPasswordField();
        JPasswordField newpasstext = new JPasswordField();
        JButton ok = new JButton("修改");
        JButton resert = new JButton("重置");

        updatepass.setLayout(null);

        namelab1.setBounds(5, 5, 70, 20);
        nametext1.setBounds(80, 5, 120, 20);
        passlab1.setBounds(5, 30, 70, 20);
        passtext1.setBounds(80, 30, 120, 20);
        newpasslab.setBounds(5, 60, 70, 20);
        newpasstext.setBounds(80, 60, 120, 20);
        ok.setBounds(10, 110, 60, 20);
        resert.setBounds(90, 110, 60, 20);

        updatepass.add(namelab1);
        updatepass.add(nametext1);
        updatepass.add(passlab1);
        updatepass.add(passtext1);
        updatepass.add(newpasslab);
        updatepass.add(newpasstext);
        updatepass.add(ok);
        updatepass.add(resert);
        JLabel label2 = new JLabel()
        {
            protected void paintComponent(Graphics g)
            {
                ImageIcon icon=new ImageIcon("src/images/change.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(), icon.getImageObserver());
            }
        };
        label2.setBounds(0,0,frame1.getWidth(),frame1.getHeight());
        updatepass.add(label2);
        frame1.add(updatepass);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        frame1.setResizable(false);

        ok.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                Jdbcs d = new Jdbcs();
                String username = nametext1.getText();
                String password1 = passtext1.getText();
                String newpassword = newpasstext.getText();
                if (d.update(username, password1, newpassword))
                {
                    frame1.setVisible(false);
                }
            }
        });
        //重置文本框 里的内容
        resert.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {

                nametext1.setText("");
                passtext1.setText("");
                newpasstext.setText("");
            }
        });
    }

    //删除用户按钮触发后的事件处理函数
    public void deleteuser()
    {
        String username = nametext.getText();
        String password = passtext.getText();
        Jdbcs s = new Jdbcs();
        s.delete(username, password);
    }

}
