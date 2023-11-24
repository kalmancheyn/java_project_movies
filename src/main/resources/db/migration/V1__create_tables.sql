CREATE TABLE users (
    user_id INTEGER NOT NULL PRIMARY KEY auto_increment,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);

CREATE TABLE genres (
    genre_id INTEGER NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE actors (
    actor_id INTEGER NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    birthdate DATE,
    nationality VARCHAR(255),
    biography TEXT
);

CREATE TABLE movies (
    movie_id INTEGER NOT NULL PRIMARY KEY auto_increment,
    title VARCHAR(255) NOT NULL,
    release_date DATE,
    description TEXT,
    duration INT,
    director VARCHAR(255),
    average_rating DECIMAL,
    poster_url VARCHAR(255),
    genre_id INT,
    actor_id INT,
    user_id INT,
    CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) REFERENCES genres(genre_id),
    CONSTRAINT fk_actor_id FOREIGN KEY (actor_id) REFERENCES actors(actor_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE reviews (
    review_id INTEGER NOT NULL PRIMARY KEY auto_increment,
    movie_id INT,
    user_id INT,
    rating DECIMAL,
    comment TEXT,
    CONSTRAINT fk_movie_id FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    CONSTRAINT fk_user_id_review FOREIGN KEY (user_id) REFERENCES users(user_id)
);