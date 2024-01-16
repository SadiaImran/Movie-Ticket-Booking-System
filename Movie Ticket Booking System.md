![](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.001.png)

**DEPARTMENT OF COMPUTER SCIENCE**

***SEMESTER PROJECT*** 

***MOVIE TICKET BOOKING SYSTEM***


|<h1><a name="_toc1391547912"></a><a name="_toc1317971950"></a><a name="_toc1789738602"></a><a name="_toc635235670"></a><a name="_toc1520366051"></a><a name="_toc1436479260"></a><a name="_toc1942581847"></a><a name="_toc2001120821"></a><a name="_toc154697967"></a><a name="_toc154699605"></a><a name="_toc154700952"></a>**SUBMITTED TO**</h1>|<h1><a name="_toc2146894413"></a><a name="_toc2008977937"></a><a name="_toc402556395"></a><a name="_toc1300913570"></a><a name="_toc1588940249"></a><a name="_toc499073483"></a><a name="_toc1070519772"></a><a name="_toc1310174024"></a><a name="_toc154697968"></a><a name="_toc154699606"></a><a name="_toc154700953"></a>**DR. RUBINA ADNAN**</h1>|
| :- | :- |
|<h1><a name="_toc28631102"></a><a name="_toc1220265568"></a><a name="_toc849018682"></a><a name="_toc1974205132"></a><a name="_toc1731985146"></a><a name="_toc1753309499"></a><a name="_toc1781050962"></a><a name="_toc1497377395"></a><a name="_toc154697969"></a><a name="_toc154699607"></a><a name="_toc154700954"></a>**SUBMITTED BY**</h1>|<p><h1><a name="_toc2067531235"></a><a name="_toc1905779646"></a><a name="_toc1041576654"></a><a name="_toc397714576"></a><a name="_toc746997668"></a><a name="_toc1233913006"></a><a name="_toc787017546"></a><a name="_toc11576231"></a><a name="_toc154697970"></a><a name="_toc154699608"></a><a name="_toc154700955"></a>**RABEEA CHUGHTAI   SP22-BCS-079**</h1></p><p><h1><a name="_toc1630804199"></a><a name="_toc1503424484"></a><a name="_toc214529024"></a><a name="_toc1376612939"></a><a name="_toc238722666"></a><a name="_toc445724626"></a><a name="_toc901596960"></a><a name="_toc1793912590"></a><a name="_toc154697971"></a><a name="_toc154699609"></a><a name="_toc154700956"></a>**SADIA IMRAN               SP22-BCS-087**</h1></p>|
|<h1><a name="_toc559211971"></a><a name="_toc253677825"></a><a name="_toc2104271698"></a><a name="_toc794812595"></a><a name="_toc333474176"></a><a name="_toc1033760507"></a><a name="_toc940620321"></a><a name="_toc1520464245"></a><a name="_toc154697972"></a><a name="_toc154699610"></a><a name="_toc154700957"></a>**COURSE TITLE**</h1>|<h1><a name="_toc1450850930"></a><a name="_toc1225766045"></a><a name="_toc411395432"></a><a name="_toc755280746"></a><a name="_toc1932682408"></a><a name="_toc671465274"></a><a name="_toc952789412"></a><a name="_toc1186127817"></a><a name="_toc154697973"></a><a name="_toc154699611"></a><a name="_toc154700958"></a>**CSC270 - DATABASE SYSTEM**</h1>|
|<h1><a name="_toc532498436"></a><a name="_toc1455178610"></a><a name="_toc1451421673"></a><a name="_toc655282124"></a><a name="_toc191388278"></a><a name="_toc17218697"></a><a name="_toc627168033"></a><a name="_toc961735010"></a><a name="_toc154697974"></a><a name="_toc154699612"></a><a name="_toc154700959"></a>**DATED**</h1>|<h1><a name="_toc154697975"></a><a name="_toc154699613"></a><a name="_toc154700960"></a>**DEC 29 2023**</h1>|
#
#


# Table of Contents
#
[**Introduction**	4](#_toc154700961)

[**Description**	4](#_toc154700962)

[**Entities and Attributes**	5](#_toc154700963)

[**1.**	**MOVIES**	5](#_toc154700964)

`    `[**2.**	**GENRE**	6](#_toc154700972)

[**3.**	**MOVIEGENRE**	6](#_toc154700975)

[**4.**	**SHOW**	7](#_toc154700978)

[**5.**	**SEAT**	7](#_toc154700984)

[**6.**	**BOOKING**	8](#_toc154700989)

[**7.**	**CUSTOMER**	9](#_toc154700995)

[**Modules**	9](#_toc154701000)

[**I.**	**ADMIN**	9](#_toc154701001)

[**II.**	**CUSTOMER**	10](#_toc154701008)

[**Functional Requirements**	12](#_toc154701013)

[·	**User Registration and Authentication**	12](#_toc154701014)

[·	**Movie Selection and Filtering**	12](#_toc154701015)

[·	**Reservation Process**	13](#_toc154701016)

[·	**User Profile Management**	13](#_toc154701017)

[·	**Administrator Control:**	13](#_toc154701018)

[·	**Booking Confirmation**	13](#_toc154701019)

[**Non-Functional Requirements:**	13](#_toc154701020)

[·	**Performance**	13](#_toc154701021)

[·	**Reliability**	13](#_toc154701022)

[**Tools and Technology:**	13](#_toc154701023)

[·	**Tools:**	14](#_toc154701024)

[·	**Technology:**	14](#_toc154701025)


#


# <a name="_toc154700961"></a>**Introduction**

Online Movie Ticket Booking System, a user-friendly platform lets you effortlessly reserve and purchase movie tickets online. With a visually appealing interface and intuitive filters, you can easily find and book your favorite movies. User accounts ensure a personalized experience, and administrators have the power to manage the movie database.

# <a name="_toc154700962"></a>**Description**

Online Movie Ticket Booking System is a comprehensive and user-friendly web application designed to streamline the process of reserving and purchasing movie tickets online. Upon visiting the website, users are greeted with a visually appealing home dashboard showcasing an extensive list of movies currently available in our database. With a user-friendly filter sidebar, patrons can tailor their movie selections based on personal preferences, including PG ratings, duration, and genre. 

To engage with our system fully, users are required to log in to their personalized accounts. For those without an account, a seamless registration process is available, ensuring accessibility for all. Once logged in, users can easily navigate through the movie listings, select their preferred film, and transition to a dedicated movie page providing details about the movie. The reservation process involves a simple 'Book Me’ click, redirecting users to a Booking page where they can effortlessly reserve a specific number of seats for a chosen showtime. Upon successful completion, the reservation details are promptly displayed on the user's profile. Our administrators wield control over the system, enabling them to add or delete movies. Users can access additional features like password reset and a feedback forum for sharing valuable insights on the website experience. Our project aims to create a movie ticket booking system that combines efficiency, accessibility, and user-centric features for a seamless and enjoyable movie-going experience.

# <a name="_toc154700963"></a>**Entities and Attributes**

1. ## <a name="_toc154700964"></a>**MOVIES**
- ### <a name="_toc154697980"></a><a name="_toc154699618"></a><a name="_toc154700965"></a>*MOVIE\_ID (Primary Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute uniquely identifying each movie. 
- ### <a name="_toc154697981"></a><a name="_toc154699619"></a><a name="_toc154700966"></a>*DURATION:*
Data Type: NUMBER (1,0)

Represents the duration of the movie
- ### <a name="_toc154697982"></a><a name="_toc154699620"></a><a name="_toc154700967"></a>*TITLE:*
Data Type: VARCHAR2(25 BYTE)

Stores the title of the movie.
- ### <a name="_toc154697983"></a><a name="_toc154699621"></a><a name="_toc154700968"></a>*PG\_RATING:*
Data Type: NUMBER (2,1)

Represents the rating of the movie, particularly the Parental Guidance (PG) rating. 
- ### <a name="_toc154697984"></a><a name="_toc154699622"></a><a name="_toc154700969"></a>*INDUSTRY:*
Data Type: VARCHAR2(255 BYTE)

Indicates the industry associated with the movie, such as Hollywood or Bollywood. 
- ### <a name="_toc154697985"></a><a name="_toc154699623"></a><a name="_toc154700970"></a>*PRICE:*
Data Type: NUMBER (4,0)

Represents the price or cost associated with the movie.
- ### <a name="_toc154697986"></a><a name="_toc154699624"></a><a name="_toc154700971"></a>*STORY:*
Data Type: VARCHAR2(80 BYTE)

Describes the storyline or plot of the movie.
1. # <a name="_toc154697988"></a><a name="_toc154699626"></a><a name="_toc154700972"></a>**GENRE**
- ### <a name="_toc154700973"></a>*GENRE\_ID (Primary Key):*
  Data Type: VARCHAR2(4 BYTE)

This attribute represents the unique identifier for each genre in the "genres" table. 
- ### <a name="_toc154697989"></a><a name="_toc154699627"></a><a name="_toc154700974"></a>*GENRE\_NAME:*
  Data Type: VARCHAR2(50 BYTE)

Stores the name or label of the genre. 
1. ## <a name="_toc154697990"></a><a name="_toc154700975"></a>**MOVIEGENRE**
- ### <a name="_toc154697991"></a><a name="_toc154699629"></a><a name="_toc154700976"></a>*MOVIE\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "MovieGenre" table and the "movies" table, allowing you to associate genres with specific movies. 
- ### <a name="_toc154697992"></a><a name="_toc154699630"></a><a name="_toc154700977"></a>*GENRE\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "MovieGenre" table and the "genres" table, allowing you to associate movies with specific genres. 
1. ## <a name="_toc154700978"></a>**SHOW**
- ### <a name="_toc154697994"></a><a name="_toc154699632"></a><a name="_toc154700979"></a>*SHOW\_ID (Primary Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute uniquely identifying each show. 
- ### <a name="_toc154697995"></a><a name="_toc154699633"></a><a name="_toc154700980"></a>*START\_TIME:*
Data Type: TIMESTAMP (6)

Represents the start time of the show. 
- ### <a name="_toc154697996"></a><a name="_toc154699634"></a><a name="_toc154700981"></a>*END\_TIME:*
Data Type: TIMESTAMP (6)

Represents the end time of the show. 
- ### <a name="_toc154697997"></a><a name="_toc154699635"></a><a name="_toc154700982"></a>*MOVIE\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "shows" table and the "movies" table, indicating which movie is being shown during the specified time. 
- ### <a name="_toc154697998"></a><a name="_toc154699636"></a><a name="_toc154700983"></a>*GENRE\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "shows" table and the "genres" table, indicating the genre associated with the movie being shown. 
1. ## <a name="_toc154700984"></a>**SEAT**
- ### <a name="_toc154698000"></a><a name="_toc154699638"></a><a name="_toc154700985"></a>*SEAT\_NUMBER:*
Data Type: VARCHAR2(10 BYTE)

Represents the seat number or identifier for each seat in the venue.
- ### <a name="_toc154698001"></a><a name="_toc154699639"></a><a name="_toc154700986"></a>*STATUS:*
Data Type: VARCHAR2(20 BYTE)

Indicates the status of the seat, such as "available," "reserved," or "booked." 
- ### <a name="_toc154698002"></a><a name="_toc154699640"></a><a name="_toc154700987"></a>*BOOKING\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "seats" table and the table representing bookings, indicating which booking is associated with a particular seat. 
- ### <a name="_toc154698003"></a><a name="_toc154699641"></a><a name="_toc154700988"></a>*SHOW\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "seats" table and the "shows" table, indicating the show during which the seat is located. 

1. ## <a name="_toc154700989"></a>**BOOKING**
- ### <a name="_toc154698005"></a><a name="_toc154699643"></a><a name="_toc154700990"></a>*BOOKING\_ID (Primary Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute uniquely identifying each booking. 
- ### <a name="_toc154698006"></a><a name="_toc154699644"></a><a name="_toc154700991"></a>*NOOFSEATS:*
Data Type: NUMBER (3,0)

Represents the number of seats booked in the booking. 
- ### <a name="_toc154698007"></a><a name="_toc154699645"></a><a name="_toc154700992"></a>*SHOW\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "booking" table and the "shows" table, indicating the show for which the seats are booked.
- ### <a name="_toc154698008"></a><a name="_toc154699646"></a><a name="_toc154700993"></a>*CUSTOMER\_ID (Foreign Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute establishes a link between the "booking" table and the table representing customers, indicating the customer who made the booking. 
- ### <a name="_toc154698009"></a><a name="_toc154699647"></a><a name="_toc154700994"></a>*PRICE:*
Data Type: NUMBER (4,0)

Represents the total price associated with the booking. 
1. ## <a name="_toc154700995"></a>**CUSTOMER**
- ### <a name="_toc154698011"></a><a name="_toc154699649"></a><a name="_toc154700996"></a>*CUSTOMER\_ID (Primary Key):*
Data Type: VARCHAR2(4 BYTE)

This attribute, uniquely identifying each customer. 
- ### <a name="_toc154698012"></a><a name="_toc154699650"></a><a name="_toc154700997"></a>*CUSTOMER\_NAME:*
Data Type: VARCHAR2(25 BYTE)

Stores the name of the customer. 
- ## <a name="_toc154698013"></a><a name="_toc154699651"></a><a name="_toc154700998"></a>*CUSTOMER\_EMAIL:*
Data Type: VARCHAR2(40 BYTE)

Represents the email address of the customer. 
- ### <a name="_toc154698014"></a><a name="_toc154699652"></a><a name="_toc154700999"></a>*PASSWORD:*
Data Type: VARCHAR2(20 BYTE)

Stores the password associated with the customer's account. 
# <a name="_toc154701000"></a>**Modules**

1. ## <a name="_toc154701001"></a>**ADMIN**
   The admin module facilitates streamlined data management, offering functions for tasks like deleting, updating, inserting, and searching records.
- ### <a name="_toc154698017"></a><a name="_toc154699655"></a><a name="_toc154701002"></a>**Display Movies**
The display function retrieves and displays all records from the "movies" table, offering a comprehensive overview of the available movie data. This function is useful for getting a quick snapshot of the existing movies in the system.
- ### <a name="_toc154698018"></a><a name="_toc154699656"></a><a name="_toc154701003"></a>**Insert**
The insert function allows the admin to add new data to the database. By selecting a table (Movies, Genre, Show, or Movies&Genre), the admin can input information for insertion. This function is crucial for keeping the database up to date with new entries.
- ### <a name="_toc154698019"></a><a name="_toc154699657"></a><a name="_toc154701004"></a>**Delete**
The delete function allows the admin to remove data from the database based on user selection. It presents a panel where the admin can choose the type of data (Movie, Genre, Show, or Booking) to delete. The selected option triggers the appropriate actions for data deletion.
- ### <a name="_toc154698020"></a><a name="_toc154699658"></a><a name="_toc154701005"></a>**Update**
The update function enables the admin to modify existing data in the database. By selecting a table (Show, Movies, or Genre), the admin is prompted to make updates to the chosen data set. This function facilitates the dynamic management of information within the system.
- ### <a name="_toc154698021"></a><a name="_toc154699659"></a><a name="_toc154701006"></a>**Search**
The search function provides the admin with the capability to find specific data in the database. In this case, the admin can search for movies by entering the movie name. The search results help in quickly locating and retrieving relevant information.

1. ## <a name="_toc154701008"></a>**CUSTOMER**
   The customer-oriented functions in the code provide a user-friendly platform for exploring and booking movie tickets.
- ### <a name="_toc154698024"></a><a name="_toc154699662"></a><a name="_toc154701009"></a>**Display Movies**
Triggered by the "Display" button, this function retrieves and displays all records from the "movies" table. It provides customers with a comprehensive overview of available movies, facilitating quick access to essential information.
- ### <a name="_toc154698025"></a><a name="_toc154699663"></a><a name="_toc154701010"></a>**Search Movies**
Activated by the "Search" button, the search function allows customers to find specific movies by entering the movie name. The search results help customers quickly locate and access detailed information about a particular movie.
- ### <a name="_toc154698026"></a><a name="_toc154699664"></a><a name="_toc154701011"></a>**Filter Movies**
The filter function, initiated by the "Filter" button, empowers customers to refine their movie selections based on various criteria. Users can choose from a range of filters such as Duration, Industry, Ratings, Price, and Genre. 

- **Duration Filter**

Customers can select the desired movie duration (e.g., 1 hour, 2 hours) using radio buttons, narrowing down the list of movies based on their preferred duration.

- **Industry Filter**

Users can filter movies by industry (e.g., Bollywood, Hollywood, Lollywood) using radio buttons, enabling a more targeted search based on industry preferences.

- **Genre Filter**

The genre filter allows customers to refine their movie choices based on specific genres. This feature enhances the browsing experience, catering to individual taste and preferences.

- **Ratings Filter**

Customers can filter movies by ratings using a slider, enabling a dynamic search based on preferred rating ranges. 

- **Price Filter**

The price filter empowers customers to dynamically adjust movie prices based on their preferences. Movies meeting the specified price criteria are instantly displayed.
- ### <a name="_toc154698027"></a><a name="_toc154699665"></a><a name="_toc154701012"></a>**Booking**
- **Choose Movie and Show Timing**

Customers can select a movie from a database-generated list, and the system updates show timings for that movie, allowing them to choose their preferred time.

- **Pick Seats**

The form displays available seats based on the selected show timing. Customers can select multiple seats using checkboxes presented in a grid layout.

- **Confirm Booking and Generate Receipt**

After choosing seats, customers can click the "Book Now" button to confirm their booking. Upon confirming the booking, a receipt is generated, displaying essential. Customers can review the receipt before closing the booking process.

# <a name="_toc154701013"></a>**Functional Requirements**

- ## <a name="_toc154701014"></a>**User Registration and Authentication**
Users should be able to create accounts with unique usernames and passwords. The system should authenticate users during login to ensure secure access.
- ## <a name="_toc154701015"></a>**Movie Selection and Filtering**
Users must be able to browse a comprehensive movie catalog with details.

Filtering options, such as genre, duration, and PG rating, should be available for personalized movie selection.
- ## <a name="_toc154701016"></a>**Reservation Process**
The system should facilitate the booking of seats for a selected movie and showtime. Users must be able to view and choose available seats in real-time.
- ## <a name="_toc154701017"></a>**User Profile Management**
Users should have the ability to view and manage their profile information. The system must display a history of previous reservations for reference.
- ## <a name="_toc154701018"></a>**Administrator Control:**
Administrators should have access to functionalities for adding, updating, and deleting movies from the database. The system should provide tools for administrators to manage user accounts.
- ## <a name="_toc154701019"></a>**Booking Confirmation**
Users should receive confirmation of their booking, including details like movie title, showtime, and seat numbers.

# <a name="_toc154701020"></a>**Non-Functional Requirements:**

- ## <a name="_toc154701021"></a>**Performance**
The system must efficiently handle concurrent user requests, ensuring responsive interactions even during peak hours, and have a response time of 2 seconds for user interactions.
- ## <a name="_toc154701022"></a>**Reliability**
  The application should guarantee 99.5% uptime for user availability.

# <a name="_toc154701023"></a>**Tools and Technology:**

- ## <a name="_toc154701024"></a>**Tools:**
***SQL Developer**:* DBMS

***MongoDB Compass*:** Non-Relational Database

***IntelliJ IDEA:*** IDE
- ## <a name="_toc154701025"></a>**Technology:**
***JAVA:*** Programming Language

***SQL***: Query Language

***MongoDB:*** NoSQL Query Language






# <a name="_toc154737086"></a>**Graphical User Interface**

![A movie theater with red seats

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.002.png)







**Login & Signup** 

![A screen shot of a login form

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.003.png)












![A screen shot of a login screen

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.004.png)







![A screenshot of a login page

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.005.png)







**Admin Panel**

![A screen shot of a computer

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.006.png)







![A screen shot of a computer

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.007.png)








![A screenshot of a computer

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.008.png)









![A screenshot of a computer

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.009.png)







**Customer Panel**

![A screenshot of a computer

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.010.png)





![A screenshot of a computer

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.011.png)

![A screenshot of a computer screen

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.012.png)
















# ![A diagram of a company

Description automatically generated](Aspose.Words.9e819bf3-f49e-4daf-8e59-560d94d1cf58.013.jpeg)**ER Diagram**
