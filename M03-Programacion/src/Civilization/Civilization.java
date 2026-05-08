package Civilization;

import java.util.ArrayList;

public class Civilization implements Variables {
    private int wood, iron, food, mana;
    private int technologyDefense, technologyAttack;
    private int farm, smithy, carpentry, magicTower, church;
    
    // ArrayLists para el ejército (9 tipos de unidades)
    private ArrayList<MilitaryUnit>[] army;

    @SuppressWarnings("unchecked")
    public Civilization() {
        this.wood = 25000;
        this.iron = 10000;
        this.food = 30000;
        this.mana = 5000;
        this.technologyDefense = 0;
        this.technologyAttack = 0;
        
        this.army = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            army[i] = new ArrayList<MilitaryUnit>();
        }
    }

    // --- MÉTODOS DE RECLUTAMIENTO DE ATAQUE ---

    public void newSwordsman(int n) throws ResourceException {
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_SWORDSMAN && wood >= WOOD_COST_SWORDSMAN && iron >= IRON_COST_SWORDSMAN) {
                food -= FOOD_COST_SWORDSMAN; wood -= WOOD_COST_SWORDSMAN; iron -= IRON_COST_SWORDSMAN;
                int armor = ARMOR_SWORDSMAN + (technologyDefense * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100);
                int damage = BASE_DAMAGE_SWORDSMAN + (technologyAttack * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * BASE_DAMAGE_SWORDSMAN / 100);
                army[0].add(new Swordsman(armor, damage));
            } else throw new ResourceException("Recursos insuficientes para Swordsman");
        }
    }

    public void newSpearman(int n) throws ResourceException {
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_SPEARMAN && wood >= WOOD_COST_SPEARMAN && iron >= IRON_COST_SPEARMAN) {
                food -= FOOD_COST_SPEARMAN; wood -= WOOD_COST_SPEARMAN; iron -= IRON_COST_SPEARMAN;
                int h = ARMOR_SPEARMAN + (technologyDefense * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * ARMOR_SPEARMAN / 100);
                int d = BASE_DAMAGE_SPEARMAN + (technologyAttack * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * BASE_DAMAGE_SPEARMAN / 100);
                army[1].add(new Spearman(h, d));
            } else throw new ResourceException("Recursos insuficientes para Spearman");
        }
    }

    public void newCrossbow(int n) throws ResourceException {
        for (int i = 0; i < n; i++) {
            if (wood >= WOOD_COST_CROSSBOW && iron >= IRON_COST_CROSSBOW) {
                wood -= WOOD_COST_CROSSBOW; iron -= IRON_COST_CROSSBOW;
                int h = ARMOR_CROSSBOW + (technologyDefense * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * ARMOR_CROSSBOW / 100);
                int d = BASE_DAMAGE_CROSSBOW + (technologyAttack * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY * BASE_DAMAGE_CROSSBOW / 100);
                army[2].add(new Crossbow(h, d));
            } else throw new ResourceException("Recursos insuficientes para Crossbow");
        }
    }

    public void newCannon(int n) throws ResourceException {
        for (int i = 0; i < n; i++) {
            if (wood >= WOOD_COST_CANNON && iron >= IRON_COST_CANNON) {
                wood -= WOOD_COST_CANNON; iron -= IRON_COST_CANNON;
                int h = ARMOR_CANNON + (technologyDefense * PLUS_ARMOR_CANNON_BY_TECHNOLOGY * ARMOR_CANNON / 100);
                int d = BASE_DAMAGE_CANNON + (technologyAttack * PLUS_ATTACK_CANNON_BY_TECHNOLOGY * BASE_DAMAGE_CANNON / 100);
                army[3].add(new Cannon(h, d));
            } else throw new ResourceException("Recursos insuficientes para Cannon");
        }
    }

    // --- MÉTODOS DE DEFENSA (AÑADIDOS PARA EL MAIN) ---

    public void newArrowTower(int n) throws ResourceException {
        for (int i = 0; i < n; i++) {
            if (wood >= WOOD_COST_ARROWTOWER && iron >= IRON_COST_ARROWTOWER) {
                wood -= WOOD_COST_ARROWTOWER; iron -= IRON_COST_ARROWTOWER;
                int h = ARMOR_ARROWTOWER + (technologyDefense * PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY * ARMOR_ARROWTOWER / 100);
                int d = BASE_DAMAGE_ARROWTOWER + (technologyAttack * PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY * BASE_DAMAGE_ARROWTOWER / 100);
                army[4].add(new ArrowTower(h, d));
            } else throw new ResourceException("Recursos insuficientes para Arrow Tower");
        }
    }

    public void newCatapult(int n) throws ResourceException {
        for (int i = 0; i < n; i++) {
            if (wood >= WOOD_COST_CATAPULT && iron >= IRON_COST_CATAPULT) {
                wood -= WOOD_COST_CATAPULT; iron -= IRON_COST_CATAPULT;
                int h = ARMOR_CATAPULT + (technologyDefense * PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * ARMOR_CATAPULT / 100);
                int d = BASE_DAMAGE_CATAPULT + (technologyAttack * PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * BASE_DAMAGE_CATAPULT / 100);
                army[5].add(new Catapult(h, d));
            } else throw new ResourceException("Recursos insuficientes para Catapult");
        }
    }

    public void newRocketLauncher(int n) throws ResourceException {
        for (int i = 0; i < n; i++) {
            if (wood >= WOOD_COST_ROCKETLAUNCHERTOWER && iron >= IRON_COST_ROCKETLAUNCHERTOWER) {
                wood -= WOOD_COST_ROCKETLAUNCHERTOWER; iron -= IRON_COST_ROCKETLAUNCHERTOWER;
                int h = ARMOR_ROCKETLAUNCHERTOWER + (technologyDefense * PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * ARMOR_ROCKETLAUNCHERTOWER / 100);
                int d = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (technologyAttack * PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * BASE_DAMAGE_ROCKETLAUNCHERTOWER / 100);
                army[6].add(new RocketLauncherTower(h, d));
            } else throw new ResourceException("Recursos insuficientes para Rocket Launcher");
        }
    }

    // --- MÉTODOS ESPECIALES ---

    public void newMagician(int n) throws ResourceException, BuildingException {
        if (magicTower < 1) throw new BuildingException("Necesitas una Torre Mágica");
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_MAGICIAN && wood >= WOOD_COST_MAGICIAN && iron >= IRON_COST_MAGICIAN && mana >= MANA_COST_MAGICIAN) {
                food -= FOOD_COST_MAGICIAN; wood -= WOOD_COST_MAGICIAN; iron -= IRON_COST_MAGICIAN; mana -= MANA_COST_MAGICIAN;
                army[7].add(new Magician());
            } else throw new ResourceException("Recursos insuficientes para Magician");
        }
    }

    public void newPriest(int n) throws ResourceException, BuildingException {
        if (church < 1) throw new BuildingException("Necesitas una Iglesia");
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_PRIEST && mana >= MANA_COST_PRIEST) {
                food -= FOOD_COST_PRIEST; mana -= MANA_COST_PRIEST;
                army[8].add(new Priest());
            } else throw new ResourceException("Recursos insuficientes para Priest");
        }
    }

    // --- GESTIÓN DE EDIFICIOS ---

    public void buildFarm() throws ResourceException {
        if (food >= FOOD_COST_FARM && wood >= WOOD_COST_FARM && iron >= IRON_COST_FARM) {
            food -= FOOD_COST_FARM; wood -= WOOD_COST_FARM; iron -= IRON_COST_FARM;
            farm++;
        } else throw new ResourceException("No hay recursos para Granja");
    }

    public void buildCarpentry() throws ResourceException {
        if (food >= FOOD_COST_CARPENTRY && wood >= WOOD_COST_CARPENTRY && iron >= IRON_COST_CARPENTRY) {
            food -= FOOD_COST_CARPENTRY; wood -= WOOD_COST_CARPENTRY; iron -= IRON_COST_CARPENTRY;
            carpentry++; 
        } else throw new ResourceException("No hay recursos para Carpintería");
    }

    public void buildSmithy() throws ResourceException {
        if (food >= FOOD_COST_SMITHY && wood >= WOOD_COST_SMITHY && iron >= IRON_COST_SMITHY) {
            food -= FOOD_COST_SMITHY; wood -= WOOD_COST_SMITHY; iron -= IRON_COST_SMITHY;
            smithy++;
        } else throw new ResourceException("No hay recursos para Herrería");
    }

    public void buildMagicTower() throws ResourceException {
        if (food >= FOOD_COST_MAGICTOWER && wood >= WOOD_COST_MAGICTOWER && iron >= IRON_COST_MAGICTOWER) {
            food -= FOOD_COST_MAGICTOWER; wood -= WOOD_COST_MAGICTOWER; iron -= IRON_COST_MAGICTOWER;
            magicTower++;
        } else throw new ResourceException("No hay recursos para Torre Mágica");
    }

    public void buildChurch() throws ResourceException {
        if (food >= FOOD_COST_CHURCH && wood >= WOOD_COST_CHURCH && iron >= IRON_COST_CHURCH) {
            food -= FOOD_COST_CHURCH; wood -= WOOD_COST_CHURCH; iron -= IRON_COST_CHURCH;
            church++;
        } else throw new ResourceException("No hay recursos para Iglesia");
    }

    // --- TECNOLOGÍAS Y RECURSOS ---

    public void upgradeTechnologyDefense() throws ResourceException {
        int costIron = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST + (technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST);
        if (iron >= costIron) {
            iron -= costIron;
            technologyDefense++;
        } else throw new ResourceException("Hierro insuficiente para mejorar defensa");
    }

    public void upgradeTechnologyAttack() throws ResourceException {
        int costIron = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST + (technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST);
        if (iron >= costIron) {
            iron -= costIron;
            technologyAttack++;
        } else throw new ResourceException("Hierro insuficiente para mejorar ataque");
    }

    public void generateResourcesNextMinute() {
        this.iron += CIVILIZATION_IRON_GENERATED + (smithy * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
        this.wood += CIVILIZATION_WOOD_GENERATED + (carpentry * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
        this.food += CIVILIZATION_FOOD_GENERATED + (farm * CIVILIZATION_FOOD_GENERATED_PER_FARM);
        this.mana += (magicTower * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
    }

    // --- GETTERS Y SETTERS ---
    public ArrayList<MilitaryUnit>[] getArmy() { return army; }
    public int getWood() { return wood; }
    public void setWood(int wood) { this.wood = wood; }
    public int getIron() { return iron; }
    public void setIron(int iron) { this.iron = iron; }
    public int getFood() { return food; }
    public void setFood(int food) { this.food = food; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public int getTechnologyAttack() { return technologyAttack; }
    public void setTechnologyAttack(int level) { this.technologyAttack = level; }
    public int getTechnologyDefense() { return technologyDefense; }
    public void setTechnologyDefense(int level) { this.technologyDefense = level; }
    public int getCarpentry() { return carpentry; }
    public void setCarpentry(int carpentry) { this.carpentry = carpentry; }
    public int getFarm() { return farm; }
    public void setFarm(int farm) { this.farm = farm; }
    public int getSmithy() { return smithy; }
    public void setSmithy(int smithy) { this.smithy = smithy; }
    public int getChurch() { return church; }
    public void setChurch(int church) { this.church = church; }
    public int getMagicTower() { return magicTower; }
    public void setMagicTower(int magicTower) { this.magicTower = magicTower; }

    // --- MÉTODO AUXILIAR PARA EL DAO ---
    public void createUnit(int unitType) {
        int armor;
        int damage;

        if (unitType == 0) { // Swordsman
            armor = ARMOR_SWORDSMAN + (technologyDefense * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100);
            damage = BASE_DAMAGE_SWORDSMAN + (technologyAttack * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * BASE_DAMAGE_SWORDSMAN / 100);
            army[0].add(new Swordsman(armor, damage));
        } 
        else if (unitType == 1) { // Spearman
            armor = ARMOR_SPEARMAN + (technologyDefense * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * ARMOR_SPEARMAN / 100);
            damage = BASE_DAMAGE_SPEARMAN + (technologyAttack * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * BASE_DAMAGE_SPEARMAN / 100);
            army[1].add(new Spearman(armor, damage));
        } 
        else if (unitType == 2) { // Crossbow
            armor = ARMOR_CROSSBOW + (technologyDefense * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * ARMOR_CROSSBOW / 100);
            damage = BASE_DAMAGE_CROSSBOW + (technologyAttack * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY * BASE_DAMAGE_CROSSBOW / 100);
            army[2].add(new Crossbow(armor, damage));
        } 
        else if (unitType == 3) { // Cannon
            armor = ARMOR_CANNON + (technologyDefense * PLUS_ARMOR_CANNON_BY_TECHNOLOGY * ARMOR_CANNON / 100);
            damage = BASE_DAMAGE_CANNON + (technologyAttack * PLUS_ATTACK_CANNON_BY_TECHNOLOGY * BASE_DAMAGE_CANNON / 100);
            army[3].add(new Cannon(armor, damage));
        } 
        else if (unitType == 4) { // ArrowTower
            armor = ARMOR_ARROWTOWER + (technologyDefense * PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY * ARMOR_ARROWTOWER / 100);
            damage = BASE_DAMAGE_ARROWTOWER + (technologyAttack * PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY * BASE_DAMAGE_ARROWTOWER / 100);
            army[4].add(new ArrowTower(armor, damage));
        } 
        else if (unitType == 5) { // Catapult
            armor = ARMOR_CATAPULT + (technologyDefense * PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY * ARMOR_CATAPULT / 100);
            damage = BASE_DAMAGE_CATAPULT + (technologyAttack * PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY * BASE_DAMAGE_CATAPULT / 100);
            army[5].add(new Catapult(armor, damage));
        } 
        else if (unitType == 6) { // RocketLauncher Tower
            armor = ARMOR_ROCKETLAUNCHERTOWER + (technologyDefense * PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * ARMOR_ROCKETLAUNCHERTOWER / 100);
            damage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (technologyAttack * PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * BASE_DAMAGE_ROCKETLAUNCHERTOWER / 100);
            army[6].add(new RocketLauncherTower(armor, damage));
        }
        else if (unitType == 7) { // Magician
            army[7].add(new Magician());
        } 
        else if (unitType == 8) { // Priest
            army[8].add(new Priest());
        }
    }
}