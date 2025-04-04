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
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import STT80_NguyenTrieuVan_22719741.NhanVien;
import STT80_NguyenTrieuVan_22719741.NhanVien_Collection;

public class NhanVien_App_DefaultTableModel extends JFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NhanVien_Collection listNV;
	private DefaultTableModel modelNhanVien;
	private LuuTru luuTru;
	private final String FILENAME = "data/NhanVien.txt";

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
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Khởi tạo đối tượng LuuTru
		luuTru = new LuuTru();

		Object data = luuTru.DocFile(FILENAME);
	    if (data != null) {
	        listNV = (NhanVien_Collection) data;
	    } else {
	        listNV = new NhanVien_Collection();
	    }
	    
	 // Đọc dữ liệu từ danh sách và hiển thị lên bảng JTable
	    DocDuLieuTuArrayListVaoModel();
		
		// Vung North
		JPanel pNorth = new JPanel();
		pNorth.add(title = new JLabel("THÔNG TIN NHÂN VIÊN"));
		Font newFont = new Font("Arial", Font.BOLD, 20);
		add(pNorth, BorderLayout.NORTH);
		title.setFont(newFont);
		title.setForeground(Color.BLUE);

		// Vung Center
		JPanel pCenter = new JPanel();

		Font font2 = new Font("Tahoma", Font.PLAIN, 13);
		// ma nv

		Box hbox = Box.createHorizontalBox();
		hbox.add(lbMaNV = new JLabel("Mã nhân viên:"));
		lbMaNV.setFont(font2);
		hbox.add(Box.createHorizontalStrut(5));
		hbox.add(textMaNV = new JTextField(70));

		// Ho ten
		Box hbox1 = Box.createHorizontalBox();
		hbox1.add(lbHo = new JLabel("Họ:"));
		lbHo.setFont(font2);
		hbox1.add(Box.createHorizontalStrut(64));
		hbox1.add(textHo = new JTextField(31));
		hbox1.add(Box.createHorizontalStrut(5));
		hbox1.add(lbTen = new JLabel("Tên nhân viên:"));
		lbTen.setFont(font2);
		hbox1.add(Box.createHorizontalStrut(5));
		hbox1.add(textTen = new JTextField(29));
		// tuoi va phai
		Box hbox2 = Box.createHorizontalBox();
		hbox2.add(lbTuoi = new JLabel("Tuổi:"));
		lbTuoi.setFont(font2);
		hbox2.add(Box.createHorizontalStrut(56));
		hbox2.add(textTuoi = new JTextField(48));
		hbox2.add(Box.createHorizontalStrut(5));
		hbox2.add(lbPhai = new JLabel("Phái:"));
		lbPhai.setFont(font2);
		hbox2.add(Box.createHorizontalStrut(10));
		hbox2.add(rbtNam = new JRadioButton("Nam"));
		rbtNam.setFont(font2);
		hbox2.add(Box.createHorizontalStrut(10));
		hbox2.add(rbtNu = new JRadioButton("Nữ"));
		rbtNu.setFont(font2);
		group = new ButtonGroup();
		rbtNam.setSelected(true);
		group.add(rbtNam);
		group.add(rbtNu);

		// tien luong

		Box hbox3 = Box.createHorizontalBox();
		hbox3.add(lbLuong = new JLabel("Tiền lương:"));
		lbLuong.setFont(font2);
		hbox3.add(Box.createHorizontalStrut(20));
		hbox3.add(textLuong = new JTextField(70));

		// JTable
		String[] columnName = { "Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền Lương" };
		modelNhanVien = new DefaultTableModel(columnName, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// Chỉ làm cho các cột ID không phải của nhân viên có thể chỉnh sửa được
				return column != 0;
			}
		};
		table = new JTable(modelNhanVien);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(805, 280));

		listNV = new NhanVien_Collection();

		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));

		DefaultTableCellRenderer centerRecender = new DefaultTableCellRenderer();
		centerRecender.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < modelNhanVien.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRecender);
		}
		// Tạo trình kết xuất tùy chỉnh cho cột giới tính
		genderComboBox = new JComboBox<>(gender);
		DefaultCellEditor genderEditor = new DefaultCellEditor(genderComboBox);
		table.getColumnModel().getColumn(3).setCellEditor(genderEditor);
		// Định dạng tiền lương $
		DecimalFormat format = new DecimalFormat("#,##0 $");
		CurrencyRenderer currencyRenderer = new CurrencyRenderer(format);
		table.getColumnModel().getColumn(5).setCellRenderer(currencyRenderer);

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
		lbTimKiem.setFont(font2);
		box_timKiem.add(Box.createHorizontalStrut(10));
		box_timKiem.add(textNhapMa = new JTextField(15));
		box_timKiem.add(Box.createHorizontalStrut(15));
		box_timKiem.add(btTim = new JButton("Tìm"));
		btTim.setFont(font2);
		jPanel_timKiem.add(box_timKiem);

		JPanel jPanel_tacVu = new JPanel();
		jPanel_tacVu.setBorder(border);
		Box box_tacVu = new Box(BoxLayout.X_AXIS);
		jPanel_tacVu.add(Box.createHorizontalStrut(40));
		box_tacVu.add(btThem = new JButton("Thêm"));
		btThem.setFont(font2);
		box_tacVu.add(Box.createHorizontalStrut(10));
		box_tacVu.add(btXoaTrang = new JButton("Xóa trắng"));
		btXoaTrang.setFont(font2);
		box_tacVu.add(Box.createHorizontalStrut(10));
		box_tacVu.add(btXoa = new JButton("Xóa"));
		btXoa.setFont(font2);
		box_tacVu.add(Box.createHorizontalStrut(10));
		box_tacVu.add(btLuu = new JButton("Lưu"));
		btLuu.setFont(font2);
		box_tacVu.add(Box.createHorizontalStrut(35));
		jPanel_tacVu.add(box_tacVu);
		jPanel_tacVu.add(Box.createHorizontalStrut(30));

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

		textMaNV.requestFocus();
		setVisible(true);
	}

	public static void main(String[] args) {
		new NhanVien_App_DefaultTableModel();
	}

	public void DocDuLieuTuArrayListVaoModel() {
		if (listNV.getSize() == 0) {
			JOptionPane.showMessageDialog(this, "Danh sách nhân viên rỗng.");
			return;
		}
		for (int i = 0; i < listNV.getSize(); i++) {
			NhanVien nv = listNV.getElement(i);
			modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(), nv.isPhai() ? "Nam" : "Nữ",
					nv.getTuoi(), nv.getLuong() });
		}
	}

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

			if (!listNV.themNV(nv)) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn lại! Vui lòng nhập mã khác.");
				textMaNV.requestFocus();
			} else {
				modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(),
						nv.isPhai() == true ? "Nam" : "Nữ", nv.getTuoi(), nv.getLuong() });
				xoaDuLieuNhap();
			}
		} else if (o.equals(btXoa)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				// Lấy mã nhân viên từ dòng được chọn
				String maNV = table.getValueAt(selectedRow, 0).toString();

				// Xóa nhân viên từ danh sách
				boolean result = listNV.xoaNV(maNV);

				if (result) {
					hienThiDanhSachNV();
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
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã số cần tìm kiếm!");
			} else {
				NhanVien nv = listNV.timKiem(maNVTimKiem);
				if (nv != null) {
					hienThiThongTinNV(nv);
					int rowIndex = timViTriDong(maNVTimKiem);
					if (rowIndex != -1) {
						table.setRowSelectionInterval(rowIndex, rowIndex);
//						table.scrollRectToVisible(table.getCellRect(rowIndex, 0, true));
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã số " + maNVTimKiem);
					xoaDuLieuNhap();
				}
			}
		}else if (o.equals(btLuu)) {
			try {
		        // Lưu toàn bộ danh sách nhân viên vào file
		        if (luuTru.LuuFile(listNV, FILENAME)) {
		            JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!");
		        } else {
		            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu!");
		        }
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu: " + ex.getMessage());
		        ex.printStackTrace();
		    }
		}
		
		}

	public void hienThiThongTinNV(NhanVien nv) {
		modelNhanVien.setRowCount(0);
		textMaNV.setText(nv.getMaNV());
		textHo.setText(nv.getHo());
		textTen.setText(nv.getTen());
		textTuoi.setText(String.valueOf(nv.getTuoi()));
		if (nv.isPhai()) {
			rbtNam.setSelected(true);
		} else {
			rbtNu.setSelected(true);
		}
		textLuong.setText(String.valueOf(nv.getLuong()));
		modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(), nv.isPhai() == true ? "Nam" : "Nữ",
				nv.getTuoi(), nv.getLuong() });
	}

	private void xoaDuLieuNhap() {
		textMaNV.setText("");
		textHo.setText("");
		textTen.setText("");
		textTuoi.setText("");
		group.clearSelection();
		textLuong.setText("");
		textMaNV.requestFocus();
		textNhapMa.setText("");
		rbtNam.setSelected(true);
	}

	private void hienThiDanhSachNV() {
		// Xóa toàn bộ dòng trên bảng
		modelNhanVien.setRowCount(0);
		ArrayList<NhanVien> dsNV = listNV.getDsNV();
		// Thêm từng NV vào bảng
		for (NhanVien nv : dsNV) {
			modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(),
					nv.isPhai() == true ? "Nam" : "Nữ", nv.getTuoi(), nv.getLuong() });
		}
	}

	private int timViTriDong(String maNV) {
		for (int i = 0; i < modelNhanVien.getRowCount(); i++) {
			String maNVTable = modelNhanVien.getValueAt(i, 0).toString();
			if (maNV.equals(maNVTable)) {
				return i;
			}
		}
		return -1;
	}

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

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		textMaNV.setText(modelNhanVien.getValueAt(row, 0).toString());
		textHo.setText(modelNhanVien.getValueAt(row, 1).toString());
		textTen.setText(modelNhanVien.getValueAt(row, 2).toString());
		textTuoi.setText(modelNhanVien.getValueAt(row, 3).toString());
		rbtNam.setSelected(modelNhanVien.getValueAt(row, 4).toString() == "Nam" ? true : false);
		textLuong.setText(modelNhanVien.getValueAt(row, 5).toString());

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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btTim.doClick();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
