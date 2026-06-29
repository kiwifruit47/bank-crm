CREATE SEQUENCE IF NOT EXISTS clients_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS accounts_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS loans_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS loan_types_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS installments_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE clients (
    id         BIGINT DEFAULT nextval('clients_seq'),
    first_name VARCHAR(255) CONSTRAINT clients_nn01 NOT NULL,
    last_name  VARCHAR(255) CONSTRAINT clients_nn02 NOT NULL,

    CONSTRAINT clients_pk
        PRIMARY KEY (id)
);

CREATE TABLE natural_persons (
    id  BIGINT,
    pin VARCHAR(255) CONSTRAINT natural_persons_nn03 NOT NULL,

    CONSTRAINT natural_persons_pk
         PRIMARY KEY (id),
    CONSTRAINT natural_persons_fk01
         FOREIGN KEY (id)
         REFERENCES clients(id),
    CONSTRAINT natural_persons_unq01
         UNIQUE (pin)
);

CREATE TABLE legal_entities (
    id                BIGINT,
    legal_entity_name VARCHAR(255) CONSTRAINT legal_entities_nn03 NOT NULL,
    uic               VARCHAR(255) CONSTRAINT legal_entities_nn04 NOT NULL,

    CONSTRAINT legal_entities_pk
        PRIMARY KEY (id),
    CONSTRAINT legal_entities_fk01
        FOREIGN KEY (id)
        REFERENCES clients(id),
    CONSTRAINT legal_entities_unq01
        UNIQUE (legal_entity_name),
    CONSTRAINT legal_entities_unq02
        UNIQUE (uic)
);

CREATE TABLE accounts (
    id        BIGINT DEFAULT nextval('accounts_seq'),
    balance   NUMERIC,
    is_active BOOLEAN DEFAULT TRUE,
    iban      VARCHAR(255) CONSTRAINT accounts_nn01 NOT NULL,
    client_id BIGINT,

    CONSTRAINT accounts_pk
        PRIMARY KEY (id),
    CONSTRAINT accounts_unq01
        UNIQUE (iban),
    CONSTRAINT accounts_fk01
        FOREIGN KEY (client_id)
        REFERENCES clients(id)
        ON DELETE RESTRICT
);

CREATE TABLE loan_types (
    id                    BIGINT DEFAULT nextval('loan_types_seq'),
    name                  VARCHAR(255) CONSTRAINT loan_types_nn01 NOT NULL,
    interest              NUMERIC,
    max_amount            NUMERIC,
    max_installment_count INT,

    CONSTRAINT loan_types_pk
        PRIMARY KEY (id),
    CONSTRAINT loan_types_unq01
        UNIQUE (name)
);

CREATE TABLE loans (
    id                 BIGINT DEFAULT nextval('loans_seq'),
    amount             NUMERIC,
    conclusion_date    DATE,
    installments_count INT,
    loan_type_id       BIGINT,
    account_id         BIGINT,

    CONSTRAINT loans_pk
        PRIMARY KEY (id),
    CONSTRAINT loans_fk01
        FOREIGN KEY (loan_type_id)
        REFERENCES loan_types(id)
        ON DELETE RESTRICT,
    CONSTRAINT loans_fk02
        FOREIGN KEY (account_id)
            REFERENCES accounts(id)
            ON DELETE RESTRICT
);

CREATE TABLE installments (
    id               BIGINT DEFAULT nextval('installments_seq'),
    amount           NUMERIC,
    interest_amount  NUMERIC,
    principal_amount NUMERIC,
    remaining_amount NUMERIC,
    month            VARCHAR,
    loan_id          BIGINT,

    CONSTRAINT installments_pk
        PRIMARY KEY (id),
    CONSTRAINT installments_fk01
        FOREIGN KEY (loan_id)
        REFERENCES loans(id)
        ON DELETE RESTRICT
);
