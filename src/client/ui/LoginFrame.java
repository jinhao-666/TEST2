package client.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import client.DataBuffer;
import client.util.ClientUtil;
import common.model.entity.*;


/** 登录窗体 */
public class LoginFrame extends JFrame {
	private static final long serialVersionUID = -3426717670093483287L;

	private JTextField idTxt;
	private JPasswordField pwdFld;
	
	public LoginFrame(){
		this.init();
		setVisible(true);
	}


	public void init(){
		this.setTitle("Happy Fly Bird登录");
		this.setSize(330, 230);
		//设置默认窗体在屏幕中央
		int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);
		this.setResizable(false);

		this.getContentPane().setLayout(null);
		JPanel imPanel = (JPanel)(this.getContentPane());
		imPanel.setOpaque(false);//将内容面板设为透明
		imPanel.setLayout(new BorderLayout());
		ImageIcon iconbg = new ImageIcon("src/images/bg1.jpg");
		JLabel labelbg = new JLabel(iconbg);//往一个标签中加入图片
		labelbg.setBounds(0, 0, this.getWidth(), this.getHeight());
		//iconbg.setImage(iconbg.getImage().getScaledInstance(labelbg.getWidth(), labelbg.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
		this.getLayeredPane().add(labelbg, Integer.valueOf(Integer.MIN_VALUE));//



		//把Logo放置到JFrame的北边
		Icon icon = new ImageIcon("images/logo.png");
		JLabel label = new JLabel(icon);
		this.getContentPane().add(label, BorderLayout.NORTH);

		//登录信息面板
		JPanel mainPanel = new JPanel();
         mainPanel.setOpaque(false);
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		mainPanel.setBorder(BorderFactory.createTitledBorder(border, "输入登录信息", TitledBorder.CENTER,TitledBorder.TOP));
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JLabel nameLbl = new JLabel("账号:");
		nameLbl.setBounds(50, 30, 40, 22);
		mainPanel.add(nameLbl);
		idTxt = new JTextField();
		idTxt.setBounds(95, 30, 150, 22);
		idTxt.requestFocusInWindow();//用户名获得焦点
		mainPanel.add(idTxt);
		
		JLabel pwdLbl = new JLabel("密码:");
		pwdLbl.setBounds(50, 60, 40, 22);
		mainPanel.add(pwdLbl);
		pwdFld = new JPasswordField();
		pwdFld.setBounds(95, 60, 150, 22);
		mainPanel.add(pwdFld);
		
		//按钮面板放置在JFrame的南边
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		this.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new BorderLayout());
		btnPanel.setBorder(new EmptyBorder(2, 8, 4, 8)); 

		JButton registeBtn = new JButton("注册");
		btnPanel.add(registeBtn, BorderLayout.WEST);
		JButton submitBtn = new JButton("登录");
		btnPanel.add(submitBtn, BorderLayout.EAST);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);



		//关闭窗口
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				Request req = new Request();
				req.setAction("exit");
				try {
					ClientUtil.sendTextRequest(req);
				} catch (IOException ex) {
					ex.printStackTrace();
				}finally{
					System.exit(0);
				}
			}
		});
		
		//注册
		registeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new RegisterFrame();  //打开注册窗体
			}
		});
		
		//"登录"
		submitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}

	/** 登录 */
	@SuppressWarnings("unchecked")
	private void login() {
		if (idTxt.getText().length() == 0 
				|| pwdFld.getPassword().length == 0) {
			JOptionPane.showMessageDialog(LoginFrame.this, 
					"账号和密码是必填的",
					"输入有误",JOptionPane.ERROR_MESSAGE);
			idTxt.requestFocusInWindow();
			return;
		}
		
		if(!idTxt.getText().matches("^\\d*$")){
			JOptionPane.showMessageDialog(LoginFrame.this, 
					"账号必须是数字",
					"输入有误",JOptionPane.ERROR_MESSAGE);
			idTxt.requestFocusInWindow();
			return;
		}
		
		Request req = new Request();
		req.setAction("userLogin");
		req.setAttribute("id", idTxt.getText());
		req.setAttribute("password", new String(pwdFld.getPassword()));
		
		//获取响应
		ResponseStatus.Response response = null;
		try {
			response = ClientUtil.sendTextRequest(req);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(response.getStatus() == ResponseStatus.OK){
			//获取当前用户
			User user2 = (User)response.getData("user");
			if(user2!= null){ //登录成功
				DataBuffer.currentUser = user2;
				//获取当前在线用户列表
				DataBuffer.onlineUsers = (List<User>)response.getData("onlineUsers");
				
				LoginFrame.this.dispose();
				new ChatFrame();  //打开聊天窗体
			}else{ //登录失败
				String str = (String)response.getData("msg");
				JOptionPane.showMessageDialog(LoginFrame.this, 
							str,
							"登录失败",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(LoginFrame.this, 
					"服务器内部错误，请稍后再试！！！","登录失败",JOptionPane.ERROR_MESSAGE);
		}
	}
}