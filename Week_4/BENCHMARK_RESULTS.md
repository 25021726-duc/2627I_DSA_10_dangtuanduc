# Kết quả khảo sát Insertion Sort và Selection Sort

Kết quả dưới đây được đo ngày 07/10/2026 bằng OpenJDK 25.0.4.1. Chương trình khởi động JVM trước khi đo, chỉ tính thời gian sắp xếp (không tính thời gian đọc file hay sao chép mảng), và kiểm tra mảng đầu ra đã tăng dần sau mỗi lần chạy.

Bộ dữ liệu file lấy từ `1Kints.txt`, `2Kints.txt`, `4Kints.txt` và `8Kints.txt` của Algorithms, 4th Edition. Thời gian tính bằng mili giây và là giá trị trung bình.

## Bài 1 - Insertion Sort

```csv
data_type,size,runs,average_ms
file:1Kints.txt,1000,3,0.077070
file:2Kints.txt,2000,3,0.248467
file:4Kints.txt,4000,3,1.110558
file:8Kints.txt,8000,3,4.758923
random,1000,5,0.080889
random,2000,5,0.339732
random,4000,5,1.208324
random,8000,5,4.761490
sorted,1000,3,0.001947
sorted,2000,3,0.002757
sorted,4000,3,0.004484
sorted,8000,3,0.013458
reverse,1000,3,0.157779
reverse,2000,3,0.614590
reverse,4000,3,2.477551
reverse,8000,3,9.399765
equal,1000,3,0.001944
equal,2000,3,0.003848
equal,4000,3,0.006674
equal,8000,3,0.008592
```

Khi kích thước tăng gấp đôi từ 4.000 lên 8.000, thời gian của dữ liệu ngẫu nhiên tăng gần 4 lần (`1,208324` lên `4,761490` ms) và dữ liệu đảo ngược cũng tăng gần 4 lần (`2,477551` lên `9,399765` ms). Điều này phù hợp với độ phức tạp `O(N²)`. Với dữ liệu đã sắp xếp hoặc toàn phần tử bằng nhau, không cần dịch phần tử nên thời gian tăng gần tuyến tính và nhỏ hơn rõ rệt.

## Bài 2 - Selection Sort và so sánh

```csv
data_type,size,runs,insertion_ms,selection_ms,faster
file:1Kints.txt,1000,3,0.066472,0.275944,insertion
file:2Kints.txt,2000,3,0.275519,1.064867,insertion
file:4Kints.txt,4000,3,1.147114,4.159397,insertion
file:8Kints.txt,8000,3,4.550783,15.731183,insertion
random,1000,5,0.077361,0.279985,insertion
random,2000,5,0.418738,1.092052,insertion
random,4000,5,1.126029,4.047470,insertion
random,8000,5,4.509915,16.030246,insertion
sorted,1000,3,0.002191,0.245314,insertion
sorted,2000,3,0.010580,0.977288,insertion
sorted,4000,3,0.012563,3.861694,insertion
sorted,8000,3,0.014412,16.115113,insertion
reverse,1000,3,0.193070,0.674479,insertion
reverse,2000,3,0.563834,1.406644,insertion
reverse,4000,3,2.216015,5.573782,insertion
reverse,8000,3,9.332844,24.075367,insertion
equal,1000,3,0.001993,0.408509,insertion
equal,2000,3,0.003680,0.994553,insertion
equal,4000,3,0.004173,3.870914,insertion
equal,8000,3,0.015989,15.907104,insertion
```

Selection Sort tăng gần 4 lần khi kích thước tăng gấp đôi với mọi dạng dữ liệu vì luôn quét toàn bộ phần chưa sắp xếp để tìm phần tử nhỏ nhất. Insertion Sort thích nghi với dữ liệu đầu vào tốt hơn: đặc biệt nhanh trên dữ liệu đã sắp xếp và dữ liệu bằng nhau. Trong lần đo này, Insertion Sort nhanh hơn Selection Sort ở tất cả trường hợp; kết luận quan trọng hơn số tuyệt đối là xu hướng tăng trưởng và sự khác nhau theo dạng dữ liệu.
