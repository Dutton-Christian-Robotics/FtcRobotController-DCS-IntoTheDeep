package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderState.DefenderState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderState.DefenderStateMachine;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusBot;
import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusConfiguration;

@Disabled
@TeleOp(name = "Experimental", group = "Testing")
public class ExperimentalTeleOpMode extends LinearOpMode {
    NautilusBot bot;
    DefenderStateMachine sm;

    public enum States {
        DRIVING,
        INTAKE_OFF,
        INTAKE_IN,
        INTAKE_OUT
    }


    public void setup() {
        bot = new NautilusBot(hardwareMap, NautilusConfiguration.class, telemetry);
        sm = new DefenderStateMachine();

        sm.addState(
                DefenderState.create(States.INTAKE_IN.ordinal())
                        .enterWhen(
                                () -> gamepad2.right_bumper,
                                () -> bot.intake.intake()
                        )
                        .exclude(new Integer[]{States.INTAKE_OFF.ordinal(), States.INTAKE_OUT.ordinal()})
        );
        sm.addState(
                DefenderState.create(States.INTAKE_OUT.ordinal())
                        .enterWhen(
                                () -> gamepad2.left_bumper,
                                () -> bot.intake.expel()
                        )
                        .exclude(new Integer[]{States.INTAKE_OFF.ordinal(), States.INTAKE_IN.ordinal()})

        );
        sm.addState(
                DefenderState.create(States.INTAKE_OFF.ordinal())
                        .exclude(new Integer[]{States.INTAKE_OUT.ordinal(), States.INTAKE_IN.ordinal()})

        );

    }

    @Override
    public void runOpMode() {
        setup();
        waitForStart();

        while (opModeIsActive()) {
            sm.run();
        }
    }

}
