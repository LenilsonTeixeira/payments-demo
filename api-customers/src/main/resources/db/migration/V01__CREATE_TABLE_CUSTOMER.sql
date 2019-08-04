CREATE TABLE t_customer
(
    id serial,
    txt_name varchar(300) NOT NULL,
    txt_ssn varchar(15) NOT NULL,
    birth_date date NOT NULL,
    num_credit numeric(10,2) NOT NULL,
    txt_street varchar NOT NULL,
    txt_city varchar(30) NOT NULL,
    txt_state varchar(2) NOT NULL,
    txt_zip varchar(10) NOT NULL,
    PRIMARY KEY (id)
);