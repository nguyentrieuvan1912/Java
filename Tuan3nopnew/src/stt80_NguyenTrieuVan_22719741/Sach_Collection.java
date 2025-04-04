package stt80_NguyenTrieuVan_22719741;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Sach_Collection implements Serializable  {

	private static final long serialVersionUID = 1L;
	private ArrayList<Sach> dsSach;
	
	
	

	public Sach_Collection() {
		dsSach = new ArrayList<Sach>();
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
