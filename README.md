# End Point : 

## _Show Company Info:_
return all users and all deposits of the company

**Curl**: 
```sh
curl --location --request GET 'http://localhost:8080/company'
```

## _Creat new Deposit:_
- Distribute Gift and Meal to Deposit to a user who is part of the company. 
- Check if company balance allow this deposit
- depositType must be GIFT or MEAL

**Curl**:
```sh
curl --location --request POST 'http://localhost:8080/deposit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId":2,
    "companieId":2,
    "amount":200.0,
    "beginDate": "2016-03-16T13:56:39.492",
    "depositType": "MEAL"

}'
```

## _Show User balance:_
show user's meal and Gift Balance

**Curl**:
```sh
curl --location --request GET 'http://localhost:8080/user/2'
```

# Persistence: 

The use of H2 database 
Initiate schema and data in file: 
- `src/main/resources/data.sql`
- `src/main/resources/schema.sql`
