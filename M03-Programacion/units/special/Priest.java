package units.special;

public class Priest extends SpecialUnit {

    public Priest() {
        super(0, 0);
    }

    @Override
    public int getFoodCost() { return FOOD_COST_PRIEST; }

    @Override
    public int getWoodCost() { return WOOD_COST_PRIEST; }

    @Override
    public int getIronCost() { return IRON_COST_PRIEST; }

    @Override
    public int getManaCost() { return MANA_COST_PRIEST; }

    @Override
    public int getChanceGeneratingWaste() { return CHANCE_GENERATNG_WASTE_PRIEST; }

    @Override
    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_PRIEST; }

    @Override
    public String toString() { return "Priest"; }
}
