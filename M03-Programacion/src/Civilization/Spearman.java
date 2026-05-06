package Civilization;

public class Spearman extends AttackUnit {
    public Spearman(int tA, int tD) {
        this.armor = ARMOR_SPEARMAN + (tD * 5 * ARMOR_SPEARMAN / 100);
        this.initialArmor = this.armor;
        this.baseDamage = DAMAGE_SPEARMAN + (tA * 5 * DAMAGE_SPEARMAN / 100);
    }
    public int attack() { 
    	return this.baseDamage; 
    }
}
