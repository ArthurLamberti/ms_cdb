CREATE TABLE wallets
(
    id    CHAR(32) NOT NULL PRIMARY KEY,
    paper_id CHAR(32) NOT NULL,
    customer_id CHAR(32) NOT NULL,
    amount DOUBLE NOT NULL,

    CONSTRAINT fk_paper FOREIGN KEY (paper_id) references papers(id) ON DELETE CASCADE
);