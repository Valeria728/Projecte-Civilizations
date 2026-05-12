package units;

import interfaces.MilitaryUnit;
import interfaces.Variables;

public abstract class AttackUnit implements MilitaryUnit, Variables {

    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;
    protected boolean sanctified;

    // Constructor with technology parameters
    public AttackUnit(int armor, int baseDamage) {
        this.armor = armor;
        this.initialArmor = armor;
        this.baseDamage = baseDamage;
        this.experience = 0;
        this.sanctified = false;
    }

    @Override
    public int attack() {
        int damage = baseDamage;
        // experience bonus
        damage = damage + (experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * baseDamage / 100);
        // sanctify bonus
        if (sanctified) {
            damage = damage + (PLUS_ATTACK_UNIT_SANCTIFIED * baseDamage / 100);
        }
        return damage;
    }

    @Override
    public void takeDamage(int receivedDamage) {
        armor = armor - receivedDamage;
    }

    @Override
    public int getActualArmor() {
        return armor;
    }

    @Override
    public void resetArmor() {
        armor = initialArmor;
    }

    @Override
    public void setExperience(int n) {
        experience = n;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    public boolean isSanctified() {
        return sanctified;
    }

    public void setSanctified(boolean sanctified) {
        this.sanctified = sanctified;
        if (sanctified) {
            armor = initialArmor + (PLUS_ARMOR_UNIT_SANCTIFIED * initialArmor / 100);
        } else {
            armor = initialArmor;
        }
    }

    public int getInitialArmor() {
        return initialArmor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
