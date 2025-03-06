package cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;

public class Seat {
    private int row;
    private int column;
    private int price;

    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean purchased;

    @ConstructorProperties({"row","column"})
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }


    public Seat(int row, int column, int price, boolean purchased) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.purchased = purchased;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

}