CREATE TABLE IF NOT EXISTS specialties(
specialty_id IDENTITY NOT NULL,
name VARCHAR_IGNORECASE,
PRIMARY KEY(specialty_id)
);

CREATE TABLE IF NOT EXISTS cities(
city_id IDENTITY NOT NULL,
name VARCHAR_IGNORECASE,
state VARCHAR_IGNORECASE,
PRIMARY KEY(city_id)
);

CREATE TABLE IF NOT EXISTS doctors(
  doctor_id IDENTITY NOT NULL,
  name VARCHAR_IGNORECASE,
  specialty_id INT,
  FOREIGN KEY (specialty_id) REFERENCES specialties(specialty_id),
  city_id INT,
  FOREIGN KEY (city_id) REFERENCES cities(city_id),
  address VARCHAR,
  PRIMARY KEY (doctor_id)
);

CREATE TABLE IF NOT EXISTS users(
user_id IDENTITY NOT NULL,
name VARCHAR,
username VARCHAR,
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS reviews(
review_id IDENTITY NOT NULL,
comment_body VARCHAR,
doctor_id INT,
FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
created_at TIMESTAMP,
rating TINYINT,
author_id INT,
is_active BOOLEAN,
FOREIGN KEY (author_id) REFERENCES users(user_id),
PRIMARY KEY (review_id)
);
