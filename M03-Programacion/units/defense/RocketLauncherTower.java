package units.defense;

import units.DefenseUnit;

public class RocketLauncherTower extends DefenseUnit{
    //necessitaeis une segundo constructor que crea el RocketLauncherTower calculando la technlogia de attack y defense
	public RocketLauncherTower(int armor, int baseDamage) { super(armor, baseDamage); }
    @Override public int attack() { return baseDamage; }
    @Override public int getFoodCost() { return FOOD_COST_ROCKETLAUNCHERTOWER; }
    @Override public int getWoodCost() { return WOOD_COST_ROCKETLAUNCHERTOWER; }
    @Override public int getIronCost() { return IRON_COST_ROCKETLAUNCHERTOWER; }
    @Override public int getManaCost() { return MANA_COST_ROCKETLAUNCHERTOWER; }
    @Override public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER; }
    @Override public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER; }

}
