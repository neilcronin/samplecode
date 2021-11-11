# To execute tests
```
./gradlew test
```


# To run the app
From the root directory of the app: 
```
./gradlew run
```

# To call Business hour calculator
```
curl --header "Content-Type: application/json" \
--request GET \
"http://localhost:8666/number-of-business-hours?start=2021-11-07T06:00:00.00Z&end=2021-11-13T23:00:00.00Z"
```

# To call the Age Validator
```
curl --header "Content-Type: application/json" \
--request GET \
"http://localhost:8666/age-validator?dob=2010-01-02"
```
