use test_database;

INSERT INTO Movie(id, name, description, score, youtubeUrl) VALUES (1, "El efecto mariposa", "Descripcion efecto mariposa", 5, "https://www.youtube.com/watch?v=9sAU7RkGHAk");
INSERT INTO Movie(id, name, description, score, youtubeUrl) VALUES (2, "Narnia 1", "Descripcion Narnia 1", 3, "https://www.youtube.com/watch?v=6asad454UASDA");
INSERT INTO Movie(id, name, description, score, youtubeUrl) VALUES (3, "Harry Potter 1", "Descripcion Harry Potter 1", 4, "https://www.youtube.com/watch?v=4545ASSDXAwqeq");


INSERT INTO MovieXTag(id, movie_id, tag) VALUES (1, 1, "Drama");
INSERT INTO MovieXTag(id, movie_id, tag) VALUES (2, 1, "Accion");

INSERT INTO MovieXTag(id, movie_id, tag) VALUES (3, 2, "Aventura");
INSERT INTO MovieXTag(id, movie_id, tag) VALUES (4, 2, "Drama");

INSERT INTO MovieXTag(id, movie_id, tag) VALUES (5, 3, "Aventura");
INSERT INTO MovieXTag(id, movie_id, tag) VALUES (6, 3, "Comedia");
INSERT INTO MovieXTag(id, movie_id, tag) VALUES (7, 3, "Magia");
