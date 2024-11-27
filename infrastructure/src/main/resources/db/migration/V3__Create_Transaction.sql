CREATE TABLE transaction_paper
(
    id    CHAR(32) NOT NULL PRIMARY KEY,
    wallet_id CHAR(32) NOT NULL,
    paper_id CHAR(32) NOT NULL,
    amount DOUBLE NOT NULL,
    type INTEGER NOT NULL,
    status INTEGER NOT NULL
);