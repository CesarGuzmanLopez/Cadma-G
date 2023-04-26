package com.main.cadma.domain.relations;
import java.util.List;

import com.main.cadma.interfaces.ActionsCadma;
import com.main.cadma.interfaces.EventComplete;
import com.main.cadma.interfaces.StatusProcess;
public class Cadma1Generate implements ActionsCadma {
    private SmileGenerate smileGenerate;
    private boolean isActivate;
    private String path;
    private String pathCadma1;
    private List<EventComplete> importProcessEvent;
    private StatusProcess statusProcess;

    public Cadma1Generate(SmileGenerate smileGenerate) {
        this.smileGenerate = smileGenerate;
        isActivate = false;
        importProcessEvent = new java.util.ArrayList<EventComplete>();
        statusProcess = StatusProcess.EMPTY;
    }

    @Override
    public void upload() {

        throw new RuntimeException("Upload not used in this context");
    }

    @Override
    public void generate() {
        if(smileGenerate.getStatusProcess() != StatusProcess.COMPLETE) {
            throw new RuntimeException("SmileGenerate not complete");
        }
        if(!isActivate) {
            throw new RuntimeException("Cadma1Generate not activate");
        }
        path = smileGenerate.getParentPath();
        pathCadma1 = path + "cadma1/";
        //verifico si existe el directorio de cadma1
        java.io.File file = new java.io.File(pathCadma1);

        if(file.exists()) {
            statusProcess = StatusProcess.INCOMPLETE;
        } else {
            file.mkdir();
            statusProcess = StatusProcess.COMPLETE;
        }

        for(EventComplete event : importProcessEvent) {
            event.execute();
        }

    }

    @Override
    public void view() {

    }

    @Override
    public void addObtainEvent(EventComplete passEvent) {
        importProcessEvent.add(passEvent);
    }

    @Override
    public StatusProcess getStatusProcess() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDelete() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isUpload() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isGenerate() {
        // TODO Auto-generated method stub
        return true;
    }

    public void enableGenerate() {
        isActivate = true;
    }
}
