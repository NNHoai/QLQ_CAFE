--
-- Cơ sở dữ liệu: `qlquancafe`
--
CREATE DATABASE IF NOT EXISTS `qlquancafe` DEFAULT CHARACTER SET utf8 COLLATE utf8_turkish_ci;
USE `qlquancafe`;

DELIMITER $$
--
-- Thủ tục
--
DROP PROCEDURE IF EXISTS `dem`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `dem` (OUT `m` INT)  begin
SELECT count(*)+1 into m  FROM hoadon;
end$$

DROP PROCEDURE IF EXISTS `getBillOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBillOrder` (IN `id` INT)  begin
select a.tenmon,c.soluong,a.dongia,c.soluong*a.dongia as thanhtien 
from monan as a, hoadon as b, chitiethoadon as c 
where b.id_hd = c.id_hd and a.id_ma = c.id_ma and b.trangthai = "Chưa thanh toán" and b.id_ban = id;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ban`
--

DROP TABLE IF EXISTS `ban`;
CREATE TABLE IF NOT EXISTS `ban` (
  `id_ban` int(11) NOT NULL AUTO_INCREMENT,
  `tenban` varchar(45) CHARACTER SET utf8 NOT NULL,
  `trangthai` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_ban`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ban`
--

INSERT INTO `ban` (`id_ban`, `tenban`, `trangthai`) VALUES
(1, 'Bàn 1', 'Bận'),
(2, 'Bàn 2', 'Bận'),
(3, 'Bàn 3', 'Bận'),
(4, 'Bàn 4', 'Trống'),
(5, 'Bàn 5', 'Bận'),
(6, 'Bàn 6', 'Bận'),
(7, 'Bàn 7', 'Trống'),
(8, 'Bàn 8', 'Bận'),
(9, 'Bàn 9', 'Trống'),
(10, 'Bàn 10', 'Trống'),
(11, 'Bàn 11', 'Trống'),
(12, 'Bàn 12', 'Trống'),
(13, 'Bàn 13', 'Trống'),
(14, 'Bàn 14', 'Trống'),
(15, 'Bàn 15', 'Trống'),
(16, 'Bàn 16', 'Trống'),
(17, 'Bàn 17', 'Trống'),
(18, 'Bàn 18', 'Trống'),
(19, 'Bàn 19', 'Trống'),
(20, 'Bàn 20', 'Trống'),
(21, 'Bàn 21', 'Trống'),
(22, 'Bàn 22', 'Trống');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `id_cthd` int(11) NOT NULL AUTO_INCREMENT,
  `id_hd` int(11) NOT NULL,
  `id_ma` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  PRIMARY KEY (`id_cthd`),
  KEY `fk_cthd_ma` (`id_ma`),
  KEY `fk_cthd_hd` (`id_hd`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`id_cthd`, `id_hd`, `id_ma`, `soluong`) VALUES
(4, 2, 2, 1),
(5, 2, 5, 3),
(6, 3, 10, 2),
(7, 4, 12, 5),
(25, 25, 1, 1),
(30, 27, 1, 1),
(31, 27, 11, 1),
(32, 28, 1, 1),
(33, 28, 3, 1),
(34, 29, 1, 1),
(35, 30, 1, 1),
(36, 31, 1, 1),
(37, 32, 1, 1),
(38, 33, 1, 1),
(39, 34, 1, 1),
(40, 35, 1, 1),
(41, 36, 1, 1),
(42, 37, 1, 1),
(43, 38, 1, 1),
(44, 39, 1, 1),
(46, 40, 5, 1),
(47, 40, 3, 1),
(49, 2, 7, 3),
(51, 3, 5, 2),
(52, 3, 12, 3),
(54, 27, 5, 4),
(55, 4, 6, 5),
(56, 41, 5, 1),
(57, 41, 8, 1),
(58, 42, 7, 1),
(59, 2, 1, 4),
(60, 43, 5, 1),
(61, 44, 4, 1),
(62, 44, 12, 1),
(63, 45, 1, 1),
(64, 45, 6, 1),
(65, 46, 1, 2),
(66, 47, 5, 1),
(67, 48, 5, 1),
(68, 49, 5, 2),
(69, 49, 2, 1),
(70, 49, 12, 1),
(71, 26, 12, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `congviec`
--

DROP TABLE IF EXISTS `congviec`;
CREATE TABLE IF NOT EXISTS `congviec` (
  `id_cv` int(11) NOT NULL AUTO_INCREMENT,
  `tencv` varchar(45) CHARACTER SET utf8 NOT NULL,
  `luongcb` float NOT NULL,
  PRIMARY KEY (`id_cv`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `congviec`
--

INSERT INTO `congviec` (`id_cv`, `tencv`, `luongcb`) VALUES
(1, 'Quản lý', 8000000),
(2, 'Nhân viên', 4000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
CREATE TABLE IF NOT EXISTS `hoadon` (
  `id_hd` int(11) NOT NULL AUTO_INCREMENT,
  `id_nv` int(11) NOT NULL,
  `id_ban` int(11) NOT NULL,
  `tongtien` double NOT NULL,
  `ngaygio` date NOT NULL,
  `trangthai` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_hd`),
  KEY `fk_hd_b` (`id_ban`),
  KEY `fk_hd_nv` (`id_nv`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`id_hd`, `id_nv`, `id_ban`, `tongtien`, `ngaygio`, `trangthai`) VALUES
(1, 4, 4, 0, '2021-06-18', 'Đã thanh toán'),
(2, 1, 1, 204000, '2021-04-21', 'Đã thanh toán'),
(3, 2, 2, 145000, '2021-05-30', 'Đã thanh toán'),
(4, 3, 3, 166250, '2021-06-18', 'Đã thanh toán'),
(25, 1, 5, 10000, '2021-06-18', 'Đã thanh toán'),
(26, 1, 5, 0, '2021-06-18', 'Chưa thanh toán'),
(27, 4, 6, 0, '2021-06-18', 'Chưa thanh toán'),
(28, 4, 7, 25000, '2021-06-18', 'Đã thanh toán'),
(29, 4, 8, 0, '2021-06-18', 'Chưa thanh toán'),
(30, 4, 9, 9500, '2021-06-18', 'Đã thanh toán'),
(31, 4, 10, 10000, '2021-06-18', 'Đã thanh toán'),
(32, 4, 11, 10000, '2021-06-18', 'Đã thanh toán'),
(33, 4, 12, 10000, '2021-06-18', 'Đã thanh toán'),
(34, 4, 13, 10000, '2021-06-18', 'Đã thanh toán'),
(35, 4, 14, 10000, '2021-06-18', 'Đã thanh toán'),
(36, 4, 15, 10000, '2021-06-18', 'Đã thanh toán'),
(37, 4, 16, 10000, '2021-06-18', 'Đã thanh toán'),
(38, 4, 17, 10000, '2021-06-18', 'Đã thanh toán'),
(39, 4, 18, 10000, '2021-06-18', 'Đã thanh toán'),
(40, 4, 20, 40000, '2021-06-18', 'Đã thanh toán'),
(41, 4, 3, 50000, '2021-06-24', 'Đã thanh toán'),
(42, 4, 3, 13000, '2021-08-01', 'Đã thanh toán'),
(43, 4, 1, 0, '2021-08-01', 'Chưa thanh toán'),
(44, 4, 2, 50000, '2021-08-02', 'Đã thanh toán'),
(45, 4, 3, 20000, '2021-08-02', 'Đã thanh toán'),
(46, 4, 3, 20000, '2021-08-02', 'Đã thanh toán'),
(47, 4, 4, 25000, '2021-08-02', 'Đã thanh toán'),
(48, 4, 2, 0, '2021-08-02', 'Chưa thanh toán'),
(49, 4, 3, 0, '2021-08-02', 'Chưa thanh toán');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `monan`
--

DROP TABLE IF EXISTS `monan`;
CREATE TABLE IF NOT EXISTS `monan` (
  `id_ma` int(11) NOT NULL AUTO_INCREMENT,
  `id_td` int(11) NOT NULL,
  `tenmon` varchar(45) NOT NULL,
  `dongia` float NOT NULL,
  PRIMARY KEY (`id_ma`),
  KEY `fk_ma_td` (`id_td`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `monan`
--

INSERT INTO `monan` (`id_ma`, `id_td`, `tenmon`, `dongia`) VALUES
(1, 1, '7up', 10000),
(2, 4, 'Bánh kem', 50000),
(3, 3, 'Cafe sữa', 15000),
(4, 5, 'Nước ép ổi', 25000),
(5, 2, 'Trà sữa Matcha', 25000),
(6, 1, 'Pepsi', 10000),
(7, 3, 'Cafe đen', 13000),
(8, 4, 'Kem dâu', 25000),
(10, 1, 'Revive', 10000),
(11, 1, 'Number 1', 10000),
(12, 4, 'Bánh Mochi', 25000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `id_nv` int(11) NOT NULL AUTO_INCREMENT,
  `id_cv` int(11) NOT NULL,
  `ten_nv` varchar(50) CHARACTER SET utf8 NOT NULL,
  `lienhe` varchar(50) CHARACTER SET utf8 NOT NULL,
  `diachi` varchar(80) CHARACTER SET utf8 NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `hesoluong` float NOT NULL,
  `casang` tinyint(1) DEFAULT NULL,
  `cachieu` tinyint(1) DEFAULT NULL,
  `catoi` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_nv`),
  KEY `fk_nv_cv` (`id_cv`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`id_nv`, `id_cv`, `ten_nv`, `lienhe`, `diachi`, `username`, `password`, `hesoluong`, `casang`, `cachieu`, `catoi`) VALUES
(1, 1, 'Hoàng Nhật Tân', '', 'Đà Nẵng', 'nhattan', '$2a$12$1ioN9goBW1DpDpQs5AbF/O6dwrqXMx9qGvmfoFmcn5EE/XXSni8uC', 0, NULL, NULL, NULL),
(2, 1, 'Hiếu Nguyễn', '0000000000', 'Nghệ An', 'hieungu', '$2a$12$CAwKamVmaFM/yre/IyTb.O5JqKHEtJsnw91IfFek3zL7iLlBqrykq', 0, NULL, NULL, NULL),
(3, 2, 'Nguyễn Ngọc Hoài', '0123456789', 'Gia Lai', 'hoai', '12345', 0, NULL, NULL, NULL),
(4, 1, 'Trương Công Văn', '', '', 'van', '123456', 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtin`
--

DROP TABLE IF EXISTS `thongtin`;
CREATE TABLE IF NOT EXISTS `thongtin` (
  `stt` int(11) NOT NULL AUTO_INCREMENT,
  `tencuahang` varchar(100) CHARACTER SET utf8 NOT NULL,
  `lienhe` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `diachi` varchar(200) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`stt`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Đang đổ dữ liệu cho bảng `thongtin`
--

INSERT INTO `thongtin` (`stt`, `tencuahang`, `lienhe`, `diachi`) VALUES
(1, 'Coffee Shop', '038888888899', '155-Tôn Đảng-Hòa Minh-Liên Chiểu-Đà Nẵng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thucdon`
--

DROP TABLE IF EXISTS `thucdon`;
CREATE TABLE IF NOT EXISTS `thucdon` (
  `id_td` int(11) NOT NULL,
  `ten_td` varchar(45) NOT NULL,
  PRIMARY KEY (`id_td`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `thucdon`
--

INSERT INTO `thucdon` (`id_td`, `ten_td`) VALUES
(1, 'Nước ngọt'),
(2, 'Trà sữa'),
(3, 'Cafe'),
(4, 'Điểm tâm'),
(5, 'Nước ép'),
(6, 'Sinh tố');

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `fk_cthd_hd` FOREIGN KEY (`id_hd`) REFERENCES `hoadon` (`id_hd`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cthd_ma` FOREIGN KEY (`id_ma`) REFERENCES `monan` (`id_ma`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk_hd_b` FOREIGN KEY (`id_ban`) REFERENCES `ban` (`id_ban`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hd_nv` FOREIGN KEY (`id_nv`) REFERENCES `nhanvien` (`id_nv`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `monan`
--
ALTER TABLE `monan`
  ADD CONSTRAINT `fk_ma_td` FOREIGN KEY (`id_td`) REFERENCES `thucdon` (`id_td`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `fk_nv_cv` FOREIGN KEY (`id_cv`) REFERENCES `congviec` (`id_cv`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

