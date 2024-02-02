--DATABASE CMR

DROP TABLE orders;
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        type_presta VARCHAR(255),
                        designation VARCHAR(255),
                        client_id INTEGER,
                        nb_days INTEGER,
                        unit_price NUMERIC,
                        total_exclude_taxe NUMERIC GENERATED ALWAYS AS (unit_price*nb_days) STORED,
--                        total_with_taxe NUMERIC GENERATED ALWAYS AS ((unit_price*nb_days)+((unit_price*nb_days)*20/100)) STORED,
                        total_with_taxe NUMERIC GENERATED ALWAYS AS (unit_price * nb_days * 1.20) STORED,
                        state INTEGER CHECK (state IN (0, 1, 2)) -- 0: CANCELED, 1: OPTION, 2: CONFIRMED
) ;


CREATE TABLE clients (
                         id SERIAL PRIMARY KEY,
                         company_name VARCHAR(255),
                         first_name VARCHAR(255),
                         last_name VARCHAR(255),
                         email VARCHAR(255),
                         phone VARCHAR(20),
                         address VARCHAR(255),
                         zip_code VARCHAR(10),
                         city VARCHAR(255),
                         country VARCHAR(255),
                         state BOOLEAN -- 0: INACTIVE, 1: ACTIVE
);



--TTC = HT + (HT x TVA / 100)
INSERT INTO orders (type_presta, designation, client_id, nb_days,unit_price, state) VALUES
    ('Formation','Angular init',2,3,1200,0),
    ('Formation','React avancé',2,3,1000,2),
    ('Coaching','React Techlead',1,20,900,2),
    ('Coaching','Nest.js Techlead',1,50,800,1),
    ('Coaching','React Techlead',3,20,900,1),
    ('Coaching','Jakarta EE',3,100,1500,1),
    ('Coaching','Angular TechLead',4,20,900,1),
    ('Formation','Java For Dummies',2,3,800,2),
    ('Formation','SQL For Dummies',4,3,800,2),
    ('Formation','Angular For Dummies',4,3,800,2)
returning *;


INSERT INTO clients (company_name, first_name, last_name, email, phone, address, zip_code, city, country, state) 
VALUES
('Sopra', 'Fabrice', 'Martin', 'martin@mail.com', '0656858433', 'abc',  'xyz', 'Nantes', 'France', FALSE),
('M2I Formation', 'Julien', 'Lamard', 'lamard@mail.com', '0611223344', 'abc',  'xyz', 'Paris', 'France', TRUE)
returning *;

INSERT INTO clients (company_name, first_name, last_name, email, phone, address, zip_code, city, country, state) 
VALUES
('ATOS', 'Alexandre', 'Dupont', 'alexandre.dupont@atos.net', '0102030405', '123 Boulevard de l’Innovation', '75001', 'Paris', 'France', TRUE),
('SOPRA STERIA', 'Isabelle', 'Durand', 'isabelle.durand@soprasteria.com', '0203040506', '456 Rue du Numérique', '69002', 'Lyon', 'France', TRUE);



SELECT * FROM clients


-- Q1*=> Ecrire une requête pour créer ces 2 tables en prenant en compte la jointure
-- Q2*=> Remplissez la base de données au travers des insertions
-- Q3*=> Afficher toutes les formations sollicités par le client M2i formation M2u information
SELECT * FROM orders 
JOIN clients ON orders.client_id = clients.id 
WHERE clients.company_name = 'M2I Formation' AND type_presta LIKE '%Formation%';

-- Q4 *=> Afficher les noms et contacts de tous les contacts des clients qui ont sollicité un coaching
SELECT clients.first_name, clients.last_name, clients.email, clients.phone 
FROM orders 
JOIN clients ON orders.client_id = clients.id 
WHERE orders.type_presta LIKE '%Coaching%';

-- Q5*=> Afficher les noms et contacts de tous les contacts des clients qui ont sollicité un coaching pour les accompagnements React.js
SELECT clients.first_name, clients.last_name, clients.email, clients.phone 
FROM orders 
JOIN clients ON orders.client_id = clients.id 
WHERE orders.designation LIKE '%React%' AND orders.type_presta LIKE '%Coaching%';

-- Q6 *=> Pour chacune des demandes de formation, afficher le prix UHT et prix TTC en se
--basant sur le unité Price(TJM) et le nombre de jours de prestation tout en sachant que la TVA est de 20%.
SELECT orders.id, orders.type_presta, orders.designation, orders.unit_price, orders.nb_days, 
       orders.total_exclude_taxe, orders.total_with_taxe 
FROM orders 
WHERE orders.type_presta LIKE '%Formation%';

-- Q7 *=> Lister toutes les prestations qui sont confirmés et qui vont rapporter plus 20.000€
SELECT * FROM orders 
WHERE state = 2 AND total_with_taxe > 20000;




