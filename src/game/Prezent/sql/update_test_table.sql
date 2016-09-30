create or replace function update_test_table(
    p_id bigint,
    p_test_column text,
    p_user bigint,
    p_description text
) returns boolean  as 
$$
DECLARE
BEGIN
        update test_table
        set test_column = p_test_column,
            up_time = localtimestamp(0),
            up_user = p_user,
            description = p_description
         where id = p_id;

         return found;
END
$$
LANGUAGE plpgsql;