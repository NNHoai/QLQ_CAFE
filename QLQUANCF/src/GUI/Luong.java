package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BLL.Staff_BLL;
import DTO.Staff;
import java.awt.Color;
import javax.swing.ImageIcon;


public class Luong extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable tbLuong;
	private Staff_BLL staff_BLL;
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
					Luong frame = new Luong(staff);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Luong(Staff s) {
		staff_BLL = new Staff_BLL();
		view();
		loadTB();
		staff.copy(s);
	}
	
	public void loadTB()
	{
		List<Staff> ls = null;
		DefaultTableModel d = (DefaultTableModel) tbLuong.getModel();
		d.setRowCount(0);
		TableRowSorter<DefaultTableModel> srt = new TableRowSorter<DefaultTableModel>(d);
		tbLuong.setRowSorter(srt);
		try
		{
			ls = staff_BLL.getAll();
		}
		catch(Exception e)
		{	}
		
		if(ls != null)
		{
			for(Staff i : ls) {
				d.addRow(new Object[] { i.getId_nv(), i.getTen_nv()});
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public void view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1073, 654);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tbLuong = new JTable();
		tbLuong.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbLuong.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ma\u0303 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "T\u00F4\u0309ng s\u00F4\u0301 ca la\u0300m", "L\u01B0\u01A1ng"
			}
		));
		tbLuong.getColumnModel().getColumn(0).setMaxWidth(75);
		tbLuong.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbLuong.getColumnModel().getColumn(1).setMaxWidth(150);
		tbLuong.getColumnModel().getColumn(2).setPreferredWidth(85);
		tbLuong.getColumnModel().getColumn(2).setMaxWidth(85);
		tbLuong.getColumnModel().getColumn(3).setPreferredWidth(120);
		tbLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 1038, 472);
		scrollPane.setViewportView(tbLuong);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Ba\u0309ng l\u01B0\u01A1ng");
		lblNewLabel.setForeground(new Color(210, 105, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(397, 83, 189, 44);
		contentPane.add(lblNewLabel);
		
		btnTinhluong = new JButton("Ti\u0301nh l\u01B0\u01A1ng");
		btnTinhluong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTinhluong.setBounds(698, 81, 145, 44);
		btnTinhluong.addActionListener(this);
		contentPane.add(btnTinhluong);
		
		btnThoat = new JButton("Thoa\u0301t");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThoat.setBounds(906, 94, 131, 31);
		btnThoat.addActionListener(this);
		contentPane.add(btnThoat);
		
		lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon("C:\\Users\\PC\\OneDrive\\M\u00E1y t\u00EDnh\\QLQ_CAFE\\QLQUANCF\\image\\BLuong.jpg"));
		lbBackground.setBounds(0, 0, 1059, 617);
		contentPane.add(lbBackground);
	}
	
	private JButton btnTinhluong;
	private JButton btnThoat;
	private JLabel lbBackground;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnTinhluong)
		{
			int n = tbLuong.getRowCount();
			Float luong = 0f;
			Integer tsc;
			for(int i = 0; i < n ; i++)
			{
				if(tbLuong.getValueAt(i, 2) == null)
					tsc = 0;
				else tsc = Integer.valueOf((String)tbLuong.getValueAt(i, 2));
				Integer id_nv = (Integer) tbLuong.getValueAt(i, 0);
				
				DecimalFormat df = new DecimalFormat("#.##");
				luong = staff_BLL.getLuong( tsc, id_nv);
				tbLuong.setValueAt(df.format(luong), i, 3);
				
			}
		}
		if(e.getSource() == btnThoat)
		{
			try {
				dispose();
				Manage frame = new Manage(staff);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
