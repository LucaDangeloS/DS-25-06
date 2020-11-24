package e3;

public class Gunfight {
    private final Gunslinger G1;
    private final Gunslinger G2;

    Gunfight(Gunslinger G1, Gunslinger G2) {
        this.G1 = G1;
        this.G2 = G2;
    }

    public void duel() {
        int turn = 1;
        GunslingerAction ga1;
        GunslingerAction ga2;

        while (turn < 50) {
            ga1 = G1.action();
            ga2 = G2.action();

            System.out.println("Turn " + turn);
            System.out.println("Gunslinger 1: " + ga1 + "\n" + "Gunslinger 2: " + ga2);

            if (ga1 == GunslingerAction.SHOOT && G1.getLoads() < 0) System.out.println("------ ILLEGAL ACTION ------");

            if (ga2 == GunslingerAction.SHOOT && G2.getLoads() < 0) System.out.println("------ ILLEGAL ACTION ------");

            switch (ga1) {
                case PROTECT:
                    if (ga2 == GunslingerAction.MACHINE_GUN) {
                        System.out.println("*******\nGunslinger 2 wins!\n*******");
                        return;
                    }
                    break;

                case RELOAD:
                    if (ga2 == GunslingerAction.SHOOT || ga2 == GunslingerAction.MACHINE_GUN) {
                        System.out.println("*******\nGunslinger 2 wins!\n*******");
                        return;
                    }
                    break;

                default:
                    if (ga2 == GunslingerAction.SHOOT || ga2 == GunslingerAction.MACHINE_GUN) {
                        System.out.println("*******\nDraw!\n*******");
                        return;
                    }
                    else if (ga2 == GunslingerAction.RELOAD) {
                        System.out.println("*******\nGunslinger 1 wins!\n*******");
                        return;
                    }
                    else if (ga1 == GunslingerAction.MACHINE_GUN && ga2 == GunslingerAction.PROTECT) {
                        System.out.println("*******\nGunslinger 1 wins!\n*******");
                        return;
                    }
                    break;
            }
            G1.rivalAction(ga2);
            G2.rivalAction(ga1);
            turn++;
        }
        System.out.println("*******\nDraw!\n*******");
    }
}
