package interfaces;

public interface MilitaryUnit {
	abstract int attack();
	abstract void takeDamage(int receivedDamage);
	abstract int getActualArmor();
    
    // Métodos para obtener costes
	abstract int getFoodCost();
	abstract int getWoodCost();
	abstract int getIronCost();
	abstract int getManaCost();
    
    // Gestión de estado
	abstract void resetArmor();
	abstract void setExperience(int n);
	abstract int getExperience();
    
    // Probabilidades de combate
	abstract int getChanceGeneratinWaste();
	abstract int getChanceAttackAgain();
}

