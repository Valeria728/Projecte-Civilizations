package civilizacion.logic;

public abstract class AttackUnit implements MilitaryUnit, Variables {
    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;
    protected boolean sanctified;

    // Implementación de métodos comunes para no repetir código
    @Override
    public int getActualArmor() {
        return this.armor;
    }

    @Override
    public void takeDamage(int receivedDamage) {
        this.armor -= receivedDamage;
    }

    @Override
    public int getExperience() {
        return this.experience;
    }

    @Override
    public void setExperience(int n) {
        this.experience = n;
    }

    @Override
    public void resetArmor() {
        this.armor = this.initialArmor;
    }
}
