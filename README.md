CubIT Android App [Work in progress 🚧]
==================

This is an Android-part of the team project.

**CubIT Android App** is a fully functional Android app built entirely with Kotlin and Jetpack Compose. 
It follows Android design and development best practices.

The app is currently in early stage development and is not yet available on the Play Store.

# Screenshots

![Screenshot showing My groups screen](docs/photo_CubIT_My_groups.jpg "Screenshot showing For You screen") 
![Screenshot showing Sign up screen](docs/photo_CubIT_sign_up.jpg "Screenshot showing Sign up screen") 
![Screenshot showing Login screen](docs/photo_CubIT_login.jpg "Screenshot showing Login screen")

# Architecture

The **CubIT Android** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture).

# Modularization

The **CubIT Android** app has been fully modularized.

![scheme modularization all](docs/modularization-all.png "scheme modularization all") 

There is the main modules dependency graph below:

![scheme modularization general](docs/modularization_general.png "scheme modularization general") 

We encapsulate all data-logic specific to each domain in data-modules.
But there is troubles when we want to have a database that have relations between domains.
For this purpose we have only DAO-interfaces in data-layers and its realization will be in one separate module - :source:database.
Also, I moved the datastore to the separate module too. This will help us if we want to rewrite this project in KMM.

![scheme modularization data](docs/modularizatoin_data.png "scheme modularization data")
