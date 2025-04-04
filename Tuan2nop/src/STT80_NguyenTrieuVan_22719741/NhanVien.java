package STT80_NguyenTrieuVan_22719741;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable {
    private static final long serialVersionUID = 1L;

    private String maNV;
    private String ho;
    private String ten;
    private boolean phai;
    private int tuoi;
    private double luong;

    // Thêm constructor mặc định để hỗ trợ đọc file
    public NhanVien() {}

    public NhanVien(String maNV, String ho, String ten, boolean phai, int tuoi, double luong) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
        this.tuoi = tuoi;
        this.luong = luong;
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isPhai() {
        return phai;
    }

    public void setPhai(boolean phai) {
        this.phai = phai;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNV);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NhanVien other = (NhanVien) obj;
        return Objects.equals(maNV, other.maNV);
    }

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", phai=" + phai + ", tuoi=" + tuoi
				+ ", luong=" + luong + "]";
	}

    // Thêm toString() để dễ kiểm tra dữ liệu
    
    
    
}
