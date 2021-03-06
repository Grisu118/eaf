INSERT INTO PRICECATEGORIES (PRICECATEGORY_ID, PRICECATEGORY_TYPE) VALUES(1, 'Regular');
INSERT INTO PRICECATEGORIES (PRICECATEGORY_ID, PRICECATEGORY_TYPE) VALUES(2, 'Children');
INSERT INTO PRICECATEGORIES (PRICECATEGORY_ID, PRICECATEGORY_TYPE) VALUES(3, 'NewRelease');

INSERT INTO MOVIES (MOVIE_ID, MOVIE_TITLE, MOVIE_RENTED, MOVIE_RELEASEDATE, PRICECATEGORY_FK) VALUES(1, 'Interstellar',         TRUE, '2015-03-31', 1);
INSERT INTO MOVIES (MOVIE_ID, MOVIE_TITLE, MOVIE_RENTED, MOVIE_RELEASEDATE, PRICECATEGORY_FK) VALUES(2, 'The Imitation Game',   TRUE, '2015-03-31', 1);
INSERT INTO MOVIES (MOVIE_ID, MOVIE_TITLE, MOVIE_RENTED, MOVIE_RELEASEDATE, PRICECATEGORY_FK) VALUES(3, 'Paddington',           TRUE, '2015-04-28', 2);
INSERT INTO MOVIES (MOVIE_ID, MOVIE_TITLE, MOVIE_RENTED, MOVIE_RELEASEDATE, PRICECATEGORY_FK) VALUES(4, 'Fifty Shades of Grey', FALSE, '2015-05-08', 3);
INSERT INTO MOVIES (MOVIE_ID, MOVIE_TITLE, MOVIE_RENTED, MOVIE_RELEASEDATE, PRICECATEGORY_FK) VALUES(5, 'Ex Machina',           FALSE, '2015-07-14', 3);

INSERT INTO USERS (USER_ID, USER_FIRSTNAME, USER_NAME, USER_EMAIL) VALUES(1, 'Marc', 'Keller', 'marc.keller@gmail.com');
INSERT INTO USERS (USER_ID, USER_FIRSTNAME, USER_NAME, USER_EMAIL) VALUES(2, 'Werner', 'Knecht', 'werner.knecht@gmail.com');
INSERT INTO USERS (USER_ID, USER_FIRSTNAME, USER_NAME, USER_EMAIL) VALUES(3, 'Barbara', 'Meyer', 'barbara.meyer@gmail.com');
INSERT INTO USERS (USER_ID, USER_FIRSTNAME, USER_NAME, USER_EMAIL) VALUES(4, 'Adolf', 'Kummer', 'adolf.kummer@gmail.com');

INSERT INTO RENTALS (RENTAL_ID, RENTAL_RENTALDATE, RENTAL_RENTALDAYS, USER_ID, MOVIE_ID) VALUES(1, '2015-09-23', 7, 1, 1);
INSERT INTO RENTALS (RENTAL_ID, RENTAL_RENTALDATE, RENTAL_RENTALDAYS, USER_ID, MOVIE_ID) VALUES(2, '2015-09-25', 6, 1, 2);
INSERT INTO RENTALS (RENTAL_ID, RENTAL_RENTALDATE, RENTAL_RENTALDAYS, USER_ID, MOVIE_ID) VALUES(3, '2015-09-27', 4, 3, 3);
