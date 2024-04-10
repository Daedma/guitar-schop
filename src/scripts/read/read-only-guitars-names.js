//load("D:/Eagle Guitars git/guitar-schop/src/scripts/read/read-only-guitar-names.js")

print(db.goods_collection.find({ type: "Guitar" }, {name: 1, _id: 0}))
