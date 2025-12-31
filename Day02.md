# 🔹 API Specialist – Day 02

## HTTP Request Anatomy
----------------

## 1️⃣ What an HTTP Request Really Is
An HTTP request is not just a call — it is a self-describing message.
> A server must be able to understand a request without knowing anything about previous requests.

That is why request anatomy matters.

Every HTTP request has 3 core parts:
1. Request Line
2. Headers
3. Optional Body

-----------

## 2️⃣ Request Line (Intent Declaration)

Format:

<METHOD> <URI> <HTTP-VERSION>


Example:

GET /users/42 HTTP/1.1

What the request line communicates:
- METHOD → What action is intended
- URI → Which resource
- HTTP version → Protocol rules

Expert insight:
- The request line defines intent, not execution
- The server is free to reject, redirect, or transform

📌 Interview Truth
> Methods describe semantics, not implementation.


-----------------


## 3️⃣ Headers vs Body (Metadata vs Data)
Headers

Headers carry metadata about:
- Authentication
- Content negotiation
- Caching
- Client identity
- Transport hints
- Headers are not business data.

Example:

Authorization: Bearer eyJhbGciOi...

User-Agent: Chrome/143.0

Body

The body carries the representation of data:
- JSON
- XML
- Form data
- Binary

Example:

{

  "name": "Munna",
  
  "role": "API Engineer"
  
}

Expert Rule:
> Headers explain the message.

> Body is the message.


---------------

## 4️⃣ Content-Type vs Accept (Very Important)

This is a classic interview trap.

Content-Type

👉 What I am sending

Content-Type: application/json


Meaning:
> “The body of THIS request is JSON”

Accept

👉 What I want back

Accept: application/json


Meaning:

“I can understand JSON responses”

Expert Insight:
- Content-Type → request body format
- Accept → response format expectation
- Servers may return 406 Not Acceptable

📌 Most devs misuse this. Senior devs don’t.

--------------


## 5️⃣ Critical Headers (Deep Meaning)
🔹 Host

Host: api.company.com

- Mandatory in HTTP/1.1
- Enables virtual hosting
- One IP → many domains

📌 Without Host, modern servers break.

🔹 User-Agent

User-Agent: Mozilla/5.0

- Identifies client software
- Used for:
   - Analytics
   - Feature toggles
   - Rate limiting
   - Bot detection

📌 Servers do not trust it, but still use it.

🔹 Authorization

Authorization: Bearer <token>

- Carries credentials
- HTTP itself does not validate auth
- Purely a transport container

📌 HTTP does NOT know:
- Who the user is
- Whether token is valid
- 
That’s application logic.


--------------


## 6️⃣ Manual HTTP Requests (Text Only)

✅ Request 1: GET user

GET /users/42 HTTP/1.1

Host: api.example.com

Accept: application/json

Authorization: Bearer abc.def.xyz

✅ Request 2: Create user

POST /users HTTP/1.1

Host: api.example.com

Content-Type: application/json

Accept: application/json

{

  "name": "Munna",
  
  "email": "munna@example.com"
  
}


✅ Request 3: Update user

PUT /users/42 HTTP/1.1

Host: api.example.com

Content-Type: application/json

Authorization: Bearer abc.def.xyz

{

  "role": "API Specialist"
  
}


📌 These requests work without frameworks.

That’s the power of HTTP.


---------------

## 7️⃣ Interview Questions (Senior-Level)

❓ Why is Host mandatory in HTTP/1.1?

👉 To support multiple domains on one IP (virtual hosting).

❓ Difference between Content-Type and Accept?

👉 Content-Type = request body format

👉 Accept = preferred response format

❓ Can GET have a body?

👉 Spec allows it, servers usually ignore it.

Never rely on it.

❓ Why Authorization is a header, not body?

👉 Credentials must be:

Metadata

Easily intercepted by middleware

Independent of payload format

❓ Does HTTP handle authentication?

👉 ❌ No

HTTP only transports credentials.

Validation is application responsibility.

Final Expert Takeaway

HTTP requests are self-contained contracts, not function calls.

Understanding request anatomy properly:

Prevents bad API design

Improves security

Makes debugging trivial

Separates protocol from business logic

This is the difference between:

Writing APIs ❌

Engineering APIs ✅








