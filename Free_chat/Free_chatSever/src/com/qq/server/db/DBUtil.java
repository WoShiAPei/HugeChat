package com.qq.server.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Common.Message;
import Common.MessageType;
import Common.User;

public class DBUtil {
	private final static String url = "jdbc:mysql://localhost:3306/hugechat";// url:// ���ݿ��ַ// jdbc:mysql://��������IP:�˿ں�//���ݿ�����
	private final static String username = "root"; // �û������������Լ���
	private final static String password = "SHUXIN0506";// ������ݿ�����// DriverManager���о�̬����
	private static Connection con;

	static {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password); // ����ֵ��Connection�ӿڵ�ʵ����,��mysql��������
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * �������ǵ����Ӷ���
	 * @return
	 */
	public static Connection getCon() {
		return con;
	}
/**
 * ͨ�����ǵ��û��������õ�����ID
 * @param name
 * @return
 * @throws SQLException
 */
	public static int QueryGetUserID(String name) throws SQLException {
		int ID = 0;
		String sql = "SELECT * FROM users WHERE UserName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			ID = rs.getInt(1);
		}
		rs.close();
		pst.close();
		return ID;
	}
/**
 * ͨ�������û���id�õ������û���
 * @param id
 * @return
 * @throws SQLException
 */
	public static String QueryGetUserName(int id) throws SQLException {//----------------------------------------------------
		String name=null;
		String sql = " SELECT * FROM users WHERE UserId=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			name = rs.getString(2);
		}
		rs.close();
		pst.close();
		return name;
	}

	/**
	 * ͨ�������û���id�õ���������
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static String QueryGetUserPsw(int id) throws SQLException {
			String psw=null;
			String sql = "SELECT * FROM users WHERE UserId=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setObject(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				psw = rs.getString(3);
			}
			rs.close();
			pst.close();
			return psw;
		}

	/**
	 * ������������������ǵ��û����Ƿ��Ѿ���ע�ᣬ����true���Ѿ���ע����
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public static boolean Query(String name) throws SQLException {
		String sql = "SELECT * FROM users WHERE UserName=? "; // �����в���,SQL����еĲ���ȫ�������ʺ�ռλ��
		PreparedStatement pst = con.prepareStatement(sql); // ����Connection�ӿڵķ���prepareStatement,��ȡPrepareStatement�ӿڵ�ʵ����
		pst.setObject(1, name); // ����pst����set����,�����ʺ�ռλ���ϵĲ���
		ResultSet rs = pst.executeQuery();// ���÷���,ִ��SQL,��ȡ�����
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
			if (rs.getString("UserName").equals(name)) {
				return true;
			}
		}
		rs.close();
		pst.close();
		return false;
	}

	/**
	 * ���������������������֤�Ƿ�ɹ���½,��֤�û���
	 *
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static boolean Query(String name, String password) throws SQLException {
		String sql = "SELECT * FROM users WHERE UserName=? "; // �����в���,SQL����еĲ���ȫ�������ʺ�ռλ��
		PreparedStatement pst = con.prepareStatement(sql); // ����Connection�ӿڵķ���prepareStatement,��ȡPrepareStatement�ӿڵ�ʵ����
		pst.setObject(1, name); // ����pst����set����,�����ʺ�ռλ���ϵĲ���
		ResultSet rs = pst.executeQuery();// ���÷���,ִ��SQL,��ȡ�����
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
			if (rs.getString("UserPsw").equals(password)) {
				return true;
			} else {
				return false;
			}
		}
		rs.close();
		pst.close();
		return false;
	}

	/**
	 * ���û���������û�
	 *
	 * @param name
	 * @param password
	 * @throws SQLException
	 */
	public static void Add(String name, String password,int image ) throws SQLException {
		String sql = "INSERT INTO users (UserName,UserPsw,UserIamge)VALUES(?,?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		pst.setObject(2, password);
		pst.setObject(3, image);
		// pst.setObject(1, "shuxin");
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}

//------------------------���Ѳ���-------------------------------------------------------------
/**
 * ͨ���û���id������Ӻ��ѣ��󶨹�ϵ
 * @param User1
 * @param User2
 * @throws SQLException
 */
	public static void AddFriend(int User1,int User2 ) throws SQLException {
		String sql = "INSERT INTO relation  (User1,User2 )VALUES(?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, User1);
		pst.setObject(2, User2);
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}
/**
 * ͨ�������û���id����һ�������ѵ��б����а����˺��ѵ�id,���֣�����
 * @param id
 * @return
 * @throws SQLException
 */
	public static ArrayList<User> QueryAllFriends(int id) throws SQLException{
		ArrayList<User> list=new ArrayList();
		String sql = "SELECT * FROM relation  WHERE User1=? OR User2=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
			if (rs.getInt(1)!=id) {
				User  u =new User();
				u.setUsername(QueryGetUserName(rs.getInt(1)));//ͨ��id�ĵ�����
				u.setPasswd(QueryGetUserPsw(rs.getInt(1)));//ͨ��id�õ�����
				u.setUserId(rs.getInt(1));
				list.add(u);
			}
			if(rs.getInt(2)!=id){
				User  u =new User();
				u.setUsername(QueryGetUserName(rs.getInt(2)));//ͨ��id�ĵ�����
				u.setPasswd(QueryGetUserPsw(rs.getInt(2)));//ͨ��id�õ�����
				u.setUserId(rs.getInt(2));
				list.add(u);
			}
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);
		return list;
	}
/**
 * ��ѯ���к�ָ��idΪ���ѹ�ϵ��id���룬����������list���з���
 * ���ص���һ��û���ظ�id������list
 * ���Ѳ���
 * @param id
 * @return
 * @throws SQLException
 */
	public static  ArrayList QueryRalation(int id) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM relation  WHERE User1=? OR User2=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
			if (rs.getInt(1)!=id) {
				list.add(rs.getInt(1));
			}
			if(rs.getInt(2)!=id){
				list.add(rs.getInt(2));
			}
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);
		return list;
	}

/**
 * �h��list�е����}Ԫ��
 * @param list
 */
	public static void removeDuplicateWithOrder(List list){
		    Set set=new HashSet();
		    List newList=new ArrayList();
		    for(Iterator iter=list.iterator(); iter.hasNext();){
		    	Object element=iter.next();
		    	if(set.add(element))
		    		newList.add(element);
		    }
	  		list.clear();
		    list.addAll(newList);
		}
//------------Ⱥ����------------------------------------------------------------------------
	public static ArrayList<Message> queryQunMessage(String name)throws SQLException{
		ArrayList<Message> list=new ArrayList();
		String sql = "SELECT * FROM message  WHERE QunName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			Message msg=new Message();
			msg.setMesType(MessageType.message_manypeople);
			msg.setSender(rs.getInt(3));
			msg.setCon(rs.getString(5));
			msg.setSendTime(rs.getString(2));
			list.add(msg);
		}

		return list;
	}


	/**
	 * ͨ������Ⱥ�����������в��ҷ���Ⱥ�������û�id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList QueryQun(String name) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE QunName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
				list.add(rs.getInt(2));
				list.add(rs.getInt(3));
				list.add(rs.getInt(4));
				list.add(rs.getInt(5));
				list.add(rs.getInt(6));
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);//Ⱥ��Ҳ����Ҫȥ�����ǵ��ظ�Ԫ��
		return list;
	}
	/**
	 * ͨ��Ⱥ��id���в���,��������Ⱥ���û�����id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList QueryQun(int id) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE QunID=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
				list.add(rs.getInt(2));
				list.add(rs.getInt(3));
				list.add(rs.getInt(4));
				list.add(rs.getInt(5));
				list.add(rs.getInt(6));
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);//Ⱥ��Ҳ����Ҫȥ�����ǵ��ظ�Ԫ��
		return list;
	}
	/**
	 * ͨ���û���id�õ��û��ڵ�����Ⱥ��id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList QueryQunByUserId(int id) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE User1=? OR User2=? OR User3=? OR User4=? OR  User5=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		pst.setObject(3, id);
		pst.setObject(4, id);
		pst.setObject(5, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
				list.add(rs.getInt(1));
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);//Ⱥ��Ҳ����Ҫȥ�����ǵ��ظ�Ԫ��
		return list;
	}
	/**
	 * ͨ���������ǵ��û�ID������һ��Ⱥ���ֵĶ���
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList<String> QueryQunAllNameByUserId(int id) throws SQLException {
		ArrayList<String> list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE User1=? OR User2=? OR User3=? OR User4=? OR  User5=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		pst.setObject(3, id);
		pst.setObject(4, id);
		pst.setObject(5, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
				list.add(rs.getString(7));
		}
		rs.close();
		pst.close();
		removeDuplicateWithOrder(list);//Ⱥ��Ҳ����Ҫȥ�����ǵ��ظ�Ԫ��
		return list;
	}
/**
 * ͨ��Ⱥ���ַ��ض�ӦȺ��ID
 * @param name
 * @return
 * @throws SQLException
 */
	public static int QueryQunIDByName(String name) throws SQLException {
		int ID=0;
		String sql = "SELECT * FROM manyrelation  WHERE QunName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//ͨ����ǰ����ָ���е�ֵ
			// rs.getString (2)//ͨ���鵽��ǰ����ָ�����ֵ���
			ID= rs.getInt(1);
		}
		rs.close();
		pst.close();
		return ID;
	}
/**
 * ��message ���뵽���ǵ�message����
 * @param time���͵�ʱ�䣬��ʽ��XXXX/XX/XX XX-XX ��-��-��-Сʱ-���ӣ�֮��ʹ��split������ֱ����ʾ�Ϳ�����
 * @param sender��Ϣ�ķ�����
 * @param receiver��Ϣ�Ľ�����
 * @param content��Ϣ������
 * @throws SQLException
 */
	public static void AddMessage(String time,int sender,int receiver,String content,String MessageType ) throws SQLException {
		String sql = "INSERT INTO message  (time,sender,receiver,content,MessageType)VALUES(?,?,?,?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, time);
		pst.setObject(2, sender);
		pst.setObject(3, receiver);
		pst.setObject(4, content);
		pst.setObject(5, MessageType);
		// pst.setObject(1, "shuxin");
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}
	/**
	 * ���һ��Ⱥ��Ϣ��String qunname����ʶ
	 * @param time
	 * @param sender
	 * @param content
	 * @param qunname
	 * @param MessageType
	 * @throws SQLException
	 */
	public static void AddQunMessage(String time,int sender,String content,String qunname,String MessageType ) throws SQLException {
		String sql = "INSERT INTO message  (time,sender,content,Qunname,MessageType)VALUES(?,?,?,?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, time);
		pst.setObject(2, sender);
		pst.setObject(3, content);
		pst.setObject(4, qunname);
		pst.setObject(5, MessageType);
		// pst.setObject(1, "shuxin");
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}
/**
 *  ͨ�������û���id,���в�������֮��������¼������һ��LIST����
 * @param User1�û�1ID
 * @param User2�û�2ID
 * @return
 * @throws SQLException
 */
	public static   ArrayList<Common.Message> GetMessage(int User1,int User2)throws SQLException{
		ArrayList<Common.Message> messages=new ArrayList();
		String sql = "SELECT * FROM message  WHERE sender=?  OR sender=? AND receiver=? OR receiver=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, User1);
		pst.setObject(2, User2);
		pst.setObject(3, User1);
		pst.setObject(4, User2);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			Common.Message message=new Common.Message();
			message.setId(rs.getInt(1));
			message.setSender(rs.getInt(3));
			message.setGetter(rs.getInt(4));
			message.setSendTime(rs.getString(2));
			message.setCon(rs.getString(5));
			message.setMesType("3");

			messages.add(message);
		}
		return messages;
	}



}
