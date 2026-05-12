package game;

import interfaces.Variables;
import units.AttackUnit;
import units.attack.Cannon;
import units.attack.Crossbow;
import units.attack.Spearman;
import units.attack.Swordsman;
//import units.special.Magician;
//import units.special.Priest;

import java.util.ArrayList;

import interfaces.MilitaryUnit;
import exceptions.BuildingException;
import exceptions.ResourcesException;
import java.util.ArrayList;

public class Civilization implements Variables {

    // Technology levels
    private int technologyDefense;
    private int technologyAttack;

    // Resources
    private int wood;
    private int iron;
    private int food;
    private int mana;

    // Buildings
    private int magicTower;
    private int church;
    private int farm;
    private int smithy;
    private int carpentry;

    // Battle counter
    private int battles;

    // Army: each index is a unit type
    // 0=Swordsman, 1=Spearman, 2=Crossbow, 3=Cannon,
    // 4=ArrowTower, 5=Catapult, 6=RocketLauncher, 7=Magician, 8=Priest
    private ArrayList<MilitaryUnit>[] army;

    @SuppressWarnings("unchecked")
    public Civilization() {
        technologyDefense = 0;
        technologyAttack = 0;
        wood = 0;
        iron = 0;
        food = 0;
        mana = 0;
        magicTower = 0;
        church = 0;
        farm = 0;
        smithy = 0;
        carpentry = 0;
        battles = 0;

        army = new ArrayList[9];
        int i = 0;
        while (i < 9) {
            army[i] = new ArrayList<MilitaryUnit>();
            i++;
        }
    }

    // -------------------------
    // GETTERS & SETTERS
    // -------------------------

    public int getTechnologyDefense() { return technologyDefense; }
    public void setTechnologyDefense(int technologyDefense) { this.technologyDefense = technologyDefense; }

    public int getTechnologyAttack() { return technologyAttack; }
    public void setTechnologyAttack(int technologyAttack) { this.technologyAttack = technologyAttack; }

    public int getWood() { return wood; }
    public void setWood(int wood) { this.wood = wood; }

    public int getIron() { return iron; }
    public void setIron(int iron) { this.iron = iron; }

    public int getFood() { return food; }
    public void setFood(int food) { this.food = food; }

    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }

    public int getMagicTower() { return magicTower; }
    public void setMagicTower(int magicTower) { this.magicTower = magicTower; }

    public int getChurch() { return church; }
    public void setChurch(int church) { this.church = church; }

    public int getFarm() { return farm; }
    public void setFarm(int farm) { this.farm = farm; }

    public int getSmithy() { return smithy; }
    public void setSmithy(int smithy) { this.smithy = smithy; }

    public int getCarpentry() { return carpentry; }
    public void setCarpentry(int carpentry) { this.carpentry = carpentry; }

    public int getBattles() { return battles; }
    public void setBattles(int battles) { this.battles = battles; }

    public ArrayList<MilitaryUnit>[] getArmy() { return army; }

    // -------------------------
    // RESOURCE GENERATION
    // -------------------------

    public void generateResources() {
        food = food + CIVILIZATION_FOOD_GENERATED + (farm * CIVILIZATION_FOOD_GENERATED_PER_FARM);
        wood = wood + CIVILIZATION_WOOD_GENERATED + (carpentry * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
        iron = iron + CIVILIZATION_IRON_GENERATED + (smithy * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
        mana = mana + (magicTower * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
    }

    // -------------------------
    // BUILDINGS
    // -------------------------

    public void newFarm() throws ResourcesException {
        if (food < FOOD_COST_FARM || wood < WOOD_COST_FARM || iron < IRON_COST_FARM) {
            throw new ResourcesException("Not enough resources to build a Farm. Required: Food=" + FOOD_COST_FARM + " Wood=" + WOOD_COST_FARM + " Iron=" + IRON_COST_FARM);
        }
        food = food - FOOD_COST_FARM;
        wood = wood - WOOD_COST_FARM;
        iron = iron - IRON_COST_FARM;
        farm++;
        System.out.println("Farm built! Total farms: " + farm);
    }

    public void newCarpentry() throws ResourcesException {
        if (food < FOOD_COST_CARPENTRY || wood < WOOD_COST_CARPENTRY || iron < IRON_COST_CARPENTRY) {
            throw new ResourcesException("Not enough resources to build a Carpentry. Required: Food=" + FOOD_COST_CARPENTRY + " Wood=" + WOOD_COST_CARPENTRY + " Iron=" + IRON_COST_CARPENTRY);
        }
        food = food - FOOD_COST_CARPENTRY;
        wood = wood - WOOD_COST_CARPENTRY;
        iron = iron - IRON_COST_CARPENTRY;
        carpentry++;
        System.out.println("Carpentry built! Total carpentries: " + carpentry);
    }

    public void newSmithy() throws ResourcesException {
        if (food < FOOD_COST_SMITHY || wood < WOOD_COST_SMITHY || iron < IRON_COST_SMITHY) {
            throw new ResourcesException("Not enough resources to build a Smithy. Required: Food=" + FOOD_COST_SMITHY + " Wood=" + WOOD_COST_SMITHY + " Iron=" + IRON_COST_SMITHY);
        }
        food = food - FOOD_COST_SMITHY;
        wood = wood - WOOD_COST_SMITHY;
        iron = iron - IRON_COST_SMITHY;
        smithy++;
        System.out.println("Smithy built! Total smithies: " + smithy);
    }

    public void newMagicTower() throws ResourcesException {
        if (food < FOOD_COST_MAGICTOWER || wood < WOOD_COST_MAGICTOWER || iron < IRON_COST_MAGICTOWER) {
            throw new ResourcesException("Not enough resources to build a Magic Tower. Required: Food=" + FOOD_COST_MAGICTOWER + " Wood=" + WOOD_COST_MAGICTOWER + " Iron=" + IRON_COST_MAGICTOWER);
        }
        food = food - FOOD_COST_MAGICTOWER;
        wood = wood - WOOD_COST_MAGICTOWER;
        iron = iron - IRON_COST_MAGICTOWER;
        magicTower++;
        System.out.println("Magic Tower built! Total magic towers: " + magicTower);
    }

    public void newChurch() throws ResourcesException {
        if (food < FOOD_COST_CHURCH || wood < WOOD_COST_CHURCH || iron < IRON_COST_CHURCH || mana < MANA_COST_CHURCH) {
            throw new ResourcesException("Not enough resources to build a Church. Required: Food=" + FOOD_COST_CHURCH + " Wood=" + WOOD_COST_CHURCH + " Iron=" + IRON_COST_CHURCH + " Mana=" + MANA_COST_CHURCH);
        }
        food = food - FOOD_COST_CHURCH;
        wood = wood - WOOD_COST_CHURCH;
        iron = iron - IRON_COST_CHURCH;
        mana = mana - MANA_COST_CHURCH;
        church++;
        System.out.println("Church built! Total churches: " + church);
    }

    // -------------------------
    // TECHNOLOGY UPGRADES
    // -------------------------

    public void upgradeTechnologyDefense() throws ResourcesException {
        int ironCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST
            + (technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST * UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST / 100);
        int woodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST
            + (technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST * UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST / 100);
        int foodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST + (technologyDefense * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST * UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST / 100);

        if (iron < ironCost || wood < woodCost || food < foodCost) {
            throw new ResourcesException("Not enough resources to upgrade Defense Technology. Required: Food=" + foodCost + " Wood=" + woodCost + " Iron=" + ironCost);
        }
        iron = iron - ironCost;
        wood = wood - woodCost;
        food = food - foodCost;
        technologyDefense++;
        System.out.println("Defense Technology upgraded to level " + technologyDefense);
    }

    public void upgradeTechnologyAttack() throws ResourcesException {
        int ironCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST
            + (technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST * UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST / 100);
        int woodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST
            + (technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST * UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST / 100);
        int foodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST
            + (technologyAttack * UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST * UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST / 100);

        if (iron < ironCost || wood < woodCost || food < foodCost) {
            throw new ResourcesException("Not enough resources to upgrade Attack Technology. Required: Food=" + foodCost + " Wood=" + woodCost + " Iron=" + ironCost);
        }
        iron = iron - ironCost;
        wood = wood - woodCost;
        food = food - foodCost;
        technologyAttack++;
        System.out.println("Attack Technology upgraded to level " + technologyAttack);
    }

    // -------------------------
    // ADD UNITS - helper
    // -------------------------

    // Returns how many units can be afforded
    private int howManyCanAfford(int n, int foodCost, int woodCost, int ironCost, int manaCost) {
        int maxByFood = foodCost > 0 ? food / foodCost : n;
        int maxByWood = woodCost > 0 ? wood / woodCost : n;
        int maxByIron = ironCost > 0 ? iron / ironCost : n;
        int maxByMana = manaCost > 0 ? mana / manaCost : n;
        int max = n;
        if (maxByFood < max) max = maxByFood;
        if (maxByWood < max) max = maxByWood;
        if (maxByIron < max) max = maxByIron;
        if (maxByMana < max) max = maxByMana;
        return max;
    }

    private void spendResources(int count, int foodCost, int woodCost, int ironCost, int manaCost) {
        food = food - (count * foodCost);
        wood = wood - (count * woodCost);
        iron = iron - (count * ironCost);
        mana = mana - (count * manaCost);
    }

    // -------------------------
    // ADD UNITS
    // -------------------------

    public void newSwordsman(int n) throws ResourcesException {
        int canAfford = howManyCanAfford(n, FOOD_COST_SWORDSMAN, WOOD_COST_SWORDSMAN, IRON_COST_SWORDSMAN, MANA_COST_SWORDSMAN);
        spendResources(canAfford, FOOD_COST_SWORDSMAN, WOOD_COST_SWORDSMAN, IRON_COST_SWORDSMAN, MANA_COST_SWORDSMAN);
        int i = 0;
        while (i < canAfford) {
            army[0].add(new Swordsman(technologyDefense, technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Swordsman added (requested " + n + ").");
        }
        System.out.println(canAfford + " Swordsman added.");
    }

    public void newSpearman(int n) throws ResourcesException {
        int canAfford = howManyCanAfford(n, FOOD_COST_SPEARMAN, WOOD_COST_SPEARMAN, IRON_COST_SPEARMAN, MANA_COST_SPEARMAN);
        spendResources(canAfford, FOOD_COST_SPEARMAN, WOOD_COST_SPEARMAN, IRON_COST_SPEARMAN, MANA_COST_SPEARMAN);
        int i = 0;
        while (i < canAfford) {
            army[1].add(new Spearman(technologyDefense, technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Spearman added (requested " + n + ").");
        }
        System.out.println(canAfford + " Spearman added.");
    }

    public void newCrossbow(int n) throws ResourcesException {
        int canAfford = howManyCanAfford(n, FOOD_COST_CROSSBOW, WOOD_COST_CROSSBOW, IRON_COST_CROSSBOW, MANA_COST_CROSSBOW);
        spendResources(canAfford, FOOD_COST_CROSSBOW, WOOD_COST_CROSSBOW, IRON_COST_CROSSBOW, MANA_COST_CROSSBOW);
        int i = 0;
        while (i < canAfford) {
            army[2].add(new Crossbow(technologyDefense, technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Crossbow added (requested " + n + ").");
        }
        System.out.println(canAfford + " Crossbow added.");
    }

    public void newCannon(int n) throws ResourcesException {
        int canAfford = howManyCanAfford(n, FOOD_COST_CANNON, WOOD_COST_CANNON, IRON_COST_CANNON, MANA_COST_CANNON);
        spendResources(canAfford, FOOD_COST_CANNON, WOOD_COST_CANNON, IRON_COST_CANNON, MANA_COST_CANNON);
        int i = 0;
        while (i < canAfford) {
            army[3].add(new Cannon(technologyDefense, technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Cannon added (requested " + n + ").");
        }
        System.out.println(canAfford + " Cannon added.");
    }

    public void newArrowTower(int n) throws ResourcesException {
        int canAfford = howManyCanAfford(n, FOOD_COST_ARROWTOWER, WOOD_COST_ARROWTOWER, IRON_COST_ARROWTOWER, MANA_COST_ARROWTOWER);
        spendResources(canAfford, FOOD_COST_ARROWTOWER, WOOD_COST_ARROWTOWER, IRON_COST_ARROWTOWER, MANA_COST_ARROWTOWER);
        int i = 0;
        while (i < canAfford) {
            army[4].add(new ArrowTower(technologyDefense, technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Arrow Tower added (requested " + n + ").");
        }
        System.out.println(canAfford + " Arrow Tower added.");
    }

    public void newCatapult(int n) throws ResourcesException {
        int canAfford = howManyCanAfford(n, FOOD_COST_CATAPULT, WOOD_COST_CATAPULT, IRON_COST_CATAPULT, MANA_COST_CATAPULT);
        spendResources(canAfford, FOOD_COST_CATAPULT, WOOD_COST_CATAPULT, IRON_COST_CATAPULT, MANA_COST_CATAPULT);
        int i = 0;
        while (i < canAfford) {
            army[5].add(new Catapult(technologyDefense, technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Catapult added (requested " + n + ").");
        }
        System.out.println(canAfford + " Catapult added.");
    }

    public void newRocketLauncher(int n) throws ResourcesException {
        int canAfford = howManyCanAfford(n, FOOD_COST_ROCKETLAUNCHERTOWER, WOOD_COST_ROCKETLAUNCHERTOWER, IRON_COST_ROCKETLAUNCHERTOWER, MANA_COST_ROCKETLAUNCHERTOWER);
        spendResources(canAfford, FOOD_COST_ROCKETLAUNCHERTOWER, WOOD_COST_ROCKETLAUNCHERTOWER, IRON_COST_ROCKETLAUNCHERTOWER, MANA_COST_ROCKETLAUNCHERTOWER);
        int i = 0;
        while (i < canAfford) {
            army[6].add(new RocketLauncherTower(technologyDefense, technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Rocket Launcher Tower added (requested " + n + ").");
        }
        System.out.println(canAfford + " Rocket Launcher Tower added.");
    }

    public void newMagician(int n) throws ResourcesException, BuildingException {
        if (magicTower < 1) {
            throw new BuildingException("You need at least 1 Magic Tower to create Magicians.");
        }
        int canAfford = howManyCanAfford(n, FOOD_COST_MAGICIAN, WOOD_COST_MAGICIAN, IRON_COST_MAGICIAN, MANA_COST_MAGICIAN);
        spendResources(canAfford, FOOD_COST_MAGICIAN, WOOD_COST_MAGICIAN, IRON_COST_MAGICIAN, MANA_COST_MAGICIAN);
        int i = 0;
        while (i < canAfford) {
            army[7].add(new Magician(technologyAttack));
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources. Only " + canAfford + " Magician added (requested " + n + ").");
        }
        System.out.println(canAfford + " Magician added.");
    }

    public void newPriest(int n) throws ResourcesException, BuildingException {
        if (church < 1) {
            throw new BuildingException("You need at least 1 Church to create Priests.");
        }
        // Priests are limited: max priests = number of churches
        int maxPriests = church - army[8].size();
        if (maxPriests <= 0) {
            throw new BuildingException("You need more Churches to create more Priests. Build " + n + " more Church(es).");
        }
        int requested = n;
        if (requested > maxPriests) {
            requested = maxPriests;
        }
        int canAfford = howManyCanAfford(requested, FOOD_COST_PRIEST, WOOD_COST_PRIEST, IRON_COST_PRIEST, MANA_COST_PRIEST);
        spendResources(canAfford, FOOD_COST_PRIEST, WOOD_COST_PRIEST, IRON_COST_PRIEST, MANA_COST_PRIEST);
        int i = 0;
        while (i < canAfford) {
            army[8].add(new Priest());
            i++;
        }
        if (canAfford < n) {
            throw new ResourcesException("Not enough resources or churches. Only " + canAfford + " Priest added (requested " + n + ").");
        }
        System.out.println(canAfford + " Priest added.");
    }

    // -------------------------
    // SANCTIFY
    // -------------------------

    public void sanctifyArmy() {
        if (army[8].isEmpty()) {
            System.out.println("No priests in army. Cannot sanctify.");
            return;
        }
        // Sanctify all units in groups 0-7
        int group = 0;
        while (group < 8) {
            int i = 0;
            while (i < army[group].size()) {
                MilitaryUnit unit = army[group].get(i);
                if (unit instanceof AttackUnit) {
                    ((AttackUnit) unit).setSanctified(true);
                }
                if (unit instanceof DefenseUnit) {
                    ((DefenseUnit) unit).setSanctified(true);
                }
                i++;
            }
            group++;
        }
        System.out.println("Army sanctified!");
    }

    public void desanctifyArmy() {
        int group = 0;
        while (group < 8) {
            int i = 0;
            while (i < army[group].size()) {
                MilitaryUnit unit = army[group].get(i);
                if (unit instanceof AttackUnit) {
                    ((AttackUnit) unit).setSanctified(false);
                }
                if (unit instanceof DefenseUnit) {
                    ((DefenseUnit) unit).setSanctified(false);
                }
                i++;
            }
            group++;
        }
    }

    // -------------------------
    // EXPERIENCE
    // -------------------------

    public void addExperienceToSurvivors() {
        int group = 0;
        while (group < 9) {
            int i = 0;
            while (i < army[group].size()) {
                MilitaryUnit unit = army[group].get(i);
                unit.setExperience(unit.getExperience() + 1);
                i++;
            }
            group++;
        }
    }

    // -------------------------
    // TOTAL UNIT COUNT
    // -------------------------

    public int getTotalUnits() {
        int total = 0;
        int i = 0;
        while (i < 9) {
            total = total + army[i].size();
            i++;
        }
        return total;
    }

    // -------------------------
    // FOOD GENERATION
    // -------------------------

    public int getFoodGeneration() {
        return CIVILIZATION_FOOD_GENERATED + (farm * CIVILIZATION_FOOD_GENERATED_PER_FARM);
    }

    public int getWoodGeneration() {
        return CIVILIZATION_WOOD_GENERATED + (carpentry * CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
    }

    public int getIronGeneration() {
        return CIVILIZATION_IRON_GENERATED + (smithy * CIVILIZATION_IRON_GENERATED_PER_SMITHY);
    }

    public int getManaGeneration() {
        return magicTower * CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
    }

    // -------------------------
    // PRINT STATS
    // -------------------------

    public void printStats() {
        System.out.println("***************************CIVILIZATION STATS***************************");
        System.out.println("--------------------------------------------------TECHNOLOGY----------------------------------------");
        System.out.println("  Attack\tDefense");
        System.out.println("  " + technologyAttack + "\t\t" + technologyDefense);
        System.out.println("---------------------------------------------------BUILDINGS----------------------------------------");
        System.out.println("  Farm\tSmithy\tCarpentry\tMagic Tower\tChurch");
        System.out.println("  " + farm + "\t" + smithy + "\t" + carpentry + "\t\t" + magicTower + "\t\t" + church);
        System.out.println("----------------------------------------------------DEFENSES----------------------------------------");
        System.out.println("  Arrow Tower\tCatapult\tRocket Launcher");
        System.out.println("  " + army[4].size() + "\t\t" + army[5].size() + "\t\t" + army[6].size());
        System.out.println("------------------------------------------------ATTACK UNITS----------------------------------------");
        System.out.println("  Swordsman\tSpearman\tCrossbow\tCannon");
        System.out.println("  " + army[0].size() + "\t\t" + army[1].size() + "\t\t" + army[2].size() + "\t\t" + army[3].size());
        System.out.println("----------------------------------------------SPECIAL UNITS----------------------------------------");
        System.out.println("  Magician\tPriest");
        System.out.println("  " + army[7].size() + "\t\t" + army[8].size());
        System.out.println("---------------------------------------------------RESOURCES----------------------------------------");
        System.out.println("  Food\t\tWood\t\tIron\t\tMana");
        System.out.println("  " + food + "\t\t" + wood + "\t\t" + iron + "\t\t" + mana);
        System.out.println("----------------------------------------GENERATION RESOURCES----------------------------------------");
        System.out.println("  Food\t\tWood\t\tIron\t\tMana");
        System.out.println("  " + getFoodGeneration() + "\t\t" + getWoodGeneration() + "\t\t" + getIronGeneration() + "\t\t" + getManaGeneration());
    }
}


