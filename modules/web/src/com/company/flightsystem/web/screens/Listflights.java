package com.company.flightsystem.web.screens;

import com.company.flightsystem.entity.Flight;

import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.screen.*;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@UiController("flightsystem_Listflights")
@UiDescriptor("ListFlights.xml")
public class Listflights extends Screen {


    @Inject
    private VBoxLayout box;
    @Inject
    private Fragments fragments;

    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions mapScreenOptions = (MapScreenOptions) event.getOptions();
        Map<String,Object> params = mapScreenOptions.getParams();
        List<Flight> flightList= new ArrayList<>();
        Collection<Object> values = params.values();
        for (Object value : values) {
            flightList.add((Flight) value);
        }
        if(flightList.isEmpty()){
            Listflightsfragment fragment = fragments.create(this,Listflightsfragment.class);
            box.add(fragment.getFragment());
        }
        for(Flight flight:flightList) {
            Listflightsfragment fragment = fragments.create(this, Listflightsfragment.class);
            fragment.setFlight(flight);
            box.add(fragment.getFragment());
        }

    }

}