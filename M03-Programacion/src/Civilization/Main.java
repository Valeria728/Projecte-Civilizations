package Civilization;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main implements Variables {
    private static Civilization myCiv = new Civilization();
    private static ArrayList<String> battleHistory = new ArrayList<>();
    private static int enemyFleetCount = 1;
    
    private static int currentEnemyWood = 15000;
    private static int currentEnemyIron = 8000;
    private static int currentEnemyFood = 12000;
    
    private static ArrayList<MilitaryUnit>[] nextEnemyArmy;

    public static void main(String[] args) {
        CivilizationDAO dao = new CivilizationDAO();
        dao.loadGame(myCiv);
        
        Scanner sc = new Scanner(System.in);
        Timer timer = new Timer();

        nextEnemyArmy = createEnemyArmy();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                myCiv.generateResourcesNextMinute();
                System.out.println("\n[SISTEMA] Recursos generados.");
            }
        }, 60000, 60000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n[ALERTA] ¡La flota enemiga nº " + enemyFleetCount + " ha llegado!");
                Battle battle = new Battle(myCiv.getArmy(), nextEnemyArmy);
                String report = battle.startBattle();
                
                battleHistory.add(0, "Batalla #" + enemyFleetCount + ":\n" + report);
                if (battleHistory.size() > 5) battleHistory.remove(5);
                
                System.out.println(report);
                
                int[] waste = battle.getWaste();
                myCiv.setWood(myCiv.getWood() + waste[0]);
                myCiv.setIron(myCiv.getIron() + waste[1]);
                
                currentEnemyWood += (currentEnemyWood * 6) / 100;
                currentEnemyIron += (currentEnemyIron * 6) / 100;
                currentEnemyFood += (currentEnemyFood * 6) / 100;
                
                enemyFleetCount++;
                nextEnemyArmy = createEnemyArmy(); 
            }
        }, 180000, 180000);

        int opc = 0;

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
            System.out.println("6. Ver Reporte de Ejército (FICHA)");
            System.out.println("7. Ver Historial Batallas");
            System.out.println("8. Ver Amenaza Inminente");
            System.out.println("9. Guardar Partida");
            System.out.println("10. Salir");
            System.out.print("Selección: ");

            try {
                opc = sc.nextInt();

                if (opc == 1) { // ATAQUE CORREGIDO
                    System.out.println("1.Swordsman 2.Spearman 3.Crossbow 4.Cannon");
                    int sel = sc.nextInt();
                    
                    if (sel == 1) System.out.println("[COSTE] Madera:" + WOOD_COST_SWORDSMAN + " Hierro:" + IRON_COST_SWORDSMAN + " Comida:" + FOOD_COST_SWORDSMAN);
                    else if (sel == 2) System.out.println("[COSTE] Madera:" + WOOD_COST_SPEARMAN + " Hierro:" + IRON_COST_SPEARMAN + " Comida:" + FOOD_COST_SPEARMAN);
                    else if (sel == 3) System.out.println("[COSTE] Madera:" + WOOD_COST_CROSSBOW + " Hierro:" + IRON_COST_CROSSBOW);
                    else if (sel == 4) System.out.println("[COSTE] Madera:" + WOOD_COST_CANNON + " Hierro:" + IRON_COST_CANNON);
                    
                    System.out.print("¿Cuántos quieres?: ");
                    int cant = sc.nextInt();
                    
                    // AQUÍ USAMOS LA VARIABLE 'cant', esto quita el error amarillo
                    if (sel == 1) myCiv.newSwordsman(cant);
                    else if (sel == 2) myCiv.newSpearman(cant);
                    else if (sel == 3) myCiv.newCrossbow(cant);
                    else if (sel == 4) myCiv.newCannon(cant);

                } 
                else if (opc == 2) { // DEFENSA - Ajustado según pág. 13
                    System.out.println("\n--- RECLUTAR DEFENSA ---");
                    System.out.println("1. Arrow Tower     (" + WOOD_COST_ARROWTOWER + "W / " + IRON_COST_ARROWTOWER + "I)");
                    System.out.println("2. Catapult        (" + WOOD_COST_CATAPULT + "W / " + IRON_COST_CATAPULT + "I)");
                    System.out.println("3. Rocket Launcher (" + WOOD_COST_ROCKETLAUNCHERTOWER + "W / " + IRON_COST_ROCKETLAUNCHERTOWER + "I)");
                    System.out.println("4. Volver");
                    System.out.print("Selección: ");
                    int sel = sc.nextInt();
                    
                    if (sel != 4) {
                        System.out.print("Cantidad: ");
                        int cant = sc.nextInt();
                        if (sel == 1) myCiv.newArrowTower(cant);
                        else if (sel == 2) myCiv.newCatapult(cant);
                        else if (sel == 3) myCiv.newRocketLauncher(cant);
                    }
                }
                else if (opc == 3) { // ESPECIALES - Ajustado según pág. 5
                    System.out.println("\n--- UNIDADES ESPECIALES ---");
                    System.out.println("1. Magician (" + MANA_COST_MAGICIAN + " Maná) -> Requiere Torre Mágica");
                    System.out.println("2. Priest   (" + MANA_COST_PRIEST + " Maná) -> Requiere Iglesia");
                    System.out.println("3. Volver");
                    System.out.print("Selección: ");
                    int sel = sc.nextInt();

                    if (sel != 3) {
                        System.out.print("Cantidad: ");
                        int cant = sc.nextInt();
                        if (sel == 1) myCiv.newMagician(cant);
                        else if (sel == 2) myCiv.newPriest(cant);
                    }
                }
                else if (opc == 4) { // GESTIÓN DE EDIFICIOS 
                    System.out.println("\n--- GESTIÓN DE EDIFICIOS ---");
                    // Mostramos lo que ya tiene el usuario
                    System.out.println("Tus edificios: Granjas:" + myCiv.getFarm() + " | Herrerías:" + myCiv.getSmithy() + 
                                       " | Carpinterías:" + myCiv.getCarpentry() + " | Torres M.:" + myCiv.getMagicTower() + 
                                       " | Iglesias:" + myCiv.getChurch());
                    System.out.println("--------------------------------");
                    System.out.println("1. Granja        (Coste: " + FOOD_COST_FARM + " Comida, " + WOOD_COST_FARM + " Madera) -> +Comida/min");
                    System.out.println("2. Herrería      (Coste: " + FOOD_COST_SMITHY + " Comida, " + IRON_COST_SMITHY + " Hierro) -> +Hierro/min");
                    System.out.println("3. Carpintería   (Coste: " + FOOD_COST_CARPENTRY + " Comida, " + WOOD_COST_CARPENTRY + " Madera) -> +Madera/min");
                    System.out.println("4. Torre Mágica  (Coste: " + FOOD_COST_MAGICTOWER + " Comida, " + WOOD_COST_MAGICTOWER + " Madera, " + IRON_COST_MAGICTOWER + " Hierro) -> Genera Maná");
                    System.out.println("5. Iglesia       (Coste: " + FOOD_COST_CHURCH + " Comida, " + WOOD_COST_CHURCH + " Madera) -> Requisito Sacerdotes");
                    System.out.println("6. Volver");
                    System.out.print("Selecciona edificio a construir: ");
                    
                    int sel = sc.nextInt();
                    if (sel == 1) {
                        myCiv.buildFarm();
                        System.out.println("¡Granja construida!");
                    } else if (sel == 2) {
                        myCiv.buildSmithy();
                        System.out.println("¡Herrería construida!");
                    } else if (sel == 3) {
                        myCiv.buildCarpentry();
                        System.out.println("¡Carpintería construida!");
                    } else if (sel == 4) {
                        myCiv.buildMagicTower();
                        System.out.println("¡Torre Mágica construida!");
                    } else if (sel == 5) {
                        myCiv.buildChurch();
                        System.out.println("¡Iglesia construida!");
                    }
                }
                else if (opc == 6) { 
                    printArmyStats(); 

                } else if (opc == 7) {
                    int i = 0;
                    if (battleHistory.isEmpty()) System.out.println("Sin registros.");
                    while (i < battleHistory.size()) {
                        System.out.println(battleHistory.get(i));
                        i++;
                    }

                } else if (opc == 8) {
                    viewThreat();

                } else if (opc == 9) {
                    dao.saveGame(myCiv);
                    System.out.println("Partida guardada.");

                } else if (opc == 10) {
                    dao.saveGame(myCiv);
                    System.out.println("Saliendo...");
                }

            } catch (ResourceException e) {
                System.err.println("RECURSOS: " + e.getMessage());
            } catch (BuildingException e) {
                System.err.println("EDIFICIOS: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error en entrada.");
                sc.next();
            }
        } 
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

    // MÉTODO para ver tabla segun pag.: 8-9.
    private static void printArmyStats() {
        ArrayList<MilitaryUnit>[] army = myCiv.getArmy();
        String[] nombres = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher", "Magician", "Priest"};
        
        System.out.println("\n*************************************************************************************");
        System.out.println("                                 CIVILIZATION STATS");
        System.out.println("*************************************************************************************");

        // 1. SECCIÓN RECURSOS
        System.out.println(" RECURSOS:");
        System.out.printf("  %-15s %-15s %-15s %-15s\n", "Madera", "Hierro", "Comida", "Maná");
        System.out.printf("  %-15d %-15d %-15d %-15d\n", 
                          myCiv.getWood(), myCiv.getIron(), myCiv.getFood(), myCiv.getMana());
        System.out.println("-------------------------------------------------------------------------------------");

        // 2. SECCIÓN EDIFICIOS
        System.out.println(" EDIFICIOS:");
        System.out.printf("  %-15s %-15s %-15s %-15s %-15s\n", "Granjas", "Herrerías", "Carpinterías", "Torres M.", "Iglesias");
        System.out.printf("  %-15d %-15d %-15d %-15d %-15d\n", 
                          myCiv.getFarm(), myCiv.getSmithy(), myCiv.getCarpentry(), myCiv.getMagicTower(), myCiv.getChurch());
        System.out.println("-------------------------------------------------------------------------------------");

        // 3. SECCIÓN TECNOLOGÍAS
        System.out.println(" TECNOLOGÍAS:");
        System.out.printf("  %-25s %-25s\n", "Nivel Tecnología Ataque", "Nivel Tecnología Defensa");
        System.out.printf("  %-25d %-25d\n", 
                          myCiv.getTechnologyAttack(), myCiv.getTechnologyDefense());
        System.out.println("-------------------------------------------------------------------------------------");

        // 4. SECCIÓN EJÉRCITO (TABLA DETALLADA)
        System.out.println(" EJÉRCITO:");
        System.out.printf("  %-20s %-10s %-15s %-15s\n", "Unidad", "Cantidad", "Ataque Total", "Defensa Total");
        
        int totalUnits = 0;
        int totalAttack = 0;
        int totalDefense = 0;

        int i = 0;
        while (i < army.length) {
            if (!army[i].isEmpty()) {
                int groupAttack = 0;
                int groupDefense = 0;
                
                int j = 0;
                while (j < army[i].size()) {
                    MilitaryUnit unit = army[i].get(j);
                    groupAttack += unit.attack();
                    groupDefense += unit.getActualArmor();
                    j++;
                }

                System.out.printf("  %-20s %-10d %-15d %-15d\n", 
                                  nombres[i], army[i].size(), groupAttack, groupDefense);
                
                totalUnits += army[i].size();
                totalAttack += groupAttack;
                totalDefense += groupDefense;
            }
            i++;
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("  %-20s %-10d %-15d %-15d\n", "TOTAL EJÉRCITO", totalUnits, totalAttack, totalDefense);
        System.out.println("*************************************************************************************");
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