package com.company.flightsystem.web.screens;

import com.company.flightsystem.entity.Flight;
import com.haulmont.cuba.gui.Fragments;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@UiController("flightsystem_Listpast")
@UiDescriptor("ListPast.xml")
public class Listpast extends Screen {
    @Inject
    private VBoxLayout box;
    @Inject
    private CollectionLoader<Flight> loaderFlight;
    @Inject
    private CollectionContainer<Flight> collectionFlight;
    @Inject
    private Fragments fragments;

    @Subscribe
    public void onInit(InitEvent event) {
        loaderFlight.load();
        List<Flight> flightList = collectionFlight.getItems();
        Date date = new Date();
        for(Flight flight:flightList){
            if(flight.getDate().before(date)){
                Listflightsfragment fragment = fragments.create(this, Listflightsfragment.class);
                fragment.setFlight(flight);
                box.add(fragment.getFragment());
            }
        }
    }


}