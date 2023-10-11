# How to run an app

You can run all the tests (both Unit and Integration) via gradle.  
```agsl
./gradlew test
```
For Integration tests you **need to have docker up and running**, because they use **Testcontainers**.


I didn't provide way to run app locally **on purpose**, because I believe that all the cases, testing, debugging should be done via **tests**.

# Final remarks
I didn't implement all CRUD operations for transactions because I thought that updating transactions doesn't make sense for current requirements.  
I also assumed that users are managed in another service/part of app so I just worked with **userId**.

# Rough estimation on work schedule
### Monday 9/10/23 (2h in total)
Start: 18:00  
End: 19:30  

Start: 21:20  
End: 21:40  

### Wednesday 11/10/23 (2,5h in total)
Start: 15:05
End: 17:22

Total: 4,5h

# Task, see pdf:
[Coding assignment - JAVA [updated March2023].pdf](src%2Fmain%2Fresources%2FCoding%20assignment%20-%20JAVA%20%5Bupdated%20March2023%5D.pdf)