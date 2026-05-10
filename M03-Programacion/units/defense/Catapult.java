package units.defense;

import units.DefenseUnit;

public class Catapult extends DefenseUnit{
    //necessitaeis une segundo constructor que crea el Catapult calculando la technlogia de attack y defense
	public Catapult(int armor, int baseDamage) { super(armor, baseDamage); }
    @Override public int attack() { return baseDamage; }
    @Override public int getFoodCost() { return FOOD_COST_CATAPULT; }
    @Override public int getWoodCost() { return WOOD_COST_CATAPULT; }
    @Override public int getIronCost() { return IRON_COST_CATAPULT; }
    @Override public int getManaCost() { return MANA_COST_CATAPULT; }
    @Override public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_CATAPULT; }
    @Override public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_CATAPULT; }
}
