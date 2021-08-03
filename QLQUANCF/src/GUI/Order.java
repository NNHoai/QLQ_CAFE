package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.BillDetails_BLL;
import BLL.Bill_BLL;
import BLL.Food_BLL;
import BLL.Menu_BLL;
import BLL.Table_BLL;
import DTO.BillOrder;
import DTO.Food;
import DTO.Menu;
import DTO.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.List;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SpinnerNumberModel;
import DTO.Table;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.awt.event.ItemEvent;
import javax.swing.JEditorPane;

public class Order extends JFrame implements ActionListener, ItemListener{
	private JComboBox<Menu> cbThucdon;
	private JComboBox<Food> cbMonan;
	private JSpinner soluong;
	private JSpinner giamgia;
	private JButton btnAdd;
	private JButton btnCheckout;
	private JButton btnRemove;
	private JButton btnThoat;
	private JTextField txtIDB;
	private JTextField txtTongtien;
	private JPanel contentPane;
	private JPanel pnTables;
	private Table_BLL table_BLL ;
	private Menu_BLL menu_BLL;
	private Food_BLL food_BLL;
	private BillDetails_BLL bd_BLL;
	private Bill_BLL bill_BLL;
	private JTable tbBillDetails;
	private DefaultTableModel d;
	private List<Table> tables;
	private List<BillOrder> ls;
	private static final Staff staff;
	private JEditorPane editorPane;
	private Locale lc ;
	private NumberFormat fm ;
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
					Order frame = new Order(staff);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Order(Staff s) {
		staff.copy(s);
		table_BLL = new Table_BLL();
		menu_BLL = new Menu_BLL();
		food_BLL = new Food_BLL();
		bd_BLL = new BillDetails_BLL();
		bill_BLL = new Bill_BLL();
		lc = new Locale("vi", "VN");
		fm = NumberFormat.getCurrencyInstance(lc);
		view(s);
		loadTable();
		setCBBMenu();
		setCBBFood(food_BLL.getFoodbyIDMenu(1));
		
	}
	public void loadTable() {
		tables = table_BLL.getAll();
		pnTables.removeAll();
		pnTables.updateUI();
		d = (DefaultTableModel)tbBillDetails.getModel();
		for(Table i : tables) {
            JButton Button = setButton(i);
            Button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				d.setRowCount(0);
    				String id = String.valueOf(i.getId_ban());
    				txtIDB.setText(id);
    				ls = bd_BLL.getlsBillDbyIDBill(i.getId_ban());
    				setBillOrder(ls);
    				setEnabled(true);
    				ResetCBB();
    			}
    		});
            pnTables.add(Button);
        }
	}
	public void setBillOrder(List<BillOrder> ls)
	{
		d.setRowCount(0);
		float total = 0;
		for(BillOrder i : ls)
		{
			d.addRow(new Object[] { i.getTen_ma(), i.getSoluong(), i.getDongia(), i.getThanhtien()});
			total += i.getThanhtien().floatValue();
		}
		txtTongtien.setText(fm.format(total));
	}
	public JButton setButton(Table i) {
		 JButton Button = new JButton(i.getTen_ban());
         Button.setBorderPainted(false);
         switch (i.getStatus()) {
         case "Bận":
			Button.setBackground(new Color(255, 0, 102));
			break;

         case "Trống":
			Button.setBackground(new Color(0, 255, 255));
			break;
         }
         Button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
         Button.setPreferredSize(new Dimension(150, 150));
         return Button;
	}
	public void setCBBMenu() {
		for(Menu i : menu_BLL.getAll())
			cbThucdon.addItem(i);
	}
	public void setCBBFood(List<Food> ls) {
		cbMonan.removeAllItems();
		for(Food i : ls)
			cbMonan.addItem(i);
	}
	public void setEnabled(boolean bl)
	{
		btnAdd.setEnabled(bl);
		btnRemove.setEnabled(bl);
		btnCheckout.setEnabled(bl);
	}
	public void ResetCBB()
	{
		cbThucdon.setSelectedIndex(0);
		cbMonan.setSelectedIndex(0);
		soluong.setValue(1);
	}
	/**
	 * Create the frame.
	 */
	public void view(Staff s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1131, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnTables = new JPanel();
		pnTables.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnTables.setBackground(new Color(230, 230, 250));
		pnTables.setLayout(new GridLayout(0,3,4,4));

		JPanel pnBill = new JPanel();
		pnBill.setBackground(new Color(0, 255, 255));
		pnBill.setBounds(538, 0, 579, 639);
		contentPane.add(pnBill);
		pnBill.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Th\u01B0\u0323c \u0111\u01A1n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(41, 56, 96, 20);
		pnBill.add(lblNewLabel);
		
		cbThucdon = new JComboBox<>();
		cbThucdon.addItemListener(this);
		cbThucdon.setBounds(142, 54, 179, 28);
		pnBill.add(cbThucdon);
		
		cbMonan = new JComboBox<>();
		cbMonan.setBounds(142, 92, 179, 28);
		pnBill.add(cbMonan);
		
		JLabel lblMonn = new JLabel("Mo\u0301n \u0103n");
		lblMonn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMonn.setBounds(41, 94, 96, 20);
		pnBill.add(lblMonn);
		
		soluong = new JSpinner();
		soluong.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		soluong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		soluong.setBounds(338, 68, 71, 30);
		pnBill.add(soluong);
		
		btnAdd = new JButton("Th\u00EAm mo\u0301n");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(430, 43, 126, 45);
		btnAdd.addActionListener(this);
		pnBill.add(btnAdd);
		
		tbBillDetails = new JTable();
		tbBillDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tên món", "Số lượng", "Đơn giá", "Thành tiền"
			}
		));
		JScrollPane pnTB = new JScrollPane();
		pnTB.setBounds(10, 130, 559, 420);
		pnTB.setViewportView(tbBillDetails);
		pnBill.add(pnTB);
			
		JLabel lblGiamGia = new JLabel("Giảm giá(%)");
		lblGiamGia.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiamGia.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblGiamGia.setBounds(10, 560, 122, 28);
		pnBill.add(lblGiamGia);
		
		JLabel lblTngTin = new JLabel("T\u00F4\u0309ng ti\u00EA\u0300n");
		lblTngTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTngTin.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblTngTin.setBounds(238, 560, 96, 28);
		pnBill.add(lblTngTin);
		
		txtTongtien = new JTextField("");
		txtTongtien.setEditable(false);
		txtTongtien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTongtien.setColumns(10);
		txtTongtien.setBounds(333, 560, 148, 28);
		pnBill.add(txtTongtien);
		
		btnCheckout = new JButton("Thanh toa\u0301n");
		btnCheckout.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnCheckout.setBounds(102, 598, 307, 39);
		btnCheckout.addActionListener(this);
		pnBill.add(btnCheckout);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u00E2n vi\u00EAn:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(29, 10, 88, 26);
		pnBill.add(lblNewLabel_1);
		
		JTextField textNhanvien = new JTextField(s.getTen_nv());
		textNhanvien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textNhanvien.setEditable(false);
		textNhanvien.setColumns(10);
		textNhanvien.setBounds(141, 10, 268, 26);
		pnBill.add(textNhanvien);
		
		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(this);
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThoat.setBounds(455, 603, 114, 31);
		pnBill.add(btnThoat);
		
		btnRemove = new JButton("Xóa");
		btnRemove.addActionListener(this);
		btnRemove.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnRemove.setBounds(440, 92, 103, 30);
		pnBill.add(btnRemove);
		
		JLabel lblBanS = new JLabel("Bàn số");
		lblBanS.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanS.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBanS.setBounds(419, 13, 82, 20);
		pnBill.add(lblBanS);
		
		txtIDB = new JTextField();
		txtIDB.setHorizontalAlignment(SwingConstants.CENTER);
		txtIDB.setEditable(false);
		txtIDB.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtIDB.setColumns(10);
		txtIDB.setBounds(502, 10, 54, 30);
		pnBill.add(txtIDB);
		
		giamgia = new JSpinner();
		giamgia.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		giamgia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		giamgia.setBounds(136, 558, 71, 30);
		pnBill.add(giamgia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 539, 639);
		scrollPane.setViewportView(pnTables);
		
		editorPane = new JEditorPane();
		editorPane.setText("");
		pnTables.add(editorPane);
		
		contentPane.add(scrollPane);
		setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd)
		{
			int id_b = Integer.parseInt(txtIDB.getText());
			int id_ma = ((Food)cbMonan.getSelectedItem()).getId_ma();
			int sl1 = (int)soluong.getValue();
			int id_nv = staff.getId_nv();
			int id_hd = bill_BLL.CheckBillByIDBan(id_b);
			int id_cthd = bd_BLL.checkID_BD(id_hd, id_ma);
			int sl_add = bd_BLL.getSoluong(id_cthd);
			int sl = sl1 + sl_add;
			if(id_hd != 0 )
			{
				if(id_cthd != 0)
				{
					bd_BLL.update(sl, id_cthd);
				}
				else {
					bd_BLL.add(id_hd, id_ma, sl);
				}
			}
			else {
				bill_BLL.add(id_nv, id_b);
				int idhd = bill_BLL.CheckBillByIDBan(id_b);
				bd_BLL.add(idhd, id_ma, sl);
			}
			ls = bd_BLL.getlsBillDbyIDBill(id_b);
			setBillOrder(ls);
			table_BLL.updateStatusBusy(id_b);
//			tables = table_BLL.getAll();
			loadTable();
		}
		if(e.getSource() == btnRemove)
		{
			int i = tbBillDetails.getSelectedRow();
			if(i >= 0)
			{
				int id_b = Integer.parseInt(txtIDB.getText());
				String name = tbBillDetails.getValueAt(i, 0).toString();
				int id_ma = food_BLL.getIDFoodByName(name);
				int sl = (int)tbBillDetails.getValueAt(i, 1);
				int id_hd = bill_BLL.CheckBillByIDBan(id_b);
				bd_BLL.del(id_hd, id_ma, sl);
				ls = bd_BLL.getlsBillDbyIDBill(id_b);
				setBillOrder(ls);
			}else JOptionPane.showMessageDialog(this,"Chọn món cần xóa");
		}
		if(e.getSource() == btnCheckout)
		{
			int id_b = Integer.parseInt(txtIDB.getText());
			int id_hd = bill_BLL.CheckBillByIDBan(id_b);
			float tt = 0;
			ls = bd_BLL.getlsBillDbyIDBill(id_b);
			int n = ls.size();
			for(int i = 0; i<n; i++)
			{
				float gt = ls.get(i).getThanhtien();
				tt +=gt;
			}
			int gg = (int)giamgia.getValue();
			tt = tt*(1-(float)gg/100);
			JOptionPane.showMessageDialog(this,"Tổng tiền: "+ fm.format(tt));
			table_BLL.updateStatusEmpty(id_b);
			bill_BLL.update(id_hd, tt);
			loadTable();
			ls = bd_BLL.getlsBillDbyIDBill(id_b);
			setBillOrder(ls);
		}
		if(e.getSource() == btnThoat)
		{
			try {
				dispose();
				Main frame = new Main(staff);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		int id = ((Menu)cbThucdon.getSelectedItem()).getId_td();
		List<Food> ls = food_BLL.getFoodbyIDMenu(id);
		setCBBFood(ls);
	}
}
