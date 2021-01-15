package com.SevenNine.essentials.Fragment;

import com.SevenNine.essentials.Bean.Sellbean;

import java.util.Comparator;

class Sortbyroll implements Comparator<Sellbean> {
    public int compare(Sellbean a, Sellbean b)
    {
        int actu1=Integer.parseInt(a.getActual_price());
        int actu2=Integer.parseInt(b.getActual_price());

        return actu1 - actu2;
    }
}
