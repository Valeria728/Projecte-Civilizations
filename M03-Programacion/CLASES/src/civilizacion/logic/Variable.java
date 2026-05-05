package civilizacion.logic;

public interface Variables {
    // Costes base de las unidades (FOOD, WOOD, IRON, MANA)
    int FOOD_COST_SWORDSMAN = 8000;
    int WOOD_COST_SWORDSMAN = 3000;
    int IRON_COST_SWORDSMAN = 50;
    
    int FOOD_COST_SPEARMAN = 5000;
    int WOOD_COST_SPEARMAN = 6500;
    int IRON_COST_SPEARMAN = 50;

    // Estadísticas base (ARMOR, ATTACK)
    int ARMOR_SWORDSMAN = 400;
    int BASE_DAMAGE_SWORDSMAN = 80;
    
    int ARMOR_SPEARMAN = 1000;
    int BASE_DAMAGE_SPEARMAN = 150;

    // Porcentajes de mejora por tecnología (5% por nivel)
    int PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY = 5;
    int PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY = 5;
    
    // Probabilidades de ataque de la civilización (en %)
    // Orden: Swordsman(0), Spearman(1), Crossbow(2), Cannon(3)...
    int[] CHANCE_ATTACK_CIVILIZATION_UNITS = {4, 9, 13, 37, 4, 9, 14, 10, 0};
}
