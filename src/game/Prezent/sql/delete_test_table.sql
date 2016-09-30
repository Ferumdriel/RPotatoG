create or replace function delete_test_table(
    p_id bigint
) returns boolean  as 
$$
DECLARE
BEGIN
    delete from test_table
    where id = p_id;
    
    return found;
        
END
$$
LANGUAGE plpgsql;