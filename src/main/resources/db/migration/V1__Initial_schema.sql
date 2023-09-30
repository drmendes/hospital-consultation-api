-- Doctors Table
CREATE TABLE doctors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         speciality_id INTEGER,
                         FOREIGN KEY (speciality_id) REFERENCES specialities(id)
);

-- Specialities Table
CREATE TABLE specialities (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL
);

-- Patients Table
CREATE TABLE patients (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          age INTEGER NOT NULL
);

-- Pathologies Table
CREATE TABLE pathologies (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL
);

-- Symptoms Table
CREATE TABLE symptoms (
                          id SERIAL PRIMARY KEY,
                          description TEXT NOT NULL,
                          pathology_id INTEGER,
                          FOREIGN KEY (pathology_id) REFERENCES pathologies(id)
);

-- Consults Table
CREATE TABLE consults (
                          id SERIAL PRIMARY KEY,
                          doctor_id INTEGER,
                          speciality_id INTEGER,
                          patient_id INTEGER,
                          pathology_id INTEGER,
                          FOREIGN KEY (doctor_id) REFERENCES doctors(id),
                          FOREIGN KEY (speciality_id) REFERENCES specialities(id),
                          FOREIGN KEY (patient_id) REFERENCES patients(id),
                          FOREIGN KEY (pathology_id) REFERENCES pathologies(id)
);
