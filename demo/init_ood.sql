create table ood.author
(
    name varchar(255) null,
    id   bigint       not null
        primary key
);

create table ood.book
(
    title       varchar(255) null,
    shelf       varchar(255) null,
    section     varchar(255) null,
    isbn_number varchar(255) null,
    id          bigint       not null
        primary key
);

create table ood.book_authors
(
    authors_id bigint not null,
    books_id   bigint not null,
    constraint FK551i3sllw1wj7ex6nir16blsm
        foreign key (authors_id) references ood.author (id),
    constraint FKmuhqocx8etx13u6jrtutnumek
        foreign key (books_id) references ood.book (id)
);

create table ood.book_copy
(
    book_copies            bigint null,
    expected_returned_date date   null,
    available              bit    null,
    id                     bigint not null
        primary key,
    constraint FK3u0rwha9yqnlyty7vnlbj0aio
        foreign key (book_copies) references ood.book (id)
);

create table ood.customer
(
    name          varchar(255) null,
    max_rentals   int          null,
    email         varchar(255) null,
    date_of_birth date         null,
    address       varchar(255) null,
    id            bigint       not null
        primary key,
    late_fees     int          null
);

create table ood.book_rental
(
    customer_id          bigint null,
    book_copy            bigint null,
    book                 bigint null,
    status               int    null,
    late_fees            int    null,
    expected_return_date date   null,
    check_out_date       date   null,
    actual_return_date   date   null,
    id                   bigint not null
        primary key,
    constraint FKbys1q9y9lfg7hgfdm5bxwebvu
        foreign key (book_copy) references ood.book_copy (id),
    constraint FKteiyoa7liagrdvjymvr6ny0r3
        foreign key (customer_id) references ood.customer (id),
    constraint FKtrpkogmrh5f9a6c8mqyrmcxc6
        foreign key (book) references ood.book (id)
);

create table ood.book_waiting_list
(
    waiting_list_id bigint not null,
    book_id         bigint not null,
    constraint UK_bamygn5qp0cgpfhduxssfvnjd
        unique (waiting_list_id),
    constraint FK2ft23p7ond78wmpexqjhdv6cw
        foreign key (book_id) references ood.book (id),
    constraint FKn0uvtxo1i8ob7m4k63sjohrj9
        foreign key (waiting_list_id) references ood.customer (id)
);

create table ood.hibernate_sequence
(
    next_val bigint null
);

INSERT INTO ood.author (id, name) VALUES (28, 'Tenrin');
INSERT INTO ood.author (id, name) VALUES (32, 'Sean');
INSERT INTO ood.author (id, name) VALUES (40, 'Author');
INSERT INTO ood.author (id, name) VALUES (42, 'Dr.Sean');
INSERT INTO ood.author (id, name) VALUES (51, 'YMV');
INSERT INTO ood.author (id, name) VALUES (53, 'Yanmin');
INSERT INTO ood.author (id, name) VALUES (69, 'Smoker');
INSERT INTO ood.author (id, name) VALUES (71, 'Abey');
INSERT INTO ood.author (id, name) VALUES (72, 'Vivek');
INSERT INTO ood.author (id, name) VALUES (73, 'Anca');

INSERT INTO ood.book (id, isbn_number, section, shelf, title) VALUES (1, '123a', '666', '666', 'Run');
INSERT INTO ood.book (id, isbn_number, section, shelf, title) VALUES (2, '33333b', '77', '88', 'Run');
INSERT INTO ood.book (id, isbn_number, section, shelf, title) VALUES (29, 'c11', '777', '114514', 'RunRunRunRun');
INSERT INTO ood.book (id, isbn_number, section, shelf, title) VALUES (33, '777d', '777', '777', 'OOP');
INSERT INTO ood.book (id, isbn_number, section, shelf, title) VALUES (41, '1235e', '1234', '123541', 'Hello');
INSERT INTO ood.book (id, isbn_number, section, shelf, title) VALUES (52, 'c222', '12345', '12345', 'OOD');
INSERT INTO ood.book (id, isbn_number, section, shelf, title) VALUES (70, '114514aaa', '17', '17', 'Computer Graphic');

INSERT INTO ood.book_authors (books_id, authors_id) VALUES (29, 28);
INSERT INTO ood.book_authors (books_id, authors_id) VALUES (33, 32);
INSERT INTO ood.book_authors (books_id, authors_id) VALUES (41, 40);
INSERT INTO ood.book_authors (books_id, authors_id) VALUES (52, 32);
INSERT INTO ood.book_authors (books_id, authors_id) VALUES (70, 69);

INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (1, false, '2022-12-24', 1);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (2, false, '2023-01-06', 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (3, false, '2023-01-01', 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (4, true, null, 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (30, true, null, 1);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (31, true, null, 1);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (34, true, null, 33);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (37, true, null, 33);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (38, true, null, 33);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (39, true, null, 33);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (44, true, null, 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (45, true, null, 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (46, true, null, 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (47, true, null, 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (48, true, null, 2);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (55, true, null, 1);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (56, true, null, 1);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (57, true, null, 1);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (58, true, null, 1);
INSERT INTO ood.book_copy (id, available, expected_returned_date, book_copies) VALUES (59, true, null, 1);

INSERT INTO ood.book_rental (id, actual_return_date, check_out_date, expected_return_date, late_fees, status, book, book_copy, customer_id) VALUES (64, '2023-01-08', '2022-12-17', '2022-12-31', 0, 0, null, 1, 1);
INSERT INTO ood.book_rental (id, actual_return_date, check_out_date, expected_return_date, late_fees, status, book, book_copy, customer_id) VALUES (65, null, '2022-12-03', '2022-12-24', 0, 1, null, 1, 1);
INSERT INTO ood.book_rental (id, actual_return_date, check_out_date, expected_return_date, late_fees, status, book, book_copy, customer_id) VALUES (66, null, '2022-12-31', '2023-01-06', 0, 1, null, 2, 1);
INSERT INTO ood.book_rental (id, actual_return_date, check_out_date, expected_return_date, late_fees, status, book, book_copy, customer_id) VALUES (67, null, '2022-12-25', '2022-12-29', 0, 1, null, 3, 1);
INSERT INTO ood.book_rental (id, actual_return_date, check_out_date, expected_return_date, late_fees, status, book, book_copy, customer_id) VALUES (68, null, '2022-12-24', '2023-01-01', 0, 1, null, 3, 1);


INSERT INTO ood.book_waiting_list (book_id, waiting_list_id) VALUES (1, 1);
INSERT INTO ood.book_waiting_list (book_id, waiting_list_id) VALUES (1, 4);
INSERT INTO ood.book_waiting_list (book_id, waiting_list_id) VALUES (1, 74);
INSERT INTO ood.book_waiting_list (book_id, waiting_list_id) VALUES (1, 75);
INSERT INTO ood.book_waiting_list (book_id, waiting_list_id) VALUES (1, 76);

INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (1, 'China', '2022-12-22', 'TenrinTsutaki@qq.com', 10, 'Tenrin123', -696);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (2, 'BeijingDaxing', '2022-12-09', '2285814046@qq.com', 999, 'Tenrin', -2160);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (3, 'BeijingDaxing', '2022-12-09', '11111@unknow.com', 999, 'Tester', 0);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (4, '222', '2022-12-10', 'yiming.lou@ucdconnect.ie', 999, 'YanMin', 0);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (49, 'BeJ', '2022-12-17', '222@qq.com', 50, 'Louis', 0);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (60, 'Beijing', '2023-01-01', '111@qq.com', 50, 'V', 0);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (74, 'Hebei', '2022-12-11', 'jiahe.zhang@ucdconnect.ie', 50, 'ZhangJiaHe', 0);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (75, 'Henan', '2022-12-12', 'feihe.huang@ucdconnect.ie', 50, 'HuangFeihe', 0);
INSERT INTO ood.customer (id, address, date_of_birth, email, max_rentals, name, late_fees) VALUES (76, 'Beijing', '2022-12-12', 'mingwei.yan@ucdconnect.ie', 50, 'SonV', 0);

INSERT INTO ood.hibernate_sequence (next_val) VALUES (77);
