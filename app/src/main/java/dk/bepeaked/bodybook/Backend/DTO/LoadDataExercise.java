package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Nicki on 04/01/17.
 */

public class LoadDataExercise extends RealmObject {

    RealmList<ExerciseDTO> exercises = new RealmList<ExerciseDTO>();
    private String name, desc;

    public LoadDataExercise() {}

    public RealmList<ExerciseDTO> getData() {
        exercises.add(new ExerciseDTO("Bench Press", "Lie on a flat bench with your feet flat on the floor, keep your back flat on the bench.\n" +
                "Grasp the bar a little wider than shoulder width apart.\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position."));
        exercises.add(new ExerciseDTO("Supermans", "Lie flat on your stomach with your arms stretched out in front of you.\n" +
                "Raise your arms and legs off the floor and hold this position for 2 seconds.\n" +
                "Return to the starting position on the floor.\n" +
                "Repeat."));
        exercises.add(new ExerciseDTO("Push Ups", "Start with a basic push up, lay face down on the floor, or a mat; with your feet together curled slightly so you rise onto the ball of your feet.\n" +
                "Place you hands shoulder width apart on the either side of your chest.\n" +
                "Draw your abs in.\n" +
                "Inhale as you raise your body up till your arms are straight.\n" +
                "Keep your head and neck level with your body (don’t look up or down) and don’t allow your back to rise or fall.\n" +
                "Exhale out as you lower your body back to the ground.\n" +
                "Repeat."));
        exercises.add(new ExerciseDTO("Pull Ups", "Grab the pull-up bar with the palms facing forward using the prescribed grip.\n" +
                "As you have both arms extended in front of you holding the bar at the chosen grip width, bring your torso back around 30 degrees or so while creating a curvature on your lower back and sticking your chest out. This is your starting position.\n" +
                "Pull your torso up until the bar touches your upper chest by drawing the shoulders and the upper arms down and back. Exhale as you perform this portion of the movement.\n" +
                "After a second on the contracted position, start to inhale and slowly lower your torso back to the starting position when your arms are fully extended and the lats are fully stretched.\n" +
                "Repeat this motion."));
        exercises.add(new ExerciseDTO("Bridging", "Lie on your back with your knees bent and your feet flat on the floor.\n" +
                "Lift up hips and bum off the floor as you draw your abs in and keep your gluts tight."));
        exercises.add(new ExerciseDTO("Chin Ups", "Grasp the bar with an supinated (overhand) grip.\n" +
                "Let your body hang from the bar with your arms straight.\n" +
                "Slowly pull yourself up so that your chin is higher than the bar.\n" +
                "With a controlled movement lower yourself to the starting position."));
        exercises.add(new ExerciseDTO("Seated Military Press", "Sit on the bench with your toes pointing straight out, back straight and abs drawn in.\n" +
                "Grip the bar with your palms facing outwards and your hands shoulder width apart\n" +
                "With bar in front of your head, press upwards extending your arms but not locking them.\n" +
                "Pause at the top and then in a controlled movement lower the bar to the starting position."));
        exercises.add(new ExerciseDTO("Close Grip Barbell Bench Press", "Lie on a flat bench with your feet flat on the floor, keep your back flat on the bench.\n" +
                "Grasp the bar a close grip (approximately 14” apart).\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position."));
        exercises.add(new ExerciseDTO("Arnold Press", "Sit on a flat bench, feet point forward with your abs drawn in.\n" +
                "Grasp a dumbbell in each hand with your palm facing towards your body\n" +
                "Bend your elbows to 90 degrees and raise both your arms to the starting point in line with your shoulders.\n" +
                "With a steady controlled motion raise your arms up while straightening your elbows while rotating your forearms so that your palms now face away from your body.\n" +
                "Bring the dumbbells closer together but do not fully extend your elbows.\n" +
                "With a controlled motion lower the dumbbells to the starting position at your shoulders."));
        exercises.add(new ExerciseDTO("Side Plank", "Lay on one side of your body with your legs straight and your forearm perpendicular to your body in front of you.\n" +
                "Drawing your abs in, slowly raise yourself up so you are balanced on your feet and your forearm.\n" +
                "Hold this position and slowly return back to the starting position.\n" +
                "Repeat on other side."));
        exercises.add(new ExerciseDTO("Standing Leg Curls", "Standing at a leg curl machine adjust the rear footpad so it is just above your ankle.\n" +
                "Grasp the handles of the machine for support and draw your abs in.\n" +
                "Raise your foot up towards your back slowly.\n" +
                "Return to the starting position and chance legs."));
        exercises.add(new ExerciseDTO("Incline Shoulder Press Dumbbell", "Lie down on an incline bench with your feet flat on the floor and grasp the dumbbells.\n" +
                "With your elbows bent raise your arms up until in line with your shoulders this is your starting position.\n" +
                "With your abs drawn in, raise the dumbbells as high as you are able above your shoulders.\n" +
                "Lower the dumbbells in a slow controlled manner to starting position."));
//        exercises.add(new ExerciseDTO("", ""));

        return exercises;
    }


}
