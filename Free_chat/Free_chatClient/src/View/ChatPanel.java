package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.qq.client.tools.FriendClickedListener;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;

import Common.Message;
import Common.User;

public class ChatPanel {
	static Point origin = new Point();
	int UserId;
	public String getter = "";// ���ͨ��֮��Ŀؼ�����������
	public boolean oneShow = false, twoShow = false, threeShow = false;
	public int clickedFriend;
	public String clickedQun;
	public boolean qun=false,single=true;//��ߵ����������������������ǵ�
	// �����û����Ѻ�Ⱥ�ĵĶ���
	public  ArrayList<String> QunName=ManageQqFriendList.QunName;
	public  ArrayList<User> FriendIFM=ManageQqFriendList.friend;
	public 	Label nowChat;//��ǰ�����������ʾ
	public 	JTextArea textArea;
	public static JPanel panel_1;
	public ArrayList<String> getQunName() {
		return QunName;
	}

	public void setQunName(ArrayList<String> qunName) {
		QunName = qunName;
	}

	public ArrayList<User> getFriendIFM() {
		return FriendIFM;
	}

	public void setFriendIFM(ArrayList<User> friendIFM) {
		FriendIFM = friendIFM;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatPanel d = new ChatPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatPanel() {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 1115, 801);
		frame.getContentPane().setLayout(null);
		// ͷ��

		JLabel lblNewLabel = new JLabel("");// ---------------��߲���
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(20, 17, 50, 50);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("src/ͷ��/" + ManageClientConServerThread.UserID % 10 + ".jpg"));

		JLabel name = new JLabel(ManageClientConServerThread.Username);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("����", Font.PLAIN, 25));
		name.setBounds(92, 28, 139, 26);
		frame.getContentPane().add(name);

		// ���水ť1
		JLabel one = new JLabel(" ");
		one.setBounds(22, 105, 33, 30);
		frame.getContentPane().add(one);
		one.setIcon(new ImageIcon("src/ChatImage/one.png"));
		one.setVisible(false);

		// ���水ť2
		JLabel two = new JLabel(" ");
		two.setBounds(22, 168, 33, 26);
		frame.getContentPane().add(two);
		two.setIcon(new ImageIcon("src/ChatImage/two.png"));
		two.setVisible(false);

		// ���水ť3
		JLabel three = new JLabel(" ");
		three.setBounds(23, 227, 31, 33);
		frame.getContentPane().add(three);
		three.setIcon(new ImageIcon("src/ChatImage/three.png"));
		three.setVisible(false);

		// ����������
		JLabel Gface = new JLabel(" ");
		Gface.setBounds(425, 589, 25, 25);
		frame.getContentPane().add(Gface);
		Gface.setIcon(new ImageIcon("src/ChatImage/Gface.png"));
		Gface.setVisible(false);

		// �������ļ�����
		JLabel Gfile = new JLabel(" ");
		Gfile.setBounds(464, 591, 24, 22);
		frame.getContentPane().add(Gfile);
		Gfile.setIcon(new ImageIcon("src/ChatImage/Gfile.png"));
		Gfile.setVisible(false);

		// ����������
		JLabel Gcut = new JLabel(" ");
		Gcut.setBounds(503, 591, 24, 22);
		frame.getContentPane().add(Gcut);
		Gcut.setIcon(new ImageIcon("src/ChatImage/Gcut.png"));
		Gcut.setVisible(false);

		// ���JLabel����������
		JLabel tool = new JLabel(" ");
		tool.setBounds(393, 575, 750, 47);
		frame.getContentPane().add(tool);
		tool.setIcon(new ImageIcon("src/ChatImage/tool.png"));

		// �����б�JPanel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 127, 316, 676);
		frame.getContentPane().add(scrollPane);


		//--------------------------�����б�
		JTextArea search = new JTextArea();
		search.setFont(new Font("Monospaced", Font.PLAIN, 20));
		search.setBackground(Color.white);
		// search.setBackground(SystemColor.controlHighlight);
		search.setBounds(86, 95, 244, 26);
		frame.getContentPane().add(search);

		// ���Һ��Ѱ�ť
		JButton searchB = new JButton("");
		searchB.setFont(new Font("����", Font.PLAIN, 12));
		searchB.setBounds(333, 95, 60, 27);
		searchB.setIcon(new ImageIcon("src/ChatImage/Gsearch.png"));
		frame.getContentPane().add(searchB);
		searchB.setVisible(false);

		JLabel search2 = new JLabel(" ");
		search2.setBounds(333, 95, 60, 27);
		frame.getContentPane().add(search2);
		search2.setIcon(new ImageIcon("src/ChatImage/search.png"));

		// ���Ͱ�ť�������Ҫ���иı��������ɫ��

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("����˷��Ͱ�ť");
				if(!qun&&single){
					Message msg= new Message();
					msg.setMesType(Common.MessageType.message_comm_mes);
					msg.setSender(ManageClientConServerThread.UserID);//���÷�������Ϣ
					msg.setGetter(clickedFriend);//���ý�����id
					msg.setCon(textArea.getText());//���ý��յ�����
					textArea.setText("");
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
					 Date date = new Date();
					 String time=simpleDateFormat.format(date);
					 msg.setSendTime(time);                //���÷��͵�ʱ��
					 System.out.println("���ͳ����ݰ�"+msg);
					try {
						ManageClientConServerThread.mclientthread.sendMessageToSingle(msg);
						System.out.println("���͵�������");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else{//��Ⱥ����Ϣ
					Message msg= new Message();
					msg.setMesType(Common.MessageType.message_manypeople);
					msg.setSender(ManageClientConServerThread.UserID);//���÷�������Ϣ
					msg.setQunname(clickedQun);//���ý���Ⱥ������
					msg.setCon(textArea.getText());//���ý��յ�����
					textArea.setText("");
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
					 Date date = new Date();
					 String time=simpleDateFormat.format(date);
					 msg.setSendTime(time);                //���÷��͵�ʱ��
					 System.out.println("���ͳ����ݰ�"+msg);
					try {
						ManageClientConServerThread.mclientthread.sendMessageToSingle(msg);
						System.out.println("���͵�������");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				addJLabeltoPanel();//���÷��������б�ˢ��
			}
		});

		frame.getContentPane().add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon("src/ChatImage/send.png"));
		btnNewButton.setBounds(1000, 753, 85, 35);
		btnNewButton.setVisible(false);

		JLabel send = new JLabel(" ");
		send.setBounds(1000, 753, 85, 35);
		frame.getContentPane().add(send);
		send.setIcon(new ImageIcon("src/ChatImage/send1.png"));

		// �����ʾ��������ĺ���
		nowChat=new Label("");
		nowChat.setBackground(SystemColor.control);
		nowChat.setFont(new Font("Dialog", Font.PLAIN, 25));
		nowChat.setBounds(404, 95, 360, 30);
		frame.getContentPane().add(nowChat);

		// ������ʾ���Ǽ�������ȥ����Ϣ
		textArea = new JTextArea();
		textArea.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		textArea.setLineWrap(true);
		textArea.setRows(10);
		textArea.setBounds(393, 622, 722, 135);
		frame.getContentPane().add(textArea);
		// --------------------------------------��Ϣ����
		// ����ʱ��Ĺ������ڣ���ʱ��ͨ�����ݿ������������JLabel,��ͷ��+��Ϣ
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(393, 127, 722, 450);
		frame.getContentPane().add(scrollPane_1);

		panel_1 = new JPanel();
		// �ô����ݿ�鵽�����Ժ���в���
		if(ManageQqFriendList.message.size()>0)
		panel_1.setLayout(new GridLayout(ManageQqFriendList.message.size(), 1));
		else
		panel_1.setLayout(new GridLayout(100, 1));//Ĭ�Ͼ���100��
		scrollPane_1.setViewportView(panel_1);
		addJLabeltoPanel();
		//---------------������ʾ�ĺ���-----֮���ڼ����ĵط���Ҫ��һ��
		// --------------------------
		JLabel bkgd = new JLabel();
		bkgd.setBackground(Color.WHITE);
		bkgd.setIcon(new ImageIcon("src/ChatImage/blackground.png"));
		bkgd.setBounds(0, 0, 1125, 805);
		frame.getContentPane().add(bkgd);

		frame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mousePressed(MouseEvent e) {
				// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				origin.x = e.getX();
				origin.y = e.getY();
			}

			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point p = frame.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			}

			public void mouseMoved(MouseEvent e) {
				if (e.getX() > 333 && e.getX() < 400 && e.getY() > 93 && e.getY() < 120) {
					searchB.setVisible(true);
					search2.setVisible(true);
				} else {
					searchB.setVisible(false);
					search2.setVisible(true);
				}
				if (e.getX() > 1000 && e.getX() < 1100 && e.getY() > 753 && e.getY() < 790) {
					btnNewButton.setVisible(true);
					send.setVisible(false);
				} else {
					btnNewButton.setVisible(false);
					send.setVisible(true);
				}
				if (e.getX() > 426 && e.getX() < 456 && e.getY() > 595 && e.getY() < 620) {
					Gface.setVisible(true);
				} else {
					Gface.setVisible(false);
				}

				if (e.getX() > 464 && e.getX() < 496 && e.getY() > 595 && e.getY() < 620) {
					Gfile.setVisible(true);
				} else {
					Gfile.setVisible(false);
				}

				if (e.getX() > 503 && e.getX() < 533 && e.getY() > 595 && e.getY() < 620) {
					Gcut.setVisible(true);
				} else {
					Gcut.setVisible(false);
				}
			}
		});

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				origin.x = e.getX();
				origin.y = e.getY();
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getX() > 1065 && e.getX() < 1085 && e.getY() > 33 && e.getY() <54){//�������˷��Ͱ�ť
					System.exit(0);
				}
				if (e.getX() > 21 && e.getX() < 54 && e.getY() > 110 && e.getY() < 140) {// ����˵�һ����˽��
					System.out.println("˽��");
					oneShow = false;
					twoShow = false;
					threeShow = false;
					try {//�������󷵻غ����б�
						ManageClientConServerThread.mclientthread.sendMessageForFriend();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					FriendIFM=ManageQqFriendList.friend;
					one.setVisible(false);
					two.setVisible(false);
					three.setVisible(false);

					JPanel bkgd2 = new JPanel();
					bkgd2.setLayout(new GridLayout(50, 1));
					scrollPane.setViewportView(bkgd2);
					if (!oneShow && !twoShow&&FriendIFM!=null) {
						for (int i = 0; i < FriendIFM.size(); i++) {// ��߾Ͳ��ø��ˣ��һ�����ݿ�õ����������пؼ�����
							JLabel image1 = new JLabel(FriendIFM.get(i).getUsername());
							image1.setIcon(new ImageIcon("src/ͷ��/" +FriendIFM.get(i).getUserId() + ".jpg"));
							image1.setFont(new Font("Dialog", Font.PLAIN, 18));
							image1.addMouseListener(new MouseAdapter(){
								 public void mouseClicked(MouseEvent e) {
									 //�õ�������û�������
									JLabel label= (JLabel)e.getSource();
									String ClickedName=label.getText();
									//ȥ��������б��в���id
									for(User u:ManageQqFriendList.friend){
										if(u.getUsername().equals(ClickedName)){//�ҵ��Ժ�id��ֵ��
											clickedFriend=u.getUserId();
										}
									}
									single=true;
									qun=false;
									Message msg =new Message();
									msg.setMesType(Common.MessageType.message_formessage);
									msg.setSender(UserId);
									msg.setGetter(clickedFriend);
									try {
										ManageClientConServerThread.mclientthread.sendMessageToSingle(msg);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									nowChat.setText("���������"+ClickedName+"����");
								 }
							});
							bkgd2.add(image1);
						}

					}
					if (oneShow && twoShow&&QunName!=null) {
						for (int i = 0; i < QunName.size(); i++) {
							JLabel image1 = new JLabel(QunName.get(i));
							image1.setIcon(new ImageIcon("src/ͷ��/Ⱥ" +(i+1)%5 + ".png"));
							image1.setFont(new Font("Dialog", Font.PLAIN, 18));
							image1.addMouseListener(new MouseAdapter(){
								 public void mouseClicked(MouseEvent e) {
									 //�õ�������û�������
									JLabel label= (JLabel)e.getSource();
									clickedQun=label.getText();
									nowChat.setText("����Ⱥ"+clickedQun+"������");
									qun=true;
									single=false;
								 }
							});
							bkgd2.add(image1);
						}
					}
				}
				if (e.getX() > 22 && e.getX() < 55 && e.getY() > 170 && e.getY() < 200) {// ����˵ڶ�����Ⱥ��
					oneShow = true;
					twoShow = true;
					threeShow = false;
					try {//�������󷵻غ����б�
						ManageClientConServerThread.mclientthread.sendMessageForQun();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					QunName=ManageQqFriendList.QunName;
					one.setVisible(true);
					two.setVisible(true);
					three.setVisible(false);

					JPanel bkgd2 = new JPanel();
					bkgd2.setLayout(new GridLayout(50, 1));
					scrollPane.setViewportView(bkgd2);
					if (!oneShow && !twoShow&&FriendIFM!=null) {
						for (int i = 0; i < FriendIFM.size(); i++) {// ��߾Ͳ��ø��ˣ��һ�����ݿ�õ����������пؼ�����
							JLabel image1 = new JLabel(FriendIFM.get(i).getUsername());
							image1.setIcon(new ImageIcon("src/ͷ��/" +FriendIFM.get(i).getUserId() + ".jpg"));
							image1.setFont(new Font("Dialog", Font.PLAIN, 18));
							image1.addMouseListener(new MouseAdapter(){
								 public void mouseClicked(MouseEvent e) {
									 //�õ�������û�������
									JLabel label= (JLabel)e.getSource();
									String ClickedName=label.getText();
									//ȥ��������б��в���id
									for(User u:ManageQqFriendList.friend){
										if(u.getUsername().equals(ClickedName)){//�ҵ��Ժ�id��ֵ��
											clickedFriend=u.getUserId();
										}
									}
									single=true;
									qun=false;
									nowChat.setText("���������"+ClickedName+"����");
								 }
							});
							bkgd2.add(image1);
						}
					}
					if (oneShow && twoShow&&QunName!=null) {
						for (int i = 0; i < QunName.size(); i++) {
							JLabel image1 = new JLabel(QunName.get(i));
							image1.setIcon(new ImageIcon("src/ͷ��/Ⱥ" +(i+1)%5 + ".png"));
							image1.setFont(new Font("Dialog", Font.PLAIN, 18));
							image1.addMouseListener(new MouseAdapter(){
								 public void mouseClicked(MouseEvent e) {
									 //�õ�������û�������
									JLabel label= (JLabel)e.getSource();
									clickedQun=label.getText();
									qun=true;
									single=false;
									nowChat.setText("����Ⱥ"+clickedQun+"������");
								 }
							});
							bkgd2.add(image1);
						}
					}
				}
				if (e.getX() > 22 && e.getX() < 55 && e.getY() > 220 && e.getY() < 250) {// ����˵��������ղ�
					oneShow = true;
					twoShow = false;
					threeShow = true;
					one.setVisible(true);
					two.setVisible(false);
					three.setVisible(true);
				}
			}
		});// -------------------------------
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public static  void addJLabeltoPanel(){
		panel_1.removeAll();
		for (int i = 0; i < ManageQqFriendList.message.size(); i++) {// ���Ӧ�þ��Ǵ����ݿ�õ����ǵ������¼�����пؼ��Ĳ�����ͬ�����б�
			// û������
				JLabel timeshow=new JLabel("                                                                                                        "+ManageQqFriendList.message.get(i).getSendTime());
				JLabel null1 = new JLabel(ManageQqFriendList.message.get(i).getCon());//��������0;
				null1.setIcon(new ImageIcon("src/ͷ��/"+ManageQqFriendList.message.get(i).getSender()%10+".jpg"));
				panel_1.add(timeshow);
				panel_1.add(null1);
		}
	}
}
