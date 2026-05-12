package Panel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import model.Civilization;
import exceptions.ResourceException;

public class TechPanel extends JPanel {

    private Civilization civ;
    private JFrame mainGUI; 

    private JLabel lblAtkLevel, lblDefLevel;
    private JLabel lblAtkCost, lblDefCost;

    public TechPanel(Civilization civ, JFrame mainGUI) {
        this.civ = civ;
        this.mainGUI = mainGUI;
        setBackground(new Color(30, 30, 40));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Arbol de Tecnologias", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(new Color(255, 215, 0));
        add(title, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 2, 20, 20));
        grid.setBackground(new Color(30, 30, 40));

        JPanel attackBox = makeBox("Tecnologia de Ataque");
        lblAtkLevel = makeLabel("Nivel actual: " + civ.getTechnologyAttack());
        lblAtkCost  = makeLabel(getAttackCostText(civ));
        JButton btnAtk = makeUpgradeButton("Mejorar Ataque");
        btnAtk.addActionListener(e -> upgradeAttack());
        
        attackBox.add(lblAtkLevel);
        attackBox.add(lblAtkCost);
        attackBox.add(btnAtk);
        attackBox.add(makeLabel(" -> +5% daño a unidades nuevas"));

        JPanel defBox = makeBox("Tecnologia de Defensa");
        lblDefLevel = makeLabel("Nivel actual: " + civ.getTechnologyDefense());
        lblDefCost  = makeLabel(getDefenseCostText(civ));
        JButton btnDef = makeUpgradeButton("Mejorar Defensa");
        btnDef.addActionListener(e -> upgradeDefense());

        defBox.add(lblDefLevel);
        defBox.add(lblDefCost);
        defBox.add(btnDef);
        defBox.add(makeLabel(" -> +5% armadura a unidades nuevas"));

        grid.add(attackBox);
        grid.add(defBox);

        JLabel nota = new JLabel("<html><i>Las mejoras solo afectan a las unidades creadas despues de subir el nivel.<br>" +
                "Cada nivel aumenta el coste de la siguiente mejora un 10%.</i></html>");
        nota.setFont(new Font("SansSerif", Font.ITALIC, 12));
        nota.setForeground(new Color(180, 180, 220));
        nota.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        add(grid, BorderLayout.CENTER);
        add(nota, BorderLayout.SOUTH);
    }

    private void upgradeAttack() {
        try {
            civ.upgradeTechnologyAttack();
            refresh(civ);
        } catch (ResourceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sin recursos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void upgradeDefense() {
        try {
            civ.upgradeTechnologyDefense();
            refresh(civ);
        } catch (ResourceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sin recursos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void refresh(Civilization civ) {
        this.civ = civ;
        if (lblAtkLevel != null) lblAtkLevel.setText("Nivel actual: " + civ.getTechnologyAttack());
        if (lblDefLevel != null) lblDefLevel.setText("Nivel actual: " + civ.getTechnologyDefense());
        if (lblAtkCost  != null) lblAtkCost.setText(getAttackCostText(civ));
        if (lblDefCost  != null) lblDefCost.setText(getDefenseCostText(civ));
        repaint();
    }

    private String getAttackCostText(Civilization civ) {
        int level = civ.getTechnologyAttack();
        int ironCost = (int)(2000 * Math.pow(1.1, level));
        return "Coste siguiente: " + ironCost + " hierro";
    }

    private String getDefenseCostText(Civilization civ) {
        int level = civ.getTechnologyDefense();
        int ironCost = (int)(2000 * Math.pow(1.1, level));
        return "Coste siguiente: " + ironCost + " hierro";
    }

    private JPanel makeBox(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(45, 45, 60));
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 180), 1),
            title, TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 13), new Color(255, 215, 0)
        ));
        return p;
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 13));
        l.setForeground(new Color(200, 220, 255));
        l.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
        return l;
    }

    private JButton makeUpgradeButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(120, 80, 180));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        return b;
    }
}