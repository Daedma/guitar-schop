//load("D:/Eagle Guitars git/guitar-schop/src/scripts/create/create-users-collection.js")

db.users_collection.insertMany( [
    {
        login: 'eeeeeeagle',
        password: 'qwerty',
        role: 'admin',
        cart: null
    },
    {
        login: 'ivanshapoval',
        password: 'asdffdsa',
        role: 'client',
        cart: null
    },
    {
        login: 'damirkhismatov',
        password: 'bgoysms',
        role: 'client',
        cart: null
    }
] )

print('Users collection was created.')
