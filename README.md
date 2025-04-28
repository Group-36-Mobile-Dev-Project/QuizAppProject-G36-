# QuizAppProject-G36-
A trivia or quiz app where users can answer questions on various topics.

## Installation 



Clone and run locally:

```bash
git clone https://github.com/Group-36-Mobile-Dev-Project/QuizAppProject-G36-.git
run app on android studio
```

## Requirements

<details>
<summary>Click to expand</summary>

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
</details>

## Function Preview

### Login & Register 
<sup><sub>LoginScreen.kt & RegisterScreen.kt</sub></sup>

![studio64_FNjqOtCdHs](https://github.com/user-attachments/assets/f8b7b474-e42a-472c-b0b4-ed43be7e4da8)

### Profile
<sup><sub>ProfileScreen.kt</sub></sup>

![studio64_zqqg1Mumf2](https://github.com/user-attachments/assets/93c6a4ba-c307-48e4-882d-f3ecfdff091b)

### Categories
<sup><sub>HomeScreen.kt</sub></sup>

![image](https://github.com/user-attachments/assets/d170074c-98d7-4c95-8a3d-fc2c4a1896d2)

### Quizzes
<sup><sub>QuizScreen.kt</sub></sup>

<details>
  <summary>General</summary>
![studio64_EzK33cGYsV](https://github.com/user-attachments/assets/797fe237-3b22-4a34-a62b-1d1bbdeac173)
</details>

<details>  
  <summary>Sports</summary>
![studio64_bUwkZIyVPT](https://github.com/user-attachments/assets/260df55f-d23d-45fd-a59a-1122758380e2)
</details>

<details>
  <summary>History</summary>
![studio64_VHAnYJnC1T](https://github.com/user-attachments/assets/03db4dd0-614b-4e20-8d22-f80b09d288aa)
</details>

<details>
  <summary>Movies</summary>
![studio64_9n9RQumfy1](https://github.com/user-attachments/assets/c8dc5c0e-84ec-4df9-8f33-0d52c48f09b7)
</details>

<details>
  <summary>Video Games</summary>
![studio64_xEQScOGdJQ](https://github.com/user-attachments/assets/7c1b6649-f1c3-467c-8967-30601984963a)
</details>






