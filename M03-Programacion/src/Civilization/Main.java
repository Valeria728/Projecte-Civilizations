package Civilization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main implements Variables {
    private static Civilization myCiv = new Civilization();
    private static int enemyFleetCount = 1;
    private static int currentEnemyWood = WOOD_BASE_ENEMY_ARMY;
    private static int currentEnemyIron = IRON_BASE_ENEMY_ARMY;
    private static int currentEnemyFood = FOOD_BASE_ENEMY_ARMY;

    public static void main(String[] args) {
    	Civilization myCiv = new Civilization();
    	CivilizationDAO dao = new CivilizationDAO();
    	dao.loadGame(myCiv);
    	// Test de conexión rápido
        try (Connection testConn = DBConnection.getConnection()) {
            if (testConn != null) {
                System.out.println("[SISTEMA] Conexión MySQL establecida.");
            }
        } catch (SQLException e) {
            System.err.println("[SISTEMA] Error inicial de BD: " + e.getMessage());
            // No detenemos el programa, permitimos que siga por consola
        }
        Scanner sc = new Scanner(System.in);
        Timer timer = new Timer();

        // TAREA 1: Generar recursos cada 1 minuto (60000 ms)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                myCiv.generateResourcesNextMinute();
                // Usamos System.err para que el mensaje salga en un color distinto o destaque
                System.out.println("\n[SISTEMA] Recursos generados (1 min).");
            }
        }, 60000, 60000);

        // TAREA 2: Ataque enemigo cada 3 minutos (180000 ms)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	System.out.println("\n[ALERTA] ¡La flota enemiga nº " + enemyFleetCount + " ha llegado!");
                ArrayList<MilitaryUnit>[] enemyArmy = createEnemyArmy();
                Battle battle = new Battle(myCiv.getArmy(), enemyArmy);
                
                // Ejecutar batalla y mostrar reporte
                System.out.println(battle.startBattle());
                
                // Recoger escombros de la batalla
                int[] waste = battle.getWaste();
                myCiv.setWood(myCiv.getWood() + waste[0]);
                myCiv.setIron(myCiv.getIron() + waste[1]);
                System.out.println("[SISTEMA] Has recogido " + waste[0] + " de madera y " + waste[1] + " de hierro de los restos.");
                
                // Incrementar fuerza enemiga para el próximo ataque (6%)
                currentEnemyWood += (currentEnemyWood * ENEMY_FLEET_INCREASE) / 100;
                currentEnemyIron += (currentEnemyIron * ENEMY_FLEET_INCREASE) / 100;
                currentEnemyFood += (currentEnemyFood * ENEMY_FLEET_INCREASE) / 100;
                enemyFleetCount++;
            }
        }, 180000, 180000);

        // --- MENÚ PRINCIPAL ---
        int opc = 0;
        while (opc != 9) {
            System.out.println("\n================================");
            System.out.println("   CIVILIZATIONS CURS 25-26");
            System.out.println("================================");
            System.out.println("Madera: " + myCiv.getWood() + " | Hierro: " + myCiv.getIron() + " | Comida: " + myCiv.getFood());
            System.out.println("--------------------------------");
            System.out.println("1. Reclutar Swordsman");
            System.out.println("2. Reclutar Spearman");
            System.out.println("3. Reclutar Crossbow");
            System.out.println("4. Reclutar Cannon");
            System.out.println("5. Reclutar Magician (Torre)");
            System.out.println("6. Reclutar Priest (Iglesia)");
            System.out.println("7. Gestión (Edificios y Tecnología)");
            System.out.println("8. Ver Reporte de Ejército");
            System.out.println("9. Salir");
            System.out.print("Selección: ");
            
            try {
                opc = sc.nextInt();

                if (opc == 1) {
                    System.out.print("¿Cantidad?: ");
                    myCiv.newSwordsman(sc.nextInt());
                } 
                else if (opc == 2) {
                    System.out.print("¿Cantidad?: ");
                    myCiv.newSpearman(sc.nextInt());
                }
                else if (opc == 3) {
                    System.out.print("¿Cantidad?: ");
                    myCiv.newCrossbow(sc.nextInt());
                }
                else if (opc == 4) {
                    System.out.print("¿Cantidad?: ");
                    myCiv.newCannon(sc.nextInt());
                }
                else if (opc == 5) {
                    System.out.print("¿Cantidad?: ");
                    myCiv.newMagician(sc.nextInt());
                }
                else if (opc == 6) {
                    System.out.print("¿Cantidad?: ");
                    myCiv.newPriest(sc.nextInt());
                }
                else if (opc == 7) {
                    System.out.println("\n-- GESTIÓN --");
                    System.out.println("1. Granja | 2. Herrería | 3. Carpintería | 4. Torre Mágica | 5. Mej. Ataque | 6. Mej. Defensa");
                    int sub = sc.nextInt();
                    
                    if (sub == 1) myCiv.buildFarm();
                    else if (sub == 2) myCiv.buildSmithy();
                    else if (sub == 3) myCiv.buildCarpentry(); 
                    else if (sub == 4) myCiv.buildMagicTower();
                    else if (sub == 5) myCiv.upgradeTechnologyAttack();
                    else if (sub == 6) myCiv.upgradeTechnologyDefense();
                }
                else if (opc == 8) {
                    printArmyStats();
                } 
                else if (opc == 9) {
                    System.out.println("Guardando progreso y saliendo...");
                    dao.saveGame(myCiv); // Guardar todo antes de cerrar
                    System.out.println("Saliendo...");
                }
                else {
                    System.out.println("Opción incorrecta.");
                }

            } catch (ResourceException e) {
                System.err.println("RECURSOS: " + e.getMessage());
            } catch (BuildingException e) {
                System.err.println("EDIFICIO: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error en la entrada de datos.");
                sc.next(); // Limpiar error
            }
        }
        
        timer.cancel(); // Cerramos los hilos
        sc.close();
        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<MilitaryUnit>[] createEnemyArmy() {
        ArrayList<MilitaryUnit>[] enemyArmy = (ArrayList<MilitaryUnit>[]) new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            enemyArmy[i] = new ArrayList<MilitaryUnit>();
        }
        
        int tempWood = currentEnemyWood;
        int tempIron = currentEnemyIron;
        int tempFood = currentEnemyFood;

        // El enemigo compra lo mejor que puede con sus recursos actuales
        while (tempIron >= IRON_COST_CANNON && tempWood >= WOOD_COST_CANNON) {
            enemyArmy[3].add(new Cannon()); 
            tempIron -= IRON_COST_CANNON; 
            tempWood -= WOOD_COST_CANNON;
        }
        while (tempIron >= IRON_COST_CROSSBOW && tempWood >= WOOD_COST_CROSSBOW) {
            enemyArmy[2].add(new Crossbow());
            tempIron -= IRON_COST_CROSSBOW; 
            tempWood -= WOOD_COST_CROSSBOW;
        }
        while (tempFood >= FOOD_COST_SPEARMAN && tempWood >= WOOD_COST_SPEARMAN) {
            enemyArmy[1].add(new Spearman());
            tempFood -= FOOD_COST_SPEARMAN; 
            tempWood -= WOOD_COST_SPEARMAN;
        }
        while (tempFood >= FOOD_COST_SWORDSMAN && tempWood >= WOOD_COST_SWORDSMAN) {
            enemyArmy[0].add(new Swordsman());
            tempFood -= FOOD_COST_SWORDSMAN; 
            tempWood -= WOOD_COST_SWORDSMAN;
        }
        return enemyArmy;
    }

    private static void printArmyStats() {
        ArrayList<MilitaryUnit>[] army = myCiv.getArmy();
        String[] nombres = {"Espadachines", "Lanceros", "Ballesteros", "Cañones", "Torres Flechas", "Catapultas", "Torres Cohete", "Magos", "Sacerdotes"};
        System.out.println("\n--- TROPAS ACTUALES ---");
        boolean vacio = true;
        for (int i = 0; i < army.length; i++) {
            if (army[i].size() > 0) {
                System.out.println(nombres[i] + ": " + army[i].size());
                vacio = false;
            }
        }
        if (vacio) System.out.println("No tienes tropas.");
    }
}