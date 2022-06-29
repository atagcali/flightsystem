package com.company.flightsystem.web.screens;

import com.company.flightsystem.entity.Flight;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.app.main.MainScreen;

import javax.inject.Inject;
import java.util.*;


@UiController("PurchseScr")
@UiDescriptor("PurchseScr.xml")
public class PurchseScr extends MainScreen {
    @Inject
    private CollectionContainer<Flight> flightCollection;
    @Inject
    private CollectionLoader<Flight> fcLoader;
    @Inject
    private LookupField<String> departureLuF;
    @Inject
    private LookupField<String> destinationLuF;


    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe
    public void onInit(InitEvent event) {
        fcLoader.load();
        List<Flight> flightList = flightCollection.getItems();
        List<String> strList = new ArrayList<>();
        for (Flight flight:flightList){
            if(!strList.contains(flight.getDeparture())) {
                strList.add(flight.getDeparture());
            }
        }
        departureLuF.setOptionsList(strList);
        strList.clear();

        for(Flight flight:flightList){
            if(!strList.contains(flight.getDestination())) {
                strList.add(flight.getDestination());
            }
        }
        destinationLuF.setOptionsList(strList);
    }

    @Subscribe("searchBtn")
    public void onSearchBtnClick(Button.ClickEvent event) {
        List<Flight> flightList = flightCollection.getItems();
        Map<String, Object> params = new HashMap<>();
        int i = 0;
        Date date = new Date();
        for(Flight flight:flightList){
            if(departureLuF.getValue().equals(flight.getDeparture())
                    && destinationLuF.getValue().equals(flight.getDestination())
                            && flight.getDate().after(date) ){

                params.put(String.valueOf(i), flight);
                i++;
            }
        }
        screenBuilders.screen(this).withScreenClass(Listflights.class)
                .withOpenMode(OpenMode.DIALOG).withOptions(new MapScreenOptions(params))
                .build().show();
    }








}