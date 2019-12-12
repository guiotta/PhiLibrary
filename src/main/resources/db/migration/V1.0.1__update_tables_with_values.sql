INSERT INTO `Role` (`id`,`name`) VALUES (1,'USER');

INSERT INTO `User` (`id`,`name`,`email`,`password`,`active`,`version`,`id_role`) VALUES (1, 'user', 'user@email.com','$2a$10$qogFrX1huKFd5OcRqX3ePOSZ4d58/0fETuWR/Zqa20H5mRIdieHDu', 1, 1, 1);