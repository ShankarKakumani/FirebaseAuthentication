# Firebase Authentication
Firebase Authentication code guideline for Android developer 

## Demo application
See [these code examples in `/app` module](/app) of this repository. Example implementations are available in Java .

You can download demo version of Android app from here

[![EasyUpiPayment Demo App](https://img.shields.io/badge/EasyUpiPaymentEXAMPLE-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/PatilShreyas/EasyUpiPayment-Android/raw/master/app/app-debug.apk)

## Screenshots
<table width="100%">
	<tr>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/home_page.jpg?raw=true"></th>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/choose_an_account.jpg?raw=true"></th>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/user_profile.jpg?raw=true"></th>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/phone_number.jpg?raw=true"></th>

	</tr>
</table>

<table width="100%">
	<tr>
	  <th width="20%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/register.jpg?raw=true"></th>
	  <th width="20%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/login.jpg?raw=true"></th>
	  
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
