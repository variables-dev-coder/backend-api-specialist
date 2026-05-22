# Day 6 – HTTP Status Codes (Deep, Practical, Expert)

## Why Status Codes Matter (Beyond Theory)

Status codes are not “just numbers”.

They communicate:
- Who is at fault (client vs server)
- What the client should do next (retry, fix request, re-authenticate)
- Whether automation is safe (retries, caching, fallbacks)
- System health (SRE & monitoring rely on them)

Bad status codes =

❌ broken retries

❌ wrong alerts

❌ confused frontend

❌ angry interviewers

---

Status Code Families (Mental Model)

| Class   | Meaning       | Ownership               |
| ------- | ------------- | ----------------------- |
| **1xx** | Informational | Transport / infra       |
| **2xx** | Success       | Client did it right     |
| **3xx** | Redirection   | Resource moved / cached |
| **4xx** | Client error  | Client mistake          |
| **5xx** | Server error  | Server failed           |


---

## 1xx – Informational (Rare but Important)

Used mostly in advanced infra / streaming / proxies

Common Ones
- 100 Continue
  
  Client asks: “Can I send the body?”
  
  Server says: “Yes, continue.”

📌 Used in: large uploads, proxies, HTTP/2

📌 Most APIs never manually return these

---

## 2xx – Success (But Which One Matters)

200 vs 201 vs 204 (CRITICAL)

| Code               | Use When                   | Body               |
| ------------------ | -------------------------- | ------------------ |
| **200 OK**         | Read / update success      | Yes                |
| **201 Created**    | Resource created           | Yes (new resource) |
| **204 No Content** | Success, nothing to return | ❌ No body         |

---
## 200 OK

✔ GET success

✔ PUT update with response

✔ POST that doesn’t create a resource

GET /users/42 → 200 OK

---

## 201 Created (VERY IMPORTANT)

✔ Resource created

✔ Must include Location header

POST /users → 201 Created

Location: /users/43

📌 Interview gold:
> “201 means creation + discoverability via Location header.”

---

## 204 No Content

✔ DELETE success

✔ PUT/PATCH success without response

✔ Logout APIs

⚠️ Rule:

❌ No response body allowed

---

## 3xx – Redirection (Mostly Ignored, But Powerful)

| Code    | Meaning              |
| ------- | -------------------- |
| **301** | Permanent move       |
| **302** | Temporary move       |
| **304** | Not modified (cache) |

📌 304 Not Modified is huge for performance

Frontend cache validation uses it

---

## 4xx – Client Errors (Client Must Fix)

400 vs 401 vs 403 (INTERVIEW FAVORITE)

| Code                 | Meaning                       | Who fixes |
| -------------------- | ----------------------------- | --------- |
| **400 Bad Request**  | Invalid request               | Client    |
| **401 Unauthorized** | Not authenticated             | Client    |
| **403 Forbidden**    | Authenticated but not allowed | Client    |


---

## 400 Bad Request

✔ Validation failure

✔ Malformed JSON

✔ Missing required fields

{

  "error": "email is required"
  
}


📌 Not auth-related.

---

## 401 Unauthorized

✔ Token missing

✔ Token expired

✔ Invalid credentials

📌 Must include:

WWW-Authenticate: Bearer

---

## 403 Forbidden

✔ Token valid

✔ User logged in

✔ But lacks permission

Example:

User tries to delete admin account

📌 Key distinction:
> 401 → “Who are you?”
> 403 → “I know you. You’re not allowed.”

---

## 404 vs 410 (Subtle but Advanced)

| Code              | Meaning                          |
| ----------------- | -------------------------------- |
| **404 Not Found** | Never existed OR hidden          |
| **410 Gone**      | Existed, now permanently deleted |

404 Not Found

✔ Resource doesn’t exist

✔ OR you want to hide its existence (security)

---

## 410 Gone (RARE but EXPERT)

✔ Resource existed

✔ Will never come back

📌 Used in:
- Deleted accounts
- Deprecated API versions

📌 Tells clients:
> “Stop retrying. Remove bookmarks.”

---

## 5xx – Server Errors (Server Failed)

500 vs 503 (Production Critical)

| Code                          | Meaning          | Retry?       |
| ----------------------------- | ---------------- | ------------ |
| **500 Internal Server Error** | Bug / crash      | ❌ Usually no |
| **503 Service Unavailable**   | Temporary outage | ✔ Yes        |

---

## 500 Internal Server Error

✔ Null pointer

✔ Unhandled exception

✔ Logic bug

📌 Never blame client here

---

## 503 Service Unavailable

✔ Database down

✔ Dependency timeout

✔ Maintenance mode

📌 Best practice:

Retry-After: 30


📌 Payment APIs rely heavily on this

---

# 🔥 HTTP STATUS CODE DECISION CHART (EXACT USAGE)

Did request reach server?

│

├── No → Network / timeout (client-side)

│

└── Yes

    │
    
    ├── Is request malformed?
    
    │     └── 400
    
    │
    
    ├── Is authentication missing/invalid?
    
    │     └── 401
    
    │
    
    ├── Is user authenticated but not allowed?
    
    │     └── 403
    
    │
    
    ├── Does resource exist?
    
    │     ├── No → 404
    
    │     └── Existed but permanently deleted → 410
    
    │
    
    ├── Is request successful?
    
    │     ├── Created → 201
    
    │     ├── Success with body → 200
    
    │     └── Success without body → 204
    
    │
    
    └── Server failed?
    
          ├── Temporary (retry safe) → 503
          
          └── Bug/crash → 500


---

















