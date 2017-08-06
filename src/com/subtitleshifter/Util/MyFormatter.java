package com.subtitleshifter.Util;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {

        return new Date(record.getMillis()) + " :: "
                + record.getSourceClassName() + " :: \n"
                + record.getLevel() + ": "
                + record.getMessage()+"\n\n";
    }

}