--Project0 - DealershipSystem, table incrementers
/* 
Auto increments the car id, when one is created,
*/
CREATE SEQUENCE car_it
    START WITH 11
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER car_it
BEFORE INSERT ON Cars
FOR EACH ROW
BEGIN
    IF :new.UserID IS NULL
    THEN
        SELECT customerIT.nextval INTO :new.UserID FROM dual;
    END IF;
END;

/* 
*/
CREATE SEQUENCE customerIT
    START WITH 11
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER customerIT
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    IF :new.UserID IS NULL
    THEN
        SELECT customerIT.nextval INTO :new.UserID FROM dual;
    END IF;
END;