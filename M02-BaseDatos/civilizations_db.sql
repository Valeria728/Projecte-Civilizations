-- ============================================================
-- CIVILIZATIONS - Script Base de Datos MySQL/MariaDB
-- ============================================================

CREATE DATABASE IF NOT EXISTS civilizations_db;
USE civilizations_db;

-- Tabla principal de la civilización
CREATE TABLE IF NOT EXISTS civilization_stats (
    civilization_id     INT PRIMARY KEY AUTO_INCREMENT,
    name                VARCHAR(100) NOT NULL,
    wood_amount         INT DEFAULT 0,
    iron_amount         INT DEFAULT 0,
    food_amount         INT DEFAULT 0,
    mana_amount         INT DEFAULT 0,
    magicTower_counter  INT DEFAULT 0,
    church_counter      INT DEFAULT 0,
    farm_counter        INT DEFAULT 0,
    smithy_counter      INT DEFAULT 0,
    carpentry_counter   INT DEFAULT 0,
    technology_defense_level INT DEFAULT 0,
    technology_attack_level  INT DEFAULT 0,
    battles_counter     INT DEFAULT 0
);

-- Unidades de ataque de la civilización
CREATE TABLE IF NOT EXISTS attack_units_stats (
    unit_id             INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    type                VARCHAR(20) NOT NULL,  -- Swordsman, Spearman, Crossbow, Cannon
    armor               INT DEFAULT 0,
    base_damage         INT DEFAULT 0,
    experience          INT DEFAULT 0,
    sanctified          BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Unidades defensivas de la civilización
CREATE TABLE IF NOT EXISTS defense_units_stats (
    unit_id             INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    type                VARCHAR(30) NOT NULL,  -- ArrowTower, Catapult, RocketLauncherTower
    armor               INT DEFAULT 0,
    base_damage         INT DEFAULT 0,
    experience          INT DEFAULT 0,
    sanctified          BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Unidades especiales de la civilización
CREATE TABLE IF NOT EXISTS special_units_stats (
    unit_id             INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    type                VARCHAR(20) NOT NULL,  -- Magician, Priest
    armor               INT DEFAULT 0,
    base_damage         INT DEFAULT 0,
    experience          INT DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Estadísticas globales de cada batalla
CREATE TABLE IF NOT EXISTS battle_stats (
    battle_id           INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    num_battle          INT NOT NULL,
    wood_acquired       INT DEFAULT 0,
    iron_acquired       INT DEFAULT 0,
    winner              VARCHAR(20),           -- 'Civilization' o 'Enemy'
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Unidades de ataque de la civilización en cada batalla
CREATE TABLE IF NOT EXISTS civilization_attack_stats (
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    num_battle          INT NOT NULL,
    type                VARCHAR(20) NOT NULL,
    initial_count       INT DEFAULT 0,
    drops               INT DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Unidades defensivas de la civilización en cada batalla
CREATE TABLE IF NOT EXISTS civilization_defense_stats (
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    num_battle          INT NOT NULL,
    type                VARCHAR(30) NOT NULL,
    initial_count       INT DEFAULT 0,
    drops               INT DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Unidades especiales de la civilización en cada batalla
CREATE TABLE IF NOT EXISTS civilization_special_stats (
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    num_battle          INT NOT NULL,
    type                VARCHAR(20) NOT NULL,
    initial_count       INT DEFAULT 0,
    drops               INT DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Unidades enemigas en cada batalla
CREATE TABLE IF NOT EXISTS enemy_attack_stats (
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    num_battle          INT NOT NULL,
    type                VARCHAR(20) NOT NULL,
    initial_count       INT DEFAULT 0,
    drops               INT DEFAULT 0,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Log paso a paso de cada batalla
CREATE TABLE IF NOT EXISTS battle_log (
    log_id              INT PRIMARY KEY AUTO_INCREMENT,
    civilization_id     INT NOT NULL,
    num_battle          INT NOT NULL,
    num_line            INT NOT NULL,
    log_entry           TEXT,
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

-- Insertar civilización por defecto para pruebas
INSERT INTO civilization_stats (name, wood_amount, iron_amount, food_amount, mana_amount)
VALUES ('MiCivilizacion', 50000, 20000, 80000, 0);
