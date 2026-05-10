package game;

import interfaces.Variables;
import units.attack.Cannon;
import units.attack.Crossbow;
import units.attack.Spearman;
import units.attack.Swordsman;
import units.special.Magician;
import units.special.Priest;

import java.util.ArrayList;

import interfaces.MilitaryUnit;
import exceptions.BuildingException;
import exceptions.ResourcesException;


public class Civilization implements Variables {
	private int wood, iron, food, mana;
    private int technologyDefense, technologyAttack;
    private int farm, smithy, carpentry, magicTower, church;
    
    // El PDF pide un array de ArrayLists para el ejército (9 tipos de unidades)
    private ArrayList<MilitaryUnit>[] army;

    @SuppressWarnings("unchecked")
    public Civilization() {
        this.wood = 25000; // Valores iniciales sugeridos
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

    // Métodos para crear unidades (Ejemplo: Swordsman)
    public void newSwordsman(int n) throws ResourcesException {
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_SWORDSMAN && wood >= WOOD_COST_SWORDSMAN && iron >= IRON_COST_SWORDSMAN) {
                food -= FOOD_COST_SWORDSMAN;
                wood -= WOOD_COST_SWORDSMAN;
                iron -= IRON_COST_SWORDSMAN;
                
                // Cálculo de estadísticas con tecnología
                int armor = ARMOR_SWORDSMAN + (technologyDefense * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100);
                int damage = BASE_DAMAGE_SWORDSMAN + (technologyAttack * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * BASE_DAMAGE_SWORDSMAN / 100);
                
                army[0].add(new Swordsman(armor, damage));
            } else {
                throw new ResourcesException("No hay suficientes recursos para crear más Swordsman.");
            }
        }
    }
    public void newSpearman(int n) throws ResourcesException {
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_SPEARMAN && wood >= WOOD_COST_SPEARMAN && iron >= IRON_COST_SPEARMAN) {
                food -= FOOD_COST_SPEARMAN; wood -= WOOD_COST_SPEARMAN; iron -= IRON_COST_SPEARMAN;
                int h = ARMOR_SPEARMAN + (technologyDefense * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * ARMOR_SPEARMAN / 100);
                int d = BASE_DAMAGE_SPEARMAN + (technologyAttack * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * BASE_DAMAGE_SPEARMAN / 100);
                army[1].add(new Spearman(h, d));
            } else throw new ResourcesException("Recursos insuficientes para Spearman");
        }
    }

    public void newCrossbow(int n) throws ResourcesException {
        for (int i = 0; i < n; i++) {
            if (wood >= WOOD_COST_CROSSBOW && iron >= IRON_COST_CROSSBOW) {
                wood -= WOOD_COST_CROSSBOW; iron -= IRON_COST_CROSSBOW;
                int h = ARMOR_CROSSBOW + (technologyDefense * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * ARMOR_CROSSBOW / 100);
                int d = BASE_DAMAGE_CROSSBOW + (technologyAttack * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY * BASE_DAMAGE_CROSSBOW / 100);
                army[2].add(new Crossbow(h, d));
            } else throw new ResourcesException("Recursos insuficientes para Crossbow");
        }
    }

    public void newCannon(int n) throws ResourcesException {
        for (int i = 0; i < n; i++) {
            if (wood >= WOOD_COST_CANNON && iron >= IRON_COST_CANNON) {
                wood -= WOOD_COST_CANNON; iron -= IRON_COST_CANNON;
                int h = ARMOR_CANNON + (technologyDefense * PLUS_ARMOR_CANNON_BY_TECHNOLOGY * ARMOR_CANNON / 100);
                int d = BASE_DAMAGE_CANNON + (technologyAttack * PLUS_ATTACK_CANNON_BY_TECHNOLOGY * BASE_DAMAGE_CANNON / 100);
                army[3].add(new Cannon(h, d));
            } else throw new ResourcesException("Recursos insuficientes para Cannon");
        }
    }

    public void newMagician(int n) throws ResourcesException, BuildingException {
        if (magicTower < 1) throw new BuildingException("Necesitas una Torre Mágica");
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_MAGICIAN && wood >= WOOD_COST_MAGICIAN && iron >= IRON_COST_MAGICIAN && mana >= MANA_COST_MAGICIAN) {
                food -= FOOD_COST_MAGICIAN; wood -= WOOD_COST_MAGICIAN; iron -= IRON_COST_MAGICIAN; mana -= MANA_COST_MAGICIAN;
                army[7].add(new Magician());
            } else throw new ResourcesException("Recursos insuficientes para Magician");
        }
    }

    public void newPriest(int n) throws ResourcesException, BuildingException {
        if (church < 1) throw new BuildingException("Necesitas una Iglesia");
        for (int i = 0; i < n; i++) {
            if (food >= FOOD_COST_PRIEST && mana >= MANA_COST_PRIEST) {
                food -= FOOD_COST_PRIEST; mana -= MANA_COST_PRIEST;
                army[8].add(new Priest());
            } else throw new ResourcesException("Recursos insuficientes para Priest");
        }
    }
    public void buildFarm() throws ResourcesException {
        if (food >= FOOD_COST_FARM && wood >= WOOD_COST_FARM && iron >= IRON_COST_FARM) {
            food -= FOOD_COST_FARM; wood -= WOOD_COST_FARM; iron -= IRON_COST_FARM;
            farm++;
        } else throw new ResourcesException("No hay recursos para Granja");
    }
    public void buildCarpentry() throws ResourcesException {
        // Usamos los costes definidos en Variables (asegúrate de que existan en tu interfaz)
        if (food >= FOOD_COST_CARPENTRY && wood >= WOOD_COST_CARPENTRY && iron >= IRON_COST_CARPENTRY) {
            food -= FOOD_COST_CARPENTRY; 
            wood -= WOOD_COST_CARPENTRY; 
            iron -= IRON_COST_CARPENTRY;
            carpentry++; 
            System.out.println("Carpintería construida. La producción de madera ha aumentado.");
        } else {
            throw new ResourcesException("No hay recursos para Carpintería");
        }
    }

    public void buildSmithy() throws ResourcesException {
        if (food >= FOOD_COST_SMITHY && wood >= WOOD_COST_SMITHY && iron >= IRON_COST_SMITHY) {
            food -= FOOD_COST_SMITHY; wood -= WOOD_COST_SMITHY; iron -= IRON_COST_SMITHY;
            smithy++;
        } else throw new ResourcesException("No hay recursos para Herrería");
    }

    public void buildMagicTower() throws ResourcesException {
        if (food >= FOOD_COST_MAGICTOWER && wood >= WOOD_COST_MAGICTOWER && iron >= IRON_COST_MAGICTOWER) {
            food -= FOOD_COST_MAGICTOWER; wood -= WOOD_COST_MAGICTOWER; iron -= IRON_COST_MAGICTOWER;
            magicTower++;
        } else throw new ResourcesException("No hay recursos para Torre Mágica");
    }

    public void upgradeTechnologyDefense() throws ResourcesException {
        int costIron = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST + (technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST);
        if (iron >= costIron) {
            iron -= costIron;
            technologyDefense++;
        } else throw new ResourcesException("Hierro insuficiente para mejorar defensa");
    }

    public void upgradeTechnologyAttack() throws ResourcesException {
        int costIron = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST + (technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST);
        if (iron >= costIron) {
            iron -= costIron;
            technologyAttack++;
        } else throw new ResourcesException("Hierro insuficiente para mejorar ataque");
    }
    public void generateResourcesNextMinute() {
        this.iron += CIVILIZATION_IRON_GENERATED + (smithy * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
        this.wood += CIVILIZATION_WOOD_GENERATED + (carpentry * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
        this.food += CIVILIZATION_FOOD_GENERATED + (farm * CIVILIZATION_FOOD_GENERATED_PER_FARM);
        this.mana += (magicTower * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
    }

    // Getters necesarios para la batalla
    public ArrayList<MilitaryUnit>[] getArmy() { return army; }
    public int getWood() { return wood; }
    public void setWood(int wood) { this.wood = wood; }
    public int getIron() { return iron; }
    public void setIron(int iron) { this.iron = iron; }
    public int getFood() { return food; }
    public void setFood(int food) { this.food = food; }
 // Getters para Base de Datos y Gestión
    public int getMana() { 
        return mana; 
    }
    
    public int getTechnologyAttack() { 
        return technologyAttack; 
    }
    
    public int getTechnologyDefense() { 
        return technologyDefense; 
    }
    public int getCarpentry() { return carpentry; }
 // Getters para el DAO (Lectura)

    public int getFarm() { 
        return farm; 
    }

    public int getSmithy() { 
        return smithy; 
    }

    public int getMagicTower() { 
        return magicTower; 
    }

    // Opcional: Setters (cargar la partida)
    public void setMana(int mana) { this.mana = mana; }
    public void setTechnologyAttack(int level) { this.technologyAttack = level; }
    public void setTechnologyDefense(int level) { this.technologyDefense = level; }
    public void setCarpentry(int carpentry) { this.carpentry = carpentry; }
 // Setters para el DAO (Escritura/Carga)

    public void setFarm(int farm) { 
        this.farm = farm; 
    }

    public void setSmithy(int smithy) { 
        this.smithy = smithy; 
    }

    public void setMagicTower(int magicTower) { 
        this.magicTower = magicTower; 
    }

    
}