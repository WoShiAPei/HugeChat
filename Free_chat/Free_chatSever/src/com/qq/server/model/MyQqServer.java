/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ��ˣ�������
 */
package com.qq.server.model;
import com.qq.server.db.DBUtil;

import Common.Message;
import Common.User;

import java.net.*;
import java.io.*;
import java.util.*;
public class MyQqServer {
	public MyQqServer()
	{
		try {
			//��9999����
			System.out.println("���Ƿ���������9999����");
			ServerSocket ss=new ServerSocket(9999);
			//����,�ȴ�����
			while(true)//ע�����¼
			{
				Socket s=ss.accept();
				//���տͻ��˷�������Ϣ.
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				System.out.println("�ͻ�������User����"+u.toString());
				System.out.println("���������յ��û���:"+u.getUsername()+"  ����:"+u.getPasswd());
				Message m=new Message();
				System.out.println("����");
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				System.out.println("����2");
				if(u.isIfregiste()){//�����ע��
					System.out.println(u.isIfregiste());
					System.out.println("���ݿ��ѯ���"+DBUtil.Query(u.getUsername()));
					if(DBUtil.Query(u.getUsername())){//����鵽���Ѿ������ֵ�
						m.setMesType("2");
						oos.writeObject(m);
						s.close();
					}else{//�ɹ�ע��
						DBUtil.Add(u.getUsername(), u.getPasswd(),1);//ע��ɹ����û��ӵ����ݿ���----ͷ���ٸĸ�
						m.setMesType("1");
						m.setId(DBUtil.QueryGetUserID(u.getUsername()));//�����ǻش������ݰ��н��û���id���д���,�Ǳ߽��н���
						m.setCon(u.getUsername());//ʹ�����ݰ�������ת����������
						m.setSendTime(u.getPasswd());//ʹ�����ݰ�������ת����������
						oos.writeObject(m);

						SerConClientThread scct=new SerConClientThread(s);
						ManageClientThread.addClientThread(DBUtil.QueryGetUserID(u.getUsername()), scct);//u��ע���ʱ����û��id��
						//������ÿͻ���ͨ�ŵ��߳�.
						scct.start();
					}
				}else{//����ע��Ļ�
					if(DBUtil.Query(u.getUsername(), u.getPasswd()))
					{
					//����һ���ɹ���½����Ϣ��
					m.setMesType("1");
					m.setId(DBUtil.QueryGetUserID(u.getUsername()));
					m.setCon(u.getUsername());//ʹ�����ݰ�������ת����������
					m.setSendTime(u.getPasswd());//ʹ�����ݰ�������ת����������
					System.out.println("��û������");
					oos.writeObject(m);

					//����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(DBUtil.QueryGetUserID(u.getUsername()), scct);
					//������ÿͻ���ͨ�ŵ��߳�.
					scct.start();

					//��֪ͨ���������û�.
					//	scct.notifyOther(u.getUserId());//�����Ҫ�ĸ�-----------------------------------
					}else{
					m.setMesType("2");
					oos.writeObject(m);
					//�ر�Socket
					s.close();
				}
			}
		}
	}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		}
	}
}
