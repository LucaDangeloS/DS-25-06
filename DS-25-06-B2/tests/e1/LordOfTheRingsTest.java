package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LordOfTheRingsTest {
    private Ejercito<Bestias> EjBestias = new Ejercito<>();
    private Ejercito<Heroes> EjHeroes = new Ejercito<>();


    @Test
    public void BeastLegionCreation() {
        Bestias cs = new Bestias("Magh'ul", 60, 47, Razas_Bestias.Orco);
        Bestias cs2 = new Bestias("Ushulum", 50, 35, Razas_Bestias.Orco);
        Bestias cs3 = new Bestias("Uruk-hai", 70, 45, Razas_Bestias.Trasgo);

        EjBestias.add(cs);
        EjBestias.add(cs2);
        EjBestias.add(cs3);
        var EjBestiasString = EjBestias.toString();
        assertEquals("Ejercito de Bestias\n" +
                "\tMagh'ul : Orco (HP: 60) (RES: 47)\n" +
                "\tUshulum : Trasgo (HP: 50) (RES: 35)\n" +
                "\tUruk-hai : Orco (HP: 70) (RES: 45)\n",EjBestiasString);
    }

    public void HeroesArmyCreation() {
        Heroes bs = new Heroes("Gandalf", 45, 33, Razas_Heroes.Humano);
        Heroes bs2 = new Heroes("Legolas", 32, 15, Razas_Heroes.Elfo);
        Heroes bs3 = new Heroes("Frodo", 20, 10, Razas_Heroes.Hobbit);

        EjHeroes.add(bs);
        EjHeroes.add(bs2);
        EjHeroes.add(bs3);
        var EjHeroesString = EjHeroes.toString();
        assertEquals("Ejercito de Heroes\n" +
                "\tGandalf : Humano (HP: 45) (RES: 33)\n" +
                "\tLegolas : Elfo (HP:32) (RES: 15)\n" +
                "\tFrodo : Hobbit (HP: 20) (RES: 10)\n",EjHeroesString);
    }
/*
Tiradas de la semilla 5
Turno 1
    Heroe: 39 Bestia: 5
    Heroe: 49 Bestia: 52
    Heroe: 68 Bestia: 27
Turno 2
    Heroe: 61 Bestia: 65
    Heroe: 88 Bestia: 79
Turno 3
    Heroe: 79 Bestia: 35
    Heroe: 54 Bestia: 0
Turno 4
    Heroe: 85 Bestia: 1
    Heroe: 88 Bestia: 69
Turno 5
    Heroe: 72 Bestia: 40

 */
//    @Test
//    void Testd () {
//      new RNG(5);
//      var game = new Game(EjHeroes, EjBestias);
//    }
}