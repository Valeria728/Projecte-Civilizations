package units.special;
import units.SpecialUnit;

public class Magician extends SpecialUnit {
/*Le pasa un 0 para la armadura y calcula su daño base inicial.
El daño del mago no es fijo; depende de un parámetro technologyAttack. 
El código calcula un extra de daño porcentual basado en las mejoras tecnológicas
*/
    public Magician(int technologyAttack) {
        super(0,BASE_DAMAGE_MAGICIAN + (technologyAttack * PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY * BASE_DAMAGE_MAGICIAN / 100));
    }

    // cuánto cuesta "entrenar" o "invocar" a un Mago, detallado en la interfaz MilitaryUnit.
    public int getFoodCost() { 
    	return FOOD_COST_MAGICIAN; 
    }

    
    public int getWoodCost() { 
    	return WOOD_COST_MAGICIAN; 
    }

    
    public int getIronCost() { 
    	return IRON_COST_MAGICIAN; 
    }

   
    public int getManaCost() { 
    	return MANA_COST_MAGICIAN; 
    }

    // Devuelve la probabilidad de que el mago genere "residuos" o "desechos"
    public int getChanceGeneratingWaste() { 
    	return CHANCE_GENERATNG_WASTE_MAGICIAN; 
    }

    // Devuelve la probabilidad de atacar de nuevo.
    public int getChanceAttackAgain() { 
    	return CHANCE_ATTACK_AGAIN_MAGICIAN; 
    }

    // Devuelve simplemente el texto "Magician".Quiera imprimir en pantalla.
    public String toString() { 
    	return "Magician";    	
    }
}
/*El Magician es un "Cañón de cristal" que consume recursos estándar más 
maná para ser creado. Su poder de ataque inicial escala con la tecnología 
de ataque de tu imperio y posee habilidades especiales como 
la oportunidad de atacar dos veces en un turno.*/ 
