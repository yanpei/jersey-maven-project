create TABLE R_N_N(
    A_ID int not null,
    B_ID int not null
);

create TABLE R_N_N_Cascade(
    A_ID int not null,
    B_ID int not null,
    FOREIGN KEY (A_ID) REFERENCES A_N_N_Cascade(ID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (B_ID) REFERENCES B_N_N_Cascade(ID) ON UPDATE CASCADE ON DELETE CASCADE

);