create or replace function save_test_table(
    p_test_column text,
    p_user bigint,
    p_description text
) returns bigint  as 
$$
DECLARE
    v_result bigint;
BEGIN
        insert into test_table(
            start_time,
            start_user,
            test_column,
            description
         ) values (
            localtimestamp(0),
            p_user,
            p_test_column,
            p_description
        ) returning id into v_result;

        return v_result;
END
$$
LANGUAGE plpgsql;