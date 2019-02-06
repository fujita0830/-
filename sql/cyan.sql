set names utf8;
set foreign_key_checks=0;
drop database if exists cyan;
create database if not exists cyan;
use cyan;

create table user_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) unique not null comment "ユーザーID",
password varchar(16) not null comment "パスワード",
family_name varchar(32) not null comment "姓",
first_name varchar(32) not null comment "名",
family_name_kana varchar(32) not null comment "姓かな",
first_name_kana varchar(32) not null comment "名かな",
sex tinyint not null default 0 comment "姓別",
email varchar(32) not null comment "メールアドレス",
status tinyint not null default 0 comment "ステータス",
logined tinyint not null default 0 comment "ログインフラグ",
regist_date datetime not null comment "登録日",
update_date datetime not null comment "更新日"
)
default charset=utf8
comment="会員情報テーブル";

insert into user_info values
(1,"guest","guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"guest@gmail.com",0,0,now(),now()),
(2,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",0,"guest2@gmail.com",0,0,now(),now()),
(3,"guest3","guest3","インターノウス","ゲストユーザー3","いんたーのうす","げすとゆーざー3",0,"guest3@gmail.com",0,0,now(),now()),
(4,"guest4","guest4","インターノウス","ゲストユーザー4","いんたーのうす","げすとゆーざー4",0,"guest4@gmail.com",0,0,now(),now()),
(5,"guest5","guest5","インターノウス","ゲストユーザー5","いんたーのうす","げすとゆーざー5",0,"guest5@gmail.com",0,0,now(),now()),
(6,"guest6","guest6","インターノウス","ゲストユーザー6","いんたーのうす","げすとゆーざー6",0,"guest6@gmail.com",0,0,now(),now()),
(7,"guest7","guest7","インターノウス","ゲストユーザー7","いんたーのうす","げすとゆーざー7",0,"guest7@gmail.com",0,0,now(),now()),
(8,"guest8","guest8","インターノウス","ゲストユーザー8","いんたーのうす","げすとゆーざー8",0,"guest8@gmail.com",0,0,now(),now()),
(9,"guest9","guest9","インターノウス","ゲストユーザー9","いんたーのうす","げすとゆーざー9",0,"guest9@gmail.com",0,0,now(),now()),
(10,"guest10","guest10","インターノウス","ゲストユーザー10","いんたーのうす","げすとゆーざー10",0,"guest10@gmail.com",0,0,now(),now()),
(11,"guest11","guest11","インターノウス","ゲストユーザー11","いんたーのうす","げすとゆーざー11",0,"guest11@gmail.com",0,0,now(),now()),
(12,"guest12","guest12","インターノウス","ゲストユーザー12","いんたーのうす","げすとゆーざー12",0,"guest12@gmail.com",0,0,now(),now());

create table product_info(
id int primary key not null auto_increment comment "ID",
product_id int unique not null comment "商品ID",
product_name varchar(100) unique not null comment "商品名",
product_name_kana varchar(100) not null comment "商品名かな",
product_description varchar(255) not null comment "商品詳細",
category_id int not null not null comment "カテゴリID",
price int comment "価格",
image_file_path varchar(100) comment "画像ファイルパス",
image_file_name varchar(50) comment "画像ファイル名",
release_date datetime not null comment "発売年月",
release_company varchar(50) comment "発売会社",
status tinyint not null default 0 comment "ステータス",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日",
foreign key(category_id) references m_category(category_id)
)
default charset=utf8
comment="商品情報テーブル";

insert into product_info values
(1,1,"和菓子１","わがし１","和菓子１の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(2,2,"和菓子２","わがし２","和菓子２の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(3,3,"和菓子３","わがし３","和菓子３の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(4,4,"和菓子４","わがし４","和菓子４の商品詳細",2,400,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(5,5,"和菓子５","わがし５","和菓子５の商品詳細",2,500,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(6,6,"和菓子６","わがし６","和菓子６の商品詳細",2,600,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(7,7,"和菓子７","わがし７","和菓子７の商品詳細",2,700,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(8,8,"和菓子８","わがし８","和菓子８の商品詳細",2,800,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(9,9,"和菓子９","わがし９","和菓子９の商品詳細",2,900,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(10,10,"和菓子１０","わがし１０","和菓子１０の商品詳細",2,1000,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(11,11,"洋菓子１","ようがし１","洋菓子１の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(12,12,"洋菓子２","ようがし２","洋菓子２の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(13,13,"洋菓子３","ようがし３","洋菓子３の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(14,14,"洋菓子４","ようがし４","洋菓子４の商品詳細",3,400,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(15,15,"洋菓子５","ようがし５","洋菓子５の商品詳細",3,500,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(16,16,"洋菓子６","ようがし６","洋菓子６の商品詳細",3,600,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(17,17,"洋菓子７","ようがし７","洋菓子７の商品詳細",3,700,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(18,18,"洋菓子８","ようがし８","洋菓子８の商品詳細",3,800,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(19,19,"洋菓子９","ようがし９","洋菓子９の商品詳細",3,900,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(20,20,"洋菓子１０","ようがし１０","洋菓子１０の商品詳細",3,1000,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(21,21,"スナック菓子１","すなっくがし１","スナック菓子１の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(22,22,"スナック菓子２","すなっくがし２","スナック菓子２の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(23,23,"スナック菓子３","すなっくがし３","スナック菓子３の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(24,24,"スナック菓子４","すなっくがし４","スナック菓子４の商品詳細",4,400,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(25,25,"スナック菓子５","すなっくがし５","スナック菓子５の商品詳細",4,500,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(26,26,"スナック菓子６","すなっくがし６","スナック菓子６の商品詳細",4,600,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(27,27,"スナック菓子７","すなっくがし７","スナック菓子７の商品詳細",4,700,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(28,28,"スナック菓子８","すなっくがし８","スナック菓子８の商品詳細",4,800,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(29,29,"スナック菓子９","すなっくがし９","スナック菓子９の商品詳細",4,900,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
(30,30,"スナック菓子１０","すなっくがし１０","スナック菓子１０の商品詳細",4,1000,"./images","sample.jpg",now(),"発売会社",0,now(),now());

create table cart_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
temp_user_id varchar(16) comment "仮ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日"
)
default charset=utf8
comment="カート情報テーブル";

create table purchase_history_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
product_id int not null comment "商品ID",
product_count int not null comment "個数",
price int not null comment "金額",
destination_id int not null comment "宛先情報ID",
regist_date datetime not null comment "登録日",
update_date datetime not null comment "更新日",
foreign key(user_id) references user_info(user_id),
foreign key(product_id) references product_info(product_id)
)
default charset=utf8
comment="購入履歴情報テーブル";

create table destination_info(
id int primary key not null auto_increment comment "ID",
user_id varchar(16) not null comment "ユーザーID",
family_name varchar(32) not null comment "姓",
first_name varchar(32) not null comment "名",
family_name_kana varchar(32) not null comment "姓かな",
first_name_kana varchar(32) not null comment "名かな",
email varchar(32) not null comment "メールアドレス",
tel_number varchar(13) not null comment "電話番号",
user_address varchar(50) not null comment "住所",
regist_date datetime not null comment "登録日",
update_date datetime comment "更新日"
)
default charset=utf8
comment="宛先情報テーブル";

insert into destination_info values
(1,"guest","インターノウス","テストユーザー","いんたーのうす","てすとゆーざー","guest@internous.co.jp","080-1234-5678","東京都千代田区三番町１−１　KY三番町ビル1F",now(),now());

create table m_category(
id int primary key not null comment "ID",
category_id int not null unique comment "カテゴリID",
category_name varchar(20) not null unique comment "カテゴリ名",
category_description varchar(100) comment "カテゴリ詳細",
insert_date datetime not null comment "登録日",
update_date datetime comment "更新日"
)
default charset=utf8
comment="カテゴリマスタテーブル";

insert into m_category values
(1,1,"すべてのカテゴリ","和菓子、洋菓子、スナック菓子すべてのカテゴリが対象となります",now(), null),
(2,2,"和菓子","和菓子に関するカテゴリが対象となります",now(),null),
(3,3,"洋菓子","洋菓子に関するカテゴリが対象となります",now(),null),
(4,4,"スナック菓子","スナック菓子に関するカテゴリが対象となります",now(),null);