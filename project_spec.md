# **AgroVision**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)

## App Overview

### Description 

**🌱AgroVision🗺️ is a gardening app that provides the user with location specific regional climate & USDA hardiness zone information which is used to get food plant gardening options (vegetables, herbs, fruits), growth care information, etc.**

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:** 
- **Mobile:** The purpose of mobile apps has always been convenience for the user--the ease of having all the information you will ever need literally at the tip of your fingers. We want this app to be our gardeners mobile hub for all information related to their future food forest.
- **Story:** One of our goals when developing this app will be to place emphasis on creating a user-friendly experience, in which a new aspiring gardener can easily take control of their diet from their backyard or balcony garden will be able to easily get started. 
- **Market:** The intended audience for this app is not meant to be extremely niche, and only directed for people who are existing gardeners, farmers, botanists, and other related professionals. The focus of this app is regular food gardening that anyone can start in their home, which means the market is specific but not too narrow.
- **Habit:** The aim of this app is certainly not be to indefinitely latch onto the users' attention but rather for quick access to gardening information and record keeping purposes.
- **Scope:** The scope for this app is still in the process of being developed and it has definitely been narrowed down greatly since the conception of our idea, yet it still remains an exciting idea that we are looking forward to bringing to life. Because of the potential usage of 3 APIs that may or may not be well-documented simultaneously and our beginner level experience with Kotlin app development, this app is likely to be technically challenging even with an extremely stripped down version of the app. We will continue to adjust our objectives as we go but we have a clearly defined overarching objective for location-specific food gardening information.


## Product Spec

### 1. User Features (Required and Optional)

Required Features:

- App gathers location information on user
    - Location information can be gathered through user-inputted zip code or automatically obtaining geolocation information 
- App uses said information to obtain location specific food gardening options
    - USDA Plant Hardiness Zones and/or Koppen Climate Classification
- App provides some plant specific growth/care details

Stretch Features:

- Edible plant options sorted into categories
- User should be able to search for a plant
- Filter information? 
- User sign-in 
- User can save plants (and their care information) for future reference on their account

### 2. Chosen API(s)

- **GET USDA Hardiness Zone and GET Classification**
  - App uses said information to obtain location specific food gardening options and growth/care details
- **GET Plant List (Perenual or Permapeople API)**
    - App uses said information to obtain location specific food gardening options
- **GET Plant Details (Perenual or Permapeople API)**
    - App provides some plant specific growth/care details

### 3. User Interaction

Required Feature

- **GET USDA Hardiness Zone and GET Classification**
  - User inputs zipcode or agree to location tracking
- **GET Plant List (Permapeople API)**
    - User browses through list of plants
- **GET Plant Details (Permapeople API)**
    - App provides some plant specific growth/care details

## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->
<img src="https://user-images.githubusercontent.com/101878146/233756216-704f4e24-5767-4ab1-8f6f-091c3355ddc9.jpg" width=300>
Log In


<img src="https://user-images.githubusercontent.com/101878146/233756299-3765f7a4-82d6-496e-ab78-6c8382086b15.jpg" width=300>
Dash Boaard


<img src="https://user-images.githubusercontent.com/101878146/233756311-62c0eeaf-c4bd-4aae-a0cb-08300cf30546.jpg" width=300>
Dash Board #2


<img src="https://user-images.githubusercontent.com/101878146/233756328-2c8ce7ce-1c79-466f-a7b3-a6fb1f527835.jpg" width=300>
Explore


## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  


Required Features:

- App gathers location information on user
    - Location information can be gathered through user-inputted zip code or automatically obtaining geolocation information 
- App uses said information to obtain location specific food gardening options
    - USDA Plant Hardiness Zones and/or Koppen Climate Classification
- App provides some plant specific growth/care details


For Milestone 2, include **2+ GIFs** of the build process here!

<img src='https://i.imgur.com/6ouCmRF.gif' title='Video Demo' width='' alt='Video Demo 1' />
<img src='https://i.imgur.com/GMj9wzd.gif' title='Video Demo' width='' alt='Video Demo 2' />

GIF created with **ScreenToGIF**

## License

Copyright **2023** **Alisha Jafry**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
