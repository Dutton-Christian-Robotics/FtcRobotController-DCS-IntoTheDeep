package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

public class NautilusWrist extends DefenderBotSystem {

    private Servo servo;

    public NautilusWrist(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        servo = hardwareMap.servo.get(NautilusConfiguration.WRIST_SERVO_NAME);
    }


    public void gotoUpPosition() {
        servo.setPosition(NautilusConfiguration.WRIST_SERVO_POSITION_TOP);
    }

    public void gotoDownPosition() {
           servo.setPosition(NautilusConfiguration.WRIST_SERVO_POSITION_BOTTOM);
    }



}