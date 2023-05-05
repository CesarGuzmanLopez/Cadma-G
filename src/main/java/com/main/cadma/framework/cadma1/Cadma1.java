package com.main.cadma.framework.cadma1;

import java.util.ArrayList;
import java.util.List;

import com.main.cadma.interfaces.Cadma1Interface;
import com.main.cadma1.views.Cadma1ViewGenerator;
import com.main.shared.domain.Molecule;
import com.main.shared.domain.cadma.interfaces.EventComplete;
import com.main.shared.domain.cadma.interfaces.EventUpdateData;


public class Cadma1 implements Cadma1Interface {

    private List<EventComplete> importProcessEvent;
    private List<EventUpdateData> updateDataEvent;
    Cadma1ViewGenerator cadma1ViewGenerator;
    public Cadma1() {
        importProcessEvent = new ArrayList<EventComplete>();
        updateDataEvent = new ArrayList<EventUpdateData>();
        cadma1ViewGenerator = new Cadma1ViewGenerator();
    }

    @Override
    public void showGenerate(Molecule molecule, List<Molecule> substitutes, List<Molecule> Generate, String path) {

        cadma1ViewGenerator.initialize( molecule,  substitutes, Generate, path);

    }

    @Override
    public void addGenerateEvent(EventComplete completeEvent) {
        importProcessEvent.add(completeEvent);
    }
    @Override
    public void addEventUpdateData(EventUpdateData eventUpdateData) {
        updateDataEvent.add(eventUpdateData);
    }

    private void executeImportProcessEvent() {
        for (EventComplete eventComplete : importProcessEvent) {
            eventComplete.execute();
        }
    }

    private void executeUpdateDataEvent() {
        for (EventUpdateData eventUpdateData : updateDataEvent) {
            eventUpdateData.updateData();
        }
    }
}
