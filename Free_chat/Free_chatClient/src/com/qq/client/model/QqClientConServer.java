/**
 * ���ǿͻ������ӷ������ĺ�̨
 */
package com.qq.client.model;
import com.qq.client.tools.*;
import java.util.*;
import java.net.*;
import java.io.*;
import Common.*;
public class QqClientConServer {


	public  Socket s;
	public void QqClientConServer(){

	}
	//���͵�һ������
	/**�ú�����user�����룬֮��ͨ�����ص�B���в��������˵û�гɹ���ô�ͻ᷵��0����֮���ص����¿��̵߳�ID
	 * @param o
	 * @return
	 */
	public  int sendLoginInfoToServer(Object o)//�����һ��User����
	{
		int b=0;
		try {
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();

			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1"))
			{
				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);

				ManageClientConServerThread.Username=ms.getCon();//�õ����Ǹ��˺ŵ�����
				ManageClientConServerThread.UserPsw=ms.getSendTime();//�õ������û�������
				ManageClientConServerThread.UserID=ms.getId();//�õ�	�����û��˺ŵ�id

				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(ms.getId(), ccst);
				b=ms.getId();
				ccst.sendMessageForFriend();
				ccst.sendMessageForQun();
			}else{
				//�ر�Scoket
				s.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		}
		return b;
	}

	public void SendInfoToServer(Object o)
	{
	}
}
