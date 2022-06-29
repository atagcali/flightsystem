package com.company.flightsystem.web.screens;

import com.company.flightsystem.entity.Flight;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;


import javax.inject.Inject;

@UiController("flightsystem_Listflightsfragment")
@UiDescriptor("ListFlightsFragment.xml")
public class Listflightsfragment extends ScreenFragment {
    @Inject
    private Button button;

    private Flight flight;
    @Inject
    private Dialogs dialogs;
    DataManager dataManager = AppBeans.get(DataManager.class);

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    @Subscribe("button")
    public void onButtonClick(Button.ClickEvent event) {
        if(flight != null) {
            dialogs.createMessageDialog()
                    .withCaption("Bilet Alındı ( " + flight.getDeparture() + " to " +
                            flight.getDestination() + "), " + flight.getDate().toString() + " price is :" + flight.getPrice())
                    .show();
            int newEmpty = Integer.valueOf(flight.getEmptySeat());
            newEmpty--;
            flight.setEmptySeat(String.valueOf(newEmpty));
            dataManager.commit(flight);
        }
    }

    @Subscribe
    public void onInit(InitEvent event) {
        if(flight!= null){
            button.setCaption(flight.getDeparture()+ " to " + flight.getDestination()+" at " + flight.getDate());
        }
        else{
            button.setCaption("Böyle bir uçuş yok");
        }
    }


}