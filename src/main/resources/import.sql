-- Seed beers
INSERT INTO beers (id, abv, beer_style, ibu, beer_name) VALUES (1, 7.2, 'IPA', 100, 'Hexagenia');
INSERT INTO beers (id, abv, beer_style, ibu, beer_name) VALUES (2, 6.0, 'PALE_ALE', 68, 'Pale Ale');
INSERT INTO beers (id, abv, beer_style, ibu, beer_name) VALUES (3, 3.8, 'LAGER', 12, 'Miller Lite');

-- Seed
INSERT INTO breweries (id, brewery_name, city, state) VALUES (1, 'Fall River Brewery', 'Redding', 'CA');
INSERT INTO breweries (id, brewery_name, city, state) VALUES (2, 'Bike Dog Brewery', 'Sacramento', 'CA');
INSERT INTO breweries (id, brewery_name, city, state) VALUES (3, 'Sierra Nevada Brewery', 'Chico', 'CA');