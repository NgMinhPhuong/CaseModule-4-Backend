create table if not exists drug(
    id int not null auto_increment primary key,
    name varchar(255),
    expire varchar(255),
    image varchar(255),
    price double
    );

insert into drug(name,expire,image,price) values
('panadol','20/11/2024','https://cdn.nhathuoclongchau.com.vn/unsafe/800x0/filters:quality(95)/https://cms-prod.s3-sgn09.fptcloud.com/panadol_la_thuoc_gi_su_dung_thuoc_panadol_co_gay_nghien_khong_1_b54c31e380.jpg',500),
('penicillin','20/10/2024','https://www.shutterstock.com/shutterstock/photos/2318019357/display_1500/stock-photo-penicillin-antibiotic-bacterial-infection-antibiotic-injection-oral-2318019357.jpg',400),
('paracetamol','29/9/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/636x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/paracetamol_d1e7bc4f13.jpg',300),
('Orihiro Glucosamine','25/5/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/DSC_09160_f0ac0588b5.jpg',800),
('Omega 3-6-9','26/6/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00028684_vien_uong_omega_3_6_9_naturecare_giam_cholesterol_bao_ve_tim_mach_60_vien_9210_63a9_large_b5425cd082.jpg',600),
('Calci K-2','30/4/2024','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00503275_vien_uong_bo_sung_canxi_giam_nguy_co_loang_xuong_pharma_word_calci_k_2_60v_2299_63ed_large_322e824179.jpg',465),
('KenKan Nattokinase','23/3/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00032767_vien_uong_kenkan_nattokinase_2400fu_60v_5866_6193_large_c6c2382123.jpg',415),
('Kaki Ekisu','13/3/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00502603_vien_uong_tang_cuong_chuc_nang_sinh_ly_nam_kenkan_kaki_ekisu_tohchukasou_60v_3488_6371_large_7c3a87ff1d.jpg',412),
('Phế Khang Hải Thượng Vương Vesta','14/4/2024','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00032568_phe_khang_hai_thuong_vuong_3x10_7009_616f_large_12889d8d3b.jpg',315),
('B Complex Vitamin','1/8/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00032942_b_complex_vitamin_royal_care_60v_5253_61c0_large_17e5e39343.jpg',255),
('Omexxel Ginkgo','30/9/2026','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00031036_omexxel_ginkgo_120_2x15_5958_633e_large_cb67aacf67.jpg',260),
('Glucosamine','30/8/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00032918_glucosamine_and_chondroitin_jpanwell_120v_9745_61a5_large_41ffa86dc1.JPG',190),
('Anica Ocavill','30/7/2024','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00021988_anica_phytextra_60v_5137_6347_large_81a2361ecd.jpg',500),
('Multi Vitas Lab Well','30/6/2024','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/DSC_08333_0d6a18e346.jpg',350),
('Elevit Bayer','25/7/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00033208_vitamin_tong_hop_elevit_bau_bayer_3x10_9269_61de_large_296df133e8.jpg',256),
('Premom Gold Jpanwell','11/11/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00500357_vien_uong_ho_tro_phu_nu_truoc_khi_mang_thai_premom_gold_jpanwell_60v_8227_6298_large_ae1845b873.jpg',1000),
('Dr. Caci Ocavill','12/12/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00500119_vien_uong_ho_tro_giam_nguy_co_loang_xuong_drcaci_ocavill_60v_9841_628b_large_f16d550237.jpg',900),
('Multivitamin +Zn +D3','7/7/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00500791_vien_uong_tang_de_khang_multivitamin_zn_d3_royal_care_60v_1169_62be_large_6f3a54bfa4.jpg',700),
('Teens Active Vitamins For Life','5/5/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00345388_teens_active_bo_sung_vitamin_cho_thieu_nien_5186_63a9_large_c8259bcfea.jpg',444),
('Happy Mom Jpanwell','4/4/2024','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00021932_happy_mom_jpanwell_60v_5688_5ff7_large_8cba32414f.JPG',580),
('Immune++','3/3/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/IMG_0310_1e60decf2a.jpg',45),
('Siro Special Kid Multivitamines','6/6/2025','https://cdn.nhathuoclongchau.com.vn/unsafe/373x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/00017886_special_kid_multivitamins_125ml_8621_5fb3_large_78ae9d56db.JPG',140)
;

create table if not exists category(
id int not null auto_increment primary key,
name varchar(255)
    );

insert into category(name) values
    ('thuốc ho'),
    ('thực phẩm bổ sung'),
    ('dinh dưỡng')
;
create table if not exists category_drug(
    id int not null auto_increment primary key,
    quantity int,
    category_id int not null,
    drug_id int not null,
    constraint category_id_fk foreign key (category_id) references category (id),
    constraint drug_id_fk foreign key (drug_id) references drug (id)
    );

insert into category_drug(quantity,category_id,drug_id) values
(10,1,1),
(5,2,2),
(7,3,3),
(10,2,4),
(15,3,5),
(20,1,6),
(10,2,7),
(19,1,8),
(12,2,9),
(25,3,10),
(24,1,11),
(30,3,12),
(5,2,13),
(3,1,14),
(6,2,15),
(7,3,16),
(1,2,17),
(12,3,18),
(33,2,19),
(3,3,20)
;