create table map_tileset(
    id bigserial,
    start_time timestamp default localtimestamp(0),
    start_user bigint,
    
    resource_path text not null,
    side_size int not null,
    

    up_time timestamp,
    up_user bigint,
    description text
);
