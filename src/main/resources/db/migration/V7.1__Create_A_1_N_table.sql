create TABLE A_1_N(
    ID int not null AUTO_INCREMENT,
    NAME VARCHAR(200),
    PRIMARY KEY (ID)
);

create TABLE A_1_N_Cascade(
    ID int not null,
    NAME VARCHAR(200),
    PRIMARY KEY (ID)
);