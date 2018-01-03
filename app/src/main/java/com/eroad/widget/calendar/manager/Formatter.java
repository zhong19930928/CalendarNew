package com.eroad.widget.calendar.manager;

import org.joda.time.LocalDate;

/**
 * Created by Blaz Solar on 26/04/15.
 */
public interface Formatter {

    String getDayName( LocalDate date);
    String getHeaderText(int type,   LocalDate from,  LocalDate to, LocalDate selected);

}
