package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

public class Property implements ISquare{
    private String name;
    private int price;
    private int rent;
    private Player owner;
    @Override
    public void execute(Player player) {

    }

    public Property(String s,int p, int r){
        name=s;
        price=p;
        rent=r;
        owner=null;
    }

    public String getName(){
     return name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
