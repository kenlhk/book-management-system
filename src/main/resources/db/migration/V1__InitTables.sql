create sequence note_id_seq start 1 increment 1;
create sequence users_id_seq start 1 increment 1;
create sequence tag_id_seq start 1 increment 1;
create sequence source_id_seq start 1 increment 1;

create table tags
(
    id       bigint not null    primary key,
    tag_name varchar(255)
    constraint uk_2c6s9hekidseaj5vbgb3pgy3k unique
);

create table sources
(
    category         varchar(31)  not null,
    id               bigint       not null  primary key,
    author           varchar(255),
    title            varchar(255) not null,
    url              varchar(255),
    publication_year integer,
    publisher        varchar(255),
    channel          varchar(255)
);

create table users
(
    id       bigint      not null   primary key,
    email    varchar(64) not null
        constraint uk_6dotkott2kjsp8vw4d0m25fb7 unique,
    password varchar(64) not null,
    username varchar(64) not null
        constraint uk_r43af9ap4edm43mmtq01oddj6 unique
);

create table notes
(
    id           bigint not null    primary key,
    content      varchar(255),
    created_time timestamp,
    edited_time  timestamp,
    subject      varchar(256),
    source_id    bigint
        constraint fkka3wrcqyt11gt9qyvbpkuah7
            references sources,
    user_id      bigint
        constraint fkechaouoa6kus6k1dpix1u91c
            references users
);

create table notes_tags
(
    note_id bigint not null
        constraint fkcxrhvlv1dppm49b2snddodsvi
            references notes,
    tags_id bigint not null
        constraint fkin78up4aqfvg08o850wtluetf
            references tags,
    primary key (note_id, tags_id)
);


INSERT INTO tags (id, tag_name)
VALUES (1, 'tag1');