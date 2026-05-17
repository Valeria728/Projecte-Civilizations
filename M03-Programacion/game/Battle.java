package game;

import java.util.ArrayList;
import java.util.Random;

import interfaces.MilitaryUnit;
import interfaces.Variables;

public class Battle implements Variables {

    private ArrayList<MilitaryUnit> civilizationArmy;
    private ArrayList<MilitaryUnit> enemyArmy;

    // Matriz de ejércitos ejércitos[0] = civilización, ejércitos[1] = enemigo
    // Cada fila tiene 9 grupos (civilización) o 4 grupos (el enemigo usa de 0 a 3)
    private ArrayList<MilitaryUnit>[][] armies;

    private String battleDevelopment;

    // CosteInicialFlota[0] = {comida, madera, hierro} civilización
    // CosteInicialFlota[1] = {comida, madera, hierro} enemigo
    private int[][] initialCostFleet;

    private int initialNumberUnitsCivilization;
    private int initialNumberUnitsEnemy;

    // WasteWoodIron[0]=madera, WasteWoodIron[1]=hierro
    private int[] wasteWoodIron;

    private int enemyDrops;
    private int civilizationDrops;

    // RecursosPérdidas[0] = {alimentos, madera, hierro, ponderado} civilización
    // RecursosPérdidas[1] = {alimentos, madera, hierro, ponderado} enemigo
    private int[][] resourcesLooses;

    // initialArmies[0][0..8] = recuento inicial por grupo de civilización
    // initialArmies[1][0..3] = recuento inicial por grupo de enemigos
    private int[][] initialArmies;

    private int[] actualNumberUnitsCivilization;
    private int[] actualNumberUnitsEnemy;

    private int battleNumber;
    private boolean civilizationWon;

    private Random random; 

    /*nombre de unidades para mostrar en el reporte final, 
     se asignan a cada grupo por orden de creación.*/
    private String[] civUnitNames = {
        "Swordsman", "Spearman", "Crossbow", "Cannon",
        "Arrow Tower", "Catapult", "Rocket Launcher Tower",
        "Magician", "Priest"
    };
    private String[] enemyUnitNames = {"Swordsman", "Spearman", "Crossbow", "Cannon"};
    /* Constructor de la batalla, recibe los grupos 
     de unidades de ambos ejércitos y el número de batalla para registro.*/
    @SuppressWarnings("unchecked")
    public Battle(ArrayList<MilitaryUnit>[] civArmyGroups, ArrayList<MilitaryUnit>[] enemyArmyGroups, int battleNumber) {
        this.battleNumber = battleNumber;
        random = new Random();
        battleDevelopment = "";
        wasteWoodIron = new int[]{0, 0};
        civilizationDrops = 0;
        enemyDrops = 0;

        /*Crear listas planas a partir de grupos para 
         facilitar el manejo de unidades individuales.*/
        civilizationArmy = new ArrayList<MilitaryUnit>();
        enemyArmy = new ArrayList<MilitaryUnit>();

        armies = new ArrayList[2][9];
        int i = 0;
        while (i < 9) {
            armies[0][i] = new ArrayList<MilitaryUnit>();
            i++;
        }
        i = 0;
        while (i < 4) {
            armies[1][i] = new ArrayList<MilitaryUnit>();
            i++;
        }

        // Copiar unidades de los grupos a las listas planas y a la matriz de grupos,
        i = 0;
        while (i < 9) {
            int j = 0;
            while (j < civArmyGroups[i].size()) {
                MilitaryUnit unit = civArmyGroups[i].get(j);
                unit.resetArmor();
                armies[0][i].add(unit);
                civilizationArmy.add(unit);
                j++;
            }
            i++;
        }

        // Copiar unidades de los grupos a las listas planas y a la matriz de grupos,
        i = 0;
        while (i < 4) {
            int j = 0;
            while (j < enemyArmyGroups[i].size()) {
                MilitaryUnit unit = enemyArmyGroups[i].get(j);
                unit.resetArmor();
                armies[1][i].add(unit);
                enemyArmy.add(unit);
                j++;
            }
            i++;
        }

        initialArmies = new int[2][9];
        actualNumberUnitsCivilization = new int[9];
        actualNumberUnitsEnemy = new int[4];

        initInitialArmies();

        initialNumberUnitsCivilization = civilizationArmy.size();
        initialNumberUnitsEnemy = enemyArmy.size();

        initialCostFleet = new int[2][3];
        fleetResourceCost();

        resourcesLooses = new int[2][4];
    }

    // ---------------------------
    // AYUDANTES DE INICIALIZACIÓN
    // ---------------------------
    /* Inicializa los conteos iniciales 
    / de unidades por grupo, para luego poder calcular las pérdidas.*/
    private void initInitialArmies() {
        int i = 0;
        while (i < 9) {
            initialArmies[0][i] = armies[0][i].size();
            i++;
        }
        i = 0;
        while (i < 4) {
            initialArmies[1][i] = armies[1][i].size();
            i++;
        }
        updateActualNumbers();
    }
    /* Actualiza los números actuales de unidades por grupo, 
     para luego poder calcular las pérdidas.*/
    private void updateActualNumbers() {
        int i = 0;
        while (i < 9) {
            actualNumberUnitsCivilization[i] = armies[0][i].size();
            i++;
        }
        i = 0;
        while (i < 4) {
            actualNumberUnitsEnemy[i] = armies[1][i].size();
            i++;
        }
    }
    /* Calcula el coste total de la flota al inicio de la batalla, 
     para luego poder calcular las pérdidas totales en caso de destrucción total.*/
    private void fleetResourceCost() {
        // Civilizacion
        int food = 0, wood = 0, iron = 0;
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < initialArmies[0][i]) {
                // Utiliza un costo unitario representativo (todos del mismo tipo, mismo costo)
                if (armies[0][i].size() > 0) {
                    MilitaryUnit u = armies[0][i].get(0);
                    food = food + u.getFoodCost();
                    wood = wood + u.getWoodCost();
                    iron = iron + u.getIronCost();
                }
                j++;
            }
            i++;
        }
        initialCostFleet[0][0] = food;
        initialCostFleet[0][1] = wood;
        initialCostFleet[0][2] = iron;

        // Enemigo
        food = 0; wood = 0; iron = 0;
        i = 0;
        while (i < 4) {
            int j = 0;
            while (j < initialArmies[1][i]) {
                if (armies[1][i].size() > 0) {
                    MilitaryUnit u = armies[1][i].get(0);
                    food = food + u.getFoodCost();
                    wood = wood + u.getWoodCost();
                    iron = iron + u.getIronCost();
                }
                j++;
            }
            i++;
        }
        initialCostFleet[1][0] = food;
        initialCostFleet[1][1] = wood;
        initialCostFleet[1][2] = iron;
    }

    // --------------------------------
    // ALGORITMO DE SELECCIÓN DE GRUPOS
    // --------------------------------

    // Seleccionar grupo atacante por matriz de probabilidad,
    private int getCivilizationGroupAttacker() {
        int total = 0;
        int i = 0;
        while (i < CHANCE_ATTACK_CIVILIZATION_UNITS.length) {
            if (armies[0][i].size() > 0) {
                total = total + CHANCE_ATTACK_CIVILIZATION_UNITS[i];
            }
            i++;
        }
        if (total == 0) return -1;
        int rnd = random.nextInt(total) + 1;
        int accumulated = 0;
        i = 0;
        while (i < CHANCE_ATTACK_CIVILIZATION_UNITS.length) {
            if (armies[0][i].size() > 0) {
                accumulated = accumulated + CHANCE_ATTACK_CIVILIZATION_UNITS[i];
                if (accumulated >= rnd) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
    /* Seleccionar grupo atacante por matriz de probabilidad, 
    solo entre los grupos que tengan unidades disponibles.*/
    private int getEnemyGroupAttacker() {
        int total = 0;
        int i = 0;
        while (i < CHANCE_ATTACK_ENEMY_UNITS.length) {
            if (armies[1][i].size() > 0) {
                total = total + CHANCE_ATTACK_ENEMY_UNITS[i];
            }
            i++;
        }
        if (total == 0) return -1;
        int rnd = random.nextInt(total) + 1;
        int accumulated = 0;
        i = 0;
        while (i < CHANCE_ATTACK_ENEMY_UNITS.length) {
            if (armies[1][i].size() > 0) {
                accumulated = accumulated + CHANCE_ATTACK_ENEMY_UNITS[i];
                if (accumulated >= rnd) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    // Seleccionar el grupo defensor en función de la proporcionalidad del número de unidades.
    private int getGroupDefender(boolean isCivilization) {
        int groups = isCivilization ? 9 : 4;
        ArrayList<MilitaryUnit>[] targetArmy = isCivilization ? armies[0] : armies[1];

        int total = 0;
        int i = 0;
        while (i < groups) {
            total = total + targetArmy[i].size();
            i++;
        }
        if (total == 0) return -1;

        int rnd = random.nextInt(total) + 1;
        int accumulated = 0;
        i = 0;
        while (i < groups) {
            accumulated = accumulated + targetArmy[i].size();
            if (accumulated >= rnd) {
                return i;
            }
            i++;
        }
        return -1;
    }

    // -------------------------
    // PRCENTAJE DE BATALLA
    // -------------------------
    /* Calcula el porcentaje de unidades 
     restantes en comparación con el inicio de la batalla.*/
    private int remainderPercentageCivilization() {
        if (initialNumberUnitsCivilization == 0) return 0;
        return (civilizationArmy.size() * 100) / initialNumberUnitsCivilization;
    }
    /* Calcula el porcentaje de unidades enemigas 
     restantes en comparación con el inicio de la batalla.*/
    private int remainderPercentageEnemy() {
        if (initialNumberUnitsEnemy == 0) return 0;
        return (enemyArmy.size() * 100) / initialNumberUnitsEnemy;
    }

    // -------------------------
    // GENERACIÓN DE RESIDUOS
    // -------------------------
    /* Cada vez que una unidad muere, se intenta generar residuos 
    según su probabilidad.*/
    private void tryGenerateWaste(MilitaryUnit unit) {
        int chance = unit.getChanceGeneratingWaste();
        if (chance == 0) return;
        int rnd = random.nextInt(100) + 1;
        if (rnd <= chance) {
            int woodWaste = unit.getWoodCost() * PERCENTATGE_WASTE / 100;
            int ironWaste = unit.getIronCost() * PERCENTATGE_WASTE / 100;
            wasteWoodIron[0] = wasteWoodIron[0] + woodWaste;
            wasteWoodIron[1] = wasteWoodIron[1] + ironWaste;
        }
    }

    // -------------------------
    //REALIZAR UN ATAQUE
    // -------------------------

    
    /* attackerIsCivilization: verdadero = la civilización ataca al enemigo, 
    falso = el enemigo ataca a la civilización.*/
    private void performAttack(boolean attackerIsCivilization) {
        int attackerGroup;
        ArrayList<MilitaryUnit>[] attackingArmy;
        ArrayList<MilitaryUnit>[] defendingArmy;
        int defenderGroups;
        String attackerSide;
        String defenderSide;

        if (attackerIsCivilization) {
            attackerGroup = getCivilizationGroupAttacker();
            attackingArmy = armies[0];
            defendingArmy = armies[1];
            defenderGroups = 4;
            attackerSide = "Civilization";
            defenderSide = "Enemy";
        } else {
            attackerGroup = getEnemyGroupAttacker();
            attackingArmy = armies[1];
            defendingArmy = armies[0];
            defenderGroups = 9;
            attackerSide = "Enemy";
            defenderSide = "Civilization";
        }

        if (attackerGroup == -1 || attackingArmy[attackerGroup].isEmpty()) return;

        // Elige una unidad atacante al azar del grupo.
        int attackerIndex = random.nextInt(attackingArmy[attackerGroup].size());
        MilitaryUnit attacker = attackingArmy[attackerGroup].get(attackerIndex);

        boolean keepAttacking = true;
        while (keepAttacking) {
            /* Elegir grupo defensor basado en la proporción de unidades restantes, 
             para simular que se defienden más las áreas con más unidades*/
            int defenderGroup = getGroupDefender(!attackerIsCivilization);
            if (defenderGroup == -1 || defendingArmy[defenderGroup].isEmpty()) break;

            // Elige una unidad defensora aleatoria
            int defenderIndex = random.nextInt(defendingArmy[defenderGroup].size());
            MilitaryUnit defender = defendingArmy[defenderGroup].get(defenderIndex);

            int damage = attacker.attack();
            defender.takeDamage(damage);

            String attackerName = attacker.toString();
            String defenderName = defender.toString();

            battleDevelopment = battleDevelopment
                + "Attacks " + attackerSide + ": " + attackerName + " attacks " + defenderName + "\n"
                + attackerName + " generates damage = " + damage + "\n"
                + defenderName + " stays with armor = " + defender.getActualArmor() + "\n";

            // comprobar si la defensa a muerto.
            if (defender.getActualArmor() <= 0) {
                battleDevelopment = battleDevelopment + "We eliminate " + defenderName + "\n";
                tryGenerateWaste(defender);

                // Eliminar del grupo
                defendingArmy[defenderGroup].remove(defenderIndex);
                // Eliminar de la lista plana y contar caídas
                if (attackerIsCivilization) {
                    enemyArmy.remove(defender);
                    enemyDrops++;
                } else {
                    civilizationArmy.remove(defender);
                    civilizationDrops++;
                }
                updateActualNumbers();
            }

            // Comprobamos si el atacante puede atacar otra vez.
            int chanceAgain = attacker.getChanceAttackAgain();
            int rnd = random.nextInt(100) + 1;
            if (rnd <= chanceAgain) {
                // Comprueba si el ejercito defensor tiene unidades.
                int totalDefenders = 0;
                int k = 0;
                while (k < defenderGroups) {
                    totalDefenders = totalDefenders + defendingArmy[k].size();
                    k++;
                }
                if (totalDefenders > 0) {
                    keepAttacking = true;
                } else {
                    keepAttacking = false;
                }
            } else {
                keepAttacking = false;
            }
        }
    }

    // -------------------------
    // Comienza la batalla.
    // -------------------------

    public void runBattle() {
        /* Decide cual ejército ataca primero, 
         se mantiene el turno de ataque hasta que se acabe la batalla.*/
        boolean civilizationAttacks = random.nextBoolean();

        battleDevelopment = battleDevelopment + "********************BATTLE START********************\n";

        while (remainderPercentageCivilization() > BATTLE_END_PERCENTAGE
            && remainderPercentageEnemy() > BATTLE_END_PERCENTAGE) {

            battleDevelopment = battleDevelopment + "********************CHANGE ATTACKER********************\n";

            performAttack(civilizationAttacks);

            // Intercambio de atacante.
            civilizationAttacks = !civilizationAttacks;
        }

        // Calcular perdidas en recursos, para mostrar en el reporte final.
        updateResourcesLooses();

        /* El ganador se decide por la pérdida total ponderada, 
         no solo por quién queda con más unidades.*/  
        int civLoss = resourcesLooses[0][3];
        int enemyLoss = resourcesLooses[1][3];
        civilizationWon = civLoss <= enemyLoss;

        battleDevelopment = battleDevelopment + "********************BATTLE END********************\n";
    }

    // -------------------------
    // actualizacion perdidas.
    // -------------------------

    private void updateResourcesLooses() {
        // perdidas en la civilizacion.
        int civFoodLoss = 0, civWoodLoss = 0, civIronLoss = 0;
        int i = 0;
        while (i < 9) {
            int droppedInGroup = initialArmies[0][i] - actualNumberUnitsCivilization[i];
            if (droppedInGroup > 0 && armies[0][i].size() > 0) {
                MilitaryUnit sample = armies[0][i].get(0);
                civFoodLoss = civFoodLoss + droppedInGroup * sample.getFoodCost();
                civWoodLoss = civWoodLoss + droppedInGroup * sample.getWoodCost();
                civIronLoss = civIronLoss + droppedInGroup * sample.getIronCost();
            }
            i++;
        }
        /* Si la civilización pierde toda la flota, se le asigna el coste total 
         de la flota como pérdida, para reflejar la destrucción total.*/
        if (actualNumberUnitsCivilization[0] == 0 && initialArmies[0][0] > 0) {
            civFoodLoss = initialCostFleet[0][0];
            civWoodLoss = initialCostFleet[0][1];
            civIronLoss = initialCostFleet[0][2];
        }
        resourcesLooses[0][0] = civFoodLoss;
        resourcesLooses[0][1] = civWoodLoss;
        resourcesLooses[0][2] = civIronLoss;
        resourcesLooses[0][3] = civIronLoss + civWoodLoss / 5 + civFoodLoss / 10;

        // perdidas en el enemigo.
        int enFoodLoss = 0, enWoodLoss = 0, enIronLoss = 0;
        i = 0;
        while (i < 4) {
            int droppedInGroup = initialArmies[1][i] - actualNumberUnitsEnemy[i];
            if (droppedInGroup > 0 && armies[1][i].size() > 0) {
                MilitaryUnit sample = armies[1][i].get(0);
                enFoodLoss = enFoodLoss + droppedInGroup * sample.getFoodCost();
                enWoodLoss = enWoodLoss + droppedInGroup * sample.getWoodCost();
                enIronLoss = enIronLoss + droppedInGroup * sample.getIronCost();
            }
            i++;
        }
        resourcesLooses[1][0] = enFoodLoss;
        resourcesLooses[1][1] = enWoodLoss;
        resourcesLooses[1][2] = enIronLoss;
        resourcesLooses[1][3] = enIronLoss + enWoodLoss / 5 + enFoodLoss / 10;
    }

    // -------------------------
    // GETTERS
    // -------------------------
    
    public boolean isCivilizationWon() { 
        return civilizationWon; 
    }
    public int[] getWasteWoodIron() { 
        return wasteWoodIron; 
    }
    public int getBattleNumber() { 
        return battleNumber; 
    }

    public String getBattleDevelopment() {
        return battleDevelopment;
    }

    public String getBattleReport() {
        String report = "\nBATTLE NUMBER: " + battleNumber + "\n";
        report = report + "BATTLE STATISTICS\n";
        report = report + String.format("%-30s %-10s %-10s %-30s %-10s %-10s%n",
            "Army Civilization Units", "Initial", "Drops",
            "Army Enemy Units", "Initial", "Drops");

        /* Imprimimos ambos ejércitos lado a lado, iterando hasta el máximo número 
        de grupos (9) y mostrando solo los datos disponibles para cada uno.*/ 
        int maxRows = 9;
        int i = 0;
        while (i < maxRows) {
            String civLine = "";
            String enemyLine = "";

            if (i < 9) {
                civLine = civUnitNames[i] + ": " + initialArmies[0][i] + " initial / " + (initialArmies[0][i] - actualNumberUnitsCivilization[i]) + " drops";
            }
            if (i < 4) {
                enemyLine = enemyUnitNames[i] + ": " + initialArmies[1][i] + " initial / " + (initialArmies[1][i] - actualNumberUnitsEnemy[i]) + " drops";
            }
            report = report + String.format("  %-45s %-45s%n", civLine, enemyLine);
            i++;
        }
        // mostramos los recursos en costes y perdidas.
        report = report + "**************************************************************************************\n";
        report = report + String.format("  %-35s %-35s%n", "Cost Army Civilization", "Cost Army Enemy");
        report = report + String.format("  Food:  %-28s Food:  %-28s%n", initialCostFleet[0][0], initialCostFleet[1][0]);
        report = report + String.format("  Wood:  %-28s Wood:  %-28s%n", initialCostFleet[0][1], initialCostFleet[1][1]);
        report = report + String.format("  Iron:  %-28s Iron:  %-28s%n", initialCostFleet[0][2], initialCostFleet[1][2]);
        report = report + "**************************************************************************************\n";
        report = report + String.format("  %-35s %-35s%n", "Losses Army Civilization", "Losses Army Enemy");
        report = report + String.format("  Food:  %-28s Food:  %-28s%n", resourcesLooses[0][0], resourcesLooses[1][0]);
        report = report + String.format("  Wood:  %-28s Wood:  %-28s%n", resourcesLooses[0][1], resourcesLooses[1][1]);
        report = report + String.format("  Iron:  %-28s Iron:  %-28s%n", resourcesLooses[0][2], resourcesLooses[1][2]);
        report = report + "**************************************************************************************\n";
        report = report + "Waste Generated:\n";
        report = report + "  Wood: " + wasteWoodIron[0] + "\n";
        report = report + "  Iron: " + wasteWoodIron[1] + "\n";

        if (civilizationWon) {
            report = report + "Battle Won by Civilization, We Collect Rubble\n";
        } else {
            report = report + "Battle Won by Enemy\n";
        }
        report = report + "##########################################################################\n";
        return report;
    }

    /* Despues de la batalla se pueden consultar 
     los grupos sobrevivientes de la civilización para mostrar en el panel.*/
    public ArrayList<MilitaryUnit>[] getSurvivingCivilizationGroups() {
        return armies[0];
    }
 // --- MÉTODOS DE COMPATIBILIDAD PARA BATTLEDAO Y MAINGUI ---

    public int getWasteWood() { 
        return wasteWoodIron[0]; 
    }

    public int getWasteIron() { 
        return wasteWoodIron[1]; 
    }

    public String getWinner() { 
        return civilizationWon ? "Civilization" : "Enemy"; 
    }

    public int[][] getInitialArmies() { 
        return initialArmies; 
    }

    public int[][] getDrops() { 
        int[][] drops = new int[2][9];
        // Calculamos las bajas de la civilización
        for (int i = 0; i < 9; i++) {
            drops[0][i] = initialArmies[0][i] - actualNumberUnitsCivilization[i];
        }
        // Calculamos las bajas del enemigo
        for (int i = 0; i < 4; i++) {
            drops[1][i] = initialArmies[1][i] - actualNumberUnitsEnemy[i];
        }
        return drops; 
    }

    // Este es el "alias": cuando alguien pida startBattle, se ejecutará runBattle
    public void startBattle() {
        runBattle();
    }

    // Adaptación para que el reporte acepte el número de batalla que envía el DAO
    public String getBattleReport(int num) {
        this.battleNumber = num;
        return getBattleReport();
    }
}


