package units.special;

import units.SpecialUnit;

public class Priest extends SpecialUnit{
	public Priest() {
        super(0); // El sacerdote no ataca, su daño base es 0
    }

    @Override public int attack() { return 0; }
    @Override public int getFoodCost() { return FOOD_COST_PRIEST; }
    @Override public int getWoodCost() { return 0; } // Según tu interfaz es 0
    @Override public int getIronCost() { return 0; } // Según tu interfaz es 0
    @Override public int getManaCost() { return MANA_COST_PRIEST; }
    @Override public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_PRIEST; }
    @Override public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_PRIEST; }
}
