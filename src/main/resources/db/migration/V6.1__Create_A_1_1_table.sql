create TABLE A_1_1(
    ID int not null AUTO_INCREMENT,
    NAME VARCHAR(200),
    B_ID int,
    PRIMARY KEY (ID)
);


create TABLE A_1_1_Cascade(
    ID int not null,
    NAME VARCHAR(200),
    B_ID int,
    PRIMARY KEY (ID),
    FOREIGN KEY (B_ID) REFERENCES B_1_1_Cascade(ID) ON UPDATE CASCADE ON DELETE CASCADE
);