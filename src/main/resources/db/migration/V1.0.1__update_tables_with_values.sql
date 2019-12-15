-- ---
-- Test Data
-- ---

INSERT INTO `Role` (`id`,`name`) VALUES (1,'USER');

INSERT INTO `User` (`id`,`name`,`email`,`password`,`active`,`version`,`id_role`) VALUES (1, 'user', 'user@email.com','$2a$10$qogFrX1huKFd5OcRqX3ePOSZ4d58/0fETuWR/Zqa20H5mRIdieHDu', 1, 1, 1);
INSERT INTO `User` (`id`,`name`,`email`,`password`,`active`,`version`,`id_role`) VALUES (2, 'john', 'john@doe.com','$2a$10$SFrYWBv8d7WHvzIZLjHO6OmEQVZeYZ9Cj0/4p5ca4Z3U467SS.K16', 1, 1, 1);
INSERT INTO `User` (`id`,`name`,`email`,`password`,`active`,`version`,`id_role`) VALUES (3, 'joanne', 'joanne@doe.com','$2a$10$Z5aBBlTQ.uVg6kt.5STDFuNDL7oZWNj7nb6L3XqV1/EuwOfY4bQcC', 1, 1, 1);

INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (1,'Titanic','James Cameron',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (2,'E.T. the Extra-Terrestrial','Steven Spielberg',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (3,'The Wizard of Oz','Victor Fleming',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (4,'Star Wars: Episode IV - A New Hope','George Lucas',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (5,'The Lord of the Rings: The Return of the King','Peter Jackson',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (6,'Snow White and the Seven Dwarfs','David Hand',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (7,'Terminator 2: Judgment Day','James Cameron',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (8,'The Lion King','Rob Minkoff',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (9,'The Godfather','Francis Ford Coppola',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (10,'The Jesus Film','Peter Sykes',0);
INSERT INTO `Movie` (`id`,`name`,`director`,`version`) VALUES (11,'Jurassic Park','Steven Spielberg',0);

INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (1,0,1);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (2,0,1);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (3,0,1);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (4,0,2);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (5,0,2);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (6,0,2);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (7,0,2);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (8,0,3);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (9,0,3);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (10,0,3);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (11,0,4);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (12,0,4);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (13,0,5);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (14,0,5);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (15,0,5);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (16,0,5);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (17,0,6);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (18,0,6);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (19,0,7);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (20,0,7);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (21,0,7);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (22,0,7);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (23,0,8);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (24,0,8);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (25,0,8);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (26,0,8);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (27,0,9);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (28,0,9);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (29,0,9);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (30,0,9);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (31,0,9);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (32,0,10);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (33,0,11);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (34,0,11);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (35,0,11);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (36,0,11);
INSERT INTO `Unit` (`id`,`version`,`id_movie`) VALUES (37,0,11);
