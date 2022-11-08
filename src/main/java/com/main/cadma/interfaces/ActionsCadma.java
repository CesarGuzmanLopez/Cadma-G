package com.main.cadma.interfaces;

import com.main.smileit.interfaces.EventInterface;

public interface ActionsCadma {
    /**
     * Upload a file.
     */
     void upload();

    /**
     * Update a file.
     */
     void update();

    /**
     * Get the name of process.
     */
     void getName();

    /**
     * Generate a process.
     */

     void generate();

    /**
     * Add event to obtain the result.
     * @param completeEvent
     */
     void addObtainEvent(EventInterface completeEvent);

    /**
     * View the result.
     */
     void view();
}
