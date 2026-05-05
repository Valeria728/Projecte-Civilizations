package civilizacion.logic;

public abstract class DefenseUnit implements MilitaryUnit, Variables {
    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;
    protected boolean sanctified;

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
    
    // Las defensas no suelen costar comida o maná según el PDF, 
    // pero debemos implementar los métodos de la interfaz.
    @Override public int getFoodCost() { return 0; }
    @Override public int getManaCost() { return 0; }
}
