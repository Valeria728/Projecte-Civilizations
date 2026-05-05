package civilizacion.logic;

public class Spearman extends AttackUnit {

    public Spearman(int techDefense, int techAttack) {
        // Usamos constantes de SPEARMAN
        this.armor = ARMOR_SPEARMAN + (techDefense * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY) * ARMOR_SPEARMAN / 100;
        this.initialArmor = this.armor;
        
        this.baseDamage = BASE_DAMAGE_SPEARMAN + (techAttack * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY) * BASE_DAMAGE_SPEARMAN / 100;
        
        this.experience = 0;
        this.sanctified = false;
    }

    @Override
    public int attack() {
        return this.baseDamage;
    }

    @Override 
    public int getFoodCost() { 
    	return FOOD_COST_SPEARMAN; 
    }
    @Override 
    public int getWoodCost() { 
    	return WOOD_COST_SPEARMAN; 
    }
    @Override 
    public int getIronCost() { 
    	return IRON_COST_SPEARMAN; 
    }
    @Override 
    public int getManaCost() { 
    	return 0; 
    }
    @Override 
    public int getChanceGeneratinWaste() { 
    	return 65; 
    } 
    @Override 
    public int getChanceAttackAgain() { 
    	return 7; 
    }    
}
