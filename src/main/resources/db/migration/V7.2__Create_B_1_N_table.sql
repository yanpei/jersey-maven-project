create TABLE B_1_N(
    ID int not null AUTO_INCREMENT,
    NAME VARCHAR(200),
    A_ID int,
    PRIMARY KEY (ID)
);


create TABLE B_1_N_Cascade(
    ID int not null,
    NAME VARCHAR(200),
    A_ID int,
    PRIMARY KEY (ID),
    FOREIGN KEY (A_ID) REFERENCES A_1_N_Cascade(ID) ON UPDATE CASCADE ON DELETE CASCADE
);