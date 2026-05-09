package Civilization;

public class Magician extends SpecialUnit {
    public Magician() {
        super(BASE_DAMAGE_MAGICIAN);
    }

    @Override 
    public int attack() { 
    	return baseDamage; 
    }
    @Override 
    public int getFoodCost() { 
    	return FOOD_COST_MAGICIAN; 
    }
    @Override 
    public int getWoodCost() { 
    	return WOOD_COST_MAGICIAN; 
    }
    @Override 
    public int getIronCost() { 
    	return IRON_COST_MAGICIAN; 
    }
    @Override 
    public int getManaCost() { 
    	return MANA_COST_MAGICIAN; 
    }
    @Override 
    public int getChanceGeneratinWaste() { 
    	return CHANCE_GENERATNG_WASTE_MAGICIAN; 
    }
    @Override 
    public int getChanceAttackAgain() { 
    	return CHANCE_ATTACK_AGAIN_MAGICIAN; 
    }
}
