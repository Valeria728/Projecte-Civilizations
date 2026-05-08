package Civilization;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main implements Variables {
    // Variables estáticas para la civilización y el enemigo
    private static Civilization myCiv = new Civilization();
    private static ArrayList<String> battleHistory = new ArrayList<>();
    private static int enemyFleetCount = 1;
    
    // Recursos iniciales del enemigo (según el PDF para que pueda comprar tropas)
    private static int currentEnemyWood = 15000;
    private static int currentEnemyIron = 8000;
    private static int currentEnemyFood = 12000;
    
    private static ArrayList<MilitaryUnit>[] nextEnemyArmy;

    public static void main(String[] args) {
        CivilizationDAO dao = new CivilizationDAO();
        dao.loadGame(myCiv);
        
        Scanner sc = new Scanner(System.in);
        Timer timer = new Timer();

        // Generamos la primera amenaza al empezar
        nextEnemyArmy = createEnemyArmy();

        // TAREA 1: Generar recursos cada minuto
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                myCiv.generateResourcesNextMinute();
                System.out.println("\n[SISTEMA] Recursos generados.");
            }
        }, 60000, 60000);

        // TAREA 2: Batalla cada 3 minutos
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n[ALERTA] ¡La flota enemiga nº " + enemyFleetCount + " ha llegado!");
                
                Battle battle = new Battle(myCiv.getArmy(), nextEnemyArmy);
                String report = battle.startBattle();
                
                // Guardar en historial (máximo 5)
                battleHistory.add(0, "Batalla #" + enemyFleetCount + ":\n" + report);
                if (battleHistory.size() > 5) {
                    battleHistory.remove(5);
                }
                
                System.out.println(report);
                
                // Recoger escombros y sumarlos a nuestra civilización
                int[] waste = battle.getWaste();
                myCiv.setWood(myCiv.getWood() + waste[0]);
                myCiv.setIron(myCiv.getIron() + waste[1]);
                
                // Escalar dificultad para la próxima flota (aumentar recursos un 6%)
                currentEnemyWood += (currentEnemyWood * 6) / 100;
                currentEnemyIron += (currentEnemyIron * 6) / 100;
                currentEnemyFood += (currentEnemyFood * 6) / 100;
                
                enemyFleetCount++;
                nextEnemyArmy = createEnemyArmy(); 
            }
        }, 180000, 180000);

        int opc = 0;

        // Bucle Principal
        while (opc != 10) {
            System.out.println("\n================================");
            System.out.println("   CIVILIZATIONS CURS 25-26");
            System.out.println("================================");
            System.out.println("Madera: " + myCiv.getWood() + " | Hierro: " + myCiv.getIron());
            System.out.println("Comida: " + myCiv.getFood() + " | Maná: " + myCiv.getMana());
            System.out.println("--------------------------------");
            System.out.println("1. Reclutar Unidades Ataque");
            System.out.println("2. Reclutar Unidades Defensa");
            System.out.println("3. Reclutar Unidades Especiales");
            System.out.println("4. Gestionar Edificios");
            System.out.println("5. Mejorar Tecnologías");
            System.out.println("6. Ver Reporte de Ejército");
            System.out.println("7. Ver Historial Batallas");
            System.out.println("8. Ver Amenaza Inminente");
            System.out.println("9. Guardar Partida");
            System.out.println("10. Salir");
            System.out.print("Selección: ");

            try {
                opc = sc.nextInt();

                if (opc == 1) { // ATAQUE
                    System.out.println("1.Swordsman 2.Spearman 3.Crossbow 4.Cannon");
                    int sel = sc.nextInt();
                    System.out.print("Cantidad: ");
                    int cant = sc.nextInt();
                    if (sel == 1) myCiv.newSwordsman(cant);
                    else if (sel == 2) myCiv.newSpearman(cant);
                    else if (sel == 3) myCiv.newCrossbow(cant);
                    else if (sel == 4) myCiv.newCannon(cant);

                } else if (opc == 2) { // DEFENSA
                    System.out.println("1.Arrow Tower 2.Catapult 3.Rocket Launcher");
                    int sel = sc.nextInt();
                    System.out.print("Cantidad: ");
                    int cant = sc.nextInt();
                    if (sel == 1) myCiv.newArrowTower(cant);
                    else if (sel == 2) myCiv.newCatapult(cant);
                    else if (sel == 3) myCiv.newRocketLauncher(cant);

                } else if (opc == 3) { // ESPECIALES
                    System.out.println("1.Magician (Torre) 2.Priest (Iglesia)");
                    int sel = sc.nextInt();
                    System.out.print("Cantidad: ");
                    int cant = sc.nextInt();
                    if (sel == 1) myCiv.newMagician(cant);
                    else if (sel == 2) myCiv.newPriest(cant);

                } else if (opc == 4) { // EDIFICIOS
                    System.out.println("1.Granja 2.Herrería 3.Carpintería 4.Torre 5.Iglesia");
                    int sel = sc.nextInt();
                    if (sel == 1) myCiv.buildFarm();
                    else if (sel == 2) myCiv.buildSmithy();
                    else if (sel == 3) myCiv.buildCarpentry();
                    else if (sel == 4) myCiv.buildMagicTower();
                    else if (sel == 5) myCiv.buildChurch();

                } else if (opc == 5) { // TECNOLOGÍA
                    System.out.println("1.Ataque 2.Defensa");
                    int sel = sc.nextInt();
                    if (sel == 1) myCiv.upgradeTechnologyAttack();
                    else if (sel == 2) myCiv.upgradeTechnologyDefense();

                } else if (opc == 6) { // REPORTE EJERCITO
                    printArmyStats();

                } else if (opc == 7) { // HISTORIAL
                    int i = 0;
                    if (battleHistory.isEmpty()) System.out.println("Sin registros.");
                    while (i < battleHistory.size()) {
                        System.out.println(battleHistory.get(i));
                        i++;
                    }

                } else if (opc == 8) { // AMENAZA
                    viewThreat();

                } else if (opc == 9) { // GUARDAR
                    dao.saveGame(myCiv);
                    System.out.println("Partida guardada.");

                } else if (opc == 10) { // SALIR
                    dao.saveGame(myCiv);
                    System.out.println("Cerrando...");
                }

            } catch (ResourceException e) {
                System.err.println("RECURSOS: " + e.getMessage());
            } catch (BuildingException e) {
                System.err.println("EDIFICIOS: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error en entrada.");
                sc.next();
            }
        } // Fin while

        timer.cancel();
        sc.close();
        System.exit(0);
    }

    private static void viewThreat() {
        int total = 0;
        int i = 0;
        while (i < nextEnemyArmy.length) {
            total += nextEnemyArmy[i].size();
            i++;
        }
        System.out.println("--- AMENAZA: Vienen " + total + " unidades enemigas ---");
    }

    private static void printArmyStats() {
        ArrayList<MilitaryUnit>[] army = myCiv.getArmy();
        String[] nombres = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher", "Magician", "Priest"};
        System.out.println("\n--- ESTADO DEL EJÉRCITO ---");
        int i = 0;
        while (i < army.length) {
            if (army[i].size() > 0) {
                System.out.println(nombres[i] + ": " + army[i].size());
            }
            i++;
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<MilitaryUnit>[] createEnemyArmy() {
        ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[9];
        int i = 0;
        while (i < 9) {
            enemyArmy[i] = new ArrayList<MilitaryUnit>();
            i++;
        }
        
        int wood = currentEnemyWood;
        int iron = currentEnemyIron;
        int food = currentEnemyFood;

        // El enemigo gasta sus recursos en unidades de ataque (de mejor a peor)
        while (iron >= IRON_COST_CANNON && wood >= WOOD_COST_CANNON) {
            enemyArmy[3].add(new Cannon());
            iron -= IRON_COST_CANNON; wood -= WOOD_COST_CANNON;
        }
        while (iron >= IRON_COST_CROSSBOW && wood >= WOOD_COST_CROSSBOW) {
            enemyArmy[2].add(new Crossbow());
            iron -= IRON_COST_CROSSBOW; wood -= WOOD_COST_CROSSBOW;
        }
        while (food >= FOOD_COST_SPEARMAN && wood >= WOOD_COST_SPEARMAN) {
            enemyArmy[1].add(new Spearman());
            food -= FOOD_COST_SPEARMAN; wood -= WOOD_COST_SPEARMAN;
        }
        while (food >= FOOD_COST_SWORDSMAN && wood >= WOOD_COST_SWORDSMAN) {
            enemyArmy[0].add(new Swordsman());
            food -= FOOD_COST_SWORDSMAN; wood -= WOOD_COST_SWORDSMAN;
        }
        return enemyArmy;
    }
}