package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class NautilusManipulatorPosition {
    public double shoulderPosition;
    public double armPosition;
    public double wristPosition;

    public NautilusManipulatorPosition(int shoulder, int arm, double wrist) {
        shoulderPosition = shoulder;
        armPosition = arm;
        wristPosition = wrist;
    }
}
