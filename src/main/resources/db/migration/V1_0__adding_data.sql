create table IF NOT EXISTS customers (
                           id bigserial not null,
                           account_num integer not null,
                           balance float(53) not null,
                           email varchar(255),
                           first_name varchar(255),
                           last_name varchar(255),
                           primary key (id)
);
create table IF NOT EXISTS limits (
                        id bigserial not null,
                        amount float(53) not null,
                        date timestamp(6),
                        spendings_category smallint,
                        customer_id bigint,
                        limits_key smallint,
                        primary key (id)
);

create table IF NOT EXISTS transactions (
                              id bigserial not null,
                              account_from integer not null,
                              account_to integer not null,
                              currency_shortname smallint,
                              datetime timestamp(6),
                              expense_category smallint,
                              limit_exceeded boolean not null,
                              sum float(53) not null,
                              customer_id bigint,
                              primary key (id)
);


INSERT INTO customers (id, account_num, balance, email, first_name, last_name)
VALUES (1, 111, 4000, 'asd@gmail.com', 'asd', 'asd');
INSERT INTO customers (id, account_num, balance, email, first_name, last_name)
VALUES (2, 222, 5000, 'qwe@gmail.com', 'qwe','qwe');

INSERT INTO limits (id, amount, date, spendings_category, customer_id)
VALUES (1, 1000, 2023-01-06, 0, 1);
INSERT INTO limits (id, amount, date, spendings_category, customer_id)
VALUES (2, 1000, 2023-01-06, 1, 1);

INSERT INTO transactions (id, account_from, account_to, currency_shortname, datetime, expense_category, limit_exceeded, sum, customer_id)
VALUES (1, 111,222, 1, 2023-01-06, 0, 0, 500, 1);
INSERT INTO transactions (id, account_from, account_to, currency_shortname, datetime, expense_category, limit_exceeded, sum, customer_id)
VALUES (1, 111,222, 1, 2023-01-06, 0, 1, 600, 1);