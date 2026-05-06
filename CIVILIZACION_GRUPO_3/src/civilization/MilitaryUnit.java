package civilization;

public interface MilitaryUnit {
    int attack();
    void takeDamage(int dmg);
    int getActualArmor();
    void resetArmor();
}
