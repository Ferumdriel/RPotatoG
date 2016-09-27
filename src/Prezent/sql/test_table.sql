create table test_table(
    id bigserial,
    start_time timestamp default localtimestamp(0),
    start_user bigint,

    test_column text,
    

    up_time timestamp,
    up_user bigint,
    description text
);
