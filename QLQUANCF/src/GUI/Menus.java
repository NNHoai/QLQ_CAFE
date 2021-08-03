package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import BLL.Menu_BLL;
import DTO.Menu;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menus extends JPanel implements ActionListener{
	private JTable tbThucdon;
	private JTextField txtID_td;
	private JTextField txtName_td;
	private JButton btnAdd_td;
	private JButton btnDel_td;
	private JButton btnUpdate_td;
	
	private Menu_BLL menu_BLL;
	/**
	 * Create the panel.
	 */
	public Menus() {
		view();
		set_tbThucdon();
	}
	public void set_tbThucdon() {
			menu_BLL = new Menu_BLL();
			DefaultTableModel d = (DefaultTableModel) tbThucdon.getModel();
			d.setRowCount(0);
			List<Menu> ls = null;
			try
			{
				ls = menu_BLL.getAll();
			}
			catch(Exception e)
			{	}
			
			if(ls != null)
			{
				for(Menu i : ls) {
					d.addRow(new Object[] { i.getId_td(),i.getTen_td() });
				}
			}
	}
	public void setText() {
		int i = tbThucdon.getSelectedRow();
		String id = tbThucdon.getValueAt(i, 0).toString();
		String name = tbThucdon.getValueAt(i, 1).toString();
		txtID_td.setText(id);
		txtName_td.setText(name);
	}
	public void clearText()
	{
		txtID_td.setText("");
		txtName_td.setText("");
	}
	public void view() {
		setBackground(new Color(224, 255, 255));
		setBounds(250, 10, 834, 501);
		setLayout(null);
		
		tbThucdon = new JTable();
		tbThucdon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
				btnDel_td.setEnabled(true);
				btnUpdate_td.setEnabled(true);
			}
		});
		tbThucdon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Th\u01B0\u0323c \u0111\u01A1n", "T\u00EAn th\u01B0\u0323c \u0111\u01A1n"
			}
		));
		tbThucdon.getColumnModel().getColumn(0).setMaxWidth(75);
		
		JScrollPane pnTB = new JScrollPane();
		pnTB.setBounds(10, 10, 422, 481);
		pnTB.setViewportView(tbThucdon);
		
		add(pnTB);
		
		JLabel lblNewLabel_1 = new JLabel("Ma\u0303 th\u01B0\u0323c \u0111\u01A1n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(454, 75, 112, 26);
		add(lblNewLabel_1);
		
		txtID_td = new JTextField();
		txtID_td.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtID_td.setColumns(10);
		txtID_td.setBounds(587, 74, 237, 27);
		add(txtID_td);
		
		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn th\u01B0\u0323c \u0111\u01A1n");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(454, 137, 112, 26);
		add(lblNewLabel_1_1);
		
		txtName_td = new JTextField();
		txtName_td.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtName_td.setColumns(10);
		txtName_td.setBounds(587, 135, 237, 27);
		add(txtName_td);
		
		btnDel_td = new JButton("Xo\u0301a");
		btnDel_td.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDel_td.setBounds(647, 201, 112, 32);
		btnDel_td.setEnabled(false);
		btnDel_td.addActionListener(this);
		add(btnDel_td);
		
		btnAdd_td = new JButton("Th\u00EAm");
		btnAdd_td.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd_td.setBounds(488, 201, 112, 32);
		btnAdd_td.addActionListener(this);
		add(btnAdd_td);
		
		btnUpdate_td = new JButton("S\u01B0\u0309a");
		btnUpdate_td.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate_td.setBounds(570, 274, 112, 32);
		btnUpdate_td.setEnabled(false);
		btnUpdate_td.addActionListener(this);
		add(btnUpdate_td);
		
		JLabel lblNewLabel = new JLabel("Th\u01B0\u0323c \u0111\u01A1n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(527, 10, 183, 45);
		add(lblNewLabel);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnAdd_td)
		{
			if(txtID_td.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = menu_BLL.CheckID(Integer.parseInt(txtID_td.getText()));
				if(t)
					JOptionPane.showMessageDialog(null, "Mã thực đơn đã tồn tại!");
				else {
					Integer id = Integer.parseInt(txtID_td.getText());
					String name = txtName_td.getText();
					Menu menu = new Menu(id, name);
					menu_BLL.add(menu);
					set_tbThucdon();
					clearText();
				}
			}
		}
		if(e.getSource()== btnDel_td)
		{
			if(txtID_td.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = menu_BLL.CheckID(Integer.parseInt(txtID_td.getText()));
				if(t) {
					int i = tbThucdon.getSelectedRow();
					Integer id = (Integer)tbThucdon.getValueAt(i, 0);
					menu_BLL.remove(id);
					set_tbThucdon();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã thực đơn chưa tồn tại!");
			}
		}
		if(e.getSource()== btnUpdate_td)
		{
			if(txtID_td.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = menu_BLL.CheckID(Integer.parseInt(txtID_td.getText()));
				if(t) {
					Integer id = Integer.parseInt(txtID_td.getText());
					String name = txtName_td.getText();
					Menu menu = new Menu(id, name);
					menu_BLL.update(menu);
					set_tbThucdon();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã thực đơn chưa tồn tại!");
			}
		}
	}
}
