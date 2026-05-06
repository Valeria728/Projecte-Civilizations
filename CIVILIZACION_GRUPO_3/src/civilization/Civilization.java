package civilization;

import java.util.ArrayList;

public class Civilization implements Variables {
    protected int food = 50000, wood = 50000, iron = 20000, mana = 5000;
    protected int tA = 0, tD = 0;
    protected ArrayList<MilitaryUnit>[] army = new ArrayList[9];

    public Civilization() {
        int i = 0;
        while (i < 9) {
            army[i] = new ArrayList<MilitaryUnit>();
            i = i + 1;
        }
    }

    public void buySwordsman(int cant) throws ResourceException {
        int i = 0;
        while (i < cant) {
            if (food >= 8000 && wood >= 3000) {
                food = food - 8000; wood = wood - 3000;
                army[0].add(new Swordsman(tA, tD));
                i = i + 1;
            } else { 
            	throw new ResourceException("No hay recursos"); 
              }
        }
    }

    public void restArmy() {
        int i = 0;
        while (i < 9) {
            int j = 0;
            while (j < army[i].size()) {
                army[i].get(j).resetArmor();
                j = j + 1;
            }
            i = i + 1;
        }
    }
}
