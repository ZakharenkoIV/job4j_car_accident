CREATE TABLE rule
(
    id   serial primary key,
    name varchar(2000) not null
);

CREATE TABLE accident_types
(
    id   serial primary key,
    name varchar(2000) not null
);

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
