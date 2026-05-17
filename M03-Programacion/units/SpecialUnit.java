package units;
import interfaces.*;

/*Al ser abstracta no se genera directamente, 
 sirve para que otras hereden de ella y compartan la logica.*/ 
public abstract class SpecialUnit implements MilitaryUnit, Variables {

    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;

    public SpecialUnit(int armor, int baseDamage) {
        //unidades espaciales no tienen armadura.
        this.armor = 0;
        this.initialArmor = 0;
        this.baseDamage = baseDamage;
        this.experience = 0;
    }

    /* calcula cuánto daño hace la unidad. No siempre hace su daño base; 
     entre más experiencia tiene la unidad, más fuerte es su ataque.*/
    public int attack() {
        int damage = baseDamage;
        damage = damage + (experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * baseDamage / 100);
        return damage;
    }

    //Resta el daño recibido directamente a la armadura.
    public void takeDamage(int receivedDamage) {
        armor = armor - receivedDamage;
    }

    /*Métodos estándar para consultar el daño, 
    la armadura actual y para consultar o modificar la experiencia de la unidad.*/
    public int getActualArmor() {
        return armor;
    }

    
    public void resetArmor() {
        armor = 0;
    }

    
    public void setExperience(int n) {
        experience = n;
    }

   
    public int getExperience() {
        return experience;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
