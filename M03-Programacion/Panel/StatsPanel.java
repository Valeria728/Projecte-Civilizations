package Panel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import model.Civilization;

public class StatsPanel extends JPanel {

    private JLabel lblFood, lblWood, lblIron, lblMana;
    private JLabel lblFoodGen, lblWoodGen, lblIronGen, lblManaGen;
    private JLabel lblFarm, lblSmithy, lblCarpentry, lblMagicTower, lblChurch;
    private JLabel lblTechAtk, lblTechDef;
    private JLabel lblSwordsman, lblSpearman, lblCrossbow, lblCannon;
    private JLabel lblArrowTower, lblCatapult, lblRocket;
    private JLabel lblMagician, lblPriest, lblBattles;

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

    private JPanel createResourcesBox(Civilization civ) {
        JPanel p = makeBox("RECURSOS");
        lblFood = makeLabel("Comida: " + civ.getFood());
        lblWood = makeLabel("Madera: " + civ.getWood());
        lblIron = makeLabel("Hierro: " + civ.getIron());
        lblMana = makeLabel("Mana:   " + civ.getMana());
        p.add(lblFood); p.add(lblWood); p.add(lblIron); p.add(lblMana);
        return p;
    }

    private JPanel createGenerationBox(Civilization civ) {
        JPanel p = makeBox("GENERACION/MIN");
        lblFoodGen = makeLabel("Comida: +" + computeFoodGen(civ));
        lblWoodGen = makeLabel("Madera: +" + computeWoodGen(civ));
        lblIronGen = makeLabel("Hierro: +" + computeIronGen(civ));
        lblManaGen = makeLabel("Mana:   +" + computeManaGen(civ));
        p.add(lblFoodGen); p.add(lblWoodGen); p.add(lblIronGen); p.add(lblManaGen);
        return p;
    }

    private JPanel createBuildingsBox(Civilization civ) {
        JPanel p = makeBox("EDIFICIOS");
        lblFarm       = makeLabel("Granjas:       " + civ.getFarm());
        lblSmithy     = makeLabel("Herrerias:     " + civ.getSmithy());
        lblCarpentry  = makeLabel("Carpinterias:  " + civ.getCarpentry());
        lblMagicTower = makeLabel("Torres Magicas:" + civ.getMagicTower());
        lblChurch     = makeLabel("Iglesias:      " + civ.getChurch());
        p.add(lblFarm); p.add(lblSmithy); p.add(lblCarpentry);
        p.add(lblMagicTower); p.add(lblChurch);
        return p;
    }

    private JPanel createTechBox(Civilization civ) {
        JPanel p = makeBox("TECNOLOGIA");
        lblTechAtk = makeLabel("Ataque:  " + civ.getTechnologyAttack());
        lblTechDef = makeLabel("Defensa: " + civ.getTechnologyDefense());
        p.add(lblTechAtk); p.add(lblTechDef);
        return p;
    }

    private JPanel createArmyBox(Civilization civ) {
        JPanel p = makeBox("EJERCITO");
        lblSwordsman  = makeLabel("Espadachines: " + civ.getArmy()[0].size());
        lblSpearman   = makeLabel("Lanceros:     " + civ.getArmy()[1].size());
        lblCrossbow   = makeLabel("Ballestas:    " + civ.getArmy()[2].size());
        lblCannon     = makeLabel("Canones:      " + civ.getArmy()[3].size());
        lblArrowTower = makeLabel("Torres Flecha:" + civ.getArmy()[4].size());
        lblCatapult   = makeLabel("Catapultas:   " + civ.getArmy()[5].size());
        lblRocket     = makeLabel("Lanzacohetes: " + civ.getArmy()[6].size());
        lblMagician   = makeLabel("Magos:        " + civ.getArmy()[7].size());
        lblPriest     = makeLabel("Sacerdotes:   " + civ.getArmy()[8].size());
        p.add(lblSwordsman); p.add(lblSpearman); p.add(lblCrossbow);
        p.add(lblCannon); p.add(lblArrowTower); p.add(lblCatapult);
        p.add(lblRocket); p.add(lblMagician); p.add(lblPriest);
        return p;
    }

    private JPanel createMiscBox(Civilization civ) {
        JPanel p = makeBox("ESTADISTICAS GENERALES");
        lblBattles = makeLabel("Batallas: " + civ.getBattles());
        p.add(lblBattles);
        return p;
    }

    public void refresh(Civilization civ) {
        lblFood.setText("Comida: " + civ.getFood());
        lblWood.setText("Madera: " + civ.getWood());
        lblIron.setText("Hierro: " + civ.getIron());
        lblMana.setText("Mana:   " + civ.getMana());
        lblFoodGen.setText("Comida: +" + computeFoodGen(civ));
        lblWoodGen.setText("Madera: +" + computeWoodGen(civ));
        lblIronGen.setText("Hierro: +" + computeIronGen(civ));
        lblManaGen.setText("Mana:   +" + computeManaGen(civ));
        lblFarm.setText("Granjas:       " + civ.getFarm());
        lblSmithy.setText("Herrerias:     " + civ.getSmithy());
        lblCarpentry.setText("Carpinterias:  " + civ.getCarpentry());
        lblMagicTower.setText("Torres Magicas:" + civ.getMagicTower());
        lblChurch.setText("Iglesias:      " + civ.getChurch());
        lblTechAtk.setText("Ataque:  " + civ.getTechnologyAttack());
        lblTechDef.setText("Defensa: " + civ.getTechnologyDefense());
        lblSwordsman.setText("Espadachines: " + civ.getArmy()[0].size());
        lblSpearman.setText("Lanceros:     " + civ.getArmy()[1].size());
        lblCrossbow.setText("Ballestas:    " + civ.getArmy()[2].size());
        lblCannon.setText("Canones:      " + civ.getArmy()[3].size());
        lblArrowTower.setText("Torres Flecha:" + civ.getArmy()[4].size());
        lblCatapult.setText("Catapultas:   " + civ.getArmy()[5].size());
        lblRocket.setText("Lanzacohetes: " + civ.getArmy()[6].size());
        lblMagician.setText("Magos:        " + civ.getArmy()[7].size());
        lblPriest.setText("Sacerdotes:   " + civ.getArmy()[8].size());
        lblBattles.setText("Batallas: " + civ.getBattles());
        repaint();
    }

    private int computeFoodGen(Civilization civ) { return 8000 + civ.getFarm() * 4000; }
    private int computeWoodGen(Civilization civ) { return 5000 + civ.getCarpentry() * 2500; }
    private int computeIronGen(Civilization civ) { return 1500 + civ.getSmithy() * 750; }
    private int computeManaGen(Civilization civ) { return civ.getMagicTower() * 10; }

    private JPanel makeBox(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(45, 45, 60));
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 160), 1),
            title, TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 13), new Color(255, 215, 0)
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