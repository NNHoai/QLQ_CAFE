package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.Staff;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Main extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtNhanvien;
	private static final Staff staff;
	static{
		staff = new Staff();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main(staff);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Main(Staff s) {
		view(s);
		staff.copy(s);
	}

	/**
	 * Create the frame.
	 */
	public void view(Staff s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 895, 472);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cofee Shop");
		lblNewLabel.setForeground(new Color(210, 105, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Vladimir Script", Font.BOLD, 48));
		lblNewLabel.setBounds(293, 34, 300, 66);
		panel.add(lblNewLabel);
		
		btnBanhang = new JButton("Ba\u0301n ha\u0300ng");
		btnBanhang.setOpaque(false);
		btnBanhang.addActionListener(this);
		btnBanhang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBanhang.setBounds(44, 89, 160, 73);
		panel.add(btnBanhang);
		
		btnQuanLy = new JButton("Qua\u0309n ly\u0301");
		btnQuanLy.setOpaque(false);
		btnQuanLy.addActionListener(this);
		btnQuanLy.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnQuanLy.setBounds(667, 89, 160, 73);
		btnQuanLy.setEnabled(false);
		panel.add(btnQuanLy);
		
		btnThongke = new JButton("Th\u00F4\u0301ng k\u00EA");
		btnThongke.setOpaque(false);
		btnThongke.addActionListener(this);
		btnThongke.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnThongke.setBounds(667, 280, 160, 73);
		btnThongke.setEnabled(false);
		panel.add(btnThongke);
		
		btnThongtin = new JButton("Th\u00F4ng tin");
		btnThongtin.setOpaque(false);
		btnThongtin.addActionListener(this);
		btnThongtin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnThongtin.setBounds(44, 280, 160, 73);
		panel.add(btnThongtin);
		
		btnLogout = new JButton("\u0110\u0103ng xu\u00E2\u0301t");
		btnLogout.addActionListener(this);
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLogout.setBounds(774, 425, 114, 43);
		panel.add(btnLogout);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u00E2n vi\u00EAn:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 10, 88, 26);
		panel.add(lblNewLabel_1);
		
		txtNhanvien = new JTextField(s.getTen_nv());
		txtNhanvien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtNhanvien.setEditable(false);
		txtNhanvien.setBounds(99, 10, 241, 26);
		panel.add(txtNhanvien);
		txtNhanvien.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\\\Users\\\\TP\\\\OneDrive - The University of Technology\\\\Desktop\\\\QLQ_CAFE\\\\QLQUANCF\\\\image\\quan-cafe.jpg"));
		lblNewLabel_2.setBounds(0, 0, 895, 472);
		panel.add(lblNewLabel_2);

		if(s.getId_cv() == 1)
		{
			btnQuanLy.setEnabled(true);
			btnThongke.setEnabled(true);
		}
	}
	private JButton btnLogout;
	private JButton btnThongtin;
	private JButton btnThongke;
	private JButton btnQuanLy;
	private JButton btnBanhang;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBanhang)
		{
			try {
				dispose();
				Order frame = new Order(staff);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if(e.getSource() == btnQuanLy)
		{
			try {
				dispose();
				Manage frame = new Manage(staff);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if(e.getSource() == btnThongke)
		{
			try {
				dispose();
				Statistical frame = new Statistical(staff);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if(e.getSource() == btnThongtin)
		{
			try {
				dispose();
				Infos frame = new Infos(staff);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if(e.getSource() == btnLogout)
		{
			try {
				dispose();
				Login frame = new Login();
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
