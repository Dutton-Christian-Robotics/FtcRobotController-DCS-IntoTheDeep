package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class NautilusEffects extends DefenderBotSystem {
    RevBlinkinLedDriver leds;

    public NautilusEffects(HardwareMap hm, DefenderBot b) {
        super(hm, b);
        leds = hardwareMap.get(RevBlinkinLedDriver.class, NautilusConfiguration.EFFECTS_LEDS_NAME);

    }

    public void setPattern(RevBlinkinLedDriver.BlinkinPattern p) {
        leds.setPattern(p);
    }

    public void solidBlue() {
        setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }

    public void solidRed() {
        setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
    }


    public void scanBlue() {
        setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE);

    }

    public void scanRed() {
        setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_RED);
    }

    public void heartbeatBlue() {
        setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE);
    }

    public void heartbeatRed() {
        setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED);
    }

    public void strobeGold() {
        setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD);
    }


}
