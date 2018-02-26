/**
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨ���߳�
 */
package com.qq.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.qq.server.db.DBUtil;

import Common.Message;
import Common.MessageType;
import Common.User;
/**
 * ��һ���ฺ��ÿһ�����̵߳Ŀͻ��˶�����м������Լ�������Ϣ�Ĵ���
 * @author SHUXIN
 *���Ӧд ˽�� Ⱥ�� �շ�ͼƬ
 */
public class SerConClientThread  extends Thread{

	Socket s;

	public SerConClientThread(Socket s)
	{
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.s=s;
	}

	public void run()
	{

		while(true)
		{
			//������߳̾Ϳ��Խ��տͻ��˵���Ϣ.
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();

				//�Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if(m.getMesType().equals(MessageType.message_comm_mes))//˽��
				{
					//һ�����ת��.
					//ȡ�ý����˵�ͨ���߳�
					System.out.println("���������ܵ����ǵ�˽�����ݰ�");
					System.out.println(m);
					DBUtil.AddMessage(m.getSendTime(),m.getSender(),m.getGetter(),m.getCon(),m.getMesType());
					ArrayList<Message> list=DBUtil.GetMessage(m.getSender(), m.getGetter());
					System.out.println("���������ص���Ϣ����"+list);
					Message msg=new Message();
					msg.setMesType(MessageType.message_comm_mes);
					msg.setMessage(list);//����һ����Ϣ�ı�־�����ǵ�˽�ķ��أ�ͬʱ�����м��룬֮��ͻ����Ǳ߿��Խ��н��������θ��ĸ����е�

					SerConClientThread sc=ManageClientThread.getClientThread(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(msg);//�����ݰ����ͳ�ȥ

					SerConClientThread sc1=ManageClientThread.getClientThread(m.getGetter());//���͵����ܶ�
					ObjectOutputStream oos1=new ObjectOutputStream(sc1.s.getOutputStream());
					oos1.writeObject(msg);//�����ݰ����ͳ�ȥ
					System.out.println("�����������ݰ����ͳ�����");
				}
				if(m.getMesType().equals(MessageType.message_formessage))//���󷵻�һ����Ϣ�Ķ���
				{
					//һ�����ת��.
					//ȡ�ý����˵�ͨ���߳�
					ArrayList list=DBUtil.GetMessage(m.getSender(), m.getGetter());
					Message msg=new Message();
					msg.setMesType(MessageType.message_formessage);
					msg.setMessage(list);//����һ����Ϣ�ı�־�����ǵ�˽�ķ��أ�ͬʱ�����м��룬֮��ͻ����Ǳ߿��Խ��н��������θ��ĸ����е�

					SerConClientThread sc=ManageClientThread.getClientThread(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(msg);//�����ݰ����ͳ�ȥ
				}
				if(m.getMesType().equals(MessageType.message_manypeople))//Ⱥ�ģ�Ⱥ���Ǹ�getter����Ⱥ�ĵ�id���п���
				{
					//message 	�е�getter��Ⱥ�ĵ�ʱ����Ǳ�ʾ����Ⱥ��iD
					//���ڷ������ĺ��Ѹ��ÿͻ��˷���.
					DBUtil.AddQunMessage(m.getSendTime(),m.getSender(),m.getCon(),m.getQunname(),m.getMesType());
					ArrayList<Integer> list=DBUtil.QueryQun(m.getQunname());//���ص�����Ⱥ���û���id
					System.out.println("���������ص�Ⱥ���û�"+list);
					ArrayList<Message> messages=DBUtil.queryQunMessage(m.getQunname());//����һ��Ⱥ����Ϣ�б�
					System.out.println(messages.size());
					for(int i:list){//��߾�û���Ը��Լ�����
							if(ManageClientThread.getClientThread(i)!=null)
							{
								SerConClientThread sc=ManageClientThread.getClientThread(i);
								ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
								Message msg=new Message();
								msg.setMesType(MessageType.message_manypeople);
								msg.setMessage(messages);
								oos.writeObject(msg);
							}
					}

					System.out.println("ִ��Ⱥ��");
				}
				if(m.getMesType().equals(MessageType.message_forFriend)){//���غ���id���˺ŵ��б�
					Message backmsg=new Message();
					ArrayList<Integer> list=DBUtil.QueryRalation(m.getSender());//���û������-------------------------------------
					ArrayList<User> friends=new ArrayList();
					for(int i=0;i<list.size();i++){
						User u=new User();
						u.setUserId(list.get(i));
						u.setUsername(DBUtil.QueryGetUserName(list.get(i)));

						friends.add(u);
					}
					backmsg.setFriend(friends);
					backmsg.setMesType(MessageType.message_forFriend);

					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(backmsg);
					System.out.println("���ͺ���");
				}
				if(m.getMesType().equals(MessageType.message_forQun)){
					Message backmsg=new Message();
					//����һ���û�����Ⱥ��id����
					ArrayList<String> qun=DBUtil.QueryQunAllNameByUserId(m.getSender());
					backmsg.setQunName(qun);//���ص���һ��Ⱥ�Ķ���
					backmsg.setMesType(MessageType.message_forQun);
					System.out.println("Ⱥ�ĵķ��Ͷ�ID"+m.getSender());
					for(String s:qun){
						System.out.println(s+"Ⱥ�Ĳ�ѯ���");
					}

					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(backmsg);
					System.out.println("����Ⱥ��");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
