DELETE FROM offer_tab;
DELETE FROM car_lot;
DELETE FROM user_acc;

INSERT INTO user_acc
VALUES ('Kyle','Sinhart','sinhky01','qwerty',1);
INSERT INTO user_acc
VALUES ('Kyle','Sinhart','sinhky02','qwerty',2);
INSERT INTO user_acc
VALUES ('Coal','Sinhart','sinhky03','qwerty',1);
INSERT INTO user_acc
VALUES ('Cilian','Sinhart','sinhky04','qwerty',1);
INSERT INTO user_acc
VALUES ('Karl','Sinhart','sinhky05','qwerty',1);
INSERT INTO user_acc
VALUES ('Kev','Sinhart','sinhky06','qwerty',1);

INSERT INTO user_acc
VALUES ('DLR','DLR','DLR','DLR',2);
/* insert a dealer account */

INSERT INTO car_lot
VALUES ('Toyota','Prius','DLR',2007,24000,'A67T54');
INSERT INTO car_lot
VALUES ('Tesla','Model 3','sinhky01',2019,32800,'SD1267J');
INSERT INTO car_lot
VALUES ('Ford','Fusion','DLR',2013,29700,'KR37O12');
INSERT INTO car_lot
VALUES ('Mercedes','E150','DLR',2015,21800,'BE99HJ3');
INSERT INTO car_lot
VALUES ('Honda','Civic','DLR',2016,24200,'HA956');
INSERT INTO car_lot
VALUES ('Toyota','RAV4','DLR',2016,18500,'TH2I8O');
INSERT INTO car_lot
VALUES ('Toyota','Corolla','DLR',2011,13000,'QW45U7');
INSERT INTO car_lot
VALUES ('Dodge','Dart','DLR',1985,6000,'OLDMB13');


INSERT INTO offer_tab
VALUES ('sinhky01','OLDMB13',10000);
INSERT INTO offer_tab
VALUES ('sinhky05','OLDMB13',12000);
INSERT INTO offer_tab
VALUES ('sinhky03','HA956',10000);
INSERT INTO offer_tab
VALUES ('sinhky06','TH2I8O',10000);

commit;