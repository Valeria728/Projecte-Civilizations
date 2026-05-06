package civilization;
import java.util.*;

public interface Variables {
	// ==============================
	// RECURSOS INICIALES DEL ENEMIGO
	// ==============================
	
	int IRON_BASE_ENEMY_ARMY = 26000;
    int WOOD_BASE_ENEMY_ARMY = 180000;
    int FOOD_BASE_ENEMY_ARMY = 70000;
    
    // PORCENTAJE DE AUMENTO DE LOS RECURSOS DISPONIBLES PARA CREAR LA FLOTA ENEMIGA 
    int ENEMY_FLEET_INCREASE = 6;
    
    // =======================
    //  GENERACIÓN DE RECURSOS
    // =======================
    int CIVILIZATION_IRON_GENERATED = 1500;
    int CIVILIZATION_WOOD_GENERATED = 5000;
    int CIVILIZATION_FOOD_GENERATED = 8000;

    int CIVILIZATION_IRON_GENERATED_PER_SMITHY = (int)(0.5 * CIVILIZATION_IRON_GENERATED);
    int CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY = (int)(0.5 * CIVILIZATION_WOOD_GENERATED);
    int CIVILIZATION_FOOD_GENERATED_PER_FARM = (int)(0.5 * CIVILIZATION_FOOD_GENERATED);
    int CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER = 10;
    
    // ============
    //  TECNOLOGIAS
    // ============

    int UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST = 2000; 
    int UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST = 2000; 
    int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST = 60; 
    int UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST = 60; 
     
    int UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST = 0; 
    int UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST = 0; 
    int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST = 0; 
    int UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST = 0; 
    
    
    // ============================
    //  UNIDADES DE ATAQUE DE COSTO
    // ============================
    int FOOD_COST_SWORDSMAN = 8000; 
    int FOOD_COST_SPEARMAN = 5000; 
    int FOOD_COST_CROSSBOW = 0; 
    int FOOD_COST_CANNON = 0; 
    int WOOD_COST_SWORDSMAN = 3000; 
    int WOOD_COST_SPEARMAN = 6500; 
    int WOOD_COST_CROSSBOW = 45000; 
    int WOOD_COST_CANNON = 30000; 
    int IRON_COST_SWORDSMAN = 50; 
    int IRON_COST_SPEARMAN = 50; 
    int IRON_COST_CROSSBOW = 7000; 
    int IRON_COST_CANNON = 15000; 
    int MANA_COST_SWORDSMAN = 0; 
    int MANA_COST_SPEARMAN = 0; 
    int MANA_COST_CROSSBOW = 0; 
    int MANA_COST_CANNON = 0;
    
    
    // ============================================================
    //  COSTO DE DEFENSA  ARROWTOWER, CATAPULT, ROCKETLAUNCHERTOWER
    // ============================================================
    int IRON_COST_ARROWTOWER = 0; 
    int IRON_COST_CATAPULT = 500; 
    int IRON_COST_ROCKETLAUNCHERTOWER = 5000; 
    int WOOD_COST_ARROWTOWER = 2000; 
    int WOOD_COST_CATAPULT = 4000; 
    int WOOD_COST_ROCKETLAUNCHERTOWER = 50000;  
    int FOOD_COST_ARROWTOWER = 0; 
    int FOOD_COST_CATAPULT = 0; 
    int FOOD_COST_ROCKETLAUNCHERTOWER = 0; 
    int MANA_COST_ARROWTOWER = 0; 
    int MANA_COST_CATAPULT = 0; 
    int MANA_COST_ROCKETLAUNCHERTOWER = 0;
    
    

    // =============================
    //  COSTO DE UNIDADES ESPECIALES 
    // =============================
    int FOOD_COST_MAGICIAN = 12000; 
    int FOOD_COST_PRIEST = 15000; 
    int WOOD_COST_MAGICIAN = 2000; 
    
    int WOOD_COST_PRIEST = 0; 
    int IRON_COST_MAGICIAN = 500; 
    int IRON_COST_PRIEST = 0; 
    int MANA_COST_MAGICIAN = 5000; 
    int MANA_COST_PRIEST = 15000; 
    
    
    
    // ===================================================================================================
    // array units costs  SWORDSMAN, SPEARMAN, CROSSBOW, CANNON, ARROWTOWER, CATAPULT, ROCKETLAUNCHERTOWER 
    // ===================================================================================================
    int[]  WOOD_COST_UNITS = {
    		WOOD_COST_SWORDSMAN,
    		WOOD_COST_SPEARMAN,
    		WOOD_COST_CROSSBOW,
    		WOOD_COST_CANNON,
    		WOOD_COST_ARROWTOWER,
    		WOOD_COST_CATAPULT,
    		WOOD_COST_ROCKETLAUNCHERTOWER,
    		WOOD_COST_MAGICIAN,
    		WOOD_COST_PRIEST
    	}; 
	int[] IRON_COST_UNITS = {
			IRON_COST_SWORDSMAN,
			IRON_COST_SPEARMAN,
			IRON_COST_CROSSBOW,
			IRON_COST_CANNON,
			IRON_COST_ARROWTOWER,
			IRON_COST_CATAPULT,
			IRON_COST_ROCKETLAUNCHERTOWER,
			IRON_COST_MAGICIAN,
			IRON_COST_PRIEST
		}; 
	int [] FOOD_COST_UNITS = {
			FOOD_COST_SWORDSMAN,
			FOOD_COST_SPEARMAN,
			FOOD_COST_CROSSBOW,
			FOOD_COST_CANNON,
			FOOD_COST_ARROWTOWER,
			FOOD_COST_CATAPULT,
			FOOD_COST_ROCKETLAUNCHERTOWER,
			FOOD_COST_MAGICIAN,
			FOOD_COST_PRIEST
		}; 
    
	// ===================
	//  EDIFICIOS DE COSTE
    // ===================
	 int  FOOD_COST_FARM = 5000; 
	 int  WOOD_COST_FARM = 10000; 
	 int  IRON_COST_FARM = 12000; 
	  
	 int  FOOD_COST_CARPENTRY = 5000; 
	 int  WOOD_COST_CARPENTRY = 10000; 
	 int  IRON_COST_CARPENTRY = 12000; 
	  
	 int  FOOD_COST_SMITHY = 5000; 
	 int  WOOD_COST_SMITHY = 10000; 
	 int  IRON_COST_SMITHY = 12000; 
	  
	 int  FOOD_COST_CHURCH = 5000; 
	 int  WOOD_COST_CHURCH = 10000; 
	 int  IRON_COST_CHURCH = 12000; 
	  
	 int  FOOD_COST_MAGICTOWER = 5000; 
	 int  WOOD_COST_MAGICTOWER = 10000; 
	 int  IROpN_COST_MAGICTOWER = 12000;
	 
	 
	// ================================
	//  UNIDADES DE ATAQUE DE DAÑO BASE
    // ================================
	 int BASE_DAMAGE_SWORDSMAN = 80; 
	 int BASE_DAMAGE_SPEARMAN = 150; 
	 int BASE_DAMAGE_CROSSBOW = 1000; 
	 int BASE_DAMAGE_CANNON = 700; 
	
	
	// ======================
	//  DEFENSAS DE DAÑO BASE
    // ======================
	 int BASE_DAMAGE_ARROWTOWER = 80; 
	 int BASE_DAMAGE_CATAPULT = 250; 
	 int BASE_DAMAGE_ROCKETLAUNCHERTOWER = 2000; 
	 int BASE_DAMAGE_MAGICIAN = 3000; 
	 
	 
	// ===============================
	//  UNIDADES DE ATAQUE DE ARMADURA
    // ===============================
	 int ARMOR_SWORDSMAN = 400; 
	 int ARMOR_SPEARMAN = 1000; 
	 int ARMOR_CROSSBOW = 6000; 
	 int ARMOR_CANNON = 8000; 
	 
	// =====================
	//  DEFENSAS DE ARMADURA
    // =====================
	 int ARMOR_ARROWTOWER = 200; 
	 int ARMOR_CATAPULT = 1200; 
	 int ARMOR_ROCKETLAUNCHERTOWER = 7000; 
	 
	 
	 
	// ================================================================================
	// PORCENTAJE DE AUMENTO DE ARMADURA DE LAS UNIDADES DE ATAQUE POR NIVEL TECNOLOGICO 
    // ================================================================================
	 int PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY = 5; 
	 int PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY = 5; 
	 int PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY = 5; 
	 int PLUS_ARMOR_CANNON_BY_TECHNOLOGY = 5;
	 
	 
	// ====================================================================
	// PORCENTAJE DE AUMENTO DE ARMADURA DE DEFENSA POR NIVEL DE TECNOLOGÍA 
    // ====================================================================
	 int PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY = 5; 
	 int PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY = 5; 
	 int PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 5; 
	 
	// =================================================================================
	// PORCENTAJE DE AUMENTO DE POODER DE LAS UNIDADES DE ATAQUE POR NIVEL DE TECNOLOGIA
    // =================================================================================
	 int PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY = 5; 
	 int PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY = 5; 
	 int PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY = 5; 
	 int PLUS_ATTACK_CANNON_BY_TECHNOLOGY = 5; 
	 
	 
	// ===========================================================================
	// PORCENTAJE DE AUMENTO DE POODER DE ATAQUE DEFENSIVO POR NIVEL DE TECNOLOGÍA
    // ===========================================================================
	 int PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY = 5; 
	 int PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY = 5; 
	 int PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY = 5; 
	  
	 int PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY  = 6; 
	  
	 int PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT = 4; 
	 int PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT = 4; 
	 
	 
	// =============================================================================
	// UNIDADES MAS AUMENTO DE PORCENTAJE DE ARMADURA/ATAQUE CUANDO SON SANTIFICADAS
    // =============================================================================
	 int PLUS_ARMOR_UNIT_SANCTIFIED = 7; 
	 int PLUS_ATTACK_UNIT_SANCTIFIED = 7; 
 
	// ====================================
	// POSIBILIDAD DE RESURRECCION POR MAGO
    // ====================================
	 int CHANCE_MAGICIAN_RESSURECT = 2; 
	 
	// ===========================================
	// POSIBILIDAD DE QUE LA FLOTA GENERE DESECHOS
    // ===========================================
	 int CHANCE_GENERATNG_WASTE_SWORDSMAN = 55; 
	 int CHANCE_GENERATNG_WASTE_SPEARMAN = 65; 
	 int CHANCE_GENERATNG_WASTE_CROSSBOW = 80; 
	 int CHANCE_GENERATNG_WASTE_CANNON = 90; 	
	 
	 
	// ======================================================================================
	// POSIBILIDAD DE DEFENSA DE GENERAR DESECHOS TORREFLECHA, CATAPULTA, TORREDELANZACOHETES
    // ======================================================================================
	 int CHANCE_GENERATNG_WASTE_ARROWTOWER = 55; 
	 int CHANCE_GENERATNG_WASTE_CATAPULT = 65; 
	 int CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER = 75; 
	 
	 
	 // ===================
	 // UNIDADES ESPECIALES
	 // ===================
	 int CHANCE_GENERATNG_WASTE_PRIEST = 0; 
	 int CHANCE_GENERATNG_WASTE_MAGICIAN = 0;
	 
	// ==========================================================
	 // PROBABILIDAD DE QUE LA UNIDAD DE ATAQUE ATAQUE NUEVAMENTE
	 // =========================================================
	 int CHANCE_ATTACK_AGAIN_SWORDSMAN = 3; 
	 int CHANCE_ATTACK_AGAIN_SPEARMAN = 7; 
	 int CHANCE_ATTACK_AGAIN_CROSSBOW = 45; 
	 int CHANCE_ATTACK_AGAIN_CANNON = 70; 
	 
	// ==================================================================================================
	 // PROBABILIDAD DE DEFENSA PARA ATACAR DE NUEVO PROBABILIDAD DE AttackUnit PARA ATACAR DE attackUnit
	 // =================================================================================================
	 int CHANCE_ATTACK_AGAIN_ARROWTOWER = 5; 
	 int CHANCE_ATTACK_AGAIN_CATAPULT = 12; 
	 int CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER = 30; 
	  
	 int CHANCE_ATTACK_AGAIN_MAGICIAN = 75; 
	 int CHANCE_ATTACK_AGAIN_PRIEST = 0; 
	 
	// ===================================
    // ATAQUE DE OPORTUNIDAD A CADA UNIDAD 
	// ===================================
	 
	// ==================================================================================================
	// SWORDSMAN, SPEARMAN, CROSSBOW, CANNON, ARROWTOWER, CATAPULT, ROCKETLAUNCHERTOWER, MAGICIAN, PRIEST 
	// ==================================================================================================
	 int[]  CHANCE_ATTACK_CIVILIZATION_UNITS = {4,9,13,37,4,9,14,10,0}; 
	 
	 
	// =====================================
    // SWORDSMAN, SPEARMAN, CROSSBOW, CANNON 
	// =====================================
	 int[]  CHANCE_ATTACK_ENEMY_UNITS = {10,20,30,40}; 
	 
	 
	// ============================================================================
	// PORCENTAJE DE DESECHOS QUE SE GENERARÁ CON RESPECTO AL COSTO DE LAS UNIDADES
	// ============================================================================
	 int PERCENTATGE_WASTE = 70; 
	 
}

