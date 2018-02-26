/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
 */
package com.qq.client.tools;

import java.io.*;
import java.net.*;

import Common.*;
import View.ChatPanel;

public class ClientConServerThread extends Thread {

	private Socket s;
	public User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	// ���캯��
	public ClientConServerThread(Socket s) {
		this.s = s;
		this.user=new User();
	}
/**
 * �������󷵻غ����б�İ�
 * @throws IOException
 */
	public  void sendMessageForFriend() throws IOException{
	ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
	Message msg=new Message();
	msg.setMesType(MessageType.message_forFriend);//�������󷵻غ����б�İ�
	msg.setSender(ManageClientConServerThread.UserID);
	oos.writeObject(msg);
	}

	/**
	 * �������󷵻�Ⱥ�б����ֵ���Ϣ��
	 * @throws IOException
	 */
	public void sendMessageForQun() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		Message msg=new Message();
		msg.setMesType(MessageType.message_forQun);//�������󷵻غ����б�İ�
		msg.setSender(ManageClientConServerThread.UserID);
		oos.writeObject(msg);
	}

	public void sendMessageToSingle(Message MsgtoOne) throws IOException {//������һ����Ϣ�Ķ��еĺ�������ͨ�����������
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(MsgtoOne);
	}


	public void run() {
/**
	String message_succeed="1";//�����ǵ�½�ɹ�
	String message_login_fail="2";//������¼ʧ��
	String message_comm_mes="3";//˽��
	String message_manypeople="4";//Ⱥ��
	String message_image="5";//����ͼƬ
	String message_forFriend="6";//���󷵻�һ�����Ѷ���
	String message_forQun="7";//���󷵻�һ��Ⱥ���ֵĶ���
	String message_formessage="8";//���󷵻�һ����Ϣ�Ķ���
 */
		while (true) {
			// ��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				if (m.getMesType().equals(MessageType.message_comm_mes)) {//˽�ķ��ص����ݰ�������Ƿ����Ǳߣ���������������һ������
					System.out.println("�ͻ��˽��ܵ����������ص�message��");
					ManageQqFriendList.setMessage(m.getMessage());//�����ص�message���д洢�����ǵľ�̬������
					ChatPanel.addJLabeltoPanel();
					System.out.println(m.getMessage()+"��ֵ������ݰ�������");
				}
				if (m.getMesType().equals(MessageType.message_formessage)) {//˽�ķ��ص����ݰ�
					ManageQqFriendList.message=m.getMessage();//�����ص�message���д洢�����ǵľ�̬������
				}
				if (m.getMesType().equals(MessageType.message_manypeople)) {//Ⱥ�ķ��ص����ݰ�
					System.out.println("����������Ⱥ�����ݰ�");
					ManageQqFriendList.setMessage(m.getMessage());
					ChatPanel.addJLabeltoPanel();
				}
				if (m.getMesType().equals(MessageType.message_forFriend)) {//Ⱥ�ķ��ص����ݰ�
					//System.out.println("���ܺ��ѵ��б� ");
					//System.out.println(m.getFriend().size());
					ManageQqFriendList.setFriend(m.getFriend());

				}
				if (m.getMesType().equals(MessageType.message_forQun)) {//Ⱥ�ķ��ص����ݰ�
					//System.out.println("����Ⱥ�����б�");
					ManageQqFriendList.setQunName(m.getQunName());

				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
