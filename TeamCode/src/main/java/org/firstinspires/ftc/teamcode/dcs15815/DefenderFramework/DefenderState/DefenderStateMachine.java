package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderState;
import java.util.ArrayList;

public class DefenderStateMachine {
    private ArrayList<DefenderState> stateList;


    public DefenderStateMachine() {
        stateList = new ArrayList<>();
    }

    public DefenderStateMachine addState(DefenderState s) {
        s.dsm = this;
        stateList.add(s);
        return this;
    }

    public int indexForStateMatchingIdentifier(int id) {
        int foundState = -1;
        for (DefenderState s: stateList) {
            if (s.identifier == id) {
                foundState = stateList.indexOf(s);
            }
        }
        return foundState;
    }

    public DefenderState stateAtIndex(int i) {
        return stateList.get(i);
    }

    public void run() {
        for (DefenderState s: stateList) {
            s.checkEntry();
        }

        for (DefenderState s: stateList) {
            s.enter();
        }

        for (DefenderState s: stateList) {
            s.exit();
        }

        for (DefenderState s: stateList) {
            s.loop();
        }
    }

}
