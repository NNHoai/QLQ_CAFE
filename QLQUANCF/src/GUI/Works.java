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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import BLL.Work_BLL;
import DTO.Work;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Works extends JPanel implements ActionListener{
	private JTable tbWork;
	private JTextField txtID_cv;
	private JTextField txtName_cv;
	private JTextField txtLuong;
	private Work_BLL work_BLL;
	public Works() {
		work_BLL = new Work_BLL();
		view();
		set_tbWork();
	}
	public void set_tbWork() {
		List<Work> ls = null;
		DefaultTableModel d = (DefaultTableModel) tbWork.getModel();
		d.setRowCount(0);
		try
		{
			ls = work_BLL.getAll();
		}
		catch(Exception e)
		{	}
		
		if(ls != null)
		{
			for(Work i : ls) {
				d.addRow(new Object[] { i.getId_cv(), i.getTen_cv(), i.getLuong()});
			}
		}
	}
	public void setText() {
		int i = tbWork.getSelectedRow();
		String id = tbWork.getValueAt(i, 0).toString();
		String name = tbWork.getValueAt(i, 1).toString();
		String salary = tbWork.getValueAt(i, 2).toString();
		txtID_cv.setText(id);
		txtName_cv.setText(name);
		txtLuong.setText(salary);
	}
	public void clearText() {
		txtID_cv.setText("");
		txtName_cv.setText("");
		txtLuong.setText("");
	}
	/**
	 * Create the panel.
	 */
	public void view() {
		setBackground(new Color(224, 255, 255));
		setBounds(250, 10, 834, 501);
		setLayout(null);
		
		tbWork = new JTable();
		tbWork.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
				btnDel_cv.setEnabled(true);
				btnUpdate_cv.setEnabled(true);
			}
		});
		tbWork.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID c\u00F4ng vi\u00EA\u0323c", "T\u00EAn c\u00F4ng vi\u00EA\u0323c", "L\u01B0\u01A1ng c\u01A1 ba\u0309n"
			}
		));
		JScrollPane pnTB = new JScrollPane();
		pnTB.setBounds(0, 10, 422, 481);
		pnTB.setViewportView(tbWork);
		add(pnTB);
		
		JLabel lblNewLabel_1 = new JLabel("Ma\u0303 c\u00F4ng vi\u00EA\u0323c");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(454, 75, 112, 26);
		add(lblNewLabel_1);
		
		txtID_cv = new JTextField();
		txtID_cv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtID_cv.setColumns(10);
		txtID_cv.setBounds(587, 74, 237, 27);
		add(txtID_cv);
		
		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn c\u00F4ng vi\u00EA\u0323c");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(454, 137, 112, 26);
		add(lblNewLabel_1_1);
		
		txtName_cv = new JTextField();
		txtName_cv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtName_cv.setColumns(10);
		txtName_cv.setBounds(587, 135, 237, 27);
		add(txtName_cv);
		
		btnDel_cv = new JButton("Xo\u0301a");
		btnDel_cv.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDel_cv.setBounds(654, 254, 112, 32);
		btnDel_cv.addActionListener(this);
		add(btnDel_cv);
		
		btnAdd_cv = new JButton("Th\u00EAm");
		btnAdd_cv.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd_cv.setBounds(495, 254, 112, 32);
		btnAdd_cv.addActionListener(this);
		btnDel_cv.setEnabled(false);
		add(btnAdd_cv);
		
		btnUpdate_cv = new JButton("S\u01B0\u0309a");
		btnUpdate_cv.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate_cv.setBounds(577, 327, 112, 32);
		btnUpdate_cv.addActionListener(this);
		btnUpdate_cv.setEnabled(false);
		add(btnUpdate_cv);
		
		JLabel lblNewLabel = new JLabel("C\u00F4ng vi\u00EA\u0323c");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(527, 10, 183, 45);
		add(lblNewLabel);
		
		txtLuong = new JTextField();
		txtLuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtLuong.setColumns(10);
		txtLuong.setBounds(587, 194, 237, 27);
		add(txtLuong);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("L\u01B0\u01A1ng");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(454, 196, 112, 26);
		add(lblNewLabel_1_1_1);

	}
	private JButton btnDel_cv;
	private JButton btnAdd_cv;
	private JButton btnUpdate_cv;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd_cv)
		{
			if(txtID_cv.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = work_BLL.CheckID(Integer.parseInt(txtID_cv.getText()));
				if(t)
					JOptionPane.showMessageDialog(null, "Mã công việc đã tồn tại!");
				else {
					Integer id = Integer.parseInt(txtID_cv.getText());
					String name = txtName_cv.getText();
					Float salary;
					if(txtLuong.getText().equals(""))
						salary = 0f;
					else {
						salary = Float.parseFloat(txtLuong.getText());
					}
					Work work = new Work(id, name, salary);
					work_BLL.add(work);
					set_tbWork();
					clearText();
				}
			}
		}
		if(e.getSource()== btnDel_cv)
		{
			if(txtID_cv.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = work_BLL.CheckID(Integer.parseInt(txtID_cv.getText()));
				if(t) {
					int i = tbWork.getSelectedRow();
					Integer id = (Integer)tbWork.getValueAt(i, 0);
					work_BLL.remove(id);
					set_tbWork();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã công việc chưa tồn tại!");
			}
		}
		if(e.getSource()== btnUpdate_cv)
		{
			if(txtID_cv.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = work_BLL.CheckID(Integer.parseInt(txtID_cv.getText()));
				if(t) {
					Integer id = Integer.parseInt(txtID_cv.getText());
					String name = txtName_cv.getText();
					Float salary;
					if(txtLuong.getText().equals(""))
						salary = 0f;
					else {
						salary = Float.parseFloat(txtLuong.getText());
					}
					Work work = new Work(id, name, salary);
					work_BLL.update(work);
					set_tbWork();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã công việc chưa tồn tại!");
			}
		}
		
	}
}
