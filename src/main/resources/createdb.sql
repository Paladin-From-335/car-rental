create table car_table
(
    car_id          bigserial             not null
        constraint car_table_pk
            primary key,
    car_reg_number  text                  not null,
    tmp_unavailable boolean default false not null,
    car_brand       text                  not null
);

alter table car_table
    owner to postgres;

create unique index car_table_car_id_uindex
    on car_table (car_id);

create unique index car_table_car_reg_number_uindex
    on car_table (car_reg_number);

create table rented
(
    rental_id      bigserial not null
        constraint rented_pk
            primary key,
    driver_license text      not null,
    car_reg_number text      not null
);

alter table rented
    owner to postgres;

create unique index rented_rental_id_uindex
    on rented (rental_id);

create unique index rented_user_id_uindex
    on rented (driver_license);

create unique index rented_car_id_uindex
    on rented (car_reg_number);

create table user_table
(
    user_id        bigserial not null
        constraint user_table_pk
            primary key,
    name           text      not null,
    driver_license text      not null
);

alter table user_table
    owner to postgres;

create unique index user_table_user_id_uindex
    on user_table (user_id);