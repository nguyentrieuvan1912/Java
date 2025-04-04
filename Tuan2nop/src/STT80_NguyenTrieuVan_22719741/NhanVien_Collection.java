package STT80_NguyenTrieuVan_22719741;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;

public class NhanVien_Collection implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<NhanVien> dsNV;
    private transient LuuTru luuTru;
    private final String FILENAME = "SRC/STT80_NguyenTrieuVan_22719741/NhanVien.txt";

    public NhanVien_Collection() {
        dsNV = new ArrayList<>();
        luuTru = new LuuTru(); // 🔹 Đảm bảo luôn khởi tạo LuuTru
        loadFromFile(); // Load dữ liệu từ file khi khởi động
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
        boolean removed = dsNV.removeIf(nv -> nv.getMaNV().equals(maNV));
        return removed;
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

    // Chuyển dữ liệu từ ArrayList<NhanVien> vào DefaultTableModel
    public DefaultTableModel DocDuLieuTuArrayListVaoModel() {
        String[] columnNames = {"Mã Nhân Viên", "Họ", "Tên", "Giới tính", "Tuổi", "Lương"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (NhanVien nv : dsNV) {
            model.addRow(new Object[]{
                nv.getMaNV(),
                nv.getHo(),
                nv.getTen(),
                nv.isPhai() ? "Nam" : "Nữ",
                nv.getTuoi(),
                nv.getLuong()
            });
        }
        return model;
    }


    // 🔹 Đọc danh sách nhân viên từ file khi mở chương trình
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        if (luuTru == null) {
            luuTru = new LuuTru();
        }
        try {
            Object obj = luuTru.DocFile(FILENAME);
            
            // Kiểm tra xem dữ liệu có null không
            if (obj == null) {
                System.out.println("File rỗng hoặc lỗi khi đọc!");
                dsNV = new ArrayList<>();
                return;
            }

            // In ra nội dung để debug
            System.out.println("Dữ liệu đọc từ file: " + obj.getClass().getName());

            // Kiểm tra đúng kiểu dữ liệu không
            if (obj instanceof ArrayList<?>) {
                dsNV = (ArrayList<NhanVien>) obj;
                System.out.println("Đọc danh sách nhân viên thành công!");
            } else {
                System.out.println("Dữ liệu đọc không phải danh sách nhân viên!");
                dsNV = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
    
    

}
