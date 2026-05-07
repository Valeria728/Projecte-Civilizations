package civilization;

public class RocketLauncherTower extends DefenseUnit {

    // Constructor unico: para nuestra defensa (con calculo de tecnologias)
    public RocketLauncherTower(int tA, int tD) {
        super(0, 0);
        
        // Formula del 5% igual que en las unidades de ataque
        this.armor = ARMOR_ROCKETLAUNCHERTOWER + (tD * 5 * ARMOR_ROCKETLAUNCHERTOWER / 100);
        this.initialArmor = this.armor;
        
        this.baseDamage = DAMAGE_ROCKETLAUNCHERTOWER + (tA * 5 * DAMAGE_ROCKETLAUNCHERTOWER / 100);
    }

    public int attack() {
        return this.baseDamage;
    }

    // Metodos de la interfaz MilitaryUnit
    public int getFoodCost() { return FOOD_COST_ROCKETLAUNCHERTOWER; }
    public int getWoodCost() { return WOOD_COST_ROCKETLAUNCHERTOWER; }
    public int getIronCost() { return IRON_COST_ROCKETLAUNCHERTOWER; }
    public int getManaCost() { return MANA_COST_ROCKETLAUNCHERTOWER; }
    public int getChanceGeneratingWaste() { return CHANCE_GENERATING_WASTE_ROCKETLAUNCHERTOWER; }
    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER; }
    
    // Implementación de los métodos obligatorios de la interfaz MilitaryUnit
    public void resetArmor() { this.armor = this.initialArmor; }
    public int getExperience() { return 0; } 
    public void takeDamage(int damage) { this.armor -= damage; }
    public void setExperience(int exp) {}
}