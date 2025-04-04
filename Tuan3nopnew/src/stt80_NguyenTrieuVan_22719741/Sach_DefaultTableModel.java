package stt80_NguyenTrieuVan_22719741;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Sach_DefaultTableModel extends JFrame implements ActionListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sach_Collection dsSach;
	private DefaultTableModel modelSach;
	
	private JPanel pCenter;
	private JTextField txtmaSach;
	private JTextField txttuaSach;
	private JTextField txttacGia;
	private JTextField txtnamXB;
	private JTextField txtdonGia;
	private JTextField txtISBN;
	private JTextField txtsoTrang;
	private JTextField txtnhaXB;
	private JButton btnThem;
	private JButton btnXoaRong;
	private JButton btnSua;
	private JComboBox cboTimMaSach;
	private JTable table;
	private JLabel lbmaSach;
	private JLabel lbtuaSach;
	private JLabel lbtacGia;
	private JLabel lbnhaXB;
	private JLabel lbnamXB;
	private JLabel lbsoTrang;
	private JLabel lbdonGia;
	private JButton btnXoa;
	private JButton btnLuu;
	private Sach_DefaultTableModel listSach;
	
	

	public Sach_DefaultTableModel() {
		super("Quản lý sách");
		setSize(950, 550);
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		

        // ======= 2. Khu vực nhập dữ liệu ======= vung center
        JPanel pCenter = new JPanel();
        Font font2 = new Font("Aria", Font.BOLD, 13);
        
        pCenter.setBorder(BorderFactory.createTitledBorder("Records Editor"));

     // Mã sách
        Box hbox1 = Box.createHorizontalBox();
        hbox1.add(lbmaSach = new JLabel("Mã sách:"));
        hbox1.add(Box.createHorizontalStrut(40));
        lbmaSach.setFont(font2);
        hbox1.add(txtmaSach = new JTextField(13));
        hbox1.add(Box.createHorizontalStrut(463));

        
        //Tựa sách va Tác giả
        Box hbox23 = Box.createHorizontalBox();
        hbox23.add(lbtuaSach = new JLabel("Tựa sách:"));
        hbox23.add(Box.createHorizontalStrut(32));
        lbtuaSach.setFont(font2);
        hbox23.add(txttuaSach = new JTextField(30));
        hbox23.add(Box.createHorizontalStrut(40));
        hbox23.add(lbtacGia = new JLabel("Tác giả:"));
        hbox23.add(Box.createHorizontalStrut(40));
        lbtacGia.setFont(font2);
        hbox23.add(txttacGia = new JTextField(30));

        // Năm xuất bản + Nhà xuất bản
        Box hbox4 = Box.createHorizontalBox();
        hbox4.add(lbnamXB = new JLabel("Năm xuất bản:"));
        hbox4.add(Box.createHorizontalStrut(5));
        lbnamXB.setFont(font2);
        hbox4.add(txtnamXB = new JTextField(30));
        hbox4.add(Box.createHorizontalStrut(40));
        hbox4.add(lbnhaXB = new JLabel("Nhà xuất bản:"));
        hbox4.add(Box.createHorizontalStrut(2));
        lbnhaXB.setFont(font2);
        hbox4.add(txtnhaXB = new JTextField(30));

        // Số trang + Đơn giá
        Box hbox5 = Box.createHorizontalBox();
        hbox5.add(lbsoTrang = new JLabel("Số trang:"));
        hbox5.add(Box.createHorizontalStrut(37));
        lbsoTrang.setFont(font2);
        hbox5.add(txtsoTrang = new JTextField(30));
        hbox5.add(Box.createHorizontalStrut(40));
        hbox5.add(lbdonGia = new JLabel("Đơn giá:"));
        hbox5.add(Box.createHorizontalStrut(37));
        lbdonGia.setFont(font2);
        hbox5.add(txtdonGia = new JTextField(30));

        // ISBN
        Box hbox6 = Box.createHorizontalBox();
        hbox6.add(new JLabel("International Standard Book Number (ISBN):"));
        hbox6.add(Box.createHorizontalStrut(2));
        hbox6.add(txtISBN = new JTextField(17));
        hbox6.add(Box.createHorizontalStrut(465));

        // Thêm các Box vào giao diện
        Box vbox = Box.createVerticalBox();
		vbox.add(hbox1);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox23);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox4);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox5);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox6);
		vbox.add(Box.createVerticalStrut(10));
		
		pCenter.add(vbox);
		add(pCenter, BorderLayout.NORTH);
		
		

        
        // ======= 3. Chức năng và bảng =======
		JPanel pSouth = new JPanel();
        pSouth.setBorder(BorderFactory.createTitledBorder("Danh sách các cuốn sách"));
        pSouth.setLayout(new BorderLayout());

      // Nút chức năng
        JPanel pButton = new JPanel();
        btnThem = new JButton("Thêm");
        btnXoaRong = new JButton("Xóa rỗng");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLuu = new JButton("Lưu");
        pButton.add(btnThem);
        pButton.add(btnXoaRong);
        pButton.add(btnSua);
        pButton.add(btnXoa);
        pButton.add(btnLuu);
        //khoang trong
        pButton.add(Box.createHorizontalStrut(40));
        
        
        // ComboBox tìm kiếm
        cboTimMaSach = new JComboBox<>(new String[]{"A001", "J002", "H003"});
        pButton.add(new JLabel("Tìm theo mã sách"));
        pButton.add(cboTimMaSach);
        
        // Bảng danh sách sách
        String[] columnNames = {"Mã sách", "Tựa sách", "Tác giả", "Năm xuất bản", "Nhà xuất bản", "Số trang", "Đơn giá", "ISBN"};
        
        ;
        
        //
        modelSach = new DefaultTableModel(columnNames, 0) {
        	static final long serialVersionUID = 1L;
			 	
        };

        
        table = new JTable(modelSach);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(805, 270));

        dsSach = new Sach_Collection();
        
        DefaultTableCellRenderer centerRecender = new DefaultTableCellRenderer();
		centerRecender.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < modelSach.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRecender);
		}
        
        pSouth.add(scrollPane, BorderLayout.SOUTH);
        add(pButton,BorderLayout.CENTER);
        add(pSouth, BorderLayout.SOUTH);
        
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoaRong.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLuu.addActionListener(this);
        
        setVisible(true);
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Sach_DefaultTableModel();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			String ma = txtmaSach.getText();
			String tua = txttuaSach.getText();
			String tg = txttacGia.getText();
			int nam = Integer.parseInt(txtnamXB.getText());
			String nxb = txtnhaXB.getText();
			int trang = Integer.parseInt(txtsoTrang.getText());
			double dongia = Double.parseDouble(txtdonGia.getText());
			String isbn = txtISBN.getText();
			
			Sach s = new Sach(ma,tua,tg,nam,nxb,trang,dongia,isbn);
			
			if(!dsSach.themSach(s)) {
				JOptionPane.showMessageDialog(this, "Ma sach da ton tai");
				txtmaSach.requestFocus();
			} else {
				modelSach.addRow(new Object[] {
						s.getMaSach(),s.getTuaSach(),s.getTacGia(),s.getNamXuatBan(),
						s.getNhaXuatBan(),s.getSoTrang(),s.getDonGia(),s.getISBN()
				});
			}
			hienThiDsSach();
			xoaDuLieuNhap();
		} else if (o.equals(btnXoa)){
			int selectedRow = table.getSelectedRow();
			if(selectedRow != -1) {
				String maSach = table.getValueAt(selectedRow, 0).toString();
				
				boolean result = dsSach.xoaSach(maSach);
				
				if(result) {
					hienThiDsSach();
					JOptionPane.showMessageDialog(this, "Xoa thanh cong");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lhong chon sach muon xoa");
			}
			
		} else if (o.equals(btnXoaRong)) {
			hienThiDsSach();
			xoaDuLieuNhap();
		} 
	}
	
	public void hienthithongtinSach(Sach s) {
		modelSach.setRowCount(0);
		txtmaSach.setText(s.getMaSach());
		txttuaSach.setText(s.getTuaSach());
		txttacGia.setText(s.getTacGia());
		txtnamXB.setText(String.valueOf(s.getNamXuatBan()));
		txtnhaXB.setText(s.getNhaXuatBan());
		txtsoTrang.setText(String.valueOf(s.getSoTrang()));
		txtdonGia.setText(String.valueOf(s.getDonGia()));
		txtISBN.setText(s.getISBN());
		modelSach.addRow(new Object[] { s.getMaSach(), s.getTuaSach(),s.getTacGia(), s.getNamXuatBan(),
				s.getNhaXuatBan(),s.getSoTrang(),s.getDonGia(), s.getISBN() });
	}
	
	private void xoaDuLieuNhap() {
		txtmaSach.setText("");
		txttuaSach.setText("");
		txttacGia.setText("");
		txtnamXB.setText("");
		txtnhaXB.setText("");
		txtsoTrang.setText("");
		txtdonGia.setText("");
		txtISBN.setText("");
		txtmaSach.requestFocus();
	}

	private void hienThiDsSach() {
		// Sắp xếp lại danh sách trước khi hiển thị
	    Collections.sort(dsSach.getDsSach(), Comparator.comparing(Sach::getMaSach));
		// Xóa toàn bộ dòng trên bảng
		modelSach.setRowCount(0);
		ArrayList<Sach> dsS = dsSach.getDsSach();
		// Thêm từng NV vào bảng
		for (Sach s : dsS) {
			modelSach.addRow(new Object[] { 
					s.getMaSach(),s.getTuaSach(),s.getTacGia(),s.getNamXuatBan(),
					s.getNhaXuatBan(),s.getSoTrang(),s.getDonGia(),s.getISBN()
				});
		}
	}

}
