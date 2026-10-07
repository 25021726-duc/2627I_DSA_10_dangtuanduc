# Week 4 - Elementary Sorts

Thư mục này gồm lời giải cho 4 bài đầu trong tab **BT lập trình** của tài liệu môn học.

## Danh sách bài

1. `Bai1InsertionSortSurvey.java`: cài đặt và khảo sát Insertion Sort.
2. `Bai2SelectionSortSurvey.java`: cài đặt Selection Sort và so sánh với Insertion Sort.
3. `Bai3InsertionSortPart1.java`: HackerRank - Insertion Sort Part 1.
4. `Bai4JavaSort.java`: HackerRank - Java Sort.

## Biên dịch

```bash
mkdir -p /tmp/dsa-week4-classes
javac -d /tmp/dsa-week4-classes Week_4/*.java
```

## Chạy bài 1 và bài 2

Không truyền tham số để khảo sát dữ liệu ngẫu nhiên, đã sắp xếp xuôi, sắp xếp ngược và các giá trị bằng nhau:

```bash
java -cp /tmp/dsa-week4-classes Bai1InsertionSortSurvey
java -cp /tmp/dsa-week4-classes Bai2SelectionSortSurvey
```

Có thể truyền một hoặc nhiều file số nguyên để chương trình đo thêm dữ liệu test. Mỗi file được đo trung bình 3 lần; dữ liệu ngẫu nhiên được đo 5 lần; các loại dữ liệu sinh còn lại được đo 3 lần.

```bash
java -cp /tmp/dsa-week4-classes Bai1InsertionSortSurvey 1Kints.txt 4Kints.txt
java -cp /tmp/dsa-week4-classes Bai2SelectionSortSurvey 1Kints.txt 4Kints.txt
```

Kết quả có định dạng CSV để dễ đưa vào bảng tính. Số liệu của một lần chạy thực tế được lưu trong [BENCHMARK_RESULTS.md](BENCHMARK_RESULTS.md).

## Nhận xét độ phức tạp

- Insertion Sort chạy gần tuyến tính trên mảng đã sắp xếp và mảng gồm các giá trị bằng nhau, vì vòng lặp trong gần như không dịch chuyển phần tử.
- Trên dữ liệu ngẫu nhiên và sắp xếp ngược, Insertion Sort có thời gian bậc hai; mảng sắp xếp ngược là trường hợp xấu nhất.
- Selection Sort luôn thực hiện xấp xỉ `N(N - 1) / 2` phép so sánh, nên dạng dữ liệu đầu vào ít ảnh hưởng hơn đến thời gian chạy.
- Insertion Sort thường nhanh hơn Selection Sort với dữ liệu đã sắp xếp, gần sắp xếp hoặc có nhiều giá trị bằng nhau. Trên dữ liệu ngẫu nhiên, cả hai đều có độ phức tạp `O(N^2)`.

Lưu ý: thời gian tuyệt đối phụ thuộc máy và JVM. Nên chạy lại benchmark trên máy nộp bài nếu cần đưa số liệu vào báo cáo.

Khi nộp Bài 3 hoặc Bài 4 trực tiếp lên HackerRank, đổi tên lớp thành `Solution` theo khung code của trang.
