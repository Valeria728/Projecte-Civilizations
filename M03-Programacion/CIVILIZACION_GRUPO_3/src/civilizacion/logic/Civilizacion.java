package civilizacion.logic;


import java.util.ArrayList;

public class Civilization implements Variables {
    // Atributos de Tecnología y Recursos
    private int technologyDefense;
    private int technologyAttack;
    private int wood;
    private int iron;
    private int food;
    private int mana;

    // Contadores de Edificios
    private int magicTower;
    private int church;
    private int farm;
    private int smithy;
    private int carpentry;
    private int battles;

    // El ejército: un array de 9 posiciones, cada una con una lista de unidades
    // 0: Swordsman, 1: Spearman, 2: Crossbow, 3: Cannon, 4: Arrow Tower...
    private ArrayList<MilitaryUnit>[] army;

    // Constructor inicial
    public Civilization() {
        this.technologyDefense = 0;
        this.technologyAttack = 0;
        this.wood = 10000; // Recursos iniciales (puedes ajustarlos)
        this.iron = 5000;
        this.food = 15000;
        this.mana = 0;
        
        // Inicializamos el array del ejército con sus 9 grupos
        this.army = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            this.army[i] = new ArrayList<MilitaryUnit>();
        }
    }

    // Método para crear un nuevo Espadachín (basado en el PDF)
    public void newSwordsman(int n) throws ResourceException {
        int count = 0;
        for (int i = 0; i < n; i++) {
            // Comprobamos si hay recursos en Variables
            if (food >= FOOD_COST_SWORDSMAN && wood >= WOOD_COST_SWORDSMAN && iron >= IRON_COST_SWORDSMAN) {
                food -= FOOD_COST_SWORDSMAN;
                wood -= WOOD_COST_SWORDSMAN;
                iron -= IRON_COST_SWORDSMAN;
                
                // Creamos la unidad pasando nuestras tecnologías actuales
                army[0].add(new Swordsman(technologyDefense, technologyAttack));
                count++;
            } else {
                // Si no hay recursos para todos los pedidos, lanzamos la excepción
                // pero informamos de cuántos SI se han podido crear
                throw new ResourceException("No hay recursos suficientes. Se han creado " + count + " Swordsman.");
            }
        }
        System.out.println("Se han añadido " + count + " Swordsman al ejército.");
    }

    // Getters y Setters necesarios para la Base de Datos (M02)
    public int getWood() { return wood; }
    public void setWood(int wood) { this.wood = wood; }
    // ... (deberás añadir el resto para iron, food, mana, etc.)
}
