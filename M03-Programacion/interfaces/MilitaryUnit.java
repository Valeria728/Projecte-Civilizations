package interfaces;
/*Esta interfaz es el molde maestro. Gracias a ella, el motor del juego puede tratar a un Mago, 
a un Sacerdote, o a cualquier otro soldado (como un Arquero o Caballero) exactamente de la misma 
manera: simplemente les dice .attack(), les aplica .takeDamage() o consulta su .
getFoodCost(), sin importarle qué tipo de unidad sea por dentro. ¡Es la magia del polimorfismo!*/ 

public interface MilitaryUnit {

    int attack();

    void takeDamage(int receivedDamage);

    int getActualArmor();

    int getFoodCost();

    int getWoodCost();

    int getIronCost();

    int getManaCost();

    int getChanceGeneratingWaste();

    int getChanceAttackAgain();

    void resetArmor();

    void setExperience(int n);

    int getExperience();
}
