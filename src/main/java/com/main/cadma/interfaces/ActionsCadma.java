package com.main.cadma.interfaces;


public interface ActionsCadma {
    /**
     * Upload a file.
     */
     void upload();


    /**
     * Generate a process.
     */

     void generate();

    /**
     * View the result.
     */
     void view();
    /**
     * @return parent path
     */

     /**
      *
      * @param completeEvent
      */
     public void addObtainEvent(final EventComplete completeEvent);
}
