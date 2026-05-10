package units.attack;

import units.AttackUnit;

public class Swordsman extends AttackUnit{

	 public Swordsman(int armor, int baseDamage) {
		 super(armor, baseDamage); 
		 }
	    public Swordsman() { super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN); } // Constructor para enemigos

	    
	    //necessitaeis une segundo constructor que crea el swordsman calculando la technlogia de attack y defense
	    public int attack() { return baseDamage; }
	    public int getFoodCost() { return FOOD_COST_SWORDSMAN; }
	    public int getWoodCost() { return WOOD_COST_SWORDSMAN; }
	    public int getIronCost() { return IRON_COST_SWORDSMAN; }
	    public int getManaCost() { return MANA_COST_SWORDSMAN; }
	    public int getChanceGeneratinWaste() { return CHANCE_GENERATNG_WASTE_SWORDSMAN; }
	    public int getChanceAttackAgain() { return CHANCE_ATTACK_AGAIN_SWORDSMAN; }
    
}
