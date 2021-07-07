--users
INSERT INTO user (user, password)
VALUES (
    'juan',
    'alguno'
);

-- media
INSERT INTO media (imdb_id, title, primary_title, original_title, year, runtime_minutes, genre, media_type)
VALUES (
    'tt0076759',
    'Star Wars: Episode IV - A New Hope',
    'Star Wars: Episode IV - A New Hope',
    'Star Wars: Episode IV - A New Hope',
    1977,
    121,
    'Action',
    'Movie'
),
(
    'tt0080684',
    'Star Wars: Episode V - The Empire Strikes Back',
    'Star Wars: Episode V - The Empire Strikes Back',
    'Star Wars: Episode V - The Empire Strikes Back',
    1980,
    124,
    'Action',
    'Movie'
),
(
    'tt0086190',
    'Star Wars: Episode VI - Return of the Jedi',
    'Star Wars: Episode IV - Return of the Jedi',
    'Star Wars: Episode IV - Return of the Jedi',
    1983,
    131,
    'Action',
    'Movie'
),
(
    'tt0068646',
    'The Godfather',
    'The Godfather',
    'The Godfather',
    1972,
    175,
    'Crime',
    'Movie'
),
(
    'tt0093870',
    'Robocop',
    'Robocop',
    'Robocop',
    1987,
    102,
    'Action',
    'Movie'
),
(
    'tt0102926',
    'The Silence of the Lambs',
    'The Silence of the Lambs',
    'The Silence of the Lambs',
    1991,
    118,
    'Crime',
    'Movie'
);

INSERT INTO media (imdb_id, title, primary_title, original_title, year, genre, media_type)
VALUES (
    'tt0944947',
    'Game of Thrones',
    'Game of Thrones',
    'Game of Thrones',
    2011,
    'Action',
    'Series'
);

INSERT INTO media (imdb_id, title, primary_title, original_title, year, runtime_minutes, genre, media_type)
VALUES (
    'tt1480055',
    'Winter Is Coming',
    'Winter Is Coming',
    'Winter Is Coming',
    2011,
    62,
    'Action',
    'Episode'
);