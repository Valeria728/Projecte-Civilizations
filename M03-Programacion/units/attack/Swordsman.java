package units.attack;

import units.AttackUnit;

public class Swordsman extends AttackUnit {

    // Constructor with technology level
    public Swordsman(int technologyDefense, int technologyAttack) {
        super(
            ARMOR_SWORDSMAN + (technologyDefense * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * ARMOR_SWORDSMAN / 100),
            BASE_DAMAGE_SWORDSMAN + (technologyAttack * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * BASE_DAMAGE_SWORDSMAN / 100)
        );
    }

    // Constructor without technology (for enemy army)
    public Swordsman() {
        super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
    }

    @Override
    public int getFoodCost() { return FOOD_COST_SWORDSMAN; }

    @Override
    public int getWoodCost() { return WOOD_COST_SWORDSMAN; }

    @Override
    public int getIronCost() { return IRON_COST_SWORDSMAN; }

    @Override
    public int getManaCost() { return MANA_COST_SWORDSMAN; }

    @Override
    public int getChanceGeneratingWaste() { return CHANCE_GENERATNG_WASTE_SWORDSMAN; }

    @Override
    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_SWORDSMAN; }

    @Override
    public String toString() { return "Swordsman"; }
}
