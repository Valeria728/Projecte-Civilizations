package units.attack;

import units.AttackUnit;

public class Spearman extends AttackUnit {

    public Spearman(int technologyDefense, int technologyAttack) {
        super(
            ARMOR_SPEARMAN + (technologyDefense * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * ARMOR_SPEARMAN / 100),
            BASE_DAMAGE_SPEARMAN + (technologyAttack * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * BASE_DAMAGE_SPEARMAN / 100)
        );
    }

    public Spearman() {
        super(ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
    }

    @Override
    public int getFoodCost() { return FOOD_COST_SPEARMAN; }

    @Override
    public int getWoodCost() { return WOOD_COST_SPEARMAN; }

    @Override
    public int getIronCost() { return IRON_COST_SPEARMAN; }

    @Override
    public int getManaCost() { return MANA_COST_SPEARMAN; }

    @Override
    public int getChanceGeneratingWaste() { return CHANCE_GENERATNG_WASTE_SPEARMAN; }

    @Override
    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_SPEARMAN; }

    @Override
    public String toString() { return "Spearman"; }
}
