create TABLE AllItems(
    BARCODE VARCHAR(200) not null,
    NAME VARCHAR(200) not null,
    UNIT VARCHAR(50) not null,
    PRICE FLOAT not null,
    PRIMARY KEY (BARCODE)
);
