package org.firstinspires.ftc.teamcode.dcs15815.NautilusBot;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPresets;

public class NautilusConfiguration extends DefenderBotConfiguration {

    /* DRIVETRAIN -------------------------------------------------- */

    public static String DRIVETRAIN_BACK_LEFT_MOTOR_NAME = "back_left_motor"; // Control Hub, Motor 0
    public static String DRIVETRAIN_FRONT_LEFT_MOTOR_NAME = "front_left_motor"; // Control Hub, Motor 1
    public static String DRIVETRAIN_FRONT_RIGHT_MOTOR_NAME = "front_right_motor"; // Control Hub, Motor 2
    public static String DRIVETRAIN_BACK_RIGHT_MOTOR_NAME = "back_right_motor"; // Control Hub, Motor 3
    public static DcMotorSimple.Direction DRIVETRAIN_BACK_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONT_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONT_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction DRIVETRAIN_BACK_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;

    public static double DRIVETRAIN_POWER_MAX = 0.35;
    public static int DRIVETRAIN_MAX_TICKS_PER_SECOND = 2800;

    /* SHOULDER -------------------------------------------------------- */

    public static String SHOULDER_LEFT_MOTOR_NAME = "shoulder_left_motor"; // Expansion Hub, Motor 0
    public static String SHOULDER_RIGHT_MOTOR_NAME = "shoulder_right_motor"; // Expansion Hub, Motor 1
    public static DcMotorSimple.Direction SHOULDER_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction SHOULDER_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static String SHOULDER_DOWN_SENSOR = "shoulder_down_sensor"; // Control Hub, Digital 0
    public static String SHOULDER_UP_SENSOR = "shoulder_up_sensor"; // Control Hub, Digital 2
    public static double SHOULDER_POWER_MAX = 1;
    public static double SHOULDER_POWER_NORMAL = 0.6;
    public static int SHOULDER_ASCEND_PREP_MAX = 3200;

    public static int SHOULDER_POSITION_MAX = 3200;
    public static int SHOULDER_POSITION_MIN = 0;
    public static int SHOULDER_POSITION_ASCENDED = -200;

    public static int SHOULDER_POSITION_DELTA = 250;
    // left straight out 1093
    // right straight out 1092
    // left up 45 2030
    // right up 45 2003
    // left straight up 2858
    // right straight up 2757

    /* ARM -------------------------------------------------------- */

    public static String ARM_LEFT_MOTOR_NAME = "arm_left_motor"; // Expansion Hub, Motor 2
    public static String ARM_RIGHT_MOTOR_NAME = "arm_right_motor"; // Expansion Hub, Motor 3
    public static DcMotorSimple.Direction ARM_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction ARM_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static String ARM_RETRACTED_SENSOR = "arm_retracted_sensor"; // Expansion Hub, Digital 0
    public static double ARM_POWER_MAX = 1;
    public static double ARM_POWER_NORMAL = 0.6;
    public static int ARM_POSITION_ASCENDED = -400;

    public static int ARM_POSITION_MIN = 0;
    public static int ARM_POSITION_MAX = 3000;
    public static int ARM_POSITION_DELTA = 125;

    // all the way out 3000

    /* WRIST -------------------------------------------------------- */

    public static String WRIST_SERVO_NAME = "wrist_servo"; // Expansion Hub, Servo 0
    public static double WRIST_SERVO_POSITION_TOP = 0.9;
    public static double WRIST_SERVO_POSITION_MIDDLE = 0.3;
    public static double WRIST_SERVO_POSITION_BOTTOM = 0.15;
    public static double WRIST_POSITION_DELTA = 0.1;

    public static DefenderPresets<Double> WRIST_PRESETS = new DefenderPresets<>(
            NautilusConfiguration.WRIST_SERVO_POSITION_TOP,
            NautilusConfiguration.WRIST_SERVO_POSITION_MIDDLE,
            NautilusConfiguration.WRIST_SERVO_POSITION_BOTTOM
    );

    /* INTAKE -------------------------------------------------------- */

    public static String INTAKE_LEFT_SERVO_NAME = "intake_left_servo"; // Expansion Hub, Servo 1
    public static String INTAKE_RIGHT_SERVO_NAME = "intake_right_servo"; // Expansion Hub, Servo 2
    public static double INTAKE_LEFT_IN_POWER = 1;
    public static double INTAKE_RIGHT_IN_POWER = -1;
    public static double INTAKE_LEFT_OUT_POWER = -.7;
    public static double INTAKE_RIGHT_OUT_POWER = .7;


    /* GAMEPADS -------------------------------------------------------- */

    public static double GAMEPAD2_RIGHT_STICK_CURVE = 2;
    public static double GAMEPAD2_RIGHT_STICK_MAX = 1;

    public static double GAMEPAD1_LEFT_STICK_Y_CURVE = 2.5;
    public static double GAMEPAD1_LEFT_STICK_Y_MAX = 1;

    public static double GAMEPAD1_RIGHT_STICK_X_CURVE = 2;
    public static double GAMEPAD1_RIGHT_STICK_X_MAX = 0.5;


    /* NAVIGATON -------------------------------------------------------- */

    public static String IMU_SENSOR_NAME = "imu";
    public static AxesOrder IMU_AXES_ORDER = AxesOrder.XYZ;

    // These are constants for the homegrown navigation.
    // This is unused since implementing RoadRunner
    public static double NAVIGATION_POWER_DEFAULT = 0.65;
    public static long NAVIGATION_TIMEOUT_DEFAULT = 10000;
    public static double NAVIGATION_TOLERANCE_ROTATION = 0.3;
    public static double NAVIGATION_TOLERANCE_X = 1.0;
    public static double NAVIGATION_TOLERANCE_Y = 1.0;
    public static double NAVIGATION_GEAR_RATIO = 1;
    public static long NAVIGATION_TICKS_PER_ROTATION = 292; // actually 292.04, geared at 3:1 (actually 2.89:1) and 4:1 (3.61)
    //    public static long NAVIGATION_TICKS_PER_ROTATION = 280;
    public static double NAVIGATION_WHEEL_RADIUS = 1.88976;
    //    public static double NAVIGATION_WHEEL_RADIUS = 2;
    public static double NAVIGATION_INCHES_PER_TICK = (2 * Math.PI * NAVIGATION_GEAR_RATIO * NAVIGATION_WHEEL_RADIUS) / NAVIGATION_TICKS_PER_ROTATION;
    public static double NAVIGATION_ROTATION_KP = 0.05;
    public static double NAVIGATION_ROTATION_KI = 0;
    public static double NAVIGATION_ROTATION_KD = 0;


    /* PRESETS -------------------------------------------------------- */

    public static NautilusManipulatorPosition PREPARE_TO_ASCEND_POSITION = new NautilusManipulatorPosition(
            NautilusConfiguration.SHOULDER_ASCEND_PREP_MAX, 2900, NautilusConfiguration.WRIST_SERVO_POSITION_TOP
    );

    public static NautilusManipulatorPosition ASCENDED_POSITION = new NautilusManipulatorPosition(
            0, 0, NautilusConfiguration.WRIST_SERVO_POSITION_TOP
    );

//    public static SBBArmPosition START_POSITION = new SBBArmPosition(
//            SBBConfiguration.LIFT_POSITION_GROUND,
//            SBBConfiguration.ARM_POSITION_GROUND,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_TRAVEL);
//
//    public static SBBArmPosition TRAVEL_POSITION = new SBBArmPosition(
//            SBBConfiguration.LIFT_POSITION_GROUND,
//            SBBConfiguration.ARM_POSITION_TRAVEL,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_TRAVEL);
//
//
////    public static SBBArmPosition GRAB_READY_POSITION = new SBBArmPosition(
////            1350,
////            SBBConfiguration.ARM_POSITION_MIN,
////            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
//
//    public static SBBArmPosition GRAB_CONTACT_POSITION = new SBBArmPosition(
//            SBBConfiguration.LIFT_POSITION_GROUND,
//            SBBConfiguration.ARM_POSITION_MIN,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
//
//    public static SBBArmPosition GRAB_READY_POSITION = new SBBArmPosition(
//            SBBConfiguration.LIFT_POSITION_GROUND,
//            300,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
//
//    public static SBBArmPosition AUTONOMOUS_DROP_POSITION = new SBBArmPosition(
//            200,
//            SBBConfiguration.ARM_POSITION_GROUND,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
//
//    public static SBBArmPosition AFTER_GRAB_POSITION = new SBBArmPosition(
//            SBBConfiguration.LIFT_POSITION_GROUND,
//            500,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
//
//
//    public static SBBArmPosition LEAVE_STACK_POSITION = new SBBArmPosition(
//            SBBConfiguration.LIFT_POSITION_GROUND,
//            780,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
//
//    public static SBBArmPosition LOW_FRONT_DELIVERY_POSITION = new SBBArmPosition(
//            0,
//            635,
//            0.425);
//
//    public static SBBArmPosition AUTONOMOUS_FRONT_DELIVERY_POSITION = new SBBArmPosition(
//            0,
//            540,
//            0.35);
//
//    public static SBBArmPosition AUTONOMOUS_FRONT_DELIVERY_RELEASE_POSITION = new SBBArmPosition(
//            0,
//            540, // 700?
//            0.6);
//
//
//    public static SBBArmPosition MID_FRONT_DELIVERY_POSITION = new SBBArmPosition(
//            500,
//            1000,
//            0.425);
//
//    public static SBBArmPosition HIGH_FRONT_DELIVERY_POSITION = new SBBArmPosition(
//            0,
//            1300,
//            0.425);
//
//    public static SBBArmPosition HIGH_BACK_DELIVERY_POSITION = new SBBArmPosition(
//            0,
//            2037,
//            0.85);
//    public static SBBArmPosition HANG_POSITION = new SBBArmPosition(
//            2600,
//            1523,
//            .225);
//    public static SBBArmPosition HANGING_POSITION = new SBBArmPosition(
//            1998,
//            31,
//            .225);
//
//
//    public static DefenderPresets<SBBArmPosition> ARM_PRESETS = new DefenderPresets<>(
//            SBBConfiguration.START_POSITION,
//            SBBConfiguration.TRAVEL_POSITION,
//            SBBConfiguration.LOW_FRONT_DELIVERY_POSITION,
//            SBBConfiguration.MID_FRONT_DELIVERY_POSITION,
//            SBBConfiguration.HIGH_BACK_DELIVERY_POSITION
//    );

    /* EFFECTS -------------------------------------------------------- */

    public static String EFFECTS_LEDS_NAME = "effects_leds"; // Control Hub, Servo 5


    /* METHODS -------------------------------------------------------- */

    public NautilusConfiguration() {
        super();
    }

}