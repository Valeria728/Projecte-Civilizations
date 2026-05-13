package civilizationnew;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel para construir edificios (Granja, Herrería, Carpintería, Torre Mágica, Iglesia).
 */
public class BuildingsPanel extends JPanel {

    private Civilization civ;
    private MainGUI mainGUI;

    private JLabel lblFarm, lblSmithy, lblCarpentry, lblMagic, lblChurch;

    public BuildingsPanel(Civilization civ, MainGUI mainGUI) {
        this.civ = civ;
        this.mainGUI = mainGUI;
        setBackground(new Color(30, 30, 40));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JLabel title = new JLabel("Construir Edificios", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(new Color(255, 215, 0));
        add(title, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 4, 12, 12));
        grid.setBackground(new Color(30, 30, 40));

        grid.add(makeHeader("Edificio"));
        grid.add(makeHeader("Construidos"));
        grid.add(makeHeader("Coste (C/M/H)"));
        grid.add(makeHeader("Acción"));

        // Granja
        lblFarm = makeLabel(String.valueOf(civ.getFarm()));
        grid.add(makeLabel("🌾 Granja"));
        grid.add(lblFarm);
        grid.add(makeLabel("5000 / 10000 / 12000"));
        grid.add(makeBuildButton("Granja"));

        // Herrería
        lblSmithy = makeLabel(String.valueOf(civ.getSmithy()));
        grid.add(makeLabel("🔨 Herrería"));
        grid.add(lblSmithy);
        grid.add(makeLabel("5000 / 10000 / 12000"));
        grid.add(makeBuildButton("Herrería"));

        // Carpintería
        lblCarpentry = makeLabel(String.valueOf(civ.getCarpentry()));
        grid.add(makeLabel("🪵 Carpintería"));
        grid.add(lblCarpentry);
        grid.add(makeLabel("5000 / 10000 / 12000"));
        grid.add(makeBuildButton("Carpintería"));

        // Torre Mágica
        lblMagic = makeLabel(String.valueOf(civ.getMagicTower()));
        grid.add(makeLabel("🗼 Torre Mágica"));
        grid.add(lblMagic);
        grid.add(makeLabel("10000 / 20000 / 24000  (+3000 maná)"));
        grid.add(makeBuildButton("TorreMagica"));

        // Iglesia
        lblChurch = makeLabel(String.valueOf(civ.getChurch()));
        grid.add(makeLabel("⛪ Iglesia"));
        grid.add(lblChurch);
        grid.add(makeLabel("10000 / 20000 / 24000  (+maná)"));
        grid.add(makeBuildButton("Iglesia"));

        // Nota informativa
        JLabel note = new JLabel("<html><i>Cada edificio incrementa la generación de recursos.<br>" +
                "Necesitas Torre Mágica para crear Magos, e Iglesia para cada Sacerdote.</i></html>");
        note.setFont(new Font("SansSerif", Font.ITALIC, 12));
        note.setForeground(new Color(180, 180, 220));
        note.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        add(grid, BorderLayout.CENTER);
        add(note, BorderLayout.SOUTH);
    }

    private JButton makeBuildButton(String type) {
        JButton btn = new JButton("Construir");
        btn.setBackground(new Color(60, 130, 80));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buildBuilding(type);
            }
        });
        return btn;
    }

    private void buildBuilding(String type) {
        try {
            if (type.equals("Granja"))       civ.newFarm();
            else if (type.equals("Herrería")) civ.newSmithy();
            else if (type.equals("Carpintería")) civ.newCarpentry();
            else if (type.equals("TorreMagica")) civ.newMagicTower();
            else if (type.equals("Iglesia"))  civ.newChurch();

            mainGUI.setStatus("✅ " + type + " construida.");
            mainGUI.refreshAllPanels();
            mainGUI.saveToDB();

        } catch (ResourceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Sin recursos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void refresh(Civilization civ) {
        this.civ = civ;
        if (lblFarm      != null) lblFarm.setText(String.valueOf(civ.getFarm()));
        if (lblSmithy    != null) lblSmithy.setText(String.valueOf(civ.getSmithy()));
        if (lblCarpentry != null) lblCarpentry.setText(String.valueOf(civ.getCarpentry()));
        if (lblMagic     != null) lblMagic.setText(String.valueOf(civ.getMagicTower()));
        if (lblChurch    != null) lblChurch.setText(String.valueOf(civ.getChurch()));
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 13));
        l.setForeground(new Color(200, 220, 255));
        return l;
    }

    private JLabel makeHeader(String text) {
        JLabel l = new JLabel(text, JLabel.CENTER);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        l.setForeground(new Color(255, 215, 0));
        return l;
    }
}
