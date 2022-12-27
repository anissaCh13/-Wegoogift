CREATE TABLE IF NOT EXISTS  Companies (
                           companie_id INT NOT NULL PRIMARY KEY,
                           balance float,
                           companie_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS T_User (
                                        user_id INT NOT NULL,
                               firstName VARCHAR(50) NOT NULL,
                               lastName VARCHAR(20) NOT NULL

);

CREATE TABLE IF NOT EXISTS Companie_User ( user_id INT NOT NULL,
    companie_id INT NOT NULL,
            foreign key (user_id) references T_User(user_id),
    foreign key (companie_id) references Companies(companie_id));

CREATE TABLE IF NOT EXISTS GIFT_DEPOSIT (
                                            giftDepositId INT NOT NULL,
                                            amout float NOT NULL,
                                            beginDate Date NOT NULL,
                                            endDate Date,
                                            depositType VARCHAR(50),
                                            user_id INT,
                                            companie_id INT NOT NULL,

    foreign key (user_id) references T_User(user_id),
    foreign key (companie_id) references Companies(companie_id)
    );