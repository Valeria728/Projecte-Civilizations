package civilization;

public class Swordsman extends AttackUnit {

    // Constructor para nuestra civilizacion (con calculo de tecnologias)
    public Swordsman(int tA, int tD) {
        super(0, 0);
        
        // Formula: Base + (Nivel * 5)% de la Base
        this.armor = ARMOR_SWORDSMAN + (tD * 5 * ARMOR_SWORDSMAN / 100);
        this.initialArmor = this.armor;
        
        this.baseDamage = DAMAGE_SWORDSMAN + (tA * 5 * DAMAGE_SWORDSMAN / 100);
    }

    // Constructor sin parametros para el enemigo
    public Swordsman() {
        super(ARMOR_SWORDSMAN, DAMAGE_SWORDSMAN);
    }

    public int attack() {
        return this.baseDamage;
    }

    // Costes y probabilidades de la interfaz
    public int getFoodCost() { return FOOD_COST_SWORDSMAN; }
    public int getWoodCost() { return WOOD_COST_SWORDSMAN; }
    public int getIronCost() { return IRON_COST_SWORDSMAN; }
    public int getManaCost() { return MANA_COST_SWORDSMAN; }
    public int getChanceGeneratingWaste() { return CHANCE_GENERATNG_WASTE_SWORDSMAN; }
    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_SWORDSMAN; }
}