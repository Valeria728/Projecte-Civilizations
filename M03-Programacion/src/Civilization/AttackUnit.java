package Civilization;

public abstract class AttackUnit implements MilitaryUnit, Variables {
    protected int armor;
    protected int initialArmor;
    protected int baseDamage;

    public void takeDamage(int dmg) { this.armor = this.armor - dmg; }
    public int getActualArmor() { return this.armor; }
    public void resetArmor() { this.armor = this.initialArmor; }
}
