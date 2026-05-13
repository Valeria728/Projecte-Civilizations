package civilizationnew;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel para mejorar las tecnologías de ataque y defensa.
 */
public class TechPanel extends JPanel {

    private Civilization civ;
    private MainGUI mainGUI;

    private JLabel lblAtkLevel, lblDefLevel;
    private JLabel lblAtkCost, lblDefCost;

    public TechPanel(Civilization civ, MainGUI mainGUI) {
        this.civ = civ;
        this.mainGUI = mainGUI;
        setBackground(new Color(30, 30, 40));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Árbol de Tecnologías", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(new Color(255, 215, 0));
        add(title, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 3, 20, 20));
        grid.setBackground(new Color(30, 30, 40));

        // ── Tecnología de Ataque ──
        JPanel attackBox = makeBox("⚔ Tecnología de Ataque");
        lblAtkLevel = makeLabel("Nivel actual: " + civ.getTechnologyAttack());
        lblAtkCost  = makeLabel(getAttackCostText(civ));
        JButton btnAtk = makeUpgradeButton("Mejorar Ataque");
        btnAtk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradeAttack();
            }
        });
        attackBox.add(lblAtkLevel);
        attackBox.add(lblAtkCost);
        attackBox.add(btnAtk);
        attackBox.add(makeLabel("  → +5% daño a unidades nuevas"));

        // ── Tecnología de Defensa ──
        JPanel defBox = makeBox("🛡 Tecnología de Defensa");
        lblDefLevel = makeLabel("Nivel actual: " + civ.getTechnologyDefense());
        lblDefCost  = makeLabel(getDefenseCostText(civ));
        JButton btnDef = makeUpgradeButton("Mejorar Defensa");
        btnDef.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradeDefense();
            }
        });
        defBox.add(lblDefLevel);
        defBox.add(lblDefCost);
        defBox.add(btnDef);
        defBox.add(makeLabel("  → +5% armadura a unidades nuevas"));

        grid.add(attackBox);
        grid.add(defBox);

        // Nota explicativa
        JLabel nota = new JLabel("<html><i>Las mejoras solo afectan a las unidades creadas <b>después</b> de subir el nivel.<br>" +
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
            mainGUI.setStatus("✅ Tecnología de Ataque mejorada a nivel " + civ.getTechnologyAttack());
            mainGUI.refreshAllPanels();
            mainGUI.saveToDB();
        } catch (ResourceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sin recursos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void upgradeDefense() {
        try {
            civ.upgradeTechnologyDefense();
            mainGUI.setStatus("✅ Tecnología de Defensa mejorada a nivel " + civ.getTechnologyDefense());
            mainGUI.refreshAllPanels();
            mainGUI.saveToDB();
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
    }

    // Calcula el coste de la siguiente mejora de ataque
    private String getAttackCostText(Civilization civ) {
        int level = civ.getTechnologyAttack();
        int ironBase = 2000;
        int ironCost = (int)(ironBase * Math.pow(1.1, level));
        return "Coste siguiente: " + ironCost + " hierro";
    }

    private String getDefenseCostText(Civilization civ) {
        int level = civ.getTechnologyDefense();
        int ironBase = 2000;
        int ironCost = (int)(ironBase * Math.pow(1.1, level));
        return "Coste siguiente: " + ironCost + " hierro";
    }

    private JPanel makeBox(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(45, 45, 60));
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 180), 1),
            title, TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 13),
            new Color(255, 215, 0)
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
        b.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        return b;
    }
}
