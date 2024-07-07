package JuegoDeRol;

public class JuegodeRol {
    public static void main(String[] args) {
        Guerreros guerrero = new Guerreros("Guerrero", 100, 1, 20, 10, 5);
        Arqueros arquero = new Arqueros("Arquero", 80, 1, 25, 15, 5);
        Magos mago = new Magos("Mago", 60, 1, 30, 20, 3);

        SistemaCombate combate = new SistemaCombate();
        
        combate.batalla(guerrero, mago);
        combate.batalla(arquero, guerrero);
        combate.batalla(mago, arquero);
        
        Personaje ganador = GanadorFinal(guerrero, arquero, mago);
        System.out.println("El ganador es: " + ganador.nombre);
    }

    public static Personaje GanadorFinal(Personaje p1, Personaje p2, Personaje p3) {
        if (p1.vida > p2.vida && p1.vida > p3.vida) {
            return p1;
        } else if (p2.vida > p1.vida && p2.vida > p3.vida) {
            return p2;
        } else {
            return p3;
        }
    }
}

class SistemaCombate {
    public void batalla(Personaje p1, Personaje p2) {
        System.out.println("La Batalla es " + p1.nombre + " y " + p2.nombre);
        while (p1.vida > 0 && p2.vida > 0) {
            p1.atacar(p2);
            if (p2.vida > 0) {
                p2.atacar(p1);
            }
        }
        if (p1.vida > 0) {
            p1.ganarBatalla();
        } else {
            p2.ganarBatalla();
        }
    }
}
abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int exp;
    protected int nivel;
    protected int ataque;
    protected int defensa;
    public Personaje(String nombre, int vida, int nivel, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.exp = 0;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defensa = defensa;
    }
    public abstract void atacar(Personaje oponente);
    public void recibirDano(int dano) {
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
    }
    public void ganarBatalla() {
        exp = exp +10;
        vida = vida + 20;
        if (exp >= nivel * 10) {
            nivel++;
            exp = 0;
            ataque = ataque + 5;
            defensa = defensa + 3;
        }
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [nombre=" + nombre + ", vida=" + vida + ", exp=" + exp + ", nivel=" + nivel + ", ataque=" + ataque + ", defensa=" + defensa + "]";
    }
}

class Guerreros extends Personaje {
    private int fuerza;

    public Guerreros(String nombre, int vida, int nivel, int ataque, int defensa, int fuerza) {
        super(nombre, vida, nivel, ataque, defensa);
        this.fuerza = fuerza;
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = (this.ataque + this.fuerza) - oponente.defensa;
        if (dano > 0) {
            oponente.recibirDano(dano);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", fuerza=" + fuerza;
    }
}

class Arqueros extends Personaje {
    private int precision;

    public Arqueros(String nombre, int vida, int nivel, int ataque, int defensa, int precision) {
        super(nombre, vida, nivel, ataque, defensa);
        this.precision = precision;
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = (this.ataque + this.precision) - oponente.defensa;
        if (dano > 0) {
            oponente.recibirDano(dano);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", precision=" + precision;
    }
}

class Magos extends Personaje {
    private int hechizo;

    public Magos(String nombre, int vida, int nivel, int ataque, int defensa, int hechizo) {
        super(nombre, vida, nivel, ataque, defensa);
        this.hechizo = hechizo;
    }

    @Override
    public void atacar(Personaje oponente) {
        int dano = (this.ataque + this.hechizo) - oponente.defensa;
        if (dano > 0) {
            oponente.recibirDano(dano);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", hechizo=" + hechizo;
    }
}
