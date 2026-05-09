package Civilization;
/*Clase que implementa todas las unidades de ataque.
 * Esta clase al ser abstracta no actua directamente
 * Las clases hijas heredan toda la funcion o comportamineto de esta  */
public abstract class AttackUnit implements MilitaryUnit, Variables {
	//Usamos protectec para que las clases hijas puedan heredar
    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;
    protected boolean sanctified;//Si esta bendecida (TRUE), funciona mejor

    public AttackUnit(int armor, int baseDamage) {
        this.armor = armor;
        this.initialArmor = armor;
        this.baseDamage = baseDamage;
        this.experience = 0;
        this.sanctified = false;
    }
    //Metodos de supervivencia.
    public void takeDamage(int receivedDamage) { //Resta daño armadura actual,se usa en battle cuando te golpea enemigo
        this.armor -= receivedDamage;
    }

    public int getActualArmor() { //Aqui vemos las armaduras que  quedan 
        return this.armor;
    }

    public void resetArmor() {  // inicializa las que siguen para recuperar energia.
        this.armor = this.initialArmor;
    }
    // GEstion esperiencia y bendicion.
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
