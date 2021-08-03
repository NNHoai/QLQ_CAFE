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

import BLL.Food_BLL;
import BLL.Menu_BLL;
import DTO.Food;
import DTO.Menu;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Foods extends JPanel implements ActionListener{
	private JTable tbFood;
	private JTextField txtIDmon;
	private JTextField txtTenmon;
	private JTextField txtDongia;
	private JComboBox<Menu> cbnName_td;
	private Food_BLL food_BLL;
	private Menu_BLL menu_BLL;
	public Foods() {
		menu_BLL = new Menu_BLL();
		food_BLL = new Food_BLL();
		view();
		set_tbFood();
		setCBB();
	}
	
	public void set_tbFood() {
		List<Food> ls = null;
		DefaultTableModel d = (DefaultTableModel) tbFood.getModel();
		d.setRowCount(0);
		try
		{
			ls = food_BLL.getAll();
		}
		catch(Exception e)
		{	}
		
		if(ls != null)
		{
			for(Food i : ls) {
				d.addRow(new Object[] { i.getId_ma(), i.getId_td(), i.getTen_ma(), i.getDongia() });
			}
		}
	}
	public void setText() {
		int i = tbFood.getSelectedRow();
		String id = tbFood.getValueAt(i, 0).toString();
		Integer id_td = (Integer)tbFood.getValueAt(i, 1);
		String name_td = menu_BLL.getNameMenu(id_td);
		String name = tbFood.getValueAt(i, 2).toString();
		String price = tbFood.getValueAt(i, 3).toString();
		txtIDmon.setText(id);
		txtTenmon.setText(name);
		cbnName_td.setSelectedItem(name_td);
		txtDongia.setText(price);
		
	}
	public void setCBB() {
		for(Menu i : menu_BLL.getAll())
			cbnName_td.addItem(i);
	}
	public void clearText() {
		txtIDmon.setText("");
		txtTenmon.setText("");
		txtDongia.setText("");
		cbnName_td.setSelectedIndex(0);
	}
	/**
	 * Create the panel.
	 */
	public void view() {
		setBackground(new Color(224, 255, 255));
		setBounds(250, 10, 834, 501);
		setLayout(null);
		
		tbFood = new JTable();
		tbFood.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
				btnUpdate_ma.setEnabled(true);
				btnDel_ma.setEnabled(true);
			}
		});
		tbFood.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID mo\u0301n \u0103n", "ID th\u01B0\u0323c \u0111\u01A1n", "T\u00EAn mo\u0301n \u0103n", "\u0110\u01A1n gia\u0301"
			}
		));
		tbFood.getColumnModel().getColumn(0).setMaxWidth(75);
		tbFood.getColumnModel().getColumn(1).setMaxWidth(75);
		
		JScrollPane pnTB = new JScrollPane();
		pnTB.setBounds(10, 10, 432, 481);
		pnTB.setViewportView(tbFood);
		add(pnTB);
		
		JLabel lblNewLabel_2 = new JLabel("Ma\u0303 mo\u0301n \u0103n");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(452, 64, 110, 37);
		add(lblNewLabel_2);
		
		txtIDmon = new JTextField();
		txtIDmon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				cbnName_td.setSelectedIndex(0);
				txtTenmon.setText("");
				txtDongia.setText("");
			}
		});
		txtIDmon.setToolTipText("Nhập ID món ở đây");
		txtIDmon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtIDmon.setColumns(10);
		txtIDmon.setBounds(572, 68, 252, 27);
		add(txtIDmon);
		
		JLabel lblMonn = new JLabel("Mo\u0301n \u0103n");
		lblMonn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblMonn.setBounds(529, 10, 201, 44);
		add(lblMonn);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tên thực đơn");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(452, 124, 110, 37);
		add(lblNewLabel_2_1);
		
		cbnName_td = new JComboBox<Menu>();
		cbnName_td.setEditable(true);
		cbnName_td.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbnName_td.setBounds(572, 130, 252, 27);
		add(cbnName_td);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("T\u00EAn mo\u0301n \u0103n");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(452, 192, 110, 37);
		add(lblNewLabel_2_1_1);
		
		txtTenmon = new JTextField();
		txtTenmon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenmon.setColumns(10);
		txtTenmon.setBounds(572, 196, 252, 27);
		add(txtTenmon);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("\u0110\u01A1n gia\u0301");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1_2.setBounds(452, 256, 110, 37);
		add(lblNewLabel_2_1_2);
		
		txtDongia = new JTextField();
		txtDongia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDongia.setColumns(10);
		txtDongia.setBounds(572, 260, 252, 27);
		add(txtDongia);
		
		btnAdd_ma = new JButton("Th\u00EAm");
		btnAdd_ma.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd_ma.setBounds(468, 319, 112, 32);
		btnAdd_ma.addActionListener(this);
		add(btnAdd_ma);
		
		btnDel_ma = new JButton("Xo\u0301a");
		btnDel_ma.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDel_ma.setBounds(590, 319, 112, 32);
		btnDel_ma.addActionListener(this);
		btnDel_ma.setEnabled(false);
		add(btnDel_ma);
		
		btnUpdate_ma = new JButton("S\u01B0\u0309a");
		btnUpdate_ma.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate_ma.setBounds(712, 319, 112, 32);
		btnUpdate_ma.addActionListener(this);
		btnUpdate_ma.setEnabled(false);
		add(btnUpdate_ma);
		
		btnSort_ma = new JButton("S\u0103\u0301p x\u00EA\u0301p");
		btnSort_ma.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSort_ma.setBounds(650, 375, 112, 32);
		btnSort_ma.addActionListener(this);
		add(btnSort_ma);
		
		btnSearch_ma = new JButton("Ti\u0300m ki\u00EA\u0301m");
		btnSearch_ma.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSearch_ma.setBounds(528, 375, 112, 32);
		btnSearch_ma.addActionListener(this);
		add(btnSearch_ma);
	}
	private JButton btnAdd_ma;
	private JButton btnDel_ma;
	private JButton btnUpdate_ma;
	private JButton btnSort_ma;
	private JButton btnSearch_ma;
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnAdd_ma)
		{
			
			if(txtIDmon.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = food_BLL.CheckID(Integer.parseInt(txtIDmon.getText()));
				if(t)
					JOptionPane.showMessageDialog(null, "Mã món ăn đã tồn tại!");
				else {
					Integer id = Integer.parseInt(txtIDmon.getText());
					Integer id_td = ((Menu)cbnName_td.getSelectedItem()).getId_td();
					String name = txtTenmon.getText();
					Float price;
					if(txtDongia.getText().equals(""))
						price = 0f;
					else {
						price = Float.parseFloat(txtDongia.getText());
					}
					Food food = new Food(id,id_td,name,price);
					food_BLL.add(food);
					set_tbFood();
					clearText();
				}
			}
		}
		if(e.getSource()== btnDel_ma)
		{
			if(txtIDmon.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = food_BLL.CheckID(Integer.parseInt(txtIDmon.getText()));
				if(t) {
					int i = tbFood.getSelectedRow();
					Integer id = (Integer)tbFood.getValueAt(i, 0);
					food_BLL.remove(id);
					set_tbFood();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã món ăn chưa tồn tại!");
			}
			
		}
		if(e.getSource()== btnUpdate_ma)
		{
			if(txtIDmon.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nhập thông tin!");
			else {
				boolean t = food_BLL.CheckID(Integer.parseInt(txtIDmon.getText()));
				if(t) {
					Integer id = Integer.parseInt(txtIDmon.getText());
					Integer id_td = ((Menu)cbnName_td.getSelectedItem()).getId_td();
					String name = txtTenmon.getText();
					Float price;
					if(txtDongia.getText().equals(""))
						price = 0f;
					else {
						price = Float.parseFloat(txtDongia.getText().toString());
					}
					Food food = new Food(id,id_td,name,price);
					food_BLL.update(food);
					set_tbFood();
					clearText();
				}
				else JOptionPane.showMessageDialog(null, "Mã món ăn chưa tồn tại!");
			}
			
		}
		if(e.getSource()== btnSort_ma)
		{
			
		}
		if(e.getSource()== btnSearch_ma)
		{
			
		}
	}

}
