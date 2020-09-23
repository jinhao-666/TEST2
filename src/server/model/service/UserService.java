package server.model.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import server.DataBuffer;

import common.model.entity.User;
import common.util.IOUtil;

/** 用户操作相关的业务逻辑类 */
public class UserService {
	private static int idCount = 4; //id
	
	/** 新增用户 */
	public void addUser(User user){
		user.setId(++idCount);
		List<User> users = loadAllUser();
		users.add(user);
		saveAllUser(users);
	}
	
	/** 用户登录 */
	public User login(long id, String password){
		User result = null;
		List<User> users = loadAllUser();
		for (User user : users) {
			if(id == user.getId() && password.equals(user.getPassword())){
				result = user;
				break;
			}
		}
		return result;
	}
	
	/** 根据ID加载用户 */
	public User loadUser(long id){
		User result = null;
		List<User> users = loadAllUser();
		for (User user : users) {
			if(id == user.getId()){
				result = user;
				break;
			}
		}
		return result;
	}
	
	
	/** 加载所有用户 */
	@SuppressWarnings("unchecked")
	public List<User> loadAllUser() {
		List<User> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(
					new FileInputStream(
							DataBuffer.configProp.getProperty("dbpath")));
			
			list = (List<User>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtil.close(ois);
		}
		return list;
	}
	
	private void saveAllUser(List<User> users) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(
						new FileOutputStream(
								DataBuffer.configProp.getProperty("dbpath")));
			//写回用户信息
			oos.writeObject(users);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtil.close(oos);
		}
	}
	

	
	/** 初始化几个测试用户 */
	public void initUser(){
		User user = new User("123", "卓世龙", 'f', 0);
		user.setId(1);
		
		User user2 = new User("123", "张克辉", 'm', 1);
		user2.setId(2);
		
		User user3 = new User("123", "靳浩", 'm', 2);
		user3.setId(3);

		User user4 = new User("123", "臧方龙", 'f', 3);
		user4.setId(4);

		List<User> users = new CopyOnWriteArrayList<User>();
		users.add(user);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		
		this.saveAllUser(users);
	}
	
	public static void main(String[] args){
		new UserService().initUser();
		List<User> users = new UserService().loadAllUser();
		for (User user : users) {
			System.out.println(user);
		}
	}
}
