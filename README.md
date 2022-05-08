# Order-Management-System


Here I use some android jetpack libraries in this project

1. Room Databse - To Strore Data in Databse
2. Dagger-Hilt - for Dependency Injection
3. Navigation Component - for Navigation
4. Safe Args - for Passing the data between fragments
5. View Binding - for Binding Views


When ever you opend the app you will see a login activity it required email and password to use this application 

- after giving your crediantials you will see main activity, in it you will see Order Fragment where you can see all your orders in it
- clicking the fab button you will navigate to Add order fragment, there you can add your Customer Details like name, id, order details
- after entering all the details by using check mark on the action bar you will save that customer data.
- you will able to see that list inside the order fragment
- here if you want to edit that customer data by tapping directly on that view, you will able to navigate to update fragment, here you can edit your previous order
- once you finish your edits then by pressing the save icon on the action bar that order will be updated
- if you want to delete that order you can delete by using delete icon on the action bar itself.
- inside order list we have one action bar icon, the purpose of it is if we want to delete all orders at once here we can do that using that action bar item.


Some more info regarding project

- I use single activity with multiple fragments here
- And also used MVVM architecture 
- Used Kotlin as main language 
- And I also didn't use ListView instead of it I use RecyclerView 
- And I did't use Data Binding here, instead of setting data directly inside XML file I do that inside my adapter class.

