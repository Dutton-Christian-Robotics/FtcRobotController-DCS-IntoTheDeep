package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class NautilusArm extends DefenderBotSystem {

    public DcMotorEx leftMotor, rightMotor;
    TouchSensor retractedSensor;


    public NautilusArm(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        leftMotor = hm.get(DcMotorEx.class, NautilusConfiguration.ARM_LEFT_MOTOR_NAME);
        rightMotor = hm.get(DcMotorEx.class, NautilusConfiguration.ARM_RIGHT_MOTOR_NAME);
        leftMotor.setDirection(NautilusConfiguration.ARM_LEFT_MOTOR_DIRECTION);
        rightMotor.setDirection(NautilusConfiguration.ARM_RIGHT_MOTOR_DIRECTION);

        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        retractedSensor = hardwareMap.touchSensor.get(NautilusConfiguration.ARM_RETRACTED_SENSOR);

    }

    public void setPosition(int p) {
//        if (p > NautilusConfiguration.ARM_POSITION_MAX) {
//            p = NautilusConfiguration.ARM_POSITION_MAX;
//        } else if (p < NautilusConfiguration.ARM_POSITION_MIN) {
//            p = NautilusConfiguration.ARM_POSITION_MIN;
//        }

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftMotor.setTargetPosition(p);
        rightMotor.setTargetPosition(p);
        leftMotor.setPower(NautilusConfiguration.ARM_POWER_MAX);
        rightMotor.setPower(NautilusConfiguration.ARM_POWER_MAX);
    }

    public int getPosition() {
        return leftMotor.getCurrentPosition();
    }

    public void setRelativePosition(int p) {
        setPosition(getPosition() + p);
    }

    public boolean isFullyRetracted() {
        return retractedSensor.isPressed();
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

    public void setPower(double p) {
        leftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftMotor.setPower(p);
        rightMotor.setPower(p);
    }

    public void extend() {
        extend(1);

    }

    public void extend(double p) {
//        if (leftMotor.getCurrentPosition() < NautilusConfiguration.ARM_POSITION_MAX) {
            if (p > NautilusConfiguration.ARM_POWER_MAX) {
                p = NautilusConfiguration.ARM_POWER_MAX;
            }
            setPower(p);
//        } else {
//           setPower(0);
//        }
    }

    public void retract() {
        retract(1);
    }

    public void retract(double p) {
//        if (leftMotor.getCurrentPosition() > NautilusConfiguration.ARM_POSITION_MIN) {
        if (!isFullyRetracted()) {
            if (p > NautilusConfiguration.ARM_POWER_MAX) {
                p = NautilusConfiguration.ARM_POWER_MAX;
            }
            setPower(-1 * p);
        } else {
            setPower(0);
        }
    }

    public void stop() {
        setPower(0);
    }



}