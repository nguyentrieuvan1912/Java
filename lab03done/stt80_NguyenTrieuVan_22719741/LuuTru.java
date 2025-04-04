package stt80_NguyenTrieuVan_22719741;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class LuuTru {

    // Phương thức lưu đối tượng vào file
    public boolean LuuFile(Object obj, String filePath) {
        // Tạo thư mục nếu chưa tồn tại
        try {
            // Kiểm tra và tạo thư mục chứa file nếu cần
            java.io.File folder = new java.io.File(filePath).getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            
            // Ghi đối tượng vào tệp
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(obj);
                oos.flush();
                System.out.println("Dữ liệu đã được lưu thành công!");
                return true;
            } catch (IOException e) {
                System.err.println("Lỗi khi ghi vào tệp: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Lỗi tổng quát: " + e.getMessage());
        }
        return false;
    }

    // Phương thức đọc đối tượng từ file
    public Object DocFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc tệp: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi không tìm thấy lớp đối tượng: " + e.getMessage());
        }
        return null;
    }
}
