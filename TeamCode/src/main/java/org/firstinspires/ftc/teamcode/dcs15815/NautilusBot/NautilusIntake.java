package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDelayedSequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NautilusIntake extends DefenderBotSystem {

    private CRServo leftServo, rightServo;
    private TouchSensor loadedSensor;

    public NautilusIntake(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        leftServo = hardwareMap.crservo.get(NautilusConfiguration.INTAKE_LEFT_SERVO_NAME);
        rightServo = hardwareMap.crservo.get(NautilusConfiguration.INTAKE_RIGHT_SERVO_NAME);

        loadedSensor = hardwareMap.touchSensor.get(NautilusConfiguration.INTAKE_LOADED_SENSOR);

    }

    public void intake() {
        leftServo.setPower(NautilusConfiguration.INTAKE_LEFT_IN_POWER);
        rightServo.setPower(NautilusConfiguration.INTAKE_RIGHT_IN_POWER);
    }

    public void expel() {
        leftServo.setPower(NautilusConfiguration.INTAKE_LEFT_OUT_POWER);
        rightServo.setPower(NautilusConfiguration.INTAKE_RIGHT_OUT_POWER);

    }

    public void stop() {
        leftServo.setPower(0);
        rightServo.setPower(0);
    }

    public boolean isLoaded() {
        return !loadedSensor.isPressed();
    }


}