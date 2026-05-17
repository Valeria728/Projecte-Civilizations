package units.special;
/*El Priest es una unidad de soporte (0 de ataque) 
y completamente indefensa (0 de armadura). 
Cuesta recursos y maná, y está diseñado para aportar 
valor al ejército a través de habilidades místicas en lugar de fuerza bruta.*/ 

import units.SpecialUnit;
// Sacerdote que hereda de SpecialUnit, unidad de soporte, no hace daño.
public class Priest extends SpecialUnit{

    public Priest() {
        super(0, 0);
    }

    
    public int getFoodCost() { 
    	return FOOD_COST_PRIEST; 
    }

    
    public int getWoodCost() { 
    	return WOOD_COST_PRIEST; 
    }

    
    public int getIronCost() { 
    	return IRON_COST_PRIEST; 
    }

    
    public int getManaCost() { 
    	return MANA_COST_PRIEST; 
    }

   
    public int getChanceGeneratingWaste() {
    	return CHANCE_GENERATNG_WASTE_PRIEST; 
    }

    
    public int getChanceAttackAgain() { 
    	return CHANCE_ATTACK_AGAIN_PRIEST; 
    }

    
    public String toString() { 
    	return "Priest"; 
    }
}
