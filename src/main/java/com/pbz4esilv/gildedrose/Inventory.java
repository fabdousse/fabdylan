package com.pbz4esilv.gildedrose;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Inventory {

    private Item[] items;
    private boolean[] conjured;
    private int jour;
    private int mois;
    private int annee;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };
        conjured=new boolean[]{false,false,false,false,false,true};

    }

    public void Update(){
        double d=nb_Update();
        if(d!=0)
        for (double i=0;i<d;i++)
            this.updateQuality();
        else System.out.println("rien n'a change");
    }

    public static double getDaysBetweenDates(Date theEarlierDate, Date theLaterDate) {
        double result = Double.POSITIVE_INFINITY;
        if (theEarlierDate != null && theLaterDate != null) {
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
            Calendar aCal = Calendar.getInstance();
            aCal.setTime(theEarlierDate);
            long aFromOffset = aCal.get(Calendar.DST_OFFSET);
            aCal.setTime(theLaterDate);
            long aToOffset = aCal.get(Calendar.DST_OFFSET);
            long aDayDiffInMili = (theLaterDate.getTime() + aToOffset) - (theEarlierDate.getTime() + aFromOffset);
            result = ((double) aDayDiffInMili / MILLISECONDS_PER_DAY);
        }
        return result;
    }

    public  double nb_Update(){
     double nb_de_update;
        Calendar now = Calendar.getInstance();
        Date nowdate=now.getTime();
        Calendar before = Calendar.getInstance();
        before.set(this.annee,this.mois,this.jour);
        Date beforedate= now.getTime();
        nb_de_update=getDaysBetweenDates(beforedate,nowdate);
        this.mois=now.get(Calendar.MONTH);
        this.jour=now.get(Calendar.DAY_OF_MONTH);
        this.annee=now.get(Calendar.YEAR);
        return  nb_de_update;
    }


    public boolean[] SetConjured(){
        boolean tab[]=new boolean[items.length];
        for(int i=0;i<items.length;i++)
        {
            System.out.print(items[i].getName()+" est-il ensorcelle ?\n");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if(str=="oui"||str=="yes")
                tab[i]=true;
            else tab[i]=false;

        }
        return tab;
    }


    public void updateQuality() {
        conjured=this.SetConjured();
        for (int i = 0; i < items.length; i++) {

            if (items[i].getName() != "Aged Brie"
                    && items[i].getName() != "Backstage passes to a TAFKAL80ETC concert"
                    && items[i].getQuality() > 0
                    && items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                        items[i].setQuality(items[i].getQuality() - 1);
                if(conjured[i]==true)
                    items[i].setQuality(items[i].getQuality() - 1);


            }

            else {

                if (items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);

                    if (items[i].getName() == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }

            if (items[i].getSellIn() < 0) {
                if (items[i].getName() != "Aged Brie") {
                    if (items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getQuality() > 0) {
                            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                                items[i].setQuality(items[i].getQuality() - 1);
                                if (conjured[i]==true)
                                    items[i].setQuality(items[i].getQuality() - 1);

                            }
                        }
                    }

                    else {
                        items[i].setQuality(items[i].getQuality() - items[i].getQuality());
                    }
                }

                else {

                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);
                    }
                }
            }
        }
    }
}