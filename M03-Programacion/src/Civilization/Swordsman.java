package Civilization;

public class Swordsman extends AttackUnit {
    public Swordsman(int armor, int baseDamage) { 
    	super(armor, baseDamage); 
    }
    public Swordsman() { 
    	super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN); 
    } 

    @Override 
    public int attack() { 
    	return baseDamage; 
    }
    @Override 
    public int getFoodCost() { 
    	return FOOD_COST_SWORDSMAN; 
    }
    @Override 
    public int getWoodCost() { 
    	return WOOD_COST_SWORDSMAN; 
    }
    @Override 
    public int getIronCost() { 
    	return IRON_COST_SWORDSMAN; 
    }
    @Override 
    public int getManaCost() { 
    	return MANA_COST_SWORDSMAN; 
    }
    @Override 
    public int getChanceGeneratinWaste() { 
    	return CHANCE_GENERATNG_WASTE_SWORDSMAN; 
    }
    @Override 
    public int getChanceAttackAgain() { 
    	return CHANCE_ATTACK_AGAIN_SWORDSMAN; 
    }
}
