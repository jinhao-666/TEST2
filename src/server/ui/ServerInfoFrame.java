package server.ui;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import common.model.entity.User;
import server.DataBuffer;
import server.model.service.UserService;

/** 服务器信息窗体 */
public class ServerInfoFrame extends JFrame {
	private static final long serialVersionUID = 6274443611957724780L;
	
	public ServerInfoFrame() {
		init();
		loadData();
		setVisible(true);
	}
	
	public void init(){  //初始化窗体
		this.setTitle("服务器启动");//设置服务器启动标题
		this.setBounds((DataBuffer.screenSize.width - 500)/2, 
				(DataBuffer.screenSize.height - 375)/2, 500, 375);
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		panel.setBorder(BorderFactory.createTitledBorder(border, 
				"服务器监控", TitledBorder.LEFT,TitledBorder.TOP));
		this.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("服务器当前监听在: " 
				+ DataBuffer.serverSocket.getLocalPort() + " 端口      ");
		panel.add(label);
		JButton exitBtn = new JButton("关闭服务器");//关闭关闭服务器按钮
		panel.add(exitBtn);
		
		//使用服务器缓存中的TableModel
		JTable onlineUserTable = new JTable(DataBuffer.onlineUserTableModel);
		JTable registedUserTable = new JTable(DataBuffer.registedUserTableModel);
		
		//选项卡
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("在线用户列表", new JScrollPane(onlineUserTable));
		tabbedPane.addTab("已注册用户列表", new JScrollPane(registedUserTable));
		tabbedPane.setTabComponentAt(0, new JLabel("在线用户列表"));
		this.add(tabbedPane, BorderLayout.CENTER);
		
		final JLabel stateBar = new JLabel("", SwingConstants.RIGHT);
		stateBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//用定时任务来显示当前时间
		new java.util.Timer().scheduleAtFixedRate(
				new TimerTask(){
					DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
					public void run() {
						stateBar.setText("当前时间：" + df.format(new Date()) + "  ");
					}
				}, 0, 1000);
		this.add(stateBar, BorderLayout.SOUTH); //把状态栏添加到窗体的南边
		
		//关闭窗口
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				logout();
			}
		});
		
		/* 添加关闭服务器按钮事件处理方法 */
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				logout();
			}
		});
	}
	
	/** 把所有已注册的用户信息加载到RegistedUserTableModel中 */
	private void loadData(){
		List<User> users = new UserService().loadAllUser();
		for (User user : users) {
			DataBuffer.registedUserTableModel.add(new String[]{
				String.valueOf(user.getId()),
				user.getPassword(),
				user.getNickname(),
				String.valueOf(user.getSex())
			});
		}
	}

	/** 关闭服务器 */
	private void logout() {
		int select = JOptionPane.showConfirmDialog(ServerInfoFrame.this, 
				"确定关闭吗？\n\n关闭服务器将中断与所有客户端的连接!", 
				"关闭服务器", 
				JOptionPane.YES_NO_OPTION);
		//如果用户点击的是关闭服务器按钮时会提示是否确认关闭。
		if (select == JOptionPane.YES_OPTION) {
			System.exit(0);//退出系统
		}else{
			//覆盖默认的窗口关闭事件动作
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		}
	}
}