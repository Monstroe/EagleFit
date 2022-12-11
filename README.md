<a name="readme-top"></a>



<!--[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]-->



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/Monstroe/EagleFit">
    <img src="images/logo.png" alt="Logo" width="300" height="300">
  </a>

<h3 align="center">EagleFit</h3>

  <p align="center">
    A fitness logbook that fits on your phone
    <br />
    <!--<a href="https://github.com/Monstroe/EagleFit"><strong>Explore the docs »</strong></a>
    <br />
    <br />-->
    <!--<a href="https://github.com/Monstroe/EagleFit">View Demo</a>
    ·
    <a href="https://github.com/Monstroe/EagleFit/issues">Report Bug</a>
    ·
    <a href="https://github.com/Monstroe/EagleFit/issues">Request Feature</a>-->
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <!--<li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>-->
    <li><a href="#contact">Contact</a></li>
    <!--<li><a href="#acknowledgments">Acknowledgments</a></li>-->
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<!--[![Product Name Screen Shot][product-screenshot]](https://example.com)-->

 According to the Centers for Disease Control and Prevention (CDC), a lack of exercise can be a huge cause of heart disease, obesity, high blood pressure, high blood cholesterol, and type 2 diabetes (Chronic disease fact sheet: Physical inactivity 2022). The CDC also reports that only 23.2% of adults met the Physical Activity guidelines for both aerobic and muscle strengthening activity (Exercise or Physical Activity 2022). This means that most of the population, or at least those who do not exercise, may be at risk of getting the previously stated diseases. It is evident that while many may want to exercise, they do not know the proper way to, which would lead to the failure of habit creation. Often, good information can be hard to come by, paywalls and false information plague the fitness community, and the sheer amount of information can be overwhelming to someone new to exercise. The purpose of EagleFit is to aid and encourage people to exercise in an effort to decrease the percentage of people who perform little to no exercise. The problem this app is trying to solve is the number of health issues linked to lack of exercise and the injuries that can occur during improper exercise routines. The creators hope to decrease the number of yearly cases of these issues with this app.

EagleFit is an app that allows users to create workouts and schedule them for intended workout days. It allows users to select from a large number of exercises and filter through said exercises by the main muscle associated with them. Upon the creation of a workout, the app will allow the user to record the number of sets, and the weight used for each exercise, and will then save this information for future use. This will allow the user to track their progress over time. The app will NOT provide workout plan recommendations nor workout recommendations.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![AndroidStudio][AndroidStudio.com]][AndroidStudio-url]
* [![SQLite][SQLite.com]][SQLite-url]
* [![Java][Java.com]][Java-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

The target operating environment for the application is an android mobile device capable of accessing the internet and downloading applications. While using the app, internet access is NOT required.

The GUI is designed to fit a cellphone screen. Only two different Android devices with different screen resolutions have been tested: the Google Pixel 3a (1080 x 2220) and the Google Pixel 2 (1080 x 1920). However, the app GUI was designed to work on many phone screen resolutions. EagleFit was designed to work only while the phone is held vertically; flipping the phone horizontally has NOT been tested and the GUI may not look the way it was intended. 

As of now, the app has only been tested on the emulators provided by Android Studio.

### Prerequisites

To run the app, [Android Studio][AndroidStudio-url] is required. 

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Monstroe/EagleFit.git
   ```
2. Open the project with [Android Studio][AndroidStudio-url]
3. Set up an [emulator](https://developer.android.com/studio/run/emulator) with [Android Studio][AndroidStudio-url]
4. Click View > Tool Windows > Device File Explorer
5. Move the database file ('EagleFitData') into the emulator. It should be installed in 'data/data/com.example.eaglefit/databases' of the emulator
6. Click the "Run app" play button in the top right (or by pressing Shift+F10)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

EagleFit can store specific personal information about the user, such as name, age, height, and weight. The app also provides a “Muscle Man” chart that is user interactive and allows the user to search for exercises that target specific muscles. The app can also allow the user to create multiple workout plans, with one being set as the active workout plan. A workout plan is a set of workouts; it can have up to 7 workouts each assigned to a day of a week. The active workout plan is the plan that the application will track. Finally, the user can add multiple exercises to each individual workout and specify the number of sets and reps for each exercise they will perform that day. The application uses the device’s internal calendar to keep track of the current day of the week and will automatically place the workout set to be performed on that day in the active workout plan. Any day of the week in workout plans can be set as a “rest day,” in which case the application will not schedule a workout for that day. When the user starts their scheduled workout, a logbook opens, allowing them to input the weight they used and the number of reps they performed for each set of each exercise. The application saves the highest amount of weight they performed and the highest number of reps they performed and displays this information in the form of progress charts.

<!--_For a video demonstration, please refer to the [VideoDemo](https://example.com)_-->

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Project Link: [https://github.com/Monstroe/EagleFit](https://github.com/Monstroe/EagleFit)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
<!--## Acknowledgments

* []()
* []()
* []()

<p align="right">(<a href="#readme-top">back to top</a>)</p>-->



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/Monstroe/EagleFit.svg?style=for-the-badge
[contributors-url]: https://github.com/Monstroe/EagleFit/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Monstroe/EagleFit.svg?style=for-the-badge
[forks-url]: https://github.com/Monstroe/EagleFit/network/members
[stars-shield]: https://img.shields.io/github/stars/Monstroe/EagleFit.svg?style=for-the-badge
[stars-url]: https://github.com/Monstroe/EagleFit/stargazers
[issues-shield]: https://img.shields.io/github/issues/Monstroe/EagleFit.svg?style=for-the-badge
[issues-url]: https://github.com/Monstroe/EagleFit/issues
[license-shield]: https://img.shields.io/github/license/Monstroe/EagleFit.svg?style=for-the-badge
[license-url]: https://github.com/Monstroe/EagleFit/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png

[AndroidStudio.com]: https://img.shields.io/badge/AndroidStudio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white
[AndroidStudio-url]: https://developer.android.com/studio/

[SQLite.com]: https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white
[SQLite-url]: https://www.sqlite.org/index.html

[Java.com]: https://img.shields.io/badge/Java-f89820?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.java.com/en/
