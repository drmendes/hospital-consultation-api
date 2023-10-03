-- Create Doctor table
CREATE TABLE IF NOT EXISTS doctors
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    speciality_id INTEGER,
    FOREIGN KEY (speciality_id) REFERENCES specialities (id)
);

-- Create Specialities Table
CREATE TABLE IF NOT EXISTS specialities
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create Patients table
CREATE TABLE IF NOT EXISTS patients
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age  INT          NOT NULL
);

-- Create Pathologies table
CREATE TABLE IF NOT EXISTS pathologies
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create Symptoms table
CREATE TABLE IF NOT EXISTS symptoms
(
    id           SERIAL PRIMARY KEY,
    description  TEXT NOT NULL,
    pathology_id INTEGER,
    FOREIGN KEY (pathology_id) REFERENCES pathologies (id)
);

-- Create Consults table
CREATE TABLE IF NOT EXISTS consults
(
    id            SERIAL PRIMARY KEY,
    doctor_id     INTEGER,
    speciality_id INTEGER,
    patient_id    INTEGER,
    pathology_id  INTEGER,
    FOREIGN KEY (doctor_id) REFERENCES doctors (id),
    FOREIGN KEY (speciality_id) REFERENCES specialities (id),
    FOREIGN KEY (patient_id) REFERENCES patients (id),
    FOREIGN KEY (pathology_id) REFERENCES pathologies (id)
);

-- Insert initial data
-- Insert Specialities
INSERT INTO specialities (name)
VALUES ('Dermatology'),
       ('Ophthalmology'),
       ('Radiology'),
       ('Family Medicine'),
       ('Pediatrics');


-- Insert Doctors
INSERT INTO doctors (name, speciality_id)
VALUES ('António', 1),
       ('Maria', 2),
       ('Carlos', 3),
       ('Gabriela', 4),
       ('Paulo', 5);

-- Insert Patients
INSERT INTO patients (name, age)
VALUES ('Manuel', 53),
       ('Joana', 32),
       ('Ana', 25),
       ('Diogo', 33),
       ('Catarina', 33),
       ('Miguel', 40);

-- Insert Pathologies
INSERT INTO pathologies (name)
VALUES ('Pathology 1'),
       ('Pathology 2'),
       ('Pathology 3'),
       ('Pathology 4'),
       ('Pathology 5'),
       ('Pathology 6'),
       ('Pathology 7');

-- Insert Symptoms
INSERT INTO symptoms (description, pathology_id)
VALUES ('Symptom 1 description', 1),
       ('Symptom 2 description', 1),
       ('Symptom 3 description', 2),
       ('Symptom 4 description', 2),
       ('Symptom 5 description', 2),
       ('Symptom 6 description', 3),
       ('Symptom 7 description', 3),
       ('Symptom 8 description', 4),
       ('Symptom 9 description', 5),
       ('Symptom 10 description', 5),
       ('Symptom 11 description', 5),
       ('Symptom 12 description', 6),
       ('Symptom 13 description', 6),
       ('Symptom 14 description', 7),
       ('Symptom 15 description', 7);

-- Insert Consults
INSERT INTO consults (speciality_id, doctor_id, patient_id, pathology_id)
VALUES (1, 1, 1, 1),
       (1, 1, 1, 2),
       (2, 2, 1, 3),
       (2, 2, 2, 3),
       (3, 3, 3, 4),
       (4, 4, 4, 5),
       (5, 5, 5, 6),
       (2, 2, 6, 7);