package Civilization;

public class ArrowTower extends DefenseUnit { // Hija de DefenseUnit hereda armadura y daño
    public ArrowTower(int armor, int baseDamage) { // Crea nueva torre
    	super(armor, baseDamage); // pasamos armadura y daño para guardar en la memoria de la unidad
    }
    @Override //sobre escribimos en clase padre o interface 
    public int attack() { //Daño inflijido al disparar Torre
    	return baseDamage; 
    }
    @Override // MEtodos para saber costes 
    public int getFoodCost() { 
    	return FOOD_COST_ARROWTOWER; 
    }
    @Override 
    public int getWoodCost() { 
    	return WOOD_COST_ARROWTOWER; 
    }
    @Override 
    public int getIronCost() { 
    	return IRON_COST_ARROWTOWER; 
    }
    @Override 
    public int getManaCost() { 
    	return MANA_COST_ARROWTOWER; 
    }
    @Override //Metodos para la batalla
    public int getChanceGeneratinWaste() { //DEvuelve lo que genera o prabilidad de escombros en %.
    	return CHANCE_GENERATNG_WASTE_ARROWTOWER; 
    }
    @Override 
    public int getChanceAttackAgain() { //Atacar denuevo en el turno, devueltve % en flechas.
    	return CHANCE_ATTACK_AGAIN_ARROWTOWER; 
    }
}
