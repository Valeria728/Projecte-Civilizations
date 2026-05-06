package Civilization;

public class ArrowTower extends AttackUnit {
    public ArrowTower(int tA, int tD) {
        this.armor = ARMOR_TOWER + (tD * 5 * ARMOR_TOWER / 100);
        this.initialArmor = this.armor;
        this.baseDamage = DAMAGE_TOWER + (tA * 5 * DAMAGE_TOWER / 100);
    }
    public int attack() { 
    	return this.baseDamage; 
    }
}
