# 🔹 Day 25 – Resource Relationships
- Nested resources
- When nesting becomes a problem

---

## 1. What is a Resource Relationship?

In REST APIs, everything is treated as a resource.

Example:

- users
- orders
- products
- comments
- payments

But real systems are not isolated.

Resources are connected.

Example:

- A user has many orders
- A post has many comments
- A product belongs to a category
- A student enrolls in courses

These connections are called:

## Resource Relationships

---

## 2. Real World Thinking

Think about:

YouTube

### Relationship examples:
- Channel → Videos
- Video → Comments
- Comment → Replies
- User → Subscriptions

Or:

### E-commerce
- User → Cart
- User → Orders
- Order → Items
- Product → Reviews

Without relationships, APIs become useless.

---

## 3. Types of Relationships
### One-to-One (1:1)

Example:
- User → Profile
- Employee → ID Card

One resource owns exactly one another resource.

Example endpoint:

GET /users/10/profile

### One-to-Many (1:N)

Most common.

Example:

- User → Orders
- Post → Comments
- Category → Products

Example:

GET /users/10/orders

User 10 has many orders.

### Many-to-Many (M:N)

Advanced relationship.

Example:

- Students ↔ Courses
- Users ↔ Roles
- Products ↔ Tags

A student can join many courses.

A course has many students.

Usually requires a join table.

---

## 4. Nested Resources

Now comes the main topic.

## What is Nested Resource?

Nested resource means:

A child resource is represented under its parent resource.

Structure:

/parent/{id}/child

Example:

/users/10/orders

Meaning:

“Orders belonging to user 10”

### Why Nested Resources Exist?

Because relationships matter.

Without nesting:

/orders?userId=10

With nesting:

/users/10/orders

Nested version gives better meaning.

It tells:

- ownership
- hierarchy
- context
- readability

---

## 5. Good Nested Resource Examples

### Example 1 – User Orders

GET /users/10/orders

Get all orders of user 10.

### Example 2 – Post Comments

GET /posts/5/comments

Get comments of post 5.

### Example 3 – Order Items

GET /orders/100/items

Get items inside order 100.

### Example 4 – GitHub Style

Repositories of a user:

GET /users/munna/repos

Very natural.

---

## 6. Deep Understanding of Nesting

Nested resources represent:

### “Containment” or “ownership”

Example:

/users/10/orders/500

Means:

Order 500 belongs to user 10.

This adds validation power.

Server can verify:

- does order 500 belong to user 10?

Huge security benefit.

---

## 7. CRUD with Nested Resources

### Create Child Resource

POST /users/10/orders

Meaning:

Create order for user 10.

### Read Child Resource

GET /users/10/orders/500

### Update Child Resource

PUT /users/10/orders/500

### Delete Child Resource

DELETE /users/10/orders/500

---

## 8. When Nesting Becomes a Problem

VERY IMPORTANT INTERVIEW TOPIC.

Nested resources are good…

UNTIL developers overdo them.

### Problem 1 – URL Becomes Huge

Bad example:

/companies/1/departments/2/employees/5/projects/7/tasks/3/comments

This is a disaster.

Why?

- hard to read
- hard to maintain
- hard to route
- ugly APIs
- difficult frontend integration

  
### Problem 2 – Tight Coupling

Suppose:

/users/10/orders/500

What if later orders become independent?

Now API design breaks.

Too much nesting creates dependency between resources.


### Problem 3 – Resource Identity Confusion

Question:

Is order identified by:

/orders/500

OR:

/users/10/orders/500

If both exist:

Confusion starts.

### Problem 4 – Duplicate APIs

You may accidentally create:

/users/10/orders

/orders?userId=10

Now two APIs do same thing.

Maintenance nightmare.


### Problem 5 – Scaling Issues

Deep nesting can slow:
- authorization checks
- DB joins
- routing logic
- caching

Especially in microservices.

---

## 9. Expert Rule for Nesting

Industry best practice:

Nest maximum 1 or 2 levels

GOOD:

/users/10/orders

/orders/500/items

BAD:

/users/10/orders/500/items/9/reviews/2/replies

---

## 10. When Should You Use Nesting?

Use nesting when:

✅ Child cannot exist meaningfully alone

Example:

/orders/500/items

Item belongs to order.

✅ Relationship is strong ownership

Example:

/posts/10/comments

Comment belongs to post.

✅ Better readability is needed

Example:

/users/10/repos


---

## 11. When Should You Avoid Nesting?

Avoid when:

❌ Resource exists independently

Example:

Orders may exist independently.

Then:

/orders/500

is enough.

❌ Relationship is many-to-many

Example:

Students and courses.

Bad:

/students/1/courses/2

Better:

/enrollments

or

/courses/2/students

depending on use case.

❌ URLs become too deep

Never create monster URLs.


---

## 12. Flat Resource Design

Sometimes flat design is better.

Instead of:

/users/10/orders

Use:

/orders?userId=10

Advantages:
- scalable
- flexible filtering
- better search
- easier pagination

Very common in large-scale APIs.

---

## 13. Nested vs Query Parameter

Comparison:

| Nested               | Query Parameter     |
| -------------------- | ------------------- |
| `/users/10/orders`   | `/orders?userId=10` |
| Relationship-focused | Filter-focused      |
| Cleaner hierarchy    | More flexible       |
| Better readability   | Better scalability  |
| Good for ownership   | Good for searching  |


---

## 14. Real Industry Approach

Big companies usually combine both.

Example:

GitHub

Nested:

/users/munna/repos

Flat search:

/repos?language=java

---

## 15. Best REST API Design Rules
### Rule 1

Resources are nouns.

GOOD:

/users

/orders

/products

### BAD:

/getUsers

/createOrder

### Rule 2

Use nesting only for clear relationships.

### Rule 3

Avoid more than 2 nesting levels.

### Rule 4

If child resource has global identity → flatten it.

Example:

Order has unique ID globally.

So:

/orders/500

is enough.

### Rule 5

Use query params for filtering/searching.

Example:

/orders?status=PAID

/orders?userId=10

---

## 16. Practical Design Exercise

### Scenario 1 – Blogging App

Entities:
- Users
- Posts
- Comments

GOOD APIs:
- GET /posts
- GET /posts/10
- GET /posts/10/comments
- POST /posts/10/comments
- GET /comments/55

Notice:

Comments have their own identity too.

### Scenario 2 – E-Commerce

Entities:
- Users
- Orders
- Products

### GOOD:
- GET /users/10/orders
- GET /orders/500
- GET /orders/500/items

NOT:

/users/10/orders/500/items/2/products/7

---

## 17. Advanced Concept – Relationship Resource

Sometimes relationship itself becomes a resource.

Example:

Student enrolled in course.

Instead of:

/students/1/courses/5

Create:

/enrollments

Body:

{

  "studentId": 1,
  
  "courseId": 5
  
}

This is enterprise-level design thinking.

---

## 18. Microservice Perspective

In microservices:

Deep nesting becomes dangerous.

Why?

Because data may come from different services.

Example:

- User Service
- Order Service
- Payment Service

Deep nested APIs may require multiple service calls.

This increases:

- latency
- failure chance
- complexity

So microservices prefer flatter APIs.

---

## 19. API Architect Thinking

Junior developer thinks:

“Can I make this endpoint?”

Senior engineer thinks:

- Is this scalable?
- Is this future-proof?
- Will frontend suffer?
- Is ownership clear?
- Is caching possible?
- Will this break later?

That mindset difference creates high salary engineers.

---

## 20. Practical Spring Boot Design

Example:

Controller

@RestController

@RequestMapping("/users/{userId}/orders")

public class OrderController {

    @GetMapping
    public List<Order> getOrders(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }

    @PostMapping
    public Order createOrder(
            @PathVariable Long userId,
            @RequestBody Order order) {

        return service.createOrder(userId, order);
    }
}


---

### Service Layer Thinking

Validate ownership:

if(!order.getUserId().equals(userId)) {

    throw new RuntimeException("Invalid ownership");
}

This is why nesting matters.

---

## 21. Interview Questions

### Q1. What are nested resources?

Nested resources represent relationships between parent and child resources in REST APIs.

Example:

/users/10/orders

### Q2. Why use nested resources?
- better readability
- ownership clarity
- hierarchical representation
- validation/security


### Q3. What problems can over-nesting create?
- huge URLs
- tight coupling
- scaling issues
- duplicate APIs
- maintenance difficulty

  
### Q4. How deep should nesting be?

Usually maximum 2 levels.


### Q5. When should flat resources be preferred?

When resources have independent identity or flexible filtering is needed.


### 22. Expert-Level API Design Pattern

Best modern approach:

Hybrid Design

Use BOTH:

Nested

For ownership/navigation:

/users/10/orders

Flat

For direct access/search:

/orders/500

/orders?status=PAID

This is what large companies actually do.

### Day 25 Final Summary

Today you learned:

✅ Resource relationships

✅ One-to-one

✅ One-to-many

✅ Many-to-many

✅ Nested resources

✅ CRUD with nested APIs

✅ Over-nesting problems

✅ Flat resource design

✅ Query param filtering

✅ Microservice impact

✅ Enterprise API design thinking

✅ Hybrid REST architecture


---







