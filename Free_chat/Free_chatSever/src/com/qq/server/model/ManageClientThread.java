package com.qq.server.model;

import java.util.*;
/**
 * ��һ������д洢����ȡ�������߳̿��ƵĿͻ��˶���
 * @author SHUXIN
 *
 */
public class ManageClientThread {
	//id��һ�޶�,�ڿͻ��˵�ĳһ�����е�user id������Ƕ�Ӧ��
	public static HashMap hm=new HashMap<String, SerConClientThread>();

	//��hm�����һ���ͻ���ͨѶ�߳�
	public static  void addClientThread(int uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}

	public static SerConClientThread getClientThread(int uid)
	{
		return (SerConClientThread)hm.get(uid);
	}

	//���ص�ǰ���ߵ��˵����
	public static String getAllOnLineUserid()
	{
		//ʹ�õ��������
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
