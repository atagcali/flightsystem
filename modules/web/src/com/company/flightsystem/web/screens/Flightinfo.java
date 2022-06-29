package com.company.flightsystem.web.screens;

import com.company.flightsystem.entity.Flight;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

@UiController("flightsystem_Flightinfo")
@UiDescriptor("Flightinfo.xml")
@DialogMode(forceDialog = true, width = "750px")
public class Flightinfo extends Screen {
    @Inject
    private DateField<Date> dateDF;
    @Inject
    private TextField<String> departureTF;
    @Inject
    private TextField<String> destinationTF;
    @Inject
    private TextField<String> emptySeatTF;
    @Inject
    private TextField<String> priceTF;
    @Inject
    private Button ChangeButton;
    DataManager dataManager = AppBeans.get(DataManager.class);
    @Inject
    private Button delButton;

    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions mapScreenOptions = (MapScreenOptions) event.getOptions();
        Map<String,Object> params = mapScreenOptions.getParams();
        Flight flight = (Flight) params.get("item");

        dateDF.setValue(flight.getDate());
        departureTF.setValue(flight.getDeparture());
        destinationTF.setValue(flight.getDestination());
        emptySeatTF.setValue(flight.getEmptySeat());
        priceTF.setValue(flight.getPrice());

        ChangeButton.addClickListener(clickEvent -> {
           flight.setDate(dateDF.getValue());
           flight.setEmptySeat(emptySeatTF.getValue());
           flight.setPrice(priceTF.getValue());
           flight.setDestination(destinationTF.getValue());
           flight.setDeparture(departureTF.getValue());

           dataManager.commit(flight);

           close(StandardOutcome.CLOSE);
        });

        delButton.addClickListener(clickEvent -> {
           dataManager.remove(flight);
           close(StandardOutcome.CLOSE);
        });
    }









}