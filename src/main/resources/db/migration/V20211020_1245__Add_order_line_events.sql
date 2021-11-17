CREATE TABLE ORDER_LINE_EVENT
(
    ID                          BIGSERIAL PRIMARY KEY,
    ORDER_ID                    VARCHAR NULL,
    TRAVEL_DATE                 TIMESTAMP     NULL,
    TIMESTAMP                   TIMESTAMP     NOT NULL
);



