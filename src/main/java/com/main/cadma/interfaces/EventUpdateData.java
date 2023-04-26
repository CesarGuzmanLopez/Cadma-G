package com.main.cadma.interfaces;
@FunctionalInterface
public interface EventUpdateData extends EventComplete{
    /**
     * This method is called when the is completed the process.
     */
    void execute( );
}
