Description of Project APIs

Implemented Admin operations module(User registration, Token Generation)

- User registration endpoint
POST localhost:8080/api/v1/user/register
Header - 
Key - Token
value - 3jlj056dndfzlxcx

Body (consumes JSON only)-
{
	"email":"abc@test.com"
}

- Token generation endpoint
POST localhost:8080/api/v1/user/token
Header -
Key - Token
value - 3jlj056dndfzlxcx

Body (consumes JSON only)-
{
	"email":"abc@test.com"
}