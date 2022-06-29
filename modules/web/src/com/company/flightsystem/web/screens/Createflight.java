package com.company.flightsystem.web.screens;

import com.company.flightsystem.entity.Flight;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;

@UiController("flightsystem_Createflight")
@UiDescriptor("createFlight.xml")
public class Createflight extends Screen {
    @Inject
    private TextField<String> departureTF;

    @Inject
    private DateField<Date> dateDF;

    @Inject
    private TextField<String> destinationTF;

    @Inject
    private TextField<String> priceTF;
    @Inject
    private TextField<String> emptySeatTF;

     @Inject
    private CollectionContainer<Flight> collectionFlight;
    DataManager dm = AppBeans.get(DataManager.class);
    @Inject
    private Notifications notifications;

    @Subscribe("createBtn")
    public void onCreateBtnClick(Button.ClickEvent event) {
         Flight flight = dm.create(Flight.class);

        flight.setDeparture(departureTF.getValue());
        flight.setDestination(destinationTF.getValue());
        flight.setPrice(priceTF.getValue());
        flight.setEmptySeat(emptySeatTF.getValue());
        flight.setDate(dateDF.getValue());
        dm.commit(flight);
        notifications.create().withCaption("Bilet satın alındı").withDescription("Bilet satın alındı").show();
        close(StandardOutcome.CLOSE);
    }



    


}