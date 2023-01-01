-- Companies DATA
INSERT INTO Companies VALUES (1, 3000,  'Tesla');
INSERT INTO Companies VALUES (2, 2000, 'Apple');

-- User DATA
INSERT INTO T_User VALUES (1, 'John', 'lastName1');
INSERT INTO T_User VALUES (2, 'Jessica', 'lastName2');

-- DEPOSIT Data
INSERT INTO DEPOSIT VALUES (NEXTVAL('DEPOSIT_SEQ'), 100.0, '2022-12-31 23.59.59', 'GIFT', '2022-12-31 23.59.59',1,1);

INSERT INTO Companie_User VALUES (1, 1);
