package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class NautilusShoulder extends DefenderBotSystem {

    public DcMotorEx leftMotor, rightMotor;
    DigitalChannel upSensor, downSensor;


    public NautilusShoulder(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        leftMotor = hm.get(DcMotorEx.class, NautilusConfiguration.SHOULDER_LEFT_MOTOR_NAME);
        rightMotor = hm.get(DcMotorEx.class, NautilusConfiguration.SHOULDER_RIGHT_MOTOR_NAME);
        leftMotor.setDirection(NautilusConfiguration.SHOULDER_LEFT_MOTOR_DIRECTION);
        rightMotor.setDirection(NautilusConfiguration.SHOULDER_RIGHT_MOTOR_DIRECTION);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        downSensor = hardwareMap.digitalChannel.get(NautilusConfiguration.SHOULDER_DOWN_SENSOR);
        downSensor.setMode(DigitalChannel.Mode.INPUT);

        upSensor = hardwareMap.digitalChannel.get(NautilusConfiguration.SHOULDER_UP_SENSOR);
        upSensor.setMode(DigitalChannel.Mode.INPUT);

    }

    public void setPosition(int p) {
        setPosition(p, NautilusConfiguration.SHOULDER_POWER_MAX);
    }

    public void gotoAutonIntakePosition() {
        setPosition(NautilusConfiguration.SHOULDER_POSITION_AUTON_INTAKE);
    }

    public void gotoAutonTravelPosition() {
        setPosition(NautilusConfiguration.SHOULDER_POSITION_AUTON_TRAVEL);
    }

    public void setPosition(int p, double power) {
//        if (p > NautilusConfiguration.SHOULDER_POSITION_MAX) {
//            p = NautilusConfiguration.SHOULDER_POSITION_MAX;
//        } else if (p < NautilusConfiguration.SHOULDER_POSITION_MIN) {
//            p = NautilusConfiguration.SHOULDER_POSITION_MIN;
//        }

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftMotor.setTargetPosition(p);
        rightMotor.setTargetPosition(p);
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public int getPosition() {
        return leftMotor.getCurrentPosition();
    }

    public void setRelativePosition(int p) {
        setPosition(getPosition() + p);
    }



//    public void gotoPosition(int p) {
//	   int tolerance = 5;
//	   double power;
//
//	   leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//	   rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//
//	   while (Math.abs(p - leftMotor.getCurrentPosition()) > tolerance) {
//		  power = pidController.calculatePower(0, leftMotor.getCurrentPosition());
//		  leftMotor.setPower(power);
//		  rightMotor.setPower(power);
//	   }
//	   leftMotor.setPower(0);
//	   rightMotor.setPower(0);
//    }
//
//    public void gotoPosition(int p, Runnable r) {
//	   gotoPosition(p);
//	   r.run();
//    }

    public boolean isFullyUp() {
        return !upSensor.getState();
    }

    public boolean isFullyDown() {
        return !downSensor.getState();
    }

    public void setPower(double p) {
        leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor.setPower(p);
        rightMotor.setPower(p);
    }

    public void tiltUp() {
        tiltUp(1);
    }

    public void tiltUp(double p) {
//        if (leftMotor.getCurrentPosition() < NautilusConfiguration.SHOULDER_POSITION_MAX) {
        if (!isFullyUp()) {
            if (p > NautilusConfiguration.SHOULDER_POWER_MAX) {
                p = NautilusConfiguration.SHOULDER_POWER_MAX;
            }
            setPower(p);
        } else {
            setPower(0);
        }
    }

    public void tiltDown() {
        tiltDown(1);
    }

    public void tiltDown(double p) {
//        if (leftMotor.getCurrentPosition() > NautilusConfiguration.SHOULDER_POSITION_MIN) {
        if (!isFullyDown()) {
            if (p > NautilusConfiguration.SHOULDER_POWER_MAX) {
                p = NautilusConfiguration.SHOULDER_POWER_MAX;
            }
            setPower(-1 * p);
        } else {
            setPower(0);
        }
    }

    public void holdCurrentPosition() {
        int shoulderPosition = getPosition();

        setPosition(shoulderPosition);

    }


    public void stop() {
        setPower(0);
    }



}