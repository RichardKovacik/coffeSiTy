/* nainstertovanie potrebnych entiti aby som mohol ulozit noveho usera*/
/* nainsertovanie krajov*/
INSERT INTO `district` VALUES (1,'BA'),(2,'TN');
/* nainsertovanie okresov*/
INSERT INTO `region` VALUES (1,'Malacky',1),(2,'Pezinok',1),(5,'Banovce',2),(6,'Partizanske',2);
/* nainsertovanie okresov*/
INSERT INTO `city` VALUES (1,'Jablonec',1),
                          (2,'Cataj',1),
                          (3,'Modra',2),(4,'Dolany',2),
                          (5,'Borcany',5),(6,'Rybany',5),
                          (7,'Kuchyna',1),(8,'Bosany',6),
                          (9,'Livinske Opatovce',6),(10,'Chynorany',6);

/* nainsrtovanie adries*/
INSERT INTO `address` VALUES (1,'95636','borcany 12',2),(2,'95311','rybany 12',2),(3,'12345','mojmirova 5',4);

/* nainserovanie 2 userov do testovacej databazy nick=filip email:masaryk@azet.com*/
INSERT INTO `user` VALUES (1,'1965-12-15','masaryk@azet.com','masaryk','samuel',
                           'filip','$2a$10$PDoeQUdzb/Xgi7vMUIavhePBEZmyW7gaifnq3xNvR59hvp.Rsw1cG',3);

INSERT INTO `user` VALUES (2,'1999-12-15','lukas@azet.com','findor','lukas',
                           'lukas3','$2a$10$PDoeQUdzb/Xgi7vMUIavhePBEZmyW7gaifnq3xNvR59hvp.Rsw1cG',1);