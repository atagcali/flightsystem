create table FLIGHTSYSTEM_FLIGHT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DEPARTURE varchar(255),
    DESTINATION varchar(255),
    PRICE varchar(255),
    EMPTY_SEAT varchar(255),
    --
    primary key (ID)
);