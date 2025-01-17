# Changelog
All notable changes to this project will be documented in this file.

##[4.0.2] - Yet to release
- Gujarati language.
- Marathi with tts
- Punjabi
- New animation yet to add
- On package update boards custom icons get deleted [issue fix]

##[4.0.1] - 12 Dec, 2021
- Trash feature to user boards.
- New Settings screen
- Added icons to the menu screen

##[4.0.0] - 16 Sept, 2021
- User can add icons or edit icons or delete icons at the home categories.
- Added the fish animation to encourage the child during app usage.
- Added th monochrome (black and white) display.

##[3.1.0] - 31 Dec, 2020
- Select voice of either male or female voice from available voices.
- Introducing the "Text bar" which displays spoken text from the app. To enable "Text bar" go to the Settings turn on the "Text bar" toggle button.
- Added English (Nigeria), Bengali (Bangladesh). 

##[3.0.3] - 12 Nov, 2020
- Added the Telugu language.

##[3.0.2] - 13 Aug, 2020
- Removed the serial keyboard.
- Improved the language selection at User registration screen.
- Simplified the language selection steps for users. Now, user chose language and then set language.
  If app does not generates the voice output, then user will see a dialog and redirected to Language setting more options.


##[3.0.1] - 10 Aug, 2020
- Fixed the immediate issue reported on Firebase Crashlytics.
- Moved the menu item "My boards" above "Keyboard Control"


##[3.0.0] - 16 July, 2020(live on Play Store 20 July, 2020)
### Added
- Make My Board
- User can search in using english text for any icon in any language.
- In "Main Screen" and "Language"  screen, an update dialog is added. The dialog will notify user if language package has changes.
- Update icon will change if update available.
- Fixed Android 10 Navigation UI issues.


##[2.0.1] - 15 January, 2020
### Added
- Fixed app shows english text at all places for menu screens.
- Fixed the app crash on privacy link at user registration link.
- Add play & Pause feature to About Jellow speak.


##[2.0] - 24 November, 2019
### Added
- Added new languages Tamil, Spanish, German & French
- Extra new icons in Spanish 41 language -> in Eating -> Breakfast, Lunch/Dinner, Snacks, Drinks categories.
- Added menu icon which opens a new dialog on home screen to select "Number of icons per screen". 
- Added separate screenshots for each language for Intro as well as Tutorial screen. 

### Updates
- Added the search functionality in Activity Sequences.
- Removed unused data collection.
- Unified the language packages. Now for all language there is common language icon set. Each language will have its own json file to load contents.
- Optimized the category icon loading.
- Registration page now stores the icon user name, caregiver number and home address as mandatory field.
- Updated the language switching process. User can switch to any language in 4 steps (excluding Marathi)
 a. Select language
 b. Disable "Wi-fi Only" button
 c. Check Text-to-speech data
 d. Apply changes
- Marathi language will download the audio package on demand. Then in 2 steps user can able switch to Marathi language.

### Removed
- The setting at intro screen which setups the Test-to-speech engine language upfront.
- Removed Intro screen for Google Text-to-speech setup and "wifi-only".
- Removed size specific screens. Each screen now have one layout for all size screens. The layout uses different dimensions for different screen sizes.


##[1.3.4-rc4]
- AccessibilitySettingsActivity and layout. This activity will has buttons that will take user to Accessibility settings directly. 
  Also, this activity has description about Switch, Visual Accessibility with respective link.

### Updates
- Updated the credits in Research, Vocabulary Development and User Studies
- Added the contents serial keyboard in KeyboardControlActivity.


##[1.3.2-rc3]
- Fixed the issue. The Model classes not able to serialize the json data on release build. Added the the annotation (@SerializedName(""))for every data variable in model class. 


##[1.3.1-rc2]
- Addition of Switch accessibility (Supports one switch, two switch with linear scanning or group scanning method).
- Addition in number of icons per screen setting. Now, user can set one, two, four icons per screen also.
- Addition of two new icons in Fun -> Indoor games:
 1) Play-doh
 2) Bubble
- Internal icon loading process now uses json data from Firebase. The icon and json files are bundled together in language package.
- Moved code base from support library to androidx library.
- Added Multidex support.
- Removed dialog library com.afollestad.material-dialogs. Now app uses native material dialog when downloading a language and deleting a language.
- Removed xml data files from values folder.  


##[1.2.5]  - 27, December 2018
- Addition of Visual accessibility (TalkBack support).
- Addition of new language: English (Australia)
- New design for Serial Keyboard.
- Addition of new language in Serial Keyboard : Bengali (India)
- Addition of new layouts to support 19 : 9 notch screen ratio devices.
- Corrected scrolls in intro screen.
- Bug reported on Crashlytics.


##[1.2.4]  - 16, October 2018
- Addition of new language: Marathi (India)
- Support for Android 9 devices (enable clear text traffic, disabled multi windows support)
- Unified parameter value for Firebase event
- Added Firebase Remote Config to update user local icon packages.
- Sequence buttons next and previous too close fixed
- User can receive notification if version code of app matches with payload version code
- Handled failures during User Registration
- Added scroll to intro screen
- Added Google text-to-speech engine availability in Intro


##[1.2.3]  - 12, September 2018
- Addition of new language: Bengali (India)
- All items in the ‘Places’ category now have context-appropriate sub-categories.
- Addition of new content/icons pertaining to good and bad habits, socially appropriate behaviors and reporting of abuse.
- Improved and colorful interface
- Clear, step-wise instructions to enable users to add/delete and change default languages within the app
- Bug fixes in Emergency call functionality
- Fixed other issues.


## [1.2.3]  -  23th, August 2018
- This is alpha version.

### Added
- Bengali language added.
- Places is changed and now have sub category.
- Added new content and new icons.

##FIXED
- Issues reported on firebase.

## [1.2.2] -  22th, June 2018
##FIXED
- Wifi only devices are unable to download new version. 


## [1.2.1]  -  20th, June 2018
### Added
- Search a category icon from level 1,2,3 and activity sequence screens in the app.
- Search events.
- Added notification to inform user about app updates on play store, Jellow workshop schedules and other relevant information.
- In app call request from Help -> Emergency, wifi only devices will speak icon speech only.
- Switch to enable or disable call feature in settings.
- Added new icons in Level Two "People", Level Three
  "Time and Weather -> Birthdays" and "Places"
- Added tap response to buttons.
- Added Firebase Crashlytics.

###Removed
- Firebase crash reporting tool

###Fixed
- In profile screen, now user details are encrypted and then stored to Firebase.
  User can now store all profile details excluding contact offline. User can change contact
  number when he/she has active Internet connection only.
- Screen automatically rotated in following cases:
   1) Home button pressed in LevelThreeActivity or SequenceActivity
   2) Save button pressed in ProfileFromActivity or SettingsActivity
   3) Language download finished
- Firebase user session are not getting stored to respective user data node.
- Locale of app changed if user locks screen or switches from app to other app and returns back.
   1) User set Hindi (India) language to app and when user is in AboutJellow screen and locks screen
    then unlocked screen presses "Speak" button then app speaks english verbiage.
- Crash after Oreo 8.0 1st April security update.
- Other issues reported in Firebase crash reporter.


## [1.2.0]    -   19th, March 2018
### Added
- Major additions.
- New content, verbiage, icons into categories Greet and Feel-> Requests, Daily Activities-> Therapy,
  Learning->Money and Help.
- Simple and easy to follow process to download new language.
- Hindi (India), English (India) two new languages.
- User preference are now divided into two sets as:
   1) English (US and UK)
   2) English (India) and Hindi (India)
- User can add/delete languages. Any three languages are allowed.
- User can switch between language.
- Text-to-speech engine will change speech accent as per app language.
- Users below Lollipop required to switch Text-to-speech language manually on the other hand devices from
  Lollipop and higher do not required to switch between Text-to-speech language.
- Added new view which has only three icons per screen.
- User can choose country to insert country code a phone number.
- Hindi serial keyboard
- Glide image loader to load images.
- Added built in package downloader to download language packages.
- Firebase crash reporting, analytics, web storage to store language wise
  icon a packages.
- Added Text-to-speech engine as service in app
- Added default exception handler.
 
### Changed
- Replaced more button feature to scroll in recycler view.
- Changed in the breadcrumb. Now items separated with "/" symbol.
- Moved all strings in app from java code to string.xml
- Moved all string arrays to arrays in string.xml. 
  Converted verbiage string to single JSON.
- About Jellow screen now uses Web view.
- About Jellow credits.
- Updated tutorial image in tutorial screen.
- Renamed multiple java classes and respective xml layout file names, variable name.
- Update in database tables which stores user preferences.
- Added simple view to show border on category icon.

### Removed
- Removed library used to draw border to image icons.

### Fixed
- app stop speaking when screen paused in About Jellow.
- To send feedback user need to send every points.
- App resumes smoothly after user resumes app.
- App by default do not save any blood group.
- UI correction in layouts for various screen size devices.
- App crash in first run on Level 1 screen on new high density phones.


## [1.1.1] - July 25th, 2017
### Changed
- Renamed App name in Google play store from Jellow International to Jellow Communicator

### Fixed
- Nougat users can now download app from Google Play store.


## [1.1.0] - April 3rd, 2017
### Added
- First, public release on the Google play store.
- Simple, intuitive and child friendly icons.
- Organized multilevel structure which enable to keep relevant content together.
- Tapping on expressive/category icon app speak (voice output) about icon.
- Expressive buttons when used in conjunction with category button app speaks sentence.
- Sequences of activities such as Brushing, Toilet, Bathing and more.
- About me category icon is used in conjunction with expressive icons speak about user.
- App remembers user preference. Such as most used category icons used pulled upward 
  in that category
- User preferences are remembered based on number of taps on icon. Also, 
  only specific category have preferences function.
- User can reset user preferences.
- App uses Text-to-speech engine to synthesize the plain text into voice output (speak).
- User can change voice output accent to US or UK.
- User can change speech (voice output) parameters such as speech speed, voice pitch.
- User can change app view either to Pictures and Text or Picture only.
- User can edit profile details.
- Keyboard input to speak text which is not available in app.
- Serial keyboard; this keyboard has letters in alphabetical order.
