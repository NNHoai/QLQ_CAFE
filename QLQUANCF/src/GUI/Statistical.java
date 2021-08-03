package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import BLL.Bill_BLL;
import DTO.Bill;
import DTO.Staff;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Statistical extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable tbBills;
	private Bill_BLL bill_BLL;
	private List<Bill> ls;
	private DefaultTableModel d;
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
					Statistical frame = new Statistical(staff);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Statistical(Staff s) {
		staff.copy(s);
		bill_BLL = new Bill_BLL();
		try
		{
			ls = bill_BLL.getAll();
		}
		catch(Exception e)
		{	}
		
		view(s);
		set_tbBills();
	}
	
	public void set_tbBills() {
		d = (DefaultTableModel) tbBills.getModel();
		d.setRowCount(0);
		if(ls != null)
		{
			for(Bill i : ls) {
				d.addRow(new Object[] { i.getId_hd(), i.getId_nv(), i.getId_b(), i.getTongtien(), i.getNgay(), i.getStatus()});
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public void view(Staff s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 738);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tbBills = new JTable();
		tbBills.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID ho\u0301a \u0111\u01A1n", "ID nh\u00E2n vi\u00EAn", "ID ba\u0300n", "T\u00F4\u0309ng ti\u00EA\u0300n", "Nga\u0300y", "Tra\u0323ng tha\u0301i"
			}
		));
		tbBills.getColumnModel().getColumn(0).setMaxWidth(80);
		tbBills.getColumnModel().getColumn(1).setMaxWidth(80);
		tbBills.getColumnModel().getColumn(2).setMaxWidth(80);

		JScrollPane pnTB = new JScrollPane();
		pnTB.setBounds(10, 171, 855, 520);
		pnTB.setViewportView(tbBills);
		getContentPane().add(pnTB);
		
		JLabel lblNewLabel = new JLabel("T\u01B0\u0300 nga\u0300y");
		lblNewLabel.setBounds(23, 68, 84, 24);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		dateStart = new JDateChooser();
		dateStart.setDateFormatString("dd-MM-yyyy");
		dateStart.setBounds(117, 68, 209, 24);
		java.util.Date date=new java.util.Date();
		dateStart.setDate(date);
		contentPane.add(dateStart);
		
		JLabel lblnNgay = new JLabel("\u0110\u00EA\u0301n nga\u0300y");
		lblnNgay.setBounds(411, 68, 84, 24);
		lblnNgay.setHorizontalAlignment(SwingConstants.CENTER);
		lblnNgay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblnNgay);
		
		dateEnd = new JDateChooser();
		dateEnd.setDateFormatString("dd-MM-yyyy");
		dateEnd.setBounds(505, 68, 209, 24);
		dateEnd.setDate(date);
		contentPane.add(dateEnd);
		
		btnSort = new JButton("S\u0103\u0301p x\u00EA\u0301p");
		btnSort.setBounds(411, 110, 120, 36);
		btnSort.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSort.addActionListener(this);
		contentPane.add(btnSort);
		
		btnThongke = new JButton("Th\u00F4\u0301ng k\u00EA");
		btnThongke.setBounds(594, 110, 120, 36);
		btnThongke.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThongke.addActionListener(this);
		contentPane.add(btnThongke);
		
		JCheckBox chbASC = new JCheckBox("T\u0103ng d\u00E2\u0300n");
		chbASC.setBounds(32, 113, 111, 31);
		chbASC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chbASC.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(chbASC);
		
		JCheckBox chbDEC = new JCheckBox("Gia\u0309m d\u00E2\u0300n");
		chbDEC.setBounds(215, 113, 111, 31);
		chbDEC.setHorizontalAlignment(SwingConstants.CENTER);
		chbDEC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(chbDEC);
		
		btnExit = new JButton("Thoa\u0301t");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExit.setBounds(745, 10, 120, 36);
		btnExit.addActionListener(this);
		contentPane.add(btnExit);
		
		JLabel lblThngKHoa = new JLabel("Th\u00F4\u0301ng k\u00EA ho\u0301a \u0111\u01A1n");
		lblThngKHoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKHoa.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblThngKHoa.setBounds(308, 10, 234, 48);
		contentPane.add(lblThngKHoa);
		
		btnReload = new JButton("Tất cả");
		btnReload.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReload.setBounds(745, 110, 120, 36);
		btnReload.addActionListener(this);
		contentPane.add(btnReload);
	}
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	public void thongke()
	{
		Date start = dateStart.getDate();
//		Date st = new java.sql.Date(start.getTime());
		Date end = dateEnd.getDate();
//		Date en = new java.sql.Date(end.getTime());
		int n = tbBills.getRowCount();
		int j = 0;
		for(int i = 0; i < n; i++)
		{
			Date date = (Date)tbBills.getValueAt(i-j, 4);
			if(checkDate(date, start, end)) {
				d.removeRow(i-j);
				j++;
			}
		}
	}
	public boolean checkDate(Date date, Date start, Date end)
	{
		if(date.equals(start))
			return false;
		if(date.equals(end))
			return false;
		if(date.after(start) && date.before(end))
			return false;
		return true;
	}
	private JButton btnSort;
	private JButton btnThongke;
	private JButton btnExit;
	private JButton btnReload;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnThongke)
		{	
				thongke();
		}
		if(e.getSource() == btnSort)
		{
			
		}
		if(e.getSource() == btnExit)
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
		if(e.getSource() == btnReload)
		{
			set_tbBills();
		}
	}
}
