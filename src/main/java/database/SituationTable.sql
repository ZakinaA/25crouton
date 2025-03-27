CREATE TABLE Situation (
    id_s INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255) NOT NULL
) ENGINE=InnoDB;
INSERT INTO Situation (type) VALUES
('Urgence'),
('Non-urgence'),
('SecoursPersonne');