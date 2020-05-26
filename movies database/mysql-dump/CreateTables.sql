CREATE TABLE Movie (
    id int NOT NULL AUTO_INCREMENT,
    name TINYTEXT NOT NULL,
    description TINYTEXT NOT NULL,
    score int NOT NULL,
    youtubeUrl TINYTEXT NOT NULL,
    CONSTRAINT Movie_PK PRIMARY KEY (id)
);

CREATE TABLE MovieXTag (
    id int NOT NULL AUTO_INCREMENT,
    movie_id TINYTEXT NOT NULL,
    tag TINYTEXT NOT NULL,
    CONSTRAINT MovieXCategory_PK PRIMARY KEY (id)
);
