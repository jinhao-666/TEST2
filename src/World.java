import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;//发出IO异常的信号
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class World extends JFrame
{
    GamePanel fp;

    public class GamePanel extends JPanel
    {
        /**
         *
         */
//        private static final long serialVersionUID = 6865748790042152200L;
        BufferedImage background;
        BufferedImage startImage;
        Ground ground;
        Column column1;
        Column column2;
        Bird bird[]=new Bird[2];
        int []score=new int[2];//分数统计
        GoldCoin gc1,gc2;
        boolean start; //游戏开始的标志位
        boolean gameover;
        boolean []over=new boolean[2];
        boolean overs;
        boolean getcoin;
        int sign;
        int random;
        int cards;
        int i = 0;
        String username;
        String password;
        public GamePanel(int l,String username,String password) throws IOException
        {
            sign=l;
            this.username = username;
            this.password = password;
            overs=true;
            background=ImageIO.read(getClass().getResource("images/day.png"));
            startImage=ImageIO.read(getClass().getResource("images/down.png"));
            column1=new Column(360);
            column2=new Column(560);
            gc1=new GoldCoin();
            gc2=new GoldCoin();
            for(int i=0;i<sign;i++)
            {
                bird[i]=new Bird(100,190+i*100,i);
                score[i]=0;
            }
            //初始化地面
            ground=new Ground();
        }
        /*重写父类的pain方法来绘制图形*/
        public void paint (Graphics g)
        {
            //绘制背景图片
            g.drawImage(background,0,0,null);
            if(start==true)
            {
                //绘制开始图片
                column1.paint(g);
                column2.paint(g);
                gc1.paint(g);
                gc2.paint(g);
                for(int i=0;i<sign;i++)
                {
                    bird[i].paint(g);
                }
            }
            if(gameover)
            {
                try
                {
                    BufferedImage end=ImageIO.read(getClass().getResource("images/Over.png"));
//                    g.drawImage(end,0,0,null);
                    cards = score[0];
                    Jdbcs d = new Jdbcs();

                    d.updateCards(username, password, cards);
                    i++;
//                    System.out.println(username);


                } catch (IOException e)
                {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }

            if(start==false&&gameover==false)
            {
                //绘制开始图片
                g.drawImage(startImage,0,0,null);
            }
            //绘制地面
            for(int i=0;i<sign;i++)
            {
                g.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
                g.setColor(Color.BLACK);
                g.drawString("score"+(i+1)+"="+score[i], 10+200*i, 40);
                if(gameover)
                {
                    if(username == null)
                    {
                        g.setColor(Color.red);
                        g.drawString("恭喜您获得"+score[0]+"分", 90, 130);
                        g.setColor(Color.white);
                        g.drawString("再来亿局",130,250);
                    }
                    else
                    {
                        g.setColor(Color.red);
                        g.drawString("恭喜"+username+"用户获得"+score[0]+"分", 30, 130);
                        g.setColor(Color.white);
                        g.drawString("再来亿局",130,250);
                    }

                }
                ground.paint(g);
            }
        }


        public void action() throws InterruptedException
        {
            this.setFocusable(true);
            addMouseListener(new MouseListener()
            {

                @Override
                public void mouseClicked(MouseEvent arg0)
                {
                    // TODO 自动生成的方法存根

                }

                @Override
                public void mouseEntered(MouseEvent arg0)
                {
                    // TODO 自动生成的方法存根

                }

                @Override
                public void mouseExited(MouseEvent arg0)
                {
                    // TODO 自动生成的方法存根

                }

                @Override
                public void mousePressed(MouseEvent arg0)
                {
                    // TODO 自动生成的方法存根
                    if(arg0.getButton()==MouseEvent.BUTTON1)
                    {
                        if(gameover==true)
                        {
                            try
                            {

                                column1=new Column(360);
                                column2=new Column(560);
                                gc1=new GoldCoin();
                                gc2=new GoldCoin();
                                for(int i=0;i<sign;i++)
                                {
                                    bird[i]=new Bird(100,190+i*100,i);
                                    score[i]=0;
                                    over[i]=false;
                                }
                                //初始化地面
                                ground=new Ground();
                                start=false;
                                gameover=false;
                            }catch(IOException e1)
                            {
                                e1.printStackTrace();
                            }
                        }
                        start=true;
                        if(over[0]==false)
                            bird[0].flappy();
                    }
                    if(arg0.getButton()==MouseEvent.BUTTON3)
                    {
                        if(sign==2)
                        {
                            if(over[1]==false)
                                bird[1].flappy();
                        }

                    }


                }

                @Override
                public void mouseReleased(MouseEvent arg0)
                {
                    // TODO 自动生成的方法存根

                }

            });
            addKeyListener(new KeyListener()
            {
                public void keyPressed(KeyEvent e)
                {
                    if(e.getKeyCode()==KeyEvent.VK_X)
                    {
                        if(gameover==true)
                        {
                            try
                            {

                                column1=new Column(360);
                                column2=new Column(560);
                                gc1=new GoldCoin();
                                gc2=new GoldCoin();
                                for(int i=0;i<sign;i++)
                                {
                                    bird[i]=new Bird(100,190+i*40,i);
                                    score[i]=0;

                                    over[i]=false;
                                }
                                //初始化地面
                                ground=new Ground();
                                start=false;
                                gameover=false;
                            }catch(IOException e1)
                            {
                                e1.printStackTrace();
                            }
                        }
                        start=true;
                        if(over[0]==false)
                            bird[0].flappy();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_N)
                    {
                        if(sign==2)
                        {
                            if(over[1]==false)
                                bird[1].flappy();
                        }

                    }
                }

                @Override
                public void keyReleased(KeyEvent arg0)
                {
                    // TODO 自动生成的方法存根

                }

                @Override
                public void keyTyped(KeyEvent e)
                {
                    // TODO 自动生成的方法存根
                }
            });
            Thread t = new Thread(new Runnable()
            {

                @Override
                public void run()
                {
                    // TODO 自动生成的方法存根
                    while(true)
                    {
                        overs=true;
                        if(start==true)
                        {
                            column1.step();
                            column2.step();
                            if(column1.ran<100&&column1.getcoin==false)
                            {
                                gc1.step(column1);
                            }
                            if(column2.ran<100&&column2.getcoin==false)
                            {
                                gc2.step(column2);
                            }
                            for(int i=0;i<sign;i++)
                            {
                                bird[i].step();
                                if(over[i]==false)
                                {
                                    //如果不加这个的话就会出现只要有一只鸟还活着就可以无限飞
                                    over[i]=bird[i].hit(column1,column2,ground);

                                }

                                if(bird[i].pass(column1, column2)&&over[i]==false)
                                {
                                    score[i]++;
                                }
                                overs=overs&over[i];
                                if(bird[i].hitCoin(gc1))
                                {
                                    score[i]+=1;
                                    gc1.TrackChange();
                                    column1.getcoin=true;
                                }
                                if(bird[i].hitCoin(gc2))
                                {
                                    score[i]+=1;
                                    gc2.TrackChange();
                                    column2.getcoin=true;
                                }
                            }

                            if(overs)
                            {
                                start=false;
                                gameover=true;
                            }
                        }
                        ground.step();
                        repaint();

                        try
                        {
                            Thread.sleep(1000/60);
                        } catch (InterruptedException e)
                        {
                            // TODO 自动生成的 catch 块
                            e.printStackTrace();
                        }
                        //每个60是重绘一次
                    }
                }

            });
            t.start();
        }
    }

    World(int l,String username,String password) throws IOException, InterruptedException
    {
        setLayout(new BorderLayout());
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,384,50);
        panel.setLayout(null);
        panel.setBackground(Color.green);
        fp = new GamePanel(l, username,password);
        fp.setLayout(null);
        setSize(384,488);
        fp.setBounds(0,30,384,448);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("主菜单");
        button.setBounds(100,0,100,30);
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                MyWindow myWindow = new MyWindow();
                setVisible(false);
                myWindow.setVisible(true);
            }
        });
        JButton button1 = new JButton("上一级");
        button1.setBounds(200,0,100,30);
        button1.setContentAreaFilled(false);
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(username == null) {
                    TravelWidget myWindow = new TravelWidget();
                    setVisible(false);
                    myWindow.setVisible(true);
                }
                else
                {
                    GameSelection selection = new GameSelection(username, password);
                    setVisible(false);
                    selection.setVisible(true);
                }
            }
        });
//        button.revalidate();
        panel.add(button1);
        panel.add(button);
        this.add(panel);
        add(fp);
        setLocationRelativeTo(null);
        setVisible(true);
        fp.action();

    }
    public static void main(String[] args) throws Exception {
        World world = new World(1,null,"3");
        world.show();
    }
}