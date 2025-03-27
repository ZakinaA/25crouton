CREATE TABLE Intervention_Pompier (
    intervention_id INT,
    pompier_id INT,
    PRIMARY KEY (intervention_id, pompier_id),
    FOREIGN KEY (intervention_id) REFERENCES Intervention(id_i), -- Utilisation de id_i
    FOREIGN KEY (pompier_id) REFERENCES Pompier(id) -- Utilisation de id
)ENGINE = InnoDB;

INSERT INTO Intervention_Pompier (intervention_id, pompier_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 4), (2, 5),
(3, 6), (3, 7), (3, 8),
(4, 9), (4, 10),
(5, 11), (5, 12), (5, 13),
(6, 14), (6, 15),
(7, 1), (7, 4), (7, 7), (7, 10),
(8, 2), (8, 5), (8, 8), (8, 11),
(9, 3), (9, 6), (9, 9), (9, 12), (9, 15),
(10, 13), (10, 14);