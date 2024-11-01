package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPresets;

public class NautilusWrist extends DefenderBotSystem {

    private Servo servo;
    public DefenderPresets<Double> wristPresets;

    public NautilusWrist(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        servo = hardwareMap.servo.get(NautilusConfiguration.WRIST_SERVO_NAME);
        wristPresets = NautilusConfiguration.WRIST_PRESETS;
    }


    public void gotoUpPosition() {
        wristPresets.selectFirst();
        servo.setPosition(wristPresets.selected());
    }

    public void gotoMidPosition() {
        servo.setPosition(NautilusConfiguration.WRIST_SERVO_POSITION_MIDDLE);
    }


    public void gotoDownPosition() {
        wristPresets.selectLast();
        servo.setPosition(wristPresets.selected());
    }

    public void gotoNextDown() {
        wristPresets.selectNext();
        servo.setPosition(wristPresets.selected());
    }

    public void gotoNextUp() {
        wristPresets.selectPrevious();
        servo.setPosition(wristPresets.selected());
    }



}