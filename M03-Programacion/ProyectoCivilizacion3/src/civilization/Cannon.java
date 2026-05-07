package civilization;

public class Cannon extends AttackUnit {

    // Constructor 1: Para nuestro equipo (calcula tecnologias)
    public Cannon(int tA, int tD) {
        super(0, 0);
        
        // Aplicamos la formula del 5% 
        this.armor = ARMOR_CANNON + (tD * 5 * ARMOR_CANNON / 100);
        this.initialArmor = this.armor;
        
        this.baseDamage = DAMAGE_CANNON + (tA * 5 * DAMAGE_CANNON / 100);
    }

    // Constructor 2: Para el enemigo (valores base)
    public Cannon() {
        super(ARMOR_CANNON, DAMAGE_CANNON);
    }

    public int attack() {
        return this.baseDamage;
    }

    // Metodos de la interfaz MilitaryUnit
    public int getFoodCost() { return FOOD_COST_CANNON; }
    public int getWoodCost() { return WOOD_COST_CANNON; }
    public int getIronCost() { return IRON_COST_CANNON; }
    public int getManaCost() { return MANA_COST_CANNON; }
    public int getChanceGeneratingWaste() { return CHANCE_GENERATING_WASTE_CANNON; }
    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_CANNON; }
}