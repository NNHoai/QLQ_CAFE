package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Staff_BLL;
import DTO.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Login extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassWord;
	private JButton btnLogin;
	private JButton btnThoat;
	private static final Staff_BLL staff;
	static {
		staff = new Staff_BLL();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		super();
		view();
	}
	private Staff login(String username, String password)
	{
		return staff.checklogin(username, password);
	}
	/**
	 * Create the frame.
	 */
	public void view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 1061, 570);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn \u0111\u0103ng nh\u00E2\u0323p");
		lblNewLabel_1.setBounds(271, 31, 147, 37);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblNewLabel_1);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(428, 36, 358, 27);
		txtUserName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel.add(txtUserName);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("M\u00E2\u0323t kh\u00E2\u0309u");
		lblNewLabel_1_1.setBounds(271, 94, 147, 37);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblNewLabel_1_1);
		
		btnLogin = new JButton("\u0110\u0103ng nh\u00E2\u0323p");
		btnLogin.setBorderPainted(false);
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnLogin.setBounds(853, 54, 187, 66);
		btnLogin.addActionListener(this);
		panel.add(btnLogin);
		
		
		btnThoat = new JButton("Thoa\u0301t");
		btnThoat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnThoat.setBounds(895, 143, 112, 49);
		btnThoat.addActionListener(this);
		panel.add(btnThoat);
		
		txtPassWord = new JPasswordField();
		txtPassWord.setBounds(428, 94, 358, 26);
		panel.add(txtPassWord);
		
		JLabel lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon("C:\\Users\\TP\\OneDrive - The University of Technology\\Desktop\\QLQ_CAFE\\QLQUANCF\\image\\Login.png"));
		lbBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lbBackground.setBounds(0, 0, 1061, 570);
		panel.add(lbBackground);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnLogin)
		{
			String username = txtUserName.getText();
			String password = String.valueOf(txtPassWord.getPassword());
			Staff s = login(username,password);
			if(s != null)
			{
				try {
					dispose();
					Main frame = new Main(s);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}else JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu sai! Vui lòng nhập lại!");
		}
		if(e.getSource() == btnThoat)
		{
			System.exit(0);
		}
	}
}
