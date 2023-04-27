package com.main.shared.domain.cadma.interfaces;

import com.main.cadma.interfaces.EventComplete;
import com.main.cadma.interfaces.EventUpdateData;

public interface ActionsCadma {
    /**
     * @return boolean if procces is posible upload
     */
     boolean isUpload();

    /**
     * Upload a file.
     */
     void upload();

    /**
     * @return boolean if procces is posible generate
     */
     boolean isGenerate();

    /**
     * Generate a process.
     */

     void generate();

    /**
     * View the result.
     */
     void view();

     /**
      *
      * @param completeEvent
      */
     public void addObtainEvent(final EventComplete completeEvent);

    /**
     *
     */
    StatusProcess getStatusProcess();
    /**
     * @return boolean if procces is posible delete
     */
    boolean isDelete();


    /**
     * delete this property
     * */
    void delete();

    /**
     *
     * @param eventUpdateData
     */
    void addEventUpdateData(EventUpdateData eventUpdateData);


}
