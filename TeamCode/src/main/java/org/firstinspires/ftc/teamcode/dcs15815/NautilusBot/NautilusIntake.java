package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

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
        expel(NautilusConfiguration.INTAKE_LEFT_OUT_POWER, NautilusConfiguration.INTAKE_RIGHT_OUT_POWER);
    }

    public void expelToTheLeft() {
        expel(NautilusConfiguration.INTAKE_LEFT_OUT_TO_LEFT_POWER, NautilusConfiguration.INTAKE_RIGHT_OUT_TO_LEFT_POWER);
    }


    public void expel(double leftPower, double rightPower) {
        leftServo.setPower(leftPower);
        rightServo.setPower(rightPower);

    }

    public void stop() {
        leftServo.setPower(0);
        rightServo.setPower(0);
    }

    public boolean isLoaded() {
        return !loadedSensor.isPressed();
    }


}