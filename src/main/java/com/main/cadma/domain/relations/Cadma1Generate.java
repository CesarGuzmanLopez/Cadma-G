package com.main.cadma.domain.relations;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import com.main.cadma.interfaces.Cadma1Interface;
import com.main.common.Constant;
import com.main.shared.domain.cadma.interfaces.ActionsCadma;
import com.main.shared.domain.cadma.interfaces.EventComplete;
import com.main.shared.domain.cadma.interfaces.EventUpdateData;
import com.main.shared.domain.cadma.interfaces.StatusProcess;
public class Cadma1Generate implements ActionsCadma {
    private SmileGenerate smileGenerate;
    private boolean isActivate;
    private String path;
    private String pathCadma1;
    private List<EventComplete> importProcessEvent;
    private StatusProcess statusProcess;
    private List<EventUpdateData> eventsUpdateData;

    private Cadma1Interface cadma1;
    public Cadma1Generate(SmileGenerate smileGenerate, Cadma1Interface cadma1) {
        this.smileGenerate = smileGenerate;
        isActivate = false;
        importProcessEvent = new java.util.ArrayList<EventComplete>();
        eventsUpdateData = new java.util.ArrayList<EventUpdateData>();
        statusProcess = StatusProcess.EMPTY;
        this.cadma1 = cadma1;
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
        if( path == null || path.length() <= 0) {
            throw new RuntimeException("path not set");
        }

        //verifico si existe el directorio de cadma1
        java.io.File file = new java.io.File(pathCadma1);
        if(statusProcess == StatusProcess.EMPTY || !file.exists()) {
            file.mkdir();
        }else{
            statusProcess = StatusProcess.IN_PROCESS;
        }
        runEventUpdateData();
        cadma1.showGenerate(smileGenerate.getSmilePrincipal().getValue(), smileGenerate.getSubstitutes().getValue(), smileGenerate.getGenerateSmiles().getValue(), path);
    }

    @Override
    public void view() {

    }

    @Override
    public void addObtainEvent(EventComplete passEvent) {
        importProcessEvent.add(passEvent);
        cadma1.addGenerateEvent(passEvent);
    }

    @Override
    public StatusProcess getStatusProcess() {
        return statusProcess;
    }

    @Override
    public void delete() {
        if(!isActivate) {
            throw new RuntimeException("Cadma1Generate not activate");
        }
        if(pathCadma1 == null || pathCadma1.length() == 0) {
            throw new RuntimeException("Cadma1Generate not activate");
        }
        java.io.File file = new java.io.File(pathCadma1);
        if(!file.exists())
            throw new RuntimeException("Cadma1Generate not activate");
        if(JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the directory?", "Delete Directory", JOptionPane.YES_NO_OPTION)) {
            return;
        }

        deleteDirectory(file);

        statusProcess = StatusProcess.EMPTY;
        isActivate = false;
        runEventUpdateData();
        enableGenerate();
    }

    private void deleteDirectory(File file) {
        if(file.isDirectory()) {
            for(File f : file.listFiles()) {
                deleteDirectory(f);
            }
        }
        file.delete();
    }

    @Override
    public boolean isDelete() {
        return true;
    }

    @Override
    public boolean isUpload() {
        return false;
    }

    @Override
    public boolean isGenerate() {
        return true;
    }

    public void enableGenerate() {
        if(isActivate) {
            throw new RuntimeException("Cadma1Generate is activate");
        }
        if(smileGenerate.getStatusProcess() != StatusProcess.COMPLETE) {
            statusProcess = StatusProcess.EMPTY;
            throw new RuntimeException("SmileGenerate not complete");

        }
        if(smileGenerate.getParentPath() == null || smileGenerate.getParentPath().length() == 0) {
            throw new RuntimeException("SmileGenerate not activate");
        }
        isActivate = true;
        path = smileGenerate.getParentPath();
        pathCadma1 = path + "/" + Constant.CADMA1_SUB_FOLDER_PATH;
        java.io.File file = new java.io.File(pathCadma1);
        if(file.exists()){
            statusProcess = StatusProcess.IN_PROCESS;
        }else {
            statusProcess = StatusProcess.NOT_IMPLEMENTED;
        }
        runEventUpdateData();
    }
   @Override
    public void addEventUpdateData(EventUpdateData eventUpdateData) {
        eventsUpdateData.add(eventUpdateData);
    }

    private void runEventUpdateData() {
        for (EventUpdateData event : eventsUpdateData) {
            event.updateData();
        }
    }
}
