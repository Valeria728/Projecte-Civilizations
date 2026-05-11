package civilization;

import java.sql.*;
import java.util.ArrayList;

public class CivilizationDAO {
	public void saveGame(Civilization civ) {
        // Query per actualitzar recursos i edificis del jugador 1
        String sqlStats = "UPDATE player_stats SET wood=?, iron=?, food=?, mana=?, tech_attack=?, tech_defense=?, carpentry=?, farm=?, smithy=?, magic_tower=? WHERE id=1";
        // Query per actualitzar la quantitat de cada tipus d'unitat
        String sqlArmy = "UPDATE player_army SET quantity=? WHERE unit_id=?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement psStats = conn.prepareStatement(sqlStats);
            psStats.setInt(1, civ.getWood());
            psStats.setInt(2, civ.getIron());
            psStats.setInt(3, civ.getFood());
            psStats.setInt(4, civ.getMana());
            psStats.setInt(5, civ.getTechnologyAttack());
            psStats.setInt(6, civ.getTechnologyDefense());
            psStats.setInt(7, civ.getCarpentry());
            psStats.setInt(8, civ.getFarm());
            psStats.setInt(9, civ.getSmithy());
            psStats.setInt(10, civ.getMagicTower());
            psStats.executeUpdate();

            PreparedStatement psArmy = conn.prepareStatement(sqlArmy);
            ArrayList<MilitaryUnit>[] army = civ.getArmy();
            for (int i = 0; i < army.length; i++) {
                psArmy.setInt(1, army[i].size()); // Guardem el total d'unitats de cada llista
                psArmy.setInt(2, i);
                psArmy.executeUpdate();
            }
            System.out.println("[BD] Partida guardada.");
            
        } catch (SQLException e) {
            System.err.println("Error en guardar: " + e.getMessage());
        }
    }

    public void loadGame(Civilization civ) {
        String sqlStats = "SELECT * FROM player_stats WHERE id=1";
        String sqlArmy = "SELECT * FROM player_army ORDER BY unit_id ASC";

        try (Connection conn = DBConnection.getConnection()) {
            Statement st = conn.createStatement();
            
            // Carreguem els valors de la BD a l'objecte Civilization
            ResultSet rsStats = st.executeQuery(sqlStats);
            if (rsStats.next()) {
                civ.setWood(rsStats.getInt("wood"));
                civ.setIron(rsStats.getInt("iron"));
                civ.setFood(rsStats.getInt("food"));
                civ.setMana(rsStats.getInt("mana"));
                civ.setTechnologyAttack(rsStats.getInt("tech_attack"));
                civ.setTechnologyDefense(rsStats.getInt("tech_defense"));
                civ.setCarpentry(rsStats.getInt("carpentry"));
                civ.setFarm(rsStats.getInt("farm"));
                civ.setSmithy(rsStats.getInt("smithy"));
                civ.setMagicTower(rsStats.getInt("magic_tower"));
            }

            // Reconstruïm els ArrayLists d'unitats
            ResultSet rsArmy = st.executeQuery(sqlArmy);
            ArrayList<MilitaryUnit>[] army = civ.getArmy();
            
            while (rsArmy.next()) {
                int id = rsArmy.getInt("unit_id");
                int qty = rsArmy.getInt("quantity");
                
                army[id].clear(); // Buidem la llista per evitar duplicats al carregar
                for (int i = 0; i < qty; i++) {
                    // Creem instàncies noves segons la ID de la unitat
                    switch(id) {
                        case 0: army[0].add(new Swordsman()); break;
                        case 1: army[1].add(new Spearman()); break;
                        case 2: army[2].add(new Crossbow()); break;
                        case 3: army[3].add(new Cannon()); break;
                        case 6: army[6].add(new RocketlauncherTower()); break;
                        case 7: army[7].add(new Magician()); break;
                        case 8: army[8].add(new Priest()); break;
                    }
                }
            }
            System.out.println("[BD] Partida carregada.");
            
        } catch (SQLException e) {
            System.err.println("Error en carregar: " + e.getMessage());
        }
    }
}

