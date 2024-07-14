----------------------------------table section----------------------------------

--member table
create table member(
  id varchar2(50) not null primary key,
  passwd varchar2(60) not null, 
  name varchar2(10) not null,
  reg_date TIMESTAMP not null,
  address varchar2(100) not null,
  tel varchar2(20) not null
);

--manager table
create table manager(
 managerId varchar2(50) not null primary key,
 managerPasswd varchar2(60) not null
);


--goods table
create table goods(
    goods_id number not null primary key,
    goods_kind varchar2(4) not null, 
    goods_title varchar2(50) not null, 
    goods_price number not null, 
    goods_count number not null, 
    goods_size varchar2(10) not null, 
    goods_image varchar2(16) default 'nothing.jpg', 
    goods_content varchar2(200) not null, 
    discount_rate number default 10, 
    reg_date TIMESTAMP not null 
);
create sequence goods_seq start with 1 increment by 1;

--qna table
create table qna(
  qna_id number not null primary key, 
  goods_id number not null, 
  goods_title varchar2(100) not null, 
  qna_writer varchar2(50) not null, 
  qna_content varchar2(200) not null, 
  group_id number not null, 
  qora number not null, 
  reply number default 0, 
  reg_date TIMESTAMP not null 
);
CREATE SEQUENCE qna_seq START WITH 1 INCREMENT BY 1;

--bank table
 create table bank(
  account varchar2(30) not null,
  bank varchar2(20) not null,
  name varchar2(20) not null
);

--cart table
create table cart(
  cart_id number not null primary key,
  buyer varchar2(50) not null,
  goods_id number not null,
  goods_title varchar2(100) not null,
  buy_price number not null,
  buy_count number not null,
  goods_image varchar2(16) default 'nothing.jpg'
); 
CREATE SEQUENCE cart_seq START WITH 1 INCREMENT BY 1;

--buy table
create table buy(
  buy_id NUMBER(19, 0) not null,
  buyer varchar2(50) not null,
  goods_id varchar2(12) not null,
  goods_title varchar2(100) not null,
  buy_price number not null,
  buy_count number not null,
  goods_image varchar2(16) default 'nothing.jpg',
  buy_date TIMESTAMP not null,
  account varchar2(50) not null,
  deliveryName varchar2(10) not null,
  deliveryTel varchar2(20) not null,
  deliveryAddress varchar2(100) not null,
  sanction varchar2(20) default '상품준비중'
);


----------------------------------SQL statement----------------------------------

-------------------------- select --------------------------

select * from member;
select * from manager;
select * from goods;
select * from qna;
select * from bank;
select * from cart;
select * from buy;

-------------------------- transaction --------------------------

COMMIT;

-------------------------- insert --------------------------

--member
insert into member(id, passwd, name, reg_date, address, tel) 
values('lee','1234','이석진',  SYSDATE , '서울시 송파구', '010-3333-3333');
insert into member(id, passwd, name, reg_date, address, tel) 
values('kim','1234','김건우', SYSDATE , '경기도 남양주', '010-4444-4444');
insert into member(id, passwd, name, reg_date, address, tel) 
values('jjang','1234','김짱구', SYSDATE , '서울시 강남구', '010-5555-5555');

--manager
insert into manager(managerId,managerPasswd) values('leelee','1234');

--goods
-- 예시 데이터 (상의)
INSERT INTO goods (
    goods_id, goods_kind, goods_title, goods_price, goods_count, goods_size, goods_image, goods_content, discount_rate, reg_date
) VALUES (
    goods_seq.NEXTVAL, '100', 'Tom Comics T-shirt', 35000, 150, 'Large', '1.jpg', 'Tom comics T-shirt with logo', 10, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '100', 'Sweaterknitted T-shirt', 25000, 100, 'Medium', '2.jpg', 'Yellow wool sweaterknitted T-shirt', 10, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '100', 'White T-shirt', 50000, 80, 'Large', '3.jpg', 'Comfortable white T-shirt with logo', 15, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '100', 'White T-shirt', 50000, 70, 'Small', '4.jpg', 'Stylish white T-shirt with logo', 15, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '100', 'Sheesh T-shirt', 70000, 100, 'Large', '6.jpg', 'Sheesh T-shirt with logo', 10, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '100', 'Cartoon Black T-shirt', 20000, 100, 'Large', '7.jpg', 'Spray cartoon graphic ss black T-shirt with logo', 10, CURRENT_TIMESTAMP
);

-- 예시 데이터 (하의)
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '200', 'All Sunday Wide Denim Pants"', 200000, 100, 'Medium', 'a.jpg', 'Comfortable All Sunday wide denim pants', 15, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '200', 'Extra Wide Denim Pants', 250000, 100, 'Large', 'b.jpg', 'Deep blue Extra Wide Denim Pants', 15, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '200', 'Carpenter Bermuda Pleated Denim Shorts', 150000, 100, 'Medium', 'c.jpg', 'Carpenter bermuda pleated denim shorts indigo blue', 5, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '200', 'Damage Washed Denim Pants', 250000, 100, 'Large', 'd.jpg', 'Damage washed denim pants medium blue cool air', 5, CURRENT_TIMESTAMP
);
INSERT INTO goods VALUES (
    goods_seq.NEXTVAL, '200', 'Wide Denim Shorts', 200000, 100, 'Small', 'f.jpg', 'Wide denim shorts dark grey', 5, CURRENT_TIMESTAMP
);

--bank
insert into bank(account, bank, name) values('11111-111-11111','내일은행','이석진');
insert into bank(account, bank, name) values('22222-222-22222','미래은행','김건우');
insert into bank(account, bank, name) values('11111-111-11111','미래은행','김짱구');
insert into bank(account, bank, name) values('11111-111-11111','오늘은행','ccc');
commit;


DELETE FROM MANAGER WHERE  managerId = 'leelee';
SELECT * FROM manager WHERE managerId = 'leelee';

