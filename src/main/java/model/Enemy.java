package model;

public class Enemy extends Statistics {
    private int award;

    public Enemy() {
        super(0, 0, 0, 0);
    }

    @Override
    public void attack(Statistics defender) {
        if (defender instanceof Player && Math.random() * 10 > 9) {
            ((Player) defender).setEnraged(true);
        }
        super.attack(defender);
    }

    public Enemy(int health, int attack, int defense, int agility, int award) {
        super(health, attack, defense, agility);
        this.award = award;
    }

    public static Enemy easy() {
        int health = 1 + (int) (Math.random() * 5);
        int stats = (int) (Math.random() * 2);
        int award = 1 + (int) (Math.random() * 5);
        return new Enemy(health, stats, stats, stats, award);
    }

    public static Enemy medium() {
        int health = 1 + (int) (Math.random() * 10);
        int stats = (int) (Math.random() * 5);
        int award = 1 + (int) (Math.random() * 20);
        return new Enemy(health, stats, stats, stats, award);
    }

    public static Enemy hard() {
        int health = 1 + (int) (Math.random() * 30);
        int stats = 1 + (int) (Math.random() * 10);
        int award = 1 + (int) (Math.random() * 100);
        return new Enemy(health, stats, stats, stats, award);
    }

    public static Enemy randomEncounter() {

        Enemy result = switch ((int) (Math.random() * 3)) {
            case 1:
                yield Enemy.hard();
            case 2:
                yield Enemy.medium();
            default:
                yield Enemy.easy();
        };
        return result;
    }

    public int getAward() {
        return award;
    }
}
