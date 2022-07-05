INSERT INTO tb_user(id, email, first_name, last_name, mobile_number, password, username)
VALUES (1, 'jampirojam@ojam.id','Mach','Rojamz','0897550001','$2a$10$e3jmiu9wNRxL/GyNapn7gO5GtEfdhrimXKR6LLuZ8NAf9eB7wvHHq','jampirojam');

INSERT INTO tb_agency(id, code, details, name, owner_user_id) VALUES 
(1, 'BDL','PT Blue Pigeon','Blue Pig',1),
(2, 'JML','PT Sinar Berkah','Restu Bunda',1),
(3, 'LKM','PT Sumber Bahagia','Sumber Bahagia',1);

INSERT INTO tb_bus(id, capacity, code, make, agency_id) VALUES 
(1, 20,'BP01','20',1),
(2, 15,'BP02','15',1),
(3, 20,'RB01','20',2),
(4, 15,'RB02','15',2),
(5, 20,'SB01','20',3),
(6, 15,'SB02','15',3);

INSERT INTO tb_role (id, name) VALUES 
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO tb_stop (id, code, detail, name) VALUES
(1, '1-4', 'Surabaya', 'Malang 1-4'),
(2, '1-8', 'Surabaya', 'Semarang 1-8'),
(3, '1-3', 'Surabaya', 'Bandung 1-3'),
(4, '1-2', 'Sukabumi', 'Sukarame 1-2'),
(5, '1-10', 'Tanjung Senang', 'Jati Agung 1-10');

INSERT INTO tb_user_roles (user_id, role_id) VALUES
(1, 2);

INSERT INTO tb_trip (id, fare, journey_time, agency_id, bus_id, dest_stop_id, source_stop_id) VALUES
(1, 15000, 15, 1, 1, 1, 3),
(2, 15000, 2, 2, 2, 5, 2),
(3, 15000, 3, 3, 2, 2, 3),
(4, 15000, 5, 2, 1, 4, 4),
(5, 15000, 4, 2, 2, 2, 5),
(6, 15000, 7, 3, 1, 3, 5);