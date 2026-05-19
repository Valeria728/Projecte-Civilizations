package PANEL;


import javax.swing.*;
import javax.swing.border.*;

import game.Civilization;

import java.awt.*;

//Panel de estadísticas de la civilización.
// Muestra recursos, edificios, tecnología y ejército en tiempo real.

public class StatsPanel extends JPanel {

    // Recursos
    private JLabel lblFood, lblWood, lblIron, lblMana;
    // Generación
    private JLabel lblFoodGen, lblWoodGen, lblIronGen, lblManaGen;
    // Edificios
    private JLabel lblFarm, lblSmithy, lblCarpentry, lblMagicTower, lblChurch;
    // Tecnología
    private JLabel lblTechAtk, lblTechDef;
    // Ejército (totales)
    private JLabel lblSwordsman, lblSpearman, lblCrossbow, lblCannon;
    private JLabel lblArrowTower, lblCatapult, lblRocket;
    private JLabel lblMagician, lblPriest;
    // Batallas
    private JLabel lblBattles;

    public StatsPanel(Civilization civ) {
        setBackground(new Color(30, 30, 40));
        setLayout(new GridLayout(2, 3, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createResourcesBox(civ));
        add(createGenerationBox(civ));
        add(createBuildingsBox(civ));
        add(createTechBox(civ));
        add(createArmyBox(civ));
        add(createMiscBox(civ));
    }

    // -------------------------------------------------------
    // Cajas de información
    // -------------------------------------------------------
    private JPanel createResourcesBox(Civilization civ) {
        JPanel p = makeBox("💰 Recursos");
        lblFood = makeLabel("Comida: " + civ.getFood());
        lblWood = makeLabel("Madera: " + civ.getWood());
        lblIron = makeLabel("Hierro: " + civ.getIron());
        lblMana = makeLabel("Maná:   " + civ.getMana());
        p.add(lblFood); p.add(lblWood); p.add(lblIron); p.add(lblMana);
        return p;
    }

    private JPanel createGenerationBox(Civilization civ) {
        JPanel p = makeBox("⚡ Generación/min");
        int fg = civ.getFood() > 0 ? computeFoodGen(civ) : 0; // simplificado
        lblFoodGen = makeLabel("Comida: +" + computeFoodGen(civ));
        lblWoodGen = makeLabel("Madera: +" + computeWoodGen(civ));
        lblIronGen = makeLabel("Hierro: +" + computeIronGen(civ));
        lblManaGen = makeLabel("Maná:   +" + computeManaGen(civ));
        p.add(lblFoodGen); p.add(lblWoodGen); p.add(lblIronGen); p.add(lblManaGen);
        return p;
    }

    private JPanel createBuildingsBox(Civilization civ) {
        JPanel p = makeBox("🏗 Edificios");
        lblFarm       = makeLabel("Granjas:       " + civ.getFarm());
        lblSmithy     = makeLabel("Herrerías:     " + civ.getSmithy());
        lblCarpentry  = makeLabel("Carpinterías:  " + civ.getCarpentry());
        lblMagicTower = makeLabel("Torres Mágicas:" + civ.getMagicTower());
        lblChurch     = makeLabel("Iglesias:      " + civ.getChurch());
        p.add(lblFarm); p.add(lblSmithy); p.add(lblCarpentry);
        p.add(lblMagicTower); p.add(lblChurch);
        return p;
    }

    private JPanel createTechBox(Civilization civ) {
        JPanel p = makeBox("🔬 Tecnología");
        lblTechAtk = makeLabel("Ataque:  " + civ.getTechnologyAttack());
        lblTechDef = makeLabel("Defensa: " + civ.getTechnologyDefense());
        p.add(lblTechAtk); p.add(lblTechDef);
        return p;
    }

    private JPanel createArmyBox(Civilization civ) {
        JPanel p = makeBox("⚔ Ejército");
        lblSwordsman = makeLabel("Espadachines:   " + civ.getArmy()[0].size());
        lblSpearman  = makeLabel("Lanceros:        " + civ.getArmy()[1].size());
        lblCrossbow  = makeLabel("Ballestas:       " + civ.getArmy()[2].size());
        lblCannon    = makeLabel("Cañones:         " + civ.getArmy()[3].size());
        lblArrowTower= makeLabel("Torres Flecha:   " + civ.getArmy()[4].size());
        lblCatapult  = makeLabel("Catapultas:      " + civ.getArmy()[5].size());
        lblRocket    = makeLabel("Lanzacohetes:    " + civ.getArmy()[6].size());
        lblMagician  = makeLabel("Magos:           " + civ.getArmy()[7].size());
        lblPriest    = makeLabel("Sacerdotes:      " + civ.getArmy()[8].size());
        p.add(lblSwordsman); p.add(lblSpearman); p.add(lblCrossbow);
        p.add(lblCannon); p.add(lblArrowTower); p.add(lblCatapult);
        p.add(lblRocket); p.add(lblMagician); p.add(lblPriest);
        return p;
    }

    private JPanel createMiscBox(Civilization civ) {
        JPanel p = makeBox("📊 General");
        lblBattles = makeLabel("Batallas: " + civ.getBattles());
        p.add(lblBattles);
        return p;
    }

    // -------------------------------------------------------
    // Refresh: actualiza todos los labels con nuevos datos
    // -------------------------------------------------------
    public void refresh(Civilization civ) {
        lblFood.setText("Comida: " + civ.getFood());
        lblWood.setText("Madera: " + civ.getWood());
        lblIron.setText("Hierro: " + civ.getIron());
        lblMana.setText("Maná:   " + civ.getMana());

        lblFoodGen.setText("Comida: +" + computeFoodGen(civ));
        lblWoodGen.setText("Madera: +" + computeWoodGen(civ));
        lblIronGen.setText("Hierro: +" + computeIronGen(civ));
        lblManaGen.setText("Maná:   +" + computeManaGen(civ));

        lblFarm.setText("Granjas:       " + civ.getFarm());
        lblSmithy.setText("Herrerías:     " + civ.getSmithy());
        lblCarpentry.setText("Carpinterías:  " + civ.getCarpentry());
        lblMagicTower.setText("Torres Mágicas:" + civ.getMagicTower());
        lblChurch.setText("Iglesias:      " + civ.getChurch());

        lblTechAtk.setText("Ataque:  " + civ.getTechnologyAttack());
        lblTechDef.setText("Defensa: " + civ.getTechnologyDefense());

        lblSwordsman.setText("Espadachines:   " + civ.getArmy()[0].size());
        lblSpearman.setText("Lanceros:        " + civ.getArmy()[1].size());
        lblCrossbow.setText("Ballestas:       " + civ.getArmy()[2].size());
        lblCannon.setText("Cañones:         " + civ.getArmy()[3].size());
        lblArrowTower.setText("Torres Flecha:   " + civ.getArmy()[4].size());
        lblCatapult.setText("Catapultas:      " + civ.getArmy()[5].size());
        lblRocket.setText("Lanzacohetes:    " + civ.getArmy()[6].size());
        lblMagician.setText("Magos:           " + civ.getArmy()[7].size());
        lblPriest.setText("Sacerdotes:      " + civ.getArmy()[8].size());

        lblBattles.setText("Batallas: " + civ.getBattles());
        repaint();
    }

    // -------------------------------------------------------
    // Cálculo de generación (igual que en Main.java)
    // -------------------------------------------------------
    private int computeFoodGen(Civilization civ) {
        return 8000 + civ.getFarm() * 4000;
    }
    private int computeWoodGen(Civilization civ) {
        return 5000 + civ.getCarpentry() * 2500;
    }
    private int computeIronGen(Civilization civ) {
        return 1500 + civ.getSmithy() * 750;
    }
    private int computeManaGen(Civilization civ) {
        return civ.getMagicTower() * 10;
    }

    // -------------------------------------------------------
    // Helpers visuales
    // -------------------------------------------------------
    private JPanel makeBox(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(45, 45, 60));
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 160), 1),
            title,
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 13),
            new Color(255, 215, 0)
        ));
        return p;
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Monospaced", Font.PLAIN, 12));
        l.setForeground(new Color(200, 220, 255));
        l.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
        return l;
    }
}
