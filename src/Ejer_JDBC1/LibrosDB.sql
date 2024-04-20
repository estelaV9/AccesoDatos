DROP DATABASE IF EXISTS Libreria;
CREATE DATABASE Libreria;
USE Libreria;

CREATE TABLE Libros (
	isbn INT PRIMARY KEY,
	titulo VARCHAR(30),
	autor VARCHAR(30),
	editorial VARCHAR(30),
    anioPublicacion DATE,
    numPag INT,
    genero VARCHAR(30)    
);


INSERT INTO Libros VALUES 
(132425, "FSAFDSA", "Antonio Arroz", "DONOS", "2010-01-01", 5, "TERROR"),
(224215, "SSSSS", "Bea Boniato", "DONOS", "2009-02-02", 6, "TERROR"),
(432435, "WWWW", "Cristina Cristal", "DONOS", "2009-03-03", 7, "COMEDIA"),
(432144, "EEE", "David Dado", "DONOS", "2009-04-04", 8, "COMEDIA"),
(541235, "EEWWEW", "Ernesto Escarola", "DSAFDSF", "2008-05-05", 9, "TERROR"),
(333365, "AAAA", "Francisco Frio", "DONOS", "2008-06-06", 9, "TERROR"),
(232237, "SSSS", "Gema Gato", "DONOS", "2008-07-07", 9, "COMEDIA"),
(111228, "ÑÑÑ", "Helena Huerto", "DONOS", "2007-08-08", 10, "COMEDIA"),
(213139, "PPPPP", "Irene Idea", "DONOS", "2007-09-09", 5, "FICCION"),
(232110, "OOOOO", "Julia Jarra", "DONOS", "2007-10-10", 6, "TERROR"),
(132141, "MMMMM", "Kika Kenya", "DONOS", "2006-11-11", 7, "TERROR"),
(125555, "NNNN", "Luna Lima", "FDSAFDSF", "2006-12-12", 888, "COMEDIA");