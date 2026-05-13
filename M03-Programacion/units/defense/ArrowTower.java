package units.defense;
import units.DefenseUnit;
public class ArrowTower extends DefenseUnit {

    public ArrowTower(int technologyDefense, int technologyAttack) {
        super(
            ARMOR_ARROWTOWER + (technologyDefense * PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY * ARMOR_ARROWTOWER / 100),
            BASE_DAMAGE_ARROWTOWER + (technologyAttack * PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY * BASE_DAMAGE_ARROWTOWER / 100)
        );
    }

    @Override
    public int getFoodCost() { return FOOD_COST_ARROWTOWER; }

    @Override
    public int getWoodCost() { return WOOD_COST_ARROWTOWER; }

    @Override
    public int getIronCost() { return IRON_COST_ARROWTOWER; }

    @Override
    public int getManaCost() { return MANA_COST_ARROWTOWER; }

    @Override
    public int getChanceGeneratingWaste() { return CHANCE_GENERATNG_WASTE_ARROWTOWER; }

    @Override
    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_ARROWTOWER; }

    @Override
    public String toString() { return "Arrow Tower"; }
}
