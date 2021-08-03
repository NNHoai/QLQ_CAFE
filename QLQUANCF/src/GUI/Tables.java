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

import BLL.Table_BLL;
import DTO.Table;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tables extends JPanel implements ActionListener{
	private JTable tbTables;
	private JTextField txtID_b;
	private JTextField txtName_b;
	private Table_BLL table_BLL;
	private JComboBox<String> cbStatus;
	public Tables() {
		table_BLL = new Table_BLL();
		view();
		set_tbTables();
	}
	public void set_tbTables() {
		
		List<Table> ls = null;
		DefaultTableModel d = (DefaultTableModel) tbTables.getModel();
		d.setRowCount(0);
		try
		{
			ls = table_BLL.getAll();
		}
		catch(Exception e)
		{	}
		
		if(ls != null)
		{
			for(Table i : ls) {
				d.addRow(new Object[] { i.getId_ban(), i.getTen_ban(), i.getStatus()});
			}
		}
	}
	public void setText() {
		int i = tbTables.getSelectedRow();
		String id = tbTables.getValueAt(i, 0).toString();
		String name = tbTables.getValueAt(i, 1).toString();
		String status = tbTables.getValueAt(i, 2).toString();
		txtID_b.setText(id);
		txtName_b.setText(name);
		cbStatus.setSelectedItem(status);
	}
	public void clearText() {
		txtID_b.setText("");
		txtName_b.setText("");
		cbStatus.setSelectedIndex(0);
	}
	/**
	 * Create the panel.
	 */
	public void view() {
		setBackground(new Color(224, 255, 255));
		setBounds(250, 10, 834, 501);
		setLayout(null);
		
		tbTables = new JTable();
		tbTables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
				btnDel_b.setEnabled(true);
				btnUpdate_b.setEnabled(true);
			}
		});
		tbTables.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID ba\u0300n", "T\u00EAn ba\u0300n", "Tra\u0323ng tha\u0301i"
			}
		));
		tbTables.getColumnModel().getColumn(0).setMaxWidth(75);
		JScrollPane pnTB = new JScrollPane();
		pnTB.setBounds(10, 10, 407, 481);
		pnTB.setViewportView(tbTables);
		add(pnTB);
		
		JLabel lblNewLabel = new JLabel("Ba\u0300n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(572, 11, 131, 42);
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Ma\u0303 ba\u0300n");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(452, 69, 110, 37);
		add(lblNewLabel_2);
		
		txtID_b = new JTextField();
		txtID_b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtID_b.setColumns(10);
		txtID_b.setBounds(557, 73, 252, 27);
		add(txtID_b);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("T\u00EAn ba\u0300n");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(452, 139, 110, 37);
		add(lblNewLabel_2_1_1);
		
		txtName_b = new JTextField();
		txtName_b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtName_b.setColumns(10);
		txtName_b.setBounds(557, 143, 252, 27);
		add(txtName_b);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Tra\u0323ng tha\u0301i");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(452, 202, 110, 37);
		add(lblNewLabel_2_1_1_1);
		
		cbStatus = new JComboBox<String>();
		cbStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Tr\u00F4\u0301ng", "B\u00E2\u0323n"}));
		cbStatus.setBounds(557, 209, 125, 27);
		add(cbStatus);
		
		btnAdd_b = new JButton("Th\u00EAm");
		btnAdd_b.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd_b.setBounds(445, 277, 110, 39);
		btnAdd_b.addActionListener(this);
		add(btnAdd_b);
		
		btnDel_b = new JButton("Xo\u0301a");
		btnDel_b.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDel_b.setBounds(697, 277, 112, 39);
		btnDel_b.setEnabled(false);
		btnDel_b.addActionListener(this);
		add(btnDel_b);
		
		btnUpdate_b = new JButton("S\u01B0\u0309a");
		btnUpdate_b.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate_b.setBounds(572, 277, 110, 39);
		btnUpdate_b.setEnabled(false);
		btnUpdate_b.addActionListener(this);
		add(btnUpdate_b);
	}
	private JButton btnAdd_b;
	private JButton btnDel_b;
	private JButton btnUpdate_b;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd_b)
		{
			if(txtID_b.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = table_BLL.CheckID(Integer.parseInt(txtID_b.getText()));
				if(t)
					JOptionPane.showMessageDialog(null, "Mã bàn đã tồn tại!");
				else {
					Integer id = Integer.parseInt(txtID_b.getText());
					String name = txtName_b.getText();
					String status = cbStatus.getSelectedItem().toString();
					Table table = new Table(id, name, status);
					table_BLL.add(table);
					set_tbTables();
					clearText();
				}
			}
		}
		if(e.getSource()== btnDel_b)
		{
			if(txtID_b.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = table_BLL.CheckID(Integer.parseInt(txtID_b.getText()));
				if(t) {
					int i = tbTables.getSelectedRow();
					Integer id = (Integer)tbTables.getValueAt(i, 0);
					table_BLL.remove(id);
					set_tbTables();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã bàn chưa tồn tại!");
			}
		}
		if(e.getSource()== btnUpdate_b)
		{
			if(txtID_b.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = table_BLL.CheckID(Integer.parseInt(txtID_b.getText()));
				if(t) {
					Integer id = Integer.parseInt(txtID_b.getText());
					String name = txtName_b.getText();
					String status = cbStatus.getSelectedItem().toString();
					Table table = new Table(id, name, status);
					table_BLL.update(table);
					set_tbTables();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã bàn chưa tồn tại!");
			}
		}
	}

}
