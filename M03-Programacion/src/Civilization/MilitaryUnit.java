package Civilization;

public interface MilitaryUnit {
    int attack();
    void takeDamage(int receivedDamage);
    int getActualArmor();
    
    // Métodos para obtener costes
    int getFoodCost();
    int getWoodCost();
    int getIronCost();
    int getManaCost();
    
    // Gestión de estado
    void resetArmor();
    void setExperience(int n);
    int getExperience();
    
    // Probabilidades de combate
    int getChanceGeneratinWaste();
    int getChanceAttackAgain();
}
