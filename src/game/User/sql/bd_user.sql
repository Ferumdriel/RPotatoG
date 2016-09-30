/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  BotNaEasyEnv
 * Created: 2016-09-29
 */

create table bd_user(
    id bigserial,
    start_time timestamp default localtimestamp(0),
    start_user bigint,

    login text not null,
    password text not null,
    active boolean not null default true,

    up_time timestamp,
    up_user bigint,
    description text
);