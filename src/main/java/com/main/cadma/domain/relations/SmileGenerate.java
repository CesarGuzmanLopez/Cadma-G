package com.main.cadma.domain.relations;

import com.main.cadma.interfaces.ActionsCadma;
import com.main.cadma.interfaces.EventComplete;
import com.main.cadma.interfaces.SmilesGuiInterface;
import com.main.cadma.interfaces.SmilesUploadInterface;
import com.main.cadma.interfaces.StatusProcess;

public class SmileGenerate implements ActionsCadma {
    private SmilesGuiInterface smilesGui;
    private SmilesUploadInterface smilesUpload;
    private StatusProcess smilesStatusProcess;

    public SmileGenerate(final SmilesGuiInterface smilesGui, final SmilesUploadInterface smilesUpload) {
        this.smilesGui = smilesGui;
        this.smilesUpload = smilesUpload;
        this.smilesStatusProcess = StatusProcess.EMPTY;

    }

    /**
     * @return parent path
     */
    public String getParentPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upload() {
        smilesUpload.showUpload();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate() {
        smilesGui.showGenerate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void view() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void addObtainEvent(final EventComplete completeEvent) {
        smilesGui.addGenerateEvent(completeEvent);
        smilesUpload.add(completeEvent);
    }

    public StatusProcess getStatusProcess() {
        return smilesStatusProcess;
    }
}
