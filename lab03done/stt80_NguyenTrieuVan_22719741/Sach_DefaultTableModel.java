package stt80_NguyenTrieuVan_22719741;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import stt80_NguyenTrieuVan_22719741.Sach;
import stt80_NguyenTrieuVan_22719741.Sach_Collection;

public class Sach_DefaultTableModel extends JFrame implements ActionListener,MouseListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sach_Collection dsSach;
	private DefaultTableModel modelSach;
	private LuuTru luuTru;
	private final String FILENAME = "src/stt80_NguyenTrieuVan_22719741/Sach.txt";

	
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
		
		
		// Khởi tạo đối tượng LuuTru
		luuTru = new LuuTru();

		
		Object data = luuTru.DocFile(FILENAME);
		if (data instanceof ArrayList<?>) {
		    dsSach = new Sach_Collection();
		    dsSach.setDsSach((ArrayList<Sach>) data);
		} else if (data instanceof Sach_Collection) {
			dsSach = (Sach_Collection) data;
		} else {
			dsSach = new Sach_Collection();
		}
		
		DocDuLieuTuArrayListVaoModel();
		

        // ======= 2. Khu vực nhập dữ liệu ======= vung center
        JPanel pCenter = new JPanel();
        Font font2 = new Font("Aria", Font.BOLD, 13);
        
        pCenter.setBorder(BorderFactory.createTitledBorder("Records Editor"));

     // Mã sách
        Box hbox1 = Box.createHorizontalBox();
        hbox1.add(lbmaSach = new JLabel("Mã sách:"));
        hbox1.add(Box.createHorizontalStrut(38));
        lbmaSach.setFont(font2);
        hbox1.add(txtmaSach = new JTextField(15));
        hbox1.add(Box.createHorizontalStrut(465));

        
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
        hbox4.add(Box.createHorizontalStrut(1));
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
        hbox5.add(Box.createHorizontalStrut(36));
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
        hbox6.add(txtISBN = new JTextField(15));
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
        
        
       
        
     // Bảng danh sách sách
        String[] columnNames = {"Mã sách", "Tựa sách", "Tác giả", "Năm xuất bản", "Nhà xuất bản", "Số trang", "Đơn giá", "ISBN"};

        // Khởi tạo modelSach
        modelSach = new DefaultTableModel(columnNames, 0) {
            static final long serialVersionUID = 1L;
        };

        // Tạo bảng và thêm JScrollPane
        table = new JTable(modelSach);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(805, 270));

        // Khởi tạo dsSach
        dsSach = new Sach_Collection();

        // Căn chỉnh các ô trong bảng
        DefaultTableCellRenderer centerRecender = new DefaultTableCellRenderer();
        centerRecender.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < modelSach.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRecender);
        }

        // Tải dữ liệu vào bảng (giả sử bạn đã có hàm này để thêm dữ liệu vào bảng)
        DocDuLieuTuArrayListVaoModel();

        // Cập nhật ComboBox tìm kiếm theo mã sách
        cboTimMaSach = new JComboBox<>();
        pButton.add(new JLabel("Tìm theo mã sách"));
        pButton.add(cboTimMaSach);

        // Thêm mã sách vào ComboBox từ dsSach
        for (Sach s : dsSach.getDsSach()) {
            cboTimMaSach.addItem(s.getMaSach());
        }

        // Xử lý sự kiện khi chọn mã sách trong ComboBox
        cboTimMaSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMaSach = (String) cboTimMaSach.getSelectedItem();
                if (selectedMaSach != null) {
                    Sach sach = dsSach.timSachTheoMa(selectedMaSach);
                    if (sach != null) {
                        hienthithongtinSach(sach);  // Hiển thị thông tin sách lên giao diện
                        txtmaSach.setEditable(false);
                    }
                }
            }
        });

        pSouth.add(scrollPane, BorderLayout.SOUTH);
        add(pButton,BorderLayout.CENTER);
        add(pSouth, BorderLayout.SOUTH);
        
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoaRong.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLuu.addActionListener(this);
        
        //kich hoat mouseClicked
        table.addMouseListener(this);

        setVisible(true);
        hienThiDsSach();
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
			// Cập nhật lại ComboBox
		    cboTimMaSach.removeAllItems();  // Xóa hết các mục hiện có trong ComboBox
		    for (Sach sach : dsSach.getDsSach()) {
		        cboTimMaSach.addItem(sach.getMaSach());  // Thêm lại mã sách vào ComboBox
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
			xoaDuLieuNhap();
			hienThiDsSach();
		} else if (o.equals(btnXoaRong)) {
			hienThiDsSach();
			xoaDuLieuNhap();
		} else if(o.equals(btnSua)) {
			int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	            String maSach = txtmaSach.getText();
	            String tua = txttuaSach.getText();
	            String tg = txttacGia.getText();
	            int nam = Integer.parseInt(txtnamXB.getText());
	            String nxb = txtnhaXB.getText();
	            int trang = Integer.parseInt(txtsoTrang.getText());
	            double dongia = Double.parseDouble(txtdonGia.getText());
	            String isbn = txtISBN.getText();

	            // Cập nhật thông tin sách trong dsSach
	            Sach s = new Sach(maSach, tua, tg, nam, nxb, trang, dongia, isbn);
	            boolean result = dsSach.suaSach(maSach, s);

	            if (result) {
	                // Cập nhật lại dòng trong bảng
	                modelSach.setValueAt(tua, selectedRow, 1);
	                modelSach.setValueAt(tg, selectedRow, 2);
	                modelSach.setValueAt(nam, selectedRow, 3);
	                modelSach.setValueAt(nxb, selectedRow, 4);
	                modelSach.setValueAt(trang, selectedRow, 5);
	                modelSach.setValueAt(dongia, selectedRow, 6);
	                modelSach.setValueAt(isbn, selectedRow, 7);
	                JOptionPane.showMessageDialog(this, "Sửa thành công");
	            } else {
	                JOptionPane.showMessageDialog(this, "Sửa thất bại");
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách muốn sửa");
	        }
			
		} else if(o.equals(btnLuu)) {
			try {
		        // Lưu toàn bộ danh sách nhân viên vào file
		        if (luuTru.LuuFile(dsSach.getDsSach(), FILENAME)) {
		            JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu vào file: " + FILENAME);
		        } else {
		            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu!");
		        }
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu: " + ex.getMessage());
		        ex.printStackTrace();
		    }
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
		
		txtmaSach.setEditable(true);
		txtmaSach.requestFocus();
	}

	
	public void DocDuLieuTuArrayListVaoModel() {
	    if (modelSach == null) {
	        System.out.println("Lỗi: modelSach chưa được khởi tạo.");
	        return;
	    }

	    if (dsSach == null || dsSach.getDsSach().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Danh sách nhân viên rỗng.");
	        return;
	    }

	    // ✅ Sắp xếp danh sách nhân viên theo Mã NV (tăng dần)
	    Collections.sort(dsSach.getDsSach(), Comparator.comparing(Sach::getMaSach));

	    // Xóa dữ liệu cũ trong bảng
	    modelSach.setRowCount(0);

	    // ✅ Hiển thị danh sách sau khi đã sắp xếp
	    for (Sach s : dsSach.getDsSach()) {
	    	modelSach.addRow(new Object[]{
	    		s.getMaSach(),
	            s.getTuaSach(),
	            s.getTacGia(),
	            s.getNamXuatBan(),
	            s.getNhaXuatBan(),
	            s.getSoTrang(),
	            s.getDonGia(),
	            s.getISBN(),
	        });
	    }
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
	
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtmaSach.setText(modelSach.getValueAt(row, 0).toString());
		txttuaSach.setText(modelSach.getValueAt(row, 1).toString());
		txttacGia.setText(modelSach.getValueAt(row, 2).toString());
		txtnamXB.setText(modelSach.getValueAt(row, 3).toString());
		txtnhaXB.setText(modelSach.getValueAt(row, 4).toString());
		txtsoTrang.setText(modelSach.getValueAt(row, 5).toString());
		txtdonGia.setText(modelSach.getValueAt(row, 6).toString());
		txtISBN.setText(modelSach.getValueAt(row, 7).toString());

		// Đặt txtmaSach không thể chỉnh sửa
	    txtmaSach.setEditable(false);
	}
	
public class LuuTru {
	    
	    // Đọc đối tượng từ file
	    public Object DocFile(String filePath) {
	        File file = new File(filePath);
	        if (!file.exists()) {
	            System.out.println("File không tồn tại: " + filePath);
	            return null;
	        }

	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
	            return ois.readObject();  // Đọc đối tượng từ file
	        } catch (IOException | ClassNotFoundException e) {
	            System.err.println("Lỗi khi đọc file: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return null;
	    }

	    // Lưu đối tượng vào file
	    public boolean LuuFile(Object obj, String filePath) {
	        // Kiểm tra xem thư mục "data" có tồn tại không, nếu không thì tạo
	        File folder = new File("data");
	        if (!folder.exists()) {
	            folder.mkdirs();  // Tạo thư mục nếu chưa tồn tại
	        }

	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
	            oos.writeObject(obj);  // Ghi đối tượng vào file
	            oos.flush();
	            return true;
	        } catch (IOException e) {
	            System.err.println("Lỗi khi lưu file: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return false;
	    }
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
