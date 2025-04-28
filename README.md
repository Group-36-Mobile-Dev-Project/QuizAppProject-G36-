# Beetlequiz

![beetlequiz](https://github.com/user-attachments/assets/9336d19e-98b6-4245-b444-a10c488855c4)

##  Table of Contents

1. [Installation](#installation)
2. [Requirements](#requirements)
3. [Function Preview](#function-preview)
   - [Login & Register](#login--register)
   - [Profile](#profile)
   - [Categories](#categories)
   - [Quizzes](#quizzes)
   - [Results](#results)
4. [Contribution](#quizzes)
5. [License](#quizzes)
6. [Author](#quizzes)

## Introduction

**Beetlequiz** is a trivia app, where users can answer questions on various topics.
Built with modern Android development practices, the app offers:
- **Category Selection**: Choosing from diverse topics like General Knowledge, Sports, Movies, and more
- **Timed Challenges**: 15-second per question limit with visual countdown
- **Progress Tracking**: Real-time score updates and final results breakdown
- **Secure Authentication**: Email/password signup/login with Firebase Auth
- **Profile Management**: View account details, reset password, or delete account
- **Dynamic Content**: Firestore-powered questions with image support

## Installation 



Clone and run locally:

```bash
git clone https://github.com/Group-36-Mobile-Dev-Project/QuizAppProject-G36-.git
run app on android studio
```

## Requirements

-google-services.json from Google Firestore in ```QuizAppProject-G36-\app``` folder

-to display questions, questions on firestore should be structured:

```bash
├── questions:
│   ├── document:
│   │   │   ├── category:
│   │   │   ├── correctAnswer:
│   │   │   ├── imageUrl:
│   │   │   ├── options:
│   │   │     └── 0/
│   │   │     └── 1/
│   │   │     └── 2/
│   │   │     └── 3/
│   │   │   └── question:
```
-to create account and login, structure should be:
```bash
├── users:
│   ├── document:
│   │   │   ├── email:
│   │   │   ├── username:
```

## Function Preview

### Login & Register 
<sup><sub>LoginScreen.kt & RegisterScreen.kt</sub></sup>

**Features**
- Data stored in Firestore
- Username appears on profile screen
---

<img src= "https://github.com/user-attachments/assets/f8b7b474-e42a-472c-b0b4-ed43be7e4da8" width ="400">

---
### Profile
<sup><sub>ProfileScreen.kt</sub></sup>

**Features**
- Account deletion
- Password resetting
- Logging out
- Account creation date
- Last sign in date
---

<img src= "https://github.com/user-attachments/assets/93c6a4ba-c307-48e4-882d-f3ecfdff091b" width ="400">

---
### Categories
<sup><sub>HomeScreen.kt</sub></sup>

Features
- Selecting category
- Profile access

<img src= "https://github.com/user-attachments/assets/d170074c-98d7-4c95-8a3d-fc2c4a1896d2" width ="400">

---
### Quizzes
<sup><sub>QuizScreen.kt</sub></sup>

**Features**
- Category color based on quiz
- Timer
- Correct & Incorrect answers
- Images for every question


General
  
  <img src= "https://github.com/user-attachments/assets/797fe237-3b22-4a34-a62b-1d1bbdeac173" width ="400">

<details>  
  <summary>Sports</summary>
  
  <img src= "https://github.com/user-attachments/assets/260df55f-d23d-45fd-a59a-1122758380e2" width ="400">
</details>

<details>
  <summary>History</summary>
  
  <img src= "https://github.com/user-attachments/assets/03db4dd0-614b-4e20-8d22-f80b09d288aa" width ="400">
</details>

<details>
  <summary>Movies</summary>
  
  <img src= "https://github.com/user-attachments/assets/c8dc5c0e-84ec-4df9-8f33-0d52c48f09b7" width ="400">
</details>

<details>
  <summary>Video Games</summary>
  
  <img src= "https://github.com/user-attachments/assets/7c1b6649-f1c3-467c-8967-30601984963a" width ="400">
</details>

---
### Results
<sup><sub>ResultsScreen.kt</sub></sup>

**Features**
- Sharing score with others
- Retrying quiz
- Encouraging statements!

Less than 5 Score
  
<img src= "https://github.com/user-attachments/assets/e53b3d7f-5e89-468c-9f5c-00e738cab899" width ="400">


<details>
  <summary>6 or 7 Score</summary>
  
<img src= "https://github.com/user-attachments/assets/9798c5a0-96bb-4915-a6da-368d7c1127ce" width ="400">
</details>

<details>
  <summary>8 or 9 Score</summary>
  
<img src= "https://github.com/user-attachments/assets/1530ef95-c096-4b2b-8b20-668a4620e76d" width ="400">
</details>

<details>
  <summary>10 Score</summary>
  
<img src= "https://github.com/user-attachments/assets/e3f31865-a2ef-4137-ba26-83971712f4c2" width ="400">
</details>


---
## Contribution

Contribution is possible and welcome as well!

If you want to contribute, please read this very nice tutorial written by firstcontributions:
- https://github.com/firstcontributions/first-contributions

Afterwards, you're free to contribute as you wish!

---

## License

This project is licensed under the MIT License.

## Author

Project was possible with the contributions from:
- Ville Kahelin
- Joona Muikku
- Ankit Rawat
