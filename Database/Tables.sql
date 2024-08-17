Books Table:
    book_id (Primary Key)
    title
    author
    category
    publication_date
    ISBN
    quantity_available
    description
    create date
    update date

Users Table:
    user_id (Primary Key)
    first_name
    last_name
    password (hashed)
    email
    role (e.g., admin, user)
    isDeleted
    create date
    update date

Borrow Table:
    borrow_id (Primary Key)
    user_id (Foreign Key referencing Users table)
    book_id (Foreign Key referencing Books table)
    Borrow_date
    return_date
    status (e.g., borrowed, returned)

Reservations Table:
    reservation_id (Primary Key)
    user_id (Foreign Key referencing Users table)
    book_id (Foreign Key referencing Books table)
    reservation_date
    status (e.g., active, fulfilled)

Transactions Table:
    transaction_id (Primary Key)
    user_id (Foreign Key referencing Users table)
    type (e.g., checkout, return, reservation)
    item_id (book_id or reservation_id)
    transaction_date

Fines Table:
    fine_id (Primary Key)
    user_id (Foreign Key referencing Users table)
    amount
    reason
    status (e.g., paid, unpaid)
    fine_date

Authors Table:
    author_id (Primary Key)
    author_name

category Table:
    category_id (Primary Key)
    category_name

Order Table:
order_id (Primary Key)
first_name
last_name
address
pincode
city
state
email
phone
