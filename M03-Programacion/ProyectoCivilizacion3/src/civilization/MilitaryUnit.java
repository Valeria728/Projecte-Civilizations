package civilization;

public interface MilitaryUnit {
	
    // Métodos de combate
    int attack();
    void takeDamage(int receivedDamage);
    int getActualArmor();
    void resetArmor();

    // Costes de recursos
    int getFoodCost();
    int getWoodCost();
    int getIronCost();
    int getManaCost();

    // Gestión de experiencia
    void setExperience(int n);
    int getExperience(); 

    // Probabilidades
    int getChanceGeneratingWaste(); 
    int getChanceAttackAgain();

}