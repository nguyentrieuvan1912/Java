package STT80_NguyenTrieuVan_22719741;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;

public class NhanVien_Collection implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<NhanVien> dsNV;

    public NhanVien_Collection() {
        dsNV = new ArrayList<>();
    }

    // Thêm nhân viên vào danh sách
    public boolean themNV(NhanVien nv) {
        if (dsNV.contains(nv)) {
            return false; // Nếu danh sách đã có nhân viên với mã giống, không thêm
        }
        dsNV.add(nv);
        return true;
    }

    // Xóa nhân viên khi biết mã
    public boolean xoaNV(String maNV) {
        return dsNV.removeIf(nv -> nv.getMaNV().equals(maNV));
    }

    // Tìm kiếm nhân viên khi biết mã
    public NhanVien timKiem(String maNV) {
        for (NhanVien nv : dsNV) {
            if (nv.getMaNV().equals(maNV)) {
                return nv;
            }
        }
        return null; // Không tìm thấy nhân viên
    }

    // Lấy danh sách nhân viên
    public ArrayList<NhanVien> getDsNV() {
        return dsNV;
    }

    // Đặt lại danh sách nhân viên
    public void setDsNV(ArrayList<NhanVien> dsNV) {
        this.dsNV = dsNV;
    }

    // Lấy phần tử nhân viên tại vị trí index
    public NhanVien getElement(int index) {
        if (index < 0 || index >= dsNV.size()) {
            return null;
        }
        return dsNV.get(index);
    }

    // Lấy kích thước của danh sách
    public int getSize() {
        return dsNV.size();
    }

    // Cập nhật phương thức để chuyển dữ liệu từ ArrayList<NhanVien> vào DefaultTableModel
    public DefaultTableModel DocDuLieuTuArrayListVaoModel() {
        // Tạo tiêu đề cột cho bảng
        String[] columnNames = {"Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh", "Chức Vụ", "Lương"};

        // Tạo mảng 2 chiều để chứa dữ liệu
        Object[][] data = new Object[dsNV.size()][];

        // Duyệt qua danh sách nhân viên và lấy dữ liệu
        for (int i = 0; i < dsNV.size(); i++) {
            NhanVien nv = dsNV.get(i);
            data[i] = new Object[] {
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getNgaySinh(),
                nv.getChucVu(),
                nv.getLuong()
            };
        }

        // Tạo và trả về DefaultTableModel với dữ liệu và tên các cột
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        return model;
    }
}
