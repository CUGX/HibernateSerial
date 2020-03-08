package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.UserDaoImple;
import entity.User;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Color;

public class loginView extends JFrame {

	private JPanel contentPane;
	
	private Container ct;  
    //创建背景面板。  
    private BackgroundPanel bgp;
    private JTextField textFieldUsername;
    private JPasswordField textFieldPasswd;
    
    public static String username = "";
    public static String passwd = "";
    private static UserDaoImple udi = new UserDaoImple();
    
    public static loginView frame = new loginView();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setVisible(true);
//					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ct=this.getContentPane();  
        getContentPane().setLayout(null);  
        contentPane.setLayout(null);
        bgp=new BackgroundPanel((new ImageIcon("pic/smartHome1.jpg")).getImage());
        bgp.setBackground(SystemColor.activeCaption);
        bgp.setBounds(0,0,1002,663);  
        ct.add(bgp);
        bgp.setLayout(null);
        bgp.setOpaque(false);
        
//        JLabel labelTitle = new JLabel("\u6B22\u8FCE\u767B\u5F55\u667A\u80FD\u519C\u4E1A\u5927\u68DA\u76D1\u63A7\u7CFB\u7EDF");
        JLabel labelTitle = new JLabel("欢迎登录智能家居系统");
        labelTitle.setBackground(new Color(102, 205, 170));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("宋体", Font.BOLD, 32));
        labelTitle.setToolTipText("");
        labelTitle.setBounds(274, 74, 509, 83);
        labelTitle.setOpaque(true);
        bgp.add(labelTitle);
        
        JLabel labelUsername = new JLabel("\u7528\u6237\u540D\uFF1A");
        labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsername.setFont(new Font("宋体", Font.PLAIN, 20));
        labelUsername.setBounds(330, 215, 100, 36);
        bgp.add(labelUsername);
        labelUsername.setOpaque(true);
        
        JLabel labelpasswd = new JLabel("\u5BC6 \u7801 \uFF1A");
        labelpasswd.setHorizontalAlignment(SwingConstants.CENTER);
        labelpasswd.setFont(new Font("宋体", Font.PLAIN, 20));
        labelpasswd.setBounds(330, 293, 100, 36);
        bgp.add(labelpasswd);
        labelpasswd.setOpaque(true);
        
        textFieldUsername = new JTextField();
        textFieldUsername.setFont(new Font("宋体", Font.PLAIN, 20));
        textFieldUsername.setBounds(470, 215, 240, 36);
        bgp.add(textFieldUsername);
        textFieldUsername.setColumns(10);
        
//        JPasswordField 
        textFieldPasswd = new JPasswordField();
        textFieldPasswd.setFont(new Font("宋体", Font.PLAIN, 20));
        textFieldPasswd.setColumns(10);
        textFieldPasswd.setBounds(470, 293, 240, 36);
        bgp.add(textFieldPasswd);
//        textFieldPasswd.setEchoChar('*');
        
        JButton buttonLogin = new JButton("\u767B \u5F55");
        buttonLogin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		username = textFieldUsername.getText();
        		passwd = textFieldPasswd.getText();
        		//在数据库中查询是否存在登录用户
//        		saveToSQL.logCheck();
        		
//        		if (saveToSQL.Query) {
        		if (udi.User_check(username)) {
        			//查询结果为真时，关闭登录界面，显示农业 大棚界面
        			JOptionPane.showMessageDialog(null, "登录成功，欢迎使用智能家居系统！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
//        			System.out.println("登录成功，欢迎进入智能农业大棚监控系统！");
        			frame.setVisible(false);
        			serialView.launch();
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "用户未注册，请先注册！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
//        			System.out.println("用户未注册，请先注册！");
        			textFieldUsername.setText("");
        			textFieldPasswd.setText("");
        		}
        	}
        });
        buttonLogin.setFont(new Font("宋体", Font.PLAIN, 20));
        buttonLogin.setBounds(560, 383, 100, 36);
        bgp.add(buttonLogin);
        
        JButton buttonRegister = new JButton("\u6CE8 \u518C");
        buttonRegister.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		username = textFieldUsername.getText();
        		passwd = textFieldPasswd.getText();
//        		saveToSQL.logCheck();
        		
//        		if (saveToSQL.Query) {
        		if (udi.User_check(username)) {
        			JOptionPane.showMessageDialog(null, "用户已存在，请直接登录！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
//        			System.out.println("用户已存在，请直接登录！");
        		}
        		else {
        			//注册进数据库
//        			saveToSQL.register();
        			udi.User_Login(new User(username,passwd));
        		}
        	}
        });
        buttonRegister.setFont(new Font("宋体", Font.PLAIN, 20));
        buttonRegister.setBounds(384, 383, 100, 36);
        bgp.add(buttonRegister);
	}
}

