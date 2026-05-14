package units;

import interfaces.*;

public abstract class DefenseUnit implements MilitaryUnit, Variables {

    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;
    protected boolean sanctified;

    public DefenseUnit(int armor, int baseDamage) {
        this.armor = armor;
        this.initialArmor = armor;
        this.baseDamage = baseDamage;
        this.experience = 0;
        this.sanctified = false;
    }

    @Override
    public int attack() {
        int damage = baseDamage;
        damage = damage + (experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * baseDamage / 100);
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
