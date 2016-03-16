create TABLE B_1_1(
    ID int not null AUTO_INCREMENT,
    NAME VARCHAR(200),
    PRIMARY KEY (ID)
);

create TABLE B_1_1_Cascade(
    ID int not null,
    NAME VARCHAR(200),
    PRIMARY KEY (ID)
);