Lựa chọn 1: "Mini-RocksDB" — LSM-Tree Storage Engine + Bloom Filter (B01 × B12 × CS 61B)

Nếu bạn thích cảm giác tự tay viết một cơ sở dữ liệu thực thụ lưu dữ liệu xuống ổ cứng, tắt chương trình bật lại vẫn còn nguyên dữ liệu và chịu được hàng trăm nghìn thao tác ghi/xóa mỗi giây

Kiến trúc DSA cốt lõi (Code 100% from scratch)
Write-Ahead Log (WAL): Ghi nối tiếp (append-only) mọi thao tác PUT(k, v) và DELETE(k) ra file log trước khi đưa vào RAM. Khi giả lập sập nguồn (crash), engine tự đọc lại WAL để khôi phục Memtable.
Memtable trên RAM (So sánh SkipList vs. Left-Leaning Red-Black Tree): Lưu trữ dữ liệu đang ghi trên bộ nhớ. Khi chạm ngưỡng dung lượng (ví dụ 64 KB hoặc 1,000 keys), Memtable đóng băng thành Immutable Memtable và xả (flush) xuống đĩa.
Định dạng file nhị phân SSTable (Sorted String Table): Tự thiết kế cấu trúc từng byte trên đĩa gồm 4 phần:
Data Block: Các cặp Key-Value đã sắp xếp + cờ Tombstone (đánh dấu xóa mềm).
Sparse Index: Lưu offset của mỗi key thứ $k$ trong RAM để dùng Binary Search nhảy thẳng tới vùng đĩa cần đọc thay vì quét từ đầu file.
Custom Bloom Filter (B12): Mảng bit kèm $h$ hàm băm độc lập gắn riêng cho từng SSTable. Nếu truy vấn một key không tồn tại, Bloom Filter trả về False ngay trên RAM $\rightarrow$ 0 lần đọc đĩa.
Footer: Lưu vị trí bắt đầu của Index và Bloom Filter.
Compaction Engine (Nút thắt thuật toán ăn điểm tuyệt đối): Dùng Min-Heap ($K$-Way Merge Iterator) để trộn nhiều SSTable cũ thành SSTable mới, loại bỏ các key bị ghi đè và dọn dẹp Tombstone. Cài đặt song song 2 chiến lược Compaction để đối đầu trực tiếp:
Size-Tiered Compaction (STCS): Gom các SSTable cùng kích thước để trộn (tối ưu cho ghi cực nhanh, nhưng tốn dung lượng đĩa và đọc chậm).
Leveled Compaction (LCS): Chia đĩa thành các tầng L0, L1, L2, L3 (tầng sau lớn gấp $10\times$ tầng trước), ép các SSTable từ L1 trở đi không được trùng dải key (tối ưu cho đọc cực nhanh, nhưng khuếch đại ghi cao).
2. Bộ thực nghiệm & CS 61B Differential Fuzzing

Oracle Verification: Dùng java.util.TreeMap (hoặc Map chuẩn) làm Oracle. Chạy bộ test Randomized Fuzzing 100,000 thao tác ngẫu nhiên (put, overwrite, delete, get, rangeScan(k1, k2), kèm các cú simulateCrash() bất ngờ) và khẳng định kết quả trả về của LSM-Tree khớp 100.00% với Oracle ở mọi bước.
Bộ chỉ số 3 chiều (RUM Conjecture) cho báo cáo 20 trang:
Write Amplification (WA): $\frac{\text{Tổng byte ghi xuống đĩa}}{\text{Tổng byte user thực ghi}}$.
Read Amplification (RA): Số lượng SSTable phải mở cho mỗi truy vấn get (đo riêng trường hợp Positive Lookup và Negative Lookup khi bật/tắt Bloom Filter).
Space Amplification (SA): Tỷ lệ dung lượng đĩa chiếm dụng thực tế so với kích thước dữ liệu sống.
Chạy trên 2 phân phối tải: Uniform Random và Zipfian 80/20 (20% key nóng bị cập nhật liên tục).
3. Giao diện "Visible Evidence & Inspectable Trace"

Bản đồ các tầng đĩa trực quan (Live Level Visualizer): Hiển thị từng ô SSTable ở Memtable, L0, L1, L2, L3. Khi chạy benchmark, bạn thấy rõ các file nhấp nháy màu xanh khi Bloom Filter chặn đứng truy vấn rác, nhấp nháy màu cam khi K-Way Merge nuốt 4 file L0 để đẻ ra 1 file L1.
Bảng đồng hồ hệ thống: Đồ thị thời gian thực vẽ p50 / p95 latency, tỷ lệ Bloom Filter False Positive Rate lý thuyết vs. thực tế, và thanh đo Write Amplification.