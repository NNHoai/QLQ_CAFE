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
	private JPanel pnTables;
	private JPanel pnManage;
	private JPanel pnMenu;
	private JLabel lblThcn;
	private JPanel pnFoods;
	private JPanel pnStaffs;
	private JPanel pnWork;
	
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
		staffs = new Staffs();
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
		
		pnStaffs = new JPanel();
		pnStaffs.setBounds(10, 274, 214, 78);
		pnManage.add(pnStaffs);
		pnStaffs.setBackground(new Color(210, 105, 30));
		pnStaffs.addMouseListener(this);
		pnStaffs.setLayout(null);
		
		JLabel lblStaffs = new JLabel("Nh\u00E2n vi\u00EAn");
		lblStaffs.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaffs.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblStaffs.setBounds(32, 10, 151, 58);
		pnStaffs.add(lblStaffs);
		
		pnTables = new JPanel();
		pnTables.setBounds(10, 186, 214, 78);
		pnManage.add(pnTables);
		pnTables.setBackground(new Color(210, 105, 30));
		pnTables.addMouseListener(this);
		pnTables.setLayout(null);
		
		JLabel lblTables = new JLabel("Ba\u0300n");
		lblTables.setHorizontalAlignment(SwingConstants.CENTER);
		lblTables.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTables.setBounds(59, 10, 85, 58);
		pnTables.add(lblTables);
		
		pnMenu = new JPanel();
		pnMenu.addMouseListener(this);
		pnMenu.setLayout(null);
		pnMenu.setBackground(new Color(210, 105, 30));
		pnMenu.setBounds(10, 10, 214, 78);
		pnManage.add(pnMenu);
		
		lblThcn = new JLabel("Th\u01B0\u0323c \u0111\u01A1n");
		lblThcn.setHorizontalAlignment(SwingConstants.CENTER);
		lblThcn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblThcn.setBounds(23, 10, 170, 58);
		pnMenu.add(lblThcn);
		
		pnFoods = new JPanel();
		pnFoods.addMouseListener(this);
		pnFoods.setLayout(null);
		pnFoods.setBackground(new Color(210, 105, 30));
		pnFoods.setBounds(10, 98, 214, 78);
		pnManage.add(pnFoods);
		
		JLabel lblMonn = new JLabel("Mo\u0301n \u0103n");
		lblMonn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblMonn.setBounds(57, 10, 100, 58);
		pnFoods.add(lblMonn);
		
		pnWork = new JPanel();
		pnWork.addMouseListener(this);
		pnWork.setLayout(null);
		pnWork.setBackground(new Color(210, 105, 30));
		pnWork.setBounds(10, 362, 214, 78);
		pnManage.add(pnWork);
		
		JLabel lblCngVic = new JLabel("C\u00F4ng vi\u00EA\u0323c");
		lblCngVic.setHorizontalAlignment(SwingConstants.CENTER);
		lblCngVic.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblCngVic.setBounds(32, 10, 151, 58);
		pnWork.add(lblCngVic);
		
		btnThoat = new JButton("Thoa\u0301t");
		btnThoat.addActionListener(this);
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThoat.setBounds(54, 460, 118, 38);
		pnManage.add(btnThoat);
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
	
	private JButton btnThoat;
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
		if(e.getSource() == pnTables)
		{
			menuClick(tables);
		}
		if(e.getSource() == pnFoods)
		{
			menuClick(foods);
		}
		if(e.getSource() == pnMenu)
		{
			menuClick(menu);
		}
		if(e.getSource() == pnStaffs)
		{
			menuClick(staffs);
		}
		if(e.getSource() == pnWork)
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
