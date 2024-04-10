//load("D:/Eagle Guitars git/guitar-schop/src/scripts/update/update-ids.js")

db.users_collection.updateOne(
    {_id: ObjectId('66142fbee1ccd5fd5a9f9911')},
    {$set: {cart: [ ObjectId('66156300c80a11a21afcecc5') ]}})

db.reviews_collection.updateOne(
    {_id: ObjectId('66145fede1ccd5fd5a9f991d')},
    {$set: {author_id: ObjectId('661459e3e1ccd5fd5a9f991c'),
            goods_id: ObjectId('661465ede1ccd5fd5a9f9927')}})

db.orders_collection.updateOne(
    {_id: ObjectId('66146368e1ccd5fd5a9f9920')},
    {$set: {user_id: ObjectId('66142fbee1ccd5fd5a9f9911'),
            goods_id: [ ObjectId('661465ede1ccd5fd5a9f9927') ]}})

db.orders_collection.updateOne(
    {_id: ObjectId('66146368e1ccd5fd5a9f9921')},
    {$set: {user_id: ObjectId('66142fbee1ccd5fd5a9f9912'),
            goods_id: [ ObjectId('661465ede1ccd5fd5a9f9927'), ObjectId('661465ede1ccd5fd5a9f9926')]}})

db.goods_collection.updateMany(
    {type: "Guitar"},
    {$set: {categories_id: ObjectId('66146575e1ccd5fd5a9f9922')}})

db.goods_collection.updateMany(
    {type: "Strings"},
    {$set: {categories_id: ObjectId('66146575e1ccd5fd5a9f9923')}})

db.categories_collection.updateMany(
    {_id: ObjectId('66146575e1ccd5fd5a9f9922')},
    {$set: {goods_id: [ ObjectId('661465ede1ccd5fd5a9f9926'), ObjectId('66156300c80a11a21afcecc5') ]}})

db.categories_collection.updateOne(
    {_id: ObjectId('66146575e1ccd5fd5a9f9923')},
    {$set: {goods_id: [ ObjectId('661465ede1ccd5fd5a9f9927')]}})

print('All ids were updated.')