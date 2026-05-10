package units.defense;
import units.DefenseUnit;

public class ArrowTower extends DefenseUnit{
    //necessitaeis une segundo constructor que crea el ArrowTower calculando la technlogia de attack y defense
	public ArrowTower(int armor, int baseDamage) { super(armor, baseDamage); }
    @Override public int attack() { return baseDamage; }
    @Override public int getFoodCost() { return FOOD_COST_ARROWTOWER; }
    @Override public int getWoodCost() { return WOOD_COST_ARROWTOWER; }
    @Override public int getIronCost() { return IRON_COST_ARROWTOWER; }
    @Override public int getManaCost() { return MANA_COST_ARROWTOWER; }
    @Override public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_ARROWTOWER; }
    @Override public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_ARROWTOWER; }
}
