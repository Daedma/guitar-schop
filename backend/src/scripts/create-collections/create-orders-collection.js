//load("D:/Eagle Guitars git/guitar-schop/src/scripts/create/create-orders-collection.js")

db.orders_collection.insertMany( [
    {
        user_id: null,
        full_name: 'Шаповал Иван Антонович',
        goods_id: null, // струны
        cost: null,
        address: {
            index: 443086,
            city: 'Самара',
            street: 'Революционная',
            building: "50"
        },
        date: new Date('2024-03-18T16:50:44Z'),
        status: "completed"
    },

    {
        user_id: null,
        full_name: 'Хисматов Дамир Русланович',
        goods_id: null, // гитара, струны
        cost: null,
        address: {
            index: 443001,
            city: 'Самара',
            street: 'Молодогвардейская',
            building: "151",
            flat: 221
        },
        date: new Date('2024-04-10T16:50:44Z'),
        status: "accepted"
    }

] )

print('Orders collection was created.')