package com.main.shared.domain.cadma.interfaces;
@FunctionalInterface
public interface EventComplete {
    /**
     * This method is called when the is completed the process.
     */
    void execute( );
}
