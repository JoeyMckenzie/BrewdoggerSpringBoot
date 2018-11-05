-- Seed breweries
INSERT INTO breweries (id, brewery_name, city, state) VALUES (1, 'Fall River Brewery', 'Redding', 'CA');
INSERT INTO breweries (id, brewery_name, city, state) VALUES (2, 'Bike Dog Brewery', 'Sacramento', 'CA');
INSERT INTO breweries (id, brewery_name, city, state) VALUES (3, 'Sierra Nevada Brewing Company', 'Chico', 'CA');

-- Seed beers
INSERT INTO beers (id, abv, beer_style, ibu, beer_name, brewery_id) VALUES (1, 7.2, 'IPA', 100, 'Hexagenia', (SELECT br.id FROM breweries AS br WHERE br.brewery_name = 'Fall River Brewery'));
INSERT INTO beers (id, abv, beer_style, ibu, beer_name, brewery_id) VALUES (2, 6.0, 'PALE_ALE', 68, 'Pale Ale', (SELECT br.id FROM breweries AS br WHERE br.brewery_name = 'Sierra Nevada Brewing Company'));
INSERT INTO beers (id, abv, beer_style, ibu, beer_name, brewery_id) VALUES (3, 6.2, 'DOUBLE_IPA', 12, 'Bike Dog Mosaic', (SELECT br.id FROM breweries AS br WHERE br.brewery_name = 'Bike Dog Brewery'));
INSERT INTO beers (id, abv, beer_style, ibu, beer_name, brewery_id) VALUES (4, 7.8, 'TRIPLE_IPA', 140, 'Widowmaker', (SELECT br.id FROM breweries AS br WHERE br.brewery_name = 'Fall River Brewery'));
INSERT INTO beers (id, abv, beer_style, ibu, beer_name, brewery_id) VALUES (5, 6.5, 'NEW_ENGLAND_IPA', 120, 'Lazy Hazy', (SELECT br.id FROM breweries AS br WHERE br.brewery_name = 'Fall River Brewery'));

-- Seed users
INSERT INTO brewdogger_users (id, first_name, last_name, username, password, user_role) VALUES (1, 'Joey', 'Mckenzie', 'joey.mckenzie', '$2a$04$votxZZM5Z6MT3ToOXQuNveXXS/BZNd3H.MkGf7JKvSq8VJIi2dJoW', 'ADMIN');
INSERT INTO brewdogger_users (id, first_name, last_name, username, password, user_role) VALUES (2, 'Sarah', 'Noland', 'sarah.noland', '$2a$04$votxZZM5Z6MT3ToOXQuNveXXS/BZNd3H.MkGf7JKvSq8VJIi2dJoW', 'USER');