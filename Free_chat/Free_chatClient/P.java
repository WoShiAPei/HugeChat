package NEWLoginWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class P extends JFrame{

	private JFrame frame;

	// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	static Point origin = new Point();
	private JTextField txtName;
	private JPasswordField txtPass;
	private JLabel user;
	private JLabel redExit;
	private JLabel greenOk;
	private JLabel login;
	private JLabel login2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					P window = new P();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public P() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(UIManager.getColor("Button.background"));
		frame.setSize(350, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//ȡ��Ļ��С
		setLocation((size.width - getWidth()) / 2, (size.height - getHeight()) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("΢���ź� Light", Font.PLAIN, 17));
		txtName.setBounds(95, 270, 160, 35);
		txtName.setText("�û���");
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtPass = new JPasswordField();
		txtPass.setEchoChar('*');
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtPass.setText("����");
		txtPass.setBounds(95, 320, 160, 35);
		frame.getContentPane().add(txtPass);

		JLabel exit = new JLabel(" ");
		exit.setBounds(310, 0, 40, 30);
		exit.setIcon(new ImageIcon("src//LoginWindow//image//xx.png"));
		frame.getContentPane().add(exit);
		exit.setVisible(false);

		JLabel HugeChat = new JLabel(" ");
		HugeChat.setBounds(0, 0, 105, 35);
		HugeChat.setIcon(new ImageIcon("src//LoginWindow//image//title.png"));
		frame.getContentPane().add(HugeChat);

		user = new JLabel("  ");
		user.setBounds(116, 120, 126, 124);
		user.setIcon(new ImageIcon("src//LoginWindow//image//man.png"));
		frame.getContentPane().add(user);

		JLabel yes = new JLabel(" ");
		yes.setBounds(147, 377, 56, 44);
		yes.setIcon(new ImageIcon("src//LoginWindow//image//ok.png"));
		frame.getContentPane().add(yes);

		redExit = new JLabel(" ");
		redExit.setBounds(310, 0, 40, 30);
		redExit.setIcon(new ImageIcon("src//LoginWindow//image//x.png"));
		frame.getContentPane().add(redExit);

		greenOk = new JLabel(" ");
		greenOk.setBounds(108, 376, 134, 40);
		greenOk.setIcon(new ImageIcon("src//LoginWindow//image//login.png"));
		frame.getContentPane().add(greenOk);
		greenOk.setVisible(false);

		login = new JLabel("ע��");
		login.setForeground(Color.GRAY);
		login.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setBounds(264, 465, 72, 18);
		frame.getContentPane().add(login);

		login2 = new JLabel("ע��");
		login2.setForeground(new Color(192, 192, 192));
		login2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		login2.setHorizontalAlignment(SwingConstants.CENTER);
		login2.setBounds(264, 465, 72, 18);
		frame.getContentPane().add(login2);
		login2.setVisible(false);

		JLabel user2 = new JLabel("  ");
		user2.setBounds(116, 120, 126, 124);
		user2.setIcon(new ImageIcon("src//LoginWindow//manT.png"));
		frame.getContentPane().add(user2);



		frame.addMouseListener(new MouseAdapter() {
			// ���£�mousePressed ���ǵ����������걻����û��̧��
			public void mousePressed(MouseEvent e) {
				// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				origin.x = e.getX();
				origin.y = e.getY();

			}
		});

		frame.addMouseListener(new MouseAdapter() {
			// ���£�mousePressed ���ǵ����������걻����û��̧��
			public void mouseClicked(MouseEvent e) {
				// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
				origin.x = e.getX();
				origin.y = e.getY();


				if(e.getX()>260&&e.getY()>460){
					login.setVisible(false);
					login2.setVisible(true);
					repaint();
				}else{
					login.setVisible(true);
					login2.setVisible(false);
				}


				if(e.getX()>116&&e.getX()<242&&e.getY()>126&&e.getY()<250){
					user.setVisible(false);
					user2.setVisible(true);
					repaint();

					T f2 = new T();
					f2.setVisible(true);


				}else{
					user.setVisible(true);
					user2.setVisible(false);
				}
			}
		});


		frame.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point p = frame.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);

			}
			public void mouseMoved(MouseEvent e) {
				if(e.getX()>320&&e.getY()<30){
					redExit.setVisible(false);
					exit.setVisible(true);
					repaint();
				}else{
					redExit.setVisible(true);
					exit.setVisible(false);
				}

				if(e.getX()>138&&e.getX()<222&&e.getY()>375&&e.getY()<415){
					yes.setVisible(false);
					greenOk.setVisible(true);
					repaint();
				}else{
					yes.setVisible(true);
					greenOk.setVisible(false);
				}

				if(e.getX()>116&&e.getX()<242&&e.getY()>126&&e.getY()<250){
					user.setVisible(false);
					user2.setVisible(true);
					repaint();

				}else{
					user.setVisible(true);
					user2.setVisible(false);
				}
			}

		});
	}
}
