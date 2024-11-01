package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BooleanSupplier;

public class DefenderState {
    public DefenderStateMachine dsm;
    protected String label;
    protected int identifier;
    public InternalState state;

    private BooleanSupplier onEnter;
    protected Runnable enterCode;
    protected ArrayList<Integer> excludedStates;

    protected Runnable loopCode;
    protected Runnable exitCode;

    public enum InternalState {
        INACTIVE,
        ENTERING,
        ACTIVE,
        EXiTING
    }

    DefenderState() {
        state = InternalState.INACTIVE;
        excludedStates = new ArrayList<>();
    }

    public static DefenderState create(int id) {
        DefenderState s = new DefenderState();
        s.identifier = id;
        return s;
    }

    public DefenderState enterWhen(BooleanSupplier b, Runnable r) {
        onEnter = b;
        enterCode = r;

        return this;
    }

    public DefenderState exclude(Integer[] arr) {
        excludedStates.addAll(Arrays.asList(arr));

        return this;
    }

    public boolean isActive() {
        return state != InternalState.INACTIVE;
    }

    public boolean checkEntry() {
        boolean b = onEnter.getAsBoolean();
        if (b) {
            state = InternalState.ENTERING;
            for (Integer id: excludedStates) {
                int i = dsm.indexForStateMatchingIdentifier(id);
                DefenderState s = dsm.stateAtIndex(i);
                s.forceExit();
            }
        }
        return b;
    }

    public void forceExit() {
        state = InternalState.EXiTING;
    }

    public void enter() {
        if (state == InternalState.ENTERING) {
            if (enterCode != null) {
                enterCode.run();
            }
            state = InternalState.ACTIVE;
        }
    }

    public void loop() {
        if (state == InternalState.ACTIVE) {
            if (loopCode !=  null) {
                loopCode.run();
            }
        }
    }

    public void exit() {
        if (state == InternalState.EXiTING) {
            if (exitCode != null) {
                exitCode.run();
            }
            state = InternalState.INACTIVE;
        }
    }




}
