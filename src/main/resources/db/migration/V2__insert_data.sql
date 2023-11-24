-- src/main/resources/db/migration/V2__insert_data.sql

INSERT INTO genres (name) VALUES ('Action'), ('Drama'), ('Comedy');

INSERT INTO users (username, password, email, role)
VALUES
    ('john_doe', 'hashed_password', 'john@example.com', 'USER'),
    ('jane_smith', 'hashed_password', 'jane@example.com', 'USER')
;

INSERT INTO actors (name, birthdate, nationality, biography)
VALUES
    ('Tom Hanks', '1956-07-09', 'American', 'Thomas Jeffrey Hanks is an American actor and filmmaker. Known for both his comedic and dramatic roles, Hanks is one of the most popular and recognizable film stars worldwide.'),
    ('Cate Blanchett', '1969-05-14', 'Australian', 'Catherine Elise Blanchett AC is an Australian actress and producer. Known for her roles in blockbusters and independent films, she has received numerous accolades, including two Academy Awards, three Golden Globe Awards, and three BAFTA Awards.'),
    ('Keanu Reeves', '1964-09-02', 'Canadian', 'Keanu Charles Reeves is a Canadian actor and producer. Born in Beirut and raised in Toronto, Reeves began acting in theatre productions and in television films before making his mainstream film debut in Youngblood.')
;

INSERT INTO movies (title, release_date, description, duration, director, average_rating, poster_url, genre_id, actor_id, user_id)
VALUES
    ('Forrest Gump', '1994-07-06', 'The story depicts several decades in the life of Forrest Gump, a slow-witted but kind and athletically-talented man from Alabama.', 142, 'Robert Zemeckis', 8.8, 'forrest_gump_poster.jpg', 2, 1, 1),
    ('The Lord of the Rings: The Return of the King', '2003-12-01', 'The final installment of the epic trilogy that follows the War of the Ring. The former Fellowship divide and prepare for the final battle to destroy the One Ring.', 201, 'Peter Jackson', 8.9, 'return_of_the_king_poster.jpg', 1, 2, 2),
    ('The Matrix', '1999-03-31', 'A computer hacker learns about the true nature of his reality and discovers his role in the war against its controllers.', 136, 'Lana and Lilly Wachowski', 8.7, 'the_matrix_poster.jpg', 1, 3, 1)
;

INSERT INTO reviews (movie_id, user_id, rating, comment)
VALUES
    (1, 1, 9.0, 'An inspiring and heartwarming film.'),
    (2, 1, 9.5, 'A masterpiece with stunning visuals and an epic story.'),
    (3, 2, 8.5, 'Revolutionary and mind-bending.')
;


