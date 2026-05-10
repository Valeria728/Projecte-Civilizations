package units.attack;

import units.AttackUnit;

public class Spearman extends AttackUnit{
	public Spearman(int armor, int baseDamage) { super(armor, baseDamage); }
    public Spearman() { super(ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN); }


    //necessitaeis une segundo constructor que crea el Spearman calculando la technlogia de attack y defense

    @Override public int attack() { return baseDamage; }
    @Override public int getFoodCost() { return FOOD_COST_SPEARMAN; }
    @Override public int getWoodCost() { return WOOD_COST_SPEARMAN; }
    @Override public int getIronCost() { return IRON_COST_SPEARMAN; }
    @Override public int getManaCost() { return MANA_COST_SPEARMAN; }
    @Override public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_SPEARMAN; }
    @Override public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_SPEARMAN; }
}
