package Civilization;

public class Magician extends SpecialUnit {

    // Constructor con parámetros según página 13 
    public Magician(int armor, int baseDamage) {
        // La armadura para SpecialUnit siempre debe ser 0, pag.10
        this.armor = 0;
        this.initialArmor = 0;
        this.baseDamage = baseDamage;
        this.experience = 0;
    }

    // Constructor sin parámetros para el ejército enemigo pág. 13
    public Magician() {
        this.armor = 0;
        this.initialArmor = 0;
        this.baseDamage = Variables.BASE_DAMAGE_MAGICIAN; // 3000
        this.experience = 0;
    }

    // Implementación de métodos de la interfaz MilitaryUnit pág. 11

    @Override
    public int attack() {
        return this.baseDamage;
    }

    @Override
    public void takeDamage(int receivedDamage) {
        this.armor -= receivedDamage;
    }

    @Override
    public int getActualArmor() {
        return this.armor;
    }

    @Override
    public int getFoodCost() {
        return Variables.FOOD_COST_MAGICIAN; // 12000
    }

    @Override
    public int getWoodCost() {
        return Variables.WOOD_COST_MAGICIAN; // 2000
    }

    @Override
    public int getIronCost() {
        return Variables.IRON_COST_MAGICIAN; // 500
    }

    @Override
    public int getManaCost() {
        return Variables.MANA_COST_MAGICIAN; // 5000
    }

    @Override
    public int getChanceGeneratinWaste() {
        return Variables.CHANCE_GENERATNG_WASTE_MAGICIAN; // 0
    }

    @Override
    public int getChanceAttackAgain() {
        return Variables.CHANCE_ATTACK_AGAIN_MAGICIAN; // 75
    }

    @Override
    public void resetArmor() {
        this.armor = this.initialArmor;
    }

    @Override
    public void setExperience(int n) {
        this.experience = n;
    }

    @Override
    public int getExperience() {
        return this.experience;
    }
}
