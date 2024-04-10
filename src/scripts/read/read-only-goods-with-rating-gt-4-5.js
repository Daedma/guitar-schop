//load("D:/Eagle Guitars git/guitar-schop/src/scripts/read/read-only-goods-with-rating-gt-4-5.js")

print(db.goods_collection.find({ rate: {$gt: 4.5} },
    {name: 1, _id: 0}))