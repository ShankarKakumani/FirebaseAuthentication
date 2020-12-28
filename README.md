# Firebase Authentication

## Demo application

You can download demo version of Android app from here

https://raw.githubusercontent.com/shankar7545/FirebaseAuthentication/master/app-debug.apk


## Screenshots
<table width="100%">
	<tr>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/home_page.jpg?raw=true"></th>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/choose_an_account.jpg?raw=true"></th>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/user_profile.jpg?raw=true"></th>
	  <th width="25%"><img src="https://github.com/shankar7545/FirebaseAuthentication/blob/master/Screenshots/phone_number.jpg?raw=true"></th>

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


## License

```
   Copyright 2020 Shankar Chowdary

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
