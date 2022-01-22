CREATE TABLE rule
(
    id   serial primary key,
    name varchar(2000) not null
);

insert into rule (name)
values ('Статья. 1');
insert into rule (name)
values ('Статья. 2');
insert into rule (name)
values ('Статья. 3');

CREATE TABLE accident_types
(
    id   serial primary key,
    name varchar(2000) not null
);

insert into accident_types (name)
values ('Две машины');
insert into accident_types (name)
values ('Машина и человек');
insert into accident_types (name)
values ('Машина и велосипед');

CREATE TABLE accident
(
    id      serial primary key,
    name    varchar(2000) not null,
    text    text          not null,
    address varchar(2000) not null,
    type_id integer       not null references rule (id)
);

CREATE TABLE rule_to_accident
(
    id          serial primary key,
    rule_id     integer not null references rule ("id"),
    accident_id integer not null references accident ("id")
);

CREATE UNIQUE INDEX "UI_rule_to_accident_accident_id_rule_id"
    ON "rule_to_accident"
        USING btree
        ("rule_id", "accident_id");

