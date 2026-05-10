package units;

import interfaces.MilitaryUnit;
import interfaces.Variables;

public abstract class SpecialUnit implements MilitaryUnit,Variables{
	protected int armor; 
	protected int initialArmor; 
	protected int baseDamage;
    protected int experience;

    public SpecialUnit(int baseDamage) {
    	this.armor =0;
    	this.initialArmor =0;
        this.baseDamage = baseDamage;
        this.experience = 0;
    }

    // Al no tener armadura, cualquier daño las elimina (armor = 0)
    public void takeDamage(int receivedDamage) {
        // Lógica interna: simplemente se marcan para morir en la clase Battle
    }

    public int getActualArmor() {
        return 0; 
    }

    public void resetArmor() {
        // No tienen armadura que resetear
    }

    public void setExperience(int n) {
        this.experience = n;
    }

    public int getExperience() {
        return this.experience;
}
}