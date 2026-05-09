-- BASE DE DATOS PROJECT CIVILIZATIONS --

-- 1. Reiniciar la base de datos
DROP DATABASE IF EXISTS civilization_game;
CREATE DATABASE civilization_game;
USE civilization_game;

-- 2. Tabla principal: Estado de la Civilización
-- Refleja los atributos
CREATE TABLE player_stats (
    id INT PRIMARY KEY DEFAULT 1,
    -- Recursos
    wood INT NOT NULL,
    iron INT NOT NULL,
    food INT NOT NULL,
    mana INT NOT NULL,
    -- Tecnologías
    tech_attack INT NOT NULL DEFAULT 0, -- technologyAtack 
    tech_defense INT NOT NULL DEFAULT 0, -- technologyDefense 
    -- Edificios
    farm INT NOT NULL DEFAULT 0,
    smithy INT NOT NULL DEFAULT 0,
    carpentry INT NOT NULL DEFAULT 0,
    magic_tower INT NOT NULL DEFAULT 0,
    church INT NOT NULL DEFAULT 0, -- Necesario para Sacerdotes
    -- Otros
    battles_count INT NOT NULL DEFAULT 0 -- Atributo 'int battles' 
);

-- 3. Tabla para el Ejército (Persistencia de la flota)
-- Se usa para guardar la cantidad de cada tipo de unidad 
CREATE TABLE player_army (
    unit_id INT PRIMARY KEY, -- IDs 0 a 8 según el array 
    unit_name VARCHAR(50) NOT NULL,
    quantity INT NOT NULL DEFAULT 0
);

-- 4. Tabla de Historial de Batallas
-- Guardar reportes y desarrollos 
CREATE TABLE battle_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    battle_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    result VARCHAR(20), -- 'Victory' o 'Defeat'
    -- Pérdidas ponderadas (Cálculo: Hierro + Madera/5 + Comida/10) 
    weighted_losses_player INT, 
    weighted_losses_enemy INT,
    -- Recursos recolectados de escombros
    waste_wood_collected INT,
    waste_iron_collected INT,
    -- Reportes de texto
    battle_report TEXT, -- Resumen de costes y bajas
    battle_development LONGTEXT -- El desarrollo paso a paso del String battleDevelopment
);

-- 5. Inserción de datos iniciales (Configuración del juego)
-- Estos valores son los que cargará tu programa al iniciar por primera vez
INSERT INTO player_stats (id, wood, iron, food, mana, tech_attack, tech_defense, farm, smithy, carpentry, magic_tower, church, battles_count) 
VALUES (1, 25000, 10000, 30000, 5000, 0, 0, 0, 0, 0, 0, 0, 0);

-- Unidades obligatorias en el orden del array Army
INSERT INTO player_army (unit_id, unit_name, quantity) VALUES 
(0, 'Swordsman', 0),
(1, 'Spearman', 0),
(2, 'Crossbow', 0),
(3, 'Cannon', 0),
(4, 'ArrowTower', 0),
(5, 'Catapult', 0),
(6, 'RocketLauncher', 0),
(7, 'Magician', 0),
(8, 'Priest', 0);