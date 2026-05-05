package civilizacion.logic;

public class Swordsman extends AttackUnit {

    // Constructor que usa las tecnologías de la civilización
    public Swordsman(int techDefense, int techAttack) {
        // Cálculo de armadura según tecnología: Base + (Nivel * Plus)%
        this.armor = ARMOR_SWORDSMAN + (techDefense * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY) * ARMOR_SWORDSMAN / 100;
        this.initialArmor = this.armor;
        
        // Cálculo de daño según tecnología
        this.baseDamage = BASE_DAMAGE_SWORDSMAN + (techAttack * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY) * BASE_DAMAGE_SWORDSMAN / 100;
        
        this.experience = 0;
        this.sanctified = false;
    }

    @Override
    public int attack() {
        return this.baseDamage;
    }

    // Métodos para obtener costes que nos vienen de la interfaz Variables.
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
    	return 0; 
    }
    @Override 
    public int getChanceGeneratinWaste() { 
    	return 55; 
    } 
    @Override 
    public int getChanceAttackAgain() { 
    	return 3; 
    }    
}
