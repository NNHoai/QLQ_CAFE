package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Info_BLL;
import DTO.Staff;
import DTO.info;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Infos extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtNameShop;
	private JTextField txtLienhe;
	private JTextPane txtDiachi;
	private Info_BLL info_BLL;
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
					Infos frame = new Infos(staff);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Infos(Staff s) {
		staff.copy(s);
		info_BLL = new Info_BLL();
		view(s);
		setText();
	}
	public void setText()
	{
		info in = info_BLL.getInfo();
		txtNameShop.setText(in.getTen());
		txtLienhe.setText(in.getLienhe());
		txtDiachi.setText(in.getDiachi());
	}
	/**
	 * Create the frame.
	 */
	public void view(Staff s) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Th\u00F4ng tin c\u01B0\u0309a ha\u0300ng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(188, 10, 369, 67);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn c\u01B0\u0309a ha\u0300ng");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(64, 76, 156, 26);
		contentPane.add(lblNewLabel_1);
		
		txtNameShop = new JTextField();
		txtNameShop.setBounds(244, 79, 378, 26);
		contentPane.add(txtNameShop);
		txtNameShop.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Li\u00EAn h\u00EA\u0323");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(43, 145, 156, 26);
		contentPane.add(lblNewLabel_1_1);
		
		txtLienhe = new JTextField();
		txtLienhe.setColumns(10);
		txtLienhe.setBounds(244, 148, 378, 26);
		contentPane.add(txtLienhe);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u0110i\u0323a chi\u0309");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(43, 222, 156, 26);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtDiachi = new JTextPane();
		txtDiachi.setBounds(244, 222, 378, 137);
		contentPane.add(txtDiachi);
		
		btnExit = new JButton("Thoa\u0301t");
		btnExit.addActionListener(this);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExit.setBounds(669, 600, 94, 38);
		contentPane.add(btnExit);
		
		btnUpdate = new JButton("C\u00E2\u0323p nh\u00E2\u0323t");
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(383, 396, 115, 38);
		btnUpdate.addActionListener(this);
		if(s.getId_cv() != 1) btnUpdate.setEnabled(false);
		contentPane.add(btnUpdate);
	}
	private JButton btnUpdate;
	private JButton btnExit;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnUpdate) {
			if(txtNameShop.getText().equals("")) 
				JOptionPane.showMessageDialog(null, "Nhập thông tin tên cửa hàng!");
			else
			{
				String name = txtNameShop.getText();
				String lh = txtLienhe.getText();
				String dc = txtDiachi.getText();
				info in = new info(1, name, lh, dc);
				info_BLL.update(in);
				setText();
				JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
			}
			
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
		
	}
}
