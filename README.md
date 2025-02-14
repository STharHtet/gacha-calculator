![image](https://github.com/user-attachments/assets/f69cbfab-8e1e-4932-80b1-75a54a21e28c)# Gacha Banner Calculator

This is a short summary and description of the code that has been developed in pure simple Java without additional modules as requested. The only library that has been used in the code is an external library called `JSON.simple`. The entire code will be attached together with this documentation. To start off, the structure of the file system of the code is represented below. The left side is the tree diagram version, and the right side is the VSCode version. Please note that the code is developed in VSCode, so if a different IDE like Eclipse or IntelliJ is used, the settings for utilizing the JSON library may have to be changed.

![image](https://github.com/user-attachments/assets/b6324669-bb2a-489c-8968-70943c3aed70)

## Banner.java

![image](https://github.com/user-attachments/assets/3157f243-c2eb-4d2f-b000-b02c58f535d1)

The `Banner.java` file handles all the banners' data. The banner data is separated into different attributes: banner name, banner start date, banner end date, and banner featured character. Furthermore, the code has been made to load banners from a JSON file. (Please look at the actual file for the full code.)

![image](https://github.com/user-attachments/assets/18fc42b2-d368-4ece-a275-02ead07322df)

The image above shows a recreated database based on the given reference in JSON format. This is the file where the banners will be loaded from. Please note it is just a recreation, so please reformat again if the actual database that is to be used has a completely different structure.

## Character.java

![image](https://github.com/user-attachments/assets/3af433c6-b759-49e8-aead-00b5b79cc26a)

This file contains the methods for handling character names on the banner.

## Player.java

![image](https://github.com/user-attachments/assets/2aaa228f-c6d4-4286-9ced-d562d81824cf)


This file contains all the methods for handling the player's currency (gems), currency (gems) per banner, the pull count, guaranteed character, and desired character. (Please look at the actual file for the full code.)

## Main.java

This is the main file where all the decisions for the banners are made, including calculating the player's resources that update based on the banner database and player's inputs.

![image](https://github.com/user-attachments/assets/5325b857-7b3c-4d50-93a2-7055f334e38e)

The code above represents the user input handling from the user, and they will be stored in their respective variables.

![image](https://github.com/user-attachments/assets/f373c198-ca75-4c76-beee-8b059b6b0d50)

Then, the code creates a player object that has all the attributes that have been created previously and shows an output that displays the player's available pulls. Next, a hash map is used to check if the same character name appears in the database (JSON file). This feature will be utilized later on to make decisions on whether to pull or skip on the character depending on whether the character will have a re-run or not.

![image](https://github.com/user-attachments/assets/6d0b64ff-2bea-4eaa-8c48-e3477e91776d)

The code above may look complex, but to break it down: first, it checks the user's desired character(s) and checks with the banner database. Then, there will be 3 types of decisions that will be made based on repeated characters and the player's resources. After the first check, the code will look through the database to see if the same character later appears in the database (a re-run). If the character is repeated in the database, the decision to 'Skip' will be made because it is the optimal decision to make and save resources for later. This decision is also good if the player wants multiple characters. Following on, the 'Indulge' decision will be made if the player has more pulls (calculated based on in-game currency) than the guaranteed pull count. Then, the 'Pull' decision will be made if the player has enough pulls or the exact optimal number of pulls for the desired character.

![image](https://github.com/user-attachments/assets/db0b18ae-c288-4bc0-aed9-8e213f09ef7f)

Following on, the decision for each banner will be displayed to the user, and the available pulls left after each banner will be calculated. This calculation is done based on the player's input that defines the in-game earnings after a banner. Afterwards, the added earnings plus the available leftover pulls will be summed up. The sum result is the pulls that will be available for the user after the decision made and earnings that have been obtained after a banner.

Compile (in this case, it has already been compiled) and run the `Main.java`, and it should be working. If you also use VSCode, I made it easy to run by clicking on the Run button on the top right corner.

**Commands to compile and run:**

```
javac -cp .;json-simple-1.1.1.jar -d out src/*.java
java -cp .;json-simple-1.1.1.jar;out Main
```
(On macOS/Linux, replace ; with :)


## Example Output

![image](https://github.com/user-attachments/assets/f901bbe2-7ce0-4185-bbe0-d6545cf9d4d5)

22,400 = 140 pulls if 60 per pull
3200 = 20 pulls if 160 per pull

140 + 20 = 160 pulls (Skip decision)
90 + 20 = 110 pulls (Skip decision)
110 + 20 = 130 pulls (Skip decision)
130 + 20 = 150 pulls (Skip decision)
160 -- 90 = 70 pulls (Indulge ∵ available pulls > guaranteed pulls)
70 + 20 = 90 pulls (Earned pulls after banner)
150 -- 90 = 60 pulls (Indulge ∵ available pulls > guaranteed pulls)
60 + 20 = 80 pulls (Earned pulls after banner)
80 pulls < 90 guaranteed pulls ∴ Skip
80 + 20 = 100 pulls left

The image above represents the example of how the code will run. First, it takes in user inputs and makes calculation and produce an output that is optimal for the user to get their desired character(s). The example above also uses the same examples that have been shown in the example scenario.
