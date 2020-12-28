# Firebase Authentication
Firebase Authentication code guideline for Android developer 

## Screenshots
<table width="100%">
	<tr>
	  <th width="33%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/home_page.jpg?raw=true"></th>
	  <th width="33%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/choose_an_account.jpg?raw=true"></th>
	  <th width="33%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/user_profile.jpg?raw=true"></th>
	</tr>
</table>

<table width="100%">
	<tr>
	  <th width="33%"><img src="https://raw.githubusercontent.com/shankar7545/FirebaseAuthentication/master/Screenshots/choose_an_account.jpg"></th>
	  <th width="33%"><img src="https://cloud.githubusercontent.com/assets/1763410/17030587/5b3c0d52-4f9a-11e6-97bc-a2ce140eb25a.png"></th>
	  <th width="33%"><img src="https://cloud.githubusercontent.com/assets/1763410/17030599/69df7e3e-4f9a-11e6-8fce-a7304a0f5e08.png"></th>
	</tr>
</table>

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
* Choose Gradle at the right side of the panel from Android studio
* then goto Tasks --> android --> signingReport.
