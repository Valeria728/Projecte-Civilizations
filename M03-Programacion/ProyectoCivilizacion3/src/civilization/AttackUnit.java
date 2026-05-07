package civilization;

public abstract class AttackUnit implements MilitaryUnit, Variables {
	
	protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;
    protected boolean sanctified;

    public AttackUnit(int armor, int baseDamage) {
        this.armor = armor;
        this.initialArmor = armor; // Guardamos el valor inicial para resetear tras batallas
        this.baseDamage = baseDamage;
        this.experience = 0;
        this.sanctified = false;
    }

    // Métodos de combate
    public void takeDamage(int receivedDamage) {
        this.armor -= receivedDamage;
    }

    public int getActualArmor() {
        return this.armor;
    }

    public void resetArmor() {
        this.armor = this.initialArmor;
    }

    // Gestión de experiencia y estado
    public void setExperience(int n) {
        this.experience = n;
    }

    public int getExperience() {
        return this.experience;
    }

    public boolean isSanctified() {
        return sanctified;
    }

    public void setSanctified(boolean sanctified) {
        this.sanctified = sanctified;
    }
}


