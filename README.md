# Firebase Authentication
Firebase Authentication code guideline for Android developer 

## Prerequisites
* google-services.json in app-level folder

## Features
* Sign-in with Email and Password
* Sign-in with Google log-in
* Sign-in with Phone Number
* Update user's password
* Send password reset
* Delete a user


## Project setup

* update your google-services.json in app-level folder.
* Add SHA certificate fingerprints in your Firebase. If not Google signin & Phone Number doesnt work.
* To add / update SHA fingerprints in firebase goto -> Project Settings --> General(Your apps) --> Add fingerprint.
* you can get SHA fingerprints from Android Studio . 
  -> Choose Gradle at the right side of the panel from Android studio.
  -> then goto Tasks --> android --> signingReport.
