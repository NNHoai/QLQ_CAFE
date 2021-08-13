package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.Staff;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Manage extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
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
					Manage frame = new Manage(staff);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private Menus menu;
	private Tables tables;
	private Staffs staffs;
	private Foods foods;
	private Works work;
	private JPanel pnManage;
	
	public Manage(Staff s){
		staff.copy(s);
		view(s);
	}
	public void view(Staff s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menu = new Menus();
		tables = new Tables();
		staffs = new Staffs(s);
		foods = new Foods();
		work = new Works();
		
		contentPane.add(menu);
		contentPane.add(tables);
		contentPane.add(staffs);
		contentPane.add(foods);
		contentPane.add(work);
		
		pnManage = new JPanel();
		pnManage.setBackground(new Color(0, 255, 255));
		pnManage.setBounds(0, 0, 240, 521);
		contentPane.add(pnManage);
		pnManage.setLayout(null);
		
		btnThoat = new JButton("Thoa\u0301t");
		btnThoat.addActionListener(this);
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThoat.setBounds(53, 473, 118, 38);
		pnManage.add(btnThoat);
		
		lbCongViec = new JLabel("C\u00F4ng vi\u00EA\u0323c");
		lbCongViec.setOpaque(true);
		lbCongViec.setBackground(new Color(210, 105, 30));
		lbCongViec.setBounds(10, 405, 220, 58);
		lbCongViec.setHorizontalAlignment(SwingConstants.CENTER);
		lbCongViec.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbCongViec.addMouseListener(this);
		pnManage.add(lbCongViec);
		
		lbNhanvien = new JLabel("Nh\u00E2n vi\u00EAn");
		lbNhanvien.setOpaque(true);
		lbNhanvien.setBackground(new Color(210, 105, 30));
		lbNhanvien.setBounds(10, 337, 220, 58);
		lbNhanvien.setHorizontalAlignment(SwingConstants.CENTER);
		lbNhanvien.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbNhanvien.addMouseListener(this);
		pnManage.add(lbNhanvien);
		
		lbBan = new JLabel("Ba\u0300n");
		lbBan.setOpaque(true);
		lbBan.setBackground(new Color(210, 105, 30));
		lbBan.setBounds(10, 269, 220, 58);
		lbBan.setHorizontalAlignment(SwingConstants.CENTER);
		lbBan.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbBan.addMouseListener(this);
		pnManage.add(lbBan);
		
		lbMonan = new JLabel("Mo\u0301n \u0103n");
		lbMonan.setOpaque(true);
		lbMonan.setBackground(new Color(210, 105, 30));
		lbMonan.setBounds(10, 201, 220, 58);
		lbMonan.setHorizontalAlignment(SwingConstants.CENTER);
		lbMonan.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbMonan.addMouseListener(this);
		pnManage.add(lbMonan);
		
		lbThucdon = new JLabel("Th\u01B0\u0323c \u0111\u01A1n");
		lbThucdon.setBackground(new Color(210, 105, 30));
		lbThucdon.setOpaque(true);
		lbThucdon.setBounds(10, 133, 220, 58);
		lbThucdon.setHorizontalAlignment(SwingConstants.CENTER);
		lbThucdon.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbThucdon.addMouseListener(this);
		pnManage.add(lbThucdon);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PC\\OneDrive\\M\u00E1y t\u00EDnh\\QLQ_CAFE\\QLQUANCF\\image\\ic_manage.png"));
		lblNewLabel.setBounds(0, 0, 240, 123);
		pnManage.add(lblNewLabel);
		
		menuClick(menu);
		
	}
	public void menuClick(JPanel jp) 
	{	
		menu.setVisible(false);
		tables.setVisible(false);
		staffs.setVisible(false);
		foods.setVisible(false);
		work.setVisible(false);
		jp.setVisible(true);
	}
	
	private JLabel lbCongViec;
	private JLabel lbThucdon;
	private JLabel lbNhanvien;
	private JLabel lbMonan;
	private JLabel lbBan;
	
	private JButton btnThoat;
	private JLabel lblNewLabel;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnThoat)
		{
			try 
			{
				dispose();
				Main frame = new Main(staff);
				frame.setVisible(true);
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == lbBan)
		{
			menuClick(tables);
		}
		if(e.getSource() == lbMonan)
		{
			menuClick(foods);
		}
		if(e.getSource() == lbThucdon)
		{
			menuClick(menu);
		}
		if(e.getSource() == lbNhanvien)
		{
			menuClick(staffs);
		}
		if(e.getSource() == lbCongViec)
		{
			menuClick(work);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
