INSERT INTO SPECIALTIES (NAME) VALUES ('OBGYN');
INSERT INTO SPECIALTIES (NAME) VALUES ('Opthamology');
INSERT INTO SPECIALTIES (NAME) VALUES ('Psychiatry');

INSERT INTO CITIES (NAME, STATE) VALUES ('Pasadena', 'CA');
INSERT INTO CITIES (NAME, STATE) VALUES ('Ventura', 'CA');
INSERT INTO CITIES (NAME, STATE) VALUES ('Oxnard', 'CA');

INSERT INTO DOCTORS (NAME, SPECIALTY_ID, CITY_ID) VALUES ('Dr. Monty Python', 3, 2);
INSERT INTO DOCTORS (NAME, SPECIALTY_ID, CITY_ID) VALUES ('Dr. Jane Goodall', 2, 1);
INSERT INTO DOCTORS (NAME, SPECIALTY_ID, CITY_ID) VALUES ('Dr. Grace Hop', 1, 3);

INSERT INTO users (name, username) values ('Shannon', 'helloworld');
INSERT INTO USERS (name, username) VALUES ('Sally', 'mesohealthy');
INSERT INTO USERS (name, username) VALUES ('Jackie', 'reviewsRmyLyf');

INSERT INTO reviews (comment_body, doctor_id, rating, author_id, is_active) values ('This is my fav doc evaaa', 1, 5, 1, true);
INSERT INTO reviews (comment_body, doctor_id, rating, author_id, is_active) values ('really close to my home so I guess thats good', 2, 5, 3, true);
INSERT INTO reviews (comment_body, doctor_id, rating, author_id, is_active) values ('Going to the doctor is such a pleasure with this one!', 3, 4, 2, true);