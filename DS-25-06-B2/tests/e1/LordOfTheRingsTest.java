package e1;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LordOfTheRingsTest {
    public static Ejercito<Bestias> EjBestias = new Ejercito<>();
    public static Ejercito<Heroes> EjHeroes = new Ejercito<>();

    @Test
    public void BeastLegionCreation() {
        if (!EjBestias.isEmpty()) EjBestias.clear();
        Bestias cs = new Orco("Magh'ul", 60, 47);
        Bestias cs2 = new Orco("Ushulum", 50, 35);
        Bestias cs3 = new Trasgo("Uruk-hai", 70, 45);

        EjBestias.add(cs);
        EjBestias.add(cs2);
        EjBestias.add(cs3);
        var EjBestiasString = EjBestias.toString();
        assertEquals("Ejercito de Bestias\n" +
                "\tMagh'ul : Orco (HP: 60) (RES: 47)\n" +
                "\tUshulum : Orco (HP: 50) (RES: 35)\n" +
                "\tUruk-hai : Trasgo (HP: 70) (RES: 45)\n",EjBestiasString);
    }
    @Test
    public void HeroesArmyCreation() {
        if (!EjHeroes.isEmpty()) EjHeroes.clear();
        Heroes bs = new Humano("Gandalf", 45, 33);
        Heroes bs2 = new Elfo("Legolas", 38, 15);
        Heroes bs3 = new Hobbit("Frodo", 20, 10);

        EjHeroes.add(bs);
        EjHeroes.add(bs2);
        EjHeroes.add(bs3);
        var EjHeroesString = EjHeroes.toString();
        assertEquals("Ejercito de Heroes\n" +
                "\tGandalf : Humano (HP: 45) (RES: 33)\n" +
                "\tLegolas : Elfo (HP: 38) (RES: 15)\n" +
                "\tFrodo : Hobbit (HP: 20) (RES: 10)\n",EjHeroesString);
    }

    @Test
    void Test_modificadores_de_raza () {
      new RNG(5); //creación de dados trucados
      var game = new Game(EjHeroes, EjBestias);
      var StringBatalla = game.batalla();
      /*
Tiradas de la semilla 5
Turno 1 : StringBatalla[0]
    Heroe(Gandalf): 39 Bestia: 5 (Bestia es Orco, DEF del heroe seria 33*0.9 = 30. Recibe 0 de daño)
    Heroe(Legolas): 49 Bestia: 52 (Bestia es Orco, DEF del heroe seria 15*0.9 = 13 y ATK de Bestia 52. Heroe (Legolas) recibe 39 de daño, se muere)
    Heroe: 68 Bestia(Uruk-hai): 27 (Bestia es Trasgo y Heroe Hobbit, ATK del heroe seria 68-5 = 63 y RES de Bestia (Uruk-hai) 45. Bestia recibe 18 de daño)
    */
    assertTrue(StringBatalla[0].contains("Gandalf (HP: 45)"));//0 de daño
    assertTrue(StringBatalla[1].contains("Gandalf (HP: 45)"));

    assertTrue(StringBatalla[0].contains("Legolas Elfo se muere!"));//Heroes muere

    assertTrue(StringBatalla[0].contains("Uruk-hai (HP: 70)"));
    assertFalse(StringBatalla[1].contains("Uruk-hai")); //Uruk-hai no está en el turno 2
    assertTrue(StringBatalla[2].contains("Uruk-hai (HP: 52)"));

    /*
    Tiradas:
Turno 2 : StringBatalla[1]
    Heroe: 61 Bestia: 65
    Heroe: 88 Bestia: 79
Turno 3 : StringBatalla[2]
    Heroe: 79 Bestia: 35
    Heroe: 54 Bestia: 0
Turno 4 : StringBatalla[3]
    Heroe: 85 Bestia: 1
    Heroe: 88 Bestia: 69
Turno 5 : StringBatalla[4]
    Heroe: 72 Bestia: 40

 */
    }

    @Order(3)
    @Test
    void batalla_final() {
        new RNG(5); //creación de dados trucados
        HeroesArmyCreation();
        BeastLegionCreation();
        var game = new Game(EjHeroes, EjBestias);
        var StringBatalla = game.batalla();

        assertTrue(StringBatalla[4].contains("Gandalf (HP: 5) y Uruk-hai (HP: 10)"));
        assertTrue(StringBatalla[5].contains("Heroes Ganan!!!"));
    }

    @Order(4)
    @Test
    void batalla_aleatoria() {
        var game = new Game(EjHeroes, EjBestias);

        //Simulacion batalla 1
        new RNG();
        HeroesArmyCreation();
        BeastLegionCreation();
        var StringBatalla = game.batalla();

        //Simulacion batalla 2
        new RNG();
        HeroesArmyCreation();
        BeastLegionCreation();
        var StringBatalla2 = game.batalla();

        assertNotEquals(StringBatalla, StringBatalla2);

        System.out.println(game.toString());
    }
}