package game;

import java.util.ArrayList;
import java.util.Random;

import interfaces.MilitaryUnit;
import interfaces.Variables;

public class Battle implements Variables {
    private ArrayList<MilitaryUnit>[] civilizationArmy;
    private ArrayList<MilitaryUnit>[] enemyArmy;
    private ArrayList<MilitaryUnit>[][] armies; // Cambiado a matriz para evitar errores de tipo
    
    private String battleDevelopment = "";
    // Se eliminan variables no usadas para evitar warnings (resourcesLooses)
    private int[] initialUnits = new int[2];
    private int[] wasteWoodIron = new int[2]; // Residuos generados (0: Wood, 1: Iron)

    @SuppressWarnings("unchecked")
    public Battle(ArrayList<MilitaryUnit>[] civArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
        this.civilizationArmy = civArmy;
        this.enemyArmy = enemyArmy;
        
        // Inicialización correcta de la matriz de ejércitos
        this.armies = new ArrayList[2][9];
        this.armies[0] = this.civilizationArmy;
        this.armies[1] = this.enemyArmy;
        
        this.initialUnits[0] = countUnits(this.civilizationArmy);
        this.initialUnits[1] = countUnits(this.enemyArmy);
    }

    public String startBattle() {
        Random rand = new Random();
        int turn = rand.nextInt(2); // 0 Civ, 1 Enemigo
        
        battleDevelopment += "¡COMIENZA LA BATALLA!\n";

        // Bucle de batalla (hasta que un bando pierde el 80% o se queda sin tropas)
        while (countUnits(armies[0]) > (initialUnits[0] * 0.2) && 
               countUnits(armies[1]) > (initialUnits[1] * 0.2) &&
               countUnits(armies[0]) > 0 && countUnits(armies[1]) > 0) {
            
            int attackerSide = turn;
            int defenderSide = (turn == 0) ? 1 : 0;
            
            executeTurn(attackerSide, defenderSide);
            
            turn = (turn == 0) ? 1 : 0;
        }

        return getBattleReport();
    }

    private void executeTurn(int atkSide, int defSide) {
        Random rand = new Random();
        
        int[] chances = (atkSide == 0) ? CHANCE_ATTACK_CIVILIZATION_UNITS : CHANCE_ATTACK_ENEMY_UNITS;
        int groupAtk = selectGroup(chances, armies[atkSide]);
        
        if (groupAtk == -1) return;

        MilitaryUnit unitAtk = armies[atkSide][groupAtk].get(rand.nextInt(armies[atkSide][groupAtk].size()));

        int groupDef = selectDefenderGroupByWeight(armies[defSide]);
        if (groupDef == -1) return;

        MilitaryUnit unitDef = armies[defSide][groupDef].get(rand.nextInt(armies[defSide][groupDef].size()));

        // Aplicar daño
        int damage = unitAtk.attack();
        unitDef.takeDamage(damage);
        
        // Comprobar si muere
        if (unitDef.getActualArmor() <= 0) {
            generateWaste(unitDef);
            armies[defSide][groupDef].remove(unitDef);
        }
    }

    private int selectGroup(int[] chances, ArrayList<MilitaryUnit>[] army) {
        Random rand = new Random();
        int roll = rand.nextInt(100);
        int accum = 0;
        for (int i = 0; i < chances.length; i++) {
            accum += chances[i];
            if (roll < accum && i < army.length && !army[i].isEmpty()) {
                return i;
            }
        }
        // Buscar el primer grupo disponible si falla la probabilidad
        for (int i = 0; i < army.length; i++) {
            if (!army[i].isEmpty()) return i;
        }
        return -1;
    }

    private int selectDefenderGroupByWeight(ArrayList<MilitaryUnit>[] army) {
        int total = countUnits(army);
        if (total == 0) return -1;
        
        Random rand = new Random();
        int roll = rand.nextInt(total);
        int accum = 0;
        for (int i = 0; i < army.length; i++) {
            accum += army[i].size();
            if (roll < accum) return i;
        }
        return -1;
    }

    private void generateWaste(MilitaryUnit unit) {
        Random rand = new Random();
        if (rand.nextInt(100) < unit.getChanceGeneratinWaste()) {
            wasteWoodIron[0] += (unit.getWoodCost() * PERCENTATGE_WASTE) / 100;
            wasteWoodIron[1] += (unit.getIronCost() * PERCENTATGE_WASTE) / 100;
        }
    }

    private int countUnits(ArrayList<MilitaryUnit>[] army) {
        int count = 0;
        if (army == null) return 0;
        for (ArrayList<MilitaryUnit> group : army) {
            if (group != null) count += group.size();
        }
        return count;
    }

    public String getBattleReport() {
        return "--- REPORTE DE BATALLA ---\n" + 
               "Unidades finales Civ: " + countUnits(armies[0]) + "\n" +
               "Unidades finales Enemigo: " + countUnits(armies[1]) + "\n" +
               "Residuos Madera: " + wasteWoodIron[0] + ", Hierro: " + wasteWoodIron[1] + "\n";
    }

    public String getBattleDevelopment() { return battleDevelopment; }
    public int[] getWaste() { return wasteWoodIron; }
}
