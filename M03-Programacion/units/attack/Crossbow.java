package units.attack;

import units.AttackUnit;

public class Crossbow extends AttackUnit{
    //necessitaeis une segundo constructor que crea el Crossbow calculando la technlogia de attack y defense
	public Crossbow(int armor, int baseDamage) { super(armor, baseDamage); }
    public Crossbow() { super(ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW); }

    @Override public int attack() { return baseDamage; }
    @Override public int getFoodCost() { return FOOD_COST_CROSSBOW; }
    @Override public int getWoodCost() { return WOOD_COST_CROSSBOW; }
    @Override public int getIronCost() { return IRON_COST_CROSSBOW; }
    @Override public int getManaCost() { return MANA_COST_CROSSBOW; }
    @Override public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_CROSSBOW; }
    @Override public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_CROSSBOW; }
}
