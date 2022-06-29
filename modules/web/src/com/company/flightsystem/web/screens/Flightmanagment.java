package com.company.flightsystem.web.screens;


import com.company.flightsystem.entity.Flight;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@UiController("flightsystem_Flightmanagment")
@UiDescriptor("flightManagment.xml")
public class Flightmanagment extends Screen {
    @Inject
    private CollectionLoader<Flight> flightLoad;
    @Inject
    private CollectionContainer<Flight> flightCol;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Table<Flight> flightsTable;

    DataManager dataManager = AppBeans.get(DataManager.class);
    @Inject
    private Button refreshBtn;

    @Subscribe
    public void onInit(InitEvent event) {
        flightLoad.load();
    }

    @Subscribe("flightsTable")
    public void onFlightsTableSelection(Table.SelectionEvent<Flight> event) {
        Flight flight = flightsTable.getSingleSelected();
        if(flight == null) return;
        Map<String, Object> params = new HashMap<>();
        params.put("item", flight);
        screenBuilders.screen(this).withScreenClass(Flightinfo.class)
                .withOpenMode(OpenMode.DIALOG).withOptions(new MapScreenOptions(params))
                .build().show();
    }

    @Subscribe("refreshBtn")
    public void onRefreshBtnClick(Button.ClickEvent event) {
//        dataManager.reload(flight1,"flightView");
        flightLoad.load();
    }


}