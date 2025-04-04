package STT80_NguyenTrieuVan_22719741;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import STT80_NguyenTrieuVan_22719741.NhanVien;
import STT80_NguyenTrieuVan_22719741.NhanVien_Collection;

public class NhanVien_App_DefaultTableModel extends JFrame implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NhanVien_Collection listNV;
	private DefaultTableModel modelNhanVien;
	private LuuTru luuTru;
	private final String FILENAME = "SRC/STT80_NguyenTrieuVan_22719741/NhanVien.txt";

	JTextField textMaNV, textHo, textTen, textTuoi, textLuong, textNhapMa;
	JLabel title, lbMaNV, lbHo, lbTen, lbTuoi, lbPhai, lbLuong, lbTimKiem;
	JButton btTim, btThem, btXoaTrang, btXoa, btLuu;
	JRadioButton rbtNam, rbtNu;
	JTable table;
	ButtonGroup group;
	String[] gender = { "Nam", "Nữ" };
	JComboBox<String> genderComboBox;

	public NhanVien_App_DefaultTableModel() {
		
		super("^-^");
		setSize(900, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Khởi tạo đối tượng LuuTru
		luuTru = new LuuTru();

		
		Object data = luuTru.DocFile(FILENAME);
		
		
		// LUOT 1
		/*
		if (data instanceof ArrayList<?>) {
		    listNV = new NhanVien_Collection();
		    listNV.setDsNV((ArrayList<NhanVien>) data);
		} else if (data instanceof NhanVien_Collection) {
		    listNV = (NhanVien_Collection) data;
		} else {
		    listNV = new NhanVien_Collection();
		}*/

		
		
		// Vung North
		JPanel pNorth = new JPanel();
		pNorth.add(title = new JLabel("THÔNG TIN NHÂN VIÊN"));
		Font newFont = new Font("Arial", Font.BOLD, 20);
		add(pNorth, BorderLayout.NORTH);
		title.setFont(newFont);
		title.setForeground(Color.BLUE);

		// Vung Center
		JPanel pCenter = new JPanel();

		// ma nv

		Box hbox = Box.createHorizontalBox();
		hbox.add(lbMaNV = new JLabel("Mã nhân viên:"));
		hbox.add(Box.createHorizontalStrut(5));
		hbox.add(textMaNV = new JTextField(70));

		// Ho ten
		Box hbox1 = Box.createHorizontalBox();
		hbox1.add(lbHo = new JLabel("Họ:"));
		hbox1.add(Box.createHorizontalStrut(64));
		hbox1.add(textHo = new JTextField(31));
		
		hbox1.add(Box.createHorizontalStrut(5));
		hbox1.add(lbTen = new JLabel("Tên nhân viên:"));
		hbox1.add(Box.createHorizontalStrut(5));
		hbox1.add(textTen = new JTextField(29));
		
		// tuoi va phai
		Box hbox2 = Box.createHorizontalBox();
		hbox2.add(lbTuoi = new JLabel("Tuổi:"));
		hbox2.add(Box.createHorizontalStrut(56));
		hbox2.add(textTuoi = new JTextField(48));
		
		hbox2.add(Box.createHorizontalStrut(5));
		hbox2.add(lbPhai = new JLabel("Phái:"));
		hbox2.add(Box.createHorizontalStrut(10));
		hbox2.add(rbtNam = new JRadioButton("Nam"));
		hbox2.add(Box.createHorizontalStrut(10));
		hbox2.add(rbtNu = new JRadioButton("Nữ"));
		group = new ButtonGroup();
		rbtNam.setSelected(true);
		group.add(rbtNam);
		group.add(rbtNu);

		// tien luong
		Box hbox3 = Box.createHorizontalBox();
		hbox3.add(lbLuong = new JLabel("Tiền lương:"));
		hbox3.add(Box.createHorizontalStrut(20));
		hbox3.add(textLuong = new JTextField(70));

		/*
		Font font2 = new Font("Tahoma", Font.PLAIN, 13);
		lbMaNV.setFont(font2);
		lbHo.setFont(font2);
		lbTen.setFont(font2);
		lbTuoi.setFont(font2);
		lbPhai.setFont(font2);
		rbtNam.setFont(font2);
		rbtNu.setFont(font2);
		lbLuong.setFont(font2);
	*/
		
		
		// JTable
		String[] columnName = { "Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền Lương" };
		modelNhanVien = new DefaultTableModel(columnName, 0) {
			private static final long serialVersionUID = 1L;
			
			//LUOC 2
			/*
			@Override
			public boolean isCellEditable(int row, int column) {
				// Chỉ làm cho các cột ID không phải của nhân viên có thể chỉnh sửa được
				return column != 0;
			}
			*/
			
		};
		
		table = new JTable(modelNhanVien);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(805, 280));

		listNV = new NhanVien_Collection();

		//LUOC 3
		/*
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		DefaultTableCellRenderer centerRecender = new DefaultTableCellRenderer();
		centerRecender.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < modelNhanVien.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRecender);
		}
		*/
		
		//LUOC 4
		/*
		// Tạo trình kết xuất tùy chỉnh cho cột giới tính
		genderComboBox = new JComboBox<>(gender);
		DefaultCellEditor genderEditor = new DefaultCellEditor(genderComboBox);
		table.getColumnModel().getColumn(3).setCellEditor(genderEditor);
		
		*/
		
		//LUOC 10
		/*
		// Định dạng tiền lương $
		DecimalFormat format = new DecimalFormat("#,##0 $");
		CurrencyRenderer currencyRenderer = new CurrencyRenderer(format);
		table.getColumnModel().getColumn(5).setCellRenderer(currencyRenderer);
		*/

		Box vbox = Box.createVerticalBox();
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox1);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox2);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox3);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(pane);

		pCenter.add(vbox);
		add(pCenter, BorderLayout.CENTER);
		// Vung South
		Border border = BorderFactory.createEtchedBorder();

		JPanel pSouth = new JPanel();
		JPanel jPanel_timKiem = new JPanel();
		jPanel_timKiem.setBorder(border);
		Box box_timKiem = Box.createHorizontalBox();
		
		box_timKiem.add(lbTimKiem = new JLabel("Nhập mã số cần tìm:"));
		box_timKiem.add(Box.createHorizontalStrut(10));
		box_timKiem.add(textNhapMa = new JTextField(15));
		box_timKiem.add(Box.createHorizontalStrut(15));
		box_timKiem.add(btTim = new JButton("Tìm"));
		jPanel_timKiem.add(box_timKiem);

		JPanel jPanel_tacVu = new JPanel();
		jPanel_tacVu.setBorder(border);
		Box box_tacVu = new Box(BoxLayout.X_AXIS);
		jPanel_tacVu.add(Box.createHorizontalStrut(40));
		box_tacVu.add(btThem = new JButton("Thêm"));
		box_tacVu.add(Box.createHorizontalStrut(10));
		box_tacVu.add(btXoaTrang = new JButton("Xóa trắng"));
		box_tacVu.add(Box.createHorizontalStrut(10));
		box_tacVu.add(btXoa = new JButton("Xóa"));
		box_tacVu.add(Box.createHorizontalStrut(10));
		box_tacVu.add(btLuu = new JButton("Lưu"));
		box_tacVu.add(Box.createHorizontalStrut(35));
		jPanel_tacVu.add(box_tacVu);
		jPanel_tacVu.add(Box.createHorizontalStrut(30));

		/*
		lbTimKiem.setFont(font2);
		btTim.setFont(font2);
		btThem.setFont(font2);
		btXoaTrang.setFont(font2);
		btXoa.setFont(font2);
		btLuu.setFont(font2);
		 */
		
		
		// JSplitPane
		JSplitPane jSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jSplit.setDividerLocation(400); // Đặt vị trí dải phân cách
		jSplit.setLeftComponent(jPanel_timKiem);
		jSplit.setRightComponent(jPanel_tacVu);

		pSouth.add(jSplit);
		add(pSouth, BorderLayout.SOUTH);

		btTim.addActionListener(this);
		btThem.addActionListener(this);
		btXoa.addActionListener(this);
		btXoaTrang.addActionListener(this);
		btLuu.addActionListener(this);

		table.addMouseListener(this);

		// ✅ Đảm bảo sắp xếp trước khi hiển thị
		//DocDuLieuTuArrayListVaoModel();
		
		textMaNV.requestFocus();
		setVisible(true);
		hienThiDanhSachNV();
	}

	public static void main(String[] args) {
		new NhanVien_App_DefaultTableModel();
		
	}
	
	/*public void DocDuLieuTuArrayListVaoModel() {
		
		
		//LUOC 5
		/*
	    if (modelNhanVien == null) {
	        System.out.println("Lỗi: modelNhanVien chưa được khởi tạo.");
	        return;
	    }

	    if (listNV == null || listNV.getDsNV().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Danh sách nhân viên rỗng.");
	        return;
	    }
		 */
		
		
	    // ✅ Sắp xếp danh sách nhân viên theo Mã NV (tăng dần)
	    //Collections.sort(listNV.getDsNV(), Comparator.comparing(NhanVien::getMaNV));

	    // Xóa dữ liệu cũ trong bảng
	    //modelNhanVien.setRowCount(0);

	    // ✅ Hiển thị danh sách sau khi đã sắp xếp
	    /*for (NhanVien nv : listNV.getDsNV()) {
	        modelNhanVien.addRow(new Object[]{
	            nv.getMaNV(),
	            nv.getHo(),
	            nv.getTen(),
	            nv.getTuoi(),
	            nv.isPhai() ? "Nam" : "Nữ",
	            nv.getLuong() + " $"
	        });
	    }
	}*/



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btThem)) {
			
			String ma = textMaNV.getText();
			String ho = textHo.getText();
			String ten = textTen.getText();
			int tuoi = Integer.parseInt(textTuoi.getText());
			Boolean phai = rbtNam.isSelected();
			double luong = Double.parseDouble(textLuong.getText());

			NhanVien nv = new NhanVien(ma, ho, ten, phai, tuoi, luong);
			listNV.themNV(nv);
			
			/*
			if (!listNV.themNV(nv)) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn lại! Vui lòng nhập mã khác.");
				textMaNV.requestFocus();
			} else {
				//modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(),
					//	nv.isPhai() == true ? "Nam" : "Nữ", nv.getTuoi(), nv.getLuong() });
				xoaDuLieuNhap();
			}*/
			
			xoaDuLieuNhap();

			hienThiDanhSachNV();
		} else if (o.equals(btXoa)) {
			
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				// Lấy mã nhân viên từ dòng được chọn
				String maNV = table.getValueAt(selectedRow, 0).toString();

				// Xóa nhân viên từ danh sách
				boolean result = listNV.xoaNV(maNV);

				if (result) {
					hienThiDanhSachNV();
					xoaDuLieuNhap();
					JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!");
			}
		} else if (o.equals(btXoaTrang)) {
				xoaDuLieuNhap();
				hienThiDanhSachNV();
		} else if (o.equals(btTim)) {
			String maNVTimKiem = textNhapMa.getText();
			if (maNVTimKiem.trim().isEmpty()) {
				hienThiDanhSachNV();
				xoaDuLieuNhap();
			} else {
				NhanVien nv = listNV.timKiem(maNVTimKiem);
				if (nv != null) {
					hienThiThongTinNV(nv);
					
					focusbang(nv);
					//LUOC 7
					/*
					int rowIndex = timViTriDong(maNVTimKiem);
					if (rowIndex != -1) {
						table.setRowSelectionInterval(rowIndex, rowIndex);
						textNhapMa.setText("");
//						table.scrollRectToVisible(table.getCellRect(rowIndex, 0, true));
					}*/
					
					
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã số " + maNVTimKiem);
					xoaDuLieuNhap();
				}
			}
		}else if (o.equals(btLuu)) {
			try {
		        // Lưu toàn bộ danh sách nhân viên vào file
		        if (luuTru.LuuFile(listNV.getDsNV(), FILENAME)) {
		            JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu vào file: " + FILENAME);
		        }
		    } catch (Exception ex) {

		    }
		}
		
	}

	public void hienThiThongTinNV(NhanVien nv) {
		//modelNhanVien.setRowCount(0);
		textMaNV.setText(nv.getMaNV());
		textHo.setText(nv.getHo());
		textTen.setText(nv.getTen());
		textTuoi.setText(String.valueOf(nv.getTuoi()));
		if (nv.isPhai())
			rbtNam.setSelected(true);
		else 
			rbtNu.setSelected(true);
		textLuong.setText(String.valueOf(nv.getLuong()));
		
		//modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(), nv.isPhai() == true ? "Nam" : "Nữ",
		//		nv.getTuoi(), nv.getLuong() });
	}

	private void xoaDuLieuNhap() {
		textMaNV.setText("");
		textHo.setText("");
		textTen.setText("");
		textTuoi.setText("");
		textLuong.setText("");
		textMaNV.requestFocus();
		textNhapMa.setText("");
		rbtNam.setSelected(true);
	}

	private void hienThiDanhSachNV() {
		// Sắp xếp lại danh sách trước khi hiển thị
	    Collections.sort(listNV.getDsNV(), Comparator.comparing(NhanVien::getMaNV));
	    
	    
	 // Gọi trực tiếp phương thức tạo model từ NhanVien_Collection
	    modelNhanVien = listNV.DocDuLieuTuArrayListVaoModel();
	    
	    // Cập nhật model cho bảng (JTable)
	    table.setModel(modelNhanVien);
	    
	    /*
		modelNhanVien.setRowCount(0);
		ArrayList<NhanVien> dsNV = listNV.getDsNV();
		// Thêm từng NV vào bảng
		for (NhanVien nv : dsNV) {
			modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(),
					nv.isPhai() == true ? "Nam" : "Nữ", nv.getTuoi(), nv.getLuong() });
		}
		
		*/
	}

	//LUOC 9
	/*
	private int timViTriDong(String maNV) {
		for (int i = 0; i < modelNhanVien.getRowCount(); i++) {
			String maNVTable = modelNhanVien.getValueAt(i, 0).toString();
			if (maNV.equals(maNVTable)) {
				return i;
			}
		}
		return -1;
	}
	*/
	
	
	//LUOC 10
	/*
	private class CurrencyRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		private final DecimalFormat format;

		public CurrencyRenderer(DecimalFormat format) {
			this.format = format;
			setHorizontalAlignment(SwingConstants.CENTER); // Hiển thị giá trị ở vị trí trái
		}

		@Override
		protected void setValue(Object value) {
			// Kiểm tra xem giá trị có phải là số hay không trước khi định dạng
			if (value != null && value instanceof Number) {
				value = format.format(value);
			}
			super.setValue(value);
		}
	}
	*/

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
	    String maNV = modelNhanVien.getValueAt(row, 0).toString();
	    NhanVien nv = listNV.timKiem(maNV);
	    hienThiThongTinNV(nv);

	}

	

	public class LuuTru {

	    public Object DocFile(String filePath) {
	        File file = new File(filePath);
	        if (!file.exists()) {
	            //System.out.println("File không tồn tại: " + filePath);
	            return null;
	        }

	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
	            return ois.readObject();
	        } catch (IOException | ClassNotFoundException e) {
	            //System.err.println("Lỗi khi đọc file: " + e.getMessage());
	        }
	        return null;
	    }

	    public boolean LuuFile(Object obj, String filePath) {
	        File folder = new File("data");
	        if (!folder.exists()) 
	        	folder.mkdirs();

	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
	            oos.writeObject(obj);
	            return true;
	        } catch (IOException e) {
	            //System.err.println("Lỗi khi lưu file: " + e.getMessage());
	        }
	        return false;
	    }
	}
	
	private void focusbang( NhanVien nv) {
		
		modelNhanVien.setRowCount(0);
		modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(), nv.isPhai() == true ? "Nam" : "Nữ",
		nv.getTuoi(), nv.getLuong() });
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
