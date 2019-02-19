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
update_date datetime comment "更新日"
)
default charset=utf8
comment="会員情報テーブル";

insert into user_info values
(1,"guest","guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"internous.guest@gmail.com",0,0,now(),now()),
(2,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",0,"internous.guest2@gmail.com",0,0,now(),now());

create table product_info(
id int primary key not null auto_increment comment "ID",
product_id int unique not null comment "商品ID",
product_name varchar(100) unique not null comment "商品名",
product_name_kana varchar(100) unique not null comment "商品名かな",
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
(1,1,"あんみつ","あんみつ","あんみつの商品詳細",2,100,"./images","annmitsu.jpg",now(),"発売会社",0,now(),now()),
(2,2,"ひよこ","ひよこ","ひよこの商品詳細",2,200,"./images","hiyoko.jpg",now(),"発売会社",0,now(),now()),
(3,3,"いちご大福","いちごだいふく","いちご大福の商品詳細",2,300,"./images","ichigodaifuku.jpg",now(),"発売会社",0,now(),now()),
(11,11,"アップルパイ","あっぷるぱい","アップルパイの商品詳細",3,100,"./images","applepie.jpg",now(),"発売会社",0,now(),now()),
(12,12,"バームクーヘン","ばーむくーへん","バームクーヘンの商品詳細",3,200,"./images","baumkuchen.jpg",now(),"発売会社",0,now(),now()),
(13,13,"バターサンド","ばたーさんど","バターサンドの商品詳細",3,300,"./images","buttersand.jpg",now(),"発売会社",0,now(),now()),
(14,14,"カップケーキ","かっぷけーき","カップケーキの商品詳細",3,400,"./images","cupcake.jpg",now(),"発売会社",0,now(),now()),
(21,21,"チョコポテト","ちょこぽてと","チョコポテトの商品詳細",4,100,"./images","chocopotato.jpg",now(),"発売会社",0,now(),now()),
(22,22,"ポップコーン","ぽっぷこーん","ポップコーンの商品詳細",4,200,"./images","garrettepopcorn.jpg",now(),"発売会社",0,now(),now()),
(23,23,"辛いスナック","からいすなっく","辛いスナックの商品詳細",4,300,"./images","karai_snack.jpg",now(),"発売会社",0,now(),now()),
(24,24,"肉ざんまいコーンスナック","にくざんまいこーんすなっく","肉ざんまいコーンスナックの商品詳細",4,400,"./images","nikuzanmaicorn.jpg",now(),"発売会社",0,now(),now()),
(25,25,"大人ポテチ","おとなぽてち","大人ポテチの商品詳細",4,500,"./images","otonarich.jpg",now(),"発売会社",0,now(),now());

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
update_date datetime comment "更新日",
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
(1,"guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー","internous.guest@gmail.com","09000000000","東京都千代田区霞が関 3-6-15",now(),now());

create table m_category(
id int primary key not null auto_increment comment "ID",
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