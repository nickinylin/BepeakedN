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

        //##### page 1 #####
        exercises.add(new ExerciseDTO("Bench Press", "This is an exercise for the chest.", "Lie on a flat bench with your feet flat on the floor, keep your back flat on the bench.\n" +
                "Grasp the bar a little wider than shoulder width apart.\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Supermans", "This is an excellent exercise and a stretch for the lower back and core muscles.", "Lie flat on your stomach with your arms stretched out in front of you.\n" +
                "Raise your arms and legs off the floor and hold this position for 2 seconds.\n" +
                "Return to the starting position on the floor.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Push Ups", "This exercise is the main strengthening exercise when wanting to strengthen ones chest, biceps and triceps as well as core muscles.", "Start with a basic push up, lay face down on the floor, or a mat; with your feet together curled slightly so you rise onto the ball of your feet.\n" +
                "Place you hands shoulder width apart on the either side of your chest.\n" +
                "Draw your abs in.\n" +
                "Inhale as you raise your body up till your arms are straight.\n" +
                "Keep your head and neck level with your body (don’t look up or down) and don’t allow your back to rise or fall.\n" +
                "Exhale out as you lower your body back to the ground.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Pull Ups", "This is an exercise in lats, biceps and middle back strengthening.", "Grab the pull-up bar with the palms facing forward using the prescribed grip.\n" +
                "As you have both arms extended in front of you holding the bar at the chosen grip width, bring your torso back around 30 degrees or so while creating a curvature on your lower back and sticking your chest out. This is your starting position.\n" +
                "Pull your torso up until the bar touches your upper chest by drawing the shoulders and the upper arms down and back. Exhale as you perform this portion of the movement.\n" +
                "After a second on the contracted position, start to inhale and slowly lower your torso back to the starting position when your arms are fully extended and the lats are fully stretched.\n" +
                "Repeat this motion." , "", "", null));
        exercises.add(new ExerciseDTO("Bridging", "This is an excellent exercise for strengthening and maintaining the core muscles.", "Lie on your back with your knees bent and your feet flat on the floor.\n" +
                "Lift up hips and bum off the floor as you draw your abs in and keep your gluts tight." , "", "", null));
        exercises.add(new ExerciseDTO("Chin Ups", "This is an extremely good exercise for upper arm and middle back strengthening.", "Grasp the bar with an supinated (overhand) grip.\n" +
                "Let your body hang from the bar with your arms straight.\n" +
                "Slowly pull yourself up so that your chin is higher than the bar.\n" +
                "With a controlled movement lower yourself to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Seated Military Press", "An exercise for the shoulder and triceps strengthening.", "Sit on the bench with your toes pointing straight out, back straight and abs drawn in.\n" +
                "Grip the bar with your palms facing outwards and your hands shoulder width apart\n" +
                "With bar in front of your head, press upwards extending your arms but not locking them.\n" +
                "Pause at the top and then in a controlled movement lower the bar to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Close Grip Barbell Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a flat bench with your feet flat on the floor, keep your back flat on the bench.\n" +
                "Grasp the bar a close grip (approximately 14” apart).\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Arnold Press", "An exercise for shoulder and triceps strengthening.", "Sit on a flat bench, feet point forward with your abs drawn in.\n" +
                "Grasp a dumbbell in each hand with your palm facing towards your body\n" +
                "Bend your elbows to 90 degrees and raise both your arms to the starting point in line with your shoulders.\n" +
                "With a steady controlled motion raise your arms up while straightening your elbows while rotating your forearms so that your palms now face away from your body.\n" +
                "Bring the dumbbells closer together but do not fully extend your elbows.\n" +
                "With a controlled motion lower the dumbbells to the starting position at your shoulders." , "", "", null));
        exercises.add(new ExerciseDTO("Side Plank", "This is an exercise for core strengthening.", "Lay on one side of your body with your legs straight and your forearm perpendicular to your body in front of you.\n" +
                "Drawing your abs in, slowly raise yourself up so you are balanced on your feet and your forearm.\n" +
                "Hold this position and slowly return back to the starting position.\n" +
                "Repeat on other side." , "", "", null));
        exercises.add(new ExerciseDTO("Standing Leg Curls", "This is an exercise for hamstring strengthening.", "Standing at a leg curl machine adjust the rear footpad so it is just above your ankle.\n" +
                "Grasp the handles of the machine for support and draw your abs in.\n" +
                "Raise your foot up towards your back slowly.\n" +
                "Return to the starting position and chance legs." , "", "", null));
        exercises.add(new ExerciseDTO("Incline Shoulder Press Dumbbell", "This is an exercise for shoulder strengthening.", "Lie down on an incline bench with your feet flat on the floor and grasp the dumbbells.\n" +
                "With your elbows bent raise your arms up until in line with your shoulders this is your starting position.\n" +
                "With your abs drawn in, raise the dumbbells as high as you are able above your shoulders.\n" +
                "Lower the dumbbells in a slow controlled manner to starting position." , "", "", null));

        //##### page 2 #####
        exercises.add(new ExerciseDTO("Incline Dumbbell Press", "This exercises is for mainly chest strengthening but also strengthens your triceps and shoulders.", "Set and incline bench at a 45 degree angle.\n" +
                        "Start with the dumbbells at shoulder height, your arms wide and elbows pointing down to the floor.\n" +
                        "Raise your arms up over your chest bringing the dumbbells closer together as they meet over your chest.\n" +
                        "Slowly return the dumbbells to starting position.\n" +
                        "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Bench Press Dumbbell", "This is an exercise for chest, triceps and shoulder strengthening.", "Grasp the dumbbells in each hand and lie on a flat bench with your feet firmly on the ground.\n" +
                "Extend your arms up over your chest with your palms facing forwards.\n" +
                "Press the dumbbells up over your chest till your arm are fully extended this is your starting position.\n" +
                "Bend your elbows to a 90 degree angle so your upper arms are parallel with the floor.\n" +
                "Slowly lower the dumbbells as low as comfortable along your chest.\n" +
                "With a controlled motion return back to your starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Upright Barbell Rows", "This is an exercise for shoulder, biceps and upper back strengthening.", "Stand with your feet shoulder width apart, your abs drawn in and back straight.\n" +
                "Place the bar on rack in the position where your arms are fully extended in front of you with your elbows slightly bent.\n" +
                "Place your hands shoulder width apart and raise the bar up towards your chin with a controlled motion.\n" +
                "Pause at the top for a moment and rotate your shoulder blades together.\n" +
                "Lower the bar to the starting position.\n" +
                "Repeat" , "", "", null));
        exercises.add(new ExerciseDTO("Incline Bench Press", "This is an exercise for chest and triceps strengthening.", "Lie flat on an incline bench set at a 45 degree angle, with your feet shoulder width apart.\n" +
                "Grasp the bar a little wider than shoulder width apart.\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar straight up till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Shoulder Shrugs", "An exercise for trapezius strengthening.", "Stand with your feet shoulder width apart and a slight bend in your knees.\n" +
                "Hold a dumbbell in each hand and with your arms at your sides.\n" +
                "Lower your shoulders as much as possible.\n" +
                "With your arms straight raise both shoulders up towards your ears.\n" +
                "Hold the upright positions for a moment and then lower in a controlled motion." , "", "", null));
        exercises.add(new ExerciseDTO("Hack Squat Machine", "This exercise is a variation of the Squat which uses a machine.  This exercise may be preferred by beginners.", "Lie face up on a Hack Squat machine with your shoulders against the pad.\n" +
                "Place your feet facing forward at slightly less than shoulder width apart with your toes point slightly outward.\n" +
                "Release the dock levers and place your hands on the hand grips.\n" +
                "Drawing your abs in, extend your body standing upright.\n" +
                "Lower your body to a squatting position so you knees are bent as if you were sitting down.\n" +
                "Return to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Wide Grip Decline Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a decline bench with your head lower than your feet.\n" +
                "Place your hands wider than shoulder width apart on the bar and lift it off the rack.\n" +
                "Bring the bar over your chest and lower it till it is just above the lower portion of your chest, this is starting position.\n" +
                "Extend your arms upward and raise the bar straight up.\n" +
                "Pause for a moment and then lower the bar to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("T-Bar Rows", "This is an exercise for middle back, biceps and shoulder strengthening.", "Place your feet on either side of a T-Bar Machine.\n" +
                "With your knees slightly bent and abs drawn in, grasp the handles with a narrow grip.\n" +
                "Bend at the waist so your chest is parallel to the floor, this is starting position. Slowly pull the bar to your chest, as high as you can.\n" +
                "Pause at the top for a moment and then lower the bar to starting position.\n" +
                "Repeat" , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Shoulder Press", "An exercise for shoulder, chest and triceps strengthening.", "Sitting on a bench, with your abs drawn in grasp a dumbbell in each hand, with your palms facing forward.\n" +
                "Begin with your arms at the height of your shoulders.\n" +
                "Extend your arms and raise the dumbbells up above your head.\n" +
                "At the top of the movement, bring your arms closer together.\n" +
                "Pause for a moment and then in a controlled movement lower the dumbbells to the starting position.\n" +
                "Repeat" , "", "", null));
        exercises.add(new ExerciseDTO("Cable Crossover", "This is an exercise for chest and shoulder strengthening.", "Attach the cable pulley to shoulder height.\n" +
                "Grasp the pulley in both hands and stand approximately one foot in front of the weight stacks, with one foot slightly in front of the other.\n" +
                "With a slight bend in your elbows bring your hand together in front of your chest on downward angle.\n" +
                "When your hands meet at the midpoint of your chest, hold for a moment.\n" +
                "With a slow controlled motion return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Barbell Squat", "This is an exercise for leg strengthening, mainly the quadriceps.", "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Flys", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a flat bench with a dumbbell in each hand and your feet firmly on the ground.\n" +
                "Lift the dumbbells over your chest extending your arms fully with a slight bend in your elbows, this is your starting position.\n" +
                "Keeping a slight bend in your elbows, lower the dumbbells to the floor in an arc like motion.\n" +
                "Slowly return the dumbbells over your chest to the starting position in a controlled motion.\n" +
                "Repeat." , "", "", null));

        //##### page 3 #####
        exercises.add(new ExerciseDTO("Chest Dips", "This is an exercise for chest, triceps and shoulder strengthening.", "Stand in between the parallel bars.\n" +
                "Grip the handles of the parallel bars and push yourself up to the starting position with straightened arms.\n" +
                "With your elbows close to your body keep your hips straight, lower your body forward by bending your elbows so your chest is leading as you go down.\n" +
                "Raise yourself back up to the starting position and repeat.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Machine Bench Press", "This is an exercise for chest, biceps and shoulder strengthening.", "Adjust the machine so that you are sitting with the bars at chest height.\n" +
                "Place your hands on the bars and place your feet on the foot rest.\n" +
                "Press out, extending your arms as far as possible in a steady motion.\n" +
                "Pause for a moment and then return slowly to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Hyperextensions", "This exercise not only adds strength but also flexibility to the back and core muscles.", "Adjust the hyperextension bench so that your ankles are tucked under the footpads and your thighs are flat across the top pad.\n" +
                "With your arms folded across your chest and your back straight, slowly bend at the waist towards the floor, keeping your back flat.\n" +
                "Slowly return to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Dead Lifts", "This is an exercise for lower back, hamstring and calves strengthening.", "Grasp 2 dumbbells and stand with your feet shoulder width apart.\n" +
                "Keeping your back straight bend at the waist, allow some bend in your knees.\n" +
                "Grasp the dumbbells with an overhand grip in each hand.\n" +
                "Straighten your back as you hold the dumbbells at arm’s length.\n" +
                "Bend over again lowering the dumbbells to just above the floor.\n" +
                "Return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Front Barbell Raises", "An exercise for shoulder, chest and forearm strengthening.", "Grasp a barbell with an overhand grip(palms of hands face downwards) .\n" +
                "Stand up-right with your feet shoulder width apart, knees slightly bent and your abs drawn in.\n" +
                "With your arms straight, raise the barbell in a controlled motion to shoulder level.\n" +
                "Hold for a moment and then lower the barbell in at the same motion." , "", "", null));
        exercises.add(new ExerciseDTO("Front Cable Raises", "An exercise for shoulders, chest and forearm strengthening.", "Place the pulley on the setting just above the floor.\n" +
                "Stand with your feet shoulder width apart, your abs drawn in and your knees slightly bent.\n" +
                "Starting at waist height, take the pulley in your left hand, palm facing the floor with otherwise known as an overhand grip, and raise your straight arm up towards your left shoulder.\n" +
                "At shoulder height pause for a moment.\n" +
                "With a controlled movement resist the weight and slowly lower your arm back to starting position at your waist.\n" +
                "Repeat the exercise with your right arm." , "", "", null));
        exercises.add(new ExerciseDTO("Front Raises", "This is an exercise for shoulder, chest and forearm strengthening.", "Stand with your feet shoulder width apart, abs drawn in and knees slightly bent.\n" +
                "Grasp a dumbbell in each hand, with your palm facing down.\n" +
                "Start with the dumbbell at waist height.\n" +
                "Raise your left arm, keeping your elbow slightly bent and your arm straight, to in line with your shoulder.\n" +
                "Pause for a moment and with a controlled motion lower your arm to back to the starting position at your waist.\n" +
                "Repeat with your right arm." , "", "", null));
        exercises.add(new ExerciseDTO("Seated Shoulder Press Machine", "This is an exercise for the shoulder strengthening.", "Sit upright with your back supported by the chair and draw your abs in.\n" +
                "Place your hands on the bars and with smooth even motions press upward extending your arms, but not locking them.\n" +
                "At the top pause then with controlled motion lower the bars to your starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Barbell Shrugs", "This is an exercise for trapezius strengthening.", "Stand on the floor with your abs drawn in and your back straight.\n" +
                "Grasp a barbell with a grip a little wider than shoulder width at arms length.\n" +
                "Drop your shoulders as much as possible to start.\n" +
                "Raise your shoulder as high as possible.\n" +
                "Pause for a moment at the top and then return to starting position in a controlled motion.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("One Arm Upright Row", "This is an exercise for shoulder and trapezius strengthening.", "Stand near a post or other stable tall object. With your one hand, hold the post.\n" +
                "Grasp the dumbbell in your other hand with a pronated grip (palms facing backwards).\n" +
                "Place the dumbbell in front of your thigh, this is starting position.\n" +
                "Lift the dumbbell upward to your shoulder with your elbow pointing away from your body in a rowing motion.\n" +
                "Lower your arm to starting position and repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Flat Bench Leg Raises", "This is an exercise for lower abdominal strengthening.", "Lie on a flat bench with your hands under your hips supporting your back.\n" +
                "Your legs should be unsupported by the bench from below your knees.\n" +
                "With your feet together and your toes flexed upwards, raise your straight legs up a few cm off the bench; both of your legs should have no contact with the bench. This is your starting position.\n" +
                "Keep your legs straight with a slight bend in the knees and raise your legs to just before 90 degrees with your hips.\n" +
                "Pause at the top and lower your legs in slow controlled manner back to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Shoulder Shrugs", "This is an exercise for shoulder strengthening.", "Place the barbell at waist height.\n" +
                "Stand with your feet shoulder width apart and your abs drawn in.\n" +
                "Grasp the bar with an overhand grip and straightened arms.\n" +
                "As you lift the bar allow the weight to pull your shoulders down, shrug your shoulders up and to the back.\n" +
                "Hold for a moment and then return to starting position." , "", "", null));

        //##### page 4 #####
        exercises.add(new ExerciseDTO("Seated Barbell Shoulder Press", "This is an exercise for shoulder, chest and triceps strengthening.", "Sitting on a bench with a barbell rack, grasp the barbell with a grip 3 to 4 inches wider than your shoulders.\n" +
                "Lift the bar off the rack and lower it to just at the height of your shoulders.\n" +
                "While maintaining  good posture, straighten your arms and raise the bar up above your head.\n" +
                "Pause for a moment and then in a controlled movement lower the bar to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Flat Bench Cable Flys", "This is an exercise for chest and shoulder strengthening.", "Lie on flat bench between two cable towers and your feet firmly on the ground.\n" +
                "Grasp a pulley in each hand with your palms facing up.\n" +
                "With a slight bend in your elbows, squeeze your chest and pull the cables together meeting in the middle of your chest.\n" +
                "Hold for a moment and then slowly lower your hands back to starting position at bench height." , "", "", null));
        exercises.add(new ExerciseDTO("Body Row", "This is a good overall exercise which strengthens your chest, core and back muscles.", "Lie under a bar so that the bar is at mid chest level and your feet are firmly on the floor.\n" +
                "Draw your abs in and keep your back flat.\n" +
                "Using your arms, lift and row your body towards the bar.\n" +
                "Pause for a moment and with slow controlled movements lower your self back to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Cuban Dumbbell Press", "This is a three point exercise for shoulder and middle back strengthening.", "Grasp dumbbells in each hand with a pronated grip (palms facing back wards, your shoulder rotated forward.\n" +
                "Stand upright with your feet shoulder width apart and your knees slightly bent, contract your abdominals.\n" +
                "Slowly lift your arms up so elbows are bent and parallel with the floor while you squeeze your shoulders together.\n" +
                "then rotate your arms, bringing the dumbbells forward so that they are now above your shoulder and in line with your elbows.\n" +
                "In a controlled manner lower your arms and return to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Bent Arm Pullover", "This is an exercise for chest, triceps, shoulders and lats strengthening.", "Lie flat on a bench with your head hanging slightly over the end and your feet flat on the floor.\n" +
                "Hold a barbell with a close grip (approximately 14”), keep your elbows in throughout the exercise.\n" +
                "Starting with your arms fully extended over your chest, slowly lower the bar in an arc over your head and towards the floor.\n" +
                "Pull the bar back up to chest height in a slow controlled manner and return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Barbell Good Mornings", "This is one of the oldest exercises in bodybuilding but it still is one of the best for working the lower back.", "Place a barbell across your shoulders.\n" +
                "Keeping your head up and your back completely straight, bend at the waist until your back is parallel with the floor.\n" +
                "Return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Good Mornings", "This exercise utilises the same technique of the standard Good Mornings yet the use of the Smith Machine provides more stability.", "Place a barbell across your shoulders.\n" +
                "Keeping your head up and your back completely straight, bend at the waist until your back is parallel with the floor.\n" +
                "Return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Barbell Dead Lifts", "The techniques is identical to that of the Smith Machine dead lift except for the fact that this exercise provides less support.", "Stand with your feet shoulder width apart so that your feet are under the bar.\n" +
                "Keeping your back straight bend at the waist, allow some bend in your knees.\n" +
                "Grasp the par with an overhand grip approximately 16 inches apart.\n" +
                "Straighten your back as you hold the bar at arm’s length.\n" +
                "Bend over again lowering the bar to just above the floor." , "", "", null));
        exercises.add(new ExerciseDTO("Seated Cable Rows", "This is an exercise for middle back, biceps and lower back strengthening.", "Sit at a low pulley machine with your feet resting against the footrests and your knees slightly bent.\n" +
                "With your abs drawn in and your back straight lean forward slightly to grasp the pulleys with an overhand grip (palms face downwards).\n" +
                "Slowly bring the pulleys back towards your abs while sitting upright, keep your elbows in close to your chest.\n" +
                "Pause for a moment then return slowly return the pulleys to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Lateral Dumbbell Raises", "This is an exercise for lateral deltoid, shoulder and forearm strengthening.", "Grasp a dumbbell in each hand, palms facing inward towards your body and the dumbbells at your sides.\n" +
                "Standing with your feet shoulder with apart, draw your abs in and bend your knees slightly.\n" +
                "While keeping your torso still, raise the your arms up to the sides keeping your arms straight with a slight bend in the elbows.\n" +
                "Raise your arms up until in line with your shoulders, your palms should face the floor.\n" +
                "Hold the position for a moment then in a controlled movement lower your arms to the starting position.\n" +
                "Repeat" , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Incline Bench Press", "This is an advanced exercise for building and sculpting the chest as well as your triceps and shoulders.", "Lie on an incline bench which has been set to an incline of 45 degrees.\n" +
                "Start with the dumbbells at shoulder height, your arms wide and elbows pointing down to the floor.\n" +
                "Grasp the dumbbells with a grip so your palms face each other.\n" +
                "Raise your arms up over your chest bringing the dumbbells closer together as they meet over your chest, as if you were clapping.\n" +
                "Slowly return the dumbbells to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Incline Chest Press", "This exercise is for strengthening the upper chest and is preferred among beginners as it provides support and control.", "Adjust the seat of the machine so that the handles are near the upper portion of your chest.\n" +
                "Slowly press the handles forward until your hands are fully extended, do not lock your elbows.\n" +
                "Pause for a moment and then with a controlled movement lower your hands back to starting position." , "", "", null));

        //##### page 5 #####
        exercises.add(new ExerciseDTO("Smith Machine Squats", "This exercise uses the Smith Machine to work the Quadriceps, Hamstrings, Calves and Glutes. A great overall exercise for the lower body. There are many variations on the squat, this is the original version using a Smith Machine for better flow of movement.", "Set the height of the barbell to shoulder height.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift over your toes (this will cause you to loose balance).\n" +
                "Pause in the downward squatting position and slowly return upright to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Bench Press", "With the use of a Smith Machine this exercise strengthens your chest shoulders and triceps.", "Place a flat bench in the middle of the Smith Machine, with the bar in line with the middle of your chest.\n" +
                "Lying on the bench, grasp the bar at shoulder width apart.\n" +
                "Unlatch the bar and slowly lower the bar to your chest.\n" +
                "Extend your arms fully and raise the bar to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Push Ups with feet on exercise ball", "This is similar to the standard Push Up except using an Exercise Ball forces you to engage the muscle of your core (Rectus Abdominis, Transverse Abdominis, and the Obliques).", "Place your feet and shins flat on an Exercise Ball.\n" +
                "Place your arms in front of you at shoulder width apart, place your hands under your arms and press up from the ground until your arms are fully extended.\n" +
                "Pause at the top for a moment and steady your balance.\n" +
                "Slowly return to the starting position with your chest lowered towards the ground." , "", "", null));
        exercises.add(new ExerciseDTO("V Bar Pull Down", "This is an exercise for lats, biceps and middle back strengthening.", "Attach a V bar to the pull down pulley of a cable machine.\n" +
                "Sitting upright with your abs drawn grasp the bar with an overhand grip, your palms facing in.\n" +
                "Pull the bar straight down to your upper chest.\n" +
                "Pause for a moment after touching the chest and then slowly return the bar to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Raise", "This is an exercise for shoulder and biceps strengthening.", "Stand upright with your feet shoulder width apart and a slight bend in your knees.\n" +
                "Grasp a dumbbell in each hand, in front of your thighs. This is your starting position.\n" +
                "Draw your abs in.\n" +
                "Raise the dumbbells up on either side of your ribs in a rowing motion by bending your elbows moving them outwards.\n" +
                "Lower to starting position and repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Incline Bench Press", "This is an exercise for chest, shoulder and triceps strengthening.", "Place an incline bench at a 45 degree angle in the middle of the Smith Machine.\n" +
                "Align the bench so the bar is across the upper portion of your chest.\n" +
                "Grasp the bar with a shoulder width grip.\n" +
                "Unlock the bar and slowly lower the weight to your chest, do not bounce the bar on your chest.\n" +
                "With slow controlled movements, raise the bar back to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Push Ups Close and Wide Hand Versions", "These are variations on Push Ups, one of the best exercise for muscles of the chest (pectorals), arms (bicep and triceps) and core development. The core muscles are the rectus abdomen and oblique’s which support the spine.", "For a Close Grip Push Up lay face down on the floor, or a mat, with your feet together curled slightly so you rise on the ball of your feet.\n" +
                "Place you hands close together so your thumbs and index fingers form a triangle on the floor.\n" +
                "Draw your abs in and inhale as you raise your body up till your arms are straight.\n" +
                "Keep your head and neck level with your body (don’t look up or down) and don’t allow your back to rise or fall.\n" +
                "Exhale out as you lower your body back to the ground.\n" +
                "For a Wide Grip Push Up move your hands out to a position slightly wider than your shoulders.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Seated Rear Lateral Cable Raise", "Tis is an exercise for shoulder strengthening.", "Sit on the edge of a bench with your feet on the floor in front of you.\n" +
                "Rest your chest on your thighs with your back straight, grasp the cable pulleys with opposite hands.\n" +
                "Raise your upper arms to shoulder height. Pause at the top for a moment.\n" +
                "Lower your arms to the starting position in a controlled motion.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Front Dumbbell Raise", "This is an exercise for shoulder strengthening.", "Stand with a dumbbell in each hand with an overhand grip, your feet shoulder width apart and your abs drawn in.\n" +
                "Keeping each arm straight raise your left arm to just above shoulder height.\n" +
                "Pause for a moment then in a controlled motion lower the weight to starting position and repeat with your right arm." , "", "", null));
        exercises.add(new ExerciseDTO("Push Ups with Feet Elevated", "This is the same general movement as with a Push Up except your feet are elevated allowing greater range of motions and targeting the pectorals differently than the regular Push Up.", "Place your feet on a bench at least 18” off the ground.\n" +
                "Make sure the tops of your feet are flush against the bench.\n" +
                "Place your hands on the floor slightly wider than shoulder width apart.\n" +
                "With your hands directly under your shoulders, press up from the floor, keeping your back and neck in a straight line so you are looking forward during the entire exercise.\n" +
                "Once your arms are fully extended pause and then with slow controlled movements lower yourself to the floor again." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Dead Lifts", "This is the same as a Dead Lift but the Smith Machine allows for better control of the weight.", "Stand with your feet shoulder width apart so that your feet are under the bar.\n" +
                "Keeping your back straight bend at the waist, allow some bend in your knees.\n" +
                "Grasp the par with an overhand grip approximately 16 inches apart.\n" +
                "Straighten your back as you hold the bar at arm’s length.\n" +
                "Bend over again lowering the bar to just above the floor." , "", "", null));
        exercises.add(new ExerciseDTO("Wide Grip Lat Pull Down", "This is an exercise for lats, biceps and middle back strengthening.", "Sit at a cable pull down machine fitted with a wide bar.\n" +
                "Grasp the bar with a wide overhand grip (palms facing forward) .\n" +
                "With your abs drawn in and back straight pull the bar down to your upper chest.\n" +
                "Pause for a moment and then return the bar to the starting position.\n" +
                "Repeat." , "", "", null));

        //##### page 6 #####
        exercises.add(new ExerciseDTO("Barbell Neck Press", "This is an advanced exercise for chest strengthening.", "Lie on a flat bench with you feet planted firmly on the floor.\n" +
                "Grasp the bar a little wider than shoulder width apart.\n" +
                "Raise the barbell above your body and move it to the top of your chest, near your neck.\n" +
                "This is your starting position.\n" +
                "Lower the bar down so it just touches the top of your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Body Leg Lifts", "This is an exercise for gluts and hamstring strengthening.", "Using a post or tall weight bench for balance stand straight with your abs drawn in.\n" +
                "Raise one off the ground and behind you while standing on the other leg.\n" +
                "Slowly lower the leg and raise it again while flexing the gluts.\n" +
                "Repeat with your other leg." , "", "", null));
        exercises.add(new ExerciseDTO("Incline Dumbbell Fly’s", "This is an exercise for chest sculpting and strengthening.", "Lie on an incline bench set a 45 degree angle with a dumbbell in each hand and your feet firmly on the ground.\n" +
                "Lift the dumbbells over your chest extending your arms fully and your palms facing each other.\n" +
                "Keeping a slight bend in your elbows, lower the dumbbells towards the floor in and arc like motion.\n" +
                "Slowly return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Decline Barbell Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a decline bench with your head lower than your feet.\n" +
                "Grasp the bar at a grip 3-6 inches wider than your shoulders.\n" +
                "Raise the bar above your chest, keeping your elbows close in.\n" +
                "Slowly and with control lower the bar straight to your lower chest.\n" +
                "Raise the bar back up to starting position with the bar just above your chest.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Back Extension on Stability Ball", "This exercise provides flexibility as it strengthens the muscles of the back and core.", "Lie prone (on your stomach) on a Stability Ball with your toes firmly planted on the floor for balance.\n" +
                "With your hands across your chest or at your ears, raise your chest off the ball so you are hyperextending your spine.\n" +
                "Slowly return your chest to the ball.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Upright Cable Row", "This is an exercise for shoulder, biceps and upper back strengthening.", "Attach a straight bar to the pulley on the floor.\n" +
                "Stand with your feet shoulder width apart, your abs drawn in and your back straight.\n" +
                "Grasp the bar with an overhand grasp (your palms facing downwards) and pull it up towards your waist, this is the starting position.\n" +
                "Raise the bar up to in line with your shoulders.\n" +
                "Pause at the top and rotate your shoulder blades together.\n" +
                "Lower the bar in a controlled motion to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Straight Arm Dumbbell Pullover", "This is an exercise for chest and shoulder strengthening which mainly targets the rotator cuff muscles.", "Lie on a flat bench with your feet flat on the floor and your head at the end of the bench.\n" +
                "Grasp a dumbbell and raise it over your chest.\n" +
                "Keeping your elbows as straight as possible, lower the weight in an arc over your head and as low to the ground as possible with out any pain.\n" +
                "Return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Rear Deltoid Row Dumbbell", "This is an exercise for deltoid, biceps and trapezius strengthening.", "Place your right foot on the floor, rest your left knee and hand on a flat bench.\n" +
                "Lean forward so your body’s weight is supported by your left arm and knee.\n" +
                "Keeping your back flat, reach down and pick up the dumbbell with your right hand.\n" +
                "Raise your right arm as close to your chest as possible; while bending and bringing your elbow back as far as you can.\n" +
                "Pause at the top for a moment and then lower the dumbbell in a controlled manner to the starting position.\n" +
                "Switch arms by doing the opposite positioning.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Stationary Abdominal Draw In", "This is an exercise for abdominal and core strengthening which are vital in supporting the spine.", "Get down on a mat on your hands and knees, forming a four point rectangle or square shape.\n" +
                "Keep your back straight and your hips and pelvis in a neutral position.\n" +
                "Draw your abs in, crunching your abs while keeping your back still.\n" +
                "Hold for a moment and then release returning to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Hammer Grip Incline Bench Press", "This is an advanced exercise for strengthening the chest, triceps and shoulders.", "Grasp a dumbbell in each hand and lay on an incline bench set at a 45 degree angle.\n" +
                "Keep your feet flat on the floor and your back against the bench at all times.\n" +
                "Using a hammer grip (with your palms facing each other), lift the weights to shoulder height on either side of your chest.\n" +
                "Extend your arms fully and press the dumbbells up.\n" +
                "Slowly return the dumbbells to the starting position at the sides of your chest." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Upright Row", "This is an exercise for shoulder and biceps strengthening.", "Stand with your feet shoulder width apart and your abs drawn in.\n" +
                "Place the bar on the Smith Machine in the position where your arms are fully extended in front of you.\n" +
                "Place your hands shoulder width apart and raise the bar up towards your chin with a controlled motion.\n" +
                "Pause at the top for a moment and rotate your shoulder blades together.\n" +
                "Lower the bar to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Incline Cable Fly’s", "This exercise is an alternative to the Butterfly or Pec Deck, it defines the muscles of the chest.Place an incline bench set at a 45 degree angle between two cable towers.", "Place an incline bench set at a 45 degree angle between two cable towers.\n" +
                "Grasp a pulley in each hand with your palms facing up.\n" +
                "With a slight bend in your elbows, squeeze your chest and pull the cables in an arc so they meet together in the middle of your chest.\n" +
                "Hold for a moment and then slowly lower your hands back to starting position at along the same arc." , "", "", null));

        //##### page 7 #####
        exercises.add(new ExerciseDTO("Smith Machine Rear Deltoid Row", "This is an exercise for rear deltoid,shoulder, biceps and back strengthening.", "Set the bar to the lowest setting.\n" +
                "Place your feet shoulder width apart with a slight bend in the knees.\n" +
                "Bend at the waist while keeping your back straight and chest parallel to the floor.\n" +
                "Grasp the bar slightly wider than your shoulders with an overhand grasp and your arms straight with a slight bend in the elbows.\n" +
                "Raise the bar with a controlled motion to the lower part of your chest by bending and elevating your elbows.\n" +
                "Hold for a moment and then lower to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Incline Cable Fly’s", "This exercise is an alternative to the Butterfly or Pec Deck, it defines the muscles of the chest.Place an incline bench set at a 45 degree angle between two cable towers.", "Place an incline bench set at a 45 degree angle between two cable towers.\n" +
                "Grasp a pulley in each hand with your palms facing up.\n" +
                "With a slight bend in your elbows, squeeze your chest and pull the cables in an arc so they meet together in the middle of your chest.\n" +
                "Hold for a moment and then slowly lower your hands back to starting position at along the same arc." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Upright Row", "This is an exercise for shoulder and biceps strengthening.", "Stand with your feet shoulder width apart and your abs drawn in.\n" +
                "Place the bar on the Smith Machine in the position where your arms are fully extended in front of you.\n" +
                "Place your hands shoulder width apart and raise the bar up towards your chin with a controlled motion.\n" +
                "Pause at the top for a moment and rotate your shoulder blades together.\n" +
                "Lower the bar to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Static Neck Flexion and Extension", "An exercise to either build or warm-up front and back neck muscles.", "Stand or sit with your head in neutral position.\n" +
                "Place both of your hands on your forehead.\n" +
                "Press your head against your hands and contract your neck muscles.\n" +
                "Keep your head from moving forward. Hold for at least 30 seconds.\n" +
                "Repeat with hands on the back of your head, pressing against them." , "", "", null));
        exercises.add(new ExerciseDTO("Decline Chest Press", "This is an exercise for the strengthening of the lower chest or pectoral muscles and is preferred by beginners as it provides support and stability.", "Adjust the seat of the machine so that the handles are near the lower portion of your chest.\n" +
                "Slowly press the handles forward until your hands are fully extended, do not lock your elbows.\n" +
                "Pause for a moment and then with a controlled movement lower your hands back to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("One Arm Dumbbell Shoulder Press", "This is an exercise for shoulder strengthening.", "Stand with your feet shoulder width apart, back straight and abs drawn in.\n" +
                "Grasp a dumbbell in your hand and lift it with a bent elbow to your shoulder height to start.\n" +
                "Lift the dumbbell straight up till your arm is fully extended overhead.\n" +
                "Lower in a controlled manner and return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Reverse Grips Bent Over Barbell Rows", "This is an exercise for middle back and biceps strengthening.", "Bend over a barbell at slight angle with your knees bent and your back straight.\n" +
                "Grasp the bar with a reverse grip (palms facing up) and slowly pull the barbell to your abs, keeping your elbows close to your sides.\n" +
                "Pause for a moment at the top.\n" +
                "Return the barbell to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Decline Dumbbell Bench Press", "his is an exercise for chest, shoulder and triceps strengthening.", "Grasp the dumbbells your hands and lie on a decline bench with your head lower than your feet.\n" +
                "Raise the dumbbells above your chest, bringing your elbows close in, this is your starting position.\n" +
                "Slowly and with control lower the dumbbells to either side of your lower chest.\n" +
                "Raise the dumbbells back up to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Static Neck Side Flexion", "An exercise either build or warm up neck side flexors", "Stand or sit with your head in neutral position.\n" +
                "Place your left hand against the left side of your head, above your ear.\n" +
                "Push your head against your hand, without moving your head.\n" +
                "Repeat with your right hand, right side of your head." , "", "", null));
        exercises.add(new ExerciseDTO("Rear Deltoid Row Barbell", "This is an exercise for shoulder and biceps strengthening.", "Place a barbell on the floor in front of you with the resided weights attached.\n" +
                "With your knees bent and back straight grasp the bar with a wide overhand grip (palms facing downwards).\n" +
                "Keep your chest steady and your arms perpendicular to your chest.\n" +
                "Pull the bar as far up to your chest as you can.\n" +
                "Hold for a moment than lower the bar in a controlled manner.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Bent Arm Pullover", "This is an exercise for chest, shoulder, lats and biceps strengthening.", "Lie flat on a bench with your head hanging slightly over the end and your feet flat on the floor.\n" +
                "Hold a dumbbell with both hands, keep your elbows in throughout the exercise.\n" +
                "Starting with your arms fully extended over your chest, slowly lower the dumbbell in an arc over your head and towards the floor.\n" +
                "Pull the dumbbell back up to chest height in a slow controlled manner and return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Decline Dumbbell Fly’s", "This is an exercise for chest and shoulder strengthening.", "Lie on a decline bench with your head lower than your body and your feet firmly on the ground.\n" +
                "With dumbbells together above your shoulders and your arms straight out above you as your starting position.\n" +
                "Keeping your arms straight and with a slight bend in your elbows lower the dumbbells down towards the floor in an arc, up until just above your shoulders.\n" +
                "Return to the starting position in a slow controlled manner.\n" +
                "Repeat." , "", "", null));

        //##### page 8 #####
        exercises.add(new ExerciseDTO("Gironda Sternum Chins", "This is an exercise for lats, biceps and middle back strengthening; which is similar t the basic chin up but focusses more on lats.", "Grasp the pull up bar with a shoulder width underhand grip.\n" +
                "Hang from the bar with your arms fully extended.\n" +
                "Pulling yourself towards the bar lean your head back as far as you can and arch your spine.\n" +
                "Continuing pulling so that your collarbone passes the bar and you are able to touch your sternum to the bar.\n" +
                "Slowly lower yourself back to starting position.\n" +
                "Repeat this motion." , "", "", null));
        exercises.add(new ExerciseDTO("Hack Squat with barbell", "This is another variation of the basic Squat that focuses primarily on the quadriceps or thigh muscles.", "Place the barbell on the floor just behind your legs and stand with your feet shoulder width apart with your toes pointing forward.\n" +
                "With your feet firmly placed on the floor, reach down and grasp the barbell from behind with an overhand grip.\n" +
                "Lift the barbell by extending your hips and knees, taking care not to lock your knees.\n" +
                "Lower yourself (squat) down until your thighs are parallel to the floor.\n" +
                "Slowly raise yourself up to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Upright Rows", "This is an exercise for shoulder, biceps and upper back strengthening.", "Grasp a dumbbell in each hand with an overhand grip (palms facing downwards) and hold them in front of your thighs.\n" +
                "Stand with your feet shoulder width apart, your abs drawn in and your back straight.\n" +
                "Starting with the dumbbells at your thighs, slowly raise them up towards your shoulders by bending your elbows.\n" +
                "Pause at the top and rotate your shoulder blades together.\n" +
                "Lower the dumbbells in a controlled motion to your thighs.\n" +
                "Repeat" , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Bent Arm Pullover", "This is an exercise for chest, shoulder, lats and biceps strengthening.", "Lie flat on a bench with your head hanging slightly over the end and your feet flat on the floor.\n" +
                "Hold a dumbbell with both hands, keep your elbows in throughout the exercise.\n" +
                "Starting with your arms fully extended over your chest, slowly lower the dumbbell in an arc over your head and towards the floor.\n" +
                "Pull the dumbbell back up to chest height in a slow controlled manner and return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Incline Bench Press with Bands", "This is an exercise for chest and biceps strengthening.", "Securely fasten the band under the leg of a flat bench, near your head.\n" +
                "Lie flat on the bench with your feet flat on the floor in order to stabilise your movements.\n" +
                "Grasp one end of the band in each hand and starting with your hands at chest level.\n" +
                "Press upwards so your arms are fully extended.\n" +
                "With a slow controlled motion return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("One Arm Barbell Floor Press", "This is an exercise for chest and triceps strengthening.", "Lie flat on your back with your knees bent and your feet firmly on the floor.\n" +
                "Draw in your abs and contract your lats, and glutes.\n" +
                "Have your partner hand you the bar and with your bicep parallel to the floor lift and extend your arm fully so it is straight.\n" +
                "Return to the starting position.\n" +
                "Complete reps and switch arms." , "", "", null));
        exercises.add(new ExerciseDTO("Rear Deltoid Row Barbell", "This is an exercise for shoulder and biceps strengthening.", "Place a barbell on the floor in front of you with the resided weights attached.\n" +
                "With your knees bent and back straight grasp the bar with a wide overhand grip (palms facing downwards).\n" +
                "Keep your chest steady and your arms perpendicular to your chest.\n" +
                "Pull the bar as far up to your chest as you can.\n" +
                "Hold for a moment than lower the bar in a controlled manner.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Decline Dumbbell Fly’s", "This is an exercise for chest and shoulder strengthening.", "Lie on a decline bench with your head lower than your body and your feet firmly on the ground.\n" +
                "With dumbbells together above your shoulders and your arms straight out above you as your starting position.\n" +
                "Keeping your arms straight and with a slight bend in your elbows lower the dumbbells down towards the floor in an arc, up until just above your shoulders.\n" +
                "Return to the starting position in a slow controlled manner.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Butterfly Machine", "This is an exercise for chest strengthening.", "Adjust the machine so you are sitting at chest heights with the pads.\n" +
                "Sit on the machine with your back flat.\n" +
                "Place your forearms on the pads, parallel to the floor, this is starting position.\n" +
                "Using your forearms, push the pads together slowly as you squeeze your chest.\n" +
                "Return your arms to the stretched out starting position in a controlled motion.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Crossover with Bands", "This is an exercise for chest, biceps and shoulder strengthening.", "Secure an exercise to a post (or secure pole) and hold it in each hand.\n" +
                "Step forward so the band has some tension, facing away from the post.\n" +
                " Your arms at mid chest height and out to the side, palms facing forward this is your starting position.\n" +
                "Bring your hands together in front of you.\n" +
                "Hold this for a moment and then in a controlled motion return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Barbell Front Raise and Pullover", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a flat bench and grasp a barbell using a medium grip (about 15” apart).\n" +
                "Place the barbell on your upper thighs and lock your arms straight with a slight bend in your elbows.\n" +
                "Draw your abs in and keeping your back flat on the bench, raise your arms up in an arc over and behind your head (as if you were performing a reverse pullover).\n" +
                "Slowly return the barbell to the starting position on your thighs." , "", "", null));
        exercises.add(new ExerciseDTO("Pullover On Stability Ball With Weight", "This is an exercise for back, shoulders and neck strengthening.", "Grasp a Barbell or Dumbbell and sit on a Ball with your feet firmly placed on the floor.\n" +
                "Lower your body so your upper abs are the only part of your body supported on the Ball.\n" +
                "Bring the Barbell or Dumbbell to your chest with your arms extend.\n" +
                "In an arcing motion raise the Barbell or Dumbbell over and behind your head towards the floor.\n" +
                "Return to starting position.\n" +
                "Repeat." , "", "", null));

        //##### page 9 #####
        exercises.add(new ExerciseDTO("Butterfly Machine", "This is an exercise for chest strengthening.", "Adjust the machine so you are sitting at chest heights with the pads.\n" +
                "Sit on the machine with your back flat.\n" +
                "Place your forearms on the pads, parallel to the floor, this is starting position.\n" +
                "Using your forearms, push the pads together slowly as you squeeze your chest.\n" +
                "Return your arms to the stretched out starting position in a controlled motion.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Crossover with Bands", "This is an exercise for chest, biceps and shoulder strengthening.", "Secure an exercise to a post (or secure pole) and hold it in each hand.\n" +
                "Step forward so the band has some tension, facing away from the post.\n" +
                " Your arms at mid chest height and out to the side, palms facing forward this is your starting position.\n" +
                "Bring your hands together in front of you.\n" +
                "Hold this for a moment and then in a controlled motion return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Barbell Front Raise and Pullover", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a flat bench and grasp a barbell using a medium grip (about 15” apart).\n" +
                "Place the barbell on your upper thighs and lock your arms straight with a slight bend in your elbows.\n" +
                "Draw your abs in and keeping your back flat on the bench, raise your arms up in an arc over and behind your head (as if you were performing a reverse pullover).\n" +
                "Slowly return the barbell to the starting position on your thighs." , "", "", null));
        exercises.add(new ExerciseDTO("Incline Fly’s with a Twist", "This is an exercise for chest sculpting and strengthening which is very similar to the incline fly’s.", "Lie on an incline bench set at a 45 degree angle with a dumbbell in each hand and your feet flat on the ground.\n" +
                "Start with the dumbbells out to the sides of your chest, parallel to the floor as you would a regular Fly.\n" +
                "Raise the dumbbells over your chest in and arc, at the top turn your pinky finger in so they are facing each other.\n" +
                "Slowly return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Wide Grip Decline Barbell Pullover", "This is an advanced strengthening exercise which targets the pectoral or chest muscles.", "Lie on a decline bench with your feet higher than your head and your feet firmly on the ground.\n" +
                "Grasp the barbell with an extra wide grip (your hands near the plates) and starting at you’re your upper thighs raise the barbell in an arc over your head towards the floor.\n" +
                "Slowly return the barbell to starting position on your thighs.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Bosu Ball Push Up", "This is an exercise for chest, core, shoulders and triceps strengthening with the use of a Bosu ball.", "Place the Bosu ball flat side down on the floor.\n" +
                "Kneel on the floor with your arms fully extended on the ball, and body straight.\n" +
                "Keeping your abs drawn in and your body straight, by bending your elbows lower your chest to the ball.\n" +
                "Pause for a moment and then return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("One Armed Biased Push Up", "This is an exercise for chest strengthening but also strengthens ones core, shoulders, biceps and triceps.", "Arrange the ball so that as you begin the exercise, one hand on the Bosu ball and the other on the floor.\n" +
                "Place the ball flat side down on the floor.\n" +
                "Kneel on the floor with your arms fully extended on the dome, and body straight.\n" +
                "Keeping your abs drawn in and your body straight, lower your chest to the ball.\n" +
                "Pause for a moment and then return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Wide Grip Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie flat on a bench with your feet flat on the floor.\n" +
                "Place your hands wider than shoulder width apart on the bar and lift it off the rack, this is starting position.\n" +
                "Bring the bar over your chest and lower it till it is just above your body.\n" +
                "Extend your arms upward and raise the bar straight up.\n" +
                "Pause for a moment and with slow controlled movement, return the bar to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Underhand Pull downs", "This is an exercise for lats and biceps strengthening.", "Sitting upright with your abs drawn in grasp a Pull Down bar with and underhand grip shoulder width apart.\n" +
                "Pull the bar down till the bar reaches your upper chest, keeping your elbows close to your body.\n" +
                "Slowly return the bar to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Underhand Pull down", "This is an exercise for lats and biceps strengthening.", "Sitting upright with your abs drawn in grasp a Pull Down bar with and underhand grip shoulder width apart.\n" +
                "Pull the bar down till the bar reaches your upper chest, keeping your elbows close to your body.\n" +
                "Slowly return the bar to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Pullover On Stability Ball With Weight", "This is an exercise for back, shoulders and neck strengthening", "Grasp a Barbell or Dumbbell and sit on a Ball with your feet firmly placed on the floor.\n" +
                "Lower your body so your upper abs are the only part of your body supported on the Ball.\n" +
                "Bring the Barbell or Dumbbell to your chest with your arms extend.\n" +
                "In an arcing motion raise the Barbell or Dumbbell over and behind your head towards the floor.\n" +
                "Return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("One Legged Cable Kickback", "This is an exercise for gluts and hamstring strengthening.", "Standing at a cable tower attach an ankle cuff to a low pulley.\n" +
                "Facing the weight stack, with a slight bend in your knees and your abs drawn in\n" +
                "Slowly kick your ankle back in and arc as high as you are able to.\n" +
                "Return to starting position and switch ankles." , "", "", null));

        //##### page 10 #####
        exercises.add(new ExerciseDTO("Bent Over Row with Barbell", "This exercise focuses on the middle back", "Place your feet shoulder width apart.\n" +
                "Bend over the bar so your back is flat and parallel to the floor.\n" +
                "Grasp the bar a bit wider than shoulder width apart with an overhand grip.\n" +
                "Hold the bar at arms length, this is starting position.\n" +
                "Slowly raise the bar to the lower part of your chest.\n" +
                "Pause for a moment than return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Back Fly’s with Exercise Band", "This is an exercise for shoulder, middle back and triceps strengthening.", "Place an exercise band around a post or machine at mid chest height.\n" +
                "Stand with your feet shoulder width apart, abs drawn in and knees slightly bent.\n" +
                "Stand back from the post so there is tension in the band, grasping the band with your arms extended in front of you and a slight bend in your elbows; this is your starting position.\n" +
                "Bring your arms back in a controlled motion till they are parallel at your sides and in line with your shoulders.\n" +
                "Slowly return your arms to the starting position in front of your chest.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Bent Over Rear Deltoid Raise With Head On Bench", "This is an exercise for shoulder strengthening.", "Rest your forehead on an incline bench in oder to stabilise your movements so you are bent over with your back parallel to the floor, draw your abs in.\n" +
                "Grasp dumbbells in your hands with your arms straight and elbows slightly bent ensuring they are not locked.\n" +
                "Slowly raise the dumbbells up to shoulder height.\n" +
                "Pause for a moment and then return to the starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Internal Cable Rotation", "This is an exercise for shoulder and biceps strengthening.", "Sit on the floor with your body close to low cable pulley.\n" +
                "Grasp the pulley with your left arm and position your elbow bent at 90 degree angle.\n" +
                "Pull the cable towards your body and internally rotate your shoulder until your forearm is across your abs.\n" +
                "Return to starting position.\n" +
                "Repeat with right arm." , "", "", null));
        exercises.add(new ExerciseDTO("One Arm Flat Bench Fly’s", "This is an exercise for chest strengthening.", "Lie on a flat bench with your free hand holding the bench.\n" +
                "Grasp the dumbbell in your other hand and with your forearm parallel to the floor raise your arm in an arc the middle of your chest.\n" +
                "Once the dumbbell is at the middle of your chest line, slowly lower is along the same path to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Upright Band Rows", "This is an exercise for lats, triceps and shoulder strengthening.", "Stand on the band so that the handles are level with your upper thigh.\n" +
                "With your back straight and abs drawn in raise your hands upward to your collarbone.\n" +
                "Your elbows and arms should be parallel with the floor.\n" +
                "Slowly lower your arms and return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Barbell Lunges", "This is a classic exercise used for strengthening the hamstrings, quadriceps, calves and gluts.", "Start by placing a barbell across your upper back, using a grip slightly wider than your shoulders.\n" +
                "Stand with your feet about 8 inches apart, toes facing forward.\n" +
                "Take a step forward (2-3 feet) keeping your abs drawn in and your upper body straight.\n" +
                "Slowly lower one knee down as if kneeling while keeping your other knee bent at a 90 degree angle, do not let your knee touch the ground.\n" +
                "Lower your body so that your rear knee is just above the floor and hold for a moment before returning to the starting position.\n" +
                "Repeat on other side." , "", "", null));
        exercises.add(new ExerciseDTO("Dumbbell Lunges", "This is an exercise for whole leg but mainly hamstring strengthening.", "Grasp a dumbbell in each hand.\n" +
                "Stand with your feet about 8 inches apart, toes facing forward.\n" +
                "Take a step forward (2-3 feet) keeping your abs drawn in and your upper body straight.\n" +
                "Slowly lower one knee down as if kneeling while keeping your other knee bent at a 90 degree angle, do not let your knee touch the ground.\n" +
                "Lower your body so that the rear knee is just above the floor and hold for a moment before returning to the starting position.\n" +
                "Repeat on opposite side." , "", "", null));
        exercises.add(new ExerciseDTO("Flutter Kicks", "This is an exercise for quadriceps and hamstring strengthening.", "Lie face down on a bench with your hips on the edge, hold onto the bench with your hands.\n" +
                "Allow your legs and toes hang down.\n" +
                "Draw your abs in, squeeze your gluts and hamstrings.\n" +
                "Kick each back and up while keeping your hips on the bench.\n" +
                "Alternate kicking each leg." , "", "", null));
        exercises.add(new ExerciseDTO("Lying Leg Curl Machine", "This is an exercise for hamstring strengthening.", "Lie face down on a leg curl machine and place your heels under the roller pad.\n" +
                "Grasp the grips with your hands for support and slowly curl your ankles up towards your back.\n" +
                "Hold for a moment and then return to starting position.\n" +
                "Repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Romanian Dead Lift", "This is an exercise similar to the regular dead lift and strengthens the hamstring, calves, quadriceps and gluts.", "Place a barbell in front of your feet on the ground.\n" +
                "Grasp the barbell with a grip a bit wider than shoulder width apart.\n" +
                "Bend your knees slightly, keeping your hips and back straight.\n" +
                "Lift the bar straight up concentrating on using your hips as you stand.\n" +
                "Stand with the bar resting against your thighs.\n" +
                "Lower the bar to the floor with a slight bend in your knees flexing your hips back for stability." , "", "", null));
        exercises.add(new ExerciseDTO("Seated Leg Curl", "This is an exercise for hamstring strengthening which is similar to the laying leg curls.", "Adjust the machine so that the footpad is just above your heels.\n" +
                "Sit upright with your abs drawn in and your legs in front of you.\n" +
                "Slowly curl your legs back towards you and hold for a moment.\n" +
                "With controlled movement return to the starting position." , "", "", null));

        //##### page 11 #####
        exercises.add(new ExerciseDTO("Walking Lunges", "A walking lunge gives you the benefit of lunges plus core muscle training from the constant motion.", "Start by either placing a barbell across your upper back or by grasping a dumbbell in each hand.\n" +
                "Stand with your feet about 8 inches apart, toes facing forward.\n" +
                "Take a lunge forward keeping your abs drawn in and your upper body straight.\n" +
                "Slowly lower one knee down as if kneeling while keeping your other knee bent at a 90 degree angle, do not let your knee touch the ground.\n" +
                "Lower your body to just above the floor and hold for a moment before pushing off with the back foot.\n" +
                "Step through and repeat the exercise with the other leg." , "", "", null));
        exercises.add(new ExerciseDTO("Smith Machine Hack Squat", "This is another variation of the basic Squat that focuses primarily on the quadriceps, or thigh muscles, while using the Smith Machine.", "Adjust the bar to a low setting just above your ankles.\n" +
                "Stand with the bar on the floor just behind your legs, with your feet shoulder width apart and your toes pointing forward.\n" +
                "With your feet firmly placed on the floor reach down and grasp the bar from behind, with an overhand grip.\n" +
                "Lift the bar by extending your hips and knees, taking care not to lock your knees.\n" +
                "Lower yourself (squat) down until your thighs are parallel to the floor.\n" +
                "Slowly raise yourself up to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Leg Press", "Along with the Squat, this exercise is crucial for building and strengthening the legs.", "Adjust the seat of the machine so that your feet comfortably reach the crosspiece with a slight bend in your knees.\n" +
                "To begin, press your feet forward at a shoulder width stance and release the safety locks.\n" +
                "Slowly lower the weight towards your body, keeping your abs drawn in and your knees moving in the same direction as your feet to a 90 degree angle.\n" +
                "Do not lock your knees or bounce the weight." , "", "", null));
        exercises.add(new ExerciseDTO("Rear Lunges with Barbell", "This is an advanced version of a lunge.", "Place a barbell on a rack at chest height.\n" +
                "Lift the barbell of of the rack and onto your shoulders, gripping the bar slightly wider than shoulder width apart.\n" +
                "Stand with your feet approximately 8 inches apart with your toes pointing forward.\n" +
                "Slowly take a step backward with your right leg.\n" +
                "Keeping your abs drawn in and your upper body straight, lower your body until your left knee is almost on the ground. You may choose to place a mat or towel under your knee.\n" +
                "Hold for a moment and then return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Rear Lunges with Dumbbell", "This is an advanced version of a lunge.", "Grasp a dumbbell in each hand.\n" +
                "Stand with your feet approximately 8 inches apart with your toes pointing forward.\n" +
                "Slowly take a step backward with your right leg.\n" +
                "Keeping your abs drawn in and your upper body straight, lower your body until your left knee is almost on the ground. You may choose to place a mat or towel under your knee.\n" +
                "Hold for a moment and then return to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Squats using Dumbbells", "This is a great overall exercise for the lower body that uses dumbbells instead of a barbell. Beginners may find this version easier to perform.", "Grasp a dumbbell in each hand.\n" +
                "Stand with feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Side Squats with Barbell", "This version of a squat works the hip adductors, helping build better core strength and coordination.", "Place a barbell on your shoulders and grasp it with a wide grip.\n" +
                "Stand with your feet wide apart, with the foot of the leg you will be leaning towards angled out.\n" +
                "Bring your lower body to your foot by bending the hip and knee of your lead leg, and keeping your other leg fairly straight.\n" +
                "Return to the starting position and switch legs." , "", "", null));
        exercises.add(new ExerciseDTO("Single Leg Squat with Barbell", "This is an advanced exercise and modification of the basic squat. Mastery of the squat is recommended before attempting this exercise.", "Place a barbell on your back, above your shoulders with a wide grip.\n" +
                "Cross the lower leg above the knee of your supporting leg (the leg you will squat with).\n" +
                "Squat down as low as you are able.\n" +
                "Slowly return to an upright position by straightening your hip and knee.\n" +
                "Switch legs and repeat." , "", "", null));
        exercises.add(new ExerciseDTO("Squat to Bench with Barbell", "This is s great overall exercise for the lower body. There are many variations on the squat, this one uses a bench to remind you how far down to squat.", "Place a bench behind you.\n" +
                "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in, descend slowly and squat down, bending at the knees and hips.\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance). Do not sit on the bench.\n" +
                "Pause in the downward position and slowly return upright to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Step Ups with Barbell", "This exercise builds coordination as well as muscle.", "Place a bench, box or step in front of you.\n" +
                "Grasp a barbell with a wide grip and place it across your shoulders.\n" +
                "With your left leg, step up on the bench, and follow by stepping up with your right leg.\n" +
                "Step down with your left leg and then your right leg.\n" +
                "Repeat starting with your right leg." , "", "", null));
        exercises.add(new ExerciseDTO("Hip Adduction", "This exercises strengthens hip flexors and muscles of the thighs.", "Stand alongside a cable pulley stack and attach a cuff to the pulley and your ankle.\n" +
                "Step away from the stack, holding onto the stack for support.\n" +
                "Standing firm on the foot not attached to the cable and slowly abduct, or pull, your cuffed ankle in front of your stabilizing leg.\n" +
                "Repeat and switch ankles." , "", "", null));
        exercises.add(new ExerciseDTO("Squat to Bench with Dumbbells", "This is a great overall exercise for the lower body. There are many variations on the squat, this one uses a bench to remind you how far down to squat.", "Place a bench behind you.\n" +
                "Grasp a dumbbell in each hand.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by squatting down and bending at the knees and hips.\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position." , "", "", null));

        //##### page 12 #####
        exercises.add(new ExerciseDTO("Step Ups with Dumbbells", "This exercise builds coordination as well as muscle.", "Place a bench, box or step in front of you.\n" +
                "Grasp a dumbbell in each hand.\n" +
                "With your left leg, step up on the bench, and follow by stepping up with your right leg.\n" +
                "Step down with your left leg and then your right leg.\n" +
                "Repeat starting with your right leg." , "", "", null));
        exercises.add(new ExerciseDTO("Front Squat with Barbell", "The Front Squat works the same muscles as the rear squat without placing the weight of the bar on your shoulders. This exercise can be a good substitute for people with back and neck injuries.", "Place the barbell on your upper chest, resting it across your front deltoids and holding it with your arms crossed securely.\n" +
                "Keep your head and back straight, abs drawn in and toes pointing slightly outward.\n" +
                "Slowly “squat” down so your upper thighs are parallel to the floor.\n" +
                "Slowly return to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Front Squat to Bench with Barbells", "The Front Squat works the same muscles as the rear squat without placing the weight of the bar on your shoulders. This exercise can be a good substitute for people with back and neck injuries. By using a bench you remind yourself how far to squat down", "Place a bench behind you.\n" +
                "Place the barbell on your upper chest, resting it across your front deltoids and holding it with your arms crossed securely.\n" +
                "Keep your head and back straight, abs drawn in and your toes pointing slightly outward.\n" +
                "Slowly “squat” down so your upper thighs are parallel to the floor.\n" +
                "Slowly return to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Iron Cross with Dumbbells", "This is an advanced exercise that works the whole body.", "With a dumbbell in each hand, stand in a low squat stance, your feet shoulder width arms held out in front of you with your palms facing in.\n" +
                "Standing upright, move your arms out and away from your body forming a “T” or “cross”." , "", "", null));
        exercises.add(new ExerciseDTO("Jefferson Squats with Barbell", "This is a rarely used exercise which is without question one of the best exercises for building and shaping the inner thighs.", "Place a barbell between your legs so it is perpendicular to your feet.\n" +
                "Grasp the barbell with one hand in front of you and one hand behind you with an overhand grip.\n" +
                "Squatting down so your thighs are parallel to the floor slowly lift the weight up between your legs as you stand.\n" +
                "Slowly return to a starting position just above the floor." , "", "", null));
        exercises.add(new ExerciseDTO("Leg Extensions", "This exercise is a staple for building strong legs.", "Adjust the seat so that your knees have full range of motion and that the footpad fits over your legs, just above your ankles.\n" +
                "Grasp the handles with your hands for support, keeping your hip and back against the bench.\n" +
                "Slowly extend your legs until your knees are straight, but do not lock your knees.\n" +
                "Hold for a moment and then with controlled movements return to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Narrow Stance Leg Press", "This is a variation of the leg press which targets more of the external muscles of the legs by moving your feet closer together.", "Sit on a leg press machine with your feet on the crosspiece 6 inches apart, with your toes pointed slightly outward.\n" +
                "Grasp the handles on the side of the machine and release the locks.\n" +
                "Keeping your abs drawn in, bend your knees and bring the weight as far as possible towards your chest.\n" +
                "Hold this position for a moment and then slowly return the weight to starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Lying Squat", "This exercise is similar to a Leg Press Machine working the same muscles.", "Adjust the shoulder rack of the machine so that your feet comfortably reach the crosspiece with a slight bend in your knees.\n" +
                "To begin, press your feet forward and release the safety locks.\n" +
                "Slowly lower the weight towards your body, keeping your abs drawn in and your knees moving in the same direction as your feet to a 90 degree angle.\n" +
                "Do not lock your knees or bounce the weight." , "", "", null));
        exercises.add(new ExerciseDTO("Narrow Stance Hack Squats", "Like the Hack Squat, this exercise works the muscles of the leg. The narrow stance targets the external muscles more efficiently.", "Lie face up on a Hack Squat machine with your shoulders against the pad.\n" +
                "Place your feet facing forward at a distance of 6 inches apart with your toes point slightly outward.\n" +
                "Release the dock levers and place your hands on the handgrips.\n" +
                "Drawing your abs in, extend your body standing upright.\n" +
                "Lower your body to a squatting position so your knees are bent as if you were sitting down.\n" +
                "Return to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("Narrow Stance Squat with Barbell", "A great overall exercise for the lower body. This version of the Squat targets and defines more of the externals leg muscles.", "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place your feet about 6 inches apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in, descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position." , "", "", null));
        exercises.add(new ExerciseDTO("One Leg Squat with Barbell", "The One Leg Squat isolates the muscles of the leg for optimal exercise. This exercise is also used to build or rebuild strength after injury.", "Place a bench or box 12-18 inches tall behind you. The taller the box the greater the difficulty of the exercise.\n" +
                "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place one foot up on the bench, and your other foot firmly on the floor 2-3 feet in front you.\n" +
                "Drawing your abs in descend slowly by bending your front knee and hip as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toe (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.\n" +
                "Switch legs and repeat." , "", "", null));
        exercises.add(new ExerciseDTO("One Arm Snatch with Barbell", "This is an advanced exercise that works most of the muscles of the body. Exercises like this are used to develop functional strength for sports that require strong hands.", "Stand with your feet shoulder width apart, and a slight bend in your knees.\n" +
                "Grasp the bar with an overhead grip.\n" +
                "Starting with the barbell held slightly above the knees (hang position), lift the weight up over your head (step 1) then back to shoulder height (step 2) and finally back to starting position between near your knees." , "", "", null));

        //##### page 13 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 14 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 15 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 16 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 17 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 18 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 19 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 20 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 21 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 22 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 23 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 24 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

        //##### page 25 #####
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));
        exercises.add(new ExerciseDTO("", "", "" , "", "", null));

//        exercises.add(new ExerciseDTO("", "", null));

        return exercises;
    }


}
