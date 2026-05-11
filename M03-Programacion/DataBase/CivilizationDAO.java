package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import game.Civilization;
import interfaces.MilitaryUnit;
import units.attack.Cannon;
import units.attack.Crossbow;
import units.attack.Spearman;
import units.attack.Swordsman;
import units.special.Magician;
import units.special.Priest;

public class CivilizationDAO {
	public void saveGame(Civilization civ) {
        // Asegúrate de que los ? coincidan con el orden de abajo (hay 10)
        String sqlStats = "UPDATE player_stats SET wood=?, iron=?, food=?, mana=?, tech_attack=?, tech_defense=?, carpentry=?, farm=?, smithy=?, magic_tower=? WHERE id=1";
        String sqlArmy = "UPDATE player_army SET quantity=? WHERE unit_id=?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement psStats = conn.prepareStatement(sqlStats);
            
            // Pasamos los valores usando tus getters de Civilization.java
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

            // Guardar el ejército
            PreparedStatement psArmy = conn.prepareStatement(sqlArmy);
            ArrayList<MilitaryUnit>[] army = civ.getArmy();
            for (int i = 0; i < army.length; i++) {
                psArmy.setInt(1, army[i].size());
                psArmy.setInt(2, i);
                psArmy.executeUpdate();
            }
            System.out.println("[BD] Datos guardados.");
            
        } catch (SQLException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void loadGame(Civilization civ) {
        String sqlStats = "SELECT * FROM player_stats WHERE id=1";
        String sqlArmy = "SELECT * FROM player_army ORDER BY unit_id ASC";

        try (Connection conn = DBConnection.getConnection()) {
            Statement st = conn.createStatement();
            
            // Cargar recursos y edificios
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

            // Cargar ejército
            ResultSet rsArmy = st.executeQuery(sqlArmy);
            while (rsArmy.next()) {
                int id = rsArmy.getInt("unit_id");
                int qty = rsArmy.getInt("quantity");
                ArrayList<MilitaryUnit>[] army = civ.getArmy();
                army[id].clear(); // Limpiamos antes de cargar
                for (int i = 0; i < qty; i++) {
                    if (id == 0) army[0].add(new Swordsman());
                    else if (id == 1) army[1].add(new Spearman());
                    else if (id == 2) army[2].add(new Crossbow());
                    else if (id == 3) army[3].add(new Cannon());
                    else if (id == 7) army[7].add(new Magician());
                    else if (id == 8) army[8].add(new Priest());
                }
            }
            System.out.println("[BD] Datos cargados.");
        } catch (SQLException e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }

}
