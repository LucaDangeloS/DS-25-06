package e2;

public abstract class GroupComponent {
    private final String name;
    private float coste;

    GroupComponent(String name, float coste) {
        if (coste < 0) throw new IllegalArgumentException();

        this.name = name;
        this.coste = coste;
    }

    void add(GroupComponent c) {throw new UnsupportedOperationException();}
    void remove(GroupComponent c) {throw new UnsupportedOperationException();}

    //getters
    public float getCoste() {return this.coste;}
    public String getName() {return this.name;}

    //util methods
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GroupComponent) { //mismo tipo
            GroupComponent groupObj = (GroupComponent) obj; //type cast
            return (this.getName().equals(groupObj.getName())); //compara el nombre, ya que es un atributo único
        } else return false;
    }
}
