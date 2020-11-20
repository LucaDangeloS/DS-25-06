package e3;

public class Gunfight {

    private final Gunslinger G1 = new Gunslinger();
    private final Gunslinger G2 = new Gunslinger();

    public String duel() {
        GunslingerAction ga1;
        GunslingerAction ga2;
        G1.setBehavior(new G_Behavior());
        G2.setBehavior(new G_Behavior());

        while (true) {
            ga1 = G1.action();
            ga2 = G2.action();

            switch (ga1) {
                case SHOOT:
                    if (ga2 == GunslingerAction.SHOOT) return "Draw!";
                    else if (ga2 != GunslingerAction.PROTECT) return "Gunslinger 1 wins!";
                default:
                    break;
            }
            G1.rivalAction(ga2);
            G2.rivalAction(ga1);
            System.out.println(G1.getRivalActions().toString());
            System.out.println(G2.getRivalActions().toString());
        }
    }

    public static void main(String args[]) {
        Gunfight gf = new Gunfight();
        System.out.println(gf.duel());
    }
}
