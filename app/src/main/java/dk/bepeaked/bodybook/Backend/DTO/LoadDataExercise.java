package dk.bepeaked.bodybook.Backend.DTO;

import android.util.Log;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DAO.ExerciseDAO;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutDAO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Nicki on 04/01/17.
 */

public class LoadDataExercise {

    RealmList<ExerciseDTO> exercises = new RealmList<ExerciseDTO>();
    private String name, desc;
    RealmList<WorkoutDTO> realmListWorkoutDTO;
    WorkoutDTO workoutDTO;
    WorkoutController wc = new WorkoutController();
    ExerciseDAO exerciseDAO = new ExerciseDAO();
    Realm realm = Realm.getDefaultInstance();



    public LoadDataExercise() {
    }

    public RealmList<WorkoutDTO> dataLoadDummy() {
        return realmListWorkoutDTO;
    }


    public void dataCreateAllNeededData(String newPlanName) {

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        RealmList<ExerciseDTO> newList;
       newList = dataCreateAllExercises();
        for (int i = 0; i < newList.size(); i++) {
            try {
                exerciseDAO.newExercise(newList.get(i));
            } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
                exceptionNameAlreadyExist.printStackTrace();
            }
        }

        try {
            wc.addPlan(newPlanName);
        } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
            exceptionNameAlreadyExist.printStackTrace();
        }
    }

    public RealmList<ExerciseDTO> dataCreateAllExercises() {

        //##### page 1 #####
        exercises.add(new ExerciseDTO(1, "Bench Press", "This is an exercise for the chest.", "Lie on a flat bench with your feet flat on the floor, keep your back flat on the bench.\n" +
                "Grasp the bar a little wider than shoulder width apart.\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position.", "http://img.everkinetic.com/?action=image&id=19&number=0&key=69cadbdd2&version=cf619212&size=280,420", "http://img.everkinetic.com/?action=image&id=19&number=1&key=69cadbdd2&version=5c40b61d&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(2, "Supermans", "This is an excellent exercise and a stretch for the lower back and core muscles.", "Lie flat on your stomach with your arms stretched out in front of you.\n" +
                "Raise your arms and legs off the floor and hold this position for 2 seconds.\n" +
                "Return to the starting position on the floor.\n" +
                "Repeat.", "http://img.everkinetic.com/?action=image&id=436&number=0&key=69cadbdd2&version=5b45371b&size=280,420", "http://img.everkinetic.com/?action=image&id=436&number=1&key=69cadbdd2&version=7a1d6486&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(3, "Push Ups", "This exercise is the main strengthening exercise when wanting to strengthen ones chest, biceps and triceps as well as core muscles.", "Start with a basic push up, lay face down on the floor, or a mat; with your feet together curled slightly so you rise onto the ball of your feet.\n" +
                "Place you hands shoulder width apart on the either side of your chest.\n" +
                "Draw your abs in.\n" +
                "Inhale as you raise your body up till your arms are straight.\n" +
                "Keep your head and neck level with your body (don’t look up or down) and don’t allow your back to rise or fall.\n" +
                "Exhale out as you lower your body back to the ground.\n" +
                "Repeat.", "http://img.everkinetic.com/?action=image&id=340&number=0&key=69cadbdd2&version=52850b73&size=280,420", "http://img.everkinetic.com/?action=image&id=340&number=1&key=69cadbdd2&version=b3f29a67&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(4, "Pull Ups", "This is an exercise in lats, biceps and middle back strengthening.", "Grab the pull-up bar with the palms facing forward using the prescribed grip.\n" +
                "As you have both arms extended in front of you holding the bar at the chosen grip width, bring your torso back around 30 degrees or so while creating a curvature on your lower back and sticking your chest out. This is your starting position.\n" +
                "Pull your torso up until the bar touches your upper chest by drawing the shoulders and the upper arms down and back. Exhale as you perform this portion of the movement.\n" +
                "After a second on the contracted position, start to inhale and slowly lower your torso back to the starting position when your arms are fully extended and the lats are fully stretched.\n" +
                "Repeat this motion.", "http://img.everkinetic.com/?action=image&id=374&number=0&key=69cadbdd2&version=b2fcdad0&size=280,420", "http://img.everkinetic.com/?action=image&id=374&number=1&key=69cadbdd2&version=fbe8dcfa&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(5, "Bridging", "This is an excellent exercise for strengthening and maintaining the core muscles.", "Lie on your back with your knees bent and your feet flat on the floor.\n" +
                "Lift up hips and bum off the floor as you draw your abs in and keep your gluts tight.", "http://img.everkinetic.com/?action=image&id=447&number=0&key=69cadbdd2&version=8f3c61db&size=280,420", "http://img.everkinetic.com/?action=image&id=447&number=1&key=69cadbdd2&version=8d4ca279&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(6, "Chin Ups", "This is an extremely good exercise for upper arm and middle back strengthening.", "Grasp the bar with an supinated (overhand) grip.\n" +
                "Let your body hang from the bar with your arms straight.\n" +
                "Slowly pull yourself up so that your chin is higher than the bar.\n" +
                "With a controlled movement lower yourself to the starting position.", "http://img.everkinetic.com/?action=image&id=380&number=0&key=69cadbdd2&version=30f71856&size=280,420", "http://img.everkinetic.com/?action=image&id=380&number=1&key=69cadbdd2&version=6cc58063&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(7, "Seated Military Press", "An exercise for the shoulder and triceps strengthening.", "Sit on the bench with your toes pointing straight out, back straight and abs drawn in.\n" +
                "Grip the bar with your palms facing outwards and your hands shoulder width apart\n" +
                "With bar in front of your head, press upwards extending your arms but not locking them.\n" +
                "Pause at the top and then in a controlled movement lower the bar to the starting position.", "http://img.everkinetic.com/?action=image&id=42&number=0&key=69cadbdd2&version=cb370555&size=280,420", "http://img.everkinetic.com/?action=image&id=42&number=1&key=69cadbdd2&version=12732321&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(8, "Close Grip Barbell Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a flat bench with your feet flat on the floor, keep your back flat on the bench.\n" +
                "Grasp the bar a close grip (approximately 14” apart).\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position.", "http://img.everkinetic.com/?action=image&id=238&number=0&key=69cadbdd2&version=32b3424f&size=280,420", "http://img.everkinetic.com/?action=image&id=238&number=1&key=69cadbdd2&version=d253d762&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(9, "Arnold Press", "An exercise for shoulder and triceps strengthening.", "Sit on a flat bench, feet point forward with your abs drawn in.\n" +
                "Grasp a dumbbell in each hand with your palm facing towards your body\n" +
                "Bend your elbows to 90 degrees and raise both your arms to the starting point in line with your shoulders.\n" +
                "With a steady controlled motion raise your arms up while straightening your elbows while rotating your forearms so that your palms now face away from your body.\n" +
                "Bring the dumbbells closer together but do not fully extend your elbows.\n" +
                "With a controlled motion lower the dumbbells to the starting position at your shoulders.", "http://img.everkinetic.com/?action=image&id=50&number=0&key=69cadbdd2&version=ac246b9e&size=280,420", "http://img.everkinetic.com/?action=image&id=50&number=1&key=69cadbdd2&version=68a62bda&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(10, "Side Plank", "This is an exercise for core strengthening.", "Lay on one side of your body with your legs straight and your forearm perpendicular to your body in front of you.\n" +
                "Drawing your abs in, slowly raise yourself up so you are balanced on your feet and your forearm.\n" +
                "Hold this position and slowly return back to the starting position.\n" +
                "Repeat on other side.", "http://img.everkinetic.com/?action=image&id=456&number=0&key=69cadbdd2&version=9d379ce4&size=280,420", "http://img.everkinetic.com/?action=image&id=456&number=1&key=69cadbdd2&version=27e06d67&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(11, "Standing Leg Curls", "This is an exercise for hamstring strengthening.", "Standing at a leg curl machine adjust the rear footpad so it is just above your ankle.\n" +
                "Grasp the handles of the machine for support and draw your abs in.\n" +
                "Raise your foot up towards your back slowly.\n" +
                "Return to the starting position and chance legs.", "http://img.everkinetic.com/?action=image&id=477&number=0&key=69cadbdd2&version=d2160de2&size=280,420", "http://img.everkinetic.com/?action=image&id=477&number=1&key=69cadbdd2&version=3a1cb415&size=280,420", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(12, "Incline Shoulder Press Dumbbell", "This is an exercise for shoulder strengthening.", "Lie down on an incline bench with your feet flat on the floor and grasp the dumbbells.\n" +
                "With your elbows bent raise your arms up until in line with your shoulders this is your starting position.\n" +
                "With your abs drawn in, raise the dumbbells as high as you are able above your shoulders.\n" +
                "Lower the dumbbells in a slow controlled manner to starting position.", "http://img.everkinetic.com/?action=image&id=137&number=0&key=69cadbdd2&version=9953e6cc&size=280,420", "http://img.everkinetic.com/?action=image&id=137&number=1&key=69cadbdd2&version=0322b66d&size=280,420", new RealmList<SetDTO>()));

        //##### page 2 #####
        exercises.add(new ExerciseDTO(13,"Incline Dumbbell Press", "This exercises is for mainly chest strengthening but also strengthens your triceps and shoulders.", "Set and incline bench at a 45 degree angle.\n" +
                "Start with the dumbbells at shoulder height, your arms wide and elbows pointing down to the floor.\n" +
                "Raise your arms up over your chest bringing the dumbbells closer together as they meet over your chest.\n" +
                "Slowly return the dumbbells to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(14, "Bench Press Dumbbell", "This is an exercise for chest, triceps and shoulder strengthening.", "Grasp the dumbbells in each hand and lie on a flat bench with your feet firmly on the ground.\n" +
                "Extend your arms up over your chest with your palms facing forwards.\n" +
                "Press the dumbbells up over your chest till your arm are fully extended this is your starting position.\n" +
                "Bend your elbows to a 90 degree angle so your upper arms are parallel with the floor.\n" +
                "Slowly lower the dumbbells as low as comfortable along your chest.\n" +
                "With a controlled motion return back to your starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(15, "Upright Barbell Rows", "This is an exercise for shoulder, biceps and upper back strengthening.", "Stand with your feet shoulder width apart, your abs drawn in and back straight.\n" +
                "Place the bar on rack in the position where your arms are fully extended in front of you with your elbows slightly bent.\n" +
                "Place your hands shoulder width apart and raise the bar up towards your chin with a controlled motion.\n" +
                "Pause at the top for a moment and rotate your shoulder blades together.\n" +
                "Lower the bar to the starting position.\n" +
                "Repeat", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(16, "Incline Bench Press", "This is an exercise for chest and triceps strengthening.", "Lie flat on an incline bench set at a 45 degree angle, with your feet shoulder width apart.\n" +
                "Grasp the bar a little wider than shoulder width apart.\n" +
                "Raise the barbell above your body and move it over the middle of your chest, this is your starting position.\n" +
                "Lower the bar down so it just touches your chest.\n" +
                "Raise the bar straight up till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(17, "Shoulder Shrugs", "An exercise for trapezius strengthening.", "Stand with your feet shoulder width apart and a slight bend in your knees.\n" +
                "Hold a dumbbell in each hand and with your arms at your sides.\n" +
                "Lower your shoulders as much as possible.\n" +
                "With your arms straight raise both shoulders up towards your ears.\n" +
                "Hold the upright positions for a moment and then lower in a controlled motion.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(18, "Hack Squat Machine", "This exercise is a variation of the Squat which uses a machine.  This exercise may be preferred by beginners.", "Lie face up on a Hack Squat machine with your shoulders against the pad.\n" +
                "Place your feet facing forward at slightly less than shoulder width apart with your toes point slightly outward.\n" +
                "Release the dock levers and place your hands on the hand grips.\n" +
                "Drawing your abs in, extend your body standing upright.\n" +
                "Lower your body to a squatting position so you knees are bent as if you were sitting down.\n" +
                "Return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(19, "Wide Grip Decline Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a decline bench with your head lower than your feet.\n" +
                "Place your hands wider than shoulder width apart on the bar and lift it off the rack.\n" +
                "Bring the bar over your chest and lower it till it is just above the lower portion of your chest, this is starting position.\n" +
                "Extend your arms upward and raise the bar straight up.\n" +
                "Pause for a moment and then lower the bar to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(20, "T-Bar Rows", "This is an exercise for middle back, biceps and shoulder strengthening.", "Place your feet on either side of a T-Bar Machine.\n" +
                "With your knees slightly bent and abs drawn in, grasp the handles with a narrow grip.\n" +
                "Bend at the waist so your chest is parallel to the floor, this is starting position. Slowly pull the bar to your chest, as high as you can.\n" +
                "Pause at the top for a moment and then lower the bar to starting position.\n" +
                "Repeat", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(21, "Dumbbell Shoulder Press", "An exercise for shoulder, chest and triceps strengthening.", "Sitting on a bench, with your abs drawn in grasp a dumbbell in each hand, with your palms facing forward.\n" +
                "Begin with your arms at the height of your shoulders.\n" +
                "Extend your arms and raise the dumbbells up above your head.\n" +
                "At the top of the movement, bring your arms closer together.\n" +
                "Pause for a moment and then in a controlled movement lower the dumbbells to the starting position.\n" +
                "Repeat", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(22, "Cable Crossover", "This is an exercise for chest and shoulder strengthening.", "Attach the cable pulley to shoulder height.\n" +
                "Grasp the pulley in both hands and stand approximately one foot in front of the weight stacks, with one foot slightly in front of the other.\n" +
                "With a slight bend in your elbows bring your hand together in front of your chest on downward angle.\n" +
                "When your hands meet at the midpoint of your chest, hold for a moment.\n" +
                "With a slow controlled motion return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(23, "Barbell Squat", "This is an exercise for leg strengthening, mainly the quadriceps.", "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(24, "Dumbbell Flys", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a flat bench with a dumbbell in each hand and your feet firmly on the ground.\n" +
                "Lift the dumbbells over your chest extending your arms fully with a slight bend in your elbows, this is your starting position.\n" +
                "Keeping a slight bend in your elbows, lower the dumbbells to the floor in an arc like motion.\n" +
                "Slowly return the dumbbells over your chest to the starting position in a controlled motion.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));

        //##### page 3 #####
        exercises.add(new ExerciseDTO(25, "Chest Dips", "This is an exercise for chest, triceps and shoulder strengthening.", "Stand in between the parallel bars.\n" +
                "Grip the handles of the parallel bars and push yourself up to the starting position with straightened arms.\n" +
                "With your elbows close to your body keep your hips straight, lower your body forward by bending your elbows so your chest is leading as you go down.\n" +
                "Raise yourself back up to the starting position and repeat.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(26, "Machine Bench Press", "This is an exercise for chest, biceps and shoulder strengthening.", "Adjust the machine so that you are sitting with the bars at chest height.\n" +
                "Place your hands on the bars and place your feet on the foot rest.\n" +
                "Press out, extending your arms as far as possible in a steady motion.\n" +
                "Pause for a moment and then return slowly to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(27, "Hyperextensions", "This exercise not only adds strength but also flexibility to the back and core muscles.", "Adjust the hyperextension bench so that your ankles are tucked under the footpads and your thighs are flat across the top pad.\n" +
                "With your arms folded across your chest and your back straight, slowly bend at the waist towards the floor, keeping your back flat.\n" +
                "Slowly return to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(28, "Dumbbell Dead Lifts", "This is an exercise for lower back, hamstring and calves strengthening.", "Grasp 2 dumbbells and stand with your feet shoulder width apart.\n" +
                "Keeping your back straight bend at the waist, allow some bend in your knees.\n" +
                "Grasp the dumbbells with an overhand grip in each hand.\n" +
                "Straighten your back as you hold the dumbbells at arm’s length.\n" +
                "Bend over again lowering the dumbbells to just above the floor.\n" +
                "Return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(29, "Front Barbell Raises", "An exercise for shoulder, chest and forearm strengthening.", "Grasp a barbell with an overhand grip(palms of hands face downwards) .\n" +
                "Stand up-right with your feet shoulder width apart, knees slightly bent and your abs drawn in.\n" +
                "With your arms straight, raise the barbell in a controlled motion to shoulder level.\n" +
                "Hold for a moment and then lower the barbell in at the same motion.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(30, "Front Cable Raises", "An exercise for shoulders, chest and forearm strengthening.", "Place the pulley on the setting just above the floor.\n" +
                "Stand with your feet shoulder width apart, your abs drawn in and your knees slightly bent.\n" +
                "Starting at waist height, take the pulley in your left hand, palm facing the floor with otherwise known as an overhand grip, and raise your straight arm up towards your left shoulder.\n" +
                "At shoulder height pause for a moment.\n" +
                "With a controlled movement resist the weight and slowly lower your arm back to starting position at your waist.\n" +
                "Repeat the exercise with your right arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(40, "Front Raises", "This is an exercise for shoulder, chest and forearm strengthening.", "Stand with your feet shoulder width apart, abs drawn in and knees slightly bent.\n" +
                "Grasp a dumbbell in each hand, with your palm facing down.\n" +
                "Start with the dumbbell at waist height.\n" +
                "Raise your left arm, keeping your elbow slightly bent and your arm straight, to in line with your shoulder.\n" +
                "Pause for a moment and with a controlled motion lower your arm to back to the starting position at your waist.\n" +
                "Repeat with your right arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(41, "Seated Shoulder Press Machine", "This is an exercise for the shoulder strengthening.", "Sit upright with your back supported by the chair and draw your abs in.\n" +
                "Place your hands on the bars and with smooth even motions press upward extending your arms, but not locking them.\n" +
                "At the top pause then with controlled motion lower the bars to your starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(42, "Barbell Shrugs", "This is an exercise for trapezius strengthening.", "Stand on the floor with your abs drawn in and your back straight.\n" +
                "Grasp a barbell with a grip a little wider than shoulder width at arms length.\n" +
                "Drop your shoulders as much as possible to start.\n" +
                "Raise your shoulder as high as possible.\n" +
                "Pause for a moment at the top and then return to starting position in a controlled motion.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(43, "One Arm Upright Row", "This is an exercise for shoulder and trapezius strengthening.", "Stand near a post or other stable tall object. With your one hand, hold the post.\n" +
                "Grasp the dumbbell in your other hand with a pronated grip (palms facing backwards).\n" +
                "Place the dumbbell in front of your thigh, this is starting position.\n" +
                "Lift the dumbbell upward to your shoulder with your elbow pointing away from your body in a rowing motion.\n" +
                "Lower your arm to starting position and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(44, "Flat Bench Leg Raises", "This is an exercise for lower abdominal strengthening.", "Lie on a flat bench with your hands under your hips supporting your back.\n" +
                "Your legs should be unsupported by the bench from below your knees.\n" +
                "With your feet together and your toes flexed upwards, raise your straight legs up a few cm off the bench; both of your legs should have no contact with the bench. This is your starting position.\n" +
                "Keep your legs straight with a slight bend in the knees and raise your legs to just before 90 degrees with your hips.\n" +
                "Pause at the top and lower your legs in slow controlled manner back to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(45, "Smith Machine Shoulder Shrugs", "This is an exercise for shoulder strengthening.", "Place the barbell at waist height.\n" +
                "Stand with your feet shoulder width apart and your abs drawn in.\n" +
                "Grasp the bar with an overhand grip and straightened arms.\n" +
                "As you lift the bar allow the weight to pull your shoulders down, shrug your shoulders up and to the back.\n" +
                "Hold for a moment and then return to starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 4 #####
        exercises.add(new ExerciseDTO(46, "Seated Barbell Shoulder Press", "This is an exercise for shoulder, chest and triceps strengthening.", "Sitting on a bench with a barbell rack, grasp the barbell with a grip 3 to 4 inches wider than your shoulders.\n" +
                "Lift the bar off the rack and lower it to just at the height of your shoulders.\n" +
                "While maintaining  good posture, straighten your arms and raise the bar up above your head.\n" +
                "Pause for a moment and then in a controlled movement lower the bar to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(47, "Flat Bench Cable Flys", "This is an exercise for chest and shoulder strengthening.", "Lie on flat bench between two cable towers and your feet firmly on the ground.\n" +
                "Grasp a pulley in each hand with your palms facing up.\n" +
                "With a slight bend in your elbows, squeeze your chest and pull the cables together meeting in the middle of your chest.\n" +
                "Hold for a moment and then slowly lower your hands back to starting position at bench height.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(48, "Body Row", "This is a good overall exercise which strengthens your chest, core and back muscles.", "Lie under a bar so that the bar is at mid chest level and your feet are firmly on the floor.\n" +
                "Draw your abs in and keep your back flat.\n" +
                "Using your arms, lift and row your body towards the bar.\n" +
                "Pause for a moment and with slow controlled movements lower your self back to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(49, "Cuban Dumbbell Press", "This is a three point exercise for shoulder and middle back strengthening.", "Grasp dumbbells in each hand with a pronated grip (palms facing back wards, your shoulder rotated forward.\n" +
                "Stand upright with your feet shoulder width apart and your knees slightly bent, contract your abdominals.\n" +
                "Slowly lift your arms up so elbows are bent and parallel with the floor while you squeeze your shoulders together.\n" +
                "then rotate your arms, bringing the dumbbells forward so that they are now above your shoulder and in line with your elbows.\n" +
                "In a controlled manner lower your arms and return to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(50, "Bent Arm Pullover", "This is an exercise for chest, triceps, shoulders and lats strengthening.", "Lie flat on a bench with your head hanging slightly over the end and your feet flat on the floor.\n" +
                "Hold a barbell with a close grip (approximately 14”), keep your elbows in throughout the exercise.\n" +
                "Starting with your arms fully extended over your chest, slowly lower the bar in an arc over your head and towards the floor.\n" +
                "Pull the bar back up to chest height in a slow controlled manner and return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(51, "Barbell Good Mornings", "This is one of the oldest exercises in bodybuilding but it still is one of the best for working the lower back.", "Place a barbell across your shoulders.\n" +
                "Keeping your head up and your back completely straight, bend at the waist until your back is parallel with the floor.\n" +
                "Return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(52, "Smith Machine Good Mornings", "This exercise utilises the same technique of the standard Good Mornings yet the use of the Smith Machine provides more stability.", "Place a barbell across your shoulders.\n" +
                "Keeping your head up and your back completely straight, bend at the waist until your back is parallel with the floor.\n" +
                "Return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(53, "Barbell Dead Lifts", "The techniques is identical to that of the Smith Machine dead lift except for the fact that this exercise provides less support.", "Stand with your feet shoulder width apart so that your feet are under the bar.\n" +
                "Keeping your back straight bend at the waist, allow some bend in your knees.\n" +
                "Grasp the par with an overhand grip approximately 16 inches apart.\n" +
                "Straighten your back as you hold the bar at arm’s length.\n" +
                "Bend over again lowering the bar to just above the floor.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(54, "Seated Cable Rows", "This is an exercise for middle back, biceps and lower back strengthening.", "Sit at a low pulley machine with your feet resting against the footrests and your knees slightly bent.\n" +
                "With your abs drawn in and your back straight lean forward slightly to grasp the pulleys with an overhand grip (palms face downwards).\n" +
                "Slowly bring the pulleys back towards your abs while sitting upright, keep your elbows in close to your chest.\n" +
                "Pause for a moment then return slowly return the pulleys to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(55, "Lateral Dumbbell Raises", "This is an exercise for lateral deltoid, shoulder and forearm strengthening.", "Grasp a dumbbell in each hand, palms facing inward towards your body and the dumbbells at your sides.\n" +
                "Standing with your feet shoulder with apart, draw your abs in and bend your knees slightly.\n" +
                "While keeping your torso still, raise the your arms up to the sides keeping your arms straight with a slight bend in the elbows.\n" +
                "Raise your arms up until in line with your shoulders, your palms should face the floor.\n" +
                "Hold the position for a moment then in a controlled movement lower your arms to the starting position.\n" +
                "Repeat", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(56, "Dumbbell Incline Bench Press", "This is an advanced exercise for building and sculpting the chest as well as your triceps and shoulders.", "Lie on an incline bench which has been set to an incline of 45 degrees.\n" +
                "Start with the dumbbells at shoulder height, your arms wide and elbows pointing down to the floor.\n" +
                "Grasp the dumbbells with a grip so your palms face each other.\n" +
                "Raise your arms up over your chest bringing the dumbbells closer together as they meet over your chest, as if you were clapping.\n" +
                "Slowly return the dumbbells to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(57, "Incline Chest Press", "This exercise is for strengthening the upper chest and is preferred among beginners as it provides support and control.", "Adjust the seat of the machine so that the handles are near the upper portion of your chest.\n" +
                "Slowly press the handles forward until your hands are fully extended, do not lock your elbows.\n" +
                "Pause for a moment and then with a controlled movement lower your hands back to starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 5 #####
        exercises.add(new ExerciseDTO(58, "Smith Machine Squats", "This exercise uses the Smith Machine to work the Quadriceps, Hamstrings, Calves and Glutes. A great overall exercise for the lower body. There are many variations on the squat, this is the original version using a Smith Machine for better flow of movement.", "Set the height of the barbell to shoulder height.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift over your toes (this will cause you to loose balance).\n" +
                "Pause in the downward squatting position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(59, "Smith Machine Bench Press", "With the use of a Smith Machine this exercise strengthens your chest shoulders and triceps.", "Place a flat bench in the middle of the Smith Machine, with the bar in line with the middle of your chest.\n" +
                "Lying on the bench, grasp the bar at shoulder width apart.\n" +
                "Unlatch the bar and slowly lower the bar to your chest.\n" +
                "Extend your arms fully and raise the bar to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(304, "Push Ups with feet on exercise ball", "This is similar to the standard Push Up except using an Exercise Ball forces you to engage the muscle of your core (Rectus Abdominis, Transverse Abdominis, and the Obliques).", "Place your feet and shins flat on an Exercise Ball.\n" +
                "Place your arms in front of you at shoulder width apart, place your hands under your arms and press up from the ground until your arms are fully extended.\n" +
                "Pause at the top for a moment and steady your balance.\n" +
                "Slowly return to the starting position with your chest lowered towards the ground.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(60, "V Bar Pull Down", "This is an exercise for lats, biceps and middle back strengthening.", "Attach a V bar to the pull down pulley of a cable machine.\n" +
                "Sitting upright with your abs drawn grasp the bar with an overhand grip, your palms facing in.\n" +
                "Pull the bar straight down to your upper chest.\n" +
                "Pause for a moment after touching the chest and then slowly return the bar to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(61, "Dumbbell Raise", "This is an exercise for shoulder and biceps strengthening.", "Stand upright with your feet shoulder width apart and a slight bend in your knees.\n" +
                "Grasp a dumbbell in each hand, in front of your thighs. This is your starting position.\n" +
                "Draw your abs in.\n" +
                "Raise the dumbbells up on either side of your ribs in a rowing motion by bending your elbows moving them outwards.\n" +
                "Lower to starting position and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(62, "Smith Machine Incline Bench Press", "This is an exercise for chest, shoulder and triceps strengthening.", "Place an incline bench at a 45 degree angle in the middle of the Smith Machine.\n" +
                "Align the bench so the bar is across the upper portion of your chest.\n" +
                "Grasp the bar with a shoulder width grip.\n" +
                "Unlock the bar and slowly lower the weight to your chest, do not bounce the bar on your chest.\n" +
                "With slow controlled movements, raise the bar back to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(63, "Push Ups Close and Wide Hand Versions", "These are variations on Push Ups, one of the best exercise for muscles of the chest (pectorals), arms (bicep and triceps) and core development. The core muscles are the rectus abdomen and oblique’s which support the spine.", "For a Close Grip Push Up lay face down on the floor, or a mat, with your feet together curled slightly so you rise on the ball of your feet.\n" +
                "Place you hands close together so your thumbs and index fingers form a triangle on the floor.\n" +
                "Draw your abs in and inhale as you raise your body up till your arms are straight.\n" +
                "Keep your head and neck level with your body (don’t look up or down) and don’t allow your back to rise or fall.\n" +
                "Exhale out as you lower your body back to the ground.\n" +
                "For a Wide Grip Push Up move your hands out to a position slightly wider than your shoulders.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(64, "Seated Rear Lateral Cable Raise", "Tis is an exercise for shoulder strengthening.", "Sit on the edge of a bench with your feet on the floor in front of you.\n" +
                "Rest your chest on your thighs with your back straight, grasp the cable pulleys with opposite hands.\n" +
                "Raise your upper arms to shoulder height. Pause at the top for a moment.\n" +
                "Lower your arms to the starting position in a controlled motion.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(65, "Front Dumbbell Raise", "This is an exercise for shoulder strengthening.", "Stand with a dumbbell in each hand with an overhand grip, your feet shoulder width apart and your abs drawn in.\n" +
                "Keeping each arm straight raise your left arm to just above shoulder height.\n" +
                "Pause for a moment then in a controlled motion lower the weight to starting position and repeat with your right arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(66, "Push Ups with Feet Elevated", "This is the same general movement as with a Push Up except your feet are elevated allowing greater range of motions and targeting the pectorals differently than the regular Push Up.", "Place your feet on a bench at least 18” off the ground.\n" +
                "Make sure the tops of your feet are flush against the bench.\n" +
                "Place your hands on the floor slightly wider than shoulder width apart.\n" +
                "With your hands directly under your shoulders, press up from the floor, keeping your back and neck in a straight line so you are looking forward during the entire exercise.\n" +
                "Once your arms are fully extended pause and then with slow controlled movements lower yourself to the floor again.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(67, "Smith Machine Dead Lifts", "This is the same as a Dead Lift but the Smith Machine allows for better control of the weight.", "Stand with your feet shoulder width apart so that your feet are under the bar.\n" +
                "Keeping your back straight bend at the waist, allow some bend in your knees.\n" +
                "Grasp the par with an overhand grip approximately 16 inches apart.\n" +
                "Straighten your back as you hold the bar at arm’s length.\n" +
                "Bend over again lowering the bar to just above the floor.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(68, "Wide Grip Lat Pull Down", "This is an exercise for lats, biceps and middle back strengthening.", "Sit at a cable pull down machine fitted with a wide bar.\n" +
                "Grasp the bar with a wide overhand grip (palms facing forward) .\n" +
                "With your abs drawn in and back straight pull the bar down to your upper chest.\n" +
                "Pause for a moment and then return the bar to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));

        //##### page 6 #####
        exercises.add(new ExerciseDTO(69, "Barbell Neck Press", "This is an advanced exercise for chest strengthening.", "Lie on a flat bench with you feet planted firmly on the floor.\n" +
                "Grasp the bar a little wider than shoulder width apart.\n" +
                "Raise the barbell above your body and move it to the top of your chest, near your neck.\n" +
                "This is your starting position.\n" +
                "Lower the bar down so it just touches the top of your chest.\n" +
                "Raise the bar till your arms are fully extended and your elbows are locked.\n" +
                "Return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(70, "Body Leg Lifts", "This is an exercise for gluts and hamstring strengthening.", "Using a post or tall weight bench for balance stand straight with your abs drawn in.\n" +
                "Raise one off the ground and behind you while standing on the other leg.\n" +
                "Slowly lower the leg and raise it again while flexing the gluts.\n" +
                "Repeat with your other leg.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(71, "Incline Dumbbell Fly’s", "This is an exercise for chest sculpting and strengthening.", "Lie on an incline bench set a 45 degree angle with a dumbbell in each hand and your feet firmly on the ground.\n" +
                "Lift the dumbbells over your chest extending your arms fully and your palms facing each other.\n" +
                "Keeping a slight bend in your elbows, lower the dumbbells towards the floor in and arc like motion.\n" +
                "Slowly return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(72, "Decline Barbell Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a decline bench with your head lower than your feet.\n" +
                "Grasp the bar at a grip 3-6 inches wider than your shoulders.\n" +
                "Raise the bar above your chest, keeping your elbows close in.\n" +
                "Slowly and with control lower the bar straight to your lower chest.\n" +
                "Raise the bar back up to starting position with the bar just above your chest.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(73, "Back Extension on Stability Ball", "This exercise provides flexibility as it strengthens the muscles of the back and core.", "Lie prone (on your stomach) on a Stability Ball with your toes firmly planted on the floor for balance.\n" +
                "With your hands across your chest or at your ears, raise your chest off the ball so you are hyperextending your spine.\n" +
                "Slowly return your chest to the ball.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(74, "Upright Cable Row", "This is an exercise for shoulder, biceps and upper back strengthening.", "Attach a straight bar to the pulley on the floor.\n" +
                "Stand with your feet shoulder width apart, your abs drawn in and your back straight.\n" +
                "Grasp the bar with an overhand grasp (your palms facing downwards) and pull it up towards your waist, this is the starting position.\n" +
                "Raise the bar up to in line with your shoulders.\n" +
                "Pause at the top and rotate your shoulder blades together.\n" +
                "Lower the bar in a controlled motion to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(75, "Straight Arm Dumbbell Pullover", "This is an exercise for chest and shoulder strengthening which mainly targets the rotator cuff muscles.", "Lie on a flat bench with your feet flat on the floor and your head at the end of the bench.\n" +
                "Grasp a dumbbell and raise it over your chest.\n" +
                "Keeping your elbows as straight as possible, lower the weight in an arc over your head and as low to the ground as possible with out any pain.\n" +
                "Return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(76, "Rear Deltoid Row Dumbbell", "This is an exercise for deltoid, biceps and trapezius strengthening.", "Place your right foot on the floor, rest your left knee and hand on a flat bench.\n" +
                "Lean forward so your body’s weight is supported by your left arm and knee.\n" +
                "Keeping your back flat, reach down and pick up the dumbbell with your right hand.\n" +
                "Raise your right arm as close to your chest as possible; while bending and bringing your elbow back as far as you can.\n" +
                "Pause at the top for a moment and then lower the dumbbell in a controlled manner to the starting position.\n" +
                "Switch arms by doing the opposite positioning.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(77, "Stationary Abdominal Draw In", "This is an exercise for abdominal and core strengthening which are vital in supporting the spine.", "Get down on a mat on your hands and knees, forming a four point rectangle or square shape.\n" +
                "Keep your back straight and your hips and pelvis in a neutral position.\n" +
                "Draw your abs in, crunching your abs while keeping your back still.\n" +
                "Hold for a moment and then release returning to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(78, "Hammer Grip Incline Bench Press", "This is an advanced exercise for strengthening the chest, triceps and shoulders.", "Grasp a dumbbell in each hand and lay on an incline bench set at a 45 degree angle.\n" +
                "Keep your feet flat on the floor and your back against the bench at all times.\n" +
                "Using a hammer grip (with your palms facing each other), lift the weights to shoulder height on either side of your chest.\n" +
                "Extend your arms fully and press the dumbbells up.\n" +
                "Slowly return the dumbbells to the starting position at the sides of your chest.", "", "", new RealmList<SetDTO>()));

        //##### page 7 #####
        exercises.add(new ExerciseDTO(305, "Smith Machine Rear Deltoid Row", "This is an exercise for rear deltoid,shoulder, biceps and back strengthening.", "Set the bar to the lowest setting.\n" +
                "Place your feet shoulder width apart with a slight bend in the knees.\n" +
                "Bend at the waist while keeping your back straight and chest parallel to the floor.\n" +
                "Grasp the bar slightly wider than your shoulders with an overhand grasp and your arms straight with a slight bend in the elbows.\n" +
                "Raise the bar with a controlled motion to the lower part of your chest by bending and elevating your elbows.\n" +
                "Hold for a moment and then lower to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(79, "Incline Cable Fly’s", "This exercise is an alternative to the Butterfly or Pec Deck, it defines the muscles of the chest.Place an incline bench set at a 45 degree angle between two cable towers.", "Place an incline bench set at a 45 degree angle between two cable towers.\n" +
                "Grasp a pulley in each hand with your palms facing up.\n" +
                "With a slight bend in your elbows, squeeze your chest and pull the cables in an arc so they meet together in the middle of your chest.\n" +
                "Hold for a moment and then slowly lower your hands back to starting position at along the same arc.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(80, "Smith Machine Upright Row", "This is an exercise for shoulder and biceps strengthening.", "Stand with your feet shoulder width apart and your abs drawn in.\n" +
                "Place the bar on the Smith Machine in the position where your arms are fully extended in front of you.\n" +
                "Place your hands shoulder width apart and raise the bar up towards your chin with a controlled motion.\n" +
                "Pause at the top for a moment and rotate your shoulder blades together.\n" +
                "Lower the bar to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(81, "Static Neck Flexion and Extension", "An exercise to either build or warm-up front and back neck muscles.", "Stand or sit with your head in neutral position.\n" +
                "Place both of your hands on your forehead.\n" +
                "Press your head against your hands and contract your neck muscles.\n" +
                "Keep your head from moving forward. Hold for at least 30 seconds.\n" +
                "Repeat with hands on the back of your head, pressing against them.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(82, "Decline Chest Press", "This is an exercise for the strengthening of the lower chest or pectoral muscles and is preferred by beginners as it provides support and stability.", "Adjust the seat of the machine so that the handles are near the lower portion of your chest.\n" +
                "Slowly press the handles forward until your hands are fully extended, do not lock your elbows.\n" +
                "Pause for a moment and then with a controlled movement lower your hands back to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(83, "One Arm Dumbbell Shoulder Press", "This is an exercise for shoulder strengthening.", "Stand with your feet shoulder width apart, back straight and abs drawn in.\n" +
                "Grasp a dumbbell in your hand and lift it with a bent elbow to your shoulder height to start.\n" +
                "Lift the dumbbell straight up till your arm is fully extended overhead.\n" +
                "Lower in a controlled manner and return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(84, "Reverse Grips Bent Over Barbell Rows", "This is an exercise for middle back and biceps strengthening.", "Bend over a barbell at slight angle with your knees bent and your back straight.\n" +
                "Grasp the bar with a reverse grip (palms facing up) and slowly pull the barbell to your abs, keeping your elbows close to your sides.\n" +
                "Pause for a moment at the top.\n" +
                "Return the barbell to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(85, "Decline Dumbbell Bench Press", "his is an exercise for chest, shoulder and triceps strengthening.", "Grasp the dumbbells your hands and lie on a decline bench with your head lower than your feet.\n" +
                "Raise the dumbbells above your chest, bringing your elbows close in, this is your starting position.\n" +
                "Slowly and with control lower the dumbbells to either side of your lower chest.\n" +
                "Raise the dumbbells back up to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(86, "Static Neck Side Flexion", "An exercise either build or warm up neck side flexors", "Stand or sit with your head in neutral position.\n" +
                "Place your left hand against the left side of your head, above your ear.\n" +
                "Push your head against your hand, without moving your head.\n" +
                "Repeat with your right hand, right side of your head.", "", "", new RealmList<SetDTO>()));

        //##### page 8 #####
        exercises.add(new ExerciseDTO(87, "Gironda Sternum Chins", "This is an exercise for lats, biceps and middle back strengthening; which is similar t the basic chin up but focusses more on lats.", "Grasp the pull up bar with a shoulder width underhand grip.\n" +
                "Hang from the bar with your arms fully extended.\n" +
                "Pulling yourself towards the bar lean your head back as far as you can and arch your spine.\n" +
                "Continuing pulling so that your collarbone passes the bar and you are able to touch your sternum to the bar.\n" +
                "Slowly lower yourself back to starting position.\n" +
                "Repeat this motion.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(88, "Hack Squat with barbell", "This is another variation of the basic Squat that focuses primarily on the quadriceps or thigh muscles.", "Place the barbell on the floor just behind your legs and stand with your feet shoulder width apart with your toes pointing forward.\n" +
                "With your feet firmly placed on the floor, reach down and grasp the barbell from behind with an overhand grip.\n" +
                "Lift the barbell by extending your hips and knees, taking care not to lock your knees.\n" +
                "Lower yourself (squat) down until your thighs are parallel to the floor.\n" +
                "Slowly raise yourself up to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(89, "Dumbbell Upright Rows", "This is an exercise for shoulder, biceps and upper back strengthening.", "Grasp a dumbbell in each hand with an overhand grip (palms facing downwards) and hold them in front of your thighs.\n" +
                "Stand with your feet shoulder width apart, your abs drawn in and your back straight.\n" +
                "Starting with the dumbbells at your thighs, slowly raise them up towards your shoulders by bending your elbows.\n" +
                "Pause at the top and rotate your shoulder blades together.\n" +
                "Lower the dumbbells in a controlled motion to your thighs.\n" +
                "Repeat", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(90, "Dumbbell Bent Arm Pullover", "This is an exercise for chest, shoulder, lats and biceps strengthening.", "Lie flat on a bench with your head hanging slightly over the end and your feet flat on the floor.\n" +
                "Hold a dumbbell with both hands, keep your elbows in throughout the exercise.\n" +
                "Starting with your arms fully extended over your chest, slowly lower the dumbbell in an arc over your head and towards the floor.\n" +
                "Pull the dumbbell back up to chest height in a slow controlled manner and return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(91, "Incline Bench Press with Bands", "This is an exercise for chest and biceps strengthening.", "Securely fasten the band under the leg of a flat bench, near your head.\n" +
                "Lie flat on the bench with your feet flat on the floor in order to stabilise your movements.\n" +
                "Grasp one end of the band in each hand and starting with your hands at chest level.\n" +
                "Press upwards so your arms are fully extended.\n" +
                "With a slow controlled motion return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(92, "One Arm Barbell Floor Press", "This is an exercise for chest and triceps strengthening.", "Lie flat on your back with your knees bent and your feet firmly on the floor.\n" +
                "Draw in your abs and contract your lats, and glutes.\n" +
                "Have your partner hand you the bar and with your bicep parallel to the floor lift and extend your arm fully so it is straight.\n" +
                "Return to the starting position.\n" +
                "Complete reps and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(93, "Rear Deltoid Row Barbell", "This is an exercise for shoulder and biceps strengthening.", "Place a barbell on the floor in front of you with the resided weights attached.\n" +
                "With your knees bent and back straight grasp the bar with a wide overhand grip (palms facing downwards).\n" +
                "Keep your chest steady and your arms perpendicular to your chest.\n" +
                "Pull the bar as far up to your chest as you can.\n" +
                "Hold for a moment than lower the bar in a controlled manner.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(94, "Decline Dumbbell Fly’s", "This is an exercise for chest and shoulder strengthening.", "Lie on a decline bench with your head lower than your body and your feet firmly on the ground.\n" +
                "With dumbbells together above your shoulders and your arms straight out above you as your starting position.\n" +
                "Keeping your arms straight and with a slight bend in your elbows lower the dumbbells down towards the floor in an arc, up until just above your shoulders.\n" +
                "Return to the starting position in a slow controlled manner.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(95,"Butterfly Machine", "This is an exercise for chest strengthening.", "Adjust the machine so you are sitting at chest heights with the pads.\n" +
                "Sit on the machine with your back flat.\n" +
                "Place your forearms on the pads, parallel to the floor, this is starting position.\n" +
                "Using your forearms, push the pads together slowly as you squeeze your chest.\n" +
                "Return your arms to the stretched out starting position in a controlled motion.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(96, "Crossover with Bands", "This is an exercise for chest, biceps and shoulder strengthening.", "Secure an exercise to a post (or secure pole) and hold it in each hand.\n" +
                "Step forward so the band has some tension, facing away from the post.\n" +
                " Your arms at mid chest height and out to the side, palms facing forward this is your starting position.\n" +
                "Bring your hands together in front of you.\n" +
                "Hold this for a moment and then in a controlled motion return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(97, "Barbell Front Raise and Pullover", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie on a flat bench and grasp a barbell using a medium grip (about 15” apart).\n" +
                "Place the barbell on your upper thighs and lock your arms straight with a slight bend in your elbows.\n" +
                "Draw your abs in and keeping your back flat on the bench, raise your arms up in an arc over and behind your head (as if you were performing a reverse pullover).\n" +
                "Slowly return the barbell to the starting position on your thighs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(98, "Pullover On Stability Ball With Weight", "This is an exercise for back, shoulders and neck strengthening.", "Grasp a Barbell or Dumbbell and sit on a Ball with your feet firmly placed on the floor.\n" +
                "Lower your body so your upper abs are the only part of your body supported on the Ball.\n" +
                "Bring the Barbell or Dumbbell to your chest with your arms extend.\n" +
                "In an arcing motion raise the Barbell or Dumbbell over and behind your head towards the floor.\n" +
                "Return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));

        //##### page 9 #####
        exercises.add(new ExerciseDTO(99, "Incline Fly’s with a Twist", "This is an exercise for chest sculpting and strengthening which is very similar to the incline fly’s.", "Lie on an incline bench set at a 45 degree angle with a dumbbell in each hand and your feet flat on the ground.\n" +
                "Start with the dumbbells out to the sides of your chest, parallel to the floor as you would a regular Fly.\n" +
                "Raise the dumbbells over your chest in and arc, at the top turn your pinky finger in so they are facing each other.\n" +
                "Slowly return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(100, "Wide Grip Decline Barbell Pullover", "This is an advanced strengthening exercise which targets the pectoral or chest muscles.", "Lie on a decline bench with your feet higher than your head and your feet firmly on the ground.\n" +
                "Grasp the barbell with an extra wide grip (your hands near the plates) and starting at you’re your upper thighs raise the barbell in an arc over your head towards the floor.\n" +
                "Slowly return the barbell to starting position on your thighs.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(101, "Bosu Ball Push Up", "This is an exercise for chest, core, shoulders and triceps strengthening with the use of a Bosu ball.", "Place the Bosu ball flat side down on the floor.\n" +
                "Kneel on the floor with your arms fully extended on the ball, and body straight.\n" +
                "Keeping your abs drawn in and your body straight, by bending your elbows lower your chest to the ball.\n" +
                "Pause for a moment and then return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(102, "One Armed Biased Push Up", "This is an exercise for chest strengthening but also strengthens ones core, shoulders, biceps and triceps.", "Arrange the ball so that as you begin the exercise, one hand on the Bosu ball and the other on the floor.\n" +
                "Place the ball flat side down on the floor.\n" +
                "Kneel on the floor with your arms fully extended on the dome, and body straight.\n" +
                "Keeping your abs drawn in and your body straight, lower your chest to the ball.\n" +
                "Pause for a moment and then return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(103, "Wide Grip Bench Press", "This is an exercise for chest, triceps and shoulder strengthening.", "Lie flat on a bench with your feet flat on the floor.\n" +
                "Place your hands wider than shoulder width apart on the bar and lift it off the rack, this is starting position.\n" +
                "Bring the bar over your chest and lower it till it is just above your body.\n" +
                "Extend your arms upward and raise the bar straight up.\n" +
                "Pause for a moment and with slow controlled movement, return the bar to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(104, "Underhand Pull downs", "This is an exercise for lats and biceps strengthening.", "Sitting upright with your abs drawn in grasp a Pull Down bar with and underhand grip shoulder width apart.\n" +
                "Pull the bar down till the bar reaches your upper chest, keeping your elbows close to your body.\n" +
                "Slowly return the bar to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(105, "Underhand Pull down", "This is an exercise for lats and biceps strengthening.", "Sitting upright with your abs drawn in grasp a Pull Down bar with and underhand grip shoulder width apart.\n" +
                "Pull the bar down till the bar reaches your upper chest, keeping your elbows close to your body.\n" +
                "Slowly return the bar to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));

        exercises.add(new ExerciseDTO(106, "One Legged Cable Kickback", "This is an exercise for gluts and hamstring strengthening.", "Standing at a cable tower attach an ankle cuff to a low pulley.\n" +
                "Facing the weight stack, with a slight bend in your knees and your abs drawn in\n" +
                "Slowly kick your ankle back in and arc as high as you are able to.\n" +
                "Return to starting position and switch ankles.", "", "", new RealmList<SetDTO>()));

        //##### page 10 #####

        exercises.add(new ExerciseDTO(107, "Back Fly’s with Exercise Band", "This is an exercise for shoulder, middle back and triceps strengthening.", "Place an exercise band around a post or machine at mid chest height.\n" +
                "Stand with your feet shoulder width apart, abs drawn in and knees slightly bent.\n" +
                "Stand back from the post so there is tension in the band, grasping the band with your arms extended in front of you and a slight bend in your elbows; this is your starting position.\n" +
                "Bring your arms back in a controlled motion till they are parallel at your sides and in line with your shoulders.\n" +
                "Slowly return your arms to the starting position in front of your chest.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(108, "Bent Over Rear Deltoid Raise With Head On Bench", "This is an exercise for shoulder strengthening.", "Rest your forehead on an incline bench in oder to stabilise your movements so you are bent over with your back parallel to the floor, draw your abs in.\n" +
                "Grasp dumbbells in your hands with your arms straight and elbows slightly bent ensuring they are not locked.\n" +
                "Slowly raise the dumbbells up to shoulder height.\n" +
                "Pause for a moment and then return to the starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(109, "Internal Cable Rotation", "This is an exercise for shoulder and biceps strengthening.", "Sit on the floor with your body close to low cable pulley.\n" +
                "Grasp the pulley with your left arm and position your elbow bent at 90 degree angle.\n" +
                "Pull the cable towards your body and internally rotate your shoulder until your forearm is across your abs.\n" +
                "Return to starting position.\n" +
                "Repeat with right arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(110, "One Arm Flat Bench Fly’s", "This is an exercise for chest strengthening.", "Lie on a flat bench with your free hand holding the bench.\n" +
                "Grasp the dumbbell in your other hand and with your forearm parallel to the floor raise your arm in an arc the middle of your chest.\n" +
                "Once the dumbbell is at the middle of your chest line, slowly lower is along the same path to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(111, "Upright Band Rows", "This is an exercise for lats, triceps and shoulder strengthening.", "Stand on the band so that the handles are level with your upper thigh.\n" +
                "With your back straight and abs drawn in raise your hands upward to your collarbone.\n" +
                "Your elbows and arms should be parallel with the floor.\n" +
                "Slowly lower your arms and return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(112, "Barbell Lunges", "This is a classic exercise used for strengthening the hamstrings, quadriceps, calves and gluts.", "Start by placing a barbell across your upper back, using a grip slightly wider than your shoulders.\n" +
                "Stand with your feet about 8 inches apart, toes facing forward.\n" +
                "Take a step forward (2-3 feet) keeping your abs drawn in and your upper body straight.\n" +
                "Slowly lower one knee down as if kneeling while keeping your other knee bent at a 90 degree angle, do not let your knee touch the ground.\n" +
                "Lower your body so that your rear knee is just above the floor and hold for a moment before returning to the starting position.\n" +
                "Repeat on other side.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(113, "Dumbbell Lunges", "This is an exercise for whole leg but mainly hamstring strengthening.", "Grasp a dumbbell in each hand.\n" +
                "Stand with your feet about 8 inches apart, toes facing forward.\n" +
                "Take a step forward (2-3 feet) keeping your abs drawn in and your upper body straight.\n" +
                "Slowly lower one knee down as if kneeling while keeping your other knee bent at a 90 degree angle, do not let your knee touch the ground.\n" +
                "Lower your body so that the rear knee is just above the floor and hold for a moment before returning to the starting position.\n" +
                "Repeat on opposite side.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(114, "Flutter Kicks", "This is an exercise for quadriceps and hamstring strengthening.", "Lie face down on a bench with your hips on the edge, hold onto the bench with your hands.\n" +
                "Allow your legs and toes hang down.\n" +
                "Draw your abs in, squeeze your gluts and hamstrings.\n" +
                "Kick each back and up while keeping your hips on the bench.\n" +
                "Alternate kicking each leg.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(115, "Lying Leg Curl Machine", "This is an exercise for hamstring strengthening.", "Lie face down on a leg curl machine and place your heels under the roller pad.\n" +
                "Grasp the grips with your hands for support and slowly curl your ankles up towards your back.\n" +
                "Hold for a moment and then return to starting position.\n" +
                "Repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(116, "Romanian Dead Lift", "This is an exercise similar to the regular dead lift and strengthens the hamstring, calves, quadriceps and gluts.", "Place a barbell in front of your feet on the ground.\n" +
                "Grasp the barbell with a grip a bit wider than shoulder width apart.\n" +
                "Bend your knees slightly, keeping your hips and back straight.\n" +
                "Lift the bar straight up concentrating on using your hips as you stand.\n" +
                "Stand with the bar resting against your thighs.\n" +
                "Lower the bar to the floor with a slight bend in your knees flexing your hips back for stability.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(117, "Seated Leg Curl", "This is an exercise for hamstring strengthening which is similar to the laying leg curls.", "Adjust the machine so that the footpad is just above your heels.\n" +
                "Sit upright with your abs drawn in and your legs in front of you.\n" +
                "Slowly curl your legs back towards you and hold for a moment.\n" +
                "With controlled movement return to the starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 11 #####
        exercises.add(new ExerciseDTO(118, "Walking Lunges", "A walking lunge gives you the benefit of lunges plus core muscle training from the constant motion.", "Start by either placing a barbell across your upper back or by grasping a dumbbell in each hand.\n" +
                "Stand with your feet about 8 inches apart, toes facing forward.\n" +
                "Take a lunge forward keeping your abs drawn in and your upper body straight.\n" +
                "Slowly lower one knee down as if kneeling while keeping your other knee bent at a 90 degree angle, do not let your knee touch the ground.\n" +
                "Lower your body to just above the floor and hold for a moment before pushing off with the back foot.\n" +
                "Step through and repeat the exercise with the other leg.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(119, "Smith Machine Hack Squat", "This is another variation of the basic Squat that focuses primarily on the quadriceps, or thigh muscles, while using the Smith Machine.", "Adjust the bar to a low setting just above your ankles.\n" +
                "Stand with the bar on the floor just behind your legs, with your feet shoulder width apart and your toes pointing forward.\n" +
                "With your feet firmly placed on the floor reach down and grasp the bar from behind, with an overhand grip.\n" +
                "Lift the bar by extending your hips and knees, taking care not to lock your knees.\n" +
                "Lower yourself (squat) down until your thighs are parallel to the floor.\n" +
                "Slowly raise yourself up to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(120, "Leg Press", "Along with the Squat, this exercise is crucial for building and strengthening the legs.", "Adjust the seat of the machine so that your feet comfortably reach the crosspiece with a slight bend in your knees.\n" +
                "To begin, press your feet forward at a shoulder width stance and release the safety locks.\n" +
                "Slowly lower the weight towards your body, keeping your abs drawn in and your knees moving in the same direction as your feet to a 90 degree angle.\n" +
                "Do not lock your knees or bounce the weight.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(121, "Rear Lunges with Barbell", "This is an advanced version of a lunge.", "Place a barbell on a rack at chest height.\n" +
                "Lift the barbell of of the rack and onto your shoulders, gripping the bar slightly wider than shoulder width apart.\n" +
                "Stand with your feet approximately 8 inches apart with your toes pointing forward.\n" +
                "Slowly take a step backward with your right leg.\n" +
                "Keeping your abs drawn in and your upper body straight, lower your body until your left knee is almost on the ground. You may choose to place a mat or towel under your knee.\n" +
                "Hold for a moment and then return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(122, "Rear Lunges with Dumbbell", "This is an advanced version of a lunge.", "Grasp a dumbbell in each hand.\n" +
                "Stand with your feet approximately 8 inches apart with your toes pointing forward.\n" +
                "Slowly take a step backward with your right leg.\n" +
                "Keeping your abs drawn in and your upper body straight, lower your body until your left knee is almost on the ground. You may choose to place a mat or towel under your knee.\n" +
                "Hold for a moment and then return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(123, "Squats using Dumbbells", "This is a great overall exercise for the lower body that uses dumbbells instead of a barbell. Beginners may find this version easier to perform.", "Grasp a dumbbell in each hand.\n" +
                "Stand with feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(124, "Side Squats with Barbell", "This version of a squat works the hip adductors, helping build better core strength and coordination.", "Place a barbell on your shoulders and grasp it with a wide grip.\n" +
                "Stand with your feet wide apart, with the foot of the leg you will be leaning towards angled out.\n" +
                "Bring your lower body to your foot by bending the hip and knee of your lead leg, and keeping your other leg fairly straight.\n" +
                "Return to the starting position and switch legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(125, "Single Leg Squat with Barbell", "This is an advanced exercise and modification of the basic squat. Mastery of the squat is recommended before attempting this exercise.", "Place a barbell on your back, above your shoulders with a wide grip.\n" +
                "Cross the lower leg above the knee of your supporting leg (the leg you will squat with).\n" +
                "Squat down as low as you are able.\n" +
                "Slowly return to an upright position by straightening your hip and knee.\n" +
                "Switch legs and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(126, "Squat to Bench with Barbell", "This is s great overall exercise for the lower body. There are many variations on the squat, this one uses a bench to remind you how far down to squat.", "Place a bench behind you.\n" +
                "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in, descend slowly and squat down, bending at the knees and hips.\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance). Do not sit on the bench.\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(127, "Step Ups with Barbell", "This exercise builds coordination as well as muscle.", "Place a bench, box or step in front of you.\n" +
                "Grasp a barbell with a wide grip and place it across your shoulders.\n" +
                "With your left leg, step up on the bench, and follow by stepping up with your right leg.\n" +
                "Step down with your left leg and then your right leg.\n" +
                "Repeat starting with your right leg.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(128, "Hip Adduction", "This exercises strengthens hip flexors and muscles of the thighs.", "Stand alongside a cable pulley stack and attach a cuff to the pulley and your ankle.\n" +
                "Step away from the stack, holding onto the stack for support.\n" +
                "Standing firm on the foot not attached to the cable and slowly abduct, or pull, your cuffed ankle in front of your stabilizing leg.\n" +
                "Repeat and switch ankles.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(129, "Squat to Bench with Dumbbells", "This is a great overall exercise for the lower body. There are many variations on the squat, this one uses a bench to remind you how far down to squat.", "Place a bench behind you.\n" +
                "Grasp a dumbbell in each hand.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in descend slowly by squatting down and bending at the knees and hips.\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 12 #####
        exercises.add(new ExerciseDTO(130, "Step Ups with Dumbbells", "This exercise builds coordination as well as muscle.", "Place a bench, box or step in front of you.\n" +
                "Grasp a dumbbell in each hand.\n" +
                "With your left leg, step up on the bench, and follow by stepping up with your right leg.\n" +
                "Step down with your left leg and then your right leg.\n" +
                "Repeat starting with your right leg.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(131, "Front Squat with Barbell", "The Front Squat works the same muscles as the rear squat without placing the weight of the bar on your shoulders. This exercise can be a good substitute for people with back and neck injuries.", "Place the barbell on your upper chest, resting it across your front deltoids and holding it with your arms crossed securely.\n" +
                "Keep your head and back straight, abs drawn in and toes pointing slightly outward.\n" +
                "Slowly “squat” down so your upper thighs are parallel to the floor.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(132, "Front Squat to Bench with Barbells", "The Front Squat works the same muscles as the rear squat without placing the weight of the bar on your shoulders. This exercise can be a good substitute for people with back and neck injuries. By using a bench you remind yourself how far to squat down", "Place a bench behind you.\n" +
                "Place the barbell on your upper chest, resting it across your front deltoids and holding it with your arms crossed securely.\n" +
                "Keep your head and back straight, abs drawn in and your toes pointing slightly outward.\n" +
                "Slowly “squat” down so your upper thighs are parallel to the floor.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(133, "Iron Cross with Dumbbells", "This is an advanced exercise that works the whole body.", "With a dumbbell in each hand, stand in a low squat stance, your feet shoulder width arms held out in front of you with your palms facing in.\n" +
                "Standing upright, move your arms out and away from your body forming a “T” or “cross”.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(134, "Jefferson Squats with Barbell", "This is a rarely used exercise which is without question one of the best exercises for building and shaping the inner thighs.", "Place a barbell between your legs so it is perpendicular to your feet.\n" +
                "Grasp the barbell with one hand in front of you and one hand behind you with an overhand grip.\n" +
                "Squatting down so your thighs are parallel to the floor slowly lift the weight up between your legs as you stand.\n" +
                "Slowly return to a starting position just above the floor.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(135, "Leg Extensions", "This exercise is a staple for building strong legs.", "Adjust the seat so that your knees have full range of motion and that the footpad fits over your legs, just above your ankles.\n" +
                "Grasp the handles with your hands for support, keeping your hip and back against the bench.\n" +
                "Slowly extend your legs until your knees are straight, but do not lock your knees.\n" +
                "Hold for a moment and then with controlled movements return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(136, "Narrow Stance Leg Press", "This is a variation of the leg press which targets more of the external muscles of the legs by moving your feet closer together.", "Sit on a leg press machine with your feet on the crosspiece 6 inches apart, with your toes pointed slightly outward.\n" +
                "Grasp the handles on the side of the machine and release the locks.\n" +
                "Keeping your abs drawn in, bend your knees and bring the weight as far as possible towards your chest.\n" +
                "Hold this position for a moment and then slowly return the weight to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(137, "Lying Squat", "This exercise is similar to a Leg Press Machine working the same muscles.", "Adjust the shoulder rack of the machine so that your feet comfortably reach the crosspiece with a slight bend in your knees.\n" +
                "To begin, press your feet forward and release the safety locks.\n" +
                "Slowly lower the weight towards your body, keeping your abs drawn in and your knees moving in the same direction as your feet to a 90 degree angle.\n" +
                "Do not lock your knees or bounce the weight.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(138, "Narrow Stance Hack Squats", "Like the Hack Squat, this exercise works the muscles of the leg. The narrow stance targets the external muscles more efficiently.", "Lie face up on a Hack Squat machine with your shoulders against the pad.\n" +
                "Place your feet facing forward at a distance of 6 inches apart with your toes point slightly outward.\n" +
                "Release the dock levers and place your hands on the handgrips.\n" +
                "Drawing your abs in, extend your body standing upright.\n" +
                "Lower your body to a squatting position so your knees are bent as if you were sitting down.\n" +
                "Return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(139, "Narrow Stance Squat with Barbell", "A great overall exercise for the lower body. This version of the Squat targets and defines more of the externals leg muscles.", "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place your feet about 6 inches apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in, descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(140, "One Leg Squat with Barbell", "The One Leg Squat isolates the muscles of the leg for optimal exercise. This exercise is also used to build or rebuild strength after injury.", "Place a bench or box 12-18 inches tall behind you. The taller the box the greater the difficulty of the exercise.\n" +
                "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Place one foot up on the bench, and your other foot firmly on the floor 2-3 feet in front you.\n" +
                "Drawing your abs in descend slowly by bending your front knee and hip as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toe (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.\n" +
                "Switch legs and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(141, "One Arm Snatch with Barbell", "This is an advanced exercise that works most of the muscles of the body. Exercises like this are used to develop functional strength for sports that require strong hands.", "Stand with your feet shoulder width apart, and a slight bend in your knees.\n" +
                "Grasp the bar with an overhead grip.\n" +
                "Starting with the barbell held slightly above the knees (hang position), lift the weight up over your head (step 1) then back to shoulder height (step 2) and finally back to starting position between near your knees.", "", "", new RealmList<SetDTO>()));

        //##### page 13 #####
        exercises.add(new ExerciseDTO(142, "One Arm Side Deadlift with Barbell", "This is an advanced exercise that works most of the muscles of the body. Exercises like this are used to develop functional strength for sports that require strong hands.", "Stand besides a barbell placed on the floor.\n" +
                "Grasp the bar with an overhand grip in the center.\n" +
                "Crouching down as if your were performing a squat, bend down so your thighs are parallel with the floor.\n" +
                "Extend your legs slowly as you straighten your body up, drawing in your abs as you rise.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(143, "Overhead Squat with Barbell", "In this version of the squat you hold the bar overhead as you perform the exercise.", "Stand with your feet a bit wider than shoulder width apart with your toes pointed slightly outward.\n" +
                "Grasp a barbell using a wide side snatch grip with your arms and elbows fully extended.\n" +
                "Keeping the bar overhead, bend your knees and lower your body until your thighs are parallel with the floor.\n" +
                "Return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(144, "Pile Squat with Dumbbell", "This version of the squat is done with a dumbbell and mimics the action of a pile driver.", "Stand with your feet 2-4 inches wider than your shoulders, with your toes pointed out at a 45 degree angle.\n" +
                "Grasp a dumbbell with both hands in the center of your body.\n" +
                "Keep your knees slightly bent and your back straight.\n" +
                "Squat down as if you were going to sit in a chair, bringing your thighs parallel to the floor.\n" +
                "With a controlled motion return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(145, "Speed Squats with Barbell", "This version of the squat uses an empty barbell and moves at a faster pace than the standard squat. This is a good exercise for building speed and conditioning used in running and jumping sports.", "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in, descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(146, "Squats with Exercise Bands", "This version of the Squat uses flexible exercise bands for resistance instead of weights.", "Stand with your feet on the exercise bands with your feet shoulder width apart.\n" +
                "Grasp the handles of the bands and pull them up to your shoulders.\n" +
                "With your abs drawn in, squat down until your thighs are parallel with the floor.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(147, "Thigh Abductor", "This exercise targets and works the muscles of the outer thigh", "Sit on the machine and place your knees against the pads.\n" +
                "Draw your abs in and “abduct” or move your thighs apart against the pads.\n" +
                "Slowly return to the starting position and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(148, "Thigh Adductor", "This exercise targets and works the muscles of the inner thigh.", "Sit on the machine and place your knees against the pads.\n" +
                "Draw your abs in and “adduct” or move your thighs together.\n" +
                "Slowly return to the starting position and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(149, "Weighted Sissy Squat with Weight Plate", "Don’t let the name of this exercise fool you, it is a an assisted squat which effectively works the quadriceps (leg muscles).", "Grasp a weight rack or bar for support with one arm and hold a free weight plate across your chest with your other hand.\n" +
                "With your abs drawn in, squat down lowering your body to the floor until your knees are almost fully flexed.\n" +
                "Return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(150, "Wide Stance Squat with Barbell", "This version of the squat uses a wider stance to isolate the inner thigh.", "Lifting a barbell off of a weight rack, position it on your shoulders.\n" +
                "Stand with your feet wider than shoulder width apart (about 30 inches apart) with your toes pointing out.\n" +
                "Drawing your abs in descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(151, "Zecher Squats", "This variation of squat is similar to a Front Squat, it works to build the upper muscles of the quadriceps (thighs).", "Lift a barbell off of a weight rack and place across your chest. Hold the barbell with your arms crossed.\n" +
                "Place your feet slightly wider than shoulder width apart with your knees and toes pointed slightly outward.\n" +
                "Drawing your abs in, descend slowly by bending at the knees and hips as if you are sitting down (squatting).\n" +
                "Lower yourself as far as you can control without letting your body shift towards your toes (this will cause you to loose balance).\n" +
                "Pause in the downward position and slowly return upright to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(152, "Bench Dips", "This exercise is one of the most basic and still one of the best for building the triceps (muscles on the back of the arm).", "Place two benches parallel to each other 3-4 feet apart.\n" +
                "Sit on one bench and place your feet on the edge of the other bench so that your legs are suspended between the 2 benches.\n" +
                "Cross your feet for support.\n" +
                "Hold onto the bench with your hands for support and slowly lower body towards the floor by bending your elbows.\n" +
                "Pause and then return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(153, "Incline Pushdown with Cable", "This exercise uses cables to isolate and work the triceps (muscles on the back of the arms).", "Place an incline bench facing away from a cable pulley.\n" +
                "Attach a short straight bar to the pulley and adjust the height so that your arms can extend fully.\n" +
                "Lie face up on the bench and grasp the bar with a narrow overhand grip.\n" +
                "Keeping your arms straight, slowly push the bar down towards your feet.\n" +
                "Pause at the bottom and then return to starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 14 #####
        exercises.add(new ExerciseDTO(154, "Incline Triceps Extension with Cable", "This exercise uses cables to isolate and work the triceps (muscles on the back of the arms).", "Place an incline bench facing away from a cable pulley.\n" +
                "Attach a short straight bar to the pulley and adjust the height so that your arms can extend fully.\n" +
                "Lie face up on the bench and grasp the bar with a narrow overhand grip.\n" +
                "Starting with your elbows bent overhead, slowly lower the bar towards your feet.\n" +
                "Pause at the bottom and then return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(155, "Lying Tricep Extension with Cable", "This exercise uses cables to isolate and work the triceps (muscles on the back of the arms).", "Place a flat bench with the end towards a weight stack.\n" +
                "Attach a short straight bar to the pulley and lower the pulley to the bottom of the stack.\n" +
                "Lie face up on the bench and grasp the bar with a narrow overhand grip.\n" +
                "Starting with your arms extended lower the bar towards the bar towards the stack by bending your elbows.\n" +
                "Slowly extend your arms upright and return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(156, "One Arm Tricep Extension with Cable", "This exercise isolates the triceps muscles individually.", "Place a cable pulley on the highest setting and adjust a stirrup handle to the pulley.\n" +
                "Stand facing the weight stack and grasp the handle with and underhand grip.\n" +
                "Keep your back straight, your abs drawn in and your knees slightly bent.\n" +
                "Starting at the top, push your arm down until it is straight, feeling the concentration in your tricep.\n" +
                "Slowly return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(157, "Standing Triceps Extension 1", "This exercise works the triceps by reaching behind your neck", "Stand with your feet shoulder width apart, your back straight and your abs drawn in.\n" +
                "Hold a dumbbell in both hands, with your palms facing up.\n" +
                "Raise the dumbbell over your head and slowly lower the dumbbell in an arc behind your head.\n" +
                "Slowly raise the dumbbell back up to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(158, "Decline Close Grip Bench to Skull Crusher", "This exercise combines a close grip bench press with triceps extension.", "Lay with your back on a flat bench.\n" +
                "Grip a barbell with a close grip 8-12 inches apart.\n" +
                "Keeping your arms close to your sides lower the bar so it touches your chest, approximately an inch below your nipples.\n" +
                "Slowly return to starting position, concentrating on your triceps.\n" +
                "At the top of the exercise bend your arms down towards your head.\n" +
                "Raise the bar back up over your chest and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(159, "Decline Triceps Extension with Dumbbell", "This exercise combines a decline bench and dumbbells to target the triceps.", "Lay face up on a decline bench with a dumbbell in each hand.\n" +
                "Extend your arms so they are perpendicular to your chest, and keeping your elbows in one place, lower your hands so the dumbbells are lowered near your head.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(160, "Decline EZ Bar Triceps Extension with Barbell", "This exercise combines a decline bench and a curved bar to more efficiently work the triceps.", "Lay face up on a decline bench and grasp an EZ Bar (Curved Barbell).\n" +
                "Extend your arms so they are perpendicular to your chest, and keeping your elbows in one place, lower the bar toward your head.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(161, "Tricep Dips using Machine", "This exercise uses a machine to work the triceps while in seated position.", "Adjust the seat of the machine so that the handles are equal with your elbows.\n" +
                "Grasping the handles, press down with equal pressure until your arms are fully extended.\n" +
                "Pause, and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(162, "Tricep Dips using Body Weight", "This classic exercise uses the weight of your own body to work your triceps.", "Grasp a set of parallel bars and push yourself up to the starting position.\n" +
                "Keeping your elbows close to your body, lower yourself down until your triceps are parallel to the floor.\n" +
                "Slowly raise yourself back to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(163, "One Arm Triceps Extension with Dumbbell", "This exercise uses a dumbbell to work each arm individually.", "Sit on a bench with your back straight and your abs drawn in.\n" +
                "Grasp a dumbbell in your hand and place it behind your neck with your elbow bent.\n" +
                "Slowly extend your arm straight up over your head and slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(164, "Incline Triceps Extension with Barbell", "This version of a triceps extension uses gravity to increase the resistance of the exercise.", "Grasp a barbell with a close grip (4-6 inches apart).\n" +
                "Lie on an incline bench and hold the bar over your head.\n" +
                "Lower your arms in a slight arc so your forearms are touching your biceps.\n" +
                "Slowly return back to the starting position with the bar over your head.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(165, "JM Press", "his exercise is named after the inventor, J.M. Blakley. This is an advanced exercise used by bodybuilders and power lifters to build explosive power in the triceps muscle (back of the arms).", "Lay on a flat bench with your head just at the end of the bench.\n" +
                "Grasp the bar with a medium overhand grip keeping your elbows close to your side, over your sternum (mid chest).\n" +
                "Lower the bar towards your sternum at a regular pace, then pause for a moment.\n" +
                "With a controlled but rapid pace, push the bar back to starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 15 #####
        exercises.add(new ExerciseDTO(166, "Kneeling Triceps Concentration Extension with Cable", "Concentration exercises limit your range of movement to increase the effectiveness of the movement.", "Attach a stirrup handle to a cable pulley and adjust the pulley up high.\n" +
                "Kneeling on your left knee, with the left side of your body turned towards the machine, keep your right leg bent and the upper portion of your thigh parallel to the floor.\n" +
                "With your right elbow and upper arm pressed against your inner thigh just above your knee, pull the cable down towards the floor in a slight arcing motion.\n" +
                "Slowly to return to starting position.\n" +
                "Repeat and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(167, "Kneeling Triceps Extension with Cable", "Kneeling allows you to isolate your triceps more effectively.", "Place a bench sideways in front of a high pulley machine.\n" +
                "Grasp a straight bar with an over head grip.\n" +
                "Kneel on the floor in front of the bench with your back straight and your head down.\n" +
                "With your elbows and forearms above your head, push the bar down in an arc so that your forearms are touching the bench.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(168, "Low Triceps Extension with Cable", "This exercise is done while lying on a seated row station.", "Attach a rope to the cable pulley at of a seated row station.\n" +
                "Lie on your back on the bench with your head facing the weight stack.\n" +
                "Grasp the rope with your palms facing each other and raise your arms over your chest, with your forearms parallel to the floor at a 90 degree angle.\n" +
                "Slowly extend your arms upward squeezing your triceps.\n" +
                "Return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(169, "Lying Close-Grip Triceps Extension Behind the Head with Barbell", "You can use a barbell or EZ (curl) bar to further concentrate the benefit to your triceps.", "Lie flat on a bench on your back.\n" +
                "Grasp the bar with a grip approximately 8 inches apart and lower the bar behind your head.\n" +
                "Keeping the bar parallel with the floor, bend your arms down in an arc so the bar moves in a direction under your head.\n" +
                "Slowly return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(170, "Lying Close Grip Triceps Press to Chin with Barbell", "This exercise is a classic for building the triceps.", "Lie on a flat bench on your back, with your head at one end.\n" +
                "Grasp a bar with a close grip, approximately 6 inches apart.\n" +
                "Raise the bar straight up over your shoulders, keeping your elbows close in.\n" +
                "Slowly lower the bar in an arc towards your chin.\n" +
                "Return the bar to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(171, "Lying Triceps Extension with Dumbbells", "This exercise uses gravity to increase the resistance of the weight.", "Lay on a flat bench with your head at one end and your feet planted firmly on the floor.\n" +
                "Grasp a dumbbell with both hands with palms facing up.\n" +
                "Raise dumbbell over your body. Your arms will be pointed towards the ceiling.\n" +
                "While keeping your upper arms and elbows still, lower the dumbbell in an arc behind your head.\n" +
                "Slowly raise the dumbbell back to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(172, "Lying Triceps Extension Across Face with Dumbbell", "This is an advanced exercise and should be avoided by beginners.", "Lay on a flat bench with your head at one end and your feet planted firmly on the floor.\n" +
                "Grasp a dumbbell in each hand with palms facing up.\n" +
                "Raise dumbbells over your body. Your arms will be pointed towards the ceiling.\n" +
                "While keeping your upper arms and elbows still, lower the dumbbell slowly over your face bending only your elbow.\n" +
                "Slowly raise the dumbbell back to the starting position and repeat with your other arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(173, "Lying Triceps Press with Barbell", "This is another classic exercise for building the triceps.", "Lie on a flat bench with your head at one end.\n" +
                "Grasp an EZ or Curl bar with an overhand grip.\n" +
                "Raise the bar straight up over your shoulders, taking care to keep your elbows in close.\n" +
                "Slowly lower the bar in an arc over your head.\n" +
                "Return the bar to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(174, "Lying Two Arm Triceps Extension with Dumbbell", "This a lying version of a Triceps Kickback.", "Lie flat on a bench, on your back, with your head at one end.\n" +
                "Grasp a dumbbell in each hand and raise them at arms length over your shoulders.\n" +
                "Lower the dumbbells in an arc bending your elbows until your forearms are touching your biceps.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(175, "Old School Reverse Extensions", "As the name suggests, this is an old classic exercise that hits the triceps hard.", "Lie down on a bench, on your back, with your head at one end.\n" +
                "Grasp a barbell with an underhand grip.\n" +
                "Move your arms beyond your head, keeping them in a straight line.\n" +
                "Bending only your elbows, slowly lower and raise the barbell.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(176, "Single Arm Pronated Triceps Extension with Dumbbell", "This is an advanced exercise to isolate and work the tricep (back of the arm) muscles.", "Lie flat in a bench with your head at one end and your feet placed firmly on the floor.\n" +
                "Grasp a dumbbell in one hand and raise it to a position above your chest, with your palm facing your feet.\n" +
                "Place your free hand under the shoulder to support your other arm.\n" +
                "Slowly lower the weight moving only your forearm and elbow towards and away from your chest.\n" +
                "Repeat with your other arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(178, "Single Arm Supinated Triceps Extension with Dumbbell", "This exercise is similar to the Single Arm Pronated Triceps Extension except the movement is over the head instead of across the chest.", "Lie flat in a bench with your head at one end and your feet placed firmly on the floor.\n" +
                "Grasp a dumbbell in one hand and raise it to a position above your chest, with your palm facing the floor.\n" +
                "Place your free hand under the shoulder to support your other arm. Slowly bend your elbow over your head with the weight moving only your forearm and elbow.\n" +
                "Return to the starting position and repeat with your other arm.", "", "", new RealmList<SetDTO>()));

        //##### page 16 #####
        exercises.add(new ExerciseDTO(179, "Close Triceps Pushup", "This version of the Pushup isolates the tricep (back of the arm) muscles.", "Kneel down on the floor or a mat, placing your hand thumbs together and raise your up on your toes.\n" +
                "Draw your abs in and keep your back and neck in a straight line.\n" +
                "Slowly lower your body towards the floor, by bending your arms, until you are nearly touching the floor.\n" +
                "In a controlled manner raise your body back up to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(180, "Reverse Grip Triceps Pushdown", "This exercise is the reverse version of the triceps pushdown.", "Stand in front of a cable machine and attach a bar to a high pulley.\n" +
                "Grasp the handle with palms facing up.\n" +
                "Keeping your elbows at your side, pull the handle down towards your thighs.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(181, "Reverse Triceps Bench Press with Barbell", "This exercise is a version of the Bench Press that specifically isolates the triceps.", "Lie on a flat bench with your head at one end and your feet placed firmly on the floor.\n" +
                "Grasp a barbell with palms facing your head about 16 inches apart.\n" +
                "Move the bar over your chest (about 1 inch below your nipples).\n" +
                "Extend your arms fully raising the bar fully and then lower the bar to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(182, "Seated One Arm Triceps Extension with Dumbbell", "This exercise uses a kickback movement to work the triceps in each arm individually.", "Sit on the edge of a bench with your feet flat on the floor.\n" +
                "Hold a dumbbell in your right hand.\n" +
                "Bring your right arm up to your side so the dumbbell is almost parallel to your chest, keeping your lower arm vertical.\n" +
                "Press your arm back in arc.\n" +
                "Return to the starting position repeat and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(183, "Seated Two-Arm Triceps Extension with Dumbbell", "This exercise is the two handed version of seated triceps extension.", "Sit on the edge of a bench with your feet flat on the floor.\n" +
                "Hold a dumbbell in each hand.\n" +
                "Bring your arms up to your side so the dumbbell is almost parallel to your chest, keeping your lower arm vertical.\n" +
                "Press your arm back in arc towards your back.\n" +
                "Return to the starting position repeat and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(184, "Seated Overhead Triceps Extension with Barbell", "This exercise uses a barbell behind your neck to isolate the triceps effectively.", "Sit on a short straight back chair with your feet firmly placed on the floor and your back straight.\n" +
                "Grasp an EZ or Curl bar, or barbell with a grip about 6 inches apart.\n" +
                "Raise the bar to arms length above your head and then lower the bar in an arc towards the floor behind your head.\n" +
                "Return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(185, "Seated Triceps Press with Dumbbell", "This exercise uses a single dumbbell held between both hands to work the triceps.", "Sit on a short, straight backed bench with your feet planted firmly on the floor.\n" +
                "Drawing your abs in, grasp the dumbbell with both hands with a palms up grip.\n" +
                "Raise the weight over your head, this is your starting position.\n" +
                "Lower the dumbbell in an arc from above your head to behind your back.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(186, "Smith Machine Close Grip Bench Press", "This version of a Bench Press uses a close grip to isolate the triceps as well as working the chest.", "Place a flat bench in the middle of a Smith Machine.\n" +
                "Using a close grip (about 6 inches apart) unlatch the bar and lower it towards your chest.\n" +
                "Pause just before the bar hits your chest and raise the bar by extending your arms to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(187, "Bent-Over One Arm Triceps Extension with Dumbbell", "This exercise uses a kickback movement to work the triceps in each arm individually.", "Stand with your feet shoulder width apart, your abs drawn in and your back straight as you bend at the waist.\n" +
                "Hold a dumbbell in your right hand.\n" +
                "Bring your right arm up to your side so the dumbbell is almost parallel to your chest, keeping your lower arm vertical and press your arm back in arc.\n" +
                "Return to the starting position repeat and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(188, "Bent-Over Two Arm Triceps Extension with Dumbbell", "This exercise uses a kickback movement to work the triceps in each arm individually.", "Stand with your feet shoulder width apart, your abs drawn in and your back straight as you bend at the waist until your upper body is parallel to the floor.\n" +
                "Hold a dumbbell in each hand.\n" +
                "Bring your right arm up to your side so the dumbbell in line with your chest, keeping your lower arm vertical and press your arm back in arc.\n" +
                "Return to the starting position repeat and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(189, "Standing Triceps Extension 2", "This exercise works the triceps by reaching behind your neck.", "Stand with your feet shoulder width apart, your back straight and your abs drawn in.\n" +
                "Hold a dumbbell in both hands, with your palms facing up.\n" +
                "Raise the dumbbell over your head and slowly lower the dumbbell in an arc behind your head.\n" +
                "Slowly raise the dumbbell back up to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(190, "One Arm Low-Pulley Triceps Extension with Cable", "This exercise uses a cable machine to isolate the triceps.", "Attach a handle to a low cable pulley machine.\n" +
                "Stand with your back to the weight stack, your feet shoulder width apart.\n" +
                "With your left hand, reach behind you and grasp the handle.\n" +
                "Place your right hand on your left elbow for support.\n" +
                "Extend your left arm straight up towards the ceiling and then lower it to the starting position.\n" +
                "Repeat and switch arms.", "", "", new RealmList<SetDTO>()));

        //##### page 17 #####
        exercises.add(new ExerciseDTO(191, "Standing One Arm Triceps Extension with Dumbbell", "This is a single arm version of a behind the neck triceps exercise.", "Stand with your feet shoulder width apart, your back straight and your abs drawn in.\n" +
                "Hold a dumbbell in your right hand with your palms facing up.\n" +
                "Raise the dumbbell over your head and slowly lower the dumbbell in an arc behind your head, so that the dumbbell lines up with your spine.\n" +
                "Slowly raise the dumbbell back up to the starting position.\n" +
                "Switch arms and repeat.", "", "", new RealmList<SetDTO>()));

        exercises.add(new ExerciseDTO(192, "Standing Triceps Extension with Towel 1", "This exercise requires another person and uses manual resistance, or your body’s own weight to build muscle.", "Stand with your feet shoulder width apart, your back straight and your abs drawn in.\n" +
                "Hold a towel or rope facing up behind and in back of your head.\n" +
                "Have a partner hold the towel taught during the exercise so the resistance is constant.\n" +
                "Lower your forearms down until they reach your biceps, then slowly raise your arms back up to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(193, "Triceps Kickback with Dumbbell", "This along with the Triceps Push Down is one of the most beneficial exercises for the arms.", "Standing along side a flat bench, bend your left knee and place it on the bench.\n" +
                "Place your left hand on the bench for support, keeping your back at a 45 degree angle.\n" +
                "Hold a dumbbell in your right hand and place your right foot on the floor.\n" +
                "Bend your right arm and raise it up to your shoulder, then with controlled motion kick it back, fully extending your arm.\n" +
                "Switch arms and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(194, "Standing Overhead Triceps Extension with Barbell", "This is a double arm barbell version of a behind the neck triceps exercise.", "Stand with your feet shoulder width apart, your back straight and your abs drawn in.\n" +
                "Hold a barbell or EZ Curl bar about 6-8 inches apart with your palms facing up.\n" +
                "Raise the bar over your head and slowly lower the dumbbell in an arc behind your head, towards your back.\n" +
                "Slowly raise the bar back up to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(195, "Standing Triceps Extension with Towel 2", "This exercise requires another person and uses manual resistance, or your body’s own weight to build muscle.", "Stand with your feet shoulder width apart, your back straight and your abs drawn in.\n" +
                "Hold a towel or rope facing up behind and in back of your head.\n" +
                "Have a partner hold the towel taught during the exercise so the resistance is constant.\n" +
                "Lower your forearms down until they reach your biceps, then slowly raise your arms back up to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(196, "Tate Press with Dumbbell", "This is an advanced triceps exercise which moves the muscle differently than other exercises.", "Lay flat on a bench with your head at one end and your feet firmly planted on the floor.\n" +
                "Hold a dumbbell in each hand so your palms are facing your feet.\n" +
                "Drawing your abs in and keeping your back on the bench, raise the weights to the center of your chest.\n" +
                "Without moving elbows, slowly raise your arms up and out contracting your triceps.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(197, "Triceps Pushdown with Cable", "This exercise is the original version of the triceps pushdown.", "Stand in front of a cable machine and attach a short bar to a high pulley.\n" +
                "Grasp the handle with your palms facing down.\n" +
                "Draw your abs in and keep your back straight.\n" +
                "Keeping your elbows at your side push the handle down towards your thighs.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(198, "Triceps Pushdown with Rope and Cable", "This version of the triceps pushdown uses a rope for better definition in the muscle.", "Stand in front of a cable machine and attach a rope to a high pulley.\n" +
                "Grasp the rope with palms facing down.\n" +
                "Draw your abs in and keep your back straight.\n" +
                "Keeping your elbows at your side push the rope down towards your thighs, if possible “split” the rope apart at the bottom.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(199, "Triceps Pushdown with V Bar and Cable", "This exercise is the triceps pushdown uses a V shaped bar. Many people find this version easier on the elbows.", "Stand in front of a cable machine and attach a V Bar to a high pulley.\n" +
                "Grasp the bar with palms facing down.\n" +
                "Draw your abs in and keep your back straight.\n" +
                "Keeping your elbows at your side push the bar down towards your thighs.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 18 #####
        exercises.add(new ExerciseDTO(200, "Standing Biceps Curl with Cable", "This version of the biceps curl uses a cable instead of a barbell to work the muscles of the arms.", "Attach a short bar to a cable pulley to the bottom of the weight stack.\n" +
                "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp the bar with palms facing up, and hands fairly close together.\n" +
                "Lower your arms fully to above your thighs and bending only your elbows, raise the bar to your upper chest.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(201, "Alternating Hammer Curl with Dumbbell", "This exercise uses a hammering (up and down) motion to isolate the biceps.", "Stand with our feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in each hand so your palms are facing each other.\n" +
                "Extend your arms so they are at the sides of your body.\n" +
                "Keeping your elbows locked lift your left arm in an arc towards your left shoulder.\n" +
                "Lower your arm and repeat with your right arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(202, "Alternating Incline Curl with Dumbbell", "This exercise uses an incline bench to change your body’s position as you perform bicep curls.", "Adjust an incline bench to a 45 degree angle.\n" +
                "Grasp a dumbbell in each hand and sit back on the bench with your feet firmly planted on the floor.\n" +
                "Allow your arms to hang down at your sides.\n" +
                "Keeping your elbow straight, raise your right arm up towards your head.\n" +
                "In a controlled manner lower the weight and repeat with your left arm.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(203, "Lying Incline Curl with Barbell", "This exercise uses an incline bench to change your body’s position as you perform bicep curls. The movement is very similar to a Preacher Curl.", "Adjust an incline bench to a 45 degree angle.\n" +
                "Grasp a barbell with a shoulder width grip and lean face down against the bench, with your feet resting on the floor for support.\n" +
                "Allow your arms to hang down at your sides.\n" +
                "Keeping your elbows straight, raise the bar up towards your head, contracting your biceps.\n" +
                "In a controlled manner lower the weight and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(204, "Hammer Curls with Rope and Cable", "Using the rope with the cable machine allows you isolate the biceps with this version of the Hammer Curl. This is a good exercise for beginners.", "Attach a rope to a cable pulley to the bottom of the weight stack. Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in. Grasp the rope with a close underhand grip (palms facing up). Lower your arms fully to above your thighs and bending only your elbows, raise the rope to your upper chest. Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(205, "Preacher Curl with Cable", "A Preacher Curl uses a special bench to support the triceps and isolate the biceps to build the arms.", "Place a padded arm curl bench in front of a cable machine.\n" +
                "Attach a short bar to the bottom pulley.\n" +
                "Rest your arms against the bench and extend them fully.\n" +
                "With a narrow grip (6 inches) grasp the bar with palms facing up and pull it towards your head.\n" +
                "Pause for a moment and then lower the bar back to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(206, "Close Grip EZ Bar Curl with Barbell", "This exercise uses a cambered EZ or curl bar to isolate the biceps during a curl.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp an EZ bar with an underhand close (4 inch) grip on the innermost part of the bar.\n" +
                "Extend your arms fully against your thighs.\n" +
                "Keeping your elbows straight, raise the bar towards your chest.\n" +
                "Pause for moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(207, "Close Grip Standing Bicep Curls with Barbell", "This exercise uses a standard barbell to isolate the biceps during a curl.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a barbell with palms up, about 12 inches apart.\n" +
                "Extend your arms fully against your thighs.\n" +
                "Keeping your elbows straight, raise the bar towards your chest until your forearms touch your chest.\n" +
                "Pause for moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(208, "Concentration Curls with Dumbbell", "Concentration exercises limit your range of movement to increase the effectiveness of the movement.", "Sit on the end of a flat bench with your feet flat and your legs spread apart.\n" +
                "Grasp a dumbbell in your hand, palms facing up.\n" +
                "With your elbow pressed against your inner thigh, curl the dumbbell up towards your chest.\n" +
                "Slowly return to the starting position.\n" +
                "Repeat and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(209, "Cross Body Hammer Curl with Dumbbell", "This exercise targets the biceps using a crossing motion rather than a standard curl.", "While standing, grab a dumbbell in each arm with palms facing in.\n" +
                "Keeping your palms facing in (and not twisting your arms) curl the dumbbell up towards your opposite shoulder.\n" +
                "Bring the top of dumbbell up to and touching the shoulder.\n" +
                "Slowly with a controlled manner, lower the dumbbell back to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(210, "Drag Curl with Barbell", "This exercise is credited to the late Vince Gironda, the “Iron Guru”. It not only blasts the biceps and works the forearms as well.", "Stand with your feet shoulder width apart, your knees slightly bent, and your abs drawn in.\n" +
                "Grasp the bar with palms down, shoulder width apart.\n" +
                "Extend your arms fully so they are against your thighs.\n" +
                "Slowly raise your arms, palms down, to touch your chest.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(211, "Alternating Bicep Curl with Dumbbell", "This exercise is a single arm version of a biceps curl.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in each hand so your palms are facing up.\n" +
                "Extend your arms so they are at the sides of your body.\n" +
                "Keeping your elbows locked lift your left arm to your chest so that your forearm touches your bicep.\n" +
                "Lower your arm and repeat with your right arm.", "", "", new RealmList<SetDTO>()));

        //##### page 19 #####
        exercises.add(new ExerciseDTO(212, "Biceps Curl with Dumbbell", "This version of a biceps curl uses both arms at the same time.", "Stand with our feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in each hand with your palms facing up.\n" +
                "Extend your arms so they are at the sides of your body.\n" +
                "Keeping your elbows, locked lift your arms to your chest so that your forearms touch your biceps.\n" +
                "In a slow controlled manner, lower your arms to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(213, "Prone Incline Biceps Curl with Dumbbell", "This exercise uses an incline bench to change your body’s position as you perform bicep curls. The movement is very similar to a Preacher Curl.", "Adjust an incline bench to a 45 degree angle.\n" +
                "Grasp dumbbells in each hand and lean face down against the bench, with your feet resting on the floor for support.\n" +
                "Allow your arms to hang down at your sides.\n" +
                "Keeping your elbow straight, raise the dumbbells up towards your head, contracting your biceps.\n" +
                "In a controlled manner, lower the weight and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(214, "EZ Bar Curl with Barbell", "This exercise uses an EZ or curl bar to better isolate and build the arms.", "Stand with your feet shoulder width apart and your knees slightly bent.\n" +
                "Grasp the barbell with palms up.\n" +
                "Lower the bar so it is against your thighs.\n" +
                "Keeping your elbows still, raise the bar up to your chest so that your forearms touch your biceps.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(215, "Bicep Hammer Curl with Dumbbell", "This exercise uses a hammering (up and down) motion to isolate the biceps.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in each hand so your palms are facing each other.\n" +
                "Extend your arms so they are at the sides of your body.\n" +
                "Keeping your elbows locked lift your arms in an arc towards your shoulders.\n" +
                "Lower your arms in a steady controlled motion and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(216, "Flexor Incline Curls with Dumbbell", "This exercise was designed to keep the stress on the biceps.", "Sit on an incline bench with your feet firmly on the floor in front of you.\n" +
                "Grasp a dumbbell with your palms facing up.\n" +
                "Bring your arms down to your sides and while keeping your wrists as straight as possible, raise your wrists to your shoulders.\n" +
                "Concentrate on squeezing your biceps as you contract (raise) your arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(217, "High Cable Curls", "This exercise allows you to target the muscles of your arms from a different position.", "Place a flat bench next to a cable weigh stack.\n" +
                "Attach a short bar to the high pulley.\n" +
                "Lay on your back with your head towards the stack.\n" +
                "Grip the bar with palms facing you)and extend your arms fully on a slight angle over your head.\n" +
                "Pull the bar down towards you, curling your arms as much as possible.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(218, "Incline Biceps Curl with Dumbbell", "This exercise uses an incline bench to change your body’s position as you perform bicep curls. The movement is very similar to a Preacher Curl.", "Adjust an incline bench to a 45 degree angle.\n" +
                "Grasp dumbbells in each hand and sit down with your back against the bench, with your feet resting on the floor for support.\n" +
                "Allow your arms to hang down at your sides.\n" +
                "Keeping your elbow straight, raise the dumbbells up towards your head, contracting your biceps.\n" +
                "In a controlled manner lower the weight and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(219, "Incline Inner Biceps Curl with Dumbbell", "This exercise uses an incline bench and twist to more to change your body’s position as you perform bicep curls. The movement is very similar to a Preacher Curl.", "Adjust an incline bench to a 45 degree angle.\n" +
                "Grasp dumbbells in each hand with and sit down with your back against the bench, with your feet resting on the floor for support.\n" +
                "Allow your arms to hang down at your sides, your palms facing your body.\n" +
                "Keeping your elbow straight, raise the dumbbells up towards your head, contracting your biceps.\n" +
                "In a controlled manner lower the weight and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(220, "Lying Bicep Cable Curl", "By lying down you are able to isolate the muscles of your biceps without standing or sitting.", "Attach a short bar to a cable pulley set on the lowest setting.\n" +
                "Lie down on a mat or the floor with your feet touching the weight stack.\n" +
                "Grasp the bar with and palms facing up.\n" +
                "With your arms fully extended and your elbows at your sides, pull the bar in an arc to your chest.\n" +
                "Pause for a moment contracting your biceps, and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(221, "Lying Close Grip Biceps Curls with Cable", "This exercise combines a flat body position with a pulling motion to work the biceps.", "Place a flat bench under a cable weight stack and attach a flat bar to the pulley.\n" +
                "Lie down with your head towards the stack and your feet firmly on the floor.\n" +
                "Grasp the bar with your palms down, about 8 inches apart, and extend your arms straight up.\n" +
                "Curl the bar down in an arc touching your chin.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(222, "Lying High Bench Biceps Curl with Barbell", "With this exercise your lay face down to isolate your biceps.", "Lay face down on a high bench with your head at one end and your toes pressed against the floor to support you.\n" +
                "Grasp a barbell with palms facing up, about 12 inches apart.\n" +
                "Extending your arms to the floor, curl your arms back towards your head in a slight arc, so your biceps touch your forearm.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(223, "Lying Supine Biceps Curl with Dumbbell", "This is another biceps exercise where you lay down to isolate the muscles you are working. This is an advanced exercise.", "Lie on a flat bench with your head at one end and your feet planted firmly on the floor.\n" +
                "With a dumbbell in each hand, palms facing in, bring your arms down to your sides, hanging off the bench.\n" +
                "Slowly raise your arms up until they are level with your chest.\n" +
                "Curl the dumbbells by twisting your palms, so that your forearms touch your biceps.\n" +
                "Slowly lower your arms to the starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 20 #####
        exercises.add(new ExerciseDTO(224, "Preacher Curl with Machine", "A Preacher Curl uses a special bench to support the triceps and isolate the biceps to build the arms. This version of the exercises is a machine exercise. This is a good exercise for beginners.",
                "Adjust the seat of the bench so your arms are level with the top of the bench. Rest your arms against the bench and extend them fully. Grasp the bar underhand (palms facing up) and pull it towards your head. Pause for a moment and then lower the bar back to starting position.",
                "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(225, "One Arm Preacher Curl with Dumbbell", "A Preacher Curl uses a special bench to support the triceps and isolate the biceps to build the arms. This version uses dumbbells and single arm movements for better control.", "Adjust the seat of the bench so your arm is level with the top of the bench.\n" +
                "Grasp a dumbbell in your hand with palms facing up.\n" +
                "Rest your arm against the bench and extend it fully down.\n" +
                "Keeping your arm on the bench at all times, curl the dumbbell up towards your head.\n" +
                "Pause for a moment and then lower the dumbbell back to starting position.\n" +
                "Switch arms and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(226, "Overhead Curl with Cable", "By using cable this exercise isolates and defines the biceps (arm) muscles.", "Attach a stirrup handle to each side of a high pulley on a cable machine.\n" +
                "Grasp the handles with palms facing up and stand with your feet shoulder width apart.\n" +
                "Extend your arms fully to each side.\n" +
                "Keeping your elbows steady, curl your wrists towards the sides of your head.\n" +
                "Contract your biceps and pause for a moment.\n" +
                "Then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(227, "Preacher Curl with Barbell", "A Preacher Curl uses a special bench to support the triceps and isolate the biceps to build the arms.", "Adjust the seat of the bench so that your arms are level with the top of the bench.\n" +
                "Grasp the bar with palms facing up, shoulder width apart.\n" +
                "Picking up the bar, rest your arms against the bench and extend them fully.\n" +
                "Keeping your arms on the bench at all times, curl the bar up towards your head.\n" +
                "Pause for a moment and then lower the bar back to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(228, "Preacher Hammer Curl with Dumbbell", "This exercise combines a Hammer Curl with a Preacher bench.", "Adjust the seat of the bench so that your arms are level with the top of the bench.\n" +
                "Grasp a dumbbell in each hand with your palms facing each other, shoulder width apart and extend your arms fully along the bench.\n" +
                "Keeping your arms and elbows on the bench at all times, curl the dumbbells up towards your head.\n" +
                "Pause for a moment and then lower the bar back to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(229, "Reverse Plate Curls with Weight", "This exercise uses a free weight plate to build muscle.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a plate in your hands with palms facing down, at the 11:00 and 1:00 o’clock position.\n" +
                "Keeping your elbows and arms at your side, curl the slowly up towards your head.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(230, "Seated Close Grip Concentration Curls with Barbell", "Concentration exercises limit your range of movement to increase the effectiveness of the movement. This version uses a barbell.", "Sit on the end of a flat bench with your feet flat and your legs spread apart.\n" +
                "Bend forward at your waist keeping your back straight.\n" +
                "Grasp a barbell in your hands with a close grip approximately 6 inches apart with your palms facing up.\n" +
                "With your elbow pressed against your inner thigh, curl the bar up towards your chest.\n" +
                "Slowly lower the bar to the starting position near the floor.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(231, "Seated Bicep Curl with Dumbbell", "This exercise is a seated biceps curl using dumbbells.", "Sit on bench with your feet firmly on the floor and your back straight.\n" +
                "Grasp a dumbbell in each hand with your palms facing each other.\n" +
                "Lower the dumbbells to your sides and slowly curl your arms up so your palms are facing up.\n" +
                "Squeeze your biceps at the top and slowly lower to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(232, "Seated Inner Biceps Curl with Dumbbell", "This exercise works the inner biceps.", "Sit on the edge of a bench with your feet firmly planted on the floor.\n" +
                "Keep your back straight and your abs drawn in.\n" +
                "Grasp a dumbbell in each arm with your palms facing each other and fully extend your arms along your sides.\n" +
                "Curl your arms up and out, turning your palms up and out as you lift.\n" +
                "Slowly lower the dumbbells to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(233, "Spider Curl with Barbell", "This exercise uses the straight side of a Preacher Curl bench to increase the range of motion of the exercise.", "Turn a Preacher Curl bench around so you are leaning against the angled side.\n" +
                "Grasp a barbell with palms up, approximately 6 inches apart.\n" +
                "Leaning against the bench, lower the bar along the flat side of the bench fully extending your arms.\n" +
                "Curl your arms up so your biceps are touching your forearms.\n" +
                "Slowly lower the bar to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(234, "Standing Inner Biceps Curl with Dumbbell", "This exercise works the inner biceps.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in each arm with your palms facing each other, fully extend your arms along your sides.\n" +
                "Curl your arms up and out, turning your palms up and out as you lift.\n" +
                "Slowly lower the dumbbells to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(235, "Standing One Arm Bicep Curl with Cable", "This is a single arm version of a bicep cable curl. Cable machines are great for helping you learn proper form while building muscle.", "Attach a stirrup bar to a low cable pulley.\n" +
                "Stand of the side of the weight stack with your legs shoulder width apart and your knees slightly bent and your abs drawn in.\n" +
                "Grasp the stirrup in one hand with an palms facing up.\n" +
                "Lower the stirrup to the top of your thighs.\n" +
                "Keeping your elbows still, curl the stirrup up towards your chest.\n" +
                "Slowly lower the stirrup to the starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 21 #####
        exercises.add(new ExerciseDTO(236, "Standing One Arm Curl Over Incline Bench with Dumbbell", "This exercise is a great alternative to the Preacher Curl.", "Stand in front of an incline bench so your arm is resting on the top of the angled portion of the bench.\n" +
                "Grasp a dumbbell in your hand with palms facing up.\n" +
                "Rest your arm against the back bench and extend it fully down.\n" +
                "Keeping your arm on the bench at all times, curl the dumbbell up towards your head.\n" +
                "Pause for a moment and then lower the dumbbell back to starting position.\n" +
                "Switch arms and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(237, "Two-Arm Preacher Curl with Dumbbell", "A Preacher Curl uses a special bench to support the triceps and isolate the biceps to build the arms. This version of the exercise uses dumbbells.", "Adjust the seat of the bench so your arms are level with the top of the bench.\n" +
                "Grasp a dumbbell in each hand with palms facing up.\n" +
                "Picking up the dumbbells, rest your arms against the bench and extend them fully towards the floor.\n" +
                "Keeping your arms on the bench at all times, curl the weight up towards your head.\n" +
                "Pause for a moment and then lower the weights back to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(238, "Wide Grip Standing Biceps Curl with Barbell", "This is a wide grip version of a biceps curl. The wider grip targets the lateral or outer portion of the muscle.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a barbell with palms up as wide as you comfortably can.\n" +
                "Extend your arms fully against your thighs.\n" +
                "Keeping your elbows straight, raise the bar towards your chest until your forearms touch your chest.\n" +
                "Pause for moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(239, "Zottman Curl with Dumbbells", "This is an advanced exercise where you turn the dumbbells up and out.", "Stand with your feet shoulder width apart, your knees slightly bent and your and your abs drawn in.\n" +
                "Grasp a dumbbell in each hand with your palms facing each other.\n" +
                "Start with your hands at your sides.\n" +
                "Curl your arms up turning your wrists so they are facing down as the dumbbells reach your chest.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(240, "Zottman Preacher Curl with Dumbbells", "This is an advanced exercise where you turn the dumbbells up and out using a Preacher Curl bench for support.", "Sit at a Preacher Curl Bench.\n" +
                "Grasp a dumbbell in your hand with your palms facing up.\n" +
                "Extend your arm fully along the bench.\n" +
                "Curl your arm up turning your wrist so it faces down as the dumbbell reaches your chest.\n" +
                "Pause for a moment and then return to the starting position and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(241, "Bicep Curl with Machine", "This exercise uses a machine to insure proper form.", "Adjust the seat of the machine so your elbows are correctly aligned with the rotation axis.\n" +
                "Press your arms against the pads and keep them stationary throughout the exercise.\n" +
                "Curl your forearms until your arms are fully flexed.\n" +
                "Slowly lower your arms back to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(242, "One Arm Bicep Curl with Olympic Bar or Barbell", "This is an advanced exercise for building stability and the muscles of the arms.", "Stand with your feet shoulder width apart and your knees slightly bent.\n" +
                "Grasp an Olympic bar (empty weight bar) in the middle with one hand so that the bar is equally balanced.\n" +
                "Keep your free hand at your side for support.\n" +
                "Curl the bar up so your forearm touches your bicep.\n" +
                "Slowly lower the bar and change arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(243, "Biceps Curl Seated on Stability Ball with Dumbbell", "This version of a biceps curl uses a Stability Ball. Exercises preformed with Stability Balls encourage better posture and core muscle development.", "Grasp a dumbbell in each hand while sitting on a Stability Ball with your feet placed firmly in front of you, and your abs drawn in.\n" +
                "Keeping your elbows at your side and your back straight, curl your arms up to your chest.\n" +
                "Pause for a moment, contracting your biceps.\n" +
                "Slowly lower your arms to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(244, "Biceps Curl Squat with Dumbbell", "This exercise combines a squat with a curl to effectively work the entire body. This is an advanced exercise.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in each hand and curl your arms up to your shoulders.\n" +
                "Bending at the waist, squat down as you lower your hands to the floor (the lower part of a curl).\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(245, "Biceps Curl Reverse with Dumbbells", "This is a reverse curl exercise that works all of the muscles of the arms.", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in each hand with palms facing down.\n" +
                "Keeping your arms at your side your elbows steady, lift the dumbbells up towards your shoulders.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(246, "One Arm Bicep Concentration on Stability Ball with Dumbbell", "This exercise uses a Stability Ball instead of a bench for better form.", "Grasp a dumbbell in one hand and sit on a Stability Ball with your feet wide apart.\n" +
                "Bend forward leaning at the waist.\n" +
                "With the dumbbell in your hand, place your elbow against your knee.\n" +
                "Lower your arm so it is flat against your calves.\n" +
                "Curl your arm up to your chest keeping your elbow steady.\n" +
                "Return to starting position and change hands.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(247, "Bicep Curl with Deadlift with Barbell", "This is an advanced exercise. This exercise combines a bicep curl with a deadlift.", "Grasp an EZ Curl or barbell with palms facing up.\n" +
                "Standing with your feet shoulder width apart and your knees slightly bent, draw your abs in.\n" +
                "Extend your arms so the bar rests in front of your thighs.\n" +
                "Bending at the waist, bring the bar down to just above the floor.\n" +
                "As you return to a standing position, bring the bar up and curl your arms bringing your forearms to your biceps.\n" +
                "Return the starting position and repeat.", "", "", new RealmList<SetDTO>()));

        //##### page 22 #####
        exercises.add(new ExerciseDTO(248, "Biceps Curl with Overhead Extension using Dumbbells on Stability Ball", "This is an advanced exercise. This exercise requires you to be able to balance kneeling on a stability ball before you can correctly perform this exercise.", "Grasp a dumbbell in each hand with palms facing up.\n" +
                "Kneel on a Stability Ball, drawing your abs in and keeping your back straight.\n" +
                "Raise one arm up over your shoulder and keep your other arm curled.\n" +
                "Bend your raised arm back as if performing a triceps extension while lowering your other arm and curling it back.\n" +
                "Return to starting position and repeat arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(249, "Quick Alternating Bicep Curls with Band", "This exercise uses a bungy or exercise band for resistance.", "Place a bungy under your feet and hold the handles palms forward in your hands.\n" +
                "Stand with your feet shoulder width apart, knees slightly bent and your abs drawn in.\n" +
                "Curl your arms up and down in an alternating motion, keeping your body straight.\n" +
                "As you become comfortable with the motion, increase your speed.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(250, "Bicep Curl Lunge with Bowling Motion", "This exercise is similar to the motions of bowling.", "Hold a Medicine Ball in one hand with your palms facing up at shoulder height.\n" +
                "Bring one foot back and into a lunge, keeping your front foot stationary and not letting your back knee touch the ground.\n" +
                "While lunging, bring the ball down and back as if you were bowling.\n" +
                "Bring your arm back up to the starting position, switch legs and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(260, "Bicep Curl on Stability Ball with Leg Raised", "This exercise incorporates balance along with a bicep curl.", "Grasp a dumbbell in each hand with a palms up grip.\n" +
                "Place the top of one foot behind you on a Stability Ball (see illustration) as if you were stretching your leg.\n" +
                "Keep your back straight and your abs drawn in.\n" +
                "Bring your arms down in front of your legs.\n" +
                "Raise your arms up, curling and contracting your biceps.\n" +
                "Lower your arms and return to starting position and switch legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(270, "Lateral Lunge with Bicep Curl with Dumbbell", "This is an advanced exercise that combines a lateral lunge with a bicep curl.", "Grasp a dumbbell in each hand with your palms facing up.\n" +
                "Stand with your feet together, your back straight and your abs drawn in.\n" +
                "Allow your arms to extend down fully in front of your thighs.\n" +
                "While keeping your body straight, take a step sideways and then bend your waist and your knee into a lunge.\n" +
                "As you go into the lunge, curl your arms up towards your shoulders contracting your biceps.\n" +
                "Return to starting position and switch legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(271, "Forward Lunge with Bicep Curl using Dumbbell", "This exercise combines a forward lunge with a bicep curl.", "Grasp a dumbbell in each hand with your palms facing up.\n" +
                "Stand with your feet together, your back straight and your abs drawn in.\n" +
                "Allow your arms to extend down fully to the sides of your body.\n" +
                "While keeping your body straight, take a step forward then bend your waist and your knee into a lunge.\n" +
                "Be careful not to let your back knee drop to the floor.\n" +
                "As you go into the lunge, curl your arms up towards your shoulders, contracting your biceps.\n" +
                "Return to starting position and switch legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(272, "Step Up Single Leg Balance with Bicep Curl using Dumbbells", "This is an advanced exercise. This exercise combines a step up with a bicep curl.", "Place a box or bench in front of you.\n" +
                "Place a dumbbell in each hand with palms facing up.\n" +
                "Keep your back straight, your shoulders back and your abs drawn in.\n" +
                "With one leg, step up on to the box and raise your other leg up (as if taking another step).\n" +
                "At the top of the step, curl your arms up by bringing your biceps towards your shoulders.\n" +
                "Return to the starting position and switch legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(273, "Medicine Ball Biceps Curl on Stability Ball", "This exercise combines a new technology, Stability Ball, with a old piece of fitness equipment, the Medicine Ball.", "Sit on a Stability Ball with your feet firmly on the floor in front of you.\n" +
                "Sit with your back straight, your shoulders back and your abs drawn in.\n" +
                "Hold a Medicine Ball in one hand and curl it up bringing your forearm up to your bicep.\n" +
                "Return to starting position and switch arms.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(274, "Stork Stance Bicep Curl with Dumbbells", "This is an advanced exercise that requires excellent coordination and balance.", "Grasp a pair of dumbbells in each hand, palms facing up.\n" +
                "Standing on one foot, extend your other foot back (see illustration) and extend your arms down so they are handing in front of your leg.\n" +
                "While on one foot, curl your arms up contracting your biceps.\n" +
                "Return to the starting position and switch legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(275, "Biceps Curl V Sit on Dome with Dumbbells", "This exercise uses a Dome (or Bosu) as a seat forcing you to engage and contract your abs throughout the exercise. The name comes from having our body in the shape of a “V”.", "Grasp a dumbbell in each hand and sit on a dome.\n" +
                "Keep your legs together and your knees bent.\n" +
                "Draw in and hold your abs and keep your back straight.\n" +
                "With you arms extended to your sides, curl and contract your biceps, bringing your forearms towards your shoulders.\n" +
                "Slowly return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(276, "Ankle Circles", "This is the most simple of all exercises for the calves.", "Stand on one foot near a chair or wall for support.\n" +
                "Lift your foot off the ground and draw circles clockwise in the air with your toes.\n" +
                "Reverse the direction (counter-clockwise) as well as switch feet.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(277, "Balance Board", "Your calves are stabilizing muscles for your legs, so this exercises uses a Balance Board or wobble board to force you to balance.", "Place a Balance Board on the floor.\n" +
                "Step onto the board with both feet and try to balance yourself.", "", "", new RealmList<SetDTO>()));

        //##### page 23 #####
        exercises.add(new ExerciseDTO(278, "Seated Calf Raise with Barbell", "This version of a calves raise uses a block and a barbell.", "Place a block on the floor about one foot in front of a bench.\n" +
                "Sit on the bench with a barbell across your upper thighs and the balls of your feet on the block.\n" +
                "Slowly raise and lower your toes up as high as possible without lifting your foot off the block.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(279, "Calves Press on Leg Machine", "This exercise uses a Leg Press Machine to work your calves. You can perform this exercise along with Leg Presses.", "Sit down on a Leg Press Machine and press the plate up as if you were performing a leg press.\n" +
                "Slide your feet down so that the balls of your feet are pressing against the rack (with your heels hanging free).\n" +
                "Keeping the handles locked, press up and flex your toes and then slowly bring your toes back towards your body.\n" +
                "Perform theses movements slowly for maximum benefit.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(280, "Donkey Calf Raises", "This is an old school gym exercise which requires the assistance of another person. This is an advanced exercise and not recommended for beginners.", "Bend your waist and lean your arms against a weight rack or bench that is waist height.\n" +
                "Have a partner sit on top of your lower back.\n" +
                "Raise up on your toes leaning slightly forward.\n" +
                "Pause at the top and then lower your heels to the floor.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(281, "Seated One Leg Calf Raise with Dumbbell", "This version of a calf raise allows you to exercise each leg individually. This exercise is helpful to people who are rehabilitating from injury as it allows you flexibility with the weight.", "Place a block on the floor about one foot in front of a bench.\n" +
                "Sit on the bench with a dumbbell across your upper thigh and the balls of one foot on the block.\n" +
                "Slowly raise and lower your toe up as high as possible without lifting your foot off the block.\n" +
                "Switch feet and repeat.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(282, "Knee Circles", "This is a simple exercise and a great stretch.", "Stand with your feet a little closer than shoulder width apart.\n" +
                "Keeping your arms across your chest or on your hips, move your knees in a circular motion, paying attention to keeping your feet flat on the floor and the movement in your ankles.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(283, "Rocking Standing Calf Raise with Barbell", "This exercise uses a simple rocking motion to strengthen the calves.", "Stand with a bar across your shoulders.\n" +
                "With your feet flat on the floor, lift your feet up and forward as if you are performing a calf raise.\n" +
                "Then “rock” back onto your ankles so your toes are flexed and off the floor.\n" +
                "Return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(284, "Seated Calf Raise using Machine", "This is one of the simplest and most effective exercises for the calves.", "Sit at a calf raise machine.\n" +
                "Put the balls of your feet on the footpad.\n" +
                "Place your thighs under the leg pad above your knees.\n" +
                "Unlock the bar and slowly raise your toes up as far as possible.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(285, "Smith Machine Reverse Calf Raises", "This is a unique exercise that works the tibialis anterior muscle, one of the muscles of the calves.", "Place a block or low box under the bar at a Smith Machine.\n" +
                "Stand on the edge of the box with your toes hanging off.\n" +
                "Place the bar across your shoulders and lean forward lifting your ankles off the box.\n" +
                "Return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(286, "Standing Barbell Calf Raise", "This is standing version of a Calf Raise.", "Place a block or two free weight plates on the floor.\n" +
                "Place a barbell across your back and step up so the balls of your feet are on the block.\n" +
                "Slowly lift your heels up and then lower them back to the floor getting as much of a stretch as possible.\n" +
                "Return to starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(287, "Standing Calf Raises using Machine", "This exercise uses a machine to allow for proper form.", "Stand with your toes on the footpad of the machine and adjust your shoulders under the upper pads.\n" +
                "Raise up on the balls of your feet, lifting your heels off the pad.\n" +
                "Hold for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(288, "Standing Calf Raise with Dumbbell", "This version of a Calf Raise uses dumbbells.", "Place a block or two free weight plates on the floor.\n" +
                "Grasp a dumbbell in each hand and step up so the balls of your feet are on the block.\n" +
                "Slowly lift your heels up and then lower them back to the floor getting as much of a stretch as possible.\n" +
                "Lower your heels and bend your ankles to stretch your calves.\n" +
                "Return to starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 24 #####
        exercises.add(new ExerciseDTO(289, "Air Bike", "This exercise works your abs by raising your arms to meet your legs. The movements are similar to riding a bike.", "Lie on your back on a mat.\n" +
                "Place your hands on either side of your head (over your ears).\n" +
                "Raise your legs up, bending your knees and keeping your calves parallel to the floor.\n" +
                "Bring your left arm elbow up to meet your right knee, as if riding a bike.\n" +
                "Repeat with your right elbow and left knee.\n" +
                "Continue alternating sides.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(290, "Ab Rollout with Barbell", "This exercise works the abs and lower back and is the proper version of an “ab roller”.", "Place a barbell on the floor at your feet.\n" +
                "Bending at the waist, grip the barbell with a shoulder with overhand grip.\n" +
                "With a slow controlled motion, roll the bar out so that your back is straight.\n" +
                "Keep your arms straight throughout the exercise.\n" +
                "Roll back up raising your hips and butt as you return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(291, "Ab Rollout on Knees with Barbell", "This exercise works the abs and lower back and is the proper version of an “ab roller”. This version is performed on your knees.", "Place a barbell on the floor in front of you.\n" +
                "Start by kneeling in front of the bar, grip the barbell with a shoulder with overhand grip.\n" +
                "With a slow controlled motion, roll the bar out so that your back is straight, bend your knees and keep your feet off the floor.\n" +
                "Keep your arms straight throughout the exercise.\n" +
                "Roll back up bring the bar under your shoulders and return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(293, "Bent Knee Hip Raise", "This is a good exercise core muscle development.", "Lie on your back with your arms outstretched to your sides.\n" +
                "Bend your knees at a 60 degree angle and hold your feet up just off the floor.\n" +
                "Keeping your back and arms down, raise your hips up and off the floor so that your knees are over your chest.\n" +
                "Contract your abs for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(294, "Seated Ab Crunch with Cable", "This exercise uses the weight of a cable machine to assist in a crunch.", "Attach a rope to a high cable pulley.\n" +
                "Place a bench in front of cable weight stack.\n" +
                "Grasp the rope over your shoulders and sit with your back towards the stack.\n" +
                "With your feet firmly on the floor and keeping your hips steady, flex at the waist bringing your elbows towards your knees.\n" +
                "Return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(295, "Cross Body Crunch", "This version of the crunch works both the upper and lower portion of the abs.", "Lie on your back and bring your knees up to a 60 degree angle, keep your feet flat on the floor.\n" +
                "Place your hands on either side of your head (over your ears).\n" +
                "Curl up raising your right elbow and your left knee so that they touch over your chest.\n" +
                "Return to the starting position and repeat with alternate arms and legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(296, "Crunches with Legs on Stability Ball", "This exercise uses a Stability Ball as the base during your crunches.", "Lie on your back and place your feet up on a stability ball.\n" +
                "Place your hands across your chest, or on either side of your head.\n" +
                "Keeping the small of your back on the floor, raise your head, shoulders and chest up and off the floor.\n" +
                "Pause for a moment contracting your abs and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(297, "Crunches", "This is the most common abdominal exercise and possibly the most often improperly performed. Here is how to perform it correctly.", "Lie on your back with your feet up on a bench and your knees bent at a 90 degree angle.\n" +
                "Place your hands across your chest or on either side of your head (over your ears).\n" +
                "Raise your head, shoulders and chest off the floor and towards your knees, crunching your abs.\n" +
                "Hold the position for a moment and then lower to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(298, "Decline Crunch", "Using a decline crunch allows you keep your legs steady and isolate all of the abdominal muscles.", "Lie on a decline bench face up.\n" +
                "Place your feet securely under the pads.\n" +
                "Place your hands either across your chest or on either side of your head (over your ears).\n" +
                "Lay fully back and then rise slowly crunching your abs.\n" +
                "Pause for a moment and then return to the starting position.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(299, "Decline Oblique Crunch", "This version of a decline crunch isolates the oblique (side) muscles of the abs", "Lie on a decline bench face up.\n" +
                "Place your feet securely under the pads.\n" +
                "Place your hands either across your chest or on either side of your head (over your ears).\n" +
                "Raise your shoulders and chest up keeping your back straight and bring your left elbow to your right knee, slowly crunching your abs.\n" +
                "Pause for a moment and then return to the starting position.\n" +
                "Lay fully back and repeat with your right elbow to your left knee.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(300, "Side Bend with Dumbbell", "This exercise works the obliques, the muscles on the sides of your abs", "Stand with your feet shoulder width apart, your knees slightly bent and your abs drawn in.\n" +
                "Grasp a dumbbell in one hand and standing straight upright, bend at the waist as far as possible to one side.\n" +
                "Slowly return back up to the staring position.\n" +
                "Switch the dumbbell to your other hand and repeat the exercise.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(301, "Stability Ball Abdominal Crunch", "This crunch uses a Stability Ball. The ball allows you a better range of movement because it adapts better to your spine.", "Sit on top of an exercise or stability ball with your feet placed firmly on the floor for support.\n" +
                "Position yourself so that your lower back is centered on the middle of the ball.\n" +
                "Lie back and bring your hands across your chest, or on either side of your head (over your ears).\n" +
                "Raise your head, chest and shoulders up crunching your abs as you sit upright.\n" +
                "Pause for a moment and then lower yourself to the starting position.", "", "", new RealmList<SetDTO>()));

        //##### page 25 #####
        exercises.add(new ExerciseDTO(302, "Exercise Ball Pull In", "This exercise uses a ball to isolate and work the lower abdominal muscles.", "Lay down as if you were performing a pushup.\n" +
                "Place your feet and shins over an exercise or stability ball.\n" +
                "Keeping your back straight and supporting your weight on your hands, pull your knees towards your chest, so the ball rolls forward under your ankles.\n" +
                "Crunch your abs and then roll the ball back to starting position straighten your legs.", "", "", new RealmList<SetDTO>()));
        exercises.add(new ExerciseDTO(340, "Bent Over Row with Barbell", "This exercise focuses on the middle back.", "Place your feet shoulder width apart.\n" +
                "Bend over the bar so your back is flat and parallel to the floor.\n" +
                "Grasp the bar a bit wider than shoulder width apart with an overhand grip.\n" +
                "Hold the bar at arms length, this is starting position.\n" +
                "Slowly raise the bar to the lower part of your chest.\n" +
                "Pause for a moment than return to starting position.\n" +
                "Note: Keep your head up and your back straight while perform", "", "", new RealmList<SetDTO>()));
        return exercises;
    }


}
