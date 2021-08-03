package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BLL.Staff_BLL;
import BLL.Work_BLL;
import DTO.Staff;
import DTO.Work;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class Staffs extends JPanel implements ActionListener{
	private JTable tbStaff;
	private JTextField txtManv;
	private JTextField txtTennv;
	private JTextField txtLienhe;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JComboBox<Work> cbTencv;
	private JTextPane txtAddress;
	private Staff_BLL staff_BLL;
	private Work_BLL work_BLL;
	public Staffs() {
		staff_BLL = new Staff_BLL();
		work_BLL = new Work_BLL();
		view();
		set_tbStaff();
		setCBB();
	}
	
	public void set_tbStaff() {
		List<Staff> ls = null;
		DefaultTableModel d = (DefaultTableModel) tbStaff.getModel();
		d.setRowCount(0);
		TableRowSorter<DefaultTableModel> srt = new TableRowSorter<DefaultTableModel>(d);
		tbStaff.setRowSorter(srt);
		try
		{
			ls = staff_BLL.getAll();
		}
		catch(Exception e)
		{	}
		
		if(ls != null)
		{
			for(Staff i : ls) {
				d.addRow(new Object[] { i.getId_nv(), i.getId_cv(), i.getTen_nv(), i.getLienhe(), i.getDiachi(), i.getUsername(), i.getPassword()});
			}
		}
	}
	public void setText() {
		int i = tbStaff.getSelectedRow();
		String id = tbStaff.getValueAt(i, 0).toString();
		Integer id_cv = (Integer)tbStaff.getValueAt(i, 1);
		String name_cv = work_BLL.getNameWork(id_cv);
		String name = tbStaff.getValueAt(i, 2).toString();
		String lh = tbStaff.getValueAt(i, 3).toString();
		String dc = tbStaff.getValueAt(i, 4).toString();
		String tk = tbStaff.getValueAt(i, 5).toString();
		String mk = tbStaff.getValueAt(i, 6).toString();
		txtManv.setText(id);
		cbTencv.setSelectedItem(name_cv);
		txtTennv.setText(name);
		txtLienhe.setText(lh);
		txtAddress.setText(dc);
		txtUsername.setText(tk);
		txtPassword.setText(mk);
		}
	public void setCBB() {
		for(Work i: work_BLL.getAll())
		{
			cbTencv.addItem(i);
		}
	}
	public void clearText() {
		txtManv.setText("");
		cbTencv.setSelectedIndex(0);
		txtTennv.setText("");
		txtLienhe.setText("");
		txtAddress.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
	}
	/**
	 * Create the panel.
	 */
	public void view() {
		setBackground(new Color(224, 255, 255));
		setBounds(250, 10, 834, 501);
		setLayout(null);
		tbStaff = new JTable();
		tbStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
				btnDel_nv.setEnabled(true);
				btnUpdate_nv.setEnabled(true);
				
			}
		});
		tbStaff.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID nh\u00E2n vi\u00EAn", "ID c\u00F4ng vi\u00EA\u0323c", "T\u00EAn nh\u00E2n vi\u00EAn", "Li\u00EAn h\u00EA\u0323", "\u0110i\u0323a chi\u0309", "Ta\u0300i khoa\u0309n", "M\u00E2\u0323t kh\u00E2\u0309u", "H\u00EA\u0323 s\u00F4\u0301 l\u01B0\u01A1ng", "Ca sa\u0301ng", "Ca chi\u00EA\u0300u", "Ca t\u00F4\u0301i"
			}
		));
		tbStaff.getColumnModel().getColumn(0).setMaxWidth(75);
		tbStaff.getColumnModel().getColumn(1).setMaxWidth(75);
		tbStaff.getColumnModel().getColumn(2).setPreferredWidth(150);
		tbStaff.getColumnModel().getColumn(2).setMaxWidth(200);
		tbStaff.getColumnModel().getColumn(3).setPreferredWidth(90);
		tbStaff.getColumnModel().getColumn(3).setMaxWidth(90);
		tbStaff.getColumnModel().getColumn(4).setPreferredWidth(200);
		tbStaff.getColumnModel().getColumn(5).setPreferredWidth(120);
		tbStaff.getColumnModel().getColumn(6).setPreferredWidth(200);
		tbStaff.getColumnModel().getColumn(7).setPreferredWidth(70);
		tbStaff.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane pnTB = new JScrollPane();
		pnTB.setAutoscrolls(true);
		pnTB.setBounds(10, 278, 814, 213);
		pnTB.setViewportView(tbStaff);
		add(pnTB);
		
		JLabel lblNhnVin = new JLabel("Nh\u00E2n vi\u00EAn");
		lblNhnVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVin.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNhnVin.setBounds(293, 0, 201, 44);
		add(lblNhnVin);
		
		txtManv = new JTextField();
		txtManv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				cbTencv.setSelectedIndex(0);
				txtTennv.setText("");
				txtLienhe.setText("");
				txtAddress.setText("");
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		txtManv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtManv.setColumns(10);
		txtManv.setBounds(130, 44, 97, 27);
		add(txtManv);
		
		JLabel lblNewLabel_2 = new JLabel("Ma\u0303 nh\u00E2n vi\u00EAn");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 40, 110, 37);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tên công việc");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(2, 80, 118, 37);
		add(lblNewLabel_2_1);
		
		cbTencv = new JComboBox<Work>();
		cbTencv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbTencv.setBounds(130, 87, 128, 27);
		cbTencv.setEditable(true);
		add(cbTencv);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("T\u00EAn nh\u00E2n vi\u00EAn ");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(250, 40, 118, 37);
		add(lblNewLabel_2_1_1);
		
		txtTennv = new JTextField();
		txtTennv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTennv.setColumns(10);
		txtTennv.setBounds(366, 44, 252, 27);
		add(txtTennv);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Li\u00EAn h\u00EA\u0323");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_2.setBounds(268, 81, 79, 37);
		add(lblNewLabel_2_1_2);
		
		txtLienhe = new JTextField();
		txtLienhe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtLienhe.setColumns(10);
		txtLienhe.setBounds(366, 85, 252, 27);
		add(txtLienhe);
		
		btnAdd_nv = new JButton("Th\u00EAm");
		btnAdd_nv.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd_nv.setBounds(671, 40, 112, 32);
		add(btnAdd_nv);
		
		btnDel_nv = new JButton("Xo\u0301a");
		btnDel_nv.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDel_nv.setBounds(671, 92, 112, 32);
		btnDel_nv.setEnabled(false);
		add(btnDel_nv);
		
		btnUpdate_nv = new JButton("S\u01B0\u0309a");
		btnUpdate_nv.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate_nv.setBounds(671, 144, 112, 32);
		btnUpdate_nv.setEnabled(false);
		add(btnUpdate_nv);
		
		txtAddress = new JTextPane();
		txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtAddress.setBounds(75, 128, 194, 63);
		add(txtAddress);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("\u0110i\u0323a chi\u0309");
		lblNewLabel_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_2_1.setBounds(2, 122, 74, 37);
		add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Ta\u0300i khoa\u0309n");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(268, 122, 94, 37);
		add(lblNewLabel_2_1_1_1);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtUsername.setColumns(10);
		txtUsername.setBounds(366, 128, 252, 27);
		add(txtUsername);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("M\u00E2\u0323t kh\u00E2\u0309u");
		lblNewLabel_2_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_2_2.setBounds(268, 155, 90, 37);
		add(lblNewLabel_2_1_2_2);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(366, 165, 252, 27);
		add(txtPassword);
		
		JLabel lblNewLabel_2_1_2_2_1 = new JLabel("Ca làm");
		lblNewLabel_2_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_2_2_1.setBounds(2, 201, 80, 37);
		add(lblNewLabel_2_1_2_2_1);
		
		chbSang = new JCheckBox("Sáng(7h - 12h)");
		chbSang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chbSang.setBounds(86, 205, 151, 27);
		add(chbSang);
		
		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("Hệ số lương");
		lblNewLabel_2_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_2_1_1.setBounds(10, 236, 110, 37);
		add(lblNewLabel_2_1_2_1_1);
		
		chbChieu = new JCheckBox("Chiều(12h - 17h)");
		chbChieu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chbChieu.setBounds(268, 205, 165, 27);
		add(chbChieu);
		
		chbToi = new JCheckBox("Tối(17h - 22h)");
		chbToi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chbToi.setBounds(467, 205, 151, 27);
		add(chbToi);
		
		txtHSL = new JTextField();
		txtHSL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtHSL.setColumns(10);
		txtHSL.setBounds(128, 241, 74, 27);
		add(txtHSL);
		
		btnAA = new JButton("Thêm");
		btnAA.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAA.setBounds(671, 211, 112, 32);
		add(btnAA);
	}
	private JCheckBox chbSang;
	private JCheckBox chbChieu;
	private JCheckBox chbToi;
	private JButton btnAdd_nv;
	private JButton btnDel_nv;
	private JButton btnUpdate_nv;
	private JTextField txtHSL;
	
	private JButton btnAA;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd_nv)
		{
			if(txtManv.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = staff_BLL.CheckID(Integer.parseInt(txtManv.getText()));
				if(t)
					JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại!");
				else {
					Integer id = Integer.parseInt(txtManv.getText());
					String name = txtTennv.getText();
					Integer idcv = ((Work)cbTencv.getSelectedItem()).getId_cv();
					String lh = txtLienhe.getText();
					String dc = txtAddress.getText();
					String username = txtUsername.getText();
					String password = txtPassword.getText();
					Float hsl = Float.parseFloat(txtHSL.getText());
					int cs = 0, cc = 0, ct = 0;
					if(chbSang.isSelected() == true)
						cs = 1;
					if(chbChieu.isSelected() == true)
						cc = 1;
					if(chbToi.isSelected() == true)
						ct = 1;
					Staff staff = new Staff(id,idcv,name,lh,dc,username,password, hsl, cs, cc, ct);
					staff_BLL.add(staff);
					set_tbStaff();
					clearText();
				}
			}
		}
		if(e.getSource()== btnDel_nv)
		{
			if(txtManv.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = staff_BLL.CheckID(Integer.parseInt(txtManv.getText()));
				if(t) {
					int i = tbStaff.getSelectedRow();
					Integer id = (Integer)tbStaff.getValueAt(i, 0);
					staff_BLL.remove(id);
					set_tbStaff();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã nhân viên chưa tồn tại!");
			}
		}
		if(e.getSource()== btnUpdate_nv)
		{
			if(txtManv.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = staff_BLL.CheckID(Integer.parseInt(txtManv.getText()));
				if(t) {
					Integer id = Integer.parseInt(txtManv.getText());
					String name = txtTennv.getText();
					Integer idcv = ((Work)cbTencv.getSelectedItem()).getId_cv();
					String lh = txtLienhe.getText();
					String dc = txtAddress.getText();
					String username = txtUsername.getText();
					String password = txtPassword.getText();
					Float hsl = Float.parseFloat(txtHSL.getText());
					int cs = 0, cc = 0, ct = 0;
					if(chbSang.isSelected() == true)
						cs = 1;
					if(chbChieu.isSelected() == true)
						cc = 1;
					if(chbToi.isSelected() == true)
						ct = 1;
					Staff staff = new Staff(id,idcv,name,lh,dc,username,password, hsl, cs, cc, ct);
					staff_BLL.update(staff);
					set_tbStaff();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã nhân viên chưa tồn tại!");
			}
		}
		if(e.getSource() == btnAA)
		{
			JOptionPane.showMessageDialog(null, chbSang.isSelected() + " " + chbChieu.isSelected());
		}
	
	}
}
