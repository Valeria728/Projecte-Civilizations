package units.attack;

import units.AttackUnit;

public class Cannon extends AttackUnit{
    //necessitaeis une segundo constructor que crea el Cannon calculando la technlogia de attack y defense
	public Cannon(int armor, int baseDamage) { super(armor, baseDamage); }
    public Cannon() { super(ARMOR_CANNON, BASE_DAMAGE_CANNON); }

    @Override public int attack() { return baseDamage; }
    @Override public int getFoodCost() { return FOOD_COST_CANNON; }
    @Override public int getWoodCost() { return WOOD_COST_CANNON; }
    @Override public int getIronCost() { return IRON_COST_CANNON; }
    @Override public int getManaCost() { return MANA_COST_CANNON; }
    @Override public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_CANNON; }
    @Override public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_CANNON; }
}
