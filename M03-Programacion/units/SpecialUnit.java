package units;
import interfaces.Variables;
import interfaces.MilitaryUnit;
public abstract class SpecialUnit implements MilitaryUnit, Variables {

    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;

    public SpecialUnit(int armor, int baseDamage) {
        // Special units have armor 0 always
        this.armor = 0;
        this.initialArmor = 0;
        this.baseDamage = baseDamage;
        this.experience = 0;
    }

    @Override
    public int attack() {
        int damage = baseDamage;
        damage = damage + (experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * baseDamage / 100);
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
        armor = 0;
    }

    @Override
    public void setExperience(int n) {
        experience = n;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
