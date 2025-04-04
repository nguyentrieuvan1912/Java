package stt80_NguyenTrieuVan_22719741;

import java.io.Serializable;
import java.util.Objects;

public class Sach implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	
	private String maSach;
    private String tuaSach;
    private String tacGia;
    private int namXuatBan;
    private String nhaXuatBan;
    private int soTrang;
    private double donGia;
    private String ISBN;
    
   
    
	public Sach() {}



	public Sach(String maSach, String tuaSach, String tacGia, int namXuatBan, String nhaXuatBan, int soTrang,
			double donGia, String iSBN) {
		super();
		this.maSach = maSach;
		this.tuaSach = tuaSach;
		this.tacGia = tacGia;
		this.namXuatBan = namXuatBan;
		this.nhaXuatBan = nhaXuatBan;
		this.soTrang = soTrang;
		this.donGia = donGia;
		ISBN = iSBN;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(ISBN, donGia, maSach, namXuatBan, nhaXuatBan, soTrang, tacGia, tuaSach);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		return Objects.equals(ISBN, other.ISBN)
				&& Double.doubleToLongBits(donGia) == Double.doubleToLongBits(other.donGia)
				&& Objects.equals(maSach, other.maSach) && namXuatBan == other.namXuatBan
				&& Objects.equals(nhaXuatBan, other.nhaXuatBan) && soTrang == other.soTrang
				&& Objects.equals(tacGia, other.tacGia) && Objects.equals(tuaSach, other.tuaSach);
	}
	
	
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTuaSach() {
		return tuaSach;
	}
	public void setTuaSach(String tuaSach) {
		this.tuaSach = tuaSach;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public int getNamXuatBan() {
		return namXuatBan;
	}
	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}
	public String getNhaXuatBan() {
		return nhaXuatBan;
	}
	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}
	public int getSoTrang() {
		return soTrang;
	}
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}



	@Override
	public String toString() {
		return "Sach {maSach=" + maSach + ", tuaSach=" + tuaSach + ", tacGia=" + tacGia + ", namXuatBan=" + namXuatBan
				+ ", nhaXuatBan=" + nhaXuatBan + ", soTrang=" + soTrang + ", donGia=" + donGia + ", ISBN=" + ISBN + "}";
	}
	
	
	
	
}
