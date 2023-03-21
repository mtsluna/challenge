-- migrate:up
create sequence percentage_id_seq
    increment by 1
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 1
    no cycle;

create table percentage_entity
(
    percentage_id bigserial
        primary key,
    percentage         double precision
);

create sequence audit_id_seq
    increment by 1
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 1
    no cycle;

create table audit_entity
(
    audit_id bigserial
        primary key,
    method   varchar(255),
    path     varchar(255),
    response jsonb
);

-- migrate:down
drop table audit_entity;
drop table percentage_entity;
drop sequence audit_id_seq;
drop sequence percentage_id_seq;

