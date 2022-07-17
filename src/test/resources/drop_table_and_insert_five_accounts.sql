begin;
    drop table account;
commit;
    create table account (
        id integer not null,
        name varchar(10)
    );
commit;
insert into account values
(1, 'David'),
(2, 'Majo'),
(3, 'Rob'),
(4, 'Lilah'),
(5, 'Barb');
commit;
end;