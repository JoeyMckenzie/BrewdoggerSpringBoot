-- Seed beers
INSERT INTO beers (id, abv, beer_style, ibu, beer_name) VALUES (1, 7.2, 'IPA', 100, 'Hexagenia');
INSERT INTO beers (id, abv, beer_style, ibu, beer_name) VALUES (2, 6.0, 'PALE_ALE', 68, 'Pale Ale');
INSERT INTO beers (id, abv, beer_style, ibu, beer_name) VALUES (3, 3.8, 'LAGER', 12, 'Miller Lite');

-- Seed breweries
INSERT INTO breweries (id, brewery_name, city, state) VALUES (1, 'Fall River Brewery', 'Redding', 'CA');
INSERT INTO breweries (id, brewery_name, city, state) VALUES (2, 'Bike Dog Brewery', 'Sacramento', 'CA');
INSERT INTO breweries (id, brewery_name, city, state) VALUES (3, 'Sierra Nevada Brewery', 'Chico', 'CA');

-- Seed users
INSERT INTO brewdogger_users (id, first_name, last_name, username, password) VALUES (1, 'Joey', 'Mckenzie', 'joey.mckenzie', '$2a$04$votxZZM5Z6MT3ToOXQuNveXXS/BZNd3H.MkGf7JKvSq8VJIi2dJoW');
INSERT INTO brewdogger_users (id, first_name, last_name, username, password) VALUES (2, 'Sarah', 'Noland', 'sarah.noland', '$2a$04$votxZZM5Z6MT3ToOXQuNveXXS/BZNd3H.MkGf7JKvSq8VJIi2dJoW');