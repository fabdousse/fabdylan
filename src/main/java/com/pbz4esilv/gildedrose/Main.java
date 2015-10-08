package com.pbz4esilv.gildedrose;

/**
 * Created by Fabien on 02/10/2015.
 */
class Main {
    public static void main(String[] args){

        Inventory inv = new Inventory(1,1,2014); // inventaire crée le 1er janvier 2015
        inv.Update();       // mettre à jour l'inventaire
        inv.Add_item(inv.Get_item()); // ajouter un item





    }



}
