/**
 * ����һ������ͻ��˺ͷ���������ͨѶ���߳��࣬��������Լ���ȡ
 */
package com.qq.client.tools;

import Common.User;
/**
 * ��ʵҲֻ��һ���̻߳ᴫ�ݵ����
 * @author SHUXIN
 *
 */
public class ManageClientConServerThread {

	public  static  ClientConServerThread mclientthread;
	public static int UserID;
	public static String Username;
	public static String UserPsw;
	public static  void addClientConServerThread(int id,ClientConServerThread mclientthread){
		UserID=id;
		ManageClientConServerThread.mclientthread=mclientthread;
	}
}
