package stt80_NguyenTrieuVan_22719741;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class Sach_Collection implements Serializable  {
	private static final long serialVersionUID = 1L;
	private ArrayList<Sach> dsSach;
	private transient LuuTru luuTru;
    private final String FILENAME = "src/stt80_NguyenTrieuVan_22719741/Sach.txt";


	public Sach_Collection() {
		dsSach = new ArrayList<>();
		luuTru = new LuuTru(); // 🔹 Đảm bảo luôn khởi tạo LuuTru
        loadFromFile();
	}
	
	private void loadFromFile() {
		// TODO Auto-generated method stub
		if (luuTru == null) {
            luuTru = new LuuTru();
        }
        try {
            Object obj = luuTru.DocFile(FILENAME);
            
            // Kiểm tra xem dữ liệu có null không
            if (obj == null) {
                System.out.println("File rỗng hoặc lỗi khi đọc!");
                dsSach = new ArrayList<>();
                return;
            }

            // In ra nội dung để debug
            System.out.println("Dữ liệu đọc từ file: " + obj.getClass().getName());

            // Kiểm tra đúng kiểu dữ liệu không
            if (obj instanceof ArrayList<?>) {
                dsSach = (ArrayList<Sach>) obj;
                System.out.println("Đọc danh sách nhân viên thành công!");
            } else {
                System.out.println("Dữ liệu đọc không phải danh sách nhân viên!");
                dsSach = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
	}

	public boolean themSach(Sach s) {
		if(dsSach.contains(s)) {
			return false;
		}
		dsSach.add(s);
		return true;
	}

	public boolean xoaSach(String maSach) {
		boolean removed = dsSach.removeIf(s -> s.getMaSach().equals(maSach));
		return removed;
	}
	
	public ArrayList<Sach> getDsSach() {
		return dsSach;
	}
	public void setDsSach(ArrayList<Sach> dsSach) {
		this.dsSach = dsSach;
	}
	
	public Sach getElement(int index) {
		if(index < 0 || index >= dsSach.size()) {
			return null;
		}
		return dsSach.get(index);
	}
	public int getSize() {
        return dsSach.size();
    }
	
	public boolean suaSach(String maSach, Sach sachMoi) {
	    for (int i = 0; i < dsSach.size(); i++) {
	        Sach s = dsSach.get(i);
	        if (s.getMaSach().equals(maSach)) {
	            dsSach.set(i, sachMoi); // Cập nhật sách
	            return true;
	        }
	    }
	    return false; // Không tìm thấy sách với mã này
	}
	
	public Sach timSachTheoMa(String maSach) {
        for (Sach s : dsSach) {
            if (s.getMaSach().equals(maSach)) {
                return s;
            }
        }
        return null; // Nếu không tìm thấy sách
    }

	
	public DefaultTableModel DocDuLieuTuArrayListVaoModel() {
        String[] columnNames = {"Mã Sách", "Tựa Sách", "Tác giả", "Năm xuất bản", "Nhà Xuất Bản", "Số trang", "Đơn giá", "ISBN"};

        DefaultTableModel model = new DefaultTableModel();
        
        for (Sach s : dsSach) {
        	model.addRow(new Object[] {
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
        return model;
	}
}
