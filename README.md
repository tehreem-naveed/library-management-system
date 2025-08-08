**FolioVerse – Library Management App - Mobile app**

**FolioVerse** is a modern Android-based library management solution built with **Java** and **XML**, powered by **Firebase** for authentication, data storage, and role management.
It provides two dedicated portals — one for **Admins** to manage library resources and one for **Users** to explore and interact with the library.

The app combines **secure login**, **intuitive navigation**, and a **clean UI** to simplify both book management and user experience.



**Admin Portal**

Admins have full control over library operations:

* Add, edit, and remove books from the catalog
* Manage user accounts (add or delete)
* View complete records of all books
* **Profile Management**:

  * View profile details
  * Edit profile information
  * Change or reset password
  * Logout securely



**User Portal**

Designed for students and general users to easily browse and interact with the library:

* Search for books by keywords
* View detailed book descriptions
* Receive in-app notifications
* Check trending books (sample data shown locally)
* **Profile Management**:

  * View personal details
  * Update profile information
  * Change or reset password
  * Logout securely

**Authentication & User Access**

* **Sign Up** with Email & Password — collects username, gender, and date of birth during registration
* **Login Options**:

  * Standard Email & Password login
  * Google Sign-In for quick access
* **Password Recovery** — users can request a password reset via email
* **Role-Based Redirection** — login automatically detects if the user is an **Admin** or **User** and opens the correct portal
* **Google Sign-In Email Auto-Fetch** — skips manual entry for quicker onboarding


**Profile & Navigation**

* Side navigation drawer with profile header showing **username** and **email**
* Profile page displays: Name, Username, Email, Date of Birth, and Role
* Profile options are **consistent** for both Admin and User roles



**Tech & Tools Used**

* **Frontend:** Java (Android Studio)
* **Authentication:** Firebase Auth
* **Backend:** Firebase Firestore
* **Tools & Libraries:**
   * Android Studio
   * Material UI Components
   * Glide (for image loading) 


**Developed By**

* Tehreem Naveed
* Eman Hamid
* Muntaha Shahab
