CREATE TABLE Intervention (
    id_i INT PRIMARY KEY AUTO_INCREMENT,
    lieu VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    heure_appel TIME NOT NULL,
    heure_arrivee TIME NOT NULL,
    duree INT NOT NULL,
    situation_id INT NOT NULL,
    FOREIGN KEY (situation_id) REFERENCES Situation(id)
) ENGINE=InnoDB;

-- Insertion de données d'exemple avec des lieux autour de Caen
INSERT INTO Intervention (lieu, date, heure_appel, heure_arrivée, durée, situation_id) VALUES
('Caen', '2024-03-01', '14:30:00', '14:45:00', 30, 1),
('Hérouville-Saint-Clair', '2024-03-02', '10:00:00', '10:20:00', 40, 2),
('Ifs', '2024-03-03', '18:15:00', '18:40:00', 35, 3),
('Mondeville', '2024-03-04', '22:00:00', '22:20:00', 25, 1),
('Bretteville-sur-Odon', '2024-03-05', '08:45:00', '09:10:00', 30, 2),
('Fleury-sur-Orne', '2024-03-06', '11:20:00', '11:35:00', 20, 3),
('Épron', '2024-03-07', '16:10:00', '16:30:00', 25, 1),
('Carpiquet', '2024-03-08', '20:50:00', '21:15:00', 40, 2),
('Giberville', '2024-03-09', '13:30:00', '13:50:00', 30, 3),
('Colombelles', '2024-03-10', '06:00:00', '06:20:00', 20, 1);
