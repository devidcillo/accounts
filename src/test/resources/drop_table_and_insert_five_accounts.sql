begin;
    drop table account;
commit;
    create table account (
        id uuid not null,
        name varchar(10)
    );
commit;
insert into account values
('f811dfc2-1761-4706-99c4-5abf0a29ac13', 'David'),
('cd8c8dc0-ae89-4ae7-b9f9-56812461faf8', 'Majo'),
('262124e8-bd3f-4fa0-8591-a9b094086b8b', 'Rob'),
('5a74d4cf-cc1f-4cbe-9534-1fa4341e1efd', 'Lilah'),
('03f833de-4d3b-4647-9d4a-0d56ce47a458', 'Barb');
commit;
end;